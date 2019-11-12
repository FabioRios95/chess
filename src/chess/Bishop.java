/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;


import javafx.scene.image.Image;


/**
 *
 * @author coleb29
 */
public class Bishop extends Piece {
Bishop(String colorPiece)
    {
        super(colorPiece); //use piece constructor
    }
    
    /** 
     helperStackBlack handles possible moves for black bishop
     * Arguments : 
     * t : the actual initial column position of the piece 
     * z : the actual position row position of the piece
     * x : the next possible column position to be tested
     * y : the next possible row position to be tested
     * direction : The bishop can only move in four possible directions. This specifies which one of those it is. 
     * border : border color for the square/VBox
     */
        public void helperStackBlack(int t, int z, int x, int y, int direction, String border)
    {
        //Checks coordinates to see if they are inside chess board or if the piece is the same color (cannot move past its own piece)
        if(!isValid(x,y) || board[t][z].getPiece().color.equals(board[x][y].getPiece().color) )
                return;
        
    switch (direction) {
        case 0: // handles  up, right position
            board[x][y].getPiece().setBorder(x, y, border);
            possible.offer(new int[]{x,y});
            if(!board[t][z].getPiece().color.equals(board[x][y].getPiece().color) && !board[x][y].getPiece().isEmpty())
                return;
            helperStackBlack(t, z, x+1, y+1, 0, border);
            break;
        case 1: //handles down, right position
            board[x][y].getPiece().setBorder(x, y, border);
            possible.offer(new int[]{x,y});
            if(!board[t][z].getPiece().color.equals(board[x][y].getPiece().color) && !board[x][y].getPiece().isEmpty())
                return;
            helperStackBlack(t, z, x+1, y-1, 1, border);
            break;
        case 2: // handles left, down position
            board[x][y].getPiece().setBorder(x, y, border);
            possible.offer(new int[]{x,y});
            if(!board[t][z].getPiece().color.equals(board[x][y].getPiece().color) && !board[x][y].getPiece().isEmpty())
                return;
            helperStackBlack(t, z, x-1, y-1, 2, border);
            break;
        case 3:  // handles up, left position
            board[x][y].getPiece().setBorder(x, y, border);
            possible.offer(new int[]{x,y});
            if(!board[t][z].getPiece().color.equals(board[x][y].getPiece().color) && !board[x][y].getPiece().isEmpty())
                return;
            helperStackBlack(t, z, x-1, y+1, 3, border);
            break;
        default:
            break;
    }
    }
    /** 
     helperStackWhite handles possible moves for white bishop
     * Arguments : 
     * t : the actual initial column position of the piece 
     * z : the actual position row position of the piece
     * x : the next possible column position to be tested
     * y : the next possible row position to be tested
     * direction : The bishop can only move in four possible directions. This specifies which one of those it is. 
     * border : border color for the square/VBox
     */
    public void helperStackWhite(int t, int z, int x, int y, int direction, String border)
    {   
        
        if(!isValid(x,y) || board[t][z].getPiece().color.equals(board[x][y].getPiece().color) )
                return;
        
    switch (direction) {
        case 0: // handles  up, right position
            board[x][y].getPiece().setBorder(x, y, border);
            possible.offer(new int[]{x,y});
            if(!board[t][z].getPiece().color.equals(board[x][y].getPiece().color) && !board[x][y].getPiece().isEmpty())
                return;
            helperStackWhite(t, z, x+1, y-1, 0, border);
            break;
        case 1: //handles down, right position
            board[x][y].getPiece().setBorder(x, y, border);
            possible.offer(new int[]{x,y});
            if(!board[t][z].getPiece().color.equals(board[x][y].getPiece().color) && !board[x][y].getPiece().isEmpty())
                return;
            helperStackWhite(t, z, x+1, y+1, 1, border);
            break;
        case 2: // handles left, down position
            board[x][y].getPiece().setBorder(x, y, border);
            possible.offer(new int[]{x,y});
            if(!board[t][z].getPiece().color.equals(board[x][y].getPiece().color) && !board[x][y].getPiece().isEmpty())
                return;
            helperStackWhite(t, z, x-1, y+1, 2, border);
            break;
        case 3: // handles up, left position
            board[x][y].getPiece().setBorder(x, y, border);
            possible.offer(new int[]{x,y});
            if(!board[t][z].getPiece().color.equals(board[x][y].getPiece().color) && !board[x][y].getPiece().isEmpty())
                return;
            helperStackWhite(t, z, x-1, y-1, 3, border);
            break;
        default:
            break;
    }
    }
    @Override
    public void possibleMove(int t, int z, String colorBorder){

            if(color.equals("black"))
            {
                helperStackBlack(t, z, t+1, z+1, 0, colorBorder); // up,right
                helperStackBlack(t, z, t+1, z-1, 1, colorBorder); // down, right
                helperStackBlack(t, z, t-1, z-1, 2, colorBorder); // left, down
                helperStackBlack(t, z, t-1, z+1, 3, colorBorder); // up , left
            } 
            else if(color.equals("white")){
                
                helperStackWhite(t, z, t+1, z-1, 0, colorBorder);
                helperStackWhite(t, z, t+1, z+1, 1, colorBorder);
                helperStackWhite(t, z, t-1, z+1, 2, colorBorder);
                helperStackWhite(t, z, t-1, z-1, 3, colorBorder);
            }

        }
    @Override
    public void removeMove(int t, int z)
    {
       possibleMove(t,z , "black");
    }
}
