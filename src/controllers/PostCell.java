package controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.NamingException;

import entity.Post;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class PostCell {

    @FXML
    private AnchorPane ap;

    @FXML
    private Pane sq;

    @FXML
    private Label name;

    @FXML
    private Label type;


    @FXML
    private Label id;

    @FXML
    private Rectangle rectangle;

    @FXML
    private ImageView iamge;

    @FXML
    private Label date;
    
    public void initialize(URL url, ResourceBundle rb) throws NamingException {
    	
    }
    
    
    public void remplir(Post p ){
    	
    	name.setText(p.getTitle());
    	type.setText(p.getCategory());
    	date.setText(p.getDate().toString());
    	id.setText(String.valueOf(p.getId()));
        Image imageURIuser = new Image("file:C:\\wamp\\www\\images\\" + p.getImage());
        
        iamge.setImage(imageURIuser);
        
    	
    }
    
    @FXML
    void goToDetail(MouseEvent event) throws NumberFormatException, SQLException  {
    	
    	 try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/PostDetail.fxml"));
           
             Parent root = loader.load();
             System.out.println("aaaa"+id.getText());
             PostDetailController pdc = loader.getController();
             pdc.remplir(Integer.valueOf(id.getText()));
             iamge.getScene().setRoot(root);
            
         } catch (IOException ex) {
             Logger.getLogger(AddPostController.class.getName()).log(Level.SEVERE, null, ex);
         } 

    	
    }

}
