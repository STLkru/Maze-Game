//Kru Shah & Darrel Jiang

import java.awt.Button;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

/**
 *	A class that handles the difficulty settings for the game and the buttons for
 *	these settings, from easy to hard.
 */
public class Difficulty {

	/**
	 * Constructor for the difficulty class, sets up the JFrame that includes
	 * the difficulty buttons as well as the listeners for mouse clicks on these
	 * buttons.
	 * This class adds three anonymous classes that essentially listened for mouse
	 * clicks on either Easy, Medium, or Hard.
	 */
	public Difficulty(){
		
		final JFrame frame = new JFrame("Menu");	
		BoxLayout boxLayout = new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS); 
		
		Button easy = new Button("Easy");
		easy.setFont(new Font("Verdana", Font.BOLD, 32));
		
		Button medium = new Button("Medium");
		medium.setFont(new Font("Verdana", Font.BOLD, 32));
		
		Button hard = new Button("Hard");
		hard.setFont(new Font("Verdana", Font.BOLD, 32));
		
		frame.add(easy); 
		frame.add(medium);
		frame.add(hard);

		frame.setLayout(boxLayout);
		frame.setSize(500,500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);		
		
		// Action Listener for EASY difficulty.
		easy.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{	
				frame.dispose();
				int easy = 10;
				new MazeFrame(easy);
			}
		});
		
		// Action Listener for MEDIUM difficulty.
		medium.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{	
				frame.dispose();
				int medium = 15;
				new MazeFrame(medium);
				
			}
		});
		
		// Action Listener for HARD difficulty.
		hard.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{	
				frame.dispose();
				int hard = 20;
				new MazeFrame(hard);
			}
		});		
	}
}
