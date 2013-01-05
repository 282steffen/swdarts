package data;

import app.RTWStrategy;
import app.TargetHelper;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataController {

    static String driver = "org.apache.derby.jdbc.EmbeddedDriver";

    static String dbName = "swdarts_dev";

    static String connectionURL = "jdbc:derby:" + dbName + ";create=true";
    static Connection conn = null;
    static Statement stmt;
    private static DataController instance_;

    private DataController() {
	if(conn == null){
	    conn = createConnection();
        createTables();
	}
    }
    private Connection createConnection() {
	try {
	    /*
	     * * Load the Derby driver.* When the embedded Driver is used this
	     * action start the Derby engine.* Catch an error and suggest a
	     * CLASSPATH problem
	     */
	    Class.forName(driver);
	    System.out.println(driver + " loaded. ");
	} catch (java.lang.ClassNotFoundException e) {
	    System.err.print("ClassNotFoundException: ");
	    System.err.println(e.getMessage());
	    System.out
	    .println("\n    >>> Please check your CLASSPATH variable   <<<\n");
	}
	// Beginning of Primary DB access section
	// ## BOOT DATABASE SECTION ##
	try {
	    // Create (if needed) and connect to the database
	    conn = DriverManager.getConnection(connectionURL);
	    System.out.println("Connected to database " + dbName);}
	catch(SQLException e){
	    e.printStackTrace();
	}
	return conn;
    }

    private void createTables(){
        //RTW Tables
        String[] rtws = {"RTWSIMPLE","RTWBIG","RTWSMALL","RTWDOUBLE","RTWTRIPLE"};
        for (String rtw : rtws) {
            createRTWTable(rtw);
        }
    }
    private void dropTable(String tablename) {
        if (conn == null) {
            createConnection();
            if (conn == null) {
                return;
            }
        }
        try {
            if (conn.isClosed()) {
                conn = createConnection();
            }
            Statement stmt = conn.createStatement();
            String query = "DROP TABLE "+tablename;
            stmt.executeUpdate(query);

        } catch (SQLException e) {
            if (!e.getSQLState().equals("42Y55")) {
                e.printStackTrace();
            }
            System.out.println(tablename + " already deleted");

        }
    }
    private void createRTWTable(String tablename) {
        if (conn == null) {
            createConnection();
            if (conn == null) {
                return;
            }
        }
        try {
            if (conn.isClosed()) {
                conn = createConnection();
            }
            Statement stmt = conn.createStatement();
            String query = "CREATE TABLE "+tablename+" ("
                    + "PLAYERNAME VARCHAR(24) NOT NULL , "
                    + "GAMEID INTEGER NOT NULL PRIMARY KEY, " + "GAMEDATE DATE, "
                    + "ONE INTEGER, " + "TWO INTEGER, " + "THREE INTEGER, "
                    + "FOUR INTEGER, " + "FIVE INTEGER, " + "SIX INTEGER, "
                    + "SEVEN INTEGER, " + "EIGHT INTEGER, " + "NINE INTEGER, "
                    + "TEN INTEGER, " + "ELEVEN INTEGER, " + "TWELVE INTEGER, "
                    + "THIRTEEN INTEGER, " + "FOURTEEN INTEGER, "
                    + "FIVETEEN INTEGER, " + "SIXTEEN INTEGER, "
                    + "SEVENTEEN INTEGER, " + "EIGHTEEN INTEGER, "
                    + "NINETEEN INTEGER, " + "TWENTY INTEGER, " + "BULL INTEGER "
                    + ") ";
            stmt.executeUpdate(query);

        } catch (SQLException e) {
            if (!e.getSQLState().equals("X0Y32")) {
                e.printStackTrace();
            }
            System.out.println(tablename + " already exists");

        }
    }

    private void createTargetPracticeTable(String tablename) {
        if (conn == null) {
            createConnection();
            if (conn == null) {
                return;
            }
        }
        try {
            if (conn.isClosed()) {
                conn = createConnection();
            }
            Statement stmt = conn.createStatement();
            String query = "CREATE TABLE "+tablename+" ("
                    + "FIELDNAME VARCHAR(24) NOT NULL PRIMARY KEY, "
                    + "HITS INTEGER, " + "ROUNDS INTEGER";
            stmt.executeUpdate(query);

        } catch (SQLException e) {
            if (!e.getSQLState().equals("X0Y32")) {
                e.printStackTrace();
            }
            System.out.println(tablename + " already exists");

        }
    }

    public static void main(String[] args) {





	String alterTable = "ALTER TABLE RTWSIMPLE ADD CONSTRAINT RTWSIMPLE_PK Primary Key (GAMEID)";
	String answer;




    }

    // ## DERBY EXCEPTION REPORTING CLASSES ##
    /***
     * Exception reporting methods with special handling of SQLExceptions
     ***/
    static void errorPrint(Throwable e) {
	if (e instanceof SQLException)
	    SQLExceptionPrint((SQLException) e);
	else {
	    System.out.println("A non SQL error occured.");
	    e.printStackTrace();
	}
    } // END errorPrint

    // Iterates through a stack of SQLExceptions
    static void SQLExceptionPrint(SQLException sqle) {
	while (sqle != null) {
	    System.out.println("\n---SQLException Caught---\n");
	    System.out.println("SQLState:   " + (sqle).getSQLState());
	    System.out.println("Severity: " + (sqle).getErrorCode());
	    System.out.println("Message:  " + (sqle).getMessage());
	    sqle.printStackTrace();
	    sqle = sqle.getNextException();
	}
    } // END SQLExceptionPrint

    public static DataController getInstance() {
	if (instance_ == null) {
	    instance_ = new DataController();
	}
	return instance_;
    }

    public void addRTWGame(String gameName, List<Integer> values) {
	PreparedStatement pstmt = null;
	try {
	    if (conn == null) {
		conn = createConnection();
	    }
	    String query = "insert into "
		    + gameName
		    + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	    pstmt = conn.prepareStatement(query);
	    pstmt.setString(1, "Steffen");
	    pstmt.setInt(2, (int) System.currentTimeMillis());
	    pstmt.setDate(3, new Date(System.currentTimeMillis()));
	    int index = 4;
	    for (Integer integer : values) {
		pstmt.setInt(index, integer);
		index++;
	    }
	    pstmt.execute();
	} catch (SQLException e) {
	    e.printStackTrace();
	}

    }

    public List<Double> calculateGameStats(String gameName) {
	List<Double> stats = new ArrayList<Double>();
	int counter = 0;

	try {
	    if (conn == null) {
		conn = createConnection();
	    }
	    String query = "select * from " + gameName;
	    stmt = conn.createStatement();

	    ResultSet rs = stmt.executeQuery(query);
	    while (rs.next()) {

		for (int columnID = 4; columnID <= 24; columnID++) {

		    if (counter == 0) {
			stats.add(0.0 + rs.getInt(columnID));
		    }else{

			stats.set(columnID-4,stats.get(columnID-4) + rs.getInt(columnID));
		    }

		}
		counter++;
	    }

	    for (int i = 0; i < stats.size(); i++) {
		stats.set(i, stats.get(i)/counter);
	    }

	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return stats;
    }


    public static void saveTPGame(ArrayList<TargetHelper> targetHelperList) {
    }
}



