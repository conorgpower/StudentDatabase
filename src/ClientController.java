import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Date;
import java.util.List;

import javax.swing.JTextArea;

public class ClientController extends Thread {
	
	/** Client Controller fields **/
	private Socket socket;
    private InetAddress address;
    private DataInputStream inputFromClient;
    private DataOutputStream outputToClient;
    private JTextArea display;
    private JDBC database;
	
    /** Default constructor **/
    public ClientController(Socket socket, JTextArea display) throws IOException {
		this.socket = socket;
		this.address = socket.getInetAddress();
		this.inputFromClient = new DataInputStream(socket.getInputStream());
		this.outputToClient = new DataOutputStream(socket.getOutputStream());
		this.display = display;
		this.database = new JDBC();
	}
    
    /** Run method for thread **/
    public void run() {
    	/** Initialise database **/
    	database.run();
    	/** Constantly listens for client requests **/
    	while (true) {
    		try {
    			String response = "";
        		/** Assign client request to string **/
    			String request = inputFromClient.readUTF();
    			/** Split request into string array, separated by commas **/ 
                String[] requestArray = request.split(",");
                
        		/** Handle client requests **/ 
                if (requestArray[0].equals("login")) {
                	respond("Login", "Signing in");
                	int uID;
                	try {
                		uID = Integer.parseInt(requestArray[1]);
                		String uName = database.searchUser(uID);
                    	if (uName == "") {
                    		respond("Response", "Sign in failed!");
                    		response = "Invalid UID";
                    	} else {
                			response = uName;
                			respond("Response ", response + " signed in successfully!");
                    	}
                	} catch (Exception e) {
                		respond("Response", "Sign in failed!");
                		response = "Invalid UID";
                	}
                	
                } else if (requestArray[0].equals("getAllStudents")) {
                	List<Student> studentList = database.getListStudents();
                	String students = encodeStudents(studentList);
                	respond("Seeding", "Fetching students");
                	response = students;
                } else if (requestArray[0].contentEquals("search")) {
                	if (requestArray[1].equals("")) {
                		respond("Response", "Search failed!");
                		response = "Invalid Surname";
                	} else {
                		List<Student> studentList = database.searchStudent(requestArray[1]);
                    	String students = encodeStudents(studentList);
                    	respond("Response", "Searching for surname: " + requestArray[1]);
                    	response = students;
                	}
                }
                // Send the response to the client
                outputToClient.writeUTF(response);
    		} catch (IOException ex) {
                respond("Closing","Connection Closed");
                this.interrupt();
            } catch (Exception e) {
    			System.out.println(e);
    		}
    	}
    }
    
    /** Write action server display **/
    private void respond(String type, String message) {
        display.append(type + ":" + new Date() + "\n" + message + ", From: "+ this.address + "\n\n");
    }
    
    private String encodeStudents(List<Student> students) {
    	try {
    		String studentsString = "students,";
    		for (int i = 0; i < students.size(); i++) {
    			studentsString += students.get(i).getSID() + ",";
    			studentsString += students.get(i).getStudID() + ",";
    			studentsString += students.get(i).getFName() + ",";
    			studentsString += students.get(i).getSName() + ",";
    		}
    		return studentsString;
    	} catch (Exception e) {
    		System.err.println(e);
    		return null;
    	}
    }
}
