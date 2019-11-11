/**
 * @author 			Conor Power
 * Student Number: 	20075871
 * Subject: 		Distributed Systems
 * Course:			Applied Computing
 * Date:			07/11/19
 * 
 * Server Class:
 * One instance of the server class is created to handle all of the connection requests
 * through its server socket. The server listens for any connection requests to this 
 * socket constantly. When a request is accepted the server creates a new client 
 * controller to handle the new client.
 */

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import javax.swing.*;

public class Server {

	/** UI for server screen **/
	private static JTextArea display = new JTextArea();
	private static JScrollPane displayPane = new JScrollPane(display, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
																JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    private static JButton clearServer = new JButton("Clear");
    private static JButton stopServer = new JButton("Stop");
    
    public static void main(String[] args) {
    	Server server = new Server();
    	server.runServer();
    }
    
    /** Server logic and UI **/
    private void runServer() {
    	/** Setup UI for server **/
    	JFrame frame = new JFrame("Student Database Server");
    	frame.setLayout(new FlowLayout());
    	frame.add(displayPane);
    	frame.add(clearServer);
    	frame.add(stopServer);
    	frame.setSize(500, 500);
    	frame.setVisible(true);
    	
    	
    	displayPane.setPreferredSize(new Dimension(500, 400));
    	display.setEditable(false);
    	clearServer.setPreferredSize(new Dimension(240, 50));
    	stopServer.setPreferredSize(new Dimension(240, 50));
    	
    	clearServer.addActionListener(actionEvent -> display.setText(""));
    	
    	stopServer.addActionListener(actionEvent -> System.exit(1));
    	
    	/** Server logic **/
    	try {
    		/** Create server socket **/
            ServerSocket serverSocket = new ServerSocket(8000);
            display.append("Server started at" + new Date() + "\n");

            /** Infinite loop listening for requests **/
            while (true) {

                /** Listen for clients trying to connect
                 *  Assign newSocket to the request **/
                Socket newSocket = serverSocket.accept();

                /** On request create a thread to handle the client **/
                ClientController newClient = new ClientController(newSocket, display);
                newClient.start();
                display.append(new Date() + "\n" + "Request: New Client at " + newSocket.toString() + "\n");
            }
        } catch (Exception e){
            System.out.println(e);
        }
    }
}
