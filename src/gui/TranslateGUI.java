package gui;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import system.Translation;

/**
 * GUI for the translator 
 * @author joe
 *
 */
public class TranslateGUI {
	
	private JFrame frame;
	private JPanel panel;
	private JTextArea input;
	private JTextArea output;
	private JButton translate;
	private String l1; //input language
	private String l2; //output language
	private Translation t;
	
	/**
	 * Constructor
	 */
	public TranslateGUI(String a, String b) { 
		
		l1 = a;
		l2 = b;
		
		t = new Translation(l1, l2);
		
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
				output.setText(t.translateToTarget(input.getText()));
			} 
			
		});
				
		
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
	
	public void setOutput(String arg0) { 
		output.setText(arg0);
	}

}
