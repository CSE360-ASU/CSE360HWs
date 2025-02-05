package application;

import java.sql.SQLException;

import databasePart1.DatabaseHelper;
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
    private final DatabaseHelper databaseHelper;

    public AdminHomePage(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }
    public void show(Stage primaryStage) {
    	VBox layout = new VBox();
    	
	    layout.setStyle("-fx-alignment: center; -fx-padding: 20;");
	    
	    // label to display the welcome message for the admin
	    Label adminLabel = new Label("Hello, Admin!");
	    
	    adminLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

	    layout.getChildren().add(adminLabel);
	    Scene adminScene = new Scene(layout, 800, 400);
	    
	    //Fields to get username and role to add
        TextField userNameField = new TextField();
        userNameField.setPromptText("Enter userName");
        userNameField.setMaxWidth(250);

        TextField roleField = new TextField();
        roleField.setPromptText("Enter role");
        roleField.setMaxWidth(250);
        //button for adding the specified role to specified user
        //need to add way to 
        Button roleButton = new Button("Assign Role");
        roleButton.setOnAction(a -> {
        	//databaseHelper = new DatabaseHelper();
            String userName = userNameField.getText();
            String role = roleField.getText();
            try {
				databaseHelper.addUserRole(userName, role);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
        });
        layout.getChildren().addAll(roleButton, userNameField, roleField);
        

	    // Set the scene to primary stage
	    primaryStage.setScene(adminScene);
	    primaryStage.setTitle("Admin Page");
    }
}