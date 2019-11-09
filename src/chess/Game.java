/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
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
    private int lastX, lastY, posX, posY;
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

              posX = (int)mouseEvent.getX()/(increment+2);
              posY = (int)mouseEvent.getY()/(increment+2);
              
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
    
    public int[] determineKing(String checkedKing)
    {
        int[] position;
        if(checkedKing.equals("white"))
            position= new int[]{whiteKing[0],whiteKing[1]};
        else
            position= new int[]{blackKing[0],blackKing[1]};
        return position;
    }
    
    public boolean moveIntoCheck(int posX, int posY)
    {
        if((lastX==whiteKing[0] && lastY==whiteKing[1]) || (lastX == blackKing[0] && lastY==blackKing[1]))
            {
                return checkEverything(new int[]{posX,posY}, 'o', board[lastX][lastY].getPiece().color);
            }
        return false;

    }
    
    public boolean moveCausesCheck(String color)
    {
        boolean causesCheck=false;
        String pieceOrig= board[lastX][lastY].getPiece().updatePiece;
        String pieceActual=board[posX][posY].getPiece().updatePiece;
        if(pieceActual == null)
            pieceActual="z";
        if(pieceOrig == null)
            pieceActual="z";
        board[posX][posY].setPiece(pieceOrig);
        board[lastX][lastY].setPiece("z");
        checkForCheck('q');
        System.out.println(board[lastX][lastY].getPiece().color);
        if(blackCheck == false && whiteCheck == false)
            causesCheck=false;
        else if(blackCheck == true && color.equals("white"))
            causesCheck=false;
        else if(whiteCheck == true && color.equals("black"))
            causesCheck=false;
        else
            causesCheck=true;
        
        board[posX][posY].setPiece(pieceActual);
        board[lastX][lastY].setPiece(pieceOrig);
  
        return causesCheck;
    }
    
    public boolean kingMoveFromCheck(String checkedKing){

        boolean canMove=false;
        int[] position=determineKing(checkedKing);

            board[position[0]][position[1]].getPiece().possibleMove(position[0], position[1], "black", board);
            if(board[position[0]][position[1]].getPiece().possible.isEmpty())
                return false;
            Queue<int[]> storage= new LinkedList<>(board[position[0]][position[1]].getPiece().possible);
            //System.out.println("possible length : " + board[position[0]][position[1]].getPiece().possible.size());
            
            boolean canBeTouched;
            while(!storage.isEmpty())
            {
               int[] possibles = storage.poll();
               canBeTouched=checkEverything(possibles, 'n', board[position[0]][position[1]].getPiece().color);
               //System.out.println("canBeTouched: " + canBeTouched + " position " + possibles[0] + " " + possibles[1]);
               if(!canBeTouched && moveIntoCheck(possibles[0],possibles[1])) //&& moveIntoCheck(possibles[0],possibles[1])
                   canMove=true;
            }
            System.out.println("canMove : " + canMove);
            return canMove; 
    }
    
    public boolean piecesCausingCheckKilled(String checkedKing)
    {
        boolean canKill=false;
        int[] position=determineKing(checkedKing);
        canKill=checkEverything(position, 'q', board[position[0]][position[1]].getPiece().color);
        
        if(checkQueue.isEmpty())
            return true;
        if(checkQueue.size()>= 2)
            return false;
        
        while(!checkQueue.isEmpty())
        {
            position=checkQueue.poll();
            canKill=checkEverything(position, 'n', board[position[0]][position[1]].getPiece().color) && moveIntoCheck(position[0],position[1]); //&& moveIntoCheck(position[0],position[1])
        }
         System.out.println("canKill : " + canKill);
        return canKill;
    }
    
    public double distance(int x2, int x1, int y2, int y1)
    {
        return Math.sqrt(Math.pow(x2-x1,2)+Math.pow(y2-y1,2));
    }
    
    public boolean pieceInPathOfCheck(String checkedKing)
    {
        boolean pathCheck=false;
        boolean canMoveInto=false;
        int[] king=determineKing(checkedKing);
        int [] position;
        double actualDistance;
        double blockDistance;
        checkEverything(king, 'q', board[king[0]][king[1]].getPiece().color);
        
        while(!checkQueue.isEmpty())
        {
                //Piece that is checking
                position=checkQueue.poll();
                //Get piece at position then check its possible position to see if king is in check.
                board[position[0]][position[1]].getPiece().possibleMove(position[0], position[1], "black", board);
                pathCheck=false;
                actualDistance=distance(king[0], position[0], king[1], position[1]);

                while(!board[position[0]][position[1]].getPiece().possible.isEmpty())
                {
                    int[] test = board[position[0]][position[1]].getPiece().possible.poll();
                    
                    //System.out.println(board[position[0]][position[1]].getPiece().updatePiece);
                    blockDistance=distance(king[0], test[0], king[1], test[1]);
                    canMoveInto = checkEverything(test ,'i', board[position[0]][position[1]].getPiece().color);
                    if(canMoveInto && blockDistance < actualDistance)
                        pathCheck=true;
                }
                System.out.println("pathCheck : " + pathCheck);
                if(pathCheck == false)
                    return false;
        }
        
        return pathCheck;
    }
    
    public void checkForCheck(char flag)
    {
           blackCheck=checkEverything(blackKing, 'n', "black");
           whiteCheck=checkEverything(whiteKing, 'n', "white");
           //System.out.println("blackKing " + blackKing[0] + " " + blackKing[1] + " whiteKing " + whiteKing[0] +" " + whiteKing[1]);
           //System.out.println("blackCheck " + blackCheck + " " + "whiteCheck " + whiteCheck);
        if(flag=='n'){
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
            }
            else
                System.out.println("Black Check!");
        }
        }
    }
    
    public void Move(int posX, int posY, Stage newWindow){
       boolean turn=playerOneTurn;
       //check to see if this will cause check

       if(moveIntoCheck(posX, posY) || moveCausesCheck(board[lastX][lastY].getPiece().color))
       {
           System.out.println("You cannot move here due to it causing check.");
           return;
       }

        playerOneTurn = previousPiece.move(posX, posY, newWindow, playerOneTurn, lastX, lastY);
      
      if(playerOneTurn != turn)
       {
           //Update King position when moved
           if(previousPiece.imageName.equals("blackKing"))
               blackKing= new int[]{posX,posY};
           else if(previousPiece.imageName.equals("whiteKing"))    
               whiteKing= new int[]{posX,posY};

           //Looks for check could be if statement
           checkForCheck('n');
       }
    }
    
    public boolean checkEverything(int[] dest, char flag, String colored){
        boolean canBeReached=false;
        for(int i=0;i<8; i++)
        {
            for(int j=0; j<8;j++)
            {
                //has to be another color piece
                if(!(board[j][i].getPiece().color.equals(colored) || board[j][i].getPiece().color.equals("empty") ))
                {
                    String pieceOrig= board[dest[0]][dest[1]].getPiece().updatePiece;
                    String pieceActual=board[j][i].getPiece().updatePiece;
                    if(flag == 'o')
                    {
                      board[dest[0]][dest[1]].setPiece(colored+ " King");
                      board[j][i].setPiece("z");
                        if(pieceActual == null)
                             pieceActual="z";
                        if(pieceOrig == null)
                            pieceActual="z";
                    }
                    if(flag == 'i')
                    {
                        if(blackKing[0] == j || blackKing[1] == i)
                            continue;
                        if(whiteKing[0] == j || whiteKing[1] == i)
                            continue;
                    }
                    board[j][i].getPiece().possibleMove(j,i,"black", board);
                    canBeReached=checkPossible(board[j][i].getPiece(), new int[]{j,i}, dest, canBeReached, flag);
                    if(flag=='o')
                    {
                       board[dest[0]][dest[1]].setPiece(pieceOrig); 
                       board[j][i].setPiece(pieceActual);
                        
                    }
                }
            }
        }
        return canBeReached;
    }

    public boolean checkPossible(Piece prevPiece, int[] prevPiecePoint, int[] destPoints, boolean canBeReached, char flag)
    {
        while(!prevPiece.possible.isEmpty())
        {

            int[] points = prevPiece.possible.poll();
            
            if(destPoints[0] != points[0] || destPoints[1] != points[1])
                continue;
            if(!prevPiece.color.equals(board[destPoints[0]][destPoints[1]].getPiece().color))
            {
                //System.out.println(prevPiece.color + board[destPoints[0]][destPoints[1]].getPiece().color);
                //System.out.println("points " + points[0] + " " + points[1] + " destPoints " + destPoints[0] +" " + destPoints[1] + prevPiece.imageName);
                canBeReached=true;
            if(flag == 'q')
            {
                checkQueue.add(prevPiecePoint);
            }
            }
            
        }
        return canBeReached;   
    }
}
