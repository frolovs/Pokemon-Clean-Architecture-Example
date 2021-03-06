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

package com.bakoproductions.pokemoncleanexample.data.entities.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Michael on 16/4/2017.
 */

public class Stat {
    @SerializedName("stat")
    @Expose
    private StatDetails statDetails;
    @SerializedName("effort")
    @Expose
    private Integer effort;
    @SerializedName("base_stat")
    @Expose
    private Integer baseStat;

    public StatDetails getStatDetails() {
        return statDetails;
    }

    public void setStatDetails(StatDetails statDetails) {
        this.statDetails = this.statDetails;
    }

    public Integer getEffort() {
        return effort;
    }

    public void setEffort(Integer effort) {
        this.effort = effort;
    }

    public Integer getBaseStat() {
        return baseStat;
    }

    public void setBaseStat(Integer baseStat) {
        this.baseStat = baseStat;
    }
}
