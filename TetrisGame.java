/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Font;
import java.util.Random;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * This should be implemented to include your game control.
 * 
 * @author pipWolfe
 */
public class TetrisGame {
	private final Tetris tetrisApp;
	private TetrisShape currentShape;
	private TetrisBoard board;
	/**
	 * Initialize the game. Remove the example code and replace with code that
	 * creates a random piece.
	 * 
	 * @param tetrisApp
	 *            A reference to the application (use to set messages).
	 * @param board
	 *            A reference to the board on which Squares are drawn
	 */
	public TetrisGame(Tetris tetrisApp, TetrisBoard board) {
		this.board = board;
		currentShape = getRandomShape(board);
		this.tetrisApp = tetrisApp;
		// You can use this to show the score, etc.
		tetrisApp.setMessage("Game has started!");
	}

	private TetrisShape getRandomShape(TetrisBoard board) {
		Random rand = new Random();
		// Random number between 1-7
		//rand.nextInt(7) + 1
		int whichPiece = rand.nextInt(7) + 1;
		// loop for adding random piece to board
		if (whichPiece == 1) {
			Piece1 shape1 = new Piece1(board);
			currentShape = shape1;
		} else if (whichPiece == 2) {
			Piece2 shape2 = new Piece2(board);
			currentShape = shape2;
		} else if (whichPiece == 3) {
			Piece3 shape3 = new Piece3(board);
			currentShape = shape3;
		} else if (whichPiece == 4) {
			Piece4 shape4 = new Piece4(board);
			currentShape = shape4;
		} else if (whichPiece == 5) {
			Piece5 shape5 = new Piece5(board);
			currentShape = shape5;
		} else if (whichPiece == 6) {
			Piece6 shape6 = new Piece6(board);
			currentShape = shape6;
		} else if (whichPiece == 7) {
			Piece7 shape7 = new Piece7(board);
			currentShape = shape7;
		}
		return currentShape;

	}

	/**
	 * Animate the game, by moving the current tetris piece down.
	 * 
	 */
	void update() {
		 if(board.gameOver()) {
			tetrisApp.setMessage("Game is over, your final score was: " + TetrisBoard.getScore());
			
		}
			else if(TetrisBoard.checkAll(currentShape)
					&& board.gameOver() == false){
			currentShape.getCenter().moveToTetrisLocation(currentShape.getCenter().getX(),
					currentShape.getCenter().getY() + 1);
			currentShape.getSquare2().moveToTetrisLocation(currentShape.getSquare2().getX(),
					currentShape.getSquare2().getY() + 1);
			currentShape.getSquare3().moveToTetrisLocation(currentShape.getSquare3().getX(),
					currentShape.getSquare3().getY() + 1);
			currentShape.getSquare4().moveToTetrisLocation(currentShape.getSquare4().getX(),
					currentShape.getSquare4().getY() + 1);
			}
		else if(board.gameOver() == false) {
			TetrisBoard.addShapeToBoard(currentShape);
			currentShape = getRandomShape(board);
		}
		//check for full rows and remove them and move rows down
		if(board.gameOver() == false){
			TetrisBoard.updateRows();
			tetrisApp.setMessage(TetrisBoard.getScore());
		}

	}
	/**
	 * Drop the current tetris piece.
	 */
	void drop() {
		for(int i = 0; i < TetrisBoard.Y_DIM_SQUARES; i++){
			if(TetrisBoard.checkAll(currentShape) == true
					&& board.gameOver() == false){
				currentShape.getCenter().moveToTetrisLocation(currentShape.getCenter().getX(),
						currentShape.getCenter().getY() + 1);
				currentShape.getSquare2().moveToTetrisLocation(currentShape.getSquare2().getX(),
						currentShape.getSquare2().getY() + 1);
				currentShape.getSquare3().moveToTetrisLocation(currentShape.getSquare3().getX(),
						currentShape.getSquare3().getY() + 1);
				currentShape.getSquare4().moveToTetrisLocation(currentShape.getSquare4().getX(),
						currentShape.getSquare4().getY() + 1);
			}
			else {
				TetrisBoard.addShapeToBoard(currentShape);
			}
		}
	}
	/**
	 * Move the current tetris piece left.
	 */
	void left() {
		if(TetrisBoard.checkAll(currentShape) == true
				&& board.checkLeftEdge(currentShape) == true
				&& board.gameOver() == false){
			currentShape.getCenter().moveToTetrisLocation(currentShape.getCenter().getX() - 1,
					currentShape.getCenter().getY());
			currentShape.getSquare2().moveToTetrisLocation(currentShape.getSquare2().getX() - 1,
					currentShape.getSquare2().getY());
			currentShape.getSquare3().moveToTetrisLocation(currentShape.getSquare3().getX() - 1,
					currentShape.getSquare3().getY());
			currentShape.getSquare4().moveToTetrisLocation(currentShape.getSquare4().getX() - 1,
					currentShape.getSquare4().getY());
		}
		else {
			tetrisApp.setMessage("Can't move here");
		}

	}

