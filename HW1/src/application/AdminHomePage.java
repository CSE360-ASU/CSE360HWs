package application;

import java.util.ArrayList;

import databasePart1.DatabaseHelper;

import java.sql.*;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * AdminPage class represents the user interface for the admin user.
 * This page displays a simple welcome message for the admin.
 */

public class AdminHomePage {
	/**
     * Displays the admin page in the provided primary stage.
     * @param primaryStage The primary stage where the scene will be displayed.
     */
    public void show(Stage primaryStage) {
    	VBox layout = new VBox();
    	
	    layout.setStyle("-fx-alignment: center; -fx-padding: 20;");
	    
	    // label to display the welcome message for the admin
	    Label adminLabel = new Label("Hello, Admin!");
	    
	    adminLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
	    
	    // button that lists all users when pressed
	    Button listButton = new Button("List Users");
	    
	    listButton.setOnAction(a -> {
        	ArrayList<String> usernames;
        	ArrayList<String> passwords;
        	ArrayList<String> roles;
			try {
				usernames = getUsernames();
				passwords = getPasswords();
				roles = getRoles();
				String result = "";
				for(int i = 0; i<usernames.size(); i++) {
	        		result += "Username: " + usernames.get(i) + " Password: " + passwords.get(i) + " Role: " + roles.get(i) + "\n";
	        	}
				adminLabel.setText(result);
			} catch (SQLException e) {
				e.printStackTrace();
			}
        });
	    
	    layout.getChildren().addAll(adminLabel, listButton);
	    Scene adminScene = new Scene(layout, 800, 400);

	    // Set the scene to primary stage
	    primaryStage.setScene(adminScene);
	    primaryStage.setTitle("Admin Page");
    }
    
    /**
     * Returns an ArrayList containing all usernames stored in the database
     */
    private ArrayList<String> getUsernames() throws SQLException{
    	ArrayList<String> userlist = new ArrayList<String>();
    	DatabaseHelper db = new DatabaseHelper();
    	String query = "SELECT userName FROM cse360users";
    	try(Connection connection = db.connectToDatabase();
    		Statement statement = connection.createStatement();
    		ResultSet result = statement.executeQuery(query);){
    		
    		
    		while(result.next()) {
    			userlist.add(result.getString(1));
    		}
    		
    		return userlist;
    	}
    	catch(SQLException e) {
    		System.err.println(e.getMessage());
    		return userlist;
    	}
    }
    
    /**
     * Returns an ArrayList containing all passwords for each user stored in the database
     */
    private ArrayList<String> getPasswords() throws SQLException{
    	ArrayList<String> passlist = new ArrayList<String>();
    	DatabaseHelper db = new DatabaseHelper();
    	String query = "SELECT password FROM cse360users";
    	try(Connection connection = db.connectToDatabase();
    		Statement statement = connection.createStatement();
    		ResultSet result = statement.executeQuery(query);){
    		
    		
    		while(result.next()) {
    			passlist.add(result.getString(1));
    		}
    		
    		return passlist;
    	}
    	catch(SQLException e) {
    		System.err.println(e.getMessage());
    		return passlist;
    	}
    }
    
    /**
     * Returns an ArrayList containing all roles for each user stored in the database
     */
    private ArrayList<String> getRoles() throws SQLException{
    	ArrayList<String> rolelist= new ArrayList<String>();
    	DatabaseHelper db = new DatabaseHelper();
    	String query = "SELECT role FROM cse360users";
    	try(Connection connection = db.connectToDatabase();
    		Statement statement = connection.createStatement();
    		ResultSet result = statement.executeQuery(query);){
    		
    		
    		while(result.next()) {
    			rolelist.add(result.getString(1));
    		}
    		
    		return rolelist;
    	}
    	catch(SQLException e) {
    		System.err.println(e.getMessage());
    		return rolelist;
    	}
    }
}