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
public class Queen extends Piece {
Queen(String colorPiece)
    {
        super(colorPiece);
    }
    
    @Override
    public Image getImage(){
        return image;
    }
    
 public void helperStackBlackDiagonal(int t, int z, int x, int y, int direction, Square[][] board, String border)
    {
        if(!isValid(x,y) || board[t][z].getPiece().color.equals(board[x][y].getPiece().color) )
                return;
        
    switch (direction) {
        case 0:
            board[x][y].getPiece().setBorder(x, y, border, board);
            possible.offer(new int[]{x,y});
            if(!board[t][z].getPiece().color.equals(board[x][y].getPiece().color) && !board[x][y].getPiece().isEmpty())
                return;
            helperStackBlackDiagonal(t, z, x+1, y+1, 0, board, border);
            break;
        case 1:
            board[x][y].getPiece().setBorder(x, y, border, board);
            possible.offer(new int[]{x,y});
            if(!board[t][z].getPiece().color.equals(board[x][y].getPiece().color) && !board[x][y].getPiece().isEmpty())
                return;
            helperStackBlackDiagonal(t, z, x+1, y-1, 1, board, border);
            break;
        case 2:
            board[x][y].getPiece().setBorder(x, y, border, board);
            possible.offer(new int[]{x,y});
            if(!board[t][z].getPiece().color.equals(board[x][y].getPiece().color) && !board[x][y].getPiece().isEmpty())
                return;
            helperStackBlackDiagonal(t, z, x-1, y-1, 2, board, border);
            break;
        case 3:
            board[x][y].getPiece().setBorder(x, y, border, board);
            possible.offer(new int[]{x,y});
            if(!board[t][z].getPiece().color.equals(board[x][y].getPiece().color) && !board[x][y].getPiece().isEmpty())
                return;
            helperStackBlackDiagonal(t, z, x-1, y+1, 3, board, border);
            break;
        default:
            break;
    }
    }
    
    public void helperStackWhiteDiagonal(int t, int z, int x, int y, int direction, Square[][] board, String border)
    {   
        
        if(!isValid(x,y) || board[t][z].getPiece().color.equals(board[x][y].getPiece().color) )
                return;
        
    switch (direction) {
        case 0:
            board[x][y].getPiece().setBorder(x, y, border, board);
            possible.offer(new int[]{x,y});
            if(!board[t][z].getPiece().color.equals(board[x][y].getPiece().color) && !board[x][y].getPiece().isEmpty())
                return;
            helperStackWhiteDiagonal(t, z, x+1, y-1, 0, board, border);
            break;
        case 1:
            board[x][y].getPiece().setBorder(x, y, border, board);
            possible.offer(new int[]{x,y});
            if(!board[t][z].getPiece().color.equals(board[x][y].getPiece().color) && !board[x][y].getPiece().isEmpty())
                return;
            helperStackWhiteDiagonal(t, z, x+1, y+1, 1, board, border);
            break;
        case 2:
            board[x][y].getPiece().setBorder(x, y, border, board);
            possible.offer(new int[]{x,y});
            if(!board[t][z].getPiece().color.equals(board[x][y].getPiece().color) && !board[x][y].getPiece().isEmpty())
                return;
            helperStackWhiteDiagonal(t, z, x-1, y+1, 2, board, border);
            break;
        case 3:
            board[x][y].getPiece().setBorder(x, y, border, board);
            possible.offer(new int[]{x,y});
            if(!board[t][z].getPiece().color.equals(board[x][y].getPiece().color) && !board[x][y].getPiece().isEmpty())
                return;
            helperStackWhiteDiagonal(t, z, x-1, y-1, 3, board, border);
            break;
        default:
            break;
    }
    }
    
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
    @Override
    public void possibleMove(int t, int z, String colorBorder, Square[][] board){

            if(color.equals("black"))
            {
                
                               //col, row, left/right, up/down 
                helperStackBlackDiagonal(t, z, t+1, z+1, 0, board, colorBorder); // up,right
                helperStackBlackDiagonal(t, z, t+1, z-1, 1, board, colorBorder); // down, right
                helperStackBlackDiagonal(t, z, t-1, z-1, 2, board, colorBorder); // left, down
                helperStackBlackDiagonal(t, z, t-1, z+1, 3, board, colorBorder);
                
                
                helperStackBlack(t, z, t, z+1, 0, board, colorBorder);
                helperStackBlack(t, z, t+1, z, 1, board, colorBorder);
                helperStackBlack(t, z, t, z-1, 2, board, colorBorder);
                helperStackBlack(t, z, t-1, z, 3, board, colorBorder);
            } 
            else if(color.equals("white")){
                
                helperStackWhiteDiagonal(t, z, t+1, z-1, 0, board, colorBorder);
                helperStackWhiteDiagonal(t, z, t+1, z+1, 1, board, colorBorder);
                helperStackWhiteDiagonal(t, z, t-1, z+1, 2, board, colorBorder);
                helperStackWhiteDiagonal(t, z, t-1, z-1, 3, board, colorBorder);
                
                
                helperStackWhite(t, z, t, z-1, 0, board, colorBorder);
                helperStackWhite(t, z, t+1, z, 1, board, colorBorder);
                helperStackWhite(t, z, t, z+1, 2, board, colorBorder);
                helperStackWhite(t, z, t-1, z, 3, board, colorBorder);
            }

        }
    @Override
    public void removeMove(int t, int z)
    {
    if(color.equals("black"))
            {
                
                               //col, row, left/right, up/down 
                helperStackBlackDiagonal(t, z, t+1, z+1, 0, board, "black"); // up,right
                helperStackBlackDiagonal(t, z, t+1, z-1, 1, board, "black"); // down, right
                helperStackBlackDiagonal(t, z, t-1, z-1, 2, board, "black"); // left, down
                helperStackBlackDiagonal(t, z, t-1, z+1, 3, board, "black");
                
                
                helperStackBlack(t, z, t, z+1, 0, board, "black");
                helperStackBlack(t, z, t+1, z, 1, board, "black");
                helperStackBlack(t, z, t, z-1, 2, board, "black");
                helperStackBlack(t, z, t-1, z, 3, board, "black");
            } 
            else if(color.equals("white")){
                
                helperStackWhiteDiagonal(t, z, t+1, z-1, 0, board, "black");
                helperStackWhiteDiagonal(t, z, t+1, z+1, 1, board, "black");
                helperStackWhiteDiagonal(t, z, t-1, z+1, 2, board, "black");
                helperStackWhiteDiagonal(t, z, t-1, z-1, 3, board, "black");
                
                
                helperStackWhite(t, z, t, z-1, 0, board, "black");
                helperStackWhite(t, z, t+1, z, 1, board, "black");
                helperStackWhite(t, z, t, z+1, 2, board, "black");
                helperStackWhite(t, z, t-1, z, 3, board, "black");
            }
    }
    
    
}
