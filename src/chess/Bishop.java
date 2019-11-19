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
public class Bishop extends Piece {
Bishop(String colorPiece)
    {
        super(colorPiece); //use piece constructor
    }

    @Override
    
    // Bishop moves diagonally.
    // t = left and right
    // z = up and down
    public void possibleMove(int t, int z, String colorBorder){

            if(color.equals("black"))
            {
                helperStackDiagonal(t, z, t+1, z+1, 0, colorBorder, "black"); // up,right
                helperStackDiagonal(t, z, t+1, z-1, 1, colorBorder, "black"); // down, right
                helperStackDiagonal(t, z, t-1, z-1, 2, colorBorder, "black"); // left, down
                helperStackDiagonal(t, z, t-1, z+1, 3, colorBorder, "black"); //left, up
            } 
            else if(color.equals("white")){
                helperStackDiagonal(t, z, t+1, z-1, 0, colorBorder , "white"); // down, right
                helperStackDiagonal(t, z, t+1, z+1, 1, colorBorder, "white");  // up,right
                helperStackDiagonal(t, z, t-1, z+1, 2, colorBorder, "white");  //left, up
                helperStackDiagonal(t, z, t-1, z-1, 3, colorBorder, "white");  // left, down
            }

        }
    @Override
    public void removeMove(int t, int z)
    {
       possibleMove(t,z , "black");
    }
}
