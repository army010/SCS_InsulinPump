
import java.awt.EventQueue;
import java.util.concurrent.TimeUnit;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.TextField;
import java.awt.TextArea;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JRadioButtonMenuItem;
import java.awt.Button;
import java.awt.Label;
import java.awt.Panel;
import javax.swing.JToggleButton;
import javax.swing.Timer;
import javax.swing.JRadioButton;
import javax.swing.event.ChangeListener;
import javax.swing.text.JTextComponent;

import org.jfree.ui.RefineryUtilities;

import javax.swing.event.ChangeEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.Date;
import java.beans.PropertyChangeEvent;
import javax.swing.JPasswordField;
import javax.swing.JMenuBar;
import javax.swing.JTextPane;

//khan
import java.awt.EventQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.TextField;
import java.awt.TextArea;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JRadioButtonMenuItem;
import java.awt.Button;
import java.awt.Label;
import java.awt.Panel;
import javax.swing.JToggleButton;
import javax.swing.Timer;
import javax.swing.JRadioButton;
import javax.swing.event.ChangeListener;
import javax.swing.text.JTextComponent;
import javax.swing.event.ChangeEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;
import java.beans.PropertyChangeEvent;
import javax.swing.JPasswordField;
import javax.swing.JPanel;
import java.awt.Font;

public class MainGUI {

	private JFrame frame;
	JLabel lblClock;
	JLabel jLabel2;
	JLabel jLabel7;
	JLabel jLabel12;
	JProgressBar progressBar;
	JProgressBar insulinBar;
	JFrame controllingFrame; //needed for dialogs
	Timer timer1;
    Timer batteryTimer;
    int batteryQty = 100;
    private AtomicBoolean paused;
    

    Messages messages = new Messages();
    Dose hormone = new Dose();
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField txtErrosAndWarnings;
    private JTextField textField_3;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		/*//copied
		Model mod = new Model();
		AbstractController contr = new ConcreteController(mod);
		GraphicalOutput output = new GraphicalOutput(mod);
		AbstractInput in = new GraphicalInput(contr, output);

		BasicInjector injector = new BasicInjector(contr);
		//BasicLogic logic = new BasicLogic(injector);
		ExtendedLogic logic = new ExtendedLogic(injector);
		BasicSensor sensor = new BasicSensor(mod,logic);

		new CSVOutput(mod,new File("testOutput.csv"),true);
		in.workLoop();
		//till here*/
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI window = new MainGUI();
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */

    private void startBattery() {

        ActionListener listener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
            	if(!paused.get()){
                batteryQty = batteryQty-1;
                setBattery(batteryQty);
            	}
            }
        };
        batteryTimer = new Timer(5000, listener);
        batteryTimer.start();
    }
    public void showWarning(String message) {

        jLabel7.setVisible(true);
        jLabel7.setText(message);
        Toolkit.getDefaultToolkit().beep();
    }
    
    public void resetWarning(){
    	jLabel7.setText("");
    }
    
    public void setBattery(int batteryQty) {

        progressBar.setValue(batteryQty);

	        if (batteryQty >= 50) {
	            progressBar.setForeground(new Color(0, 204, 0));
	
	        } else if (batteryQty < 50 && batteryQty >= 25) {
	
	            progressBar.setForeground(Color.yellow);
	
	        } else if (batteryQty < 25 && batteryQty > 0) {
	
	            progressBar.setForeground(Color.red);
	            showWarning(messages.WARNING_01);
	
	        } else {
	
	            progressBar.setForeground(Color.red);
	            showWarning(messages.WARNING_02);
	            batteryTimer.stop();
	        }
	    
        
        if(batteryQty==0){
            System.exit(0);
        }
    }
    
