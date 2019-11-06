import java.awt.event.ActionEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JTextArea;

public class ClientController extends Thread {
	
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
    
    public void run() {
    	database.run();
    	/** Listen for client requests **/
    	while (true) {
    		try {
    			String response = "";
        		String request = inputFromClient.readUTF();
                String[] requestArray = request.split(",");
                
        		/** Handle client requests **/ 
                if (requestArray[0].equals("login")) {
                	respond("Login", "Signing in");
                	int uID = Integer.parseInt(requestArray[1]);
                	String uName = database.searchUser(uID);
                	if (uName == "") {
                		respond("Response", "Sign in failed!");
                		response = "Invalid UID";
                	} else {
            			response = uName;
            			respond("Response ", response + " signed in successfully!");
                	}
                } else if (requestArray[0].contentEquals("getAllStudents")) {
                	List<Student> studentList = database.getListStudents();
                	String students = encodeStudents(studentList);
                	respond("Startup", "Fetching students");
                	response = students;
                } else if (requestArray[0] == "search") {
                	
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
        display.append(type + ":" + new Date() + "\n" + message + ", From: "+ this.address + "\n");
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
