/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * A Pane in which tetris squares can be displayed.
 * 
 * @author pipWolfe
 */
public class TetrisBoard extends Pane{
    // The size of the side of a tetris square
    public static final int SQUARE_SIZE = 20;
    // The number of squares that fit on the screen in the x and y dimensions
    public static final int X_DIM_SQUARES = 20;
    public static final int Y_DIM_SQUARES = 30;
    //create variable to keep track of what row is full
    public static int fullRow;
    //variable to keep track of score
    public static int score;
    //boolean for game ending
    public static boolean gameOver = false;
    //game end row
    private static final int gameOverRow = 3;
    //data structure
    private static TetrisSquare[][] boardTetrisSquares = new TetrisSquare[Y_DIM_SQUARES]
    		[X_DIM_SQUARES];

    /**
     * Sizes the board to hold the specified number of squares in the x and y
     * dimensions.
     */
    public TetrisBoard() {
        this.setPrefHeight(Y_DIM_SQUARES*SQUARE_SIZE);
        this.setPrefWidth(X_DIM_SQUARES*SQUARE_SIZE);
        BackgroundFill myBF = new BackgroundFill(Color.WHITE, new CornerRadii(1),
            new Insets(0.0,0.0,0.0,0.0));// or null for the padding
        setBackground(new Background(myBF));
    }
    /**
     * Adds a tetris square to the data structure
     * @param A single TetrisShape
     * 
     */
    // a method that adds TetrisSquare object to the board, at the square's current x, y location. 
    public static void addShapeToBoard(TetrisShape shape){
    	boardTetrisSquares[shape.getCenter().getY()][shape.getCenter().getX()] = shape.getCenter();
    	boardTetrisSquares[shape.getSquare2().getY()][shape.getSquare2().getX()] = shape.getSquare2();
    	boardTetrisSquares[shape.getSquare3().getY()][shape.getSquare3().getX()] = shape.getSquare3();
    	boardTetrisSquares[shape.getSquare4().getY()][shape.getSquare4().getX()] = shape.getSquare4();
    }

