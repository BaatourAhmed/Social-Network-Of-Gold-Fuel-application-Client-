package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import entity.Post;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import services.GestionPost;


public class ListPostController  implements Initializable{

    @FXML
    private AnchorPane ap;

    @FXML
    private ScrollPane pane;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	
    	GestionPost gp = new GestionPost();
    	List<Post> list = gp.FindAllPosts();
    	
		
			 TilePane b = new TilePane();
		        b.setPadding(new javafx.geometry.Insets(30));
		        TilePane c = new TilePane();
    	
		
		for(Post p :list){
			
			 try {
				 
		        	FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/PostCell.fxml"));
					Parent root = (Pane) loader.load();
					PostCell DHC = loader.getController();
	                DHC.remplir(p);
	                c.getChildren().removeAll();
	                
	                
	                c.getChildren().add(root);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 
			 
		}
		   c.setPrefColumns(3);
	        c.setPadding(new javafx.geometry.Insets(0));
	        c.setHgap(10);
	        c.setVgap(80);
	        b.getChildren().add(c);
	        b.setPrefWidth(1000);
	        pane.setContent(b);
	        
	        
	        
		} 
       
        
       
    

}
