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
    protected Square[][] board;
    protected String updatePiece;
    protected Queue<int[]> possible;
    protected Boolean hasMoved;
    
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
    
    private void openFile()
    {
        File file = new File("./Images/" + this.imageName +".png");
        if(file.exists())
            image= new Image(file.toURI().toString());
        else
            System.out.println("File does not exist.");
    }
    
    public boolean move(int posX, int posY, Stage newWindow, boolean playerOneTurn, int lastX, int lastY){
    while(!possible.isEmpty())
        {
           int[] points = possible.poll();
            if(posX != points[0] || posY != points[1])
            continue;
        if(playerOneTurn && board[lastX][lastY].getPiece().color.equals("white"))
        {
            System.out.println("Player Two's Turn");
            String updated = board[lastX][lastY].getPiece().updatePiece;
            board[lastX][lastY].setPiece(" ");
            board[posX][posY].setPiece(updated);
            playerOneTurn=false;
            hasMoved=true;
        }  
        else if(!playerOneTurn && board[lastX][lastY].getPiece().color.equals("black") )
        {
            System.out.println("Player One's Turn");
            String updated = board[lastX][lastY].getPiece().updatePiece;
            board[lastX][lastY].setPiece(" ");
            board[posX][posY].setPiece(updated);
            playerOneTurn=true;
            hasMoved=true;
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
    
    public boolean isValid(int t, int z)
    {
        return t<8 && t>=0 && z<8 && z>=0;
    }
    
    public void setBoard(Square[][] board)
    {
        this.board=board;
    }
    
    public abstract void possibleMove(int t, int z, String colorBorder, Square[][] board);
    
    public abstract void removeMove(int t, int z);
 
    
    public void setBorder(int t, int z, String borderColor, Square[][] board)
    {
    Background original = board[t][z].getBox().getBackground();
                        board[t][z].getBox().setStyle(
                      "-fx-border-style: solid inside;" + 
                      "-fx-border-width: 2;" +
                      "-fx-border-color: "+ borderColor +";"
                        );
                        board[t][z].getBox().setBackground(original);
    
    }
    

    
}
