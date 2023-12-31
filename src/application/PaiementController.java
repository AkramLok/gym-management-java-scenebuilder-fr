package application;
import java.io.IOException;
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
public class PaiementController{
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	
	@FXML
    private Button actuelPaiement;

    @FXML
    private Button addpaiement;


    @FXML
    private Button historicPaiement;
	
	@FXML
    private TableColumn<Paiement, String> TypeAbcolonne;

    @FXML
    private Button btnClient;

    @FXML
    private TableColumn<Paiement, String> datecolonne;

  

    @FXML
    private TableColumn<Paiement, Integer> idpaiementcolonne;

    @FXML
    private TableColumn<Paiement, String> idclientcolonne;

    @FXML
    private Button showtablePaiment;

    @FXML
    private TableColumn<Paiement, String> statutPaiement;

    @FXML
    private TableView<Paiement> tablePaiment;


    @FXML
    private TableColumn<Paiement, String> dateabcolonne;

    @FXML
    private TableColumn<Paiement, String> datefinabcolonne;

    @FXML
    private Button showtabelPaiment;
    

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
    void goToAddpaiement(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("addPaiement.fxml"));	
		root = loader.load();	
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

    }

    @FXML
    void gotoHistPaiement(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("PaiementHist.fxml"));	
		root = loader.load();	
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }

    @FXML
    void gotoPaiement(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Paiement.fxml"));	
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
    private Button btnPayer;

   

    @FXML
    private Label errorAddClient;

  

    @FXML
    private TextField idclients;

    @FXML
    private TextField nameSearch;

    @FXML
    private TextField nomclients;

    @FXML
    private TextField price;

    @FXML
    private ImageView search;

    @FXML
    private Label statutencour;

    @FXML
    private Label statutpass;



 

    @FXML
    void payer(ActionEvent event) {

    }

    @FXML
    void searchClient(MouseEvent event) {

    }
    
    public ObservableList<Paiement> PaiementHistListData(){
    	DatabaseConnector connectNow = new DatabaseConnector();
    	Connection connectDB = connectNow.getConnection();
    	String DisplayQueryA = "SELECT * FROM Abonnement ";
    	ObservableList<Paiement> ListData = FXCollections.observableArrayList();
    	String DisplayQuery = "SELECT * FROM Paiement";
    	
    	try {
      
            //Abonnement al;
            //al = new Abonnement(resultSetA.getInt("code"),resultSetA.getString("type"),resultSetA.getInt("durée"),resultSetA.getDouble("tarif"));
            PreparedStatement statement = connectDB.prepareStatement(DisplayQuery);
            ResultSet resultSet = statement.executeQuery();
            
            Paiement pl;
            while(resultSet.next())
            {
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
                      
                	}
                }
            	pl = new Paiement(resultSet.getInt("code"),resultSet.getInt("Id_client"),resultSet.getString("Type_Ab"),resultSet.getString("Date_ab"),d2,statut);
            	ListData.add(pl);
            }

	    }
    	catch(Exception e)
	    {
	        e.printStackTrace();
	    }
    	return ListData;
   
    }
    
    
    
    
    
    
    @FXML
    private Button showpaiementdata;
    
    private ObservableList<Paiement> PaiementHist;
    public void PaiementHistShowData()
    {
    	PaiementHist = PaiementHistListData();
    	idpaiementcolonne.setCellValueFactory(new PropertyValueFactory<>("code"));
    	idclientcolonne.setCellValueFactory(new PropertyValueFactory<>("Id_client"));
    	TypeAbcolonne.setCellValueFactory(new PropertyValueFactory<>("Type_Ab"));
    	dateabcolonne.setCellValueFactory(new PropertyValueFactory<>("Date_ab"));
    	datefinabcolonne.setCellValueFactory(new PropertyValueFactory<>("Date_fin_ab"));
    	statutPaiement.setCellValueFactory(new PropertyValueFactory<>("Statut"));
    	tablePaiment.setItems(PaiementHist);
    	
    }
    
    
    


    public ObservableList<Paiement> PaiementCoursListData(){
      	DatabaseConnector connectNow = new DatabaseConnector();
    	Connection connectDB = connectNow.getConnection();
    	String DisplayQueryA = "SELECT * FROM Abonnement ";
    	ObservableList<Paiement> ListData = FXCollections.observableArrayList();
    	String DisplayQuery = "SELECT * FROM Paiement";
    	
    	try {
      
            //Abonnement al;
            //al = new Abonnement(resultSetA.getInt("code"),resultSetA.getString("type"),resultSetA.getInt("durée"),resultSetA.getDouble("tarif"));
            PreparedStatement statement = connectDB.prepareStatement(DisplayQuery);
            ResultSet resultSet = statement.executeQuery();
            
            Paiement pl;
            while(resultSet.next())
            {
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
                      
                	}
                }
                if(statut.equals("En cours"))
                {
                	pl = new Paiement(resultSet.getInt("code"),resultSet.getInt("Id_client"),resultSet.getString("Type_Ab"),resultSet.getString("Date_ab"),d2,statut);
                	ListData.add(pl);
                }
            }

	    }
    	catch(Exception e)
	    {
	        e.printStackTrace();
	    }
    	return ListData;
   
    }
    private ObservableList<Paiement> PaiementCours;
    public void PaiementCoursShowData()
    {
    	PaiementCours = PaiementCoursListData();
    	idpaiementcolonne.setCellValueFactory(new PropertyValueFactory<>("code"));
    	idclientcolonne.setCellValueFactory(new PropertyValueFactory<>("Id_client"));
    	TypeAbcolonne.setCellValueFactory(new PropertyValueFactory<>("Type_Ab"));
    	dateabcolonne.setCellValueFactory(new PropertyValueFactory<>("Date_ab"));
    	datefinabcolonne.setCellValueFactory(new PropertyValueFactory<>("Date_fin_ab"));
    	statutPaiement.setCellValueFactory(new PropertyValueFactory<>("Statut"));
    	tablePaiment.setItems(PaiementCours);
    	
    }
    
 
}
