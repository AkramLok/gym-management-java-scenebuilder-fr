package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javafx.scene.Node;

public class AdminController implements Initializable{
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	 @FXML
	    private TextField AdresseAdmin;

	    @FXML
	    private TextField Login;

	    @FXML
	    private TextField NomAdmin;

	    @FXML
	    private TextField Passwd;

	    @FXML
	    private TextField PrenomAdmin;

	    @FXML
	    private TextField TelAdmin;
	    
	    
	    @FXML
	    private TableColumn<Admin, String> NomAdmincl;


	    @FXML
	    private TableColumn<Admin, String> adresseAdmincl;

	    @FXML
	    private TableColumn<Admin, Integer> idAdmin;

	    @FXML
	    private TableColumn<Admin, String> loginAdmincl;

//	    @FXML
//	    private TableColumn<Admin, String> passwdAdmincl;

	    @FXML
	    private TableColumn<Admin, String> prenomAdmincl;
	    @FXML
	    private TableView<Admin> tableAdmin;
	   

	    @FXML
	    private TableColumn<Admin, String> telAdmincl;
	    @FXML
	    private Button btnAddAdmin;

	    @FXML
	    private Button btndeletAdmin;

	    @FXML
	    private Button btneditAdmin;

	    @FXML
	    private Label errorAddClient;

	   
	    
	    
	    public ObservableList<Admin> AdminListData(){
	    	DatabaseConnector connectNow = new DatabaseConnector();
	    	Connection connectDB = connectNow.getConnection();
	    	
	    	ObservableList<Admin> ListData = FXCollections.observableArrayList();
	    	String DisplayQuery="SELECT * FROM Administrateur";
	    	try {
	    		
	            PreparedStatement statement = connectDB.prepareStatement(DisplayQuery);
	            ResultSet resultSet = statement.executeQuery();
	            
	            Admin admin ;
	            
	            while(resultSet.next())
	            {
	            	admin = new Admin(resultSet.getInt("id"),resultSet.getString("nom"),resultSet.getString("prenom"),resultSet.getString("adresse"),resultSet.getString("tél"),resultSet.getString("login"),resultSet.getString("passwd"));
	            	ListData.add(admin);
	            }
	    }catch(Exception e) {
            e.printStackTrace();
        }
	    	return ListData;
	   
}
	    private ObservableList<Admin> Admin;
	    public void AdminShowData()
	    {
	    	Admin = AdminListData();
	    	idAdmin.setCellValueFactory(new PropertyValueFactory<>("id"));
	    	NomAdmincl.setCellValueFactory(new PropertyValueFactory<>("nom"));
	    	prenomAdmincl.setCellValueFactory(new PropertyValueFactory<>("prenom"));
	    	adresseAdmincl.setCellValueFactory(new PropertyValueFactory<>("adresse"));
	    	telAdmincl.setCellValueFactory(new PropertyValueFactory<>("tel"));
	    	loginAdmincl.setCellValueFactory(new PropertyValueFactory<>("login"));

	    	
	    	tableAdmin.setItems(Admin);
	    	
	    }
	    
	    
	    
	    
	    


	    @FXML
	    private Label errorAdda;

