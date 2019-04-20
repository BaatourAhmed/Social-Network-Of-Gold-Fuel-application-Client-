package controllers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import services.GestionPost;

public class EditPostController implements Initializable {

	@FXML
	private JFXComboBox<String> category;

	@FXML
	private FontAwesomeIconView souscatw;

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
	private Button submit;

	@FXML
	private AnchorPane an1;

	@FXML
	private FontAwesomeIconView image;

	@FXML
	private ImageView pic;

	@FXML
	private DatePicker date;

	@FXML
	private FontAwesomeIconView image1w;

	Post p;
	
	@FXML
	void SubmitPost(ActionEvent event) {
	
		GestionPost gp = new GestionPost();
		p.setCategory(category.getValue());
		p.setTitle(title.getText());
		p.setDescription(description.getText());
		Image imagex = pic.getImage();
		String nameImage = saveToFileImageNormal(imagex);
		p.setImage(nameImage);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.now();
		
		
		
		if(date.getValue() == null){
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-mm-yyyy");
			java.util.Date datee;
			try {
				datee = sdf1.parse(date.getPromptText());
				java.sql.Date sqlStartDate = new java.sql.Date(datee.getTime()); 
				p.setDate(sqlStartDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else{
			java.util.Date datee = java.util.Date
					.from(date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
			java.sql.Date sqldate = new java.sql.Date(datee.getTime());
			p.setDate(sqldate);
		}
		
		
		gp.UpdatePost(p);
		
		
		 try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ListPost.fxml"));
             Parent root = loader.load();
             
             an1.getScene().setRoot(root);
            
         } catch (IOException ex) {
             Logger.getLogger(AddPostController.class.getName()).log(Level.SEVERE, null, ex);
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
	
	void remplir(Post q){
		p=q;
		description.setText(q.getDescription());
		title.setText(q.getTitle());
		category.setValue(q.getCategory());
		date.setPromptText(p.getDate().toString());
    	
        Image imageURIuser = new Image("file:C:\\xampp\\htdocs\\images\\" + p.getImage());
        
        pic.setImage(imageURIuser);
	}

}
