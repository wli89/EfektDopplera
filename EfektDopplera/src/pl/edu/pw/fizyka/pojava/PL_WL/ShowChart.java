package pl.edu.pw.fizyka.pojava.PL_WL;

import javax.swing.JFrame;

public class ShowChart extends JFrame	//Weronika Lis
{
	
	public ShowChart()
	{
		this.setSize(700,500);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Efekt Dopplera");
		
	}
	
	public static void main(String[] args) {

		ShowChart frame = new ShowChart();
		frame.setVisible(true);
	}

}
