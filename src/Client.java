import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;

public class Client {
	/** Panels for each UI component **/
    private static JPanel loginScreen = new JPanel();
    private static JPanel mainScreen = new JPanel();
    private static JPanel studentScreen = new JPanel();
    private static JPanel controlScreen = new JPanel();
    private static JPanel serverScreen = new JPanel();
	
	/** UI for login screen **/
	private static JLabel loginTitle = new JLabel("Login Screen");
	private static JLabel userIdLabel = new JLabel("User ID:");
	private static JTextField userIdField = new JTextField();
	private static JLabel loginError = new JLabel("");
    private static JButton loginButton = new JButton("Login");
    
    /** UI for student view **/
	private static JLabel sIDLabel = new JLabel("SID:");
	private static JLabel sID = new JLabel("");
	private static JLabel studIDLabel = new JLabel("Student ID:");
	private static JLabel studID = new JLabel("");
	private static JLabel fNameLabel = new JLabel("First Name:");
	private static JLabel fName = new JLabel("");
	private static JLabel sNameLabel = new JLabel("Surname:");
	private static JLabel sName = new JLabel("");
	
	/** UI for control screen **/
    private static JButton next = new JButton("Next");
    private static JButton previous = new JButton("Previous");
	private static JTextField searchField = new JTextField();
	private static JLabel searchError = new JLabel("");
    private static JButton searchButton = new JButton("Search");
    private static JButton clearStudent = new JButton("Clear");
    private static JButton logout = new JButton("Logout");
    
