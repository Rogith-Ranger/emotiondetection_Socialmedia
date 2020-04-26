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

import java.util.Objects;

/**
 *
 * @author Tarek Boutefara <t_boutefara@esi.dz>
 */
public class Term {
    
    final private String value;
    final private int number;

    public Term(String value, int number) {
        this.value = value;
        this.number = number;
    }

    public String getValue() {
        return value;
    }

    public int getNumber() {
        return number;
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
        final Term other = (Term) obj;
        if (this.number != other.number) {
            return false;
        }
        return Objects.equals(this.value, other.value);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.value);
        hash = 71 * hash + this.number;
        return hash;
    }

    @Override
    public String toString() {
        return value + "#" + number;
    }
    
    
    
    
    
}
