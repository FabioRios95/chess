/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;


import javafx.stage.Stage;


/**
 *
 * @author coleb29
 */
public class Pawn extends Piece {
    
Pawn(String colorPiece)
    {
        super(colorPiece);
        hasMoved=false;
    }
     
    @Override
    public void possibleMove(int t, int z, String colorBorder)
    {
        if(!hasMoved)
        {
            int upOne,upTwo, diagLeft, diagRight;
            if(color.equals("black"))
            {
                upOne=z+1;
                upTwo=z+2;
                diagLeft=t-1;
                diagRight=t+1;
            } 
            else if(color.equals("white")){
                upOne=z-1;
                upTwo=z-2;
                diagLeft=t-1;
                diagRight=t+1;
            }
            else
            {
                upOne=8;
                upTwo=8;
                diagLeft=8;
                diagRight=8;
            }
            if(isValid(diagLeft,upOne) && !board[diagLeft][upOne].getPiece().isEmpty() && !board[t][z].getPiece().color.equals(board[diagLeft][upOne].getPiece().color))
            {
                        board[diagLeft][upOne].getPiece().setBorder(diagLeft, upOne, colorBorder);
                        possible.offer(new int[]{diagLeft,upOne});
            }
            if(isValid(diagRight,upOne) && !board[diagRight][upOne].getPiece().isEmpty() && !board[t][z].getPiece().color.equals(board[diagRight][upOne].getPiece().color))
            {
                        board[diagRight][upOne].getPiece().setBorder(diagRight, upOne, colorBorder);
                        possible.offer(new int[]{diagRight,upOne});
            }
            if(isValid(t,upOne) && board[t][upOne].getPiece().isEmpty())
            {
                        board[t][upOne].getPiece().setBorder(t, upOne, colorBorder);
                        possible.offer(new int[]{t,upOne});
            }
            if((z == 1 || z == 6) && isValid(t,upTwo)&& board[t][upTwo].getPiece().isEmpty())
            {
                board[t][upTwo].getPiece().setBorder(t, upTwo, colorBorder);
  
                        possible.offer(new int[]{t,upTwo});
            }
        }
    }
    
        @Override
    public void removeMove(int t, int z)
    {
           if(!hasMoved)
        {
            int upOne,upTwo, diagLeft, diagRight;
            if(color.equals("black"))
            {
                upOne=z+1;
                upTwo=z+2;
                diagLeft=t-1;
                diagRight=t+1;
            } 
            else if(color.equals("white")){
                upOne=z-1;
                upTwo=z-2;
                diagLeft=t-1;
                diagRight=t+1;
            }
            else
            {
                upOne=8;
                upTwo=8;
                diagLeft=8;
                diagRight=8;
            }
            if(isValid(diagLeft,upOne) && !board[diagLeft][upOne].getPiece().isEmpty() && !board[t][z].getPiece().color.equals(board[diagLeft][upOne].getPiece().color))
                board[diagLeft][upOne].getPiece().setBorder(diagLeft, upOne, "black");
            if(isValid(diagRight,upOne) && !board[diagRight][upOne].getPiece().isEmpty() && !board[t][z].getPiece().color.equals(board[diagRight][upOne].getPiece().color))
                board[diagRight][upOne].getPiece().setBorder(diagRight, upOne, "black");
            if(isValid(t,upOne) && board[t][upOne].getPiece().isEmpty())
                board[t][upOne].getPiece().setBorder(t, upOne, "black");
            if((z == 1 || z == 6) && isValid(t,upTwo)&& board[t][upTwo].getPiece().isEmpty())
                board[t][upTwo].getPiece().setBorder(t, upTwo, "black");
        }
    }
    
    
}
