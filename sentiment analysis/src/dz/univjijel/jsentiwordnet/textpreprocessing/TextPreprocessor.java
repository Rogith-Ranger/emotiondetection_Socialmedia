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
import dz.univjijel.jsentiwordnet.textpreprocessing.lemmatisation.Lemmatizer;
import dz.univjijel.jsentiwordnet.textpreprocessing.stopwords.StopWordManager;
import dz.univjijel.jsentiwordnet.textpreprocessing.text.Text;
import dz.univjijel.jsentiwordnet.textpreprocessing.text.Word;
import dz.univjijel.jsentiwordnet.textpreprocessing.tokenizer.Tokenizer;
import java.util.List;

/**
 *
 * @author Tarek Boutefara <t_boutefara@esi.dz>
 */
public class TextPreprocessor {
    
    Configuration config;

    public TextPreprocessor(Configuration config) {
        this.config = config;
    }

    public Text process(String string){
        Text text = new Text(string);
        process(text);
        return text;
    }
    
    public void process(Text text){
        tokenize(text);
        filter(text);
        normalize(text);
    }

    private void tokenize(Text text) {
        Tokenizer tokenizer = config.getTokenizer();
        String textContent = text.getValue();
        List<String> words = tokenizer.tokenize(textContent);
        words.stream().forEach((word) -> {
            text.getWords().add(new Word(word));
        });
    }

    private void filter(Text text) {
        StopWordManager swm = config.getStopWordManager();
        for(int i = text.getWords().size() - 1; i >= 0; i--){
            if(swm.isAStopWord(text.getWords().get(i).getValue()))
                text.getWords().remove(i);
        }
    }

    private void normalize(Text text) {
        Lemmatizer lemmatizer = config.getLemmatizer();
        text.getWords().stream().forEach((word) -> {
            word.setLemme(lemmatizer.lemme(word.getValue()));
        });
    }
    
}
