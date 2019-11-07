
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
