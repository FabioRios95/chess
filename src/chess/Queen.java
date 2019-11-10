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
public class Queen extends Piece {
Queen(String colorPiece)
    {
        super(colorPiece);
    }
        
 public void helperStackBlackDiagonal(int t, int z, int x, int y, int direction, String border)
    {
        if(!isValid(x,y) || board[t][z].getPiece().color.equals(board[x][y].getPiece().color) )
                return;
        
    switch (direction) {
        case 0:
            board[x][y].getPiece().setBorder(x, y, border);
            possible.offer(new int[]{x,y});
            if(!board[t][z].getPiece().color.equals(board[x][y].getPiece().color) && !board[x][y].getPiece().isEmpty())
                return;
            helperStackBlackDiagonal(t, z, x+1, y+1, 0, border);
            break;
        case 1:
            board[x][y].getPiece().setBorder(x, y, border);
            possible.offer(new int[]{x,y});
            if(!board[t][z].getPiece().color.equals(board[x][y].getPiece().color) && !board[x][y].getPiece().isEmpty())
                return;
            helperStackBlackDiagonal(t, z, x+1, y-1, 1, border);
            break;
        case 2:
            board[x][y].getPiece().setBorder(x, y, border);
            possible.offer(new int[]{x,y});
            if(!board[t][z].getPiece().color.equals(board[x][y].getPiece().color) && !board[x][y].getPiece().isEmpty())
                return;
            helperStackBlackDiagonal(t, z, x-1, y-1, 2, border);
            break;
        case 3:
            board[x][y].getPiece().setBorder(x, y, border);
            possible.offer(new int[]{x,y});
            if(!board[t][z].getPiece().color.equals(board[x][y].getPiece().color) && !board[x][y].getPiece().isEmpty())
                return;
            helperStackBlackDiagonal(t, z, x-1, y+1, 3, border);
            break;
        default:
            break;
    }
    }
    
    public void helperStackWhiteDiagonal(int t, int z, int x, int y, int direction, String border)
    {   
        
        if(!isValid(x,y) || board[t][z].getPiece().color.equals(board[x][y].getPiece().color) )
                return;
        
    switch (direction) {
        case 0:
            board[x][y].getPiece().setBorder(x, y, border);
            possible.offer(new int[]{x,y});
            if(!board[t][z].getPiece().color.equals(board[x][y].getPiece().color) && !board[x][y].getPiece().isEmpty())
                return;
            helperStackWhiteDiagonal(t, z, x+1, y-1, 0, border);
            break;
        case 1:
            board[x][y].getPiece().setBorder(x, y, border);
            possible.offer(new int[]{x,y});
            if(!board[t][z].getPiece().color.equals(board[x][y].getPiece().color) && !board[x][y].getPiece().isEmpty())
                return;
            helperStackWhiteDiagonal(t, z, x+1, y+1, 1, border);
            break;
        case 2:
            board[x][y].getPiece().setBorder(x, y, border);
            possible.offer(new int[]{x,y});
            if(!board[t][z].getPiece().color.equals(board[x][y].getPiece().color) && !board[x][y].getPiece().isEmpty())
                return;
            helperStackWhiteDiagonal(t, z, x-1, y+1, 2, border);
            break;
        case 3:
            board[x][y].getPiece().setBorder(x, y, border);
            possible.offer(new int[]{x,y});
            if(!board[t][z].getPiece().color.equals(board[x][y].getPiece().color) && !board[x][y].getPiece().isEmpty())
                return;
            helperStackWhiteDiagonal(t, z, x-1, y-1, 3, border);
            break;
        default:
            break;
    }
    }
    
    public void helperStackBlack(int t, int z, int x, int y, int direction, String border)
    {
        if(!isValid(x,y) || board[t][z].getPiece().color.equals(board[x][y].getPiece().color) )
                return;
        
    switch (direction) {
        case 0:
            board[x][y].getPiece().setBorder(x, y, border);
            possible.offer(new int[]{x,y});
            if(!board[t][z].getPiece().color.equals(board[x][y].getPiece().color) && !board[x][y].getPiece().isEmpty())
                return;
            helperStackBlack(t, z, x, y+1, 0, border);
            break;
        case 1:
            board[x][y].getPiece().setBorder(x, y, border);
            possible.offer(new int[]{x,y});
            if(!board[t][z].getPiece().color.equals(board[x][y].getPiece().color) && !board[x][y].getPiece().isEmpty())
                return;
            helperStackBlack(t, z, x+1, y, 1, border);
            break;
        case 2:
            board[x][y].getPiece().setBorder(x, y, border);
            possible.offer(new int[]{x,y});
            if(!board[t][z].getPiece().color.equals(board[x][y].getPiece().color) && !board[x][y].getPiece().isEmpty())
                return;
            helperStackBlack(t, z, x, y-1, 2, border);
            break;
        case 3:
            board[x][y].getPiece().setBorder(x, y, border);
            possible.offer(new int[]{x,y});
            if(!board[t][z].getPiece().color.equals(board[x][y].getPiece().color) && !board[x][y].getPiece().isEmpty())
                return;
            helperStackBlack(t, z, x-1, y, 3, border);
            break;
        default:
            break;
    }
    }
    
