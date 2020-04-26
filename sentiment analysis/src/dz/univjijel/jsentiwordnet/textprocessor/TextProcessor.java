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
package dz.univjijel.jsentiwordnet.textprocessor;

import dz.univjijel.jsentiwordnet.core.Configuration;
import dz.univjijel.jsentiwordnet.textpreprocessing.text.Text;
import dz.univjijel.jsentiwordnet.textscoring.ScoringFeatures;

/**
 *
 * @author Tarek Boutefara <t_boutefara@esi.dz>
 */
public class TextProcessor {
    
    Configuration config;

    public TextProcessor(Configuration config) {
        this.config = config;
    }
    
    public void process(Text text){
        project(text);
        disambiguate(text);
        scoring(text);
    }

    private void project(Text text) {
        config.getProjector().project(text);
    }

    private void disambiguate(Text text) {
        config.getDisambiguator().disambiguate(text);
    }

    private void scoring(Text text) {
        ScoringFeatures sf = config.getScoringFeatures();
        sf.calculate(text);
        sf.getFeatures().stream().forEach((feature) -> {
            System.out.println(feature + " = " + sf.getFeatureValues().get(feature));
        });
    }
    
}
