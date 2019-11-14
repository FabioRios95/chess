/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import static chess.Piece.board;
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
    private boolean blackCheck=false;
    private boolean whiteCheck=false;
    
 
    /**
     All space between king and rook empty on King Side, for castling
     */
    public boolean allSpaceBetweenKingAndRookEmptyKS(String color)
    {
        Boolean answer=false;
        if("white".equals(color))
        {
            //System.out.println(board[5][7].getPiece().isEmpty());
            //System.out.println(board[6][7].getPiece().isEmpty());
            if(board[5][7].getPiece().isEmpty() && board[6][7].getPiece().isEmpty())
                return true;
        }
        else{
            if(board[5][0].getPiece().isEmpty() && board[6][0].getPiece().isEmpty())
                return true;
        }
        
        return answer;
    }
    
     /**
     All space between king and rook empty on Queen Side, for castling
     */
    public boolean allSpaceBetweenKingAndRookEmptyQS(String color)
    {
        Boolean answer=false;
        if("white".equals(color))
        {
            if(board[1][7].getPiece().isEmpty() && board[2][7].getPiece().isEmpty() && board[3][7].getPiece().isEmpty())
                answer=true;
        }
        else{
            if(board[1][0].getPiece().isEmpty() && board[2][0].getPiece().isEmpty() && board[3][0].getPiece().isEmpty())
                answer=true;
        }
        
        return answer;
    }
    //Squres king passes over not under attack for castling King Side
    public boolean spaceUnderAttackKS(String color){

        Boolean answer=false;
        if("white".equals(color))
        {
            answer = chess.Game.checkEverything(new int[]{5,7}, 'i', color);
            if(answer == true)
                return true;
            answer = chess.Game.checkEverything(new int[]{6,7}, 'i', color);
        }
        else{
            answer = chess.Game.checkEverything(new int[]{5,0}, 'i', color);
            if(answer == true)
                return true;
            answer = chess.Game.checkEverything(new int[]{6,0}, 'i', color);
        }    
        return answer;
    }
    
    //Squres king passes over not under attack for castling Queen Side
    public boolean spaceUnderAttackQS(String color){

        Boolean answer=false;
        
        if("white".equals(color))
        {
            answer = chess.Game.checkEverything(new int[]{1,7}, 'i', color);
            if(answer == true)
                return true;
            answer = chess.Game.checkEverything(new int[]{2,7}, 'i', color);
            if(answer == true)
                return true;
            answer = chess.Game.checkEverything(new int[]{3,7}, 'i', color);
        }
        else{
            answer = chess.Game.checkEverything(new int[]{1,0}, 'i', color);
            if(answer == true)
                return true;
            answer = chess.Game.checkEverything(new int[]{2,0}, 'i', color);
            if(answer == true)
                return true;
            answer = chess.Game.checkEverything(new int[]{3,0}, 'i', color);
        }   
        return answer;
    }
    
    //Determine if king is in check
    public boolean isKingInCheck(int lastX, int lastY, String color){
        Boolean answer;

        if(color.equals("white"))
            answer=chess.Game.getWhiteCheck();
        else
            answer=chess.Game.getBlackCheck();

        return answer;
    }
     //verifies move is possible for each piece, if it is returns the new player. Otherwise returns current player, overrides piece
    // because it needs to handle castling. 
    public void castling(int posX, int posY){
        if(posX == 6 && posY == 7) // King Side White
        {
            board[5][7].setPiece("white Rook");
            board[7][7].setPiece(" ");
        }
        if(posX == 2 && posY == 7) // Queen Side White
        {
            board[3][7].setPiece("white Rook");
            board[0][7].setPiece(" ");
        }  
        if(posX == 6 && posY == 0) // King Side Black
        {
            board[5][0].setPiece("black Rook");
            board[7][0].setPiece(" ");
        }  
        if(posX == 2 && posY == 0) // Queen Side Black
        {
            board[3][0].setPiece("black Rook");
            board[0][0].setPiece(" ");
        }
    }
    @Override
    public boolean move(int posX, int posY, Stage newWindow, boolean playerOneTurn, int lastX, int lastY){
    while(!possible.isEmpty())
        {
           int[] points = possible.poll();
            if(posX != points[0] || posY != points[1])
            continue;
        if(playerOneTurn && board[lastX][lastY].getPiece().color.equals("white"))
        {
            String updated = board[lastX][lastY].getPiece().updatePiece;
            if( ((posX == 6 && posY == 7) && ( !board[7][7].getPiece().hasMoved)) || ((posX == 2 && posY == 7) && ( !board[0][7].getPiece().hasMoved)))
                castling(posX,posY);
            board[lastX][lastY].setPiece(" "); // set old square to empty
            board[posX][posY].setPiece(updated); // move piece to new square
            board[posX][posY].getPiece().hasMoved=true;
            playerOneTurn=false;
        }  
        else if(!playerOneTurn && board[lastX][lastY].getPiece().color.equals("black") )
        {
            String updated = board[lastX][lastY].getPiece().updatePiece;
           // System.out.println(posX + " " + posY);
            if( ((posX == 6 && posY == 0) && ( !board[7][0].getPiece().hasMoved))|| ((posX == 2 && posY == 0) && ( !board[0][0].getPiece().hasMoved)))
                castling(posX,posY);
            board[lastX][lastY].setPiece(" ");
            board[posX][posY].setPiece(updated);
            board[posX][posY].getPiece().hasMoved=true;
            playerOneTurn=true;
        }
        }
        newWindow.close(); 
        return playerOneTurn;
    }
    
