package pl.edu.pw.fizyka.pojava.PL_WL;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Chart extends JFrame implements ActionListener	//Weronika Lis	
{
	
	//---------Wykres
	private XYSeries series1;
	private XYSeriesCollection dataset1;
	JFreeChart lineGraph;
	private ChartPanel chartPanel;
	private JPanel centralPanel;

	public void actionPerformed(ActionEvent e8) 
    {
		Chart chartFrame = new Chart();
		
		chartFrame.setSize(700,500);
		chartFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		chartFrame.setTitle("Efekt Dopplera");
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		chartFrame.setLocation(dim.width/2-chartFrame.getSize().width/2, dim.height/2-chartFrame.getSize().height/2);
		
		centralPanel = new JPanel(); 
		chartFrame.add(centralPanel, BorderLayout.CENTER);
		centralPanel.setBackground(Color.white);
		
		chartPanel = new ChartPanel(lineGraph); 
		centralPanel.add(chartPanel);
		
		series1 = new XYSeries("dane", true, true);
		dataset1 = new XYSeriesCollection();
		dataset1.addSeries(series1);
		//rand = new Random();
		
		lineGraph = ChartFactory.createXYLineChart(
				"Tytu³ wykresu", "D³ugoœæ fali", "Y",
				dataset1,	//dane
				PlotOrientation.VERTICAL,	//orientacja
				true,	//legenda
				true,	//wskazowki
				false);
		series1.clear();	//uzuwanie poprzedniej serii  
		
		/*lineGraph.setTitle("Funkcja sinus");
		for(int i = 0; i < 100; i++)
		{
			double x = (i)/10.0;
			double y = Math.sin(x);
			series1.addOrUpdate(x,y);
		}*/
		
		chartPanel = new ChartPanel(lineGraph);
		centralPanel.add(chartPanel);
		chartFrame.add(chartPanel);
		chartFrame.setVisible(true);
	
		//series1.clear();	//uzuwanie poprzedniej serii  
		
		
		//chartPanel = new ChartPanel(lineGraph);
		//centralPanel.add(chartPanel);
		//frame.add(chartPanel);
	
    }

		
	}
	
