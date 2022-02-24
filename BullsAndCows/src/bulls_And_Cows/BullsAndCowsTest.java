package bulls_And_Cows;

import javax.swing.JFrame;

public class BullsAndCowsTest {
	private static BullsAndCows bullsAndCows;
	public static void main(String[] args)
	{
		bullsAndCows = new BullsAndCows();
		bullsAndCows.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		bullsAndCows.setResizable(false); // can't reSize
		bullsAndCows.setSize(480,770); // set frame size
		bullsAndCows.setVisible( true ); // display frame
	}	
}