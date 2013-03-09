//Kru Shah & Darrel Jiang

import java.awt.*;
import javax.swing.*;
import java.util.*;

/**
 * A board class that generates a full game board represented by
 * a 2D array of characters.
 */
public class Board extends JPanel {
	
	private char[][] board;
	private int size;
	private int unVisited;
	private int scale=1; 
	LinkedList<Position> positionList = new LinkedList<Position>();
	
	/**
	 * The constructor for the board, taking in an x and a y as the 
	 * length and height of the board. The outer walls are represented
	 * with the '#', inner walls are '=', freespace is 'u'. 
	 * @param x the size of the board in the x direction
	 * @param y the size of the board in the y direction
	 */
	public Board(int x, int y, int obj){
		unVisited = (x*x);
		x *= 2; y *= 2; x++; y++;
		scale = y;
		
		board = new char [x][y];
		size = x;
		
		generateBoard();
		addObject(obj);
	}	

	/**
	 * The generateBoard() resets the board	
	 */
	public void generateBoard(){
		for (int i=0; i < size; i++){
			for (int k=0; k < size; k++){
				board[i][k] = 'u';
			}
		}
		
		for (int i=0; i < size; i+=2){
			for (int k=0; k < size; k++){
				board[i][k] = '=';
				board[k][i] = '=';
			}
		}
		for (int i=0; i < size; i++){
			board[i][0] = '#';
			board[0][i] = '#';
			board[size-1][i] = '#';
			board[i][size-1] = '#';
		}
		generate(1,1);
	}
	
	
	/**
	 * Modification of the paint method that correctly paints the 
	 * game board on the canvas with respect to the 2D array of the
	 * maze, scaled accordingly.
	 */
	public void paint(Graphics g){
		super.paint(g);
		int n = 500/(scale+10);
		
		for(int i = 0; i < size; i++){
			for( int k = 0; k < size; k++){
				if((board[i][k] == '#')){
					g.setColor(Color.black);
					g.fillRect(i*n, k*n, n, n);
				} else if(board[i][k] == '='){
					g.setColor(Color.black);
					g.fillRect(i*n, k*n, n, n);
				} else if(board[i][k] == '8'){
					g.setColor(Color.red);
					g.fillRect(i*n, k*n, n, n);
				} else if(board[i][k] == 'X'){
					g.setColor(Color.blue);
					g.fillRect(i*n, k*n, n, n);
				} else if(board[i][k] == '+'){
					g.setColor(Color.yellow);
					g.fillOval(i*n, k*n, n, n);
				}
			}
		}
	}	
	
	/**
	 * A function to get the value at the specified coordinates
	 * @param x X coordinate.
	 * @param y Y coordinate.
	 * @return the value at the x and y coordinate.
	 */
	public char get(int x, int y){
		return board[x][y];
	}
	
	/**
	 * A function to set the value at the specified coordinates
	 * @param x X coordinate.
	 * @param y Y coordinate.
	 * @param value The value to replace the existing character.
	 */
	public void set(int x, int y, char value){
		board[x][y] = value;
		repaint();
	}
	
	/** 
	 * A method that prints the text based version of the board.
	 */
	public void printBoard(){
		for (int i=0; i < size; i++){
			for (int k=0; k < size; k++){
				System.out.print(board[i][k]);
				System.out.print(" ");
			}
			System.out.println("");
		}
	}
	
	/**
	 * A function that adds amount number of objects to
	 * random locations on the maze
	 * @param amount The amount of objects that will be
	 * placed on the board.
	 */
	public void addObject(int amount){
		Random location = new Random();
		
		for(int i=0; i<amount; i++){
			int xPos=0, yPos=0;
			while((xPos==0) || (yPos==0)){
				int randX = location.nextInt(size);
				int randY = location.nextInt(size);
		
				if (((randX%2) != 0) && (randX != 0 || randX != size))	xPos = randX;
				if (((randY%2) != 0) && (randY != 0 || randY != size))	yPos = randY;
				
				if((get(xPos,yPos) == 'X') || get(xPos,yPos) == '8') {
					xPos = 0;yPos = 0;
				}
			}
			
			set(xPos, yPos, '+');
		}
	}
		
