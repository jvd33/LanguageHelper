package db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * Class to coordinate with the DB so
 * we dont have to query google everytime 
 * @author joe
 *
 */
public class MySQLHelper {
	

	private Connection connect = null;
	private PreparedStatement query = null; //query
	private PreparedStatement preparedStatement = null; //entry
	private String sizeString;
	private String queryString;
	private String randQuery;
	private ResultSet rs = null;
	
	/**
	 * Constructor
	 * @param dbname (different for each translator)
	 */
	public MySQLHelper(String dbname, String t) { 
		openConnection(dbname, t);
		
	}
	
	/**
	 * Open a connection
	 * @param s, db name
	 * @param t, table name
	 */
	public void openConnection(String s, String t) { 
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost/" + s
					+"?user=joe&password=mystery&characterEncoding=utf8");
			//?'s -> lang 1, lang 2
			preparedStatement = connect.prepareStatement("insert into " + s+"."+t+ " values" +
					"(?, ?, ?)");
			//?'s -> desired column, given column, held value
			queryString = "SELECT * FROM " + s + "." + t + " WHERE ";
			randQuery = "SELECT cro, eng from croeng ORDER BY RAND() limit 1";
			sizeString = "SELECT count(*) row from " + t;
		} catch(Exception e) { 
			e.printStackTrace();
		} 
	}
	
	/**
	 * Adds an entry to the db
	 * @param i, the input string
	 * @param t, the translated target string
	 */
	public void addEntry(String i, String t) { 
		try { 
			preparedStatement.setNull(1, java.sql.Types.INTEGER);
			preparedStatement.setString(2, i);
			preparedStatement.setString(3, t);
			preparedStatement.executeUpdate();
		}
		catch(SQLException e) { 
			e.printStackTrace();
		} 
	}
		
	/**
	 * Queries the DB
	 * @param col, the column you want the string from
	 * @param lang, the language of the input you have
	 * @param t, the input you have
	 * @return String, the translation of t
	 * @throws Exception
	 */
	public String queryDB(String col, String lang, String t) { 
		try { 
			query = connect.prepareStatement(queryString + lang + "=?");
			query.setString(1, t);
			rs = query.executeQuery();
			if(rs.next()) { 
				return rs.getString(col);
			} else { 
				return "";
			}
		} catch(SQLException e) { 
			e.printStackTrace();
			return "";
		}
	}
	
	/**
	 * Gets a key, val pair entry by primary key
	 * @param id
	 * @return
	 */
	public ArrayList<String> getRandEntry() { 
		ArrayList<String> keyvals = new ArrayList<String>();
		try { 
			PreparedStatement entryID = connect.prepareStatement(randQuery);
			rs = entryID.executeQuery();
			while(rs.next()) { 
				keyvals.add(rs.getString(1));
				keyvals.add(rs.getString(2));
			}
		} catch(SQLException e) { 
			e.printStackTrace();
		}
		return keyvals;
	}
	
	/**
	 * Gotta close, yo
	 */
	public void close() { 
		try {
			connect.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}