public class Student {
	
	private String sID;
	private String studID;
	private String fName;
	private String sName;
	
	// Default constructor
	public Student(String sID, String studID, String fName, String sName) {
		this.sID = sID;
		this.studID = studID;
		this.fName = fName;
		this.sName = sName;
	}
	
	// Getters
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
	
	// Setters
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
