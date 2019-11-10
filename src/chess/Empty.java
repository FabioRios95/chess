package chess;


/**
 *
 * @author coleb29
 */
public class Empty extends Piece {
    
        Empty(String colorPiece)
    {
        super(colorPiece);
    }
    
    @Override
    public void possibleMove(int t, int z, String colorBorder){}
    @Override
    public void removeMove(int t, int z){}
    
}
