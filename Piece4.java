import javafx.scene.paint.Color;
/**
 * Extends Tetris shape class and creates one of the 7 pieces by
 * arranging the 4 Tetris squares and giving them color
 * @author LiamO
 *
 */
public class Piece4 extends TetrisShape {
		public Piece4(TetrisBoard board) {
			super(board);
			generalShape[0].moveToTetrisLocation(board.X_DIM_SQUARES/2, 2);
			generalShape[1].moveToTetrisLocation(generalShape[0].getX() + 1, generalShape[0].getY());
			generalShape[2].moveToTetrisLocation(generalShape[0].getX(), generalShape[0].getY() -1);
			generalShape[3].moveToTetrisLocation(generalShape[0].getX() - 1, generalShape[0].getY() -1);
			for(int i=0; i < generalShape.length; i++){
				generalShape[i].setColor(Color.YELLOW);
				generalShape[i].setStroke(Color.BLACK);
			}
		}
		
    }