/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.stage.Stage;


/**
 *
 * @author coleb29
 */
public abstract class Piece {
    
    protected Image image;
    protected String color;
    protected String pieceName;
    protected String imageName;
    protected static Square[][] board=viewBoard.getBoard();
    protected String updatePiece;
    protected Queue<int[]> possible;
    protected Boolean hasMoved=false;
    
    Piece(String colorPiece)
    {
        possible = new LinkedList<>();
        String[] temp = colorPiece.split(" ");
        if(temp.length>=2)
        {
        this.color=temp[0];
        this.pieceName=temp[1];
        this.imageName=color+pieceName;
        this.updatePiece=color + " " + pieceName;
        }
        else
        {
            imageName="empty";
            color="empty";
        }
        
        
        openFile();
    }
    
    public Boolean getMoved()
    {
        return hasMoved;
    }
    
    
    //Retrieves the chess image based on imageName from the directory.
    private void openFile()
    {
        File file = new File("./Images/" + this.imageName +".png");
        if(file.exists())
            image= new Image(file.toURI().toString());
        else
            System.out.println("File does not exist.");
    }
    
    
    //verifies move is possible for each piece, if it is returns the new player. Otherwise returns current player
    public boolean move(int posX, int posY, Stage newWindow, boolean playerOneTurn, int lastX, int lastY){
    while(!possible.isEmpty())
        {
           int[] points = possible.poll();
            if(posX != points[0] || posY != points[1])
            continue;
        if(playerOneTurn && board[lastX][lastY].getPiece().color.equals("white"))
        {
            String updated = board[lastX][lastY].getPiece().updatePiece;
            board[lastX][lastY].setPiece(" "); // set old square to empty
            board[posX][posY].setPiece(updated); // move piece to new square
            board[posX][posY].getPiece().hasMoved=true;
            playerOneTurn=false;
        }  
        else if(!playerOneTurn && board[lastX][lastY].getPiece().color.equals("black") )
        {
            String updated = board[lastX][lastY].getPiece().updatePiece;
            board[lastX][lastY].setPiece(" ");
            board[posX][posY].setPiece(updated);
            board[posX][posY].getPiece().hasMoved=true;
            playerOneTurn=true;
        }
        }
        newWindow.close(); 
        return playerOneTurn;
    }

    Image getImage() {        
        return image;
    }
    
    public boolean isEmpty()
    {
        return imageName.equals("empty");
    }
    
//Determines if the coordinates t,z are a valid position on chess board.
    public boolean isValid(int t, int z)
    {
        return t<8 && t>=0 && z<8 && z>=0;
    }
    
    public abstract void possibleMove(int t, int z, String colorBorder);
    
    public abstract void removeMove(int t, int z);
 
   
    public void setBorder(int t, int z, String borderColor)
    {
    Background original = board[t][z].getBox().getBackground();
                        board[t][z].getBox().setStyle(
                      "-fx-border-style: solid inside;" + 
                      "-fx-border-width: 2;" +
                      "-fx-border-color: "+ borderColor +";"
                        );
                        board[t][z].getBox().setBackground(original);
    
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
    public void caseForDiagonalStack(int t, int z, int x, int y, int direction, String border, String color)
{
    int[][] possibleCoordinatesBlackDiagonal = {{x+1,y+1},{x+1,y-1},{x-1,y-1},{x-1,y+1}};
    int[][] possibleCoordinatesWhiteDiagonal = {{x+1,y-1},{x+1,y+1},{x-1,y+1},{x-1,y-1}};
        board[x][y].getPiece().setBorder(x, y, border);
            possible.offer(new int[]{x,y});
            if(!board[t][z].getPiece().color.equals(board[x][y].getPiece().color) && !board[x][y].getPiece().isEmpty()) // if the color is the same and it is not empty
                return;
            if(color.equals("black"))
                helperStackDiagonal(t, z, possibleCoordinatesBlackDiagonal[direction][0], possibleCoordinatesBlackDiagonal[direction][1], direction, border, color);

            if(color.equals("white"))
                helperStackDiagonal(t, z, possibleCoordinatesWhiteDiagonal[direction][0], possibleCoordinatesWhiteDiagonal[direction][1], direction, border, color);

}

public void caseForHorizontalStack(int t, int z, int x, int y, int direction, String border, String color)
{
    int[][] possibleCoordinatesBlackHorizontal = {{x,y+1},{x+1,y},{x,y-1},{x-1,y}};
    int[][] possibleCoordinatesWhiteHorizontal = {{x,y-1},{x+1,y},{x,y+1},{x-1,y}};
        board[x][y].getPiece().setBorder(x, y, border);
            possible.offer(new int[]{x,y});
            if(!board[t][z].getPiece().color.equals(board[x][y].getPiece().color) && !board[x][y].getPiece().isEmpty()) // if the color is the same and it is not empty
                return;
            if(color.equals("black"))
                helperStackHorizontal(t, z, possibleCoordinatesBlackHorizontal[direction][0], possibleCoordinatesBlackHorizontal[direction][1], direction, border, color);

            if(color.equals("white"))
                helperStackHorizontal(t, z, possibleCoordinatesWhiteHorizontal[direction][0], possibleCoordinatesWhiteHorizontal[direction][1], direction, border, color);
            

}

int[][] direction = {{},{},{}};
        
 public void helperStackDiagonal(int t, int z, int x, int y, int direction, String border, String color)
    {
        if(!isValid(x,y) || board[t][z].getPiece().color.equals(board[x][y].getPiece().color) )
                return;
        
            caseForDiagonalStack(t,z,x,y,direction,border, color);

    }
 
 public void helperStackHorizontal(int t, int z, int x, int y, int direction, String border, String color)
    {
        if(!isValid(x,y) || board[t][z].getPiece().color.equals(board[x][y].getPiece().color) )
                return;
            caseForHorizontalStack(t,z,x,y,direction,border, color);
   
    }
    

    
}
