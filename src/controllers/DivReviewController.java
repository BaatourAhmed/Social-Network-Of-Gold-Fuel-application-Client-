package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

import com.jfoenix.controls.JFXButton;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entity.Comment;
import entity.Like;
import entity.Post;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import services.GestionPost;

public class DivReviewController implements Initializable {

	@FXML
	private Label rname;

	@FXML
	private Pane sq;

	@FXML
	private JFXButton rjaime;

	@FXML
	private JFXButton rliekd;

	@FXML
	private Label nbrlikes;

	@FXML
	private Circle rcircle;

	@FXML
	private Label rcom;

	@FXML
	private Label id;

	@FXML
	private FontAwesomeIconView trash;

	@FXML
	private Label daterev;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		rliekd.setVisible(false);
trash.setVisible(false);

	}

	public void LoadValues(Comment c, Post p) throws IOException, SQLException {

		rcom.setText(c.getComment());
		daterev.setText(String.valueOf(c.getDate()));
		id.setText(String.valueOf(c.getId()));

		rjaime.setOnMouseClicked((event) -> {
			rjaime.setVisible(false);
			rliekd.setVisible(true);
			GestionPost gp = new GestionPost();
			
			Like l = new Like();

			l.setIdcomment(c);
			l.setIduser(application.Main.user);

			gp.AddLike(l);
			nbrlikes.setText(String.valueOf(Integer.valueOf(nbrlikes.getText() + 1)));
		});
		rliekd.setOnMouseClicked((event) -> {
			GestionPost gp = new GestionPost();
			
				gp.RemoveLike(application.Main.user.getId(),Integer.valueOf(id.getText()));
				rjaime.setVisible(true);
				rliekd.setVisible(false);
			
				nbrlikes.setText(String.valueOf(Integer.valueOf(nbrlikes.getText())-1));
			
		});

		
		rname.setText(c.getIduser().getName());
		
		rcircle.setFill(new ImagePattern(new Image("file:C:\\wamp\\www\\images\\24ebV7OF.jpg")));

	}

}