	/**
	 * A position structure thats sole purpose is to keep track
	 * of the x and y coordinates of the position. Used in generate.
	 */
	public class Position{
		int x; int y;
		public Position(int x, int y){
			this.x = x;
			this.y = y;
		}
		public Position(){
			this.x = 0;
			this.y = 0;
		}
		public int getX(){
			return x;
		}
		public int getY(){
			return y;
		}
		public void setX(int x){
			this.x = x;
		}
		public void setY(int y){
			this.y = y;
		}
	}
	
	/**
	 * A function whose sole purpose is to update the
	 * cardinal direction values with respect to the current
	 * Cell variable.
	 * @param cC Pass in the currentCell, which is a position
	 * @return the updated cardinal positions
	 */
	public char[] updateDirection(Position cC){
		char north=0,south=0,east=0,west=0;
		
		if (get(cC.getX(),cC.getY()+1) != '#')
			east = get(cC.getX(), cC.getY()+2);
		if (get(cC.getX(),cC.getY()-1) != '#')
			west = get(cC.getX(), cC.getY()-2);
		if (get(cC.getX()-1,cC.getY()) != '#')
			north = get(cC.getX()-2, cC.getY());
		if (get(cC.getX()+1,cC.getY()) != '#')
			south = get(cC.getX()+2, cC.getY());		
		char direction[] = {west,east,south,north};
		return direction;
	}

	
	Position posList[] = new Position[(2*(getX()/2))];
	Position cC = new Position(5,5);
	
	
	/**
	 * A function that begins to randomly generate a random
	 * maze on the game board at the specified posX and posY
	 * coordinates of the game board. Pseudocode used from Wikipedia.
	 * @param posX The x coordinates of the game board.
	 * @param posY The y coordinates of the game board.
	 */
	public void generate(int posX, int posY){
		cC = new Position(posX,posY);
		set(cC.getX(),cC.getY(), 'v');
		unVisited-=1;
		
		char north=0,south=0,east=0,west=0;
		char direction[] = {west,east,south,north};
		
		direction = updateDirection(cC);

		while(unVisited != 0){
			int free = 0;
			if((direction[0] == 'u') || (direction[1] == 'u') || (direction[2] == 'u') || (direction[3] == 'u'))
				free = 1;
			
			Random generator = new Random();
			int random = generator.nextInt(4);
			set(cC.getX(),cC.getY(), 'v');
		
			if((random == 0) && (direction[0] == 'u')){ //West
				if (get(cC.getX(),cC.getY()-1) != '#'){
					set(cC.getX(), cC.getY()-1, 'v');
					cC = new Position(cC.getX(), cC.getY()-2);
					positionList.push(cC);
					
					direction = updateDirection(cC);
					unVisited--;
					
				}
			}
			else if((random == 1) && (direction[1] == 'u')){ //East
				if (get(cC.getX(),cC.getY()+1) != '#'){
					set(cC.getX(), cC.getY()+1, 'v');
					cC = new Position(cC.getX(), cC.getY()+2);
					positionList.push(cC);
					
					direction = updateDirection(cC);
					unVisited--;
				}
			}
			
			else if((random == 2) && (direction[2] == 'u')){ //South
				if (get(cC.getX()+1,cC.getY()) != '#'){
					set(cC.getX()+1, cC.getY(), 'v');
					cC = new Position(cC.getX()+2, cC.getY());
					positionList.push(cC);
					
					direction = updateDirection(cC);
					unVisited--;
					
				}
			}
			else if((random == 3) && (direction[3] == 'u')){ //North
				if (get(cC.getX()-1,cC.getY()) != '#'){
					set(cC.getX()-1, cC.getY(), 'v');
					cC = new Position(cC.getX()-2, cC.getY());
					positionList.push(cC);
					
					direction = updateDirection(cC);
					unVisited--;
					
				}
			} else {
				if(free == 0 && positionList.size() != 0){
					cC = positionList.get(positionList.size()-1);
					positionList.remove(positionList.size()-1);
					direction = updateDirection(cC);
					
					
				}
			}
		}
		set(cC.getX(),cC.getY(),'8');
		set(1,1,'X');
	}
}