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
        super(colorPiece);
    }
    
    @Override
    public Image getImage(){
        return image;
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
            helperStackBlack(t, z, x+1, y+1, 0, border);
            break;
        case 1:
            board[x][y].getPiece().setBorder(x, y, border);
            possible.offer(new int[]{x,y});
            if(!board[t][z].getPiece().color.equals(board[x][y].getPiece().color) && !board[x][y].getPiece().isEmpty())
                return;
            helperStackBlack(t, z, x+1, y-1, 1, border);
            break;
        case 2:
            board[x][y].getPiece().setBorder(x, y, border);
            possible.offer(new int[]{x,y});
            if(!board[t][z].getPiece().color.equals(board[x][y].getPiece().color) && !board[x][y].getPiece().isEmpty())
                return;
            helperStackBlack(t, z, x-1, y-1, 2, border);
            break;
        case 3:
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
            helperStackWhite(t, z, x+1, y-1, 0, border);
            break;
        case 1:
            board[x][y].getPiece().setBorder(x, y, border);
            possible.offer(new int[]{x,y});
            if(!board[t][z].getPiece().color.equals(board[x][y].getPiece().color) && !board[x][y].getPiece().isEmpty())
                return;
            helperStackWhite(t, z, x+1, y+1, 1, border);
            break;
        case 2:
            board[x][y].getPiece().setBorder(x, y, border);
            possible.offer(new int[]{x,y});
            if(!board[t][z].getPiece().color.equals(board[x][y].getPiece().color) && !board[x][y].getPiece().isEmpty())
                return;
            helperStackWhite(t, z, x-1, y+1, 2, border);
            break;
        case 3:
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
                
                               //col, row, left/right, up/down 
                helperStackBlack(t, z, t+1, z+1, 0, colorBorder); // up,right
                helperStackBlack(t, z, t+1, z-1, 1, colorBorder); // down, right
                helperStackBlack(t, z, t-1, z-1, 2, colorBorder); // left, down
                helperStackBlack(t, z, t-1, z+1, 3, colorBorder);
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
      if(color.equals("black"))
            {
                
                               //col, row, left/right, up/down 
                helperStackBlack(t, z, t+1, z+1, 0, "black"); // up,right
                helperStackBlack(t, z, t+1, z-1, 1, "black"); // down, right
                helperStackBlack(t, z, t-1, z-1, 2, "black"); // left, down
                helperStackBlack(t, z, t-1, z+1, 3, "black");
            } 
            else if(color.equals("white")){
                
                helperStackWhite(t, z, t+1, z-1, 0, "black");
                helperStackWhite(t, z, t+1, z+1, 1, "black");
                helperStackWhite(t, z, t-1, z+1, 2, "black");
                helperStackWhite(t, z, t-1, z-1, 3, "black");
            }

    }
}
