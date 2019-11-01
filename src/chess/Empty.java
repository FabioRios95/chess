/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author coleb29
 */
public class Empty extends Piece {
    
        Empty(String colorPiece)
    {
        super(colorPiece);
    }
    
    @Override
    public Image getImage(){
        return image;
    }
    
    @Override
    public boolean move(int posX, int posY, Stage newWindow, boolean playerOneTurn, int lastX, int lastY)
    {
        return playerOneTurn;
    }
    @Override
    public void possibleMove(int t, int z, String colorBorder, Square[][] board){}
    public void removeMove(int t, int z){}
    
}
