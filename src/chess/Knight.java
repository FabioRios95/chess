/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import java.io.File;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.stage.Stage;

/**
 *
 * @author coleb29
 */
public class Knight extends Piece {
Knight(String colorPiece)
    {
        super(colorPiece);
    }
    
    @Override
    public Image getImage(){
        return image;
    }
    
    public void canMove(int x, int y, int t, int z, String color , Square[][] board)
    {
     if(isValid(x,y) && !board[t][z].getPiece().color.equals(board[x][y].getPiece().color))
            {
                        board[x][y].getPiece().setBorder(x, y, color, board);
                        //if(color.equals("yellow"))
                            possible.offer(new int[]{x,y});
            }
    }
  
    public void removeMove(int t, int z)
    {
    int downTwo,upTwo, diagLeft, diagRight;
    int downOne, upOne, leftTwo, rightTwo;
            if(color.equals("black"))
            {
                downTwo=z-2;
                downOne=z-1;
                upTwo=z+2;
                upOne=z+1;
                diagLeft=t-1;
                leftTwo=t-2;
                diagRight=t+1;
                rightTwo=t+2;
            } 
            else if(color.equals("white")){
                downTwo=z+2;
                downOne=z+1;
                upTwo=z-2;
                upOne=z-1;
                diagLeft=t-1;
                leftTwo=t-2;
                rightTwo=t+2;
                diagRight=t+1;
            }
            else
            {
                downTwo=8;
                upTwo=8;
                diagLeft=8;
                diagRight=8;
                downOne=8;
                upOne=8;
                leftTwo=8;
                rightTwo=8;
            }
            canMove(leftTwo, upOne, t, z,"black", board);
            canMove(leftTwo, downOne, t, z,"black", board);
            canMove(rightTwo, upOne, t, z,"black", board);
            canMove(rightTwo, downOne, t, z,"black", board);

            canMove(diagLeft, upTwo, t, z,"black", board);
            canMove(diagRight, upTwo, t, z,"black", board);
            canMove(diagLeft, downTwo, t, z,"black", board);
            canMove(diagRight, downTwo, t, z,"black", board);
    }
    
    @Override
    public void possibleMove(int t, int z, String colorBorder, Square[][] board){

    int downTwo,upTwo, diagLeft, diagRight;
    int downOne, upOne, leftTwo, rightTwo;
            if(color.equals("black"))
            {
                downTwo=z-2;
                downOne=z-1;
                upTwo=z+2;
                upOne=z+1;
                diagLeft=t-1;
                leftTwo=t-2;
                diagRight=t+1;
                rightTwo=t+2;
            } 
            else if(color.equals("white")){
                downTwo=z+2;
                downOne=z+1;
                upTwo=z-2;
                upOne=z-1;
                diagLeft=t-1;
                leftTwo=t-2;
                rightTwo=t+2;
                diagRight=t+1;
            }
            else
            {
                downTwo=8;
                upTwo=8;
                diagLeft=8;
                diagRight=8;
                downOne=8;
                upOne=8;
                leftTwo=8;
                rightTwo=8;
            }
            canMove(leftTwo, upOne, t, z, colorBorder, board);
            canMove(leftTwo, downOne, t, z,colorBorder, board);
            canMove(rightTwo, upOne, t, z,colorBorder, board);
            canMove(rightTwo, downOne, t, z,colorBorder, board);

            canMove(diagLeft, upTwo, t, z,colorBorder, board);
            canMove(diagRight, upTwo, t, z,colorBorder, board);
            canMove(diagLeft, downTwo, t, z,colorBorder, board);
            canMove(diagRight, downTwo, t, z,colorBorder, board);
        
        }
}

