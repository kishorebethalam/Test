import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;




public class Test {

	 private static Connection connect = null;
	  private static Statement statement = null;
	  private static ResultSet resultSet = null;

	/**K
	 * @param args
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		try {
			testMySQL();
		} catch (MalformedURLException | InstantiationException
				| IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void testMySQL() throws ClassNotFoundException, SQLException, MalformedURLException, InstantiationException, IllegalAccessException{
		// TODO Auto-generated method stub
		 Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
	      // setup the connection with the DB.
//	      connect = DriverManager
//	          .getConnection("jdbc:derby:openbravopos-database;create+true");
	      //+ "user=root&password=TGXMAcqi"

	      DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
	      connect = DriverManager.getConnection("/Users/kbethalam/openbravopos-database", "", "");


//          ClassLoader cloader = new URLClassLoader(new URL[] {new File("/Users/kbethalam/Documents/workspaces/hbase/Test/lib/derby.jar").toURI().toURL()});
//          DriverManager.registerDriver(new DriverWrapper((Driver) Class.forName("org.apache.derby.jdbc.EmbeddedDriver", true, cloader).newInstance()));
//// statements allow to issue SQL queries to the database
//	      statement = connect.createStatement();
	      // resultSet gets the result of the SQL query
//	      resultSet = statement
//	          .executeQuery("select * from FEEDBACK.COMMENTS");
//	      writeResultSet(resultSet);

	}
	
	private static void writeResultSet(ResultSet resultSet) throws SQLException {
	    // resultSet is initialised before the first data set
	    while (resultSet.next()) {
	      // it is possible to get the columns via name
	      // also possible to get the columns via the column number
	      // which starts at 1
	      // e.g., resultSet.getSTring(2);
	      String user = resultSet.getString("myuser");
	      String website = resultSet.getString("webpage");
	      String summary = resultSet.getString("summary");
	      Date date = resultSet.getDate("datum");
	      String comment = resultSet.getString("comments");
	      System.out.println("User: " + user);
	      System.out.println("Website: " + website);
	      System.out.println("Summary: " + summary);
	      System.out.println("Date: " + date);
	      System.out.println("Comment: " + comment);
	    }
	  }

}
