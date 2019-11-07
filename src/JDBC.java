import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class JDBC {
	
	/** ArrayList for storing all students **/
	private List<Student> studentList = new ArrayList<Student>();
	
	/** The local record of the SQL table **/
	private ResultSet resultSet;

	/** The count for navigating the ResultSet object **/
	private int count = 0;

	/** Checks if connection is establishing for first time **/
	public Boolean firstRun = true;

	/** The name of the MySQL account to use (or empty for anonymous) */
	private final String userName = "root";

	/** The name of the computer running MySQL */
	private final String serverName = "localhost";

	/** The port of the MySQL server (default is 3306) */
	private final int portNumber = 3306;

	/** The name of the database we are using */
	private final String dbName = "Assign2";
	
	/** Getters **/
	private ResultSet getResultSet() {
		return resultSet;
	}
	
	private int getCount() {
		return count;
	}
	
	public Boolean getFirstRun() {
		return firstRun;
	}

	public List<Student> getStudentList() {
		return studentList;
	}

	/** Setters **/
	private void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}
	
	private void setCount(int count) {
		this.count = count;
	}
	
	public void setFirstRun(Boolean firstRun) {
		this.firstRun = firstRun;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}

	/**
	 * Get a new database connection
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {
		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", this.userName);
		conn = DriverManager.getConnection("jdbc:mysql://"
				+ this.serverName + ":" + this.portNumber + "/" + this.dbName,
				connectionProps);
		return conn;
	}
	
	/** Establish database connection **/
	public void run() {
		// Connect to MySQL
		try {
			getConnection();
			System.out.println("Connected to database");
			setStudentList(getListStudents());
		} catch (SQLException e) {
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
			return;
		}
	}
	
	/** Search for user by uID **/
	public String searchUser(int uID) {
		try {
			Statement s = this.getConnection().createStatement();
			s.executeQuery("SELECT * FROM users WHERE UID = "+ uID );
			setResultSet(s.getResultSet());
			User user = null;
			while (getResultSet().next()) {
				String uName = getResultSet().getString("UNAME");
				user = new User(uID, uName);
			}
			getResultSet().close();
			setCount(0);
			s.close();
			if (user != null) {
				return user.getUName();
			} else {
				return "";
			}
		} catch (SQLException e) {
			System.err.println ("Error message: "+ e.getMessage());
			System.err.println ("Error number: " + e.getErrorCode());
			return "";
		}
	}
	
	/** Method for getting all student table entries and returning them in the ArrayList studentList **/
	public List<Student> getListStudents() {
		try {
			Statement s = this.getConnection().createStatement();
			s.executeQuery("SELECT * FROM students");
			setResultSet(s.getResultSet());
			studentList.clear();
			while (getResultSet().next()) {
				String sID = getResultSet().getString("SID");
				String studID = getResultSet().getString("STUD_ID");
				String fName = getResultSet().getString("FNAME");
				String sName = getResultSet().getString("SNAME");
				Student student = new Student(sID, studID, fName, sName);
				studentList.add(student);
			}
			setCount(0);
			getResultSet().close();
			s.close();
			return studentList;
		} catch (SQLException e) {
			System.err.println ("Error message: "+ e.getMessage());
			System.err.println ("Error number: " + e.getErrorCode());
			return null;
		}
	}
	
	/** Search for student by surname **/
	public List<Student> searchStudent(String surnameSearch) {
		try {
			Statement s = this.getConnection().createStatement();
			s.executeQuery("SELECT * FROM students WHERE SNAME = '" + surnameSearch + "'");
			setResultSet(s.getResultSet());
			studentList.clear();
			while (getResultSet().next()) {
				String sID = getResultSet().getString("SID");
				String studID = getResultSet().getString("STUD_ID");
				String fName = getResultSet().getString("FNAME");
				String sName = getResultSet().getString("SNAME");
				Student student = new Student(sID, studID, fName, sName);
				studentList.add(student);
			}
			getResultSet().close();
			setCount(0);
			s.close();
			return studentList;
		} catch (SQLException e) {
			System.err.println ("Error message: "+ e.getMessage());
			System.err.println ("Error number: " + e.getErrorCode());
			return null;
		}
	}
	
	/** Method to navigate to next student **/
	public Student nextStudent() {
		Student student = null;
		try {
			student = studentList.get(getCount());
			setCount(getCount()+1);
		} catch (Exception e) {
			System.err.println ("Error message: "+ e.getMessage());
		}
		return student;
	}
	
	/** Method to navigate to previous student **/
	public Student previousStudent() {
		Student student = null;
		try {
			student = studentList.get(getCount()-1);
			setCount(getCount()-1);
		} catch (Exception e) {
			System.err.println ("Error message: "+ e.getMessage());
		}
		return student;
	}
}