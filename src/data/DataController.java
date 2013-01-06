package data;

import app.RTWStrategy;
import app.TargetHelper;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class DataController {

    static String driver = "org.apache.derby.jdbc.EmbeddedDriver";

    static String dbName = "swdarts_dev";

    static String connectionURL = "jdbc:derby:" + dbName + ";create=true";
    static Connection conn = null;
    static Statement stmt;
    private static DataController instance_;
    private Set<String> tpSet = new TreeSet<String>();
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
        createTPTable();
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

    private void createTPTable() {
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
            String query = "CREATE TABLE TargetPractice ("
                    + "GAMEID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " + "GAMEDATE DATE, "
                    + "ONE DOUBLE, " + "TWO DOUBLE, " + "THREE DOUBLE, "
                    + "FOUR DOUBLE, " + "FIVE DOUBLE, " + "SIX DOUBLE, "
                    + "SEVEN DOUBLE, " + "EIGHT DOUBLE, " + "NINE DOUBLE, "
                    + "TEN DOUBLE, " + "ELEVEN DOUBLE, " + "TWELVE DOUBLE, "
                    + "THIRTEEN DOUBLE, " + "FOURTEEN DOUBLE, "
                    + "FIVETEEN DOUBLE, " + "SIXTEEN DOUBLE, "
                    + "SEVENTEEN DOUBLE, " + "EIGHTEEN DOUBLE, "
                    + "NINETEEN DOUBLE, " + "TWENTY DOUBLE, " + "BULL DOUBLE, CONSTRAINT primary_key PRIMARY KEY (GAMEID) "
                    + ") ";
            stmt.executeUpdate(query);

        } catch (SQLException e) {
            if (!e.getSQLState().equals("X0Y32")) {
                e.printStackTrace();
            }


        }
    }
    public void saveTPGame(ArrayList<TargetHelper> targetHelperList) {
        PreparedStatement pstmt = null;
        tpSet.add("1");
        tpSet.add("2");
        tpSet.add("3");
        tpSet.add("4");
        tpSet.add("5");
        tpSet.add("6");
        tpSet.add("7");
        tpSet.add("8");
        tpSet.add("9");
        tpSet.add("10");
        tpSet.add("11");
        tpSet.add("12");
        tpSet.add("13");
        tpSet.add("14");
        tpSet.add("15");
        tpSet.add("16");
        tpSet.add("17");
        tpSet.add("18");
        tpSet.add("19");
        tpSet.add("20");
        tpSet.add("Bull");

        try {
            if (conn == null) {
                conn = createConnection();
            }
            String query = "insert into TargetPractice " +
                    "(GAMEDATE,ONE,TWO,THREE,FOUR,FIVE,SIX,SEVEN,EIGHT,NINE,TEN,ELEVEN,TWELVE,THIRTEEN,FOURTEEN,FIVETEEN,SIXTEEN,SEVENTEEN,EIGHTEEN,NINETEEN,TWENTY,BULL)"
                    + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(query);
            pstmt.setDate(1, new Date(System.currentTimeMillis()));
            for (TargetHelper targetHelper : targetHelperList) {
                 String target = targetHelper.getTarget();
                 tpSet.remove(target);
                 int param;
                 param = determineParam(target);
                 pstmt.setDouble(param,targetHelper.getHpr());
            }
            for (String s : tpSet) {
                  pstmt.setNull(determineParam(s), Types.DOUBLE);
            }
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int determineParam(String target) {
        if(target.equals("Bull")){
            return 22;

        }else{
           return Integer.parseInt(target)+1;
        }
    }
}



