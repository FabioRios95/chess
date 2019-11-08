/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;


import java.util.LinkedList;
import java.util.Queue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author coleb29
 */
public class Game {
    private Square[][] board;
    private Scene scene;
    private boolean isSelected= false;
    private int lastX, lastY;
    private Piece previousPiece;
    private Stage stage;
    private boolean playerOneTurn=true;
    private int[] blackKing= new int[]{4,0};
    private int[] whiteKing= new int[]{4,7};
    private boolean blackCheck=false;
    private boolean whiteCheck=false;
    private Queue<int[]> checkQueue = new LinkedList<>();
   
    
 
    public void setBoard(Square[][] board){
        this.board=board;
    }
    
    public void setScene(Scene scene)
    {
        this.scene=scene;
    }
    
    public void handleClicks(int increment)
    {
        scene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

              int posX = (int)mouseEvent.getX()/(increment+2);
              int posY = (int)mouseEvent.getY()/(increment+2);
              
                if (posX >= 0 && posX <= 7 && posY >=0 && posY <=7 && board[posX][posY].getBox().contains(posX, posY))
               {
                        //Toggle green/black border for selected square
                        selectedSquare(posX, posY); 
               }
               else{
                   System.out.println("That is not a valid click.");
               }
            }
            });
    }
    
    public void selectedSquare(int posX, int posY)
    {
        //need to make it only run once
        board[posX][posY].getPiece().setBoard(board);
        if(!isSelected){
                        Background original = board[posX][posY].getBox().getBackground();
                        board[posX][posY].getBox().setStyle(
                      "-fx-border-style: solid inside;" + 
                      "-fx-border-width: 2;" +
                      "-fx-border-color: green;"
                        );
                        board[posX][posY].getBox().setBackground(original);
                        isSelected=true;
                        lastX=posX;
                        lastY=posY;
                        board[posX][posY].getPiece().possibleMove(posX, posY, "yellow", board);
                        previousPiece=board[posX][posY].getPiece();
                        }
                        else{
                        createPop(posX, posY);
                        previousPiece.removeMove(lastX, lastY);
                        resetBackground(lastX, lastY);
                        isSelected=false;
                        }
    }
    public void resetBackground(int lastX, int lastY)
    {
                        Background original = board[lastX][lastY].getBox().getBackground();
                        board[lastX][lastY].getBox().setStyle(
                      "-fx-border-style: solid inside;" + 
                      "-fx-border-width: 2;" +
                      "-fx-border-color: black;"
                        );
                        board[lastX][lastY].getBox().setBackground(original);
    }
    public void createPop(int posX, int posY)
    {
            Label secondLabel = new Label("You have chosen to move the "+ board[lastX][lastY].getPiece().updatePiece  + " at " + board[lastX][lastY].getLocation(board[lastX][lastY].getYPosition(),board[lastX][lastY].getXPosition()) + " to "+ board[posX][posY].getLocation(board[posX][posY].getYPosition(),board[posX][posY].getXPosition()) );
            Button confirmButton = new Button();
            confirmButton.setText("Confirm");
            Button cancelButton = new Button();
            cancelButton.setText("Cancel");
            
            VBox vbox = new VBox();
            
            HBox row1 = new HBox();
            row1.setAlignment(Pos.CENTER);

            HBox row2 = new HBox();
            row2.setAlignment(Pos.CENTER);
                
                row1.getChildren().add(secondLabel);
                row2.getChildren().add(confirmButton);
                row2.getChildren().add(cancelButton);
                vbox.getChildren().add(row1);
                vbox.getChildren().add(row2);
                vbox.setAlignment(Pos.BASELINE_CENTER);
 
                

                
                
                Scene secondScene = new Scene(vbox, 400, 50);
 
                // New window (Stage)
                Stage newWindow = new Stage();
                newWindow.setTitle("Confirm Move");
                newWindow.setScene(secondScene);
 
                // Set position of second window, related to primary window.
                newWindow.setX(stage.getX() + 420);
                newWindow.setY(stage.getY() );
                
                            EventHandler<ActionEvent> confirmed =  
             new EventHandler<ActionEvent>() { 
   
            public void handle(ActionEvent e) 
            {
                
                Move(posX,posY, newWindow);
            } 
        }; 
             EventHandler<ActionEvent> close =  
             new EventHandler<ActionEvent>() { 
   
            public void handle(ActionEvent e) 
            { 
                //Need to loop again basically or do nothing.
                newWindow.close();
            } 
        }; 

            confirmButton.setOnAction(confirmed);
            cancelButton.setOnAction(close);
                
 
                newWindow.show();
    }
    
    public void setStage(Stage stage)
    {
        this.stage=stage;
    }
    
    public boolean kingMoveFromCheck(String checkedKing){

        boolean canMove=false;
        int[] position;
        if(checkedKing.equals("white"))
            position= new int[]{whiteKing[0],whiteKing[1]};
        else
            position= new int[]{blackKing[0],blackKing[1]};
            board[position[0]][position[1]].getPiece().possibleMove(position[0], position[1], "black", board);
            if(board[position[0]][position[1]].getPiece().possible.isEmpty())
                return canMove;
            
            while(!board[position[0]][position[1]].getPiece().possible.isEmpty())
            {
               canMove=checkEverything(board[position[0]][position[1]].getPiece().possible.poll(), 'n');
            }
            return canMove; 
    }
    
    public boolean piecesCausingCheckKilled(String checkedKing)
    {
        System.out.println("Queue size: " + checkQueue.size());
        if(checkQueue.size() == 0)
            return false;
        if(checkQueue.size()>= 2)
        {
            //pieceInPathOfCheck(checkedKing);
            return false;
        }
        return false;
    }
    
    public boolean pieceInPathOfCheck(String checkedKing)
    {
        return false;
    }
    
    public void checkForCheck()
    {
        if(whiteCheck)
        {
            if(!kingMoveFromCheck("white") && !piecesCausingCheckKilled("white") && !pieceInPathOfCheck("white"))
            {
                System.out.println("Black Wins!\nCheckmate!");
                return;
            }
            else
                System.out.println("White Check!");
        }
        if(blackCheck)
        {
            if(!kingMoveFromCheck("black") && !piecesCausingCheckKilled("black") && !pieceInPathOfCheck("black"))
            {
                System.out.println("White Wins!\nCheckmate!");
                return;
            }
            else
                System.out.println("Black Check!");
        }
    }
    
    public void Move(int posX, int posY, Stage newWindow){
       boolean turn=playerOneTurn;

        playerOneTurn = previousPiece.move(posX, posY, newWindow, playerOneTurn, lastX, lastY);
      
      if(playerOneTurn != turn)
       {
           //Update King position when moved
           if(previousPiece.imageName.equals("blackKing"))
               blackKing= new int[]{posX,posY};
           else if(previousPiece.imageName.equals("whiteKing"))    
               whiteKing= new int[]{posX,posY};

           //Looks for check could be if statement
           blackCheck=checkEverything(blackKing, 'n');
           whiteCheck=checkEverything(whiteKing, 'n');
           //System.out.println("blackKing " + blackKing[0] + " " + blackKing[1] + " whiteKing " + whiteKing[0] +" " + whiteKing[1]);
           //System.out.println("blackCheck " + blackCheck + " " + "whiteCheck " + whiteCheck);
           checkForCheck();
       }
    }
    
    public boolean checkEverything(int[] dest, char flag){
        boolean canBeReached=false;
        for(int i=0;i<8; i++)
        {
            for(int j=0; j<8;j++)
            {
                //has to be another color piece 
                if(board[j][i].getPiece().color.equals(board[dest[0]][dest[1]].getPiece().color) || board[j][i].getPiece().color.equals("empty"))
                   continue;
                else {
                    board[j][i].getPiece().possibleMove(j,i,"black", board);
                    canBeReached=checkPossible(board[j][i].getPiece(), flag, new int[]{j,i}, dest, canBeReached);
                }
            }
        }
        return canBeReached;
    }

    public boolean checkPossible(Piece prevPiece, char flag, int[] prevPiecePoint, int[] destPoints, boolean canBeReached)
    {
        while(!prevPiece.possible.isEmpty())
        {
            int[] points = prevPiece.possible.poll();
            //System.out.println("points " + points[0] + " " + points[1] + " destPoints " + destPoints[0] +" " + destPoints[1] + prevPiece.color);
            if(destPoints[0] != points[0] || destPoints[1] != points[1])
                continue;
            canBeReached=true;
        }
        return canBeReached;   
    }
}
