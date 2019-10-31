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
            
            if(isValid(leftTwo,upOne) && !board[t][z].getPiece().color.equals(board[leftTwo][upOne].getPiece().color))
            {
                        board[leftTwo][upOne].getPiece().setBorder(leftTwo, upOne, "black", board);
                        possible.offer(new int[]{leftTwo,upOne});
            }
            if(isValid(leftTwo,downOne) && !board[t][z].getPiece().color.equals(board[leftTwo][downOne].getPiece().color))
            {
                        board[leftTwo][downOne].getPiece().setBorder(leftTwo, downOne, "black", board);
                        possible.offer(new int[]{leftTwo,downOne});
            }
            if(isValid(rightTwo,upOne) && !board[t][z].getPiece().color.equals(board[rightTwo][upOne].getPiece().color))
            {
                        board[rightTwo][upOne].getPiece().setBorder(rightTwo, upOne, "black", board);
                        possible.offer(new int[]{rightTwo,upOne});
            }
            if(isValid(rightTwo,downOne) && !board[t][z].getPiece().color.equals(board[rightTwo][downOne].getPiece().color))
            {
                        board[rightTwo][downOne].getPiece().setBorder(rightTwo, downOne, "black", board);
                        possible.offer(new int[]{rightTwo,downOne});
            }
            
            if(isValid(diagLeft,upTwo) && !board[t][z].getPiece().color.equals(board[diagLeft][upTwo].getPiece().color))
            {
                        board[diagLeft][upTwo].getPiece().setBorder(diagLeft, upTwo, "black", board);
                        
            }
            if(isValid(diagRight,upTwo) && !board[t][z].getPiece().color.equals(board[diagRight][upTwo].getPiece().color))
            {
                        board[diagRight][upTwo].getPiece().setBorder(diagRight, upTwo, "black", board);
                        
            }
            if(isValid(diagLeft,downTwo) && !board[t][z].getPiece().color.equals(board[diagLeft][downTwo].getPiece().color))
            {
                        board[diagLeft][downTwo].getPiece().setBorder(diagLeft, downTwo, "black", board);
                        
            }
            if(isValid(diagRight,downTwo)  && !board[t][z].getPiece().color.equals(board[diagRight][downTwo].getPiece().color))
            {
                        board[diagRight][downTwo].getPiece().setBorder(diagRight, downTwo, "black", board);
                        
            }
    }
    
    public void possibleMove(int t, int z){

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
            
            if(isValid(leftTwo,upOne) && !board[t][z].getPiece().color.equals(board[leftTwo][upOne].getPiece().color))
            {
                        board[leftTwo][upOne].getPiece().setBorder(leftTwo, upOne, "yellow", board);
                        possible.offer(new int[]{leftTwo,upOne});
            }
            if(isValid(leftTwo,downOne) && !board[t][z].getPiece().color.equals(board[leftTwo][downOne].getPiece().color))
            {
                        board[leftTwo][downOne].getPiece().setBorder(leftTwo, downOne, "yellow", board);
                        possible.offer(new int[]{leftTwo,downOne});
            }
            if(isValid(rightTwo,upOne) && !board[t][z].getPiece().color.equals(board[rightTwo][upOne].getPiece().color))
            {
                        board[rightTwo][upOne].getPiece().setBorder(rightTwo, upOne, "yellow", board);
                        possible.offer(new int[]{rightTwo,upOne});
            }
            if(isValid(rightTwo,downOne) && !board[t][z].getPiece().color.equals(board[rightTwo][downOne].getPiece().color))
            {
                        board[rightTwo][downOne].getPiece().setBorder(rightTwo, downOne, "yellow", board);
                        possible.offer(new int[]{rightTwo,downOne});
            }
            
            
            if(isValid(diagLeft,upTwo) && !board[t][z].getPiece().color.equals(board[diagLeft][upTwo].getPiece().color))
            {
                        board[diagLeft][upTwo].getPiece().setBorder(diagLeft, upTwo, "yellow", board);
                        possible.offer(new int[]{diagLeft,upTwo});
            }
            if(isValid(diagRight,upTwo) && !board[t][z].getPiece().color.equals(board[diagRight][upTwo].getPiece().color))
            {
                        board[diagRight][upTwo].getPiece().setBorder(diagRight, upTwo, "yellow", board);
                        possible.offer(new int[]{diagRight,upTwo});
            }
            if(isValid(diagLeft,downTwo) && !board[t][z].getPiece().color.equals(board[diagLeft][downTwo].getPiece().color))
            {
                        board[diagLeft][downTwo].getPiece().setBorder(diagLeft, downTwo, "yellow", board);
                        possible.offer(new int[]{diagLeft,downTwo});
            }
            if(isValid(diagRight,downTwo)  && !board[t][z].getPiece().color.equals(board[diagRight][downTwo].getPiece().color))
            {
                        board[diagRight][downTwo].getPiece().setBorder(diagRight, downTwo, "yellow", board);
                        possible.offer(new int[]{diagRight,downTwo});
            }

        }
}

