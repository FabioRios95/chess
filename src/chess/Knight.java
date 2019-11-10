package chess;


/**
 *
 * @author coleb29
 */
public class Knight extends Piece {
Knight(String colorPiece)
    {
        super(colorPiece);
    }
    
    
    public void canMove(int x, int y, int t, int z, String color)
    {
     if(isValid(x,y) && !board[t][z].getPiece().color.equals(board[x][y].getPiece().color))
            {
                        board[x][y].getPiece().setBorder(x, y, color);
                            possible.offer(new int[]{x,y});
            }
    }
  
    public void removeMove(int t, int z)
    {
        possibleMove(t,z, "black");
    }
    
    @Override
    public void possibleMove(int t, int z, String colorBorder){

    int downTwo,upTwo, diagLeft, diagRight;
    int downOne, upOne, leftTwo, rightTwo;
            if(color.equals("black"))
            {
                downTwo=z-2;
                downOne=z-1;
                upTwo=z+2;
                upOne=z+1;
                diagLeft=t-1;
                leftTwo=t-2;
                diagRight=t+1;
                rightTwo=t+2;
            } 
            else if(color.equals("white")){
                downTwo=z+2;
                downOne=z+1;
                upTwo=z-2;
                upOne=z-1;
                diagLeft=t-1;
                leftTwo=t-2;
                rightTwo=t+2;
                diagRight=t+1;
            }
            else
            {
                downTwo=8;
                upTwo=8;
                diagLeft=8;
                diagRight=8;
                downOne=8;
                upOne=8;
                leftTwo=8;
                rightTwo=8;
            }
            canMove(leftTwo, upOne, t, z, colorBorder);
            canMove(leftTwo, downOne, t, z,colorBorder);
            canMove(rightTwo, upOne, t, z,colorBorder);
            canMove(rightTwo, downOne, t, z,colorBorder);

            canMove(diagLeft, upTwo, t, z,colorBorder);
            canMove(diagRight, upTwo, t, z,colorBorder);
            canMove(diagLeft, downTwo, t, z,colorBorder);
            canMove(diagRight, downTwo, t, z,colorBorder);
        
        }
}

