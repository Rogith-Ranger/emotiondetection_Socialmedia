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
package dz.univjijel.jsentiwordnet.core;

import dz.univjijel.jsentiwordnet.textpreprocessing.lemmatisation.Lemmatizer;
import dz.univjijel.jsentiwordnet.textpreprocessing.lemmatisation.TrancuteLemmatizer;
import dz.univjijel.jsentiwordnet.textpreprocessing.stopwords.SimpleStopWordManager;
import dz.univjijel.jsentiwordnet.textpreprocessing.stopwords.StopWordManager;
import dz.univjijel.jsentiwordnet.textpreprocessing.tokenizer.DefaultTokenizer;
import dz.univjijel.jsentiwordnet.textpreprocessing.tokenizer.Tokenizer;
import dz.univjijel.jsentiwordnet.textprocessor.disambiguator.Disambiguator;
import dz.univjijel.jsentiwordnet.textprocessor.disambiguator.FirstSynsetDisambiguator;
import dz.univjijel.jsentiwordnet.textprocessor.projector.Projector;
import dz.univjijel.jsentiwordnet.textprocessor.projector.SimpleProjector;
import dz.univjijel.jsentiwordnet.textscoring.JuliaAndNeeleFeatures;
import dz.univjijel.jsentiwordnet.textscoring.ScoringFeatures;

/**
 *
 * @author Tarek Boutefara <t_boutefara@esi.dz>
 */
public class Configuration {
    
    String dictPath;
    Lemmatizer lemmatizer;
    Tokenizer tokenizer;
    Projector projector;
    Disambiguator disambiguator;
    ScoringFeatures scoringFeatures;

    public ScoringFeatures getScoringFeatures() {
        return scoringFeatures;
    }

    public void setScoringFeatures(ScoringFeatures scoringFeatures) {
        this.scoringFeatures = scoringFeatures;
    }
    

    public Projector getProjector() {
        return projector;
    }

    public void setProjector(Projector projector) {
        this.projector = projector;
    }

    public Disambiguator getDisambiguator() {
        return disambiguator;
    }

    public void setDisambiguator(Disambiguator disambiguator) {
        this.disambiguator = disambiguator;
    }

    public Tokenizer getTokenizer() {
        return tokenizer;
    }

    public void setTokenizer(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }
    StopWordManager stopWordManager;

    public Configuration() {
        dictPath = "./SentiWordNet.txt";
    }

    public Configuration(String dictPath) {
        this.dictPath = dictPath;
    }

    public String getDictPath() {
        return dictPath;
    }

    public void setDictPath(String dictPath) {
        this.dictPath = dictPath;
    }

    public Lemmatizer getLemmatizer() {
        return lemmatizer;
    }

    public StopWordManager getStopWordManager() {
        return stopWordManager;
    }

    public void setLemmatizer(Lemmatizer lemmatizer) {
        this.lemmatizer = lemmatizer;
    }

    public void setStopWordManager(StopWordManager stopWordManager) {
        this.stopWordManager = stopWordManager;
    }
    
    public static Configuration defaultConfigurationFactory() {
        Configuration config = new Configuration();
        config.setLemmatizer(new TrancuteLemmatizer(5));
        config.setStopWordManager(new SimpleStopWordManager());
        config.setTokenizer(new DefaultTokenizer());
        config.setProjector(new SimpleProjector());
        config.setDisambiguator(new FirstSynsetDisambiguator());
        config.setScoringFeatures(new JuliaAndNeeleFeatures());
        return config;
    }
    
    public static Configuration defaultConfigurationFactory(String dictPath) {
        Configuration config = new Configuration(dictPath);
        config.setLemmatizer(new TrancuteLemmatizer(5));
        config.setStopWordManager(new SimpleStopWordManager());
        config.setTokenizer(new DefaultTokenizer());
        config.setProjector(new SimpleProjector());
        config.setDisambiguator(new FirstSynsetDisambiguator());
        config.setScoringFeatures(new JuliaAndNeeleFeatures());
        return config;
    }
    
}
