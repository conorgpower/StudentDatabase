import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	private static JLabel sID = new JLabel("Test");
	private static JLabel studIDLabel = new JLabel("Student ID:");
	private static JLabel studID = new JLabel("Test");
	private static JLabel fNameLabel = new JLabel("First Name:");
	private static JLabel fName = new JLabel("Test");
	private static JLabel sNameLabel = new JLabel("Surname:");
	private static JLabel sName = new JLabel("Test");
	
	/** UI for control screen **/
    private static JButton next = new JButton("Next");
    private static JButton previous = new JButton("Previous");
	private static JTextField searchField = new JTextField();
	private static JLabel searchError = new JLabel("");
    private static JButton searchButton = new JButton("Search");
    private static JButton clearStudent = new JButton("Clear");
    private static JButton logout = new JButton("Logout");
    
    /** UI for server response screen **/
    private static JTextArea serverResponse = new JTextArea("TESTING");
    
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
    	
    	serverScreen.add(serverResponse);
    	
    	serverResponse.setPreferredSize(new Dimension(500, 180));
    	
    	loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
            	System.out.println("Login Button Pressed");
            	loginError.setText("Login Button Pressed");
            }
        });
    	
    	next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
            	System.out.println("Next Button Pressed");
            }
        });
    	
    	previous.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
            	System.out.println("Previous Button Pressed");
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
    	
    	frame.add(loginScreen, BorderLayout.NORTH);
    	frame.add(mainScreen, BorderLayout.CENTER);
    	frame.add(serverScreen, BorderLayout.SOUTH);
    	frame.setLayout(new GridLayout(3,1));
    	frame.setVisible(true);
    }
    
}
