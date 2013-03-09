//Kru Shah & Darrel Jiang

import java.awt.event.*;
import javax.swing.*;

/**
 * A class that creates all the elements necessary for a player
 * to traverse the maze.
 */
public class Player extends JPanel implements ActionListener{
	
	private int money=0;
	private int xPos=0;
	private int yPos=0;
	
	private final Board board;
	
	/**
	 * Constructor for the player that will be set on the specified
	 * board. The player will automatically start at the top left 
	 * of the board
	 * @param board The board the player will play on.
	 */
	public Player(Board board){
		this.board = board;
		xPos = 1;
		yPos = 1;
	}	
	
	/**
	 * The method repaints the board.
	 */
	public void actionPerformed(ActionEvent e){
		repaint();
	}
	
	/**
	 * Inner class that allows for the keyboard input of
	 * {w,a,s,d} to move up, left, down, right.
	 */
	public class MyKeyListener extends KeyAdapter{
		public void keyPressed(KeyEvent e){
			if(e.getKeyChar() == 'a') 	moveLeft(board);
			if(e.getKeyChar() == 'w') 	moveUp(board);
			if(e.getKeyChar() == 'd')	moveRight(board);
			if(e.getKeyChar() == 's')	moveDown(board);
		
		}
	}
	
	/**
	 * Allows the player to move left, checking for if the player's next
	 * move hits a win cell or a coin cell.
	 * @param board The board the player will move on.
	 */
	public void moveLeft(Board board){
		if((board.get(xPos-1, yPos) != '#') && (board.get(xPos-1, yPos) != '=')){
			board.set(xPos, yPos, 'O');
			if(board.get(xPos-=1, yPos) == '8')	Win();
			else if(board.get(xPos, yPos) == '+') money++;
			else 	board.set(xPos, yPos, 'X');
		}
	}
	
	/**
	 * Allows the player to move right, checking for if the player's next
	 * move hits a win cell or a coin cell.
	 * @param board The board the player will move on.
	 */
	public void moveRight(Board board){
		if((board.get(xPos+1, yPos) != '#') && (board.get(xPos+1, yPos) != '=')){
			board.set(xPos, yPos, 'O');
			if(board.get(xPos+=1, yPos) == '8')	Win();
			else if(board.get(xPos, yPos) == '+') money++;
			else	board.set(xPos, yPos, 'X');
		}
	}
	
	/**
	 * Allows the player to move up, checking for if the player's next
	 * move hits a win cell or a coin cell.
	 * @param board The board the player will move on.
	 */
	public void moveUp(Board board){
		if((board.get(xPos, yPos-1) != '#') && (board.get(xPos, yPos-1) != '=')){
			board.set(xPos, yPos, 'O');
			if(board.get(xPos, yPos-=1) == '8')	Win();
			else if(board.get(xPos, yPos) == '+') money++;
			else	board.set(xPos, yPos, 'X');
		}
	}
	
	/**
	 * Allows the player to move down, checking for if the player's next
	 * move hits a win cell or a coin cell.
	 * @param board The board the player will move on.
	 */
	public void moveDown(Board board){
		if((board.get(xPos, yPos+1) != '#') && (board.get(xPos, yPos+1) != '=')){
			board.set(xPos, yPos, 'O');
			if(board.get(xPos, yPos+=1) == '8') Win();
			else if(board.get(xPos, yPos) == '+') money++;
			else	board.set(xPos, yPos, 'X');
		}
	}
	
	/**
	 * A win method that will display the number of coins obtained.
	 */
	public void Win(){
        new MazeFrame(20, money);        
	}	
}	
