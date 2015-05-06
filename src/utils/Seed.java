package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.gtranslate.Language;

import system.Translation;

import db.MySQLHelper;

/**
 * Seeds the DB with the default language vals
 * @author joe
 *
 */
public class Seed {
	private static MySQLHelper dao;
	private static String[] enWords;
	private static List<String> englishWords;
	private static Translation t;
	
	public static void main(String args[]) { 
		t = new Translation(Language.ENGLISH, Language.CROATIAN);
		try { 
			BufferedReader br = new BufferedReader(new FileReader(new File("res/seed.csv")));
			enWords = br.readLine().split(",");
			englishWords =  Arrays.asList(enWords);
			br.close();
		}
		catch(IOException e) { 
			System.out.println("File not found");
			e.printStackTrace();
		}
		Iterator<String> it = englishWords.iterator();
		while(it.hasNext()) { 
			String s = it.next();
			t.translateToTarget(s);
		}
	}
	

}
