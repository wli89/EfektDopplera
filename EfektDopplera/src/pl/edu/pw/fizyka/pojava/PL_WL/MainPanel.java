package pl.edu.pw.fizyka.pojava.PL_WL;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JPanel;
import javax.swing.Timer;

public class MainPanel extends JPanel implements ActionListener 	//Piotr Lebiedziewicz
{

	Timer tm;
	int time = 0;
	int xRedObject = 200, velX = 0;
	int xBlueObject = 600, velXBlue = 0;
	int velR = 6;
	boolean running;
	
	private int[] circleX = new int [1000];
	private int[] circleY = new int [1000];
	private int[] circleR = new int [1000];
	int counter = 0;
	
	int frequency = 20;
	boolean changeFrequency = true;
	
	void uruchomAnimacje()
	{
		
		tm = new Timer(10, this);
	  	tm.addActionListener(new ActionListener() 
	  	{
        	@Override
        	public void actionPerformed(ActionEvent e)
        	{
        		if (!changeFrequency) {
        			
        		} else {
        			for (int f = 1; f <1000; f++) {
        				circleX[f] = circleX[f - 1] + frequency*velX;
        			}
        			changeFrequency = false;
        		}
        		
        		xRedObject = xRedObject + velX;     		
        		
        		for (int i = 0; i < counter + 1; i++) 
        		{    	
            		circleX[i] = circleX[i] - velR/2;
            		circleY[i] = circleY[i] - velR/2;
            		circleR[i] = circleR[i] + velR;
        		}
        		
        		xBlueObject = xBlueObject + velXBlue;     		
        		
        		time = time + 1;
        		if (time % frequency == 0)
        		{
        			counter++;
        		}
        		
    			System.out.println(circleX[0]);

        		repaint();
        		
        		if (xRedObject > 778 || xRedObject < 6 || xBlueObject > 778 || xBlueObject < 7)
        		{
        			tm.stop();
        			running = false;
        		}
        	}
    	});	
	  	tm.start();
	  	running = true;
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(Color.BLUE);
		g.fillRect(xBlueObject - 5, 300 - 5, 10, 10);
		
		g.setColor(Color.RED);
		g.fillRect(xRedObject - 5, 300 - 5, 10, 10);
		for (int i = 0; i < counter +1; i++) {
			g.drawOval(circleX[i], circleY[i], circleR[i], circleR[i]);
		}
		

	}
	
	public void reset() {
		Arrays.fill(circleX, 200);
		Arrays.fill(circleY, 300);
		Arrays.fill(circleR, 0);
		tm = null;
		time = 0;
		xRedObject = 200;
		velX = 0;
		xBlueObject = 600;
		velXBlue = 0;
		velR = 6;
		running = false;
		counter = 0;
		frequency = 20;
		changeFrequency = true;
		repaint();
	}
	
	public MainPanel()
	{
		Arrays.fill(circleX, 200);
		Arrays.fill(circleY, 300);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0){
		// TODO Auto-generated method stub
		
	}


}
