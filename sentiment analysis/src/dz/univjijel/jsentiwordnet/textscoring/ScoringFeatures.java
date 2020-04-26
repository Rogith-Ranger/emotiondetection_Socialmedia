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
import java.util.List;
import java.util.Map;

/**
 *
 * @author Tarek Boutefara <t_boutefara@esi.dz>
 */
public interface ScoringFeatures {
    
    public void calculate(Text text);
    public Map<String, Double> getFeatureValues();
    public List<String> getFeatures();
    
}
