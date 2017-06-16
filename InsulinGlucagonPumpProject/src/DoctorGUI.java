import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JRadioButton;
import javax.swing.ListModel;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;

public class DoctorGUI extends JFrame{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public DoctorGUI() {
		getContentPane().setLayout(null);
		frame = new JFrame();
		frame.setBounds(100, 100, 830, 618);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.getContentPane().setLayout(null);
		
		JCheckBox autoButton = new JCheckBox("Automatic mode");
		autoButton.setBounds(590, 409, 184, 29);
		frame.getContentPane().add(autoButton);
		
		JCheckBox chckbxManualMode = new JCheckBox("Manual mode");
		chckbxManualMode.setBounds(590, 446, 139, 29);
		frame.getContentPane().add(chckbxManualMode);
		
		JButton backButton = new JButton("<< Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		backButton.setBounds(24, 492, 136, 56);
		frame.getContentPane().add(backButton);
		
		JLabel lblModeSettings = new JLabel("Mode Settings:");
		lblModeSettings.setBounds(590, 377, 139, 20);
		frame.getContentPane().add(lblModeSettings);

		JList<String> historyList = new JList<String>();
		historyList.setBounds(24, 61, 264, 313);
		frame.getContentPane().add(historyList);
		
		JButton historyButton = new JButton("History");
		historyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultListModel<String> list = new DefaultListModel<String>();
				
				// Read CSV File
				Path pathToFile = FileSystems.getDefault().getPath("test", "book1.csv");
				
				CsvReader csvReader = new CsvReader(); 				
				String[][] result = null;
				
				try {
					result = csvReader.readFile(pathToFile);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 

				int numOfRows = result.length;
				int numOfColumns = result[0].length;
				String temp = "";

				// Print out the array:
				for (int i = 0; i < numOfRows; i++) {
					for (int j = 0; j < numOfColumns; j++) {
						if(result[i][j] != null) {
							temp += result[i][j];
						}
						
					}
					list.addElement(temp);
					temp="";
				}
				
				historyList.setModel(list);		
				
			}
		});
		historyButton.setBounds(99, 16, 115, 29);
		frame.getContentPane().add(historyButton);
		


		
		
		
	}

}
