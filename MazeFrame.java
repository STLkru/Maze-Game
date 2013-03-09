//Kru Shah & Darrel Jiang

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;


/**
 *	A class that encompasses the elements of the game board including
 *	the maze GUI and the buttons for movement.
 */
public class MazeFrame {
	
	static JFrame frame = new JFrame("Maze");
	JPanel p = new JPanel(new BorderLayout());
	JPanel p2 = new JPanel(new BorderLayout());
	
	/**
	 * Constructs the maze frame with a board and a player along with
	 * all the button listeners for movement.
	 * @param level
	 */
	public MazeFrame(int level){		
		
		final Board test = new Board(level,level,level);
		final Player player = new Player(test);
		
		p.add(test, BorderLayout.CENTER);
		p.setFocusable(true);
		frame.add(p, BorderLayout.CENTER);				
		frame.add(p2, BorderLayout.SOUTH);	
		
		
		JButton south = new JButton("South");
		south.setFocusable(false);
		p2.add(south, BorderLayout.SOUTH);			
		south.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{	
				player.moveDown(test);
			}
		});
		
		JButton north = new JButton("North");
		north.setFocusable(false);
		p2.add(north, BorderLayout.NORTH);
		north.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{	
				player.moveUp(test);
			}
		});
		
		JButton east = new JButton("East");
		east.setFocusable(false);
		p2.add(east, BorderLayout.EAST);
		east.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{	
				player.moveRight(test);
			}
		});		
		
		
		JButton west = new JButton("West");
		west.setFocusable(false);
		p2.add(west, BorderLayout.WEST);
		west.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{	
				player.moveLeft(test);
			}
		});	
		
		JButton menu = new JButton("New Game");
		menu.setFocusable(false);
		p2.add(menu, BorderLayout.CENTER);
		menu.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{	
				frame.dispose();
				new MainMenu();
			}
		});	
				
		p.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e){				
				int keyCode = e.getKeyCode();
				if(e.getKeyChar() == 'a' || keyCode == KeyEvent.VK_LEFT) player.moveLeft(test);
				if(e.getKeyChar() == 'd' || keyCode == KeyEvent.VK_RIGHT) player.moveRight(test);
				if(e.getKeyChar() == 'w' || keyCode == KeyEvent.VK_UP) player.moveUp(test);
				if(e.getKeyChar() == 's' || keyCode == KeyEvent.VK_DOWN) player.moveDown(test);
			}
		});	
		
		frame.setSize(500, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setBackground(Color.green);
		frame.setVisible(true);
	}

	/**
	 * Constructor for maze frame that displays the win frame.
	 * @param level The size of the Frame
	 * @param money The amount of coins collected by the player.
	 */
	public MazeFrame(int level, int money){
        
        frame.dispose();        
        JFrame frame2 = new JFrame();
        
        JLabel textLabel = new JLabel("<html>Congratulations!<br>You got " + money + " coin(s)!</html>", JLabel.CENTER);
        textLabel.setFont(new Font("Verdana", Font.BOLD, 32));
        
        frame2.setBackground(Color.green);
        frame2.add(textLabel, BorderLayout.CENTER);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setSize(500,500);
        frame2.setLocationRelativeTo(null);
        frame2.setVisible(true);
    }
}
