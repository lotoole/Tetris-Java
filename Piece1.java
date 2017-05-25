import javafx.scene.paint.Color;
/**
 * Extends Tetris shape class and creates one of the 7 pieces by
 * arranging the 4 Tetris squares and giving them color
 * @author LiamO
 *
 */
public class Piece1 extends TetrisShape {
	
		public Piece1(TetrisBoard board) {
			super(board);
			generalShape[0].moveToTetrisLocation(board.X_DIM_SQUARES/2, 2);
			generalShape[1].moveToTetrisLocation(super.getCenter().getX(), super.getCenter().getY() -1);
			generalShape[2].moveToTetrisLocation(super.getCenter().getX() -1, super.getCenter().getY() -1);
			generalShape[3].moveToTetrisLocation(super.getCenter().getX() -1, super.getCenter().getY());
			for(int i=0; i < generalShape.length; i++){
				generalShape[i].setColor(Color.BLACK);
				generalShape[i].setStroke(Color.WHITE);
			}
			
		}
		
    }