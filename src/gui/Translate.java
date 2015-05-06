package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Runs the app
 * @author joe
 *
 */
public class Translate { 
	
	private static String[] langOpts;
	private static JFrame frame;
	private static JComboBox<String> inputLang;
	private static JComboBox<String> outputLang;
	private static JPanel panel;
	private static JButton test = new JButton("Test me!");
	private static JButton translate = new JButton("Translate Mode");
	private static JLabel output = new JLabel("Target Language: ");
	private static JLabel input = new JLabel("Input Language: ");
	
	/**
	 * Gets the input language
	 * @return
	 */
	public static String getInputLang() {
		return (String) inputLang.getSelectedItem();
	}

	/**
	 * Gets output language
	 * @return
	 */
	public static String getTargetLang() {
		return (String) outputLang.getSelectedItem();
	}
	
	/**
	 * Reads the language from the csv file. Will be from the DB later?
	 */
	private static void loadLanguages() {
		try { 
			BufferedReader br = new BufferedReader(new FileReader(new File("res/lang.csv")));
			langOpts = br.readLine().split(",");
			br.close();
		}
		catch(IOException e) { 
			System.out.println("File not found");
			e.printStackTrace();
		}
		
	}
	
	public static void main(String args[]) { 
		frame = new JFrame();
		panel = new JPanel();
		frame.add(panel);
		frame.setTitle("Language Helper v.01");
		test.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new TestGUI(getInputLang(), getTargetLang());
				frame.dispose();
				
			} 
			
		});
		translate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new TranslateGUI(getInputLang(), getTargetLang());
				frame.dispose();	
			} 
			
		});
		loadLanguages();
		inputLang = new JComboBox<String>(langOpts);
		outputLang = new JComboBox<String>(langOpts);
		panel.setSize(600, 600);
		panel.add(input, BorderLayout.NORTH);
		panel.add(inputLang, BorderLayout.CENTER);
		panel.add(output, BorderLayout.NORTH);
		panel.add(outputLang, BorderLayout.CENTER);
		panel.add(test, BorderLayout.SOUTH);
		panel.add(translate, BorderLayout.SOUTH);
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
		
	}
}