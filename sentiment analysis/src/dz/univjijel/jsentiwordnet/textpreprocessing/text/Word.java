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
package dz.univjijel.jsentiwordnet.textpreprocessing.text;

import dz.univjijel.jsentiwordnet.dict.Synset;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tarek Boutefara <t_boutefara@esi.dz>
 */
public class Word {

    String value;
    String lemme;

    double score;
    String synsetId;
    
    List<Synset> condidatSynsets;

    public Word() {
        condidatSynsets = new ArrayList<>();
    }

    public Word(String value) {
        this.value = value;
        condidatSynsets = new ArrayList<>();
    }

    public List<Synset> getCondidatSynsets() {
        return condidatSynsets;
    }

    public void setCondidatSynsets(List<Synset> condidatSynsets) {
        this.condidatSynsets = condidatSynsets;
    }
    public String getLemme() {
        return lemme;
    }
    public void setLemme(String lemme) {
        this.lemme = lemme;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getSynsetId() {
        return synsetId;
    }

    public void setSynsetId(String synsetId) {
        this.synsetId = synsetId;
    }

}
