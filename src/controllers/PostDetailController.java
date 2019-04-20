package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.controlsfx.control.Notifications;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextArea;

import application.Main;
import entity.Comment;
import entity.Post;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;
import services.GestionPost;

public class PostDetailController implements Initializable {
	private List<Comment> listComment;
	@FXML
	private JFXTabPane tab;
	@FXML
	private ImageView image;

	@FXML
	private Label title;

	@FXML
	private Label date;

	@FXML
	private Label description;

	@FXML
	private JFXButton edit;

	@FXML
	private JFXButton delete;

	@FXML
	private JFXTextArea labelComment;

	@FXML
	private JFXButton commentAdd;

	@FXML
	private AnchorPane anchorComment;

	@FXML
	private Label idd;
	@FXML
	private Tab tabX;

	@FXML
	void goBack(MouseEvent event) throws IOException {
		FXMLLoader loader;
		try {
			loader = new FXMLLoader(getClass().getResource("/views/ListPost.fxml"));
			Parent root = loader.load();

			date.getScene().setRoot(root);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	GestionPost gp = new GestionPost();
	Post p;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	

	}

	@FXML
	void addComment(ActionEvent event) throws SQLException, IOException {
		Comment c = new Comment();
		c.setComment(labelComment.getText());
		java.util.Date d = new java.util.Date();
		java.sql.Date sqldate = new java.sql.Date(d.getTime());
		c.setDate(sqldate);

		Post p = new Post();
		p.setId(Integer.valueOf(idd.getText()));
		c.setIdpost(p);
		c.setIduser(application.Main.user);
		gp.AddComment(c);

		
		Reviewslist(p);
		 tab.getSelectionModel().select(tabX);
	}

	void remplir(int id) throws SQLException, IOException {
		
		idd.setText(String.valueOf(id));
		p = gp.FindPost(id);
		if (!(application.Main.user.getId()==p.getIduser().getId()))
		{
			edit.setVisible(false);
			delete.setVisible(false);
		}
		date.setText(p.getDate().toString());
		description.setText(p.getDescription());
		title.setText(p.getTitle());
		Image imageURIuser = new Image("file:C:\\wamp\\www\\images\\" + p.getImage());
		image.setImage(imageURIuser);
		Reviewslist(p);
	}

	@FXML
	void delete(ActionEvent event) {

		gp.RemovePost(p);
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ListPost.fxml"));
			Parent root = loader.load();

			image.getScene().setRoot(root);

		} catch (IOException ex) {
			Logger.getLogger(AddPostController.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	@FXML
	void edit(ActionEvent event) {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/EditPost.fxml"));
			Parent root = loader.load();
			EditPostController epc = loader.getController();
			epc.remplir(p);

			image.getScene().setRoot(root);

		} catch (IOException ex) {
			Logger.getLogger(AddPostController.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	public void Reviewslist(Post p) throws SQLException, IOException {

		TilePane b = new TilePane();

		b.setPadding(new javafx.geometry.Insets(30));
		TilePane cx = new TilePane();

		GestionPost gp = new GestionPost();
		listComment = gp.ListComments(Integer.valueOf(idd.getText()));

		for (Comment c : listComment) {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/DivReview.fxml"));
			Parent root = (Pane) loader.load();
			DivReviewController DHC = loader.getController();
			DHC.LoadValues(c, p);

			// c.setVgap(40);
			cx.getChildren().removeAll();

			cx.getChildren().add(root);

		}
		cx.setPrefColumns(2);
		cx.setPadding(new javafx.geometry.Insets(0));
		cx.setHgap(25);
		cx.setVgap(50);

		b.getChildren().add(cx);
		b.setPrefWidth(1000);
		anchorComment.getChildren().add(b);

	}
}
