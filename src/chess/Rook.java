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
public class Rook extends Piece {
Rook(String colorPiece)
    {
        super(colorPiece);
    }
    
    @Override
    public Image getImage(){
        return image;
    }
    
    //0 is up, 1 is right, 2 is down, 3 is left
    //t,z is original position
    //x,y is new
      public void helperStackBlack(int t, int z, int x, int y, int direction, Square[][] board, String border)
    {
        if(!isValid(x,y) || board[t][z].getPiece().color.equals(board[x][y].getPiece().color) )
                return;
        
    switch (direction) {
        case 0:
            board[x][y].getPiece().setBorder(x, y, border, board);
            possible.offer(new int[]{x,y});
            if(!board[t][z].getPiece().color.equals(board[x][y].getPiece().color) && !board[x][y].getPiece().isEmpty())
                return;
            helperStackBlack(t, z, x, y+1, 0, board, border);
            break;
        case 1:
            board[x][y].getPiece().setBorder(x, y, border, board);
            possible.offer(new int[]{x,y});
            if(!board[t][z].getPiece().color.equals(board[x][y].getPiece().color) && !board[x][y].getPiece().isEmpty())
                return;
            helperStackBlack(t, z, x+1, y, 1, board, border);
            break;
        case 2:
            board[x][y].getPiece().setBorder(x, y, border, board);
            possible.offer(new int[]{x,y});
            if(!board[t][z].getPiece().color.equals(board[x][y].getPiece().color) && !board[x][y].getPiece().isEmpty())
                return;
            helperStackBlack(t, z, x, y-1, 2, board, border);
            break;
        case 3:
            board[x][y].getPiece().setBorder(x, y, border, board);
            possible.offer(new int[]{x,y});
            if(!board[t][z].getPiece().color.equals(board[x][y].getPiece().color) && !board[x][y].getPiece().isEmpty())
                return;
            helperStackBlack(t, z, x-1, y, 3, board, border);
            break;
        default:
            break;
    }
    }
    
    public void helperStackWhite(int t, int z, int x, int y, int direction, Square[][] board, String border)
    {   
        
        if(!isValid(x,y) || board[t][z].getPiece().color.equals(board[x][y].getPiece().color) )
                return;
        
    switch (direction) {
        case 0:
            board[x][y].getPiece().setBorder(x, y, border, board);
            possible.offer(new int[]{x,y});
            if(!board[t][z].getPiece().color.equals(board[x][y].getPiece().color) && !board[x][y].getPiece().isEmpty())
                return;
            helperStackWhite(t, z, x, y-1, 0, board, border);
            break;
        case 1:
            board[x][y].getPiece().setBorder(x, y, border, board);
            possible.offer(new int[]{x,y});
            if(!board[t][z].getPiece().color.equals(board[x][y].getPiece().color) && !board[x][y].getPiece().isEmpty())
                return;
            helperStackWhite(t, z, x+1, y, 1, board, border);
            break;
        case 2:
            board[x][y].getPiece().setBorder(x, y, border, board);
            possible.offer(new int[]{x,y});
            if(!board[t][z].getPiece().color.equals(board[x][y].getPiece().color) && !board[x][y].getPiece().isEmpty())
                return;
            helperStackWhite(t, z, x, y+1, 2, board, border);
            break;
        case 3:
            board[x][y].getPiece().setBorder(x, y, border, board);
            possible.offer(new int[]{x,y});
            if(!board[t][z].getPiece().color.equals(board[x][y].getPiece().color) && !board[x][y].getPiece().isEmpty())
                return;
            helperStackWhite(t, z, x-1, y, 3, board, border);
            break;
        default:
            break;
    }
    }
    
    public void possibleMove(int t, int z){

            if(color.equals("black"))
            {
                
                helperStackBlack(t, z, t, z+1, 0, board, "yellow");
                helperStackBlack(t, z, t+1, z, 1, board, "yellow");
                helperStackBlack(t, z, t, z-1, 2, board, "yellow");
                helperStackBlack(t, z, t-1, z, 3, board, "yellow");
            } 
            else if(color.equals("white")){
                
                helperStackWhite(t, z, t, z-1, 0, board, "yellow");
                helperStackWhite(t, z, t+1, z, 1, board, "yellow");
                helperStackWhite(t, z, t, z+1, 2, board, "yellow");
                helperStackWhite(t, z, t-1, z, 3, board, "yellow");
            }

        }
    public void removeMove(int t, int z)
    {
    if(color.equals("black"))
            {
                helperStackBlack(t, z, t, z+1, 0, board, "black");
                helperStackBlack(t, z, t+1, z, 1, board, "black");
                helperStackBlack(t, z, t, z-1, 2, board, "black");
                helperStackBlack(t, z, t-1, z, 3, board, "black");
            } 
            else if(color.equals("white")){
                helperStackWhite(t, z, t, z-1, 0, board, "black");
                helperStackWhite(t, z, t+1, z, 1, board, "black");
                helperStackWhite(t, z, t, z+1, 2, board, "black");
                helperStackWhite(t, z, t-1, z, 3, board, "black");
            }
    }
    
    
}
