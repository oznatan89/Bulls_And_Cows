//project - "Bulls_And_Cows"

package bulls_And_Cows;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

//import java.io.File;
//import javax.imageio.ImageIO;

@SuppressWarnings("serial")
public class BullsAndCows extends JFrame
{
	private Container content_Pane;
	protected int count_Chance = 11, num_Of_Guess_Line = 1;

	private String [] colors_Table = {"BLUE","GREEN","GRAY","MAGENTA","ORANGE","WHITE","PINK","RED","BLACK","YELLOW"};
	private RandomColorArray RandomColorArray_Pointer = new RandomColorArray();

	private JButton button_Random_Arr[] = new JButton[4];
	private JButton button_Arr_Guess[] = new JButton[4];
	private JButton button_Arr_Score[] = new JButton[4];
	private JButton ten_Colors_Table [];
	private JButton button_Exit, ok_Button, button_Temp = new JButton(" ");

	private JLabel label_First;
	private JPanel jPnl_1, jpnl_3, jpnl_2, jpnl_4, little, big, line;

	//private JFrame frame = new JFrame();

	private Font font;
	//private StyleSheet styleSheet_temp = new StyleSheet ();
	private JMenu help_Menu, file_Menu;
	private JMenuBar menu_Bar;

	public BullsAndCows()
	{

		super( "BullsAndCows by oznatan89" );
		content_Pane = getContentPane();

		button_Random_Arr = RandomColorArray_Pointer.create_Color_Arr();

		//set layout
		content_Pane.setLayout(new FlowLayout());
		
		//set menu Bar
		menu_Bar = new JMenuBar();
		create_File_Menu();
		create_Help_Menu();
		setJMenuBar(menu_Bar);

		//set Title Game
		label_First = new JLabel("Bulls And Cows");
		//font = new Font("Arial", Font.PLAIN + Font.BOLD, 50);
		label_First.setFont(new Font("Arial", Font.BOLD , 50));	
		add(label_First /*,BorderLayout.CENTER*/);

		jPnl_1 = new JPanel();
		jPnl_1.setPreferredSize(new Dimension(380, 440));
		//jPnl_1.setBackground(Color.yellow);
		jPnl_1.setLayout(new FlowLayout(2,2,2));

		//Create CenterPanelButton
		line = new JPanel();		
		//line.setBackground(Color.red);
		line.setLayout(new FlowLayout(0,0,1));
		//line.setLayout(new FlowLayout());

		JLabel s = new JLabel (""+ (num_Of_Guess_Line++) +""); 
		s.setFont(new Font("Arial", Font.BOLD ,20));		
		line.add(s);

		{
			big = new JPanel();
			big.setBackground(Color.green);

			//for (JButton button: button_Arr_Guess) {
			for (int i=0; i<=3; i++)
			{
				JButton button = new JButton(" ");
				button.setPreferredSize(new Dimension(65, 30));
				button.addFocusListener(new CustomFocusListener()); 
				//button.setBackground(Color.WHITE);
				button.setToolTipText("Click on me and then choose color");
				button_Arr_Guess[i] = button; 
			}

			big = paint_JButton_Arr_Into_JPanel(button_Arr_Guess);
		}


		line.add(big);

		{
			//create helpButton
			little = new JPanel();
			//little.setLayout(new FlowLayout(2,2,2));
			//little.setBackground(Color.yellow);


			//for (JButton button : button_Arr_Score) {
			for (int i=0; i<=3; i++)
			{
				JButton button = new JButton();
				button.setPreferredSize(new Dimension(12,30));
				button.setToolTipText("I'll give you a score");
				button_Arr_Score[i] = button;				
			}

			little = paint_JButton_Arr_Into_JPanel(button_Arr_Score);
		}
		line.add(little);			

		jPnl_1.add(line);

		add(jPnl_1);



		ButtonHandler handler = new ButtonHandler();
		ok_Button = new JButton("You have 10 attempts");
		ok_Button.setFont(new Font("Arial", Font.BOLD ,15));
		ok_Button.setPreferredSize(new Dimension(200,35));
		ok_Button.addActionListener(handler);

		ok_Button.addActionListener(new ActionListener()
		{		
			@SuppressWarnings("deprecation")
			public void actionPerformed( ActionEvent event )
			{
				//If there are two buttons in the same color
				if(
						button_Arr_Guess[0].getBackground() == button_Arr_Guess[1].getBackground() ||
						button_Arr_Guess[0].getBackground() == button_Arr_Guess[2].getBackground() ||
						button_Arr_Guess[0].getBackground() == button_Arr_Guess[3].getBackground() ||
						button_Arr_Guess[1].getBackground() == button_Arr_Guess[2].getBackground() ||
						button_Arr_Guess[1].getBackground() == button_Arr_Guess[3].getBackground() ||
						button_Arr_Guess[2].getBackground() == button_Arr_Guess[3].getBackground()
						)
					return;

				int count_Red = 0;
				int count_Yellow = 0;

				for (int i=0 ; i< button_Arr_Guess.length; i++){ 
					for(int j=0 ; j< button_Random_Arr.length; j++){
						if (button_Arr_Guess[i].getBackground() == button_Random_Arr[j].getBackground()){
							if (i==j){	
								//win++;	
								count_Red++;
								break;
							}else{
								count_Yellow++;
								break;
							}}}}

				//count_Red = 2;
				//count_Yellow = 2;

				int yellow_And_Red = count_Red + count_Yellow;

				for (int i=0; i<=2; i++)
				{
					if (yellow_And_Red-- > 0)
					{	
						if (count_Red-- >0)															
							button_Arr_Score[i].setBackground(Color.RED);
						else
							button_Arr_Score[i].setBackground(Color.YELLOW);
					}
					else return;
				}

				if (yellow_And_Red-- > 0)
				{	
					if (count_Red-- >0)															
					{
						button_Arr_Score[3].setBackground(Color.RED);

						int temp =0; 
						// smartCheck, is a count_Red was 'positive'?

						ok_Button.hide();
						button_Exit.hide();

						temp = JOptionPane.showConfirmDialog( null, "Congratulations ! \nYou solved in " + (12 - count_Chance) + " attempts \nDo you want to start a new game?", "System Message", 1);
						if(temp == JOptionPane.YES_OPTION){ 
							dispose();
							BullsAndCowsTest.main(null);
						}
						else {
							System.exit(0);
						}}
					else
						button_Arr_Score[3].setBackground(Color.YELLOW);
				}}
		});


		jpnl_2 = new JPanel();
		jpnl_2.add(ok_Button);

		add(jpnl_2);

		jpnl_3 = new JPanel();		
		jpnl_3.setLayout(new GridLayout(2,2));

		ten_Colors_Table  = new JButton[RandomColorArray_Pointer.get_Color_Arr().length]; // create buttons array

		for ( int count = 0; count < ten_Colors_Table.length; count++ ) 
		{

			Color color_Temp = RandomColorArray_Pointer.get_Color_Arr()[count];

			ten_Colors_Table [count] = new JButton(colors_Table[count]);
			ten_Colors_Table [count].setBackground(color_Temp);
			ten_Colors_Table [count].addActionListener(handler);					
			ten_Colors_Table [count].setPreferredSize(new Dimension(84, 40));			
			jpnl_3.add( ten_Colors_Table [ count ] ); 
		}

		add(jpnl_3);

		//create panel+Buttom 'exit'

		button_Exit = new JButton ("Exit");
		button_Exit.setPreferredSize(new Dimension(100, 30));

		jpnl_4 = new JPanel();
		jpnl_4.add(button_Exit);

		//jpnl_4.setBackground(Color.LIGHT_GRAY);
		add(jpnl_4);

		//add addActionListener for 'exitButton'
		button_Exit.addActionListener(
				new ActionListener() // anonymous inner class
				{  
					// process leftJButton event  
					public void actionPerformed( ActionEvent event )
					{
						System.exit(0);
					} // end method actionPerformed
				} // end anonymous inner class 
				); // end call to addActionListener

	}
	private JPanel paint_JButton_Arr_Into_JPanel(JButton[] button_Arr_Guess_temp) {
		JPanel jPanel_Temp = new JPanel();
		for( JButton temp : button_Arr_Guess_temp)
			jPanel_Temp.add(temp);
		return jPanel_Temp;
	}

