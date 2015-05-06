package system;
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
public class Translation {
	
	private MySQLHelper dao;
	private static Translator translator;
	private String l1; //input language
	private String l2; //target language
	
	/**
	 * Constructor for a croation translator
	 */
	public Translation(String a, String b) { 
		dao = new MySQLHelper("translations", "croeng");
		l1 = a;
		l2 = b;
		translator = Translator.getInstance();
	}
	
	/**
	 * Translate input into target language
	 */
	public String translateToTarget(String a) {
		String temp = dao.queryDB("cro", "eng", a);
		if(temp.equals("")) { 
			temp = translator.translate(a, Language.ENGLISH, Language.CROATIAN);
			dao.addEntry(a, temp);
		} 
		return temp;
	}

	/**
	 * Translate target language into input language
	 */
	private void translateFromTarget(String a, String b) {
	}

	
	public static void main(String args[]) { 
		String a = Translator.getInstance().translate("good day", Language.ENGLISH, Language.CROATIAN);
		System.out.println(a);
	}

}
