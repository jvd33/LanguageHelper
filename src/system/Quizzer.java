package system;

import java.util.ArrayList;
import java.util.Random;

import db.MySQLHelper;

/**
 * Model class for an ongoing translation game
 * @author joe
 *
 */
public class Quizzer {
	
	private MySQLHelper dao;
	private ArrayList<String> curr;
	
	public Quizzer() { 
		dao = new MySQLHelper("translations", "croeng");

		
	}
	
	public boolean check(String s) { 
		return s.equals(curr.get(1));
	}
	
	/**
	 * gets a random word from croatian to ask to translate
	 * @return
	 */
	public String nextQuestion() { 
		curr = dao.getRandEntry();
		return curr.get(0);
	}

}