    /**
     * 
     *  Check the left edges of the board
     *  return true if it is not the left edge of the board
     *  @param A tetris square
     */
    public static boolean checkLeftEdge(TetrisShape shape){
    	TetrisSquare[] squares = new TetrisSquare[]{shape.getCenter(), shape.getSquare2(), shape.getSquare3(), shape.getSquare4()};
    	if(shape.getCenter().getX() - 1 < 0){
    		return false;
    	}
    	else if(shape.getSquare2().getX() - 1 < 0){
    		return false;
    	}
    	else if(shape.getSquare3().getX() - 1 < 0){
    		return false;
    	}
    	else if(shape.getSquare4().getX() - 1 < 0){
    		return false;
    	}
    	for(TetrisSquare sq: squares){
    		if(boardTetrisSquares[sq.getY()][sq.getX() - 1] != null){
    			return false;
    		}
    	}
    		return true;
    	
    }
    /**
     *  Check the right edges of the board
     *  return true if it is not the right edge of the board
     *  @param A tetris square
     */
    public static boolean checkRightEdge(TetrisShape shape){
    	TetrisSquare[] squares = new TetrisSquare[]{shape.getCenter(), shape.getSquare2(), shape.getSquare3(), shape.getSquare4()};
    	if (shape.getCenter().getX() + 1 >= X_DIM_SQUARES){
    		return false;
    	}
    	else if (shape.getSquare2().getX() + 1 >= X_DIM_SQUARES){
    		return false;
    	}
       	else if (shape.getSquare3().getX() >= X_DIM_SQUARES){
    		return false;
    	}
       	else if (shape.getSquare4().getX() + 1 >= X_DIM_SQUARES){
    		return false;
    	}
    	for(TetrisSquare sq: squares){
    		if(boardTetrisSquares[sq.getY()][sq.getX() + 1] != null){
    			return false;
    		}
    	}
    		return true;
    }
    public static boolean checkEdges(TetrisShape shape){
    	TetrisSquare[] squares = new TetrisSquare[]{shape.getCenter(), shape.getSquare2(), shape.getSquare3(), shape.getSquare4()};
    	boolean okay = true;
    	for (TetrisSquare sq: squares){
    		if (sq.getX() < 0 || sq.getX() >= X_DIM_SQUARES - 1 || sq.getY() < 0 || sq.getY() >= Y_DIM_SQUARES - 1){ 
    			return false;
    		}
    	}
    	return okay;
    }
    /**
     *  Check the bpttpm edges of the board
     *  return true if it is not the bpttpm edge of the board
     *  @param A tetris square
     */
    public static boolean checkAll(TetrisShape shape){    	
    	TetrisSquare[] squares = new TetrisSquare[]{shape.getCenter(), shape.getSquare2(), shape.getSquare3(), shape.getSquare4()};
    	boolean okay = true;

    	    	for (TetrisSquare sq: squares){
    	    		if (sq.getY() < 0 || sq.getY() >= Y_DIM_SQUARES - 1 || boardTetrisSquares[sq.getY() + 1][sq.getX()] != null){ 
    	    			return false;
    	    		}

    	}
    	return okay;
    }
    /*
     * check borders for right rotate
     */
	public static boolean checkRotateRight(TetrisShape shape) {
		TetrisSquare[] squares = new TetrisSquare[] { shape.getSquare2(), shape.getSquare3(), shape.getSquare4() };
		if(checkLeftEdge(shape) == false){
			return false;
		}
		for (TetrisSquare sq: squares){
		if (boardTetrisSquares [shape.getCenter().getY() + (sq.getX() - shape.getCenter().getX())] 
				[shape.getCenter().getX() - (sq.getY() - shape.getCenter().getY())] != null) {
			return false;
		}
		}
		return true;
	}
    /*
     * check borders for left rotate
     */
	public static boolean checkRotateLeft(TetrisShape shape) {
		TetrisSquare[] squares = new TetrisSquare[] { shape.getSquare2(), shape.getSquare3(), shape.getSquare4() };
		if(checkLeftEdge(shape) == false){
			return false;
		}
		for (TetrisSquare sq: squares){
		if (boardTetrisSquares
				[shape.getCenter().getY() - (sq.getX() - shape.getCenter().getX())]
						[shape.getCenter().getX() + (sq.getY() - shape.getCenter().getY())] != null) {
			return false;
		}}
		return true;
	}
    /**
     * Check to see if a row is full
     * Clear full rows and move rows down
     */
    public static void updateRows(){
    	//loop that checks each row to see if it is full
    	for(int row = 0; row < Y_DIM_SQUARES; row++){
    		//if the row is full, clear the row
    		if(checkFullRow(row)){
    		for(int k = 0; k < X_DIM_SQUARES; k++){
    			boardTetrisSquares[row][k].removeFromDrawing();
    			boardTetrisSquares[row][k] = null;
    		}
    		moveRowsDown(row);
    		score += X_DIM_SQUARES;
    		}
    	}
    }
    /*
     * Check for full rows
     * return false if not full
     * true if full
     * @param row number
     */
    private static boolean checkFullRow(int row){
//    	boolean fullRow = true;
    	for(int j = 0; j < boardTetrisSquares[row].length; j++) {
    		if(boardTetrisSquares[row][j] == null){
    			return false;
    		}
    	}
    	return true;
    }
    
    /*
     * Move rows down
     * @param the full row number
     */
	private static void moveRowsDown(int row) {
		// loop that moves rows down
		for (int i = row; i > 1; i--) {
			for (int j = 0; j < X_DIM_SQUARES; j++) {
				if (boardTetrisSquares[i - 1][j] != null) {
					boardTetrisSquares[i - 1][j].moveToTetrisLocation(boardTetrisSquares[i - 1][j].getX(),
							boardTetrisSquares[i - 1][j].getY() + 1);
				}
			}
			boardTetrisSquares[i] = boardTetrisSquares[i - 1];
		}
		boardTetrisSquares[0] = new TetrisSquare[X_DIM_SQUARES];
	}
    /*
     * Return the current score
     */
    public static String getScore(){
    	return Integer.toString(score);
    }
    /*
     * Check to see if game should end 
     */
    public boolean gameOver(){
    	//check to see if genereated piece can't move
    	//if it can't, change game over to true
			for(int j = 0; j < X_DIM_SQUARES; j++){
		    	if(boardTetrisSquares[gameOverRow][j] != null){
		    		return true;
		    	}
			}
    	return false;
    }
}


