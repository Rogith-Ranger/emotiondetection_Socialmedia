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
package dz.univjijel.jsentiwordnet.cli;

/**
 *
 * @author Tarek Boutefara <t_boutefara@esi.dz>
 */
public class Commands {
    
    public static final String BY_ID = "id";
    public static final String TERM = "term";
    public static final String IN_GLOSS = "gloss";
    public static final String NATIVE = "native";
    public static final String HELP = "help";
    public static final String QUIT = "quit";
    
    
    public static final String HELP_CONTENT = "JSentiWordnet CLI (v 0.0.1)\n"
            + "Available commands :\n"
            + "1.\tid id_value\tSerach the synset having id_value as id (example : id 02739889).\n"
            + "2.\tterm term_value\tSerach for synsets having term among it terms (example : term car).\n"
            + "3.\tnative term_in_native\tSerach the term with the wordnet native term format : term#number (example : car#4).\n"
            + "4.\tgloss string_to_find\tSerach in synset gloss (example : gloss conveyance).\n"
            + "5.\tquit\tQuit.\n"
            + "6.\thelp\tDisplay this help message.\n";
    
}
