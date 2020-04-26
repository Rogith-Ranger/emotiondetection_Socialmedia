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

import dz.univjijel.jsentiwordnet.core.JSentiWordNet;
import java.util.Scanner;

/**
 *
 * @author Tarek Boutefara <t_boutefara@esi.dz>
 */
public class CommandLineLoop {
    
    public static void cliLoop() {
        boolean looping = true;
        Scanner input = new Scanner(System.in);
        
        while(looping){
            
            String commandLine = input.nextLine();
            String[] commandParts = commandLine.split(" ");
            
            if(commandParts.length < 1){
                System.out.println("Empty command");
                continue;
            }
            
            switch(commandParts[0]){
                case Commands.BY_ID :
                    if(commandParts.length < 2) {
                        System.out.println("Id not given");
                    } else {
                        System.out.println("Finding the synset with id : " + commandParts[1]);
                        System.out.println(JSentiWordNet.findById(commandParts[1]));
                    }
                    break;
                    
                case Commands.TERM :
                    if(commandParts.length < 2) {
                        System.out.println("Term not given");
                    } else {
                        System.out.println("Finding the synset with term : " + commandParts[1]);
                        System.out.println(JSentiWordNet.findTerm(commandParts[1]));
                    }
                    break;
                    
                case Commands.NATIVE :
                    if(commandParts.length < 2) {
                        System.out.println("Native format not given");
                    } else if (commandParts[1].contains("#")) {
                        System.out.println("Finding the synset with native format : " + commandParts[1]);
                        System.out.println(JSentiWordNet.findNativeTerm(commandParts[1]));
                    } else {
                        System.out.println("Native format incorrect (term#number)");
                    }
                    break;
                    
                case Commands.IN_GLOSS :
                    if(commandParts.length < 2) {
                        System.out.println("String to find not given");
                    } else {
                        System.out.println("Finding in synset gloss : " + commandParts[1]);
                        System.out.println(JSentiWordNet.findInGloss(commandParts[1]));
                    }
                    break;
                    
                case Commands.HELP :
                    System.out.println(Commands.HELP_CONTENT);
                    break;
                    
                case Commands.QUIT :
                    System.out.println("Bye");
                    looping = false;
                    break;
                
                default:
                    System.out.println("Unknown command.");
                    
            }
            
        }
        
    }
    
}
