package application;
	
import java.io.IOException;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	public static entity.User user = new entity.User(1, "Sana Chadly");
	
	@Override
	public void start(Stage primaryStage) {
		
		   Parent root;
		try {
			
			root = FXMLLoader.load(getClass().getResource("/views/AddPost.fxml"));
			Scene scene = new Scene(root);
	      
	        primaryStage.setWidth(1300);
	        primaryStage.setHeight(768);
	        primaryStage.setScene(scene);
	            
	        
	        primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	        
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
