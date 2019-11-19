/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

/*Java FX creates an application thread for running the application*/
import static javafx.application.Application.launch;

/**
 *
 * @author coleb29
 */
public class Chess {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(viewBoard.class,args); /* Launch a standalone(can be executed independently) application*/
    }
    
}
