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

import dz.univjijel.jsentiwordnet.dict.Dict;
import dz.univjijel.jsentiwordnet.dict.Synset;
import dz.univjijel.jsentiwordnet.dict.util.IncorrectSentiWordNetFile;
import dz.univjijel.jsentiwordnet.dict.util.PartsOfSpeech;
import dz.univjijel.jsentiwordnet.textpreprocessing.TextPreprocessor;
import dz.univjijel.jsentiwordnet.textpreprocessing.WordPreprocessor;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tarek Boutefara <t_boutefara@esi.dz>
 */
public class JSentiWordNet {
    
    public static Dict dict;
    public static Configuration config;
    public static TextPreprocessor textPreprocessor;
    public static WordPreprocessor wordPreprocessor;
    
    public static void init(Configuration c) throws IncorrectSentiWordNetFile{
        config = c;
        dict = Dict.dictFactory(config);
        textPreprocessor = new TextPreprocessor(config);
        wordPreprocessor = new WordPreprocessor(config);
        
    } 
    
    public static Synset findById(String id){
        for(Synset synset : dict.getSynsets()){
            if(synset.getId().equals(id))
                return synset;
        }
        return null;
    }
    
    public static Synset findByIntId(int id){
        for(Synset synset : dict.getSynsets()){
            if(synset.getIntId() == id)
                return synset;
        }
        return null;
    }
    
    public static List<Synset> findTerm(String term){
        List<Synset> synsets = new ArrayList<>();
        dict.getSynsets().stream().filter((synset) -> (synset.containsTerm(term))).forEach((synset) -> {
            synsets.add(synset);
        });
        return synsets;
    }
    
    public static List<Synset> findLemme(String lemme){
        List<Synset> synsets = new ArrayList<>();
        dict.getSynsets().stream().filter((synset) -> (synset.containsLemme(lemme, wordPreprocessor))).forEach((synset) -> {
            synsets.add(synset);
        });
        return synsets;
    }
    
    public static List<Synset> findNumberedTerm(String termValue, int termNumber){
        List<Synset> synsets = new ArrayList<>();
        dict.getSynsets().stream().filter((synset) -> (synset.containsNumberedTerm(termValue, termNumber))).forEach((synset) -> {
            synsets.add(synset);
        });
        return synsets;
    }
    
    public static List<Synset> findTermWithPartOfSpeech(String termValue, PartsOfSpeech pos){
        List<Synset> synsets = new ArrayList<>();
        dict.getSynsets().stream().filter((synset) -> (synset.containsTermWithPartOfSpeech(termValue, pos))).forEach((synset) -> {
            synsets.add(synset);
        });
        return synsets;
    }
    
    public static List<Synset> findInGloss(String string){
        List<Synset> synsets = new ArrayList<>();
        dict.getSynsets().stream().filter((synset) -> (synset.getGloss().contains(string))).forEach((synset) -> {
            synsets.add(synset);
        });
        return synsets;
    }
    
    public static List<Synset> findNativeTerm(String term){
        String[] parts = term.split("#");
        String termValue = parts[0];
        int termNumber = Integer.parseInt(parts[1]);
        List<Synset> synsets = new ArrayList<>();
        dict.getSynsets().stream().filter((synset) -> (synset.containsNumberedTerm(termValue, termNumber))).forEach((synset) -> {
            synsets.add(synset);
        });
        return synsets;
    }
    
}
