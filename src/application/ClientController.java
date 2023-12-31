package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javafx.scene.Node;

public class ClientController implements Initializable{
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	
	    @FXML
	    private Button goback;
	    
	    @FXML
	    private Label clientIdEdit;   

	    @FXML
	    void goBack(ActionEvent event) throws IOException{
	    	
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Managpage.fxml"));	
			root = loader.load();	
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
	    }
	    @FXML
	    private Button gobackclient;

	    @FXML
	    void goBackCl(ActionEvent event) throws IOException{
	    	
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Client.fxml"));	
			root = loader.load();	
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
	    }
	    @FXML
	    private Button addclient;
	    
	    @FXML
	    private TextField clientCin;
	    
	    @FXML
	    private TextField ClientTel;

	    @FXML
	    private Button btnaddclient;

	    @FXML
	    private TextField clientName;

	    @FXML
	    private TextField clientSName;
	    @FXML
	    private TextField ClientAdresse;
	  
	    @FXML
	    private TextField price;
	    
	    @FXML
	    private Button btnPayer;

	    @FXML
	    private Label idClient;
	    
	    @FXML
	    private Label statutLabel;

	    @FXML
	    private Label statutpass;
	    @FXML
	    private ChoiceBox<String> gender;

	    @FXML
	    private ChoiceBox<String> typeAb;

