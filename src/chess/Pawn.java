/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

/* Java fx is a library that creates Java GUI applications*/
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;


/**
 *
 * @author coleb29
 */

/* class Piece automatically has all variables and methods defined in Pawn class*/
public class Pawn extends Piece {
    
Pawn(String colorPiece)
    {
        super(colorPiece);
    }

private String updated="";

/* makes a popup message*/
private void createPopUp(String color){

 String optionalPieces[] = 
                   { "Queen", "Rook", "Bishop", 
                                   "Knight" }; 
  
        // Create a combo box 
        ComboBox combo_box = 
                    new ComboBox(FXCollections 
                              .observableArrayList(optionalPieces)); 
  
                Stage newWindow = new Stage();
                newWindow.setTitle("Promotion");
                Scene secondScene = new Scene(combo_box, 400, 50);
                newWindow.setScene(secondScene);
                newWindow.setX(20);
                newWindow.setY(20);        // Set on action 

        EventHandler<ActionEvent> event;
    event = new EventHandler<ActionEvent>() { 
        public void handle(ActionEvent e)
        {
            updated=color+ " "+ combo_box.getValue();
            newWindow.close();
        }
    }; 
        combo_box.setOnAction(event);         
        newWindow.showAndWait();

}

   //verifies move is possible for each piece, if it is returns the new player. Otherwise returns current player
    //Overrides piece because it needs to handle promotions
@Override    
public boolean move(int posX, int posY, Stage newWindow, boolean playerOneTurn, int lastX, int lastY){
    while(!possible.isEmpty())
        {
           int[] points = possible.poll();
            if(posX != points[0] || posY != points[1])
            continue;
        if(playerOneTurn && board[lastX][lastY].getPiece().color.equals("white"))
        {
            updated = board[lastX][lastY].getPiece().updatePiece;
            if(posY == 0)
            {   createPopUp("white");
                System.out.println("Updated: " +updated);
            }
            board[lastX][lastY].setPiece(" "); // set old square to empty
            board[posX][posY].setPiece(updated); // move piece to new square
            board[posX][posY].getPiece().hasMoved=true;
            playerOneTurn=false;
        }  
        else if(!playerOneTurn && board[lastX][lastY].getPiece().color.equals("black") )
        {
            updated = board[lastX][lastY].getPiece().updatePiece;
            if(posY == 7)
            {   createPopUp("black");
                System.out.println("Updated: " +updated);
            }
            board[lastX][lastY].setPiece(" ");
            board[posX][posY].setPiece(updated);
            board[posX][posY].getPiece().hasMoved=true;
            playerOneTurn=true;
        }
        }
        newWindow.close(); 
        return playerOneTurn;
    }


    @Override
    /* possible movesets at specific location*/
    public void possibleMove(int t, int z, String colorBorder)
    {
            int upOne,upTwo, diagLeft, diagRight;
            if(color.equals("black"))
            {
                upOne=z+1;
                upTwo=z+2;
                diagLeft=t-1;
                diagRight=t+1;
            } 
            else if(color.equals("white")){
                upOne=z-1;
                upTwo=z-2;
                diagLeft=t-1;
                diagRight=t+1;
            }
            else
            {
                upOne=8;
                upTwo=8;
                diagLeft=8;
                diagRight=8;
            }
            if(isValid(diagLeft,upOne) && !board[diagLeft][upOne].getPiece().isEmpty() && !board[t][z].getPiece().color.equals(board[diagLeft][upOne].getPiece().color))
            {
                        board[diagLeft][upOne].getPiece().setBorder(diagLeft, upOne, colorBorder);
                        possible.offer(new int[]{diagLeft,upOne});
            }
            if(isValid(diagRight,upOne) && !board[diagRight][upOne].getPiece().isEmpty() && !board[t][z].getPiece().color.equals(board[diagRight][upOne].getPiece().color))
            {
                        board[diagRight][upOne].getPiece().setBorder(diagRight, upOne, colorBorder);
                        possible.offer(new int[]{diagRight,upOne});
            }
            if(isValid(t,upOne) && board[t][upOne].getPiece().isEmpty())
            {
                        board[t][upOne].getPiece().setBorder(t, upOne, colorBorder);
                        possible.offer(new int[]{t,upOne});
            }
            if((z == 1 || z == 6) && isValid(t,upTwo)&& board[t][upTwo].getPiece().isEmpty())
            {
                board[t][upTwo].getPiece().setBorder(t, upTwo, colorBorder);
  
                        possible.offer(new int[]{t,upTwo});
            }
        
    }
    
        @Override
     /* removes move at specific location(t,z)*/
    public void removeMove(int t, int z)
    {

            int upOne,upTwo, diagLeft, diagRight;
            if(color.equals("black"))
            {
                upOne=z+1;
                upTwo=z+2;
                diagLeft=t-1;
                diagRight=t+1;
            } 
            else if(color.equals("white")){
                upOne=z-1;
                upTwo=z-2;
                diagLeft=t-1;
                diagRight=t+1;
            }
            else
            {
                upOne=8;
                upTwo=8;
                diagLeft=8;
                diagRight=8;
            }
            if(isValid(diagLeft,upOne) && !board[diagLeft][upOne].getPiece().isEmpty() && !board[t][z].getPiece().color.equals(board[diagLeft][upOne].getPiece().color))
                board[diagLeft][upOne].getPiece().setBorder(diagLeft, upOne, "black");
            if(isValid(diagRight,upOne) && !board[diagRight][upOne].getPiece().isEmpty() && !board[t][z].getPiece().color.equals(board[diagRight][upOne].getPiece().color))
                board[diagRight][upOne].getPiece().setBorder(diagRight, upOne, "black");
            if(isValid(t,upOne) && board[t][upOne].getPiece().isEmpty())
                board[t][upOne].getPiece().setBorder(t, upOne, "black");
            if((z == 1 || z == 6) && isValid(t,upTwo)&& board[t][upTwo].getPiece().isEmpty())
                board[t][upTwo].getPiece().setBorder(t, upTwo, "black");

    }
    
    
}
