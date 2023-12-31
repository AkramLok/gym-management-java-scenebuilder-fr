package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.scene.shape.*;
import javafx.animation.Interpolator;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import javafx.scene.Node;

public class LoginController implements Initializable{
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	@FXML
    private Button button;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private Label wronglogin;
    

    @FXML
     public void userLogin(ActionEvent event) {
    	
    	DatabaseConnector connectNow = new DatabaseConnector();
    	Connection connectDB = connectNow.getConnection();
    	
    	String name = username.getText();
        String pass = password.getText();

        String selectQuery = "SELECT * FROM administrateur WHERE login = ? AND passwd = ?";
        String selectQuery1 = "SELECT * FROM propriétaire WHERE login = ? AND passwd = ?";
        try {
            PreparedStatement statement = connectDB.prepareStatement(selectQuery);
            PreparedStatement statement1 = connectDB.prepareStatement(selectQuery1);
            statement.setString(1, name);
            statement.setString(2, pass);
            ResultSet resultSet = statement.executeQuery();
            statement1.setString(1, name);
            statement1.setString(2, pass);
            ResultSet resultSet1 = statement1.executeQuery();

            if (resultSet1.next()) {
                // Successful login
            	FXMLLoader loader = new FXMLLoader(getClass().getResource("Managpage.fxml"));	
        		root = loader.load();	
        		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        		scene = new Scene(root);
        		stage.setScene(scene);
        		stage.show();
               
            }
            else if(resultSet.next())
            {
            	 // Successful login
            	FXMLLoader loader = new FXMLLoader(getClass().getResource("managpageAdmin.fxml"));	
        		root = loader.load();	
        		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        		scene = new Scene(root);
        		stage.setScene(scene);
        		stage.show();
            }
            else // Invalid credentials
            {
            	wronglogin.setText("username or password incorrect");

            }

           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    private Button btnClient;

    @FXML
    void goToClient(ActionEvent event) throws IOException{
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Client.fxml"));	
		root = loader.load();	
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
		
    }
    @FXML
    private Button gocoach;


    @FXML
    void goCoach(ActionEvent event) throws IOException{
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Coach.fxml"));	
 		root = loader.load();	
 		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
 		scene = new Scene(root);
 		stage.setScene(scene);
 		stage.show();
    }
    @FXML
    void gopaiement(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Paiement.fxml"));	
 		root = loader.load();	
 		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
 		scene = new Scene(root);
 		stage.setScene(scene);
 		stage.show();

    }
    @FXML
    private Button equipementBtn;

    @FXML
    void gotoEquipement(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Equipement.fxml"));	
 		root = loader.load();	
 		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
 		scene = new Scene(root);
 		stage.setScene(scene);
 		stage.show();

    }
    
    @FXML
    private Button gotoPaiement;
    @FXML
    void goToPaiement(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("addPaiement.fxml"));	
 		root = loader.load();	
 		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
 		scene = new Scene(root);
 		stage.setScene(scene);
 		stage.show();

    }
    
    @FXML
    private Button btnClientAdmin;

    @FXML
    void goToClientAdmin(ActionEvent event) throws IOException{
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("ClientAdmin.fxml"));	
		root = loader.load();	
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
		
    }
    @FXML
    private Button gocoachAdmin;


    @FXML
    void goCoachAdmin(ActionEvent event) throws IOException{
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("CoachAdmin.fxml"));	
 		root = loader.load();	
 		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
 		scene = new Scene(root);
 		stage.setScene(scene);
 		stage.show();
    }
    @FXML
    void gopaiementAdmin(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Paiement.fxml"));	
 		root = loader.load();	
 		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
 		scene = new Scene(root);
 		stage.setScene(scene);
 		stage.show();

    }
    @FXML
    private Button equipementBtnAdmin;

    @FXML
    void gotoEquipementAdmin(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("EquipementAdmin.fxml"));	
 		root = loader.load();	
 		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
 		scene = new Scene(root);
 		stage.setScene(scene);
 		stage.show();

    }
    
    @FXML
    private Button gotoPaiementAdmin;
    @FXML
    void goToPaiementAdmin(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("addPaiementAdmin.fxml"));	
 		root = loader.load();	
 		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
 		scene = new Scene(root);
 		stage.setScene(scene);
 		stage.show();

    }
    
    @FXML
    void gotoAdmin(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin.fxml"));	
 		root = loader.load();	
 		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
 		scene = new Scene(root);
 		stage.setScene(scene);
 		stage.show();

    }
    
    @FXML
    private Button gotoBilan;

   
    @FXML
    void goToBilan(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Bilan.fxml"));	
 		root = loader.load();	
 		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
 		scene = new Scene(root);
 		stage.setScene(scene);
 		stage.show();


    }

    @FXML
    void backlogin(MouseEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Loginpage.fxml"));	
 		root = loader.load();	
 		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
 		scene = new Scene(root);
 		stage.setScene(scene);
 		stage.show();


    }
    @FXML
    private ImageView test;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
//		RotateTransition rotate = new RotateTransition();
//		rotate.setNode(test);
//		rotate.setDuration(Duration.millis(1500));
//		rotate.setCycleCount(RotateTransition.INDEFINITE);
//		rotate.setByAngle(-20);
//		rotate.setAutoReverse(true);
//		
//		rotate.play();
		ScaleTransition rotate = new ScaleTransition();
		rotate.setNode(test);
		rotate.setDuration(Duration.millis(1500));
		rotate.setCycleCount(RotateTransition.INDEFINITE);
		rotate.setInterpolator(Interpolator.LINEAR);
		rotate.setByX(0.3);
		rotate.setByY(0.3);
		rotate.setAutoReverse(true);
		
		rotate.play();
		
		
	}




}