    public void helperStackWhite(int t, int z, int x, int y, int direction, String border)
    {   
        
        if(!isValid(x,y) || board[t][z].getPiece().color.equals(board[x][y].getPiece().color) )
                return;
        
    switch (direction) {
        case 0:
            board[x][y].getPiece().setBorder(x, y, border);
            possible.offer(new int[]{x,y});
            if(!board[t][z].getPiece().color.equals(board[x][y].getPiece().color) && !board[x][y].getPiece().isEmpty())
                return;
            helperStackWhite(t, z, x, y-1, 0, border);
            break;
        case 1:
            board[x][y].getPiece().setBorder(x, y, border);
            possible.offer(new int[]{x,y});
            if(!board[t][z].getPiece().color.equals(board[x][y].getPiece().color) && !board[x][y].getPiece().isEmpty())
                return;
            helperStackWhite(t, z, x+1, y, 1, border);
            break;
        case 2:
            board[x][y].getPiece().setBorder(x, y, border);
            possible.offer(new int[]{x,y});
            if(!board[t][z].getPiece().color.equals(board[x][y].getPiece().color) && !board[x][y].getPiece().isEmpty())
                return;
            helperStackWhite(t, z, x, y+1, 2, border);
            break;
        case 3:
            board[x][y].getPiece().setBorder(x, y, border);
            possible.offer(new int[]{x,y});
            if(!board[t][z].getPiece().color.equals(board[x][y].getPiece().color) && !board[x][y].getPiece().isEmpty())
                return;
            helperStackWhite(t, z, x-1, y, 3, border);
            break;
        default:
            break;
    }
    }
    @Override
    public void possibleMove(int t, int z, String colorBorder){

            if(color.equals("black"))
            {
                
                               //col, row, left/right, up/down 
                helperStackBlackDiagonal(t, z, t+1, z+1, 0, colorBorder); // up,right
                helperStackBlackDiagonal(t, z, t+1, z-1, 1, colorBorder); // down, right
                helperStackBlackDiagonal(t, z, t-1, z-1, 2, colorBorder); // left, down
                helperStackBlackDiagonal(t, z, t-1, z+1, 3, colorBorder);
                
                
                helperStackBlack(t, z, t, z+1, 0, colorBorder);
                helperStackBlack(t, z, t+1, z, 1, colorBorder);
                helperStackBlack(t, z, t, z-1, 2, colorBorder);
                helperStackBlack(t, z, t-1, z, 3, colorBorder);
            } 
            else if(color.equals("white")){
                
                helperStackWhiteDiagonal(t, z, t+1, z-1, 0, colorBorder);
                helperStackWhiteDiagonal(t, z, t+1, z+1, 1, colorBorder);
                helperStackWhiteDiagonal(t, z, t-1, z+1, 2, colorBorder);
                helperStackWhiteDiagonal(t, z, t-1, z-1, 3, colorBorder);
                
                
                helperStackWhite(t, z, t, z-1, 0, colorBorder);
                helperStackWhite(t, z, t+1, z, 1, colorBorder);
                helperStackWhite(t, z, t, z+1, 2, colorBorder);
                helperStackWhite(t, z, t-1, z, 3, colorBorder);
            }

        }
    @Override
    public void removeMove(int t, int z)
    {
    if(color.equals("black"))
            {
                
                               //col, row, left/right, up/down 
                helperStackBlackDiagonal(t, z, t+1, z+1, 0, "black"); // up,right
                helperStackBlackDiagonal(t, z, t+1, z-1, 1, "black"); // down, right
                helperStackBlackDiagonal(t, z, t-1, z-1, 2, "black"); // left, down
                helperStackBlackDiagonal(t, z, t-1, z+1, 3, "black");
                
                
                helperStackBlack(t, z, t, z+1, 0, "black");
                helperStackBlack(t, z, t+1, z, 1, "black");
                helperStackBlack(t, z, t, z-1, 2, "black");
                helperStackBlack(t, z, t-1, z, 3, "black");
            } 
            else if(color.equals("white")){
                
                helperStackWhiteDiagonal(t, z, t+1, z-1, 0, "black");
                helperStackWhiteDiagonal(t, z, t+1, z+1, 1, "black");
                helperStackWhiteDiagonal(t, z, t-1, z+1, 2, "black");
                helperStackWhiteDiagonal(t, z, t-1, z-1, 3, "black");
                
                
                helperStackWhite(t, z, t, z-1, 0, "black");
                helperStackWhite(t, z, t+1, z, 1, "black");
                helperStackWhite(t, z, t, z+1, 2, "black");
                helperStackWhite(t, z, t-1, z, 3, "black");
            }
    }
    
    
}
