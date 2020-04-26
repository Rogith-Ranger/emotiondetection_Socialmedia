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
package dz.univjijel.jsentiwordnet.textscoring;

import dz.univjijel.jsentiwordnet.textpreprocessing.text.Text;
import dz.univjijel.jsentiwordnet.textpreprocessing.text.Word;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Tarek Boutefara <t_boutefara@esi.dz>
 */
public class JuliaAndNeeleFeatures implements ScoringFeatures {
    
    private double sumAll;
    private double averageAll;
    private double averageNonZero;

    public double getSumAll() {
        return sumAll;
    }

    public double getAverageAll() {
        return averageAll;
    }

    public double getAverageNonZero() {
        return averageNonZero;
    }

    @Override
    public void calculate(Text text) {
        
        int wordCount = 0;
        int nonZeroCount = 0;
        sumAll = 0;
        
        for(Word word : text.getWords()){
            sumAll += word.getScore();
            wordCount++;
            if(word.getScore() != 0.0)
                nonZeroCount++;
        }
        
        averageAll = sumAll / wordCount;
        averageNonZero = sumAll / nonZeroCount;
        
    }

    @Override
    public Map<String, Double> getFeatureValues() {
        Map<String, Double> result = new HashMap<>();
        result.put("SumAll", sumAll);
        result.put("AverageAll", averageAll);
        result.put("AverageNonZero", averageNonZero);
        return result;
    }

    @Override
    public List<String> getFeatures() {
        return Arrays.asList("SumAll", "AverageAll", "AverageNonZero");
    }
    
}
