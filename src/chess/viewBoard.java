/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;




import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class viewBoard extends Application {
    
        private Square[][] board = new Square[8][8];
        private Game game = new Game();
        private int width=400;
        private int increment=width/8;
        private int height=400;
        private VBox rows = new VBox();

        
    public String initializePieces(int t, int z)
    {
        String piece=" ";
        if(z == 0)
            {
            switch(t)
            {
                case(0):
                case(7):
                    piece="black Rook";
                    break;
                case(1):
                case(6):
                    piece="black Knight";
                    break;
                case(2):
                case(5):
                    piece="black Bishop";
                    break;
                case(3):
                    piece="black Queen";
                    break;
                case(4):
                    piece="black King";
                    break;      
            }
        }
        else if( z == 1)
        {
            switch(t)
            {
                default:
                    piece="black Pawn";
            }
        }
        else if(z == 6)
        {
            switch(t)
            {
                default:
                    piece="white Pawn";
            }
        }
        else if(z==7)
        {
        switch(t)
            {
                case(0):
                case(7):
                    piece="white Rook";
                    break;
                case(1):
                case(6):
                    piece="white Knight";
                    break;
                case(2):
                case(5):
                    piece="white Bishop";
                    break;
                case(3):
                    piece="white Queen";
                    break;
                case(4):
                    piece="white King";
                    break;      
            }
        }
        else{
        piece=" ";
        }
        
        return piece;
    }
    
    public Square[][] getBoard()
    {
        return board;
    }
    
    public void createSquare(int t, int z, int i, int j, VBox vbox, String piece, String color)
    {
        board[t][z] = new Square(i, j, (int)(increment*0.96), (int)(increment*0.96) , t, z, "WHITE", piece, vbox); // gives image
                    vbox.setStyle(
                      "-fx-border-style: solid inside;" + 
                      "-fx-border-width: 2;" +
                      "-fx-border-color: black;" +
                        "-fx-background-color: "+color);
    }
    
    public void start(Stage stage){
        int     x=0, 
                y=0,
                increment=width/8; //width and height must be divisible by 8
                game.setBoard(board);
     

            int row=0;
            for(int j=y,z=0;j<height;j+=increment,z++){
            int col=0;
            HBox hbox = new HBox();
            for(int i=x,t=0;i<width;i+=increment,t++)
            {
                String piece = initializePieces(t,z); //allows background color for squares
                VBox vbox = new VBox(); 
                if(row % 2 == 0 && col % 2 == 0)
                {
                    createSquare(t,z,i,j,vbox, piece, "#ffffe6");
                }
                else if(row % 2 != 0 && col % 2 != 0)
                {
                    createSquare(t,z,i,j,vbox, piece , "#ffffe6");
                }
                else
                {
                    createSquare(t,z,i,j,vbox, piece, "#996633");
                }
                vbox.getChildren().add(board[t][z].getRectangle()); //add image to square
                hbox.getChildren().add(vbox); 
                col++;
            }
            row++;
            rows.getChildren().add(hbox); // add to outer container
        }
           Scene scene = new Scene(rows); //show container
           game.setScene(scene);
           game.setStage(stage);
           game.handleClicks(increment);
            stage.setScene(scene); 
            stage.show(); 
    }
}


