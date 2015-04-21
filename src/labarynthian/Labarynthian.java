/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labarynthian;

import environment.ApplicationStarter;
/**
 *
 * @author ilovesoccer127
 */
public class Labarynthian {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ApplicationStarter.run("Labarynthian", new LabarynthianEnvironment());
//        ApplicationStarter.run(null, "Labarynthian", null, new LabarynthianEnvironment);

    }
    
}
