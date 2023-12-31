package application;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.text.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.animation.PauseTransition;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Node;
public class PaiementController2Admin implements Initializable{
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	

    @FXML
    private Button actuelPaiement;

    @FXML
    private Label datefinabLabel;
    
    @FXML
    private Button addpaiement;

    @FXML
    private Button btnPayer;



    @FXML
    private Label errorAddClient;

    @FXML
    private Button historicPaiement;

    @FXML
    private Label idClient;

    @FXML
    private TextField price;

    @FXML
    private Label statutLabel;

    @FXML
    private Label statutpass;

    @FXML
    private ChoiceBox<String> typeAb;
    
    @FXML
    private ChoiceBox<String> cinSelect;

    private String[] Abonnment= {"Mensuel","Trimestriel","Annuel"};
    private ArrayList<String> CIN= ClientListData();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		typeAb.getItems().addAll(Abonnment);
		typeAb.setOnAction(this::getTypeAb);
		cinSelect.getItems().addAll(CIN);
		cinSelect.setOnAction(this::getStatut);
	}
	
	
	
	
	
	
	
	
	
	
	
    public void getStatut(ActionEvent event){
    	DatabaseConnector connectNow = new DatabaseConnector();
    	Connection connectDB = connectNow.getConnection();
    	String DisplayQueryA = "SELECT * FROM Abonnement ";
    	String DisplayQuery = "SELECT * FROM Paiement WHERE Id_client = ? ORDER BY code DESC LIMIT 1";
    	String DisplayQueryC = "SELECT * FROM Client WHERE cin = ?";
		String cin =cinSelect.getValue();

    	try {
            //Abonnement al;
            //al = new Abonnement(resultSetA.getInt("code"),resultSetA.getString("type"),resultSetA.getInt("durée"),resultSetA.getDouble("tarif"));
    		PreparedStatement statementC = connectDB.prepareStatement(DisplayQueryC);
        	statementC.setString(1, cin);
            ResultSet resultSetC = statementC.executeQuery();
            resultSetC.next();
            int ClientId = resultSetC.getInt("id");
            idClient.setText(String.valueOf(ClientId));
            
            
            
            PreparedStatement statement = connectDB.prepareStatement(DisplayQuery);
        	statement.setInt(1, ClientId);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            
        
    		PreparedStatement statementA = connectDB.prepareStatement(DisplayQueryA);
            ResultSet resultSetA = statementA.executeQuery();
            String d1 = "";
            String d2 = "";
            String statut = "";
            while(resultSetA.next())
            {
            	if(resultSetA.getString("type").equals(resultSet.getString("Type_Ab")))
            	{
            	  int duree = resultSetA.getInt("durée");
            	  System.out.println("Paiement in id " + resultSet.getInt("code")+", client n°"+ resultSet.getInt("Id_client") +" a abonnement " + resultSetA.getString("type")+" "+ resultSet.getString("Type_Ab")+ " avec duree "+ duree+" et la date est: "+ resultSet.getString("Date_ab"));
            	  d1 = resultSet.getString("Date_ab");  
            	  System.out.println("Date avant l'addition: "+d1);
            	  //Spécifier le format de date correspondant à la date d1
            	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            	  Calendar cal = Calendar.getInstance();
            	  try{
            	     //Définir la date
            	     cal.setTime(sdf.parse(d1));
            	  }catch(ParseException e){
            	    e.printStackTrace();
            	   }
            	     
            	  //Nombre de jours à ajouter
            	  cal.add(Calendar.DAY_OF_MONTH, duree);  
            	  //Date après avoir ajouté les jours à la date indiquée
            	  d2 = sdf.format(cal.getTime());
            	  datefinabLabel.setText(d2);
            	  System.out.println("Date après l'addition: "+d2);
            	  
                  Date startDate = sdf.parse(d1);
                  Date endDate = sdf.parse(d2);
                  
                  SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
                  Date now = new Date();
                  String strDate = sdfDate.format(now);
                  Date dateAbonnee = sdf.parse(strDate);
                  
                  if((dateAbonnee.after(startDate) && dateAbonnee.before(endDate))||dateAbonnee.compareTo(startDate) == 0 ||dateAbonnee.compareTo(endDate) == 0 )
                  {
                      System.out.println("The date "+sdf.format(dateAbonnee)+" en cours");
                      statut = "En cours";
                  }
                  else if(dateAbonnee.after(startDate) && !dateAbonnee.before(endDate))
                  {
              			System.out.println(sdf.format(dateAbonnee)+" passé");
                        statut = "Passé";
                  }
                  else if(!dateAbonnee.after(startDate) && dateAbonnee.before(endDate))
                  {
            			System.out.println(sdf.format(dateAbonnee)+" abonnée en avance");
                        statut = "En avance";
                  }
                  else
                  {
          				System.out.println("Impossible");
                  }
                  statutLabel.setText(statut);
            	}
            }
	    }
    	catch(Exception e)
	    {
	        e.printStackTrace();
	    }   
    }
    
    
    
    
	
	
	
	
	
	public ArrayList <String> ClientListData(){
    	DatabaseConnector connectNow = new DatabaseConnector();
    	Connection connectDB = connectNow.getConnection();
    	
    	ArrayList<String> ListData = new ArrayList<>();
    	String DisplayQuery="SELECT * FROM client";
    	try {
    		
            PreparedStatement statement = connectDB.prepareStatement(DisplayQuery);
            ResultSet resultSet = statement.executeQuery();
                        
            while(resultSet.next())
            {
            	//cl = new Client(resultSet.getInt("id"),resultSet.getString("cin"),resultSet.getString("nom"),resultSet.getString("prénom"),resultSet.getString("adresse"),resultSet.getString("tél"));
            	ListData.add(resultSet.getString("cin"));
            }
    	
    }catch(Exception e) {
        e.printStackTrace();
    }
    	return ListData;
   
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
    void goBack(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("ManagpageAdmin.fxml"));	
		root = loader.load();	
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

    }

    @FXML
    void goToAddpaiement(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("addPaiementAdmin.fxml"));	
		root = loader.load();	
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

    }

    @FXML
    void gotoHistPaiement(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("PaiementHistAdmin.fxml"));	
		root = loader.load();	
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }

    @FXML
    void gotoPaiement(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("PaiementAdmin.fxml"));	
		root = loader.load();	
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

    }

    @FXML
    void handleMouseAction(MouseEvent event) {

    }

    @FXML
    void showdata(ActionEvent event) {

    }

    @FXML
    void payer(ActionEvent event) {
    	DatabaseConnector connectNow = new DatabaseConnector();
    	Connection connectDB = connectNow.getConnection();
    	int Id_Client = Integer.parseInt(idClient.getText());
    	String Type_Ab = typeAb.getValue();
    	String date_fin = datefinabLabel.getText();
    	String statut = statutLabel.getText();
        String insertQuery1 = "INSERT INTO paiement(Id_Client, Type_Ab,Date_ab) VALUES (?, ?, ?)";
		try {
            PreparedStatement statement1 = connectDB.prepareStatement(insertQuery1);
            statement1.setInt(1,Id_Client);
            statement1.setString(2,Type_Ab);
	    	if(statut.equals("Passé"))
	    	{
	            String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	            statement1.setString(3, currentDate);
	            statement1.executeUpdate();

	    	}
	    	else if(statut.equals("En cours") || statut.equals("En avance"))
	    	{
    		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
          	  Calendar cal = Calendar.getInstance();
          	  try
          	  {
          	     cal.setTime(sdf.parse(date_fin));
          	  }
          	  catch(ParseException e)
          	  {
          	    e.printStackTrace();
          	  }
          	  cal.add(Calendar.DAY_OF_MONTH, 1);  
	          statement1.setString(3,sdf.format(cal.getTime()));
	          statement1.executeUpdate();
	    	}
	    	else
	    	{
	    		System.out.println("Impossible pas statut choisi.");
	    	}
	    	getStatut(null);
	    	getTypeAb(null);
	    }
		catch (Exception e)
		{
	        e.printStackTrace();
	    }
    }

    @FXML
    void searchClient(MouseEvent event) {

    }
    
    



}