	    @FXML
	    void Addadmin(ActionEvent event) {
	    	DatabaseConnector connectNow = new DatabaseConnector();
	    	Connection connectDB = connectNow.getConnection();
	    	 if (NomAdmin.getText().isEmpty() || PrenomAdmin.getText().isEmpty() || AdresseAdmin.getText().isEmpty() || TelAdmin.getText().isEmpty() || Login.getText().isEmpty() || Passwd.getText().isEmpty() ) {
	    		    errorAdda.setText("Veuillez remplir tous les champs");
		            return; 
		        }

	    	 String nomQuery = "SELECT nom FROM administrateur";
	    	 String insertQuery = "INSERT INTO administrateur(nom, prenom, adresse, tél, login, passwd) VALUES (?, ?, ?, ?, ?, ?)";
	    	 try {
	    		 PreparedStatement statement0 = connectDB.prepareStatement(nomQuery);
		        	ResultSet resultSetCin = statement0.executeQuery();
		        	while(resultSetCin.next())
		        	{
		        		if(resultSetCin.getString("Nom").equalsIgnoreCase(NomAdmin.getText()))
		        		{
		        			errorAdda.setText("Cet admin existe déjà");
		        			return;
		        		}
		        	}
		        	PreparedStatement statement = connectDB.prepareStatement(insertQuery);
		            statement.setString(1, NomAdmin.getText());
		            statement.setString(2, PrenomAdmin.getText());
		            statement.setString(3, AdresseAdmin.getText());
		            statement.setString(4, TelAdmin.getText());
		            statement.setString(5, Login.getText());
		            statement.setString(6, Passwd.getText());
		            int rowsAffected = statement.executeUpdate();
		            if (rowsAffected > 0) {
		                // Insertion successful
		            	AdminShowData();
		                System.out.println("Data inserted successfully");
		                NomAdmin.setText("");
		                PrenomAdmin.setText("");
		                AdresseAdmin.setText("");
		                TelAdmin.setText("");
		                Login.setText("");
		                Passwd.setText("");
		                errorAdda.setText("");
		            } 
		            else 
		            {
		                // Insertion failed
		            	System.out.println("Insertion failed");
		            }
	    	 } catch (Exception e) {
		            e.printStackTrace();
		        }

	    }
	    

	    @FXML
	    void DeleteAdmin(ActionEvent event) {
	    	DatabaseConnector connectNow = new DatabaseConnector();
	    	Connection connectDB = connectNow.getConnection();
	    	String DeletQuery = "DELETE FROM administrateur WHERE nom=?";
	    	try {
	            PreparedStatement statement = connectDB.prepareStatement(DeletQuery);
	            statement.setString(1, NomAdmin.getText());
	            statement.executeUpdate();
	            AdminShowData();
	            NomAdmin.setText("");
                PrenomAdmin.setText("");
                AdresseAdmin.setText("");
                TelAdmin.setText("");
                Login.setText("");
                Passwd.setText("");
                errorAdda.setText("");
    	       
	            
	        } catch (Exception e) {
            e.printStackTrace();
	        }

	    }

	    @FXML
	    void UpdateAdmin(ActionEvent event) {
	    	DatabaseConnector connectNow = new DatabaseConnector();
	    	Connection connectDB = connectNow.getConnection();
	    	String UpdateQuery="UPDATE administrateur SET prenom=?, adresse=?, tél=?, login=?, passwd=? WHERE nom=?";
	    	try {
	            PreparedStatement statement = connectDB.prepareStatement(UpdateQuery);
	            
	            statement.setString(1, PrenomAdmin.getText());
	            statement.setString(2, AdresseAdmin.getText());
	            statement.setString(3, TelAdmin.getText());
	            statement.setString(4, Login.getText());
	            statement.setString(5, Passwd.getText());
	            statement.setString(6, NomAdmin.getText());
	            
	            statement.executeUpdate();
	            AdminShowData();
	            
	        

	            
	        } catch (Exception e) {
            e.printStackTrace();
	        }

	    }

	    @FXML
	    void goBack(ActionEvent event) throws IOException {
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Managpage.fxml"));	
			root = loader.load();	
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

	    }

	    @FXML
	    void handleMouseAction(MouseEvent event) {
	    	Admin admin = tableAdmin.getSelectionModel().getSelectedItem();
	    	 NomAdmin.setText(admin.getNom());
             PrenomAdmin.setText(admin.getPrenom());
             AdresseAdmin.setText(admin.getAdresse());
             TelAdmin.setText(admin.getTel());
             Login.setText(admin.getLogin());
             Passwd.setText(admin.getPassword());
             errorAdda.setText("");

	    }

	  
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			
			AdminShowData();
		}
	

}
