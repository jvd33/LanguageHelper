package system;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.TreeMap;

import db.MySQLHelper;

/**
 * Model class for an ongoing translation game
 * @author joe
 *
 */
public class Quizzer {
	
	private MySQLHelper dao;
	private ArrayList<String> curr; //current question
	private TreeMap<String, String> used; //all questions for the current quiz
	private int quesNum; //num of questions
	private Iterator<String> it;
	private float score; //total score
	private int currAttempts; //num of attempts at current problem
	private String currString;
	private int totalQ;
	
	public Quizzer() { 
		dao = new MySQLHelper("translations", "croeng");
		quesNum = 0;
		totalQ = 2;
		used = new TreeMap<String, String>();
		fillQuestions();
		score = 0;
		it = used.keySet().iterator();
		

		
	}
	
	public boolean check(String s) { 
		if(s.equals(used.get(currString))) { 
			score += (1/currAttempts);
			return true;
		} else { 
			currAttempts++;
			return false;
		}
	}
	
	/**
	 * gets a random word from croatian to ask to translate
	 * @return
	 */
	public void fillQuestions() { 
		while(quesNum < totalQ) { 
			curr = dao.getRandEntry();
			if(used.containsKey(curr.get(0))) { 
				continue;
			} else { 
				used.put(curr.get(0), curr.get(1));
			}
			quesNum++;
		}
		dao.close();
	}
		
	/**
	 * Gets the next string to translate.
	 */
	public String next() { 
		currAttempts = 1;
		if(it.hasNext()) { 
			currString = it.next();
			return "Translate: " + currString;
		} else { 
			return "Game over, you scored " + calculateScore() + "%";
		}
	}
	
	/**
	 * Calculates the user's quiz score
	 * @return
	 */
	private float calculateScore() { 
		System.out.println(score);
		System.out.println(quesNum);
		return (score/quesNum) * 100;
		
	}

}