@Override
public void possibleMove(int t, int z, String colorBorder){
    int upOne,downOne, leftOne, rightOne;

            if(color.equals("black"))
            {
                upOne=z+1;
                downOne=z-1; 
                leftOne=t-1;
                rightOne=t+1;

            } 
            else if(color.equals("white")){
                downOne=z+1;
                upOne=z-1;
                leftOne=t-1;
                rightOne=t+1;
            }
            else
            {
                upOne=8;
                downOne=8; 
                leftOne=8;
                rightOne=8;
            }
         //Castling
         
                if(isValid(t,z) && !board[t][z].getPiece().hasMoved &&allSpaceBetweenKingAndRookEmptyQS(color) && !spaceUnderAttackQS(color) && !isKingInCheck(t, z, color))
                {
                    while(!possible.isEmpty())
                        possible.poll();
                    if(color.equals("black") && !board[0][0].getPiece().hasMoved)
                            possible.offer(new int[]{2,0});
                    else if(color.equals("white") && !board[0][7].getPiece().hasMoved)
                            possible.offer(new int[]{2,7});
                }
                if(isValid(t,z)&& !board[t][z].getPiece().hasMoved &&allSpaceBetweenKingAndRookEmptyKS(color)&&!spaceUnderAttackKS(color)&&!isKingInCheck(t, z, color))
                {    
                    while(!possible.isEmpty())
                        possible.poll();
                    if(color.equals("black") && !board[7][0].getPiece().hasMoved)
                            possible.offer(new int[]{6,0});
                    else if(color.equals("white") && !board[7][7].getPiece().hasMoved)
                            possible.offer(new int[]{6,7});
                }
        
       
            //End of castle, regular movement begins
            if(isValid(leftOne,upOne) && !board[t][z].getPiece().color.equals(board[leftOne][upOne].getPiece().color))
            {
                        board[leftOne][upOne].getPiece().setBorder(leftOne, upOne, colorBorder);
                        possible.offer(new int[]{leftOne,upOne});
            }
            if(isValid(rightOne,upOne) && !board[t][z].getPiece().color.equals(board[rightOne][upOne].getPiece().color))
            {
                        board[rightOne][upOne].getPiece().setBorder(rightOne, upOne, colorBorder);
                        possible.offer(new int[]{rightOne,upOne});
            }
            if(isValid(leftOne,downOne) && !board[t][z].getPiece().color.equals(board[leftOne][downOne].getPiece().color))
            {
                        board[leftOne][downOne].getPiece().setBorder(leftOne, downOne, colorBorder);
                        possible.offer(new int[]{leftOne,downOne});
            }
            if(isValid(rightOne,downOne) && !board[t][z].getPiece().color.equals(board[rightOne][downOne].getPiece().color))
            {
                        board[rightOne][downOne].getPiece().setBorder(rightOne, downOne, colorBorder);
                        possible.offer(new int[]{rightOne,downOne});
            }
            if(isValid(t,upOne) && !board[t][z].getPiece().color.equals(board[t][upOne].getPiece().color))
            {
                        board[t][upOne].getPiece().setBorder(t, upOne, colorBorder);
                        possible.offer(new int[]{t,upOne});
            }
            if(isValid(t,downOne) && !board[t][z].getPiece().color.equals(board[t][downOne].getPiece().color))
            {
                        board[t][downOne].getPiece().setBorder(t, downOne, colorBorder);
                        possible.offer(new int[]{t,downOne});
            }
            if(isValid(leftOne,z) && !board[t][z].getPiece().color.equals(board[leftOne][z].getPiece().color))
            {
                        board[leftOne][z].getPiece().setBorder(leftOne, z, colorBorder);
                        possible.offer(new int[]{leftOne,z});
            }
            if(isValid(rightOne,z) && !board[t][z].getPiece().color.equals(board[rightOne][z].getPiece().color))
            {
                        board[rightOne][z].getPiece().setBorder(rightOne, z, colorBorder);
                        possible.offer(new int[]{rightOne,z});
            }
}

