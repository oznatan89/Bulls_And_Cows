package bulls_And_Cows;

import java.awt.Color;
import java.util.Random;
import javax.swing.JButton;

public class RandomColorArray
{
	private JButton [] buttons_Arr = new JButton[4];	
	private Color[] color_Arr = {
			Color.BLUE,
			Color.GREEN,
			Color.GRAY,
			Color.MAGENTA,
			Color.ORANGE,
			Color.WHITE,
			Color.PINK,
			Color.RED,
			Color.BLACK,
			Color.YELLOW
	};
	public RandomColorArray()
	{			
		
	}
	public Color[] get_Color_Arr()
	{
		return color_Arr;
	}
	
	public JButton[] create_Color_Arr(){
		
		int[] int_Arr = create_int_Arr();
		
		for(int i = 0; i <= 3; i++) {
			/*JButton btn_Temp = new JButton();
			btn_Temp.setBackground(color_String_Arr[int_Arr[i++]]);
			buttons_Arr[i] = new JButton (); 
			buttons_Arr[i] = btn_Temp;*/
			buttons_Arr[i] = new JButton();
			buttons_Arr[i].setBackground(color_Arr[int_Arr[i]]);
			
		}
		return buttons_Arr;
	}	
	private int[] create_int_Arr(){
		
		int [] random_int_Arr = {0,0,0,0};		
		Random rand = new Random();
		
		random_int_Arr[0] = rand.nextInt(10);	//1
		
		do{
			random_int_Arr[1] = rand.nextInt(10);   //2
		}while(random_int_Arr[0] == random_int_Arr[1]);
		
		do{
			random_int_Arr[2] = rand.nextInt(10);    //3
		}while(random_int_Arr[2] == random_int_Arr[1] || random_int_Arr[2] == random_int_Arr[0]);
		
		do{
			random_int_Arr[3] = rand.nextInt(10);    //4
		}while(random_int_Arr[3] == random_int_Arr[0] || random_int_Arr[3] == random_int_Arr[1] || random_int_Arr[3] == random_int_Arr[2]);
		
		return random_int_Arr;		
	}	
}
