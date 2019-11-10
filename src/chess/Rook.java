package chess;



/**
 *
 * @author coleb29
 */
public class Rook extends Piece {
Rook(String colorPiece)
    {
        super(colorPiece);
    }
    
    //0 is up, 1 is right, 2 is down, 3 is left
    //t,z is original position
    //x,y is new
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
                
                helperStackBlack(t, z, t, z+1, 0, colorBorder);
                helperStackBlack(t, z, t+1, z, 1, colorBorder);
                helperStackBlack(t, z, t, z-1, 2, colorBorder);
                helperStackBlack(t, z, t-1, z, 3, colorBorder);
            } 
            else if(color.equals("white")){
                
                helperStackWhite(t, z, t, z-1, 0, colorBorder);
                helperStackWhite(t, z, t+1, z, 1, colorBorder);
                helperStackWhite(t, z, t, z+1, 2, colorBorder);
                helperStackWhite(t, z, t-1, z, 3, colorBorder);
            }

        }
    @Override
    public void removeMove(int t, int z)
    {
        possibleMove(t,z, "black");
    }
    
    
}