@Override
public void removeMove(int t, int z){
int upOne,downOne, leftOne, rightOne;

            if(color.equals("black"))
            {
                upOne=z+1;
                downOne=z-1; 
                leftOne=t-1;
                rightOne=t+1;

            } 
            else if(color.equals("white")){
                downOne=z+1;
                upOne=z-1;
                leftOne=t-1;
                rightOne=t+1;
            }
            else
            {
                upOne=8;
                downOne=8; 
                leftOne=8;
                rightOne=8;
            }
            
            if(isValid(leftOne,upOne) && !board[t][z].getPiece().color.equals(board[leftOne][upOne].getPiece().color))
            {
                        board[leftOne][upOne].getPiece().setBorder(leftOne, upOne, "black");
            }
            if(isValid(rightOne,upOne) && !board[t][z].getPiece().color.equals(board[rightOne][upOne].getPiece().color))
            {
                        board[rightOne][upOne].getPiece().setBorder(rightOne, upOne, "black");
            }
            if(isValid(leftOne,downOne) && !board[t][z].getPiece().color.equals(board[leftOne][downOne].getPiece().color))
            {
                        board[leftOne][downOne].getPiece().setBorder(leftOne, downOne, "black");

            }
            if(isValid(rightOne,downOne) && !board[t][z].getPiece().color.equals(board[rightOne][downOne].getPiece().color))
            {
                        board[rightOne][downOne].getPiece().setBorder(rightOne, downOne, "black");

            }
            if(isValid(t,upOne) && !board[t][z].getPiece().color.equals(board[t][upOne].getPiece().color))
            {
                        board[t][upOne].getPiece().setBorder(t, upOne, "black");

            }
            if(isValid(t,downOne) && !board[t][z].getPiece().color.equals(board[t][downOne].getPiece().color))
            {
                        board[t][upOne].getPiece().setBorder(t, downOne, "black");

            }
            if(isValid(leftOne,z) && !board[t][z].getPiece().color.equals(board[leftOne][z].getPiece().color))
            {
                        board[leftOne][upOne].getPiece().setBorder(leftOne, z, "black");

            }
            if(isValid(rightOne,z) && !board[t][z].getPiece().color.equals(board[rightOne][z].getPiece().color))
            {
                        board[rightOne][z].getPiece().setBorder(rightOne, z, "black");

            }
}
    
    
}
