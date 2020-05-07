package pl.edu.pw.fizyka.pojava.PL_WL;


import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.KeyStroke;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

public class MainFrame extends JFrame implements ActionListener		//Piotr Lebiedziewicz
{
	Image icon;

	JPanel leftPanel, bottomPanel, centerPanel;
	
	//-------MenuItem-------
	JMenuBar menuBar;
	JMenu help, menu;
	JMenuItem medium, water, air, carbon;
	JMenuItem save, background, exit;
	JMenuItem info, language, eng, esp, pol;
	
	//------Panel lewy------
	JLabel vSourceLabel;
	JSlider vSourceSlider;
	
	JLabel vObserverLabel;
	JSlider vObserverSlider;
	
	JLabel frequency;
	JTextField frequencyField;
	JButton count;
	
	JLabel result;
	JTextField resultField;
	
	JLabel empty;
	JLabel lab1, lab2;
	JLabel lab;
	
	static final int MIN = -10;
	static final int MAX = 10;
	static final int INIT = 0;
	
	static int x;
	static int n=1;
	
	//-----Panel dolny-----
	JButton reset, przycisk;
	JLabel chart;
	JComboBox cb;
	JToggleButton stopStart;
	
	JButton button;
	
	
	public MainFrame() throws HeadlessException
	{
		this.setSize(1050,700);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Efekt Dopplera");
		
		
		//-----------Dodawnie ikony--------------------------
		icon = Toolkit.getDefaultToolkit().getImage("icon.png");    
		this.setIconImage(icon); 
		
		
		//------------Ustawienie okna na œrodku---------------
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		
		//------------Menu----------------
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		menu = new JMenu("Menu");
		menuBar.add(menu);
		
		medium = new JMenuItem("Wybierz oœrodek");
		medium = new JMenu("Wybierz osrodek");
		
		water = new JMenuItem("WODA");
		air = new JMenuItem("POWIETRZE");
		carbon = new JMenuItem("DWUTLENEK WÊGLA");
		
		ChangeMedium e5 = new ChangeMedium();
		water.setActionCommand("WODA");
		water.addActionListener(e5);
		air.setActionCommand("POWIETRZE");
		air.addActionListener(e5);
		carbon.setActionCommand("DWUTLENEK WÊGLA");
		carbon.addActionListener(e5);
		
		medium.add(water);
		medium.add(air);
		medium.add(carbon);
		menu.add(medium);
		
		save = new JMenuItem("Zapisz dane");
		SaveFile e6 = new SaveFile();
		save.addActionListener(e6);
		menu.add(save);
		
		background = new JMenuItem("Wybierz kolor t³a");
		background.addActionListener(new ActionListener()
	    {
	    	@Override
	        public void actionPerformed(ActionEvent e) 
	        {
	            final JColorChooser newColor = new JColorChooser();
	            JDialog dialog = JColorChooser.createDialog(MainFrame.this, "Choose background color", true, newColor, new ActionListener()
	            {
	            	@Override
	                public void actionPerformed(ActionEvent e) 
	                {
	                    centerPanel.setBackground(newColor.getColor());
	                }
	             },
	            
	             	new ActionListener() 
	           		{
	               		@Override
	               		public void actionPerformed(ActionEvent e) 
	               		{
	                    
	               		}
	           		});
	            
	         dialog.setVisible(true);
	        }
	    }); 
	    
		menu.add(background);
		
		exit = new JMenuItem("Wyjdz");
		menu.addSeparator();
		menu.add(exit);
	  
  		//------------ Wyjscie z programu----------------
		exit.setAccelerator(KeyStroke.getKeyStroke("ctrl X"));
		exit.addActionListener(new ActionListener() 
		{
			@Override
		  	public void actionPerformed(ActionEvent e)
		  	{
				System.exit(0); 						
		  	}
		 });

		
		//------------ Dialog Box----------------
		help = new JMenu("Pomoc");
		info = new JMenuItem("Info");
		info.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				JOptionPane.showMessageDialog(null,
							  "Symulacja Efektu Dopplera", "Informacje",JOptionPane.QUESTION_MESSAGE);
						
			}
					
		});
		help.add(info);
		menuBar.add(help);
		
		//------------ Zmiana jezyka z menu----------------
		language = new JMenu("Wybierz jêzyk");
		help.add(language);
		
		eng = new JMenuItem("English", new ImageIcon("en_flag.png"));
		language.add(eng);
		ChangeToEnglish e1 = new ChangeToEnglish();
		eng.addActionListener(e1);
		
		esp = new JMenuItem("Espanol", new ImageIcon("sp.flag.png"));
		language.add(esp);
		ChangeToSpanish e2 = new ChangeToSpanish();
		esp.addActionListener(e2);
		
		pol = new JMenuItem("Polski", new ImageIcon("pl_flag.png"));
		language.add(pol);
		ChangeToPolish e3 = new ChangeToPolish();
		pol.addActionListener(e3);
			
		
		//------------ Panel----------------
		leftPanel = new JPanel();
		centerPanel = new JPanel();
		bottomPanel = new JPanel();
		centerPanel.setBackground(Color.WHITE);
		
		this.add(leftPanel, BorderLayout.LINE_START);
		this.add(centerPanel, BorderLayout.CENTER);
		this.add(bottomPanel, BorderLayout.PAGE_END);
		
		//-------------Centralny panel----------
	
		/*ImagePanel obrazek = new ImagePanel();
		//setSize(obrazek.getPreferredSize());
		
		
		JPanel centerPanel = new JPanel();
		JPanel leftPanel = new JPanel();
		JPanel rightPanel = new JPanel();
		
		this.add(leftPanel, BorderLayout.PAGE_START);
		this.add(centerPanel, BorderLayout.CENTER);
		this.add(rightPanel, BorderLayout.PAGE_END);
		
		rightPanel.setBackground(Color.WHITE);
		
		centerPanel.add(obrazek);*/
		
		//------------ Lewy Panel----------------
		leftPanel.setLayout(new GridLayout(11,1));
		leftPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		
		lab1 = new JLabel("V oœrodek [m/s] - POWIETRZE");
		lab2 = new JLabel("343");
		
		vSourceLabel = new JLabel("Prêdkoœæ ród³a [m/s]:   0 ");   
		vSourceSlider = new JSlider(JSlider.HORIZONTAL, MIN, MAX, INIT);
		vObserverLabel = new JLabel("Prêdkoœæ Obserwatora [m/s]:   0 ");
		vObserverSlider = new JSlider(JSlider.HORIZONTAL, MIN, MAX, INIT);
		frequency = new JLabel("Czêstotliwoœæ [Hz]:");
		frequencyField = new JTextField();
		count = new JButton("OBLICZ");
		result = new JLabel("Wyniki obliczeñ:");
		resultField = new JTextField();
		resultField.getScrollableTracksViewportHeight();
		
		ValueOfSlider e = new ValueOfSlider();
		vSourceSlider.addChangeListener(e);
		vObserverSlider.addChangeListener(e);
		
		Calculations e4 = new Calculations();
		count.addActionListener(e4);
		
			
		vSourceSlider.setMajorTickSpacing(2);
		vSourceSlider.setMinorTickSpacing(1);
		vSourceSlider.setPaintTicks(true);
		vSourceSlider.setPaintLabels(true);
		vObserverSlider.setMajorTickSpacing(2);
		vObserverSlider.setMinorTickSpacing(1);
		vObserverSlider.setPaintTicks(true);
		vObserverSlider.setPaintLabels(true);
		
		//lewy.add(predkosci);
		leftPanel.add(vSourceLabel);
		leftPanel.add(vSourceSlider);
		leftPanel.add(vObserverLabel);
		leftPanel.add(vObserverSlider);
		leftPanel.add(lab1);
		leftPanel.add(lab2);
		leftPanel.add(frequency);
		leftPanel.add(frequencyField);
		leftPanel.add(count);
		leftPanel.add(result);
		leftPanel.add(resultField);
		
		
		//------------ Dolny Panel----------------
		//dolny.setLayout(new BoxLayout(dolny, BoxLayout.X_AXIS));
		reset = new JButton("Zeruj dane");
	    ClearData e7 = new ClearData();
	    reset.addActionListener(e7);

		chart = new JLabel("Wykres");
		//odglos = new JLabel("Odg³os Ÿród³a");
		
		String sounds[]={"Brak dzwiêku", "Odg³os zrodl aa", "Odg³os zrodl bb",};        
	    cb = new JComboBox(sounds);    
	    
	    stopStart = new JToggleButton("START");
	    stopStart.addActionListener(this);
	    
	    stopStart.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				 if (stopStart.isSelected())  
			            stopStart.setText("STOP ");  
			        else  
			        	
			        stopStart.setText("START");
			}			
		});
		 
		bottomPanel.add(reset);
		bottomPanel.add(chart);
		bottomPanel.add(cb);   
		bottomPanel.add(stopStart);
		
		//------------------------------
		
	}  
	public class ChangeMedium implements ActionListener	//Weronika Lis	
	{
		public void actionPerformed(ActionEvent e5) 
        {	
			if(e5.getActionCommand().equals("WODA"))
			{
				lab1.setText("V oœrodka [m/s] - WODA");
				lab2.setText("1490");
			}
			else if(e5.getActionCommand().equals("POWIETRZE"))
			{
				lab1.setText("V oœrodka [m/s] - POWIETRZE");
				lab2.setText("v343");
			}
			else if(e5.getActionCommand().equals("DWUTLENEK WÊGLA"))
			{
				lab1.setText("V oœrodka [m/s]  - DWUTLENEK WÊGLA");
				lab2.setText("259");
			}
			
        }
	}
	
	public class SaveFile implements ActionListener		//Weronika Lis	
	{
		public void actionPerformed(ActionEvent e6)
		{
			File dirPath = new File(System.getProperty("user.dir"));
            JFileChooser jchooser = new JFileChooser(dirPath);
            int returnVal = jchooser.showSaveDialog(null); 
            if (returnVal==JFileChooser.APPROVE_OPTION) 
            {
                try 
                {
                    File outputFile = jchooser.getSelectedFile();
                    OutputStreamWriter osw = new OutputStreamWriter(
                            new FileOutputStream(outputFile),
                            Charset.forName("UTF-8").newEncoder()

                    );
                    osw.write(resultField.getText());
                    osw.close();
                } 
                catch (FileNotFoundException e1)
                {
                    e1.printStackTrace();
                }
                catch (IOException e1) 
                {
                    e1.printStackTrace();
                }
             }				
		}
	}
	
	public class ChangeToEnglish implements ActionListener	//Weronika Lis	
	{
		public void actionPerformed(ActionEvent e1)
		{
			help.setText("Help");
			medium.setText("Choose medium");
			water.setText("Water"); 
			air.setText("Air");
			save.setText("Save data"); 
			background.setText("Set background kolor");
			exit.setText("Exit");
			info.setText("Info");
			language.setText("Choose language");
			vSourceLabel.setText("Velocity of source");
			vObserverLabel.setText("Velocity of observer");
			frequency.setText("Frequency");
			count.setText("Count");
			result.setText("Result");
			reset.setText("Reset");
			chart.setText("Chart");
			
		}
	}
	public class ChangeToSpanish implements ActionListener	//Weronika Lis
	{
		public void actionPerformed(ActionEvent e2)
		{
			help.setText("Ayuda");
			medium.setText("Seleccione un resort");
			water.setText("Aqua"); 
			air.setText("Aire");
			save.setText("Guadar los datos"); 
			background.setText("Elgir color de fondo");
			exit.setText("Salia");
			info.setText("Informacion");
			language.setText("Elgir lengua");
			vSourceLabel.setText("Velocidad de fuente");
			vObserverLabel.setText("Velocidad del observador");
			frequency.setText("Frecuencia");
			count.setText("Calcular");
			result.setText("Resultar");
			reset.setText("Reiniciar");
			chart.setText("Grafico");
		}
	}
	public class ChangeToPolish implements ActionListener	//Weronika Lis
	{
		public void actionPerformed(ActionEvent e3)
		{
			help.setText("Pomoc");
			medium.setText("Wybierz orodek");
			water.setText("Woda"); 
			air.setText("Powietrze");
			save.setText("¿apisz dane"); 
			background.setText("Wybierz kolor t³a");
			exit.setText("Wyjœcie");
			info.setText("Info");
			language.setText("Wybierz jêzyk");
			vSourceLabel.setText("Prêdkoœæ zród³¹");
			vObserverLabel.setText("Prêdkoœæ obserwatora");
			frequency.setText("Czêstotliwoœæ");
			count.setText("Oblicz");
			result.setText("Wynik");
			reset.setText("Zeruj");
			chart.setText("Wykres");
		}
	}
	
	public class ValueOfSlider implements ChangeListener	//Weronika Lis
	{
		public void stateChanged(ChangeEvent e)
		{
			int value = vSourceSlider.getValue();
			vSourceLabel.setText("Prêdkoœæ ród³a [m/s]:   " + value);
			
			int value2 = vObserverSlider.getValue();
			vObserverLabel.setText("Prêdkoœæ Obserwatowa [m/s]:   " + value2);
			
		}
	}
	
	public class Calculations implements ActionListener	//Weronika Lis
	{
		public void actionPerformed(ActionEvent e) //wypisywanie liczb
        {	
            double number, vMe;
            String freq = frequencyField.getText();
            String medium = lab2.getText();
            int vSo = vSourceSlider.getValue();
            int vOb = vObserverSlider.getValue();
           
            
            try
            {
                number = Double.parseDouble(freq);
                vMe = Double.parseDouble(medium);
                resultField.setText(resultField.getText()+ "fs = " + number + "Hz");	
           
                resultField.setText(resultField.getText()+ "; Vs = " + vSo + "m/s");	
                
                resultField.setText(resultField.getText()+ "; Vob = " + vOb + "m/s");
                
                if(vOb == vSo )	
                {
                	double fk = number;
                	resultField.setText(resultField.getText()+ "; f ="+ fk + "Hz");
                	resultField.setText(resultField.getText()+ "; T ="+ (1/fk) + "s");
                }
                else if(vOb == 0 & vSo>0)
                {
                  	double v = vMe+vSo;
                  	double fk = number*(vMe/v);
                	resultField.setText(resultField.getText()+ "; f = "+ fk + "Hz");
                	resultField.setText(resultField.getText()+ "; T ="+ (1/fk) + "s");
                }
                 else if(vOb == 0 & vSo<0)
                {
                	 double v = vMe-vSo;
                	 double fk = number*(vMe/v);
                	resultField.setText(resultField.getText()+ "; f ="+ fk + "Hz");
                	resultField.setText(resultField.getText()+ "; T ="+ (1/fk) + "s");
                }
                 else if(vOb<0 & vSo==0)
                {
                 	double v = vMe-vOb;
                 	double fk = number*(v/vMe);
                	resultField.setText(resultField.getText()+ "; f ="+ fk + "Hz");
                	resultField.setText(resultField.getText()+ "; T ="+ (1/fk) + "s");
                }
                else if(vOb>0 & vSo==0)
                {
                	double v = vMe+vOb;
                	double fk = number*(v/vMe);
                	resultField.setText(resultField.getText()+ "; f ="+ fk + "Hz");
                	resultField.setText(resultField.getText()+ "; T ="+ (1/fk) + "s");
                }
                else if(vOb>0 & vSo<0)//Obs i zr poruszaja sie w prawo; f'>f
                {
                	double v = vMe+vOb;
                	double v1 = vMe-vSo;
                	double fk = number*(v/v1);
                	resultField.setText(resultField.getText()+ "; f ="+ fk + "Hz");
                	resultField.setText(resultField.getText()+ "; T ="+ (1/fk) + "s");
                }
                else if(vOb<0 & vSo>0)
                {
                	double v = vMe-vOb;
                	double v1 = vMe+vSo;
                	double fk = number*(v/v1);
                	resultField.setText(resultField.getText()+ "; f ="+ fk + "Hz");
                	resultField.setText(resultField.getText()+ "; T ="+ (1/fk) + "s");
                }
                else if(vOb>0 & vSo>0 & vOb>vSo)
                {
                	double v = vMe+vOb;
                	double v1 = vMe-vSo;
                	double fk = number*(v/v1);
                	resultField.setText(resultField.getText()+ "; f ="+ fk + "Hz");
                	resultField.setText(resultField.getText()+ "; T ="+ (1/fk) + "s");
                }
                else if(vOb>0 & vSo>0 & vOb<vSo)
                {
                	double v = vMe-vOb;
                	double v1 = vMe+vSo;
                	double fk = number*(v/v1);
                	resultField.setText(resultField.getText()+ "; f ="+ fk + "Hz");
                	resultField.setText(resultField.getText()+ "; T ="+ (1/fk) + "s");
                }
                else if(vOb<0 & vSo<0 & vOb<vSo)
                {
                	double v = vMe+vOb;
                	double v1 = vMe-vSo;
                	double fk = number*(v/v1);
                	resultField.setText(resultField.getText()+ "; f ="+ fk + "Hz");
                	resultField.setText(resultField.getText()+ "; T ="+ (1/fk) + "s");
                }
                else if(vOb<0 & vSo<0 & vOb>vSo)
                {
                	double v = vMe-vOb;
                	double v1 = vMe+vSo;
                	double fk = number*(v/v1);
                	resultField.setText(resultField.getText()+ "; f ="+ fk + "Hz");
                	resultField.setText(resultField.getText()+ "; T ="+ (1/fk) + "s");
                }
            }
            
            catch(NumberFormatException exception)	//wypisuje, gdy podamy np litery
            {
            	 JOptionPane.showMessageDialog(null, "Czêstotlowoœæ musi byæ liczb¹. "
                 		+ " Liczbe u³amkow¹ wprowadzamy za pomoc¹ KROPKI.",
                         "B³êdnie wprowadzone dane!",
                         JOptionPane.WARNING_MESSAGE);
            }

        }

	}
	
	public class ClearData implements ActionListener	//Weronika Lis	
	{
		public void actionPerformed(ActionEvent e7) 
        {	
			frequencyField.setText("");
			resultField.setText("");
			vSourceSlider.setValue(0);
			vObserverSlider.setValue(0);
			lab1.setText("V oœrodka [m/s] - POWIETRZE");
			lab2.setText("343");
        }
	}	
	public MainFrame(GraphicsConfiguration arg0)
	{
		super(arg0);
	}

	public MainFrame(String arg0) throws HeadlessException
	{
		super(arg0);
	}

	public MainFrame(String arg0, GraphicsConfiguration arg1) {
		super(arg0, arg1);
	}

	public static void main(String[] args)
	{
		MainFrame frame = new MainFrame();
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

}
