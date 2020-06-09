package pl.edu.pw.fizyka.pojava.PL_WL;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.KeyStroke;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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

public class MainFrame extends JFrame implements ActionListener		//Piotr Lebiedziewicz, Weronika Lis
{
	Image icon;

	JPanel leftPanel, bottomPanel;
	MainPanel centerPanel;
	
	//-------Menu----
	JMenuBar menuBar;
	JMenu help, menu;
	JMenuItem medium, water, air, carbon;
	JMenuItem save, background, exit;
	JMenuItem info, language, eng, esp, pol;
	
	//------PanelLewy----
	JLabel vSourceLabel;
	JSlider vSourceSlider;
	
	JLabel vObserverLabel;
	JSlider vObserverSlider;
	
	JLabel MediumLabel, MediumValueLabel;
	
	JLabel frequencyLabel;
	JTextField frequencyField;
	JButton countButton;
	
	JLabel resultLabel;
	JEditorPane resultField;
	JScrollPane  scrollPane;
	
	static final int MIN = -10;
	static final int MAX = 10;
	static final int INIT = 0;
	
	//static int x;
	//static int nn = 1;
	
	//-----PanelDolny----
	JButton resetButton, chartButton;
	JComboBox sound;
	JToggleButton stopStart;
	
	//----Dzwiek----
	String audioFilePath = "";
	Clip audioClip = null;
	File audioFile = null;
	AudioInputStream audioStream = null;
	
