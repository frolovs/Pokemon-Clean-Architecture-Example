/*
 * Copyright 2017 Michael Bakogiannis, Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bakoproductions.pokemoncleanexample.presentation.presenters;

import com.bakoproductions.pokemoncleanexample.R;
import com.bakoproductions.pokemoncleanexample.data.repository.PokemonRepository;
import com.bakoproductions.pokemoncleanexample.data.repositoryCached.PokemonDetailsDataStatic;
import com.bakoproductions.pokemoncleanexample.domain.interactors.GetPokemonDetailsUseCase;
import com.bakoproductions.pokemoncleanexample.domain.models.Pokemon;
import com.bakoproductions.pokemoncleanexample.domain.models.PokemonAbility;
import com.bakoproductions.pokemoncleanexample.domain.models.PokemonDetails;
import com.bakoproductions.pokemoncleanexample.domain.models.PokemonStat;
import com.bakoproductions.pokemoncleanexample.domain.models.errors.PokemonDetailsError;
import com.bakoproductions.pokemoncleanexample.presentation.screens.PokemonDetailsScreen;
import com.squareup.otto.Subscribe;

/**
 * Created by Michael on 17/4/2017.
 */

public class PokemonDetailsPresenter extends BasePresenter {
    private PokemonDetailsScreen screen;

    /**
     * Provides the presenter instance
     *
     * @param screen The reference to the screen implementor
     */
    public PokemonDetailsPresenter(PokemonDetailsScreen screen) {
        this.screen = screen;
    }

    /**
     * In order for this presenter to start, we need to provide the Pokemon we want to present
     * @param pokemon The instance of the Pokemon we need to show the details for.
     */
    public void initialize(Pokemon pokemon) {
        screen.initializeUI();
        screen.setToolbarTitle(getString(R.string.pokemon_details, pokemon.getNameFormatted()));

        // Registering subscribers
        register();

        // Filling the data we can show without the need for calling a use case such as the name
        // and the avatar
        fillPokemon(pokemon);

        // Getting the rest of the details
        getDetails(pokemon);
    }

    public void destroy() {
        unregister();
    }

    public void onWeightInteracted() {
        screen.showLegend(getString(R.string.leg_weight));
    }

    public void onBaseXPInteracted() {
        screen.showLegend(getString(R.string.leg_base_xp));
    }

    public void onTypesInteracted() {
        screen.showLegend(getString(R.string.leg_types));
    }

    public void onAbilitiesInteracted() {
        screen.showLegend(getString(R.string.leg_abilities));
    }

    public void onStatsInteracted() {
        screen.showLegend(getString(R.string.leg_stats));
    }

    public void getDetails(Pokemon pokemon) {
        // Showing loading before requesting the data
        screen.showLoading();

        // This use case supports cashing. If the details of this pokemon exist in the cash then
        // we will receive them instantly in the onPokemonDetailsReceived(...) method
        executeUseCase(new GetPokemonDetailsUseCase(
                pokemon.getId(),
                new PokemonRepository(),
                PokemonDetailsDataStatic.get()));
    }

    @Subscribe
    public void onPokemonDetailsReceived(PokemonDetails details) {
        // Hiding the loading progress
        screen.hideLoading();
        screen.showDetailsPanel();

        // And filling the details
        fillDetails(details);
    }

    @Subscribe
    public void onPokemonDetailsError(PokemonDetailsError error) {
        screen.hideLoading();
        screen.showNameAndAvatarPanel();

        if (error.isNetworkError()) {
            screen.showNoInternetError();
        } else {
            screen.showError(error);
        }
    }

    public void fillPokemon(Pokemon pokemon) {
        screen.setAvatar(pokemon.getAvatarUrl());
        screen.setName(pokemon.getNameFormatted());
    }

    public void fillDetails(PokemonDetails details) {
        fillWeight(details);
        fillBaseXP(details);
        fillTypes(details);
        fillAbilities(details);
        fillStats(details);
    }

    public void fillWeight(PokemonDetails details) {
        screen.setWeight(details.getWeightKG() + getString(R.string.kg));
    }

    public void fillBaseXP(PokemonDetails details) {
        screen.setBaseXP(details.getBaseXP() + getString(R.string.xp));
    }

    public void fillTypes(PokemonDetails details) {
        StringBuilder builder = new StringBuilder();

        for (int i=0;i<details.getTypes().size();i++) {
            if (i != 0) {
                builder.append("\n");
            }
            builder.append(details.getTypes().get(i));
        }

        screen.setTypes(builder.toString());
    }

    public void fillAbilities(PokemonDetails details) {
        StringBuilder builder = new StringBuilder();

        for (int i=0;i<details.getAbilities().size();i++) {
            if (i != 0) {
                builder.append("\n");
            }

            PokemonAbility ability = details.getAbilities().get(i);
            String visibility =
                    ability.isHidden() ? getString(R.string.hidden) : getString(R.string.visible);
            builder.append(ability.getName())
                    .append(" - ").append(visibility);
        }

        screen.setAbilities(builder.toString());
    }

    public void fillStats(PokemonDetails details) {
        StringBuilder builder = new StringBuilder();

        for (int i=0;i<details.getStats().size();i++) {
            if (i != 0) {
                builder.append("\n");
            }

            PokemonStat stat = details.getStats().get(i);
            builder.append(stat.getName())
                    .append("\n  • ").append(getString(R.string.effort)).append("  ").append(stat.getEffort())
                    .append("\n  • ").append(getString(R.string.base_stat)).append("  ").append(stat.getBase());
        }

        screen.setStats(builder.toString());
    }

    // We usually want to use the context in the presenter. In order to mock it easily
    // we provide a method that gets the string for us.
    public String getString(int resource) {
        return screen.getContext().getString(resource);
    }

    public String getString(int resource, String arg) {
        return screen.getContext().getString(resource, arg);
    }
}
