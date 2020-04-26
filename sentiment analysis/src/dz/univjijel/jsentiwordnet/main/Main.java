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
package dz.univjijel.jsentiwordnet.main;

import dz.univjijel.jsentiwordnet.cli.CommandLineLoop;
import dz.univjijel.jsentiwordnet.core.Configuration;
import dz.univjijel.jsentiwordnet.core.JSentiWordNet;
import dz.univjijel.jsentiwordnet.gui.Splash;
import dz.univjijel.jsentiwordnet.ws.WebInterrogationPublisher;

/**
 *
 * @author Tarek Boutefara <t_boutefara@esi.dz>
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            
            if(args.length != 1){
                System.out.println("Invalid parameter, usage :\n"
                        + "java JSentiWordNet gui\trun with GUI,\n"
                        + "java JSentiWordNet cli\trun in CLI,"
                        + "java JSentiWordNet ws\tTo start to web service.");
                System.exit(0);
            }
            
            String option = args[0];
            switch(option){
                
                case "gui" :
                    Splash splash = new Splash();
                    break;
                    
                case "cli" :
                    System.out.println("JSentiWordnet (v 0.0.1)");
                    System.out.println("Loading data ...");
                    JSentiWordNet.init(Configuration.defaultConfigurationFactory("./Dict/SentiWordNet.txt"));
                    System.out.println("Loading completed ...");
                    System.out.println("Enter a command, or \"help\" to get help");
                    CommandLineLoop.cliLoop();
                    break;
                    
                case "ws" :
                    JSentiWordNet.init(Configuration.defaultConfigurationFactory("./Dict/SentiWordNet.txt"));
                    WebInterrogationPublisher.startService();
                    break;
                    
                default :
                    System.out.println("Unknown option, please retry with gui, cli or ws.");
                    
            }
            
            // JSentiWordNet.init(Configuration.defaultConfigurationFactory("./Dict/SentiWordNet.txt"));
            // CommandLineLoop.cliLoop();
            // System.out.println(JSentiWordNet.findInGloss("woodwork"));
            // System.out.println(JSentiWordNet.findNativeTerm("cabinetwork#2"));
            // Démo
            /*
            TextPreprocessor pp = new TextPreprocessor(JSentiWordNet.config);
            TextProcessor p = new TextProcessor(JSentiWordNet.config);
            String positiveText = "I like you";
            Text pText = pp.process(positiveText);
            p.process(pText);
            pText.getWords().stream().forEach((word) -> {
            System.out.println(word.getLemme() + "  " + word.getScore() + "\n" + word.getCondidatSynsets());
            });
            System.out.println("\n===============================\n");
            String negativeText = "I hate you";
            Text nText = pp.process(negativeText);
            p.process(nText);
            nText.getWords().stream().forEach((word) -> {
            System.out.println(word.getLemme() + "  " + word.getScore() + "\n" + word.getCondidatSynsets());
            });
             */
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
}