	//---------------------------------------------------
	public MainFrame() throws HeadlessException
	{
		this.setSize(1050,650);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Efekt Dopplera");
		
		
		//-----------DodawnieIkony----
		icon = Toolkit.getDefaultToolkit().getImage("icon.png");    
		this.setIconImage(icon); 
		
		
		//------------UstawienieOknaNaŒrodku----
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
	               		public void actionPerformed(ActionEvent e) {}
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
							  " SYMULACJA EFEKTU DOPPLERA\n "
							  + "Zjawisko obserwowane dla fal, polega na\n"
							  + " powstawaniu ró¿nicy czêstotliwoœci fali\n"
							  + " wysy³anej przez zród³o, które siê porusza\n"
							  + " oraz czêstotliwoœci fali rejestrowanej przez\n"
							  + " obserwatora.", 
							  		"Co to za program?",JOptionPane.QUESTION_MESSAGE);
						
			}
					
		});
		help.add(info);
		menuBar.add(help);
		
		//------------ ZmianaJezykaZmenu----------------
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
			
		
		//------------ Panel----
		leftPanel = new JPanel();
		centerPanel = new MainPanel();
		bottomPanel = new JPanel();
		centerPanel.setBackground(Color.WHITE);
		
		this.add(leftPanel, BorderLayout.LINE_START);
		leftPanel.setPreferredSize(new Dimension(250, 1050));
		this.add(centerPanel, BorderLayout.CENTER);
		this.add(bottomPanel, BorderLayout.PAGE_END);
		
		
		//------------ LewyPanel----
		leftPanel.setLayout(new GridLayout(11,1));
		leftPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

		vSourceLabel = new JLabel("Prêdkoœæ ród³a [m/s]:   0 ");   
		vSourceSlider = new JSlider(JSlider.HORIZONTAL, MIN, MAX, INIT);
		vObserverLabel = new JLabel("Prêdkoœæ Obserwatora [m/s]:   0 ");
		vObserverSlider = new JSlider(JSlider.HORIZONTAL, MIN, MAX, INIT);
		MediumLabel = new JLabel("Prêd. w oœrodku[m/s] - POWIETRZE");
		MediumValueLabel = new JLabel("343");
		frequencyLabel = new JLabel("Czêstotliwoœæ [Hz]:");
		frequencyField = new JTextField();
		countButton = new JButton("OBLICZ");
		resultLabel = new JLabel("Wyniki obliczeñ:");
		resultField = new JEditorPane();
		resultField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		resultField.setEditable(false);
		scrollPane = new JScrollPane();
		
		ValueOfSlider e = new ValueOfSlider();
		vSourceSlider.addChangeListener(e);
		vObserverSlider.addChangeListener(e);
		
		Calculations e4 = new Calculations();
		countButton.addActionListener(e4);	
			
		vSourceSlider.setMajorTickSpacing(2);
		vSourceSlider.setMinorTickSpacing(1);
		vSourceSlider.setPaintTicks(true);
		vSourceSlider.setPaintLabels(true);
		vObserverSlider.setMajorTickSpacing(2);
		vObserverSlider.setMinorTickSpacing(1);
		vObserverSlider.setPaintTicks(true);
		vObserverSlider.setPaintLabels(true);
		
		leftPanel.add(vSourceLabel);
		leftPanel.add(vSourceSlider);
		leftPanel.add(vObserverLabel);
		leftPanel.add(vObserverSlider);
		leftPanel.add(MediumLabel);
		leftPanel.add(MediumValueLabel);
		leftPanel.add(frequencyLabel);
		leftPanel.add(frequencyField);
		leftPanel.add(countButton);
		leftPanel.add(resultLabel);
		leftPanel.add(scrollPane);
		scrollPane.setViewportView(resultField);
		
	
		//------------ Dolny Panel----------------
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
		resetButton = new JButton("Zeruj dane");
		
	    ClearData e7 = new ClearData();
	    resetButton.addActionListener(e7);

		chartButton = new JButton("Wykres");
		Chart e8 = new Chart();
		chartButton.addActionListener(e8);
		
		String sounds[]={"Brak dzwiêku", "1) Odg³os zrodla ", "2) Odg³os zrodl ",};        
	    sound = new JComboBox(sounds);
	    
	    sound.addActionListener(new ActionListener() 
	    {
	        public void actionPerformed(ActionEvent e)
	        {
	          
	          if (sound.getSelectedItem().equals("1) Odg³os zrodla ")) 
	          {
	        	  		
	  		    	play("./resources/1-welcome.wav");	//wstawiæ jakiœ dzwiêk zamiast tego
	  		    	audioClip.start();
	        }
	          if (sound.getSelectedItem().equals("Brak dzwiêku")) 
	          {
	  		    	audioClip.stop();
	          }
	          
	          if (sound.getSelectedItem().equals("2) Odg³os zrodl ")) 
	          {
	        	  	//play("./resources/1-welcome.wav");	//wstawiæ jakiœ INNY dzwiêk zamiast tego
	  		    	//audioClip.start();
	          }
	        }
	      });
	    
	    
	    stopStart = new JToggleButton("START ANIMACJI");
	    stopStart.addActionListener(this);
	   
	    stopStart.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				 if (stopStart.isSelected())  
			            stopStart.setText("STOP ANIMACJI");  
			        else  
			        	
			        stopStart.setText("START ANIMACJI");
					if(!centerPanel.running){
						centerPanel.uruchomAnimacje();				
					} else
					{
						centerPanel.tm.stop();
						centerPanel.running = false;
					}
					
			}			
		});
		 
		bottomPanel.add(resetButton);
		bottomPanel.add(chartButton);
		bottomPanel.add(sound);   
		bottomPanel.add(stopStart);
		
		//------------------------------
		
	}  
	public class ChangeMedium implements ActionListener	//Weronika Lis	
	{
		public void actionPerformed(ActionEvent e5) 
        {	
			if(e5.getActionCommand().equals("WODA"))
			{
				MediumLabel.setText("Prêd. w oœrodku[m/s] - WODA");
				MediumValueLabel.setText("1490");
			}
			else if(e5.getActionCommand().equals("POWIETRZE"))
			{
				MediumLabel.setText("Prêd. w oœrodku[m/s] - POWIETRZE");
				MediumValueLabel.setText("343");
			}
			else if(e5.getActionCommand().equals("DWUTLENEK WÊGLA"))
			{
				MediumLabel.setText("Prêd. w oœrodku[m/s] - DWUTLENEK WÊGLA");
				MediumValueLabel.setText("259");
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
			MediumLabel.setText("Velo. of medium[m/s] - AIR");
			frequencyLabel.setText("Frequency");
			countButton.setText("Count");
			resultLabel.setText("Result");
			resetButton.setText("Reset");
			chartButton.setText("Chart");
			MediumLabel.setText("V in medium [m/s] - WATER");
			String sounds[]={"No sound", "1) Source sound ", "2) Source sound ",};        
		    
			
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
			MediumLabel.setText("Velo. of medium[m/s] - AIR");
			frequencyLabel.setText("Frecuencia");
			countButton.setText("Calcular");
			resultLabel.setText("Resultar");
			resetButton.setText("Reiniciar");
			chartButton.setText("Grafico");
			MediumLabel.setText("V en resort [m/s] - AQUA");
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
			MediumLabel.setText("Prêd. w oœrodku[m/s] - POWIETRZE");
			frequencyLabel.setText("Czêstotliwoœæ");
			countButton.setText("Oblicz");
			resultLabel.setText("Wynik");
			resetButton.setText("Zeruj");
			chartButton.setText("Wykres");
			MediumLabel.setText("V w oœrodku [m/s] - WODA");
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
		public void actionPerformed(ActionEvent e) 
        {	
            double freq, vMe;
            String frequency = frequencyField.getText();
            String medium = MediumValueLabel.getText();
            int vSo = vSourceSlider.getValue();
            int vOb = vObserverSlider.getValue();
 
            try
            {
                freq = Double.parseDouble(frequency);
                vMe = Double.parseDouble(medium);
              
                if(vOb == vSo )	//oba nieruchome
                {
                	double fk = freq;
                	resultField.setText(resultField.getText()+ " fk ="+ fk + "Hz"+"\n");
                	resultField.setText(resultField.getText()+ " T ="+ (1/fk) + "s"+"\n");
                	resultField.setText(resultField.getText()+ " d³.fali ="+ (vMe/fk) + "m"+"\n");
                }
                else if(vOb == 0 & vSo>0)	 //Zrodlo sie zbliza
                {
                  	double v = vMe-vSo;
                  	double fk = freq*(vMe/v);
                	resultField.setText(resultField.getText()+ " fk = "+ fk + "Hz"+"\n");
                	resultField.setText(resultField.getText()+ " T ="+ (1/fk) + "s"+"\n");
                	resultField.setText(resultField.getText()+ " d³.fali ="+ (vMe/fk) + "m"+"\n");
                }
                 else if(vOb == 0 & vSo<0)		//Zrodlo sie oddala
                {
                	 double v = vMe+vSo;
                	 double fk = freq*(vMe/v);
                	resultField.setText(resultField.getText()+ " f ="+ fk + "Hz"+"\n");
                	resultField.setText(resultField.getText()+ " T ="+ (1/fk) + "s"+"\n");
                	resultField.setText(resultField.getText()+ " d³.fali ="+ (vMe/fk) + "m"+"\n");
                }
                 else if(vOb<0 & vSo==0)	//Obserwator sie oddala
                {
                 	double v = vMe-vOb;
                 	double fk = freq*(v/vMe);
                	resultField.setText(resultField.getText()+ " f ="+ fk + "Hz"+"\n");
                	resultField.setText(resultField.getText()+ " T ="+ (1/fk) + "s"+"\n");
                	resultField.setText(resultField.getText()+ "d³.fali ="+ (vMe/fk) + "m"+"\n");
                }
                else if(vOb>0 & vSo==0)		//Obserwator siê zbli¿a
                {
                	double v = vMe+vOb;
                	double fk = freq*(v/vMe);
                	resultField.setText(resultField.getText()+ " f ="+ fk + "Hz"+"\n");
                	resultField.setText(resultField.getText()+ " T ="+ (1/fk) + "s"+"\n");
                	resultField.setText(resultField.getText()+ "d³.fali ="+ (vMe/fk) + "m"+"\n");
                }
                else if(vOb>0 & vSo<0)		//Obserwator i zród³o oddalaj¹ siê
                {
                	double v = vMe-vOb;
                	double v1 = vMe+vSo;
                	double fk = freq*(v/v1);
                	resultField.setText(resultField.getText()+ " f ="+ fk + "Hz"+"\n");
                	resultField.setText(resultField.getText()+ " T ="+ (1/fk) + "s"+"\n");
                	resultField.setText(resultField.getText()+ " d³.fali ="+ (vMe/fk) + "m"+"\n");
                }
                else if(vOb<0 & vSo>0)	//Obserwator i zród³o zbli¿aj¹ siê
                {
                	double v = vMe+vOb;
                	double v1 = vMe-vSo;
                	double fk = freq*(v/v1);
                	resultField.setText(resultField.getText()+ " f ="+ fk + "Hz"+"\n");
                	resultField.setText(resultField.getText()+ " T ="+ (1/fk) + "s"+"\n");
                	resultField.setText(resultField.getText()+ " d³.fali ="+ (vMe/fk) + "m"+"\n");
                }
                else if(vOb>0 & vSo>0 )	//Obserwator sie oddala, zród³o zbli¿a
                {
                	double v = vMe-vOb;
                	double v1 = vMe-vSo;
                	double fk = freq*(v/v1);
                	resultField.setText(resultField.getText()+ " f ="+ fk + "Hz"+"\n");
                	resultField.setText(resultField.getText()+ " T ="+ (1/fk) + "s"+"\n");
                	resultField.setText(resultField.getText()+ " d³.fali ="+ (vMe/fk) + "m"+"\n");
                }

                else if(vOb<0 & vSo<0)	//Obserwator siê zbli¿a, zród³o oddala
                {
                	double v = vMe+vOb;
                	double v1 = vMe+vSo;
                	double fk = freq*(v/v1);
                	resultField.setText(resultField.getText()+ " f ="+ fk + "Hz"+"\n");
                	resultField.setText(resultField.getText()+ " T ="+ (1/fk) + "s"+"\n");
                	resultField.setText(resultField.getText()+ " d³.fali ="+ (vMe/fk) + "m"+"\n");
                }
            }
            
            catch(NumberFormatException exception)	
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
			MediumLabel.setText("V oœrodka [m/s] - POWIETRZE");
			MediumValueLabel.setText("343");
			//centerPanel.clear();
        }
	}	

    void play(String audioFilePath) //Weronika Lis
    {
        try 
        {
            audioFile = new File(audioFilePath);
            audioStream = AudioSystem.getAudioInputStream(audioFile);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            audioClip = (Clip) AudioSystem.getLine(info);
            audioClip.open(audioStream);
        }
        catch (UnsupportedAudioFileException ex)
        {
            System.out.println("The specified audio file is not supported.");
            ex.printStackTrace();
        } 
        catch (LineUnavailableException ex) 
        {
            System.out.println("Audio line for playing back is unavailable.");
            ex.printStackTrace();
        } 
        catch (IOException e1) 
        {
            System.out.println("Error playing the audio file.");
			e1.printStackTrace();
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
	public void actionPerformed(ActionEvent arg0){}

}

