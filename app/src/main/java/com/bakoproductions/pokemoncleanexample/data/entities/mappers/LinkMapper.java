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

package com.bakoproductions.pokemoncleanexample.data.entities.mappers;

import com.bakoproductions.pokemoncleanexample.domain.models.Link;

/**
 * Created by Michael on 15/4/2017.
 */

public class LinkMapper {
    /**
     * Transforms a string url to a Link object
     * @param url A web url
     * @return The class that holds that url
     */
    public Link transform(String url) {
        Link link = new Link();
        link.setLinkUrl(url);
        return link;
    }
}