public void setInsulin(int qty){
    	
       int a= qty;
       String b= Integer.toString(a);
    	insulinBar.setValue(a);
        jLabel12.setText(b);        
        if ( qty >= 50 ) {

        	insulinBar.setForeground(Color.green);
             
        } else if( qty < 50 && qty >= 25 ) {
    		 
        	insulinBar.setForeground(Color.yellow);
          
        } else if ( qty < 25 && batteryQty > 0 ){
        	 
        	insulinBar.setForeground(Color.red);
        	 showWarning(messages.WARNING_03);
        	 
        } else {
        	 
        	insulinBar.setForeground(Color.red);
        	 showWarning(messages.WARNING_04);

        }
        
    }

	private static boolean isPasswordCorrect(char[] input) {
	    boolean isCorrect = true;
	    char[] correctPassword = { 'h', 'e', 'l', 'l', 'o' };
	
	    if (input.length != correctPassword.length) {
	        isCorrect = false;
	    } else {
	        isCorrect = Arrays.equals (input, correctPassword);
	    }
	
	    //Zero out the password.
	    Arrays.fill(correctPassword,'0');
	
	    return isCorrect;
	}

    
	public void clock(){
		Thread clk = new Thread()
		{
			public void run()
			{
				try {
					while(true){
					Calendar cal = new GregorianCalendar();
					/*int day = cal.get(Calendar.DAY_OF_MONTH);
					int month = cal.get(Calendar.MONTH);
					int year = cal.get(Calendar.YEAR);
					*/

					int second = cal.get(Calendar.SECOND);
					int minute = cal.get(Calendar.MINUTE);
					int hour = cal.get(Calendar.HOUR);
					
					lblClock.setText("Clock Time "+ hour +":"+minute +":"+second+" ");

					sleep(1000);
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		clk.start();
			}


	public MainGUI() {
		initialize();
		clock();
        startBattery();
	}

	private void initialize() {
		
		JButton btnDoctorsPanel = new JButton("Doctor's Panel");
		btnDoctorsPanel.setBounds(212, 275, 121, 23);
		
		paused = new AtomicBoolean(false);
		setFrame(new JFrame());
		getFrame().setBounds(100, 100, 595, 451);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrame().getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Blood Sugar Level");
		lblNewLabel.setBounds(10, 56, 110, 14);
		getFrame().getContentPane().add(lblNewLabel);
		
		JLabel lblBatteryRemaining = new JLabel("Battery level");
		lblBatteryRemaining.setBounds(101, 345, 74, 14);
		getFrame().getContentPane().add(lblBatteryRemaining);
		
		progressBar = new JProgressBar();
		progressBar.setBackground(Color.WHITE);
		progressBar.setValue(100);
		progressBar.setForeground(new Color(0, 204, 0));
		progressBar.setStringPainted(true);
		progressBar.setBounds(204, 345, 146, 14);
		getFrame().getContentPane().add(progressBar);
		
		JLabel lblInsulinLevel = new JLabel("Insulin level");
		lblInsulinLevel.setBounds(10, 117, 89, 14);
		getFrame().getContentPane().add(lblInsulinLevel);
		
		
		JLabel lblGlucagonLevel = new JLabel("Glucagon level");
		lblGlucagonLevel.setBounds(10, 171, 89, 14);
		getFrame().getContentPane().add(lblGlucagonLevel);
		

	    //jLabel2.setText("100"); 
	    
		
		
		lblClock = new JLabel("Clock");
		lblClock.setBounds(427, 12, 121, 23);
		getFrame().getContentPane().add(lblClock);
		
		jLabel7 = new JLabel("Full");
		jLabel7.setBounds(268, 364, 146, 14);
		jLabel7.setText("     ");
		getFrame().getContentPane().add(jLabel7);
		
		insulinBar = new JProgressBar();
		insulinBar.setValue(100);
		insulinBar.setForeground(new Color(0, 204, 0));
		insulinBar.setBounds(121, 117, 146, 14);
		getFrame().getContentPane().add(insulinBar);
		
		JProgressBar glucagonBar = new JProgressBar();
		glucagonBar.setValue(100);
		glucagonBar.setForeground(new Color(0, 204, 0));
		glucagonBar.setBounds(121, 171, 146, 14);
		getFrame().getContentPane().add(glucagonBar);
		
		JButton insulinFull = new JButton("+");
		insulinFull.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		insulinFull.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(!paused.get()){
					insulinBar.setValue(100);
				}
			}
		});
		insulinFull.setBounds(284, 113, 41, 18);
		getFrame().getContentPane().add(insulinFull);
		
		JButton glucagonFull = new JButton("+");
		glucagonFull.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		glucagonFull.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!paused.get()){
					glucagonBar.setValue(100);
				}
			}
		});
		glucagonFull.setBounds(284, 169, 41, 18);
		getFrame().getContentPane().add(glucagonFull);
		
		JButton btnChargeDevice = new JButton("+");
		btnChargeDevice.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(!paused.get()){
					setBattery(100);
					jLabel7.setText("Full");
				}
			}
		});
		btnChargeDevice.setBounds(373, 345, 41, 14);
		getFrame().getContentPane().add(btnChargeDevice);
		
		textField = new JTextField();
		textField.setBackground(Color.RED);
		textField.setBounds(6, 12, 14, 14);
		getFrame().getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBackground(Color.ORANGE);
		textField_1.setColumns(10);
		textField_1.setBounds(32, 12, 14, 14);
		getFrame().getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setBackground(Color.GREEN);
		textField_2.setColumns(10);
		textField_2.setBounds(58, 12, 14, 14);
		getFrame().getContentPane().add(textField_2);
		
		txtErrosAndWarnings = new JTextField();
		txtErrosAndWarnings.setEnabled(false);
		txtErrosAndWarnings.setText("Erros and Warnings");
		txtErrosAndWarnings.setBounds(94, 9, 126, 20);
		getFrame().getContentPane().add(txtErrosAndWarnings);
		txtErrosAndWarnings.setColumns(10);
		
		JLabel JLabel12 = new JLabel("");
		JLabel12.setBounds(165, 143, 55, 16);
		getFrame().getContentPane().add(JLabel12);
		
		textField_3 = new JTextField();
		textField_3.setBounds(120, 53, 114, 20);
		getFrame().getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JMenuBar menuBar = new JMenuBar();
		getFrame().setJMenuBar(menuBar);
		
				
		JMenu mnPumpSettings = new JMenu("Pump Settings");
		menuBar.add(mnPumpSettings);
		
		JCheckBoxMenuItem chckbxmntmSetting = new JCheckBoxMenuItem("Setting 1");
		chckbxmntmSetting.setSelected(true);
		mnPumpSettings.add(chckbxmntmSetting);
		
		JCheckBoxMenuItem chckbxmntmSetting_1 = new JCheckBoxMenuItem("Setting 2");
		chckbxmntmSetting_1.setSelected(true);
		mnPumpSettings.add(chckbxmntmSetting_1);
		
		JMenu mnPumpMode = new JMenu("Pump Mode");
		menuBar.add(mnPumpMode);
		
		JRadioButtonMenuItem rdbtnmntmAutomatic = new JRadioButtonMenuItem("Automatic");
		rdbtnmntmAutomatic.setSelected(true);
		mnPumpMode.add(rdbtnmntmAutomatic);
		
		JRadioButtonMenuItem rdbtnmntmManual = new JRadioButtonMenuItem("Manual");
		mnPumpMode.add(rdbtnmntmManual);
		
		JMenu mnGraphs = new JMenu("Graphs");
		mnGraphs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!paused.get()){
					GraphGUI graph = new GraphGUI("BSL Simulation");
					graph.pack();
			        RefineryUtilities.centerFrameOnScreen(graph);
			        graph.setVisible(true);
				}
			}
		});
		menuBar.add(mnGraphs);
		
		JToggleButton powerButton = new JToggleButton("Power On/Off");
		powerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(powerButton.isSelected()){
					powerButton.setText("ON");
					paused.set(true);
					//injectInsulinButton.setEnabled(false);
					//injectGlucagonButton.setEnabled(false);		
					insulinFull.setEnabled(false);
					glucagonFull.setEnabled(false);
					mnGraphs.setEnabled(false);
					mnPumpMode.setEnabled(false);
					mnPumpSettings.setEnabled(false);
					btnChargeDevice.setEnabled(false);
					btnDoctorsPanel.setEnabled(false);
				}
				else
				{
					powerButton.setText("OFF");
					paused.set(false);
					//injectInsulinButton.setEnabled(true);
					//injectGlucagonButton.setEnabled(true);
					insulinFull.setEnabled(true);
					glucagonFull.setEnabled(true);
					mnGraphs.setEnabled(true);
					mnPumpMode.setEnabled(true);
					mnPumpSettings.setEnabled(true);
					btnChargeDevice.setEnabled(true);
					btnDoctorsPanel.setEnabled(true);
				}

			}
		});
		
		
		powerButton.setBounds(212, 308, 121, 23);
		getFrame().getContentPane().add(powerButton);
		
		//JButton btnDoctorsPanel = new JButton("Doctor's Panel");
		//btnDoctorsPanel.setBounds(212, 275, 121, 23);
		frame.getContentPane().add(btnDoctorsPanel);
		btnDoctorsPanel.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				if(!paused.get()){
					JPanel panel = new JPanel();
					JLabel label = new JLabel("Enter a password:");
					JPasswordField pass = new JPasswordField(10);
					panel.add(label);
					panel.add(pass);
					String[] options = new String[]{"OK", "Cancel"};
					int option = JOptionPane.showOptionDialog(null, panel, "Doctor's mode",
					                         JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
					                         null, options, options[1]);
					if(option == 0) // pressing OK button
					{
					    char[] password = pass.getPassword();
					    if (isPasswordCorrect(password)) {
				            JOptionPane.showMessageDialog(controllingFrame,
				                "Success! You typed the right password.");
				            
				            DoctorGUI doctorGUI = new DoctorGUI();
				           
				            
				        } else {
				            JOptionPane.showMessageDialog(controllingFrame,
				                "Invalid password. Try again.",
				                "Error Message",
				                JOptionPane.ERROR_MESSAGE);
				        }
					}
					
				}
			}
		});
		
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	}