	//This function reduces the user's chance to counter,
	//and returns "true" if it did not pass the first 10
	public boolean count_Up()
	{
		return ( (--this.count_Chance <= 0) ? false : true); 
	}
	//This function creates the first tab 'File'
	private void create_File_Menu()
	{
		ButtonHandler handler = new ButtonHandler();
		file_Menu = new JMenu("File");

		JMenuItem item = new JMenuItem("New Game");
		item.addActionListener(handler);		
		file_Menu.add(item);

		//file_Menu.addSeparator();

		item = new JMenuItem("Exit");
		item.addActionListener(handler);
		file_Menu.add(item);

		menu_Bar.add(file_Menu);
	}

	//This function creates the second tab 'Help'
	private void create_Help_Menu()
	{
		ButtonHandler handler = new ButtonHandler();
		help_Menu = new JMenu("Help");

		JMenuItem item = new JMenuItem("About");
		item.addActionListener(handler);
		help_Menu.add(item);

		menu_Bar.add(help_Menu);
	}

	//return count_Chance
	public int getCount()
	{
		return this.count_Chance;
	}





	private class ButtonHandler implements ActionListener 
	{
		@SuppressWarnings("deprecation")

		public void actionPerformed( ActionEvent event )
		{
			//This line checks whether it is the act Menu
			if(event.getSource() instanceof JMenuItem)
			{
				String command = event.getActionCommand();

				if (command == "New Game")
				{								
					dispose();
					BullsAndCowsTest.main(null);
				}
				else if(command == "Exit")
					System.exit(0);
				else if(command == "About")
					JOptionPane.showMessageDialog(null, "You are playing \'Cows and bull\' game developed by \'Oznatan89\'.");
				return;
			}

			//Loop paints the set of four buttons kept current by the 'focus button'.
			for ( JButton button : ten_Colors_Table  )
			{
				if ( event.getSource() == button )
				{
					if (button_Temp == button_Arr_Guess[0])
						button_Arr_Guess[0].setBackground(button.getBackground());
					else if (button_Temp == button_Arr_Guess[1])
						button_Arr_Guess[1].setBackground(button.getBackground());
					else if (button_Temp == button_Arr_Guess[2])
						button_Arr_Guess[2].setBackground(button.getBackground());
					else if (button_Temp == button_Arr_Guess[3])
						button_Arr_Guess[3].setBackground(button.getBackground());
					return;
				}			
			}

			if (
					button_Arr_Guess[0].getBackground() == button_Arr_Guess[1].getBackground() ||
					button_Arr_Guess[0].getBackground() == button_Arr_Guess[2].getBackground() ||
					button_Arr_Guess[0].getBackground() == button_Arr_Guess[3].getBackground() ||

					button_Arr_Guess[1].getBackground() == button_Arr_Guess[2].getBackground() ||
					button_Arr_Guess[1].getBackground() == button_Arr_Guess[3].getBackground() ||

					button_Arr_Guess[2].getBackground() == button_Arr_Guess[3].getBackground()
					)
			{
				JOptionPane.showMessageDialog( null, "You can not select the same color twice.\nTry again", "Warning message", JOptionPane.INFORMATION_MESSAGE );
				return;
			}

			// Change the text button 'OK'
			if ( count_Up() )
				ok_Button.setText("Try again, "+ (count_Chance-1) +" attempt left");

			if ( getCount() == 2 )				
				ok_Button.setText("Last chance");

			else if ( getCount() == 1 ) // Game Over
			{
				JPanel jpnl_Game_Over = new JPanel();	

				JPanel jpanel_Result = new JPanel();

				JButton[] button_Result = new JButton[10];
				for(int i=0 ; i<4 ; i++)
				{
					button_Result[i] =new JButton();
					button_Result[i].setBackground(button_Random_Arr[i].getBackground());		
					button_Result[i].setPreferredSize(new Dimension(65, 30));			
					jpanel_Result.add( button_Result[i] ); 
				}

				Label Result_Label =  new Label("Result :");
				Result_Label.setPreferredSize(new Dimension(80, 30));
				Result_Label.setFont(new Font("Arial", Font.PLAIN + Font.BOLD, 30));

				jpnl_Game_Over.add( Result_Label );
				jpnl_Game_Over.add( jpanel_Result );
				jpnl_Game_Over.setLayout(new GridLayout(2,2));
				add(jpnl_Game_Over);


				ok_Button.hide();				
				jpnl_3.hide();
				jpnl_4.hide();

				int temp = JOptionPane.showConfirmDialog( null, "Start a new game?", "System Message", 1);
				if(temp == JOptionPane.YES_OPTION){ 
					dispose();
					BullsAndCowsTest.main(null);
				}
				else if (temp == JOptionPane.NO_OPTION){
					System.exit(0);
				}
			}

			//add another line
			if (getCount() > 1)
			{
				//Create CenterPanelButton
				line = new JPanel();		
				//line.setBackground(Color.red);
				line.setLayout(new FlowLayout(2,0,1));

				JLabel s = new JLabel (""+ (num_Of_Guess_Line++) +""); 
				s.setFont(new Font("Arial", Font.BOLD ,20));		
				line.add(s);

				{
					big = new JPanel();
					big.setLayout(new FlowLayout(1,1,1));

					//for (JButton button: button_Arr_Guess) {
					for (int i=0; i<=3; i++)
					{
						JButton button = new JButton(" ");
						button.setPreferredSize(new Dimension(65, 30));
						button.addFocusListener(new CustomFocusListener());
						//button.setBackground(Color.WHITE);
						//button.setBackground(RandomColorArray_Pointer.get_Color_Arr()[new Random().nextInt(10)]);
						button.setToolTipText("Click on me and then choose color");
						button_Arr_Guess[i] = button;
					}
					big = paint_JButton_Arr_Into_JPanel(button_Arr_Guess);
					line.add(big);
				}

				{
					//create helpButton
					little = new JPanel();
					little.setLayout(new FlowLayout(1,1,1));
					little.setBackground(Color.yellow);

					//for (JButton button : button_Arr_Score) {
					for (int i=0; i<=3; i++)
					{
						JButton button = new JButton();
						button.setPreferredSize(new Dimension(12,30));
						button.setToolTipText("I'll give you a score");
						button_Arr_Score[i] = button; 
					}
					little = paint_JButton_Arr_Into_JPanel(button_Arr_Score);
					line.add(little);
				}

				jPnl_1.add(line);										
			}			
		}
	}





	//What happens when a user changed the focus buttons
	class CustomFocusListener implements FocusListener{
		@Override
		public void focusGained(FocusEvent e) {
		}
		@Override
		public void focusLost(FocusEvent e)
		{
			if(e.getSource() == button_Arr_Guess[0])
				button_Temp = button_Arr_Guess[0];
			else if(e.getSource() == button_Arr_Guess[1] )
				button_Temp = button_Arr_Guess[1];
			else if(e.getSource() == button_Arr_Guess[2] )
				button_Temp = button_Arr_Guess[2];
			else if(e.getSource() == button_Arr_Guess[3])
				button_Temp = button_Arr_Guess[3];
			else
				button_Temp = null;
		}
	}
}