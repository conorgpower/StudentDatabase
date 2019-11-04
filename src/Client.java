import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Client {
	// Panels for each UI component
    private static JPanel loginScreen = new JPanel();
	
	// UI for login screen
	private static JLabel loginTitle = new JLabel("Login Screen");
	private static JLabel userIdLabel = new JLabel("User ID:");
	private static JTextField userIdField = new JTextField();
	private static JLabel loginError = new JLabel("");
    private static JButton loginButton = new JButton("Login");
    
    public static void main(String[] args) {
    	Client client = new Client();
    	client.createInterface();
    }
    
    // Create user interface components
    private void createInterface() {
    	JFrame frame = new JFrame("Student Database");
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(800, 600);
    	frame.setLayout(null);
    	
    	loginScreen.setSize(800, 120);

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
    	
    	loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
            	System.out.println("Login Button Pressed");
            	loginError.setText("Login Button Pressed");
            }
        });
    	
    	frame.add(loginScreen);
    	frame.setVisible(true);
    }
    
}
