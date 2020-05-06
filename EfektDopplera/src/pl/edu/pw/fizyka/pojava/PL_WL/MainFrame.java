package pl.edu.pw.fizyka.pojava.PL_WL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
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

public class MainFrame extends JFrame implements ActionListener
{
	Image icon;
	
	JPanel leftPanel, bottomPanel, centerPanel;
	
	//-------MenuItem-------
	JMenuBar menuBar;
	JMenu help, menu;
	JMenuItem medium, water, air, save, background, exit, info, language, eng, esp, pol;
	
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
	
	JLabel pusty1;
	JLabel pusty2;
	
	static final int MIN = -10;
	static final int MAX = 10;
	static final int INIT = 0;
	
	//-----Panel dolny-----
	JButton reset, przycisk;
	JLabel chart, sound;
	JComboBox cb;
	JToggleButton stopStart;
	
	ImageIcon obrazek2;
	JButton button;
	
	
	public MainFrame() throws HeadlessException
	{
		this.setSize(1000,600);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Efekt Dopplera");
		
		
		//-----------Dodawnie ikony--------------------------
		icon = Toolkit.getDefaultToolkit().getImage("icon.png");    
		this.setIconImage(icon); 
		
		
		//------------Ustawienie okna na úrodku---------------
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		
		//------------Menu----------------
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		menu = new JMenu("Menu");
		menuBar.add(menu);
		
		medium = new JMenuItem("Wybierz oúrodek");
		
		medium = new JMenu("Wybierz osrodek");
		water = new JMenuItem("WODA");
		air = new JMenuItem("POWIETRZE");
		medium.add(water);
		medium.add(air);
		menu.add(medium);
		
		save = new JMenuItem("Zapisz dane");
		menu.add(save);
		
		background = new JMenuItem("Wybierz kolor t≥a");
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
		language = new JMenu("Wybierz jÍzyk");
		help.add(language);
		
		eng = new JMenuItem("English", new ImageIcon("en_flag.png"));
		eng.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) 
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
		});
		language.add(eng);
		
		esp = new JMenuItem("Espanol", new ImageIcon("sp.flag.png"));
		esp.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) 
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
		});
		
		language.add(esp);
		
		pol = new JMenuItem("Polski", new ImageIcon("pl_flag.png"));
		pol.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				
				help.setText("Pomoc");
				medium.setText("Wybierz orodek");
				water.setText("Woda"); 
				air.setText("Powietrze");
				save.setText("øapisz dane"); 
				background.setText("Wybierz kolor t≥a");
				exit.setText("Wyjúcie");
				info.setText("Info");
				language.setText("Wybierz jÍzyk");
				vSourceLabel.setText("PrÍdkoúÊ zrÛd≥π");
				vObserverLabel.setText("PrÍdkoúÊ obserwatora");
				frequency.setText("CzÍstotliwoúÊ");
				count.setText("Oblicz");
				result.setText("Wynik");
				reset.setText("Zeruj");
				chart.setText("Wykres");
				
				
			}
		});
		
		language.add(pol);
		
		
		//------------ Panel----------------
		leftPanel = new JPanel();
		centerPanel = new JPanel();
		bottomPanel = new JPanel();
		centerPanel.setBackground(Color.WHITE);

		this.add(leftPanel, BorderLayout.LINE_START);
		this.add(centerPanel, BorderLayout.CENTER);
		this.add(bottomPanel, BorderLayout.PAGE_END);
		
		
		//------------ Lewy Panel----------------
		leftPanel.setLayout(new GridLayout(11,1));
		leftPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		pusty1 = new JLabel();
		pusty2 = new JLabel();
		
		vSourceLabel = new JLabel("PrÍdkoúÊ èrÛd≥a [m/s]");
		vSourceSlider = new JSlider(JSlider.HORIZONTAL, MIN, MAX, INIT);
		vObserverLabel = new JLabel("PrÍdkoúÊ Obserwatora [m/s]");
		vObserverSlider = new JSlider(JSlider.HORIZONTAL, MIN, MAX, INIT);
		frequency = new JLabel("CzÍstotliwoúÊ:");
		frequencyField = new JTextField();
		count = new JButton("OBLICZ");
		result = new JLabel("Wyniki obliczeÒ:");
		resultField = new JTextField();
		
		
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
		leftPanel.add(pusty1);
		leftPanel.add(frequency);
		leftPanel.add(frequencyField);
		leftPanel.add(count);
		leftPanel.add(pusty2);
		leftPanel.add(result);
		leftPanel.add(resultField);
		
		
		//------------ Dolny Panel----------------
		//dolny.setLayout(new BoxLayout(dolny, BoxLayout.X_AXIS));
		
		reset = new JButton("Zeruj dane");
		chart = new JLabel("Wykres");
		//odglos = new JLabel("Odg≥os ürÛd≥a");
		
		String sounds[]={"Brak dzwiÍku", "Odg≥os zrodl aa", "Odg≥os zrodl bb",};        
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
