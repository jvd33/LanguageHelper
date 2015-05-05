package translate;
import gui.TranslateGUI;
import java.util.Observable;
import java.util.Observer;
import com.gtranslate.*;

import db.MySQLHelper;

/**
 * Concrete instance of an English to Croatian translator
 * @author joe
 *
 */
public class Translation implements Observer {
	
	private MySQLHelper dao;
	private static Translator translator;
	private String targetString;
	private String finalString; //translated string
	private String l1; //input language
	private String l2; //target language
	private TranslateGUI gui;
	
	/**
	 * Constructor 
	 */
	public Translation(TranslateGUI g) { 
		dao = new MySQLHelper("translations", "croeng");
		gui = g;
		gui.addObserver(this);
		targetString = gui.getTarget();
		translator = Translator.getInstance();
	}
	
	/**
	 * Translate input into target language
	 */
	private void translateToTarget(String l1, String l2) {
		boolean inDB = false;
		try {
			String temp = dao.queryDB("cro", "eng", targetString);
			if(temp.equals("")) { 
				finalString = translator.translate(targetString, Language.ENGLISH, Language.CROATIAN);
				dao.addEntry(targetString, finalString);
			} else { 
				finalString = temp;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Translate target language into input language
	 */
	private void translateFromTarget(String l1, String l2) {
		finalString = translator.translate(targetString, l2, l1);
	}

	/**
	 * Update on changes to the GUI
	 */
	@Override
	public void update(Observable o, Object arg) {
		l1 = gui.getInputLang();
		l2 = gui.getTargetLang();
		targetString = (String)arg;
		translateToTarget(l1, l2);
		gui.setOutput(finalString);
		
	}
	
	public static void main(String args[]) { 
		String a = Translator.getInstance().translate("good day", Language.ENGLISH, Language.CROATIAN);
		System.out.println(a);
	}

}
