/**
 * @author 			Conor Power
 * Student Number: 	20075871
 * Subject: 		Distributed Systems
 * Course:			Applied Computing
 * Date:			07/11/19
 * 
 * User Class:
 * The user object is used by the database interactions to verify
 * login uID's and to return the user name for display.
 */

public class User {
	
	/** User fields **/
	private int uID;
	private String uName;
	
	/** Default constructor **/
	public User(int uID, String uName) {
		this.uID = uID;
		this.uName = uName;
	}
	
	/** Getters **/
	public int getUID() {
		return uID;
	}
	public String getUName() {
		return uName;
	}
	
	/** Setters **/
	public void setUID(int uID) {
		this.uID = uID;
	}
	
	public void setUName(String uName) {
		this.uName = uName;
	}
}