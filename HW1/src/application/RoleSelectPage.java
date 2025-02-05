package application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Set;

/**
 * This page displays all the roles available to the user.
 * These are all the roles: Admin, Student, Reviewer, Instructor, Staff
 */

public class RoleSelectPage {

    public void show(Stage primaryStage, User user) {
    	VBox layout = new VBox();
	    layout.setStyle("-fx-alignment: center; -fx-padding: 20;");
	    
	    // Label to display page instructions
	    Label userLabel = new Label("Hello, please select the role you wish to use");
	    userLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

	    layout.getChildren().add(userLabel);
	    Scene userScene = new Scene(layout, 800, 400);
	    
        // Checks user roles and displays a corresponding button for each
	    // Un implemented user roles have their actions commented out, because those pages don't exist yet
        Set<String> roles = user.getRole();
        if (roles.contains("admin")) {
            Button adminButton = new Button("Admin");
            adminButton.setOnAction(a -> new AdminHomePage(null).show(primaryStage));
            layout.getChildren().add(adminButton);
        }
        if (roles.contains("student")) {
            Button studentButton = new Button("Student");
            //studentButton.setOnAction(a -> new StudentHomePage().show(primaryStage));
            layout.getChildren().add(studentButton);
        }
        if (roles.contains("reviewer")) {
            Button reviewerButton = new Button("Reviewer");
            //reviewerButton.setOnAction(a -> new ReviewerHomePage().show(primaryStage));
            layout.getChildren().add(reviewerButton);
        }
        if (roles.contains("instructor")) {
            Button instructorButton = new Button("Instructor");
            //instructorButton.setOnAction(a -> new InstructorHomePage().show(primaryStage));
            layout.getChildren().add(instructorButton);
        }
        if (roles.contains("staff")) {
            Button staffButton = new Button("Staff");
            //staffButton.setOnAction(a -> new StaffHomePage().show(primaryStage));
            layout.getChildren().add(staffButton);
        }
        primaryStage.setScene(userScene);
        primaryStage.setTitle("Role Select Page");
    	
    }
}