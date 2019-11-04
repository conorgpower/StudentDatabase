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
	public String getsID() {
		return sID;
	}
	public String getStudID() {
		return studID;
	}
	public String getfName() {
		return fName;
	}
	public String getsName() {
		return sName;
	}
	
	// Setters
	public void setsID(String sID) {
		this.sID = sID;
	}
	public void setStudID(String studID) {
		this.studID = studID;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}	
}
