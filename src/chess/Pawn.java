/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import javafx.scene.layout.Background;
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
    public boolean move(int posX, int posY, Stage newWindow, boolean playerOneTurn, int lastX, int lastY)
    {
       while(!possible.isEmpty())
        {
           int[] points = possible.poll();
            if(posX != points[0] || posY != points[1])
            continue;
        if(playerOneTurn && board[lastX][lastY].getPiece().color.equals("white"))
        {
            System.out.println("Player Two's Turn");
            String updated = board[lastX][lastY].getPiece().updatePiece;
            board[lastX][lastY].setPiece(" ");
            board[posX][posY].setPiece(updated);
            playerOneTurn=false;
            hasMoved=true;
        }  
        else if(!playerOneTurn && board[lastX][lastY].getPiece().color.equals("black") )
        {
            System.out.println("Player One's Turn");
            String updated = board[lastX][lastY].getPiece().updatePiece;
            board[lastX][lastY].setPiece(" ");
            board[posX][posY].setPiece(updated);
            playerOneTurn=true;
            hasMoved=true;
        }
        }
        newWindow.close(); 
        return playerOneTurn;
    }
    
    @Override
    public void possibleMove(int t, int z)
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
                        board[diagLeft][upOne].getPiece().setBorder(diagLeft, upOne, "yellow", board);
                        possible.offer(new int[]{diagLeft,upOne});
            }
            if(isValid(diagRight,upOne) && !board[diagRight][upOne].getPiece().isEmpty() && !board[t][z].getPiece().color.equals(board[diagRight][upOne].getPiece().color))
            {
                        board[diagRight][upOne].getPiece().setBorder(diagRight, upOne, "yellow", board);
                        possible.offer(new int[]{diagRight,upOne});
            }
            if(isValid(t,upOne) && board[t][upOne].getPiece().isEmpty())
            {
                        board[t][upOne].getPiece().setBorder(t, upOne, "yellow", board);
                        possible.offer(new int[]{t,upOne});
            }
            if((z == 1 || z == 6) && isValid(t,upTwo)&& board[t][upTwo].getPiece().isEmpty())
            {
                board[t][upTwo].getPiece().setBorder(t, upTwo, "yellow", board);
  
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
            {
            Background original = board[diagLeft][upOne].getBox().getBackground();
                        board[diagLeft][upOne].getBox().setStyle(
                      "-fx-border-style: solid inside;" + 
                      "-fx-border-width: 2;" +
                      "-fx-border-color: black;"
                        );
                        board[diagLeft][upOne].getBox().setBackground(original);
                        
            }
            if(isValid(diagRight,upOne) && !board[diagRight][upOne].getPiece().isEmpty() && !board[t][z].getPiece().color.equals(board[diagRight][upOne].getPiece().color))
            {
            Background original = board[diagRight][upOne].getBox().getBackground();
                        board[diagRight][upOne].getBox().setStyle(
                      "-fx-border-style: solid inside;" + 
                      "-fx-border-width: 2;" +
                      "-fx-border-color: black;"
                        );
                        board[diagRight][upOne].getBox().setBackground(original);
                        
            }
            if(isValid(t,upOne) && board[t][upOne].getPiece().isEmpty())
            {
                 Background original = board[t][upOne].getBox().getBackground();
                        board[t][upOne].getBox().setStyle(
                      "-fx-border-style: solid inside;" + 
                      "-fx-border-width: 2;" +
                      "-fx-border-color: black;"
                        );
                        board[t][upOne].getBox().setBackground(original);
                       
            }
            if((z == 1 || z == 6) && isValid(t,upTwo)&& board[t][upTwo].getPiece().isEmpty())
            {
                Background original = board[t][upTwo].getBox().getBackground();
                        board[t][upTwo].getBox().setStyle(
                      "-fx-border-style: solid inside;" + 
                      "-fx-border-width: 2;" +
                      "-fx-border-color: black;"
                        );
                        board[t][upTwo].getBox().setBackground(original);
                        
            }
        }
    }
    
    
}
