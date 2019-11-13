/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;


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
