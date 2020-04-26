/* 
 * Copyright (C) 2018 Tarek Boutefara <t_boutefara@esi.dz>.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package dz.univjijel.jsentiwordnet.textpreprocessing;

import dz.univjijel.jsentiwordnet.core.Configuration;

/**
 *
 * @author Tarek Boutefara <t_boutefara@esi.dz>
 */
public class WordPreprocessor {
    
    Configuration config;

    public WordPreprocessor(Configuration config) {
        this.config = config;
    }

    public String lemme(String terme) {
        return config.getLemmatizer().lemme(terme);
    }
    
    public boolean isStopWord(String terme){
        return config.getStopWordManager().isAStopWord(terme);
    }
    
}
