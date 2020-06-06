package pl.edu.pw.fizyka.pojava.PL_WL;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class ShowChart extends JFrame	//Weronika Lis
{
	
	public ShowChart()
	{
		this.setSize(700,500);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Efekt Dopplera");
		
		//------------Ustawienie okna na œrodku---------------
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	}

}
