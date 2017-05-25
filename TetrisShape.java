import java.util.Arrays;

import com.sun.javafx.geom.Point2D;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
/**
 * This class will set up an array with 4 tetris squares and give access to each square
 * @author Liam O'Toole
 */
public class TetrisShape  {
	protected  TetrisSquare[] generalShape = new TetrisSquare[4];
    private TetrisBoard board;
    /**
     * This constructor creates the array of 4 pieces that will be displayed on the board
     * @param board
     */
    public TetrisShape(TetrisBoard board){
    	this.board = board;
    	for(int i =0; i < generalShape.length; i++){
    		generalShape[i] = new TetrisSquare(board);
    	}
    }
    /**
     * These 4 classes gives access to the objects
     * 4 squares by returning the specific square object
     * @return a tetris square
     * 
     */
	public TetrisSquare getCenter(){
		return generalShape[0];
	}
	public TetrisSquare getSquare2(){
		return generalShape[1];
	}
	public TetrisSquare getSquare3(){
		return generalShape[2];
	}
	public TetrisSquare getSquare4(){
		return generalShape[3];
	}
}

