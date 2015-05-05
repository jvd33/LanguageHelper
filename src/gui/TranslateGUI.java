package gui;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import translate.Translation;

/**
 * GUI for the translator 
 * @author joe
 *
 */
public class TranslateGUI extends Observable {
	
	private JFrame frame;
	private JPanel panel;
	private String[] langOpts;
	private JTextArea input;
	private JTextArea output;
	private JButton translate;
	private JComboBox<String> inputLang;
	private JComboBox<String> outputLang;
	
	/**
	 * Constructor
	 */
	public TranslateGUI() { 
		//read languages in from .csv file
		loadLanguages();
		
		Translation t = new Translation(this);
		
		//swing components
		GridBagConstraints c = new GridBagConstraints();
		frame = new JFrame();
		frame.getContentPane().setSize(600, 600);
		panel = new JPanel();
		panel.setSize(600, 600);
		frame.add(panel);
		panel.setLayout(new GridBagLayout());
		
		input = new JTextArea();
		input.setPreferredSize(new Dimension(150, 50));
		
		output = new JTextArea();
		output.setPreferredSize(new Dimension(150, 50));
		output.setEditable(false);
		
		translate = new JButton("TRANSLATE!");
		translate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setChanged();
				notifyObservers(input.getText());
			} 
			
		});
		
		inputLang = new JComboBox<String>(langOpts);
		outputLang = new JComboBox<String>(langOpts);
		
		c.gridx = 0;
		c.gridy = 0;
		panel.add(inputLang, c);
		
		c.gridx = 1;
		panel.add(outputLang, c);
		
		c.gridy = 1;
		c.gridx = 0;
		panel.add(input, c);
		
		c.gridx = 1;
		panel.add(output, c);
		
		c.gridx = 2;
		c.gridy = 2;
		panel.add(translate, c);
		
		/**
		JMenuBar menu = new JMenuBar();
		JMenu file = new JMenu("File");
		menu.add(file);
		frame.add(menu);*/
		
		frame.setTitle("Translation Helper v.01");
		frame.pack();
		frame.setVisible(true);
		
	}

	/**
	 * Reads the language from the csv file. Will be from the DB later?
	 */
	private void loadLanguages() {
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

	/**
	 * Gets the input language
	 * @return
	 */
	public String getInputLang() {
		return (String) inputLang.getSelectedItem();
	}

	/**
	 * Gets output language
	 * @return
	 */
	public String getTargetLang() {
		return (String) outputLang.getSelectedItem();
	}

	/**
	 * get target text
	 * @return
	 */
	public String getTarget() {
		String r;
		try { 
			r = input.getSelectedText();
		} catch(NullPointerException e) { 
			r = "";
		}
		return r;
	}
	
	public void setOutput(String arg0) { 
		output.setText(arg0);
	}

}
