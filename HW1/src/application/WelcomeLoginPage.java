package application;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.application.Platform;

import java.util.Set;

import databasePart1.*;

/**
 * The WelcomeLoginPage class displays a welcome screen for authenticated users.
 * It allows users to navigate to their respective pages based on their role or quit the application.
 */
public class WelcomeLoginPage {
	
	private final DatabaseHelper databaseHelper;

    public WelcomeLoginPage(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }
    public void show( Stage primaryStage, User user) {
    	
    	VBox layout = new VBox(5);
	    layout.setStyle("-fx-alignment: center; -fx-padding: 20;");
	    
	    Label welcomeLabel = new Label("Welcome!!");
	    welcomeLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
	    
	    // Button to navigate to the user's respective page based on their role
	    Button continueButton = new Button("Continue to your Page");
	    continueButton.setOnAction(a -> {
	    	Set<String> role =user.getRole();
	    	//updated for multiple user roles, if a user has only 1 role (start counting from 0, they go to that page, otherwise to a role selection page
	    	if (role.size() == 1) {
	    		System.out.println("first if activated for role.size() == 1");
		    	if(role.contains("admin")) {
		    		new AdminHomePage(databaseHelper).show(primaryStage);
		    	}
		    	else if(role.contains("user")) {
		    		new UserHomePage().show(primaryStage);
		    	}
	    	}
	    	// user has > 1 role, go to role selection page (needs adjustment)
	    	else {
	    		System.out.println("Else activated");
	    		new RoleSelectPage(databaseHelper).show(primaryStage, user);
	    	}
	    });
	    
	    // Button to quit the application
	    Button quitButton = new Button("Quit");
	    quitButton.setOnAction(a -> {
	    	databaseHelper.closeConnection();
	    	Platform.exit(); // Exit the JavaFX application
	    });
	    
	    // "Invite" button for admin to generate invitation codes
//	    if ("admin".equals(user.getRole())) {
	    if (user.getRole().contains("admin")) {	    //updated the role check to work with multiple user roles
            Button inviteButton = new Button("Invite");
            inviteButton.setOnAction(a -> {
                new InvitationPage().show(databaseHelper, primaryStage);
            });
            layout.getChildren().add(inviteButton);
        }

	    layout.getChildren().addAll(welcomeLabel,continueButton,quitButton);
	    Scene welcomeScene = new Scene(layout, 800, 400);

	    // Set the scene to primary stage
	    primaryStage.setScene(welcomeScene);
	    primaryStage.setTitle("Welcome Page");
    }
}