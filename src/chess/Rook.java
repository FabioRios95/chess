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
    
    @Override
    
    // Rook possible moves horizontally.
    public void possibleMove(int t, int z, String colorBorder){

            if(color.equals("black")) // black piece
            {
                helperStackHorizontal(t, z, t, z+1, 0, colorBorder, "black"); //up
                helperStackHorizontal(t, z, t+1, z, 1, colorBorder, "black"); //right
                helperStackHorizontal(t, z, t, z-1, 2, colorBorder, "black"); //down
                helperStackHorizontal(t, z, t-1, z, 3, colorBorder, "black"); //left
            } 
        
        //white piece
            else if(color.equals("white")){
                
                helperStackHorizontal(t, z, t, z-1, 0, colorBorder, "white");  //down
                helperStackHorizontal(t, z, t+1, z, 1, colorBorder, "white");  //right
                helperStackHorizontal(t, z, t, z+1, 2, colorBorder, "white");  //up
                helperStackHorizontal(t, z, t-1, z, 3, colorBorder, "white");  //left
            }

        }
    @Override
    public void removeMove(int t, int z)
    {
        possibleMove(t,z, "black");
    }
    
    
}
