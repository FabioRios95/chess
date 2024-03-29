package chess;

/**
 *
 * @author coleb29
 */

import java.util.LinkedList;
import java.util.Queue;
import javafx.animation.PauseTransition;
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
import javafx.util.Duration;

/**
 *
 * @author coleb29
 */

/**
 * Class game handles user input via mouse and win conditions for Chess
*/
public class Game {
    private static Square[][] board=viewBoard.getBoard();
    private Scene scene;
    private boolean isSelected= false;
    private int lastX, lastY, posX, posY;
    private Piece previousPiece;
    private Stage stage;
    private boolean playerOneTurn=true;
    private static int[] blackKing= new int[]{4,0};
    private static int[] whiteKing= new int[]{4,7};
    private static boolean blackCheck=false;
    private static boolean whiteCheck=false;
    private static Queue<int[]> checkQueue = new LinkedList<>();
  
    public static boolean getBlackCheck(){
        return blackCheck;
    }
    
    public static boolean getWhiteCheck(){
        return whiteCheck;
    }
    
    public void setScene(Scene scene)
    {
        this.scene=scene;
    }
    /**
     handleClicks is passed the increment [ window size ] and computes if the click corresponds to a square on the board
     */
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
                   generalPopUp("That is not a valid click.");
               }
            }
            });
    }
    
    
    /**
     selectedSquare is passed valid coordinates from handleClicks. If the square has a piece on it then it highlights the possible moves for it
     * if it receives a second click, will attempt to move there.
     */
    public void selectedSquare(int posX, int posY)
    {
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
                        board[posX][posY].getPiece().possibleMove(posX, posY, "yellow");
                        previousPiece=board[posX][posY].getPiece();
                        }
                        else{
                        movePopUp(posX, posY);
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
    
    
    /** Creates a pop up window displaying the argument message that is passed to it. The window lasts one second before closing.*/
    public void generalPopUp(String message)
    {
            Label secondLabel = new Label(message);

                Stage newWindow = new Stage();
                newWindow.setTitle("Chess");
                Scene secondScene = new Scene(secondLabel, 400, 50);
                newWindow.setScene(secondScene);
                newWindow.setX(stage.getX());
                newWindow.setY(stage.getY()-75);
                newWindow.show();
                PauseTransition delay = new PauseTransition(Duration.seconds(2));
                delay.setOnFinished( event -> newWindow.close() );
                delay.play();
    }
    
    /**
     movePopUp handles the actual move conditions, the user must click the confirm button for a move to be made.
     */
    public void movePopUp(int posX, int posY)
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
                newWindow.close();
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
    
    
    //Passes stage from javafx to game class
    public void setStage(Stage stage)
    {
        this.stage=stage;
    }
    /**
     * determineKing
     * 
     * @param checkedKing : color of King
     * @return the coordinates of the king
     */
    public int[] determineKing(String checkedKing)
    {
        int[] position;
        if(checkedKing.equals("white"))
            position= new int[]{whiteKing[0],whiteKing[1]};
        else
            position= new int[]{blackKing[0],blackKing[1]};
        return position;
    }
    /**moveIntoCheck is used in the special case where the king can kill the piece causing check. This function assumes that move an determines if the king is still in check after killing that piece.
     If he is then he is unable to move there. It is a necessary function in determining checkmate. The arguments posX and posY are the potential coordinates of the king on the board. The final argument is
     * the color of the king.
     
     PreCondition: King Piece is in a position to kill the piece causing check.
     * 
     PostCondition: Returns true if king would be in check at the new position.
     
     */
    public boolean moveIntoCheck(int posX, int posY, String color)
    {
            if(color.equals("black"))
            {
                board[blackKing[0]][blackKing[1]].getPiece().possibleMove(blackKing[0], blackKing[1], "black");
                while(!board[blackKing[0]][blackKing[1]].getPiece().possible.isEmpty())
                {
                    int test[] = board[blackKing[0]][blackKing[1]].getPiece().possible.poll();
                    if(test[0] == posX && test[1] == posY)
                        return checkEverything(new int[]{posX,posY}, 'o', color);
                }
            }
            if(color.equals("white"))
            {
                board[whiteKing[0]][whiteKing[1]].getPiece().possibleMove(whiteKing[0], whiteKing[1], "black");
                while(!board[whiteKing[0]][whiteKing[1]].getPiece().possible.isEmpty())
                {
                    int test[] = board[whiteKing[0]][whiteKing[1]].getPiece().possible.poll();
                    if(test[0] == posX && test[1] == posY)
                        return checkEverything(new int[]{posX,posY}, 'o', color);
                }
            }
            return false;
    }
    
    /**
     moveCausesCheck determines if moving a piece will put the king in check, if it does it will not allow them to move.
     */
    public boolean moveCausesCheck(String color)
    {
        boolean causesCheck=false;
        String pieceTrue= board[lastX][lastY].getPiece().updatePiece;
        String pieceFalse=board[posX][posY].getPiece().updatePiece;
        String pieceName = board[lastX][lastY].getPiece().pieceName;
        if(pieceTrue == null)
            pieceTrue="empty";
        if(pieceFalse == null)
            pieceFalse="empty";
        board[posX][posY].setPiece(pieceTrue);
        board[lastX][lastY].setPiece("empty");
        if(pieceName != null && pieceName.equals("King"))
        {
           //System.out.println("King.");
           blackCheck=checkEverything(new int[]{posX,posY}, 'n', "black");
           whiteCheck=checkEverything(new int[]{posX,posY}, 'n', "white");
        }
        else {
        checkForCheck('q');
       }
        
        //System.out.println("color " + color);
        if(color.equals("black"))
        {
            if(blackCheck == true)
                causesCheck=true;
            else
                causesCheck=false;
        }
        if(color.equals("white"))
        {
            if(whiteCheck == true)
                causesCheck=true;
            else
                causesCheck=false;
        }
 
        board[posX][posY].setPiece(pieceFalse);
        //System.out.println("whiteCheck " + whiteCheck);
        board[lastX][lastY].setPiece(pieceTrue);
        //System.out.println("blackCheck" + blackCheck);
  
        
        checkForCheck('q'); // reset real values for check
       // System.out.println("causesCheck " + causesCheck);
        return causesCheck;
    }
    
    public boolean kingMoveFromCheck(String checkedKing){

        boolean canMove=false;
        int[] position=determineKing(checkedKing);

            board[position[0]][position[1]].getPiece().possibleMove(position[0], position[1], "black");
            if(board[position[0]][position[1]].getPiece().possible.isEmpty())
                return false;
            Queue<int[]> storage= new LinkedList<>(board[position[0]][position[1]].getPiece().possible);
            
            
            boolean canBeTouched;
            while(!storage.isEmpty())
            {
               int[] possibles = storage.poll();
               canBeTouched=checkEverything(possibles, 'n', board[position[0]][position[1]].getPiece().color);
              
               if(!canBeTouched && !moveIntoCheck(possibles[0],possibles[1], "black")) 
                   canMove=true;
            }
            //System.out.println("canMove : " + canMove);
            return canMove; 
    }
    /**piecesCausingCheckKilled determines if you can kill the piece that caused check, if there are more than two pieces causing check
     it returns false, as you cannot move to both locations. */
    public boolean piecesCausingCheckKilled(String checkedKing)
    {
        boolean canKill=false;
        int[] position=determineKing(checkedKing);
        checkEverything(position, 'q', board[position[0]][position[1]].getPiece().color);
        
        if(checkQueue.isEmpty())
            return true;
        if(checkQueue.size()>= 2)
            return false;
        
        while(!checkQueue.isEmpty())
        {
            position=checkQueue.poll();
            canKill=checkEverything(position, 'n', board[position[0]][position[1]].getPiece().color)  && !moveIntoCheck(position[0],position[1], "black");
        }
        return canKill;
    }
    
    //Calculates distance between squares, used for pieceInPathOfCheck. Effectively gets rid of potential moves in the opposite direction of the king.
    public double distance(int x2, int x1, int y2, int y1)
    {
        return Math.sqrt(Math.pow(x2-x1,2)+Math.pow(y2-y1,2));
    }
    //Determines if you can block the piece causing check with another
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
                board[position[0]][position[1]].getPiece().possibleMove(position[0], position[1], "black");
                pathCheck=false;
                actualDistance=distance(king[0], position[0], king[1], position[1]);

                while(!board[position[0]][position[1]].getPiece().possible.isEmpty())
                {
                    int[] test = board[position[0]][position[1]].getPiece().possible.poll();
                    

                    blockDistance=distance(king[0], test[0], king[1], test[1]);
                    canMoveInto = checkEverything(test ,'i', board[position[0]][position[1]].getPiece().color);
                    if(canMoveInto && blockDistance < actualDistance)
                        pathCheck=true;
                }

                if(pathCheck == false)
                    return false;
        }
        
        return pathCheck;
    }
    /** checkForCheck will check to see if either King is in a check position, outputting nothing, check, or checkmate.*/
    public void checkForCheck(char flag)
    {
           blackCheck=checkEverything(blackKing, 'n', "black");
           whiteCheck=checkEverything(whiteKing, 'n', "white");
          
        if(flag=='n')
        {
            if(whiteCheck)
            {
                if(!kingMoveFromCheck("white") && !piecesCausingCheckKilled("white") && !pieceInPathOfCheck("white"))
                {
                    generalPopUp("Black Wins!\nCheckmate!");
                    stage.close();
                    return;
                }
                else
                    generalPopUp("White Check!");
            }
            if(blackCheck)
            {
                if(!kingMoveFromCheck("black") && !piecesCausingCheckKilled("black") && !pieceInPathOfCheck("black"))
                {
                    generalPopUp("White Wins!\nCheckmate!");
                    stage.close();
                }
                else
                    generalPopUp("Black Check!");
            }
        }
    }
    /*Handles player moves, switch player turn*/
    public void Move(int posX, int posY, Stage newWindow){
       boolean turn=playerOneTurn;
       
       if(moveCausesCheck(board[lastX][lastY].getPiece().color)) // moveIntoCheck(posX, posY) || 
       {
           generalPopUp("You cannot move here due to it causing check.");
           return;
       }
        previousPiece.possibleMove(lastX, lastY, "black");
        playerOneTurn = previousPiece.move(posX, posY, newWindow, playerOneTurn, lastX, lastY);
        //Was the move successful. 
      if(playerOneTurn != turn)
       {
           
           //System.out.println(board[posX][posY].getPiece().hasMoved + " AfterMove");
           if(playerOneTurn)
               generalPopUp("Player One's Turn");
           else
               generalPopUp("Player Two's Turn");
           //Update King position when moved
           if(previousPiece.imageName.equals("blackKing"))
               blackKing= new int[]{posX,posY};
           else if(previousPiece.imageName.equals("whiteKing"))    
               whiteKing= new int[]{posX,posY};

           //Looks for check could be if statement
           checkForCheck('n');
       }

    }
   /**
 checkEverything

* This will pass every piece that is not the same color to checkPossible
* 
 * destPoints are the coordinates for the position being attacked
 * 
 * flag 
 *  n : normal operation
 *  q : queue the coordinates of attacking piece
 *  i : ignore king as potential piece
 *  o : override used to temporarily test if king can move in checked scenario
 */ 
    public static boolean checkEverything(int[] dest, char flag, String colored){
        boolean canBeReached=false;
        for(int i=0;i<8; i++)
        {
            for(int j=0; j<8;j++)
            {
                //has to be another color piece
                if(!(board[j][i].getPiece().color.equals(colored) || board[j][i].getPiece().color.equals("empty") ))
                {
                    String kingFalsePosition=null, kingTruePosition=null;
                    if(flag == 'o')
                    {
                        if(colored.equals("black")){
                            kingFalsePosition=board[dest[0]][dest[1]].getPiece().updatePiece;
                            kingTruePosition= board[blackKing[0]][blackKing[1]].getPiece().updatePiece;
                            board[dest[0]][dest[1]].setPiece(colored+ " King");
                            board[blackKing[0]][blackKing[1]].setPiece("z");
                            if(kingFalsePosition == null)
                                kingFalsePosition="empty";
                            if(kingTruePosition == null)
                                kingTruePosition="black King";
                        }
                        if(colored.equals("white"))
                        {
                            kingFalsePosition=board[dest[0]][dest[1]].getPiece().updatePiece;
                            kingTruePosition= board[whiteKing[0]][whiteKing[1]].getPiece().updatePiece;
                            board[dest[0]][dest[1]].setPiece(colored+ " King");
                            board[whiteKing[0]][whiteKing[1]].setPiece("z");
                            if(kingFalsePosition == null)
                                kingFalsePosition="empty";
                            if(kingTruePosition == null)
                               kingTruePosition="white King";
                        }
                    }
                    if(flag == 'i')
                    {
                        if(blackKing[0] == j || blackKing[1] == i)
                            continue;
                        if(whiteKing[0] == j || whiteKing[1] == i)
                            continue;
                    }
                    board[j][i].getPiece().possibleMove(j,i,"black");
                    canBeReached=checkPossible(board[j][i].getPiece(), new int[]{j,i}, dest, canBeReached, flag);
                    if(flag=='o')
                    {
                        if(colored.equals("black")){
                            board[dest[0]][dest[1]].setPiece(kingFalsePosition); 
                            board[blackKing[0]][blackKing[1]].setPiece(kingTruePosition);
                        }
                        if(colored.equals("white")){
                            board[dest[0]][dest[1]].setPiece(kingFalsePosition); 
                            board[whiteKing[0]][whiteKing[1]].setPiece(kingTruePosition);
                        }
                    }
                }
            }
        }
        return canBeReached;
    }
/**
 chcheckPossible
 * prevPiece: is the potential attacking piece
 * prevPiecePoint: is the coordinates of the attacking piece
 * destPoints are the coordinates for the position being attacked
 * canBeReached returns true if the prevPiece can reach the destPoints
 * flag 
 *  n : normal operation
 *  q : queue the coordinates of attacking piece
 *  i : ignore king as potential piece
 *  o : override used to temporarily test if king can move in checked scenario
 */
    public static boolean checkPossible(Piece prevPiece, int[] prevPiecePoint, int[] destPoints, boolean canBeReached, char flag)
    {
        while(!prevPiece.possible.isEmpty())
        {

            int[] points = prevPiece.possible.poll();
            
            if(destPoints[0] != points[0] || destPoints[1] != points[1])
                continue;
            if(!prevPiece.color.equals(board[destPoints[0]][destPoints[1]].getPiece().color))
            {
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
