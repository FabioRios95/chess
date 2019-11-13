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
    
     public void checkForCheck(char flag)
    {
           blackCheck=checkEverything(chess.Game.getBlackKing(), 'n', "black");
           whiteCheck=checkEverything(chess.Game.getWhiteKing(), 'n', "white");
    }
    
    public boolean checkEverything(int[] dest, char flag, String colored){
        boolean canBeReached=false;
        for(int i=0;i<8; i++)
        {
            for(int j=0; j<8;j++)
            {
                if(!(board[j][i].getPiece().color.equals(colored) || board[j][i].getPiece().color.equals("empty") || board[j][i].getPiece().pieceName.equals("King")))
                {
                    board[j][i].getPiece().possibleMove(j,i,"black");
                    canBeReached=checkPossible(board[j][i].getPiece(), new int[]{j,i}, dest, canBeReached, flag);
                }
            }
        }
        return canBeReached;
    }
/**
 checkPossible
 * prevPiece: is the potential attacking piece
 * prevPiecePoint: is the coordinates of the attacking piece
 * destPoints are the coordinates for the position being attacked
 * canBeReached returns true if the prevPiece can reach the destPoints
 * flag 
 *  n : normal operation
 *  q : queue the coordinates of attacking piece
 *  i : ignore king as potential piece
 *  o : override used to temporarily test if king can move in checked scenario
 */
    public boolean checkPossible(Piece prevPiece, int[] prevPiecePoint, int[] destPoints, boolean canBeReached, char flag)
    {
        while(!prevPiece.possible.isEmpty())
        {

            int[] points = prevPiece.possible.poll();
            
            if(destPoints[0] != points[0] || destPoints[1] != points[1])
                continue;
            if(!prevPiece.color.equals(board[destPoints[0]][destPoints[1]].getPiece().color))
            {
                canBeReached=true;
            }
            
        }
        return canBeReached;   
    }
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
            answer = checkEverything(new int[]{5,7}, 'n', color);
            if(answer == true)
                return true;
            answer = checkEverything(new int[]{6,7}, 'n', color);
        }
        else{
            answer = checkEverything(new int[]{5,0}, 'n', color);
            if(answer == true)
                return true;
            answer = checkEverything(new int[]{6,0}, 'n', color);
        }    
        return answer;
    }
    
    //Squres king passes over not under attack for castling Queen Side
    public boolean spaceUnderAttackQS(String color){

        Boolean answer=false;
        
        if("white".equals(color))
        {
            answer = checkEverything(new int[]{1,7}, 'n', color);
            if(answer == true)
                return true;
            answer = checkEverything(new int[]{2,7}, 'n', color);
            if(answer == true)
                return true;
            answer = checkEverything(new int[]{3,7}, 'n', color);
        }
        else{
            answer = checkEverything(new int[]{1,0}, 'n', color);
            if(answer == true)
                return true;
            answer = checkEverything(new int[]{2,0}, 'n', color);
            if(answer == true)
                return true;
            answer = checkEverything(new int[]{3,0}, 'n', color);
        }   
        return answer;
    }
    
    //Determine if king is in check
    public boolean isKingInCheck(int lastX, int lastY, String color){
        Boolean answer;

        checkForCheck('q');

        if(color.equals("white"))
            answer=whiteCheck;
        else
            answer=blackCheck;

        return answer;
    }
     //verifies move is possible for each piece, if it is returns the new player. Otherwise returns current player, overrides piece
    // because it needs to handle castling. 
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
            board[lastX][lastY].setPiece(" "); // set old square to empty
            board[posX][posY].setPiece(updated); // move piece to new square
            board[posX][posY].getPiece().hasMoved=true;
            playerOneTurn=false;
        }  
        else if(!playerOneTurn && board[lastX][lastY].getPiece().color.equals("black") )
        {
            String updated = board[lastX][lastY].getPiece().updatePiece;
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
          /*  
            //Can castle or not
            System.out.println(" t " + t + " z " + z);
            System.out.println("spacesBetween " + allSpaceBetweenKingAndRookEmptyKS(color));
            boolean test = spaceUnderAttackKS(color);
            System.out.println("kingInCheck " + test);
            //+ " spaceUnderAttack " + spaceUnderAttackQS(color)
            //+ " isKingInCheck " + isKingInCheck(t, z, color));
                if(allSpaceBetweenKingAndRookEmptyQS(color) && spaceUnderAttackQS(color) && isKingInCheck(t, z, color))
                {
                    if(color.equals("black"))
                            possible.offer(new int[]{1,0});
                    else
                            possible.offer(new int[]{1,7});
                }
                if(allSpaceBetweenKingAndRookEmptyKS(color)&&spaceUnderAttackKS(color)&&isKingInCheck(t, z, color))
                {    
                    if(color.equals("black"))
                            possible.offer(new int[]{6,0});
                    else
                            possible.offer(new int[]{6,7});
                }
        */
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
                        board[t][upOne].getPiece().setBorder(t, downOne, colorBorder);
                        possible.offer(new int[]{t,downOne});
            }
            if(isValid(leftOne,z) && !board[t][z].getPiece().color.equals(board[leftOne][z].getPiece().color))
            {
                        board[leftOne][upOne].getPiece().setBorder(leftOne, z, colorBorder);
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
                        possible.offer(new int[]{leftOne,upOne});
            }
            if(isValid(rightOne,upOne) && !board[t][z].getPiece().color.equals(board[rightOne][upOne].getPiece().color))
            {
                        board[rightOne][upOne].getPiece().setBorder(rightOne, upOne, "black");
                        possible.offer(new int[]{rightOne,upOne});
            }
            if(isValid(leftOne,downOne) && !board[t][z].getPiece().color.equals(board[leftOne][downOne].getPiece().color))
            {
                        board[leftOne][downOne].getPiece().setBorder(leftOne, downOne, "black");
                        possible.offer(new int[]{leftOne,downOne});
            }
            if(isValid(rightOne,downOne) && !board[t][z].getPiece().color.equals(board[rightOne][downOne].getPiece().color))
            {
                        board[rightOne][downOne].getPiece().setBorder(rightOne, downOne, "black");
                        possible.offer(new int[]{rightOne,downOne});
            }
            if(isValid(t,upOne) && !board[t][z].getPiece().color.equals(board[t][upOne].getPiece().color))
            {
                        board[t][upOne].getPiece().setBorder(t, upOne, "black");
                        possible.offer(new int[]{t,upOne});
            }
            if(isValid(t,downOne) && !board[t][z].getPiece().color.equals(board[t][downOne].getPiece().color))
            {
                        board[t][upOne].getPiece().setBorder(t, downOne, "black");
                        possible.offer(new int[]{t,downOne});
            }
            if(isValid(leftOne,z) && !board[t][z].getPiece().color.equals(board[leftOne][z].getPiece().color))
            {
                        board[leftOne][upOne].getPiece().setBorder(leftOne, z, "black");
                        possible.offer(new int[]{leftOne,z});
            }
            if(isValid(rightOne,z) && !board[t][z].getPiece().color.equals(board[rightOne][z].getPiece().color))
            {
                        board[rightOne][z].getPiece().setBorder(rightOne, z, "black");
                        possible.offer(new int[]{rightOne,z});
            }
}
    
    
}