    /** UI for server response screen **/
    private static JTextArea serverResponse = new JTextArea();
    private static JButton clearDisplay = new JButton("Clear");
	private static JScrollPane displayPane = new JScrollPane(serverResponse, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    
    /** Database connection fields **/
    private Socket socket;
    private DataOutputStream toServer;
    private DataInputStream fromServer;
    
    /** Database fields **/
    private ArrayList<Student> studentList;
    private int displayIndex = 0;
    
    public static void main(String[] args) {
    	Client client = new Client();
    	client.createInterface();
    }
    
    /** Create user interface components **/
    private void createInterface() {
    	JFrame frame = new JFrame("Student Database");
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(500, 600);
    	frame.setLayout(null);
    	
    	loginScreen.setSize(500, 120);
    	loginScreen.setLayout(new FlowLayout());
    	mainScreen.setSize(500, 360);
    	mainScreen.setLayout(new GridLayout(1, 2));
    	mainScreen.add(studentScreen);
    	mainScreen.add(controlScreen);
    	studentScreen.setSize(250, 360);
    	studentScreen.setLayout(new FlowLayout());
    	controlScreen.setSize(250, 360);
    	controlScreen.setLayout(new FlowLayout());
    	serverScreen.setSize(500, 120);
    	
    	loginScreen.add(loginTitle);
    	loginScreen.add(userIdLabel);
    	loginScreen.add(userIdField);
    	loginScreen.add(loginError);
    	loginScreen.add(loginButton);
    	
    	loginTitle.setPreferredSize(new Dimension(800, 20));
        loginTitle.setHorizontalAlignment(JLabel.CENTER);
    	userIdLabel.setPreferredSize(new Dimension(100, 20));
    	userIdLabel.setHorizontalAlignment(JLabel.RIGHT);
    	userIdField.setPreferredSize(new Dimension(200, 20));
    	userIdField.setHorizontalAlignment(JTextField.LEFT);
    	loginError.setPreferredSize(new Dimension(800, 20));
    	loginError.setHorizontalAlignment(JLabel.CENTER);
    	loginButton.setPreferredSize(new Dimension(100, 20));
    	loginError.setHorizontalAlignment(JButton.CENTER);
    	
    	studentScreen.add(sIDLabel);
    	studentScreen.add(sID);
    	studentScreen.add(studIDLabel);
    	studentScreen.add(studID);
    	studentScreen.add(fNameLabel);
    	studentScreen.add(fName);
    	studentScreen.add(sNameLabel);
    	studentScreen.add(sName);
    	
    	sIDLabel.setPreferredSize(new Dimension(90, 20));
        sID.setPreferredSize(new Dimension(120, 20));
        studIDLabel.setPreferredSize(new Dimension(90, 20));
        studID.setPreferredSize(new Dimension(120, 20));
        fNameLabel.setPreferredSize(new Dimension(90, 20));
        fName.setPreferredSize(new Dimension(120, 20));
        sNameLabel.setPreferredSize(new Dimension(90, 20));
        sName.setPreferredSize(new Dimension(120, 20));
        
        controlScreen.add(next);
        controlScreen.add(previous);
        controlScreen.add(searchField);
       
        controlScreen.add(searchField);
        controlScreen.add(searchButton);
        controlScreen.add(searchError);
        controlScreen.add(clearStudent);
        controlScreen.add(logout);
        
        next.setPreferredSize(new Dimension(120, 20));
        previous.setPreferredSize(new Dimension(120, 20));
        searchField.setPreferredSize(new Dimension(150, 20));
        searchButton.setPreferredSize(new Dimension(90, 20));
        searchError.setPreferredSize(new Dimension(240, 20));
        clearStudent.setPreferredSize(new Dimension(240, 20));
        logout.setPreferredSize(new Dimension(240, 20));
    	
    	serverScreen.add(displayPane);
    	serverScreen.add(clearDisplay);
    	
    	displayPane.setPreferredSize(new Dimension(500, 145));
    	serverResponse.setEditable(false);
    	clearDisplay.setPreferredSize(new Dimension(500, 30));
    	
    	loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
            	login(userIdField.getText());
            }
        });
    	
    	next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
            	getDisplayStudent("next");
            }
        });
    	
    	previous.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
            	getDisplayStudent("previous");
            }
        });
    	
    	searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
            	System.out.println("Search Button Pressed");
            	searchError.setText("Search Button Pressed");
            }
        });
    	
    	clearStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
            	System.out.println("Clear Button Pressed");
            }
        });
    	
    	logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
            	System.out.println("Logout Button Pressed");
            }
        });
    	
    	clearDisplay.addActionListener(actionEvent -> serverResponse.setText(""));
    	
    	frame.add(loginScreen, BorderLayout.NORTH);
    	frame.add(mainScreen, BorderLayout.CENTER);
    	frame.add(serverScreen, BorderLayout.SOUTH);
    	frame.setLayout(new GridLayout(3,1));
    	frame.setVisible(true);
    	
    	mainScreen.setVisible(false);
    }
    
    private void displayResponse(String response){
        serverResponse.append(new Date() + "\n" + response + ", From: " + socket.getInetAddress() + "\n");
    }
    
    private void login(String uID) {
    	String response = "";
    	
    	if (uID.equals("")) {
    		loginError.setText("Please Enter a UID!");
    	} else {
    		Boolean isValid = connectToServer();
    		
    		if(isValid) {
    			try {
    				toServer.writeUTF("login," + uID);
                    toServer.flush();
                    response = fromServer.readUTF();
                    
                    if (!response.equals("Invalid UID")) {
                    	this.getStudentData();
                    	loginScreen.setVisible(false);
                    	mainScreen.setVisible(true);
                    	displayResponse("Welcome " + response);
                    } else {
                    	loginError.setText(response);
                    	displayResponse("Invalid UID try again");
                    }
    			} catch (Exception e) {
    				System.err.println(e);
    			}
    		}
    	}
    }
    
    Boolean connectToServer() {
    	try {
    		socket = new Socket("localhost", 8000);
            fromServer = new DataInputStream(socket.getInputStream());
            toServer = new DataOutputStream(socket.getOutputStream());
            return true;
    	} catch (Exception e) {
    		System.out.println(e);
    		serverResponse.append("Error Connecting to Server\n");
            loginError.setText("Error Connecting to Server");
            return false;
    	}
    }
    
    private void getStudentData() {
        try {
            toServer.writeUTF("getAllStudents");
            toServer.flush();
            String response = fromServer.readUTF();
            String[] studentData = response.split(",");

            if(studentData[0].equals("students")){
                displayResponse("Fetching Students from Database");
                studentList = decodeUserData(studentData);
                getDisplayStudent("init");
            } else{
                displayResponse("Error Getting all Student Data");
            }
        } catch (java.net.SocketException e){
            displayResponse("Connection to Database Lost");
        }catch (Exception e){
            System.out.println(e);
            displayResponse("Error Getting Student Data");
        }
    }
    
    private void getDisplayStudent(String option) {
        if(option.equals("init")){
            displayIndex = 0;
        } else if(option.equals("next")){
            if(displayIndex == studentList.size() - 1){
            	String message = "Error!\nNo next student in list!\n";
		        JOptionPane.showMessageDialog(null, message, "ERROR", JOptionPane.ERROR_MESSAGE);
            } else {
                displayIndex += 1;
            }
        } else if(option.equals("previous")){
            if(displayIndex == 0){
            	String message = "Error!\nNo previous student in list!\n";
		        JOptionPane.showMessageDialog(null, message, "ERROR", JOptionPane.ERROR_MESSAGE);
            } else {
                displayIndex -= 1;
            }
        }
        displayStudent(studentList.get(displayIndex));
    }
    
    /** Display student on UI **/
    private void displayStudent(Student student) {
        sID.setText(student.getSID());
        studID.setText(student.getStudID());
        fName.setText(student.getFName());
        sName.setText(student.getSName());
    }
    
    
    private ArrayList<Student> decodeUserData(String[] students){

        ArrayList<Student> studentList = new ArrayList<Student>();

        for(int i = 1; i < students.length; i += 4){
            String sid = students[i];
            String stud_id = students[i+1];
            String fname = students[i+2];
            String sname= students[i+3];
            Student student = new Student(sid, stud_id, fname, sname);
            studentList.add(student);
        }
        return studentList;
    }
}