	/**
	 * Move the current tetris piece right.
	 */
	void right() {
		// get the square for the current piece and add 1 to the x value
		if(TetrisBoard.checkAll(currentShape) == true
				&& board.checkRightEdge(currentShape) == true
				&& board.gameOver() == false){
		currentShape.getCenter().moveToTetrisLocation(currentShape.getCenter().getX() + 1,
				currentShape.getCenter().getY());
		currentShape.getSquare2().moveToTetrisLocation(currentShape.getSquare2().getX() + 1,
				currentShape.getSquare2().getY());
		currentShape.getSquare3().moveToTetrisLocation(currentShape.getSquare3().getX() + 1,
				currentShape.getSquare3().getY());
		currentShape.getSquare4().moveToTetrisLocation(currentShape.getSquare4().getX() + 1,
				currentShape.getSquare4().getY());
		} else {
			tetrisApp.setMessage("Can't move here");
		}
	}


	/**
	 * Rotate the current piece counter-clockwise.
	 */
	void rotateLeft() {
		if(TetrisBoard.checkAll(currentShape) == true
				&& TetrisBoard.checkEdges(currentShape) == true
				&& board.gameOver() == false
				&& TetrisBoard.checkRotateLeft(currentShape)){
		currentShape.getSquare2().moveToTetrisLocation(currentShape.getCenter().getX() +

				(currentShape.getSquare2().getY() - currentShape.getCenter().getY()),
				currentShape.getCenter().getY() - (currentShape.getSquare2().getX() - currentShape.getCenter().getX()));

		currentShape.getSquare3().moveToTetrisLocation(
				currentShape.getCenter().getX() + (currentShape.getSquare3().getY() - currentShape.getCenter().getY()),
				currentShape.getCenter().getY() - (currentShape.getSquare3().getX() - currentShape.getCenter().getX()));

		currentShape.getSquare4().moveToTetrisLocation(
				currentShape.getCenter().getX() + (currentShape.getSquare4().getY() - currentShape.getCenter().getY()),
				currentShape.getCenter().getY() - (currentShape.getSquare4().getX() - currentShape.getCenter().getX()));
		}
		else {
			tetrisApp.setMessage("Can't move here");
		}
	}

	/**
	 * Rotate the current piece clockwise.
	 */
	void rotateRight() {
		// Here to get the new relative x, the current center x - the difference
		// between the current squares y and the center y gives the new relative
		// value
		if(TetrisBoard.checkAll(currentShape) == true
				&& TetrisBoard.checkEdges(currentShape) == true
				&& board.gameOver() == false
				&& TetrisBoard.checkRotateRight(currentShape)){
		currentShape.getSquare2().moveToTetrisLocation(
				currentShape.getCenter().getX() - (currentShape.getSquare2().getY() - currentShape.getCenter().getY()),

				currentShape.getCenter().getY() + (currentShape.getSquare2().getX() - currentShape.getCenter().getX()));

		currentShape.getSquare3().moveToTetrisLocation(
				currentShape.getCenter().getX() - (currentShape.getSquare3().getY() - currentShape.getCenter().getY()),
				currentShape.getCenter().getY() + (currentShape.getSquare3().getX() - currentShape.getCenter().getX()));

		currentShape.getSquare4().moveToTetrisLocation(
				currentShape.getCenter().getX() - (currentShape.getSquare4().getY() - currentShape.getCenter().getY()),
				currentShape.getCenter().getY() + (currentShape.getSquare4().getX() - currentShape.getCenter().getX()));
		}
		else {
			tetrisApp.setMessage("Can't move here");
		}
	}
	public String finalScore(){
		return "Game is over, your final score was: " + TetrisBoard.getScore();
	}
}
