package chess;



import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;


public class Square {
    private int height;
    private int width;
    private int x;
    private int y;
    private Rectangle space;
    private Piece piece;
    private String pieceName;
    private String color;
    private int horizontalPosition;
    private int verticalPosition;
    private static String[][] position;
    private VBox vbox;
    
    
    Square(int height, int width, int x, int y, int horizontalPosition, int verticalPosition, String color, String pieceName, VBox vbox)
    {
        this.height=height;
        this.width=width;
        this.x=x;
        this.vbox=vbox;
        this.y=y;
        this.color=color;
        this.pieceName=pieceName;
        this.horizontalPosition=horizontalPosition;
        this.verticalPosition=verticalPosition;
        space = new Rectangle(height,width,x,y);
        setPiece(pieceName);
        if(position == null)
            createMap();
    }
    
    public Piece getPiece(){
        return piece;
    }
    
    
    public Rectangle getRectangle()
    {
        return space;
    }
    
    public VBox getBox(){
        return vbox;
    }
    
    private static void createMap()
    {
        position = new String[8][8];
        for(int i=0;i<8;i++)
        {
            int z=0;
            for(int j=72;j>=65;j--)
            {
                position[i][z]=(char)j+""+(i+1); //uses ascii char placement for letters
                z++;
            }
        }
        
    }
    
    public int getXPosition()
    {
        return horizontalPosition;
    }
    
    public int getYPosition()
    {
        return verticalPosition;
    }
    
    public static String getLocation(int x, int y)
    {
        return position[x][y];
    }
    public final void setPiece(String piece){
        switch (piece) {
            case "black King":
                {
                    this.piece = new King("black King");
                    ImagePattern imagePattern = new ImagePattern(this.piece.getImage());
                    space.setFill(imagePattern);
                    break;
                }
            case "black Rook":
                {
                    this.piece = new Rook("black Rook");
                    ImagePattern imagePattern = new ImagePattern(this.piece.getImage());
                    space.setFill(imagePattern);
                    break;
                }
            case "black Knight":
                {
                    this.piece = new Knight("black Knight");
                    ImagePattern imagePattern = new ImagePattern(this.piece.getImage());
                    space.setFill(imagePattern);
                    break;
                }
            case "black Bishop":
                {
                    this.piece = new Bishop("black Bishop");
                    ImagePattern imagePattern = new ImagePattern(this.piece.getImage());
                    space.setFill(imagePattern);
                    break;
                }
            case "black Queen":
                {
                    this.piece = new Queen("black Queen");
                    ImagePattern imagePattern = new ImagePattern(this.piece.getImage());
                    space.setFill(imagePattern);
                    break;
                }
            case "black Pawn":
                {
                    this.piece = new Pawn("black Pawn");
                    ImagePattern imagePattern = new ImagePattern(this.piece.getImage());
                    space.setFill(imagePattern);
                    break;
                }
            case "white King":
                {
                    this.piece = new King("white King");
                    ImagePattern imagePattern = new ImagePattern(this.piece.getImage());
                    space.setFill(imagePattern);
                    break;
                }
            case "white Rook":
                {
                    this.piece = new Rook("white Rook");
                    ImagePattern imagePattern = new ImagePattern(this.piece.getImage());
                    space.setFill(imagePattern);
                    break;
                }
            case "white Knight":
                {
                    this.piece = new Knight("white Knight");
                    ImagePattern imagePattern = new ImagePattern(this.piece.getImage());
                    space.setFill(imagePattern);
                    break;
                }
            case "white Bishop":
                {
                    this.piece = new Bishop("white Bishop");
                    ImagePattern imagePattern = new ImagePattern(this.piece.getImage());
                    space.setFill(imagePattern);
                    break;
                }
            case "white Queen":
                {
                    this.piece = new Queen("white Queen");
                    ImagePattern imagePattern = new ImagePattern(this.piece.getImage());
                    space.setFill(imagePattern);
                    break;
                }
            case "white Pawn":
                {
                    this.piece = new Pawn("white Pawn");
                    ImagePattern imagePattern = new ImagePattern(this.piece.getImage());
                    space.setFill(imagePattern);
                    break;
                }
            default:
                {
                    this.piece = new Empty(" ");
                    ImagePattern imagePattern = new ImagePattern(this.piece.getImage());
                    space.setFill(imagePattern);
                    this.piece.color="empty";
                    this.piece.imageName="empty";
                    break;
                }
        }
    }
}


