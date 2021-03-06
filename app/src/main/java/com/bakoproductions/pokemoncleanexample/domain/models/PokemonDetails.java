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

package com.bakoproductions.pokemoncleanexample.domain.models;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Michael on 16/4/2017.
 *
 * The data that the PokemonDetailsPresenter needs in order to operate
 */
public class PokemonDetails implements Serializable {
    private String id = "";
    private int weight;
    private int baseXP;

    private ArrayList<String> types;
    private ArrayList<PokemonAbility> abilities;
    private ArrayList<PokemonStat> stats;

    public PokemonDetails() {}

    public PokemonDetails(String id) {
        setId(id);
    }

    public void setId(String id) {
        if (id == null) {
            return;
        }

        this.id = id;
    }

    public String getId() {
        return id;
    }

    public int getWeight() {
        return weight;
    }

    /**
     * Formats the weight to be displayed to the user. The weight is formatted to kg.
     * @return The weight formatted to kg.
     */
    public String getWeightKG() {
        float weightF = weight / 10.0f;

        double weightD = weightF - Math.floor(weightF);

        if (weightD == 0) {
            return new DecimalFormat("#", new DecimalFormatSymbols(Locale.US)).format(weightF);
        } else {
            return new DecimalFormat("#.#", new DecimalFormatSymbols(Locale.US)).format(weightF);
        }
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getBaseXP() {
        return baseXP;
    }

    public void setBaseXP(int baseXP) {
        this.baseXP = baseXP;
    }

    public ArrayList<String> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<String> types) {
        this.types = types;
    }

    public ArrayList<PokemonAbility> getAbilities() {
        return abilities;
    }

    public void setAbilities(ArrayList<PokemonAbility> abilities) {
        this.abilities = abilities;
    }

    public ArrayList<PokemonStat> getStats() {
        return stats;
    }

    public void setStats(ArrayList<PokemonStat> stats) {
        this.stats = stats;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj instanceof PokemonDetails) {
            PokemonDetails details = (PokemonDetails) obj;
            return id.equals(details.getId());
        } else {
            return false;
        }
    }
}
