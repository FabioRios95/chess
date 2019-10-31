/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import java.io.File;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author coleb29
 */
public class King extends Piece {
    King(String colorPiece)
    {
        super(colorPiece);
    }
    
    
    @Override
    public boolean move(int posX, int posY, Stage newWindow, boolean playerOneTurn, int lastX, int lastY)
    {
        return playerOneTurn;
    }
    
    
}
