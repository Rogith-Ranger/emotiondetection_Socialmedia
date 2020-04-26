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
package dz.univjijel.jsentiwordnet.dict;

import dz.univjijel.jsentiwordnet.dict.util.IncorrectSynsetString;
import dz.univjijel.jsentiwordnet.dict.util.PartsOfSpeech;
import dz.univjijel.jsentiwordnet.textpreprocessing.WordPreprocessor;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Tarek Boutefara <t_boutefara@esi.dz>
 */
public class Synset {
    
    final private String id;
    final private int intId;
    
    final private PartsOfSpeech pos;
    
    final private double posScore;
    final private double negScore;
    
    final private List<Term> terms;
    
    final private String gloss;

    public Synset(String id, PartsOfSpeech pos, double posScore, double negScore, String gloss) throws IncorrectSynsetString {
        
        try{
            this.id = id;
            this.intId = Integer.parseInt(id);
            this.pos = pos;
            this.posScore = posScore;
            this.negScore = negScore;
            this.gloss = gloss;
        
            terms = new ArrayList<>();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            throw new IncorrectSynsetString(id);
        }
    }

    public String getId() {
        return id;
    }

    public int getIntId() {
        return intId;
    }

    public PartsOfSpeech getPos() {
        return pos;
    }

    public double getPosScore() {
        return posScore;
    }

    public double getNegScore() {
        return negScore;
    }

    public List<Term> getTerms() {
        return terms;
    }
    
    public Term getTerm(int position){
        return terms.get(position);
    }

    public String getGloss() {
        return gloss;
    }
    
    public Polarity getPolarity(){
        if(posScore > 0)
            return Polarity.Positive;
        else if (negScore > 0)
            return Polarity.Negative;
        else
            return Polarity.Objective;
    }
    
    public boolean containsTerm(String termValue){
        return terms.stream().anyMatch((term) -> (term.getValue().equals(termValue)));
    }
    
    public boolean containsNumberedTerm(String termValue, int termNumber){
       return terms.stream().anyMatch((term) -> (term.getValue().equals(termValue) && term.getNumber() == termNumber)); 
    }
    
    public boolean containsTermWithPartOfSpeech(String termValue, PartsOfSpeech termPos) {
        return terms.stream().anyMatch((term) -> (term.getValue().equals(termValue) && pos == termPos)); 
    }
    
    public boolean containsLemme(String lemme, WordPreprocessor preprocessor) {
        return terms.stream().anyMatch((term) -> (preprocessor.lemme(term.getValue()).equals(lemme)));
    }
    
    public static Synset synsetFactory(String toParse) throws IncorrectSynsetString {
        
        try{
            String[] parts = toParse.split("\t");
        
            PartsOfSpeech pos = getPart(parts[0]);
            
            String id = parts[1];
            double posScore = Double.parseDouble(parts[2]);
            double negScore = Double.parseDouble(parts[3]);
            String gloss = parts[5];
            
            Synset synset = new Synset(id, pos, posScore, negScore, gloss);
            
            String termString = parts[4];
            String[] terms = termString.split(" ");
            for (String term : terms) {
                String[] termParts = term.split("#");
                synset.getTerms().add(new Term(termParts[0], Integer.parseInt(termParts[1])));
            } 
            
            return synset;
            
        }catch(NumberFormatException | IncorrectSynsetString ex){
            System.out.println(ex.getMessage());
            throw new IncorrectSynsetString(toParse);
        }
        
    }
    
    private static PartsOfSpeech getPart(String abbreviation){
        switch(abbreviation){
            case "a" : return PartsOfSpeech.Adjective;
            case "n" : return PartsOfSpeech.Noun;
            case "v" : return PartsOfSpeech.Verb;
            case "r" : return PartsOfSpeech.Adverb;
            default : return PartsOfSpeech.Noun;
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.id);
        hash = 71 * hash + this.intId;
        hash = 71 * hash + Objects.hashCode(this.pos);
        hash = 71 * hash + (int) (Double.doubleToLongBits(this.posScore) ^ (Double.doubleToLongBits(this.posScore) >>> 32));
        hash = 71 * hash + (int) (Double.doubleToLongBits(this.negScore) ^ (Double.doubleToLongBits(this.negScore) >>> 32));
        hash = 71 * hash + Objects.hashCode(this.terms);
        hash = 71 * hash + Objects.hashCode(this.gloss);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Synset other = (Synset) obj;
        if (this.intId != other.intId) {
            return false;
        }
        if (Double.doubleToLongBits(this.posScore) != Double.doubleToLongBits(other.posScore)) {
            return false;
        }
        if (Double.doubleToLongBits(this.negScore) != Double.doubleToLongBits(other.negScore)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.gloss, other.gloss)) {
            return false;
        }
        if (this.pos != other.pos) {
            return false;
        }
        if (!terms.stream().noneMatch((term) -> (!other.getTerms().contains(term)))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Synset(" + id + ")\n" + gloss + "\n{\n\tpos=" + pos + ",\n\tposScore=" + posScore + ",\n\tnegScore=" + negScore + ",\n\tterms=" + terms + "\n}";
    }
    
    
}
