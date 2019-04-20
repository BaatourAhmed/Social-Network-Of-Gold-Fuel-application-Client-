package controllers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.RandomStringUtils;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;


import application.Main;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entity.CategoryName;
import entity.Post;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import services.GestionPost;

public class AddPostController implements Initializable {
	@FXML
    private FontAwesomeIconView souscatww;

	 @FXML
	    private JFXComboBox<String> category;

	    @FXML
	    private FontAwesomeIconView souscatw;

	    @FXML
	    private DatePicker date;

	    @FXML
	    private FontAwesomeIconView datew;

	    @FXML
	    private JFXTextField title;

	    @FXML
	    private JFXTextArea description;

	    @FXML
	    private FontAwesomeIconView emailw;

	    @FXML
	    private FontAwesomeIconView libellew;

	    @FXML
	    private FontAwesomeIconView descriptionw;

	    @FXML
	    private FontAwesomeIconView titlew;

	    @FXML
	    private Button submit;

	    @FXML
	    private AnchorPane an1;

	    @FXML
	    private FontAwesomeIconView image;

	    @FXML
	    private ImageView pic;

	    @FXML
	    private FontAwesomeIconView image1w;

	    @FXML
	    private FontAwesomeIconView imagew;


	@FXML
	void SubmitPost(ActionEvent event) {
		
		if (controleSaisie())
		{
		Post p = new Post();
		p.setCategory(category.getValue());
		p.setTitle(title.getText());
		p.setIduser(application.Main.user);
		p.setDescription(description.getText());
		Image imagex = pic.getImage();
		String nameImage = saveToFileImageNormal(imagex);
		p.setImage(nameImage);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate localDate = LocalDate.now();
		if (Date.valueOf(date.getValue()).after(Date.valueOf(localDate))
				|| Date.valueOf(date.getValue()).compareTo(Date.valueOf(localDate)) == 0) {
			java.util.Date datee = java.util.Date
					.from(date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
			java.sql.Date sqldate = new java.sql.Date(datee.getTime());
			p.setDate(sqldate);
		}

	
		GestionPost gp = new GestionPost();
		gp.AddPost(p);
		
		
		 try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ListPost.fxml"));
             Parent root = loader.load();
             
             an1.getScene().setRoot(root);
            
         } catch (IOException ex) {
             Logger.getLogger(AddPostController.class.getName()).log(Level.SEVERE, null, ex);
         }
		}
	}

	@FXML
	void addImage4(MouseEvent event) {
		FileChooser fc = new FileChooser();

		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
		fc.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
		File selectedFile = fc.showOpenDialog(null);
		try {
			BufferedImage bufferedImage = ImageIO.read(selectedFile);
			Image image = SwingFXUtils.toFXImage(bufferedImage, null);
			pic.setImage(image);
		} catch (IOException ex) {
			Logger.getLogger(AddPostController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		category.getSelectionModel().selectFirst();
		category.getItems().add(CategoryName.Declaration_By_The_Government.toString().replaceAll("_", " "));
		category.getItems().add(CategoryName.Declaration_of_a_strike.toString().replaceAll("_", " "));
		category.getItems().add(CategoryName.Declaration_On_the_price_of_fuel.toString().replaceAll("_", " "));
		category.getItems().add(CategoryName.Recruitment.toString().replaceAll("_", " "));

	}

	public static String saveToFileImageNormal(Image image) {

		String ext = "jpg";
		File dir = new File("C:\\wamp\\www\\images");
		String name = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(8), ext);
		File outputFile = new File(dir, name);
		BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
		try {
			ImageIO.write(bImage, "png", outputFile);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return name;
	}
	 public boolean controleSaisie() {
		   if (category.getSelectionModel().getSelectedIndex()<0) {
	            souscatww.setVisible(true);
	            souscatww.setStyle("-jfx-focus-color:red");
	            category.requestFocus();
	            org.controlsfx.control.PopOver pop = new org.controlsfx.control.PopOver();
	            pop.setContentNode(new Label("Veuillez choisir une sous categorie"));

	            souscatww.setOnMouseEntered((event) -> {
	                pop.show(souscatww);
	            });
	            souscatww.setOnMouseExited((event) -> {
	                pop.hide();
	                souscatww.setVisible(false);
	            });
	            category.setOnKeyTyped((event2) -> {
	                souscatww.setVisible(false);
	                category.setStyle("-jfx-focus-color:green");
	            });
	            return false;

	        }
		   if (date.getValue()==null) {
	        	datew.setVisible(true);
	        	date.setStyle("-jfx-focus-color:red");
	        	date.requestFocus();
	            org.controlsfx.control.PopOver pop = new org.controlsfx.control.PopOver();
	            pop.setContentNode(new Label("Pick a date"));

	            datew.setOnMouseEntered((event) -> {
	                pop.show(datew);
	            });
	            datew.setOnMouseExited((event) -> {
	                pop.hide();
	                datew.setVisible(false);
	            });
	            date.setOnKeyTyped((event2) -> {
	            	datew.setVisible(false);
	            	date.setStyle("-jfx-focus-color:green");
	            });
	            return false;

	        }
	       

	        if (title.getText().isEmpty()) {
	            titlew.setVisible(true);
	            title.setStyle("-jfx-focus-color:red");
	            title.requestFocus();
	            org.controlsfx.control.PopOver pop = new org.controlsfx.control.PopOver();
	            pop.setContentNode(new Label("Give a title"));

	            titlew.setOnMouseEntered((event) -> {
	                pop.show(titlew);
	            });
	            titlew.setOnMouseExited((event) -> {
	                pop.hide();
	                titlew.setVisible(false);
	            });
	            title.setOnKeyTyped((event2) -> {
	                libellew.setVisible(false);
	                title.setStyle("-jfx-focus-color:green");
	            });
	            return false;

	        }
	       
	        if (description.getText().isEmpty()) {
	            descriptionw.setVisible(true);
	            description.setStyle("-jfx-focus-color:red");
	            description.requestFocus();
	            org.controlsfx.control.PopOver pop = new org.controlsfx.control.PopOver();
	            pop.setContentNode(new Label("Give a description"));

	            descriptionw.setOnMouseEntered((event) -> {
	                pop.show(descriptionw);
	            });
	            descriptionw.setOnMouseExited((event) -> {
	                pop.hide();
	                descriptionw.setVisible(false);
	            });
	            description.setOnKeyTyped((event2) -> {
	                descriptionw.setVisible(false);
	                description.setStyle("-jfx-focus-color:green");
	            });
	            return false;

	        }
	      
	    if ((pic.getImage()==null)) {
	            image1w.setVisible(true);
	            pic.setStyle("-jfx-focus-color:red");
	            pic.requestFocus();
	            org.controlsfx.control.PopOver pop = new org.controlsfx.control.PopOver();
	            pop.setContentNode(new Label("Pic an image"));

	            image1w.setOnMouseEntered((event) -> {
	                pop.show(image1w);
	            });
	            image1w.setOnMouseExited((event) -> {
	                pop.hide();
	            });
	             pic.setOnMouseClicked((event2) -> {
	                image1w.setVisible(false);
	                 addImage4(event2);
	            });
	           
	            return false;

	        }
	       
	        
	        return true;
	    }

}
