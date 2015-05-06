package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import system.Quizzer;

public class TestGUI {
	
	private JFrame frame;
	private JPanel panel;
	private JLabel question;
	private JTextArea input;
	private JButton translate;
	private String label;
	private String l1; //input language
	private String l2; //output language
	private Quizzer q;
	
	/**
	 * Constructor
	 */
	public TestGUI(String a, String b) { 
		l1 = a;
		l2 = b;
		
		q = new Quizzer();
		
		//swing components
		GridBagConstraints c = new GridBagConstraints();
		frame = new JFrame();
		frame.getContentPane().setSize(600, 600);
		panel = new JPanel();
		panel.setSize(600, 600);
		frame.add(panel);
		panel.setLayout(new GridBagLayout());
		
		question = new JLabel(q.next()+".");
		question.setPreferredSize(new Dimension(150, 50));
		
		input = new JTextArea();
		input.setPreferredSize(new Dimension(150, 50));
		
		translate = new JButton("Check");
		translate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(q.check(input.getText())) { 
					input.setBackground(Color.WHITE);
					question.setText(q.next()+".");
				} else { 
					input.setBackground(Color.RED);
				}
			} 
			
		});
				
		
		c.gridy = 1;
		c.gridx = 0;
		panel.add(question, c);
		
		c.gridx = 1;
		panel.add(input, c);
		
		c.gridx = 2;
		c.gridy = 2;
		panel.add(translate, c);
		
		/**
		JMenuBar menu = new JMenuBar();
		JMenu file = new JMenu("File");
		menu.add(file);
		frame.add(menu);*/
		
		frame.setTitle("Language Helper v.01");
		frame.pack();
		frame.setVisible(true);
		
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
	

}