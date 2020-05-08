package pl.edu.pw.fizyka.pojava.PL_WL;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MainPanel extends JPanel implements ActionListener 	//Piotr Lebiedziewicz
{

	Timer tm;
	int time = 0;
	int x = 200, velX = 1;
	int velR = 4;
	JButton start;
	boolean running;
	
	private int[] circleX = new int [10];
	private int[] circleY = new int [10];
	private int[] circleR = new int [10];
	int licznik = 0;
	
	

	void uruchomAnimacje()
	{
		tm = new Timer(10, this);
	  	tm.addActionListener(new ActionListener() 
	  	{
        	@Override
        	public void actionPerformed(ActionEvent e)
        	{
        		x = x+velX;     		
        		
        		for (int i = 0; i < 1; i++) 
        		{    	
            		circleX[i] = circleX[i] - velR/2;
            		circleY[i] = circleY[i] - velR/2;
            		circleR[i] = circleR[i] + velR;
        		}
        		
        		
        		time = time + 1;
        		if (time % 100 == 0)
        		{
        			licznik++;
        		}
        		
    			System.out.println(circleX[0]);

        		repaint();
        		
        		if (x > 803)
        		{
        			tm.stop();
        		}
        	}
    	});	
	  	tm.start();
	  	running = true;
	}
	
    ActionListener listener1 = new ActionListener()
    {
		
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			
			if(!running){
				uruchomAnimacje();				
			}
		}
	};
	
	ActionListener listener2 = new ActionListener()
	{
		
		@Override
		public void actionPerformed(ActionEvent e)
		{
			
			if(running){
				tm.stop();
				running = false;
			}
		}
	};
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		
		g.setColor(Color.RED);
		g.fillRect(x, 300, 10, 10);
		//g.drawOval(circleXold, circleYold, r, r);
		for (int i = 0; i < 1; i++) {
			g.drawOval(circleX[i], circleY[i], circleR[i], circleR[i]);
		}
		
		g.setColor(Color.BLUE);
		g.fillRect(600, 300, 10, 10);
	}
	
	public MainPanel()
	{
		JButton b1 = new JButton("Animacja start");
		b1.addActionListener(listener1);
		add(b1);
		JButton b2 = new JButton("Animacja pauza");
		b2.addActionListener(listener2);
		add(b2);
		
		Arrays.fill(circleX, 200);
		Arrays.fill(circleY, 300);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0){
		// TODO Auto-generated method stub
		
	}

}