	    private String[] Abonnment= {"Mensuel","Trimestriel","Annuel"};
	    private String[] Sex= {"Homme","Femme"};

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			typeAb.getItems().addAll(Abonnment);
			typeAb.setOnAction(this::getTypeAb);
			gender.getItems().addAll(Sex);
			gender.setOnAction(this::getTypeAb);
			CLientShowData();


		}
	    
		public void getTypeAb(ActionEvent event)
		{
			DatabaseConnector connectNow = new DatabaseConnector();
	    	Connection connectDB = connectNow.getConnection();
			String type=typeAb.getValue();
			String getPriceQuery="SELECT tarif FROM abonnement WHERE type=?";
			try {
	            PreparedStatement statement = connectDB.prepareStatement(getPriceQuery);
	            statement.setString(1,type);
	            ResultSet resultSet = statement.executeQuery(); 
	            while(resultSet.next())
	            {
	            	price.setText(resultSet.getString("tarif"));	
	            }
	        } catch (Exception e) {
            e.printStackTrace();
	        }
		}
		
		 @FXML
		    private Label errorAddClient;
	    @FXML
	    void AddClient(ActionEvent event){
	    	DatabaseConnector connectNow = new DatabaseConnector();
	    	
	    	Connection connectDB = connectNow.getConnection();
	    	String cin = clientCin.getText();
	    	String Nom = clientName.getText();
	        String prenom = clientSName.getText();
	        String adresse = ClientAdresse.getText();
	        String tel = ClientTel.getText();
	        String Gender = gender.getValue();
	        if (cin.isEmpty() || Nom.isEmpty() || prenom.isEmpty() || adresse.isEmpty() || tel.isEmpty() || Gender == null || typeAb.getValue() == null) {
	        	errorAddClient.setText("Veuillez remplir tous les champs");
	            return; 
	        }

	        
	        String cinQuery = "SELECT cin FROM client";
	        String insertQuery = "INSERT INTO client(cin, nom, prénom, adresse, tél,Gender) VALUES (?, ?, ?, ?, ?, ?)";
	        String selectId= "SELECT id FROM client ORDER BY id DESC LIMIT 1";
	        String insertQuery1 = "INSERT INTO paiement(Id_Client, Type_Ab,Date_ab) VALUES (?, ?, NOW())";
	        try {
	        	PreparedStatement statement0 = connectDB.prepareStatement(cinQuery);
	        	ResultSet resultSetCin = statement0.executeQuery();
	        	while(resultSetCin.next())
	        	{
	        		if(resultSetCin.getString("cin").equalsIgnoreCase(cin))
	        		{
	        			errorAddClient.setText("Ce cin existe déjà");
	        			return;
	        		}
	        	}
	        	
	        	PreparedStatement statement = connectDB.prepareStatement(insertQuery);
	        	statement.setString(1, cin);
	            statement.setString(2, Nom);
	            statement.setString(3, prenom);
	            statement.setString(4, adresse);
	            statement.setString(5, tel);
	            statement.setString(6, Gender);
	            int rowsAffected = statement.executeUpdate();
	            CLientShowData();

	            if (rowsAffected > 0) {
	                // Insertion successful
	                System.out.println("Data inserted successfully");
	                clear();
	            } 
	            else 
	            {
	                // Insertion failed
	            	System.out.println("Insertion failed");
	            }
	            
	        	PreparedStatement statement2 = connectDB.prepareStatement(selectId);
	            ResultSet resultSet = statement2.executeQuery();
	            resultSet.next();
	            int idSelected = resultSet.getInt("id");
	            
	            
	            
	            PreparedStatement statement1 = connectDB.prepareStatement(insertQuery1);
	            statement1.setInt(1,idSelected);
	            statement1.setString(2,typeAb.getValue());
	            statement1.executeUpdate();
	           
	            
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	        
	    }
	    
	    public void EditClient(ActionEvent event) {
	        DatabaseConnector connectNow = new DatabaseConnector();
	        Connection connectDB = connectNow.getConnection();
	        String updateQuery = "UPDATE client SET  nom = ?, prénom = ?, adresse = ?, tél = ?,Gender=?  WHERE cin = ?";
		    clientIdEdit.setVisible(false);
	        try {
	            PreparedStatement statement = connectDB.prepareStatement(updateQuery);
//	            statement.setString(1, clientCin.getText());
	            statement.setString(1, clientName.getText());
	            statement.setString(2, clientSName.getText());
	            statement.setString(3, ClientAdresse.getText());
	            statement.setString(4, ClientTel.getText());
	            statement.setString(5, gender.getValue());
	            statement.setString(6,  clientCin.getText());
	            statement.executeUpdate();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    	CLientShowData();
	    	clear();
	    }

	    
	    @FXML
	    private TableColumn<Client, String> adresseclientcolonne;
	    @FXML
	    private TableColumn<Client, Integer> idclient;

	    @FXML
	    private TableColumn<Client, String> nomclientcolonne;

	    @FXML
	    private TableColumn<Client, String> prenomclientcolonne;

	    @FXML
	    private TableView<Client> tableClient;

	    @FXML
	    private TableColumn<Client, String> telclientcolonne;
	    @FXML
	    private TableColumn<Client, String> sexClient;
	    
	    @FXML
	    private TableColumn<Client, String> cinclientcolonne;
	    
	    public ObservableList<Client> ClientListData(){
	    	DatabaseConnector connectNow = new DatabaseConnector();
	    	Connection connectDB = connectNow.getConnection();
	    	
	    	ObservableList<Client> ListData = FXCollections.observableArrayList();
	    	String DisplayQuery="SELECT * FROM client";
	    	try {
	    		
	            PreparedStatement statement = connectDB.prepareStatement(DisplayQuery);
	            ResultSet resultSet = statement.executeQuery();
	            
	            Client cl ;
	            
	            while(resultSet.next())
	            {
	            	cl = new Client(resultSet.getInt("id"),resultSet.getString("cin"),resultSet.getString("nom"),resultSet.getString("prénom"),resultSet.getString("adresse"),resultSet.getString("tél"),resultSet.getString("Gender"));
	            	
	            	ListData.add(cl);
	            }
	    	
	    }catch(Exception e) {
            e.printStackTrace();
        }
	    	return ListData;
	   
	    }
	    
	    private ObservableList<Client> Client;
	    public void CLientShowData()
	    {
	    	Client = ClientListData();
	    	idclient.setCellValueFactory(new PropertyValueFactory<>("id"));
	    	cinclientcolonne.setCellValueFactory(new PropertyValueFactory<>("cin"));
	    	nomclientcolonne.setCellValueFactory(new PropertyValueFactory<>("nom"));
	    	prenomclientcolonne.setCellValueFactory(new PropertyValueFactory<>("prenom"));
	    	adresseclientcolonne.setCellValueFactory(new PropertyValueFactory<>("adresse"));
	    	telclientcolonne.setCellValueFactory(new PropertyValueFactory<>("tel"));
	    	sexClient.setCellValueFactory(new PropertyValueFactory<>("Gender"));
	    	tableClient.setItems(Client);
	    	
	    }
	    @FXML
	    private Button showclientdata;
	   
	    @FXML
	    void handleMouseAction(MouseEvent event) {
	    	Client client = tableClient.getSelectionModel().getSelectedItem();
	    	clientIdEdit.setText(String.valueOf(client.getId()));
	    	clientCin.setText(client.getCin());
	    	clientName.setText(client.getNom());
	        clientSName.setText(client.getPrenom());
	        ClientAdresse.setText(client.getAdresse());
	        ClientTel.setText(client.getTel());
	        gender.setValue(client.getGender());
	    }
	    
	    @FXML
	    private Button btndeleteClient;

	    @FXML
	    private Button btneditclient;
	    
	    public void DeleteCLient() {
	    	DatabaseConnector connectNow = new DatabaseConnector();
	    	Connection connectDB = connectNow.getConnection();
	    	String DeletQuery = "DELETE FROM client WHERE cin=?";
	    	try {
	            PreparedStatement statement = connectDB.prepareStatement(DeletQuery);
	            statement.setString(1, clientCin.getText());
	            statement.executeUpdate();
	            CLientShowData();
	            clear();
	           
    	       
	            
	        } catch (Exception e) {
            e.printStackTrace();
	        }
	    	
	    	
	    	
	    }
	    @FXML
	    private Button btndeletclient;

	    @FXML
	    private Button btneditClient;
	    
	    @FXML
	    private TextField nameSearch;
	    @FXML
	    void searchClient(MouseEvent event) {
	    	DatabaseConnector connectNow = new DatabaseConnector();
	    	Connection connectDB = connectNow.getConnection();
	    	String SearchQuery="SELECT * FROM client WHERE cin=?";
	    	try {
	            PreparedStatement statement = connectDB.prepareStatement(SearchQuery);
	            statement.setString(1, nameSearch.getText());
	            ResultSet resultSet = statement.executeQuery();
	            
	            if(resultSet.next())
	            {
    	        	clientCin.setText(resultSet.getString("cin"));
    	        	clientName.setText(resultSet.getString("nom"));
    		        clientSName.setText(resultSet.getString("prénom"));
    		        ClientAdresse.setText(resultSet.getString("adresse"));
    		        ClientTel.setText(resultSet.getString("tél"));
    		        gender.setValue(resultSet.getString("Gender"));
    		        errorAddClient.setText("");
    		        

	            }
	            else {
	            	 errorAddClient.setText("Cin not found");
	            }

	        } catch (Exception e) {
            e.printStackTrace();
	        }
	    	

	    }
	    @FXML
	    private Button btnreset;
	    @FXML
	    void Resetc(ActionEvent event) {
	    	clear();
	    }
	    public void clear() {
	    	clientCin.setText("");
	        clientName.setText("");
 	        clientSName.setText("");
 	        ClientAdresse.setText("");
 	        ClientTel.setText("");
 	        nameSearch.setText("");
 	        errorAddClient.setText("");
 	        price.setText("");
	        typeAb.setValue(null);
	        gender.setValue(null);
	        idClient.setText("");
	    }
	    
}
