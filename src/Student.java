/**
 * @author 			Conor Power
 * Student Number: 	20075871
 * Subject: 		Distributed Systems
 * Course:			Applied Computing
 * Date:			07/11/19
 * 
 * Student Class:
 * The student object is used by the database, client and client controller
 * whenever students from database interactions are being stored or accessed.
 */

public class Student {
	
	/** Student fields **/
	private String sID;
	private String studID;
	private String fName;
	private String sName;
	
	/** Default constructor **/
	public Student(String sID, String studID, String fName, String sName) {
		this.sID = sID;
		this.studID = studID;
		this.fName = fName;
		this.sName = sName;
	}
	
	/** Getters **/
	public String getSID() {
		return sID;
	}
	public String getStudID() {
		return studID;
	}
	public String getFName() {
		return fName;
	}
	public String getSName() {
		return sName;
	}
	
	/** Setters **/
	public void setSID(String sID) {
		this.sID = sID;
	}
	public void setStudID(String studID) {
		this.studID = studID;
	}
	public void setFName(String fName) {
		this.fName = fName;
	}
	public void setSName(String sName) {
		this.sName = sName;
	}	
}
