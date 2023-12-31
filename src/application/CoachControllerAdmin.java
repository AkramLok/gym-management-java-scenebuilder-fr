package application;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.converter.StringConverter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Node;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;


public class CoachControllerAdmin implements Initializable {
	private Stage stage;
	private Scene scene;
	private Parent root;
    @FXML
    private Button addcoach;

    @FXML
    private Button editcoach;
    @FXML
    private Button deletecoach;

    @FXML
    private TableView<PresenceData> tablepresence;
    @FXML
    private TableColumn<PresenceData, String> jourcolumn;

    @FXML
    private TableColumn<PresenceData, String> matincolumn;

    @FXML
    private TableColumn<PresenceData, String> apresmidicolumn;

    @FXML
    private TableColumn<PresenceData, String> nuitcolumn;
    
    @FXML
    private Label planMessage1;

    @FXML
    private Label planMessage2;
    
    @FXML
    private Button gobackcoach;
    
    @FXML
    private Button Addcoach;

    @FXML
    private TextField Nomcoach;

    @FXML
    private TextField adressecoach;


    @FXML
    private TextField prenomcoach;

    @FXML
    private TextField salairecoach;

    @FXML
    private TextField telcoach;


    @FXML
    private Button Addplanning;


    @FXML
    private CheckBox dimanche;

    @FXML
    private CheckBox dimanche1;

    @FXML
    private CheckBox dimanche2;

    @FXML
    private CheckBox dimanche3;

    @FXML
    private CheckBox samedi;

    @FXML
    private CheckBox samedi1;

    @FXML
    private CheckBox samedi2;

    @FXML
    private CheckBox samedi3;

    @FXML
    private CheckBox vendredi;

    @FXML
    private CheckBox vendredi1;

    @FXML
    private CheckBox vendredi2;

    @FXML
    private CheckBox vendredi3;

    @FXML
    private CheckBox jeudi;

    @FXML
    private CheckBox jeudi1;

    @FXML
    private CheckBox jeudi2;

    @FXML
    private CheckBox jeudi3;

    @FXML
    private CheckBox lundi;

    @FXML
    private CheckBox lundi1;

    @FXML
    private CheckBox lundi2;

    @FXML
    private CheckBox lundi3;

    @FXML
    private CheckBox mardi;

    @FXML
    private CheckBox mardi1;

    @FXML
    private CheckBox mardi2;

    @FXML
    private CheckBox mardi3;

    @FXML
    private CheckBox mercredi;

    @FXML
    private CheckBox mercredi1,mercredi2,mercredi3;

    private String[] jourArr = {"Lundi","Mardi","Mercredi","Jeudi","Vendredi","Samedi","Dimanche"};

    @FXML
    private Label erroraddc;
    @FXML
    void AddCoach(ActionEvent event) {
    	
    	DatabaseConnector connectNow = new DatabaseConnector();
    	Connection connectDB = connectNow.getConnection();
    	
    	String Nom = Nomcoach.getText();
        String prenom = prenomcoach.getText();
        String adresse = adressecoach.getText();
        String tel = telcoach.getText();
        String salaire = salairecoach.getText();

        if (Nom.isEmpty() || prenom.isEmpty() || adresse.isEmpty() || tel.isEmpty() || salaire.isEmpty()) {
        	erroraddc.setText("Veuillez remplir tous les champs");
            return; 
        }

        String insertQueryc = "INSERT INTO coach(nom, prénom, tél, adresse, salaire) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connectDB.prepareStatement(insertQueryc);
            statement.setString(1, Nom);
            statement.setString(2, prenom);
            statement.setString(3, adresse);
            statement.setString(4, tel);
            statement.setString(5, salaire);
            int rowsAffected = statement.executeUpdate();
            CoachShowData();

            if (rowsAffected > 0) {
                // Insertion successful
                System.out.println("Data inserted successfully");
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
    private TableColumn<Coach, String> adressecoachcolonne;
    @FXML
    private TableColumn<Coach, Integer> idcoach;
    @FXML
    private TableColumn<Coach, String> nomcoachcolonne;
    @FXML
    private TableColumn<Coach, String> prenomcoachcolonne;
    @FXML
    private TableColumn<Coach, Double> salairecoachcolonne;
    
    @FXML
    private TableColumn<Coach, Integer> planningidcolonne;
   
    @FXML
    private TableView<Coach> tableCoach;
    @FXML
    private TableColumn<Coach, String> telcoachcolonne;


    
    public ObservableList<Coach> CoachListData(){
    	DatabaseConnector connectNow = new DatabaseConnector();
    	Connection connectDB = connectNow.getConnection();
    	
    	ObservableList<Coach> ListData = FXCollections.observableArrayList();
    	String DisplayQueryc="SELECT * FROM coach";
    	try {
    		
            PreparedStatement statement = connectDB.prepareStatement(DisplayQueryc);
            ResultSet resultSet = statement.executeQuery();
            
            Coach coach ;
            
            while(resultSet.next())
            {
            	coach = new Coach(resultSet.getInt("id"),resultSet.getString("nom"),resultSet.getString("prénom"),resultSet.getString("tél"),resultSet.getString("adresse"),resultSet.getDouble("salaire"),resultSet.getInt("planningid"));
            	System.out.println(resultSet.getInt("id")+" "+resultSet.getString("nom")+" "+resultSet.getString("prénom")+" "+resultSet.getString("tél")+" "+resultSet.getString("adresse")+" "+resultSet.getDouble("salaire")+" "+resultSet.getInt("planningid"));
            	ListData.add(coach);
            }
    	
	    }
    	catch(Exception e)
    	{
	        e.printStackTrace();
	    }
    	return ListData;
    }
    
    public ObservableList<PresenceData> JourPresenceListData(int planning_id)
    {
    	DatabaseConnector connectNow = new DatabaseConnector();
    	Connection connectDB = connectNow.getConnection();
    	
    	ObservableList<PresenceData> ListData = FXCollections.observableArrayList();
    	String DisplayQueryc = "SELECT * FROM planning WHERE code = ?";
    	try {
    		
            PreparedStatement statement = connectDB.prepareStatement(DisplayQueryc);
            statement.setInt(1,planning_id);
            ResultSet resultSet = statement.executeQuery();
            
            PresenceData Jourdata ;
            if(resultSet.next())
            {
	            String jour = resultSet.getString("JourPresence");
	            String matin = resultSet.getString("Matin");
	            String apresmidi = resultSet.getString("Apresmidi");
	            String nuit = resultSet.getString("Nuit");
	            for (int i = 0; i < jour.length(); i++) {
	                if (jour.charAt(i) == '1')
	                {
	                	Jourdata = new PresenceData(jourArr[i],(matin.charAt(i) == '1')?'x':' ',(apresmidi.charAt(i) == '1')?'x':' ',(nuit.charAt(i) == '1')?'x':' ');
	                	ListData.add(Jourdata);
	                }
	            }
            }
            else
            {
            	System.out.println("PAS PLANNING POUR CE USER.");
            }
	    }
    	catch(Exception e)
    	{
	        e.printStackTrace();
	    }
    	return ListData;
    }
    
    
    
    
    
    
    private ObservableList<Coach> Coach;
    public void CoachShowData()
    {
    	Coach = CoachListData();
    	idcoach.setCellValueFactory(new PropertyValueFactory<>("id"));
    	nomcoachcolonne.setCellValueFactory(new PropertyValueFactory<>("nom"));
    	prenomcoachcolonne.setCellValueFactory(new PropertyValueFactory<>("prenom"));
    	adressecoachcolonne.setCellValueFactory(new PropertyValueFactory<>("adresse"));
    	telcoachcolonne.setCellValueFactory(new PropertyValueFactory<>("tel"));
    	salairecoachcolonne.setCellValueFactory(new PropertyValueFactory<>("salaire"));
    	planningidcolonne.setCellValueFactory(new PropertyValueFactory<>("planningid"));
    	tableCoach.setItems(Coach);
    	
    }
    
    
    
    private ObservableList<PresenceData> presenceDataList = FXCollections.observableArrayList();
    public void JourPresenceShowData(int planning_id)
    {
    	presenceDataList = JourPresenceListData(planning_id);
	    jourcolumn.setCellValueFactory(new PropertyValueFactory<>("jour"));
	    matincolumn.setCellValueFactory(new PropertyValueFactory<>("matin"));
	    apresmidicolumn.setCellValueFactory(new PropertyValueFactory<>("apresmidi"));
	    nuitcolumn.setCellValueFactory(new PropertyValueFactory<>("nuit"));
	    // Add presence data for all days
	    //presenceDataList.add(new PresenceData("Monday", true, false, true));
	    //presenceDataList.add(new PresenceData("Tuesday", false, true, true));
	    // Set the presenceDataList as the items of the TableView
	    tablepresence.setItems(presenceDataList);
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
    private Label IdcoachAff;
    
    
    @FXML
    void handleMouseAction(MouseEvent event) {
    	Coach coach = tableCoach.getSelectionModel().getSelectedItem();
    	IdcoachAff.setText(String.valueOf(coach.getId()));
    	Nomcoach.setText(coach.getNom());
    	prenomcoach.setText(coach.getPrenom());
    	adressecoach.setText(coach.getAdresse());
    	telcoach.setText(coach.getTel());
    	salairecoach.setText(coach.getSalaire());
    	//salairecoach.setText(String.valueOf(coach.getPlanningid()));

		JourPresenceShowData(coach.getPlanningid());
    }

    @FXML
    void DeleteCoach(ActionEvent event) {
    	DatabaseConnector connectNow = new DatabaseConnector();
    	Connection connectDB = connectNow.getConnection();
    	String DeletQuery = "DELETE FROM coach WHERE nom=?";
    	try {
            PreparedStatement statement = connectDB.prepareStatement(DeletQuery);
            statement.setString(1, Nomcoach.getText());
            statement.executeUpdate();
            CoachShowData();         
        } catch (Exception e) {
        e.printStackTrace();
        }
    }

    @FXML
    void EditCoach(ActionEvent event) {
        DatabaseConnector connectNow = new DatabaseConnector();
        Connection connectDB = connectNow.getConnection();
        String updateQuery = "UPDATE coach SET nom = ?, prénom = ? , tél = ?, adresse = ?,salaire = ?  WHERE id = ?";
        IdcoachAff.setVisible(false);
        try {
            PreparedStatement statement = connectDB.prepareStatement(updateQuery);
            statement.setString(1, Nomcoach.getText());
            statement.setString(2, prenomcoach.getText());
            statement.setString(3, adressecoach.getText());
            statement.setString(4, telcoach.getText());
            statement.setDouble(5, Double.parseDouble(salairecoach.getText()));
            statement.setInt(6, Integer.parseInt(IdcoachAff.getText()));
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        CoachShowData();
    	clear();
    }

    @FXML
    private Button resetbtna;
    @FXML
    void Reset(ActionEvent event) {
    	clear();
    }

    public void clear() {
    	Nomcoach.setText("");
    	prenomcoach.setText("");
    	adressecoach.setText("");
    	telcoach.setText("");
    	salairecoach.setText("");
    }
    
    @FXML
    private Button Deletecoach;
    
    
    private ArrayList<Planning> PlanningArray= PlanningListData();
    private CheckBox[] checkboxes;
    private CheckBox[] checkboxes0;
    
    @FXML
    private Label planMessage;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		checkboxes = new CheckBox[]{lundi1, lundi2, lundi3, mardi1, mardi2, mardi3, mercredi1, mercredi2, mercredi3,
	            jeudi1, jeudi2, jeudi3, vendredi1, vendredi2, vendredi3, samedi1, samedi2, samedi3, dimanche1, dimanche2, dimanche3};
	    for (int i = 0; i < jourArr.length; i++) {
	        for (int j = 0; j < 3; j++) {
	            checkboxes[i * 3 + j].setVisible(false);
	        }
	    }
	    
	    
	    
	    checkboxes0= new CheckBox[]{lundi,mardi,mercredi,jeudi,vendredi,samedi ,dimanche};;
	    int i=0;
	    for (i = 0; i < jourArr.length; i++) {
	        final int index = i; // Variable finale locale pour capturer la valeur de 'i'
	    	checkboxes0[i].selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    // Checkbox est sélectionnée, exécutez votre code ici
                    handleCheckboxSelected(index);
                } else {
                    // Checkbox est désélectionnée, exécutez votre code ici
                    handleCheckboxDeselected(index);
                }
            }
        });     
	    }

	    //PlanningId.getItems().clear(); // Clear existing items before adding new ones

	    //PlanningId.getItems().addAll(PlanningArray);	
	    //lundi.setOnAction(this::getCheckJour);
		//PlanningId.setOnAction(this::getCheck);
		deleteOrphanedPlanningRows();
	    if (!checkIfRowExists()) {
	        // Insert the default row
	        insertDefaultRow();
	    }
		CoachShowData();
	}
	
	private void deleteOrphanedPlanningRows() {
	    // Perform the necessary database DELETE query to remove orphaned planning rows
	    // Example:
	    // You can use a DELETE query with a subquery to delete rows from the planning table
	    // where the ID does not exist in the coach table

	    String deleteQuery = "DELETE FROM planning WHERE code NOT IN (SELECT planningid FROM coach)";
	    DatabaseConnector connectNow = new DatabaseConnector();
	    Connection connectDB = connectNow.getConnection();
	    try
	    {
	        PreparedStatement statement = connectDB.prepareStatement(deleteQuery);
	        int rowsAffected = statement.executeUpdate();
	        if (rowsAffected > 0) {
	            updateCoachIdForDeletedPlanning();
	        }
	        System.out.println("Deleted orphaned planning rows: " + rowsAffected);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	private void updateCoachIdForDeletedPlanning() throws SQLException {
	    // Perform the necessary database UPDATE query to update coach_id in coach table
	    // Example:
	    // You can use an UPDATE query to set coach_id to NULL for the deleted planning rows
	    DatabaseConnector connectNow = new DatabaseConnector();
	    Connection connectDB = connectNow.getConnection();

	    String updateQuery = "UPDATE coach SET planningid = 1 WHERE planningid NOT IN (SELECT code FROM planning)";

	    try (PreparedStatement statement = connectDB.prepareStatement(updateQuery)) {
	        int rowsAffected = statement.executeUpdate();
	        System.out.println("Updated planningid for deleted planning rows: " + rowsAffected);
	    }
	}
	
	private boolean checkIfRowExists() {
	    DatabaseConnector connectNow = new DatabaseConnector();
	    Connection connectDB = connectNow.getConnection();

	    String DisplayQuery = "SELECT * FROM planning WHERE code = 1";

	    try {
	        PreparedStatement statement = connectDB.prepareStatement(DisplayQuery);
	        ResultSet resultSet = statement.executeQuery();

	        // Check if any row exists in the ResultSet
	        if (resultSet.next()) {
	            // Row exists
	            return true;
	        } else {
	            // Row does not exist
	            return false;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Close the database connection and resources
	        try {
	            connectDB.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return true;
	}


	private void insertDefaultRow() {
	    DatabaseConnector connectNow = new DatabaseConnector();
	    Connection connectDB = connectNow.getConnection();

	    String DisplayQuery = "INSERT INTO planning(code, JourPresence, Matin, Apresmidi, Nuit) VALUES (?, ?, ?, ?, ?)";

	    try {
	        PreparedStatement statement = connectDB.prepareStatement(DisplayQuery);
	        statement.setInt(1, 1);
	        statement.setString(2, "1010110");
	        statement.setString(3, "1000010");
	        statement.setString(4, "1010000");
	        statement.setString(5, "1000110");
	        statement.executeUpdate();

	        System.out.println("Default row inserted successfully.");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Close the database connection and resources
	        try {
	            connectDB.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    System.out.println("Inserting default row...");

	}

    public void handleCheckboxSelected(int i) {
        for (int j = 0; j < 3; j++) {
            checkboxes[i * 3 + j].setVisible(true);
        }
    }

    public void handleCheckboxDeselected(int i) {
        for (int j = 0; j < 3; j++) {
            checkboxes[i * 3 + j].setVisible(false);
        }
    }



    // private CheckBox[] checkboxes0= new CheckBox[]{lundi,mardi,mercredi,jeudi,vendredi,samedi ,dimanche};
    // private CheckBox[] checkboxes = new CheckBox[]{lundi1, lundi2, lundi3, mardi1, mardi2, mardi3, mercredi1, mercredi2, mercredi3,
    //        jeudi1, jeudi2, jeudi3, vendredi1, vendredi2, vendredi3, samedi1, samedi2, samedi3, dimanche1, dimanche2, dimanche3};
    @FXML
    void AddPlanning(ActionEvent event) {
    	Integer indexTime = 0,indexJour = 0;
    	String jour = "", matin = "", apresmidi = "",nuit = "";
    	if(IdcoachAff.getText().equals("idCoach"))
    	{
            planMessage.setText("Planning: Faut séléctionner coach dans tableau");
    		return;
    	}
	    for (int i = 0; i < 7; i++) {
	    	if(checkboxes0[i].isSelected())
	    	{
	    		jour+="1";
	    		indexTime = 0;
		        for (int j = 0; j < 1; j++) 
		        {
			    	if(checkboxes[i * 3 + 0].isSelected())
			    	{
			    		matin+="1";
			            //checkboxes[i * 3 + 0].setVisible(false);
			    	}
			    	else
			    	{
			    		indexTime++;
			    		matin+="0";
			    	}
			    	if(checkboxes[i * 3 + 1].isSelected())
			    	{
			    		apresmidi+="1";
			            //checkboxes[i * 3 + 0].setVisible(false);
			    	}
			    	else
			    	{
			    		indexTime++;
			    		apresmidi+="0";
			    	}
			    	if(checkboxes[i * 3 + 2].isSelected())
			    	{
			    		nuit+="1";
			            //checkboxes[i * 3 + 0].setVisible(false);
			    	}
			    	else
			    	{
			    		indexTime++;
			    		nuit+="0";
			    	}

		        }
	    	}
	    	else
	    	{
	    		indexJour++;
	    		jour+="0";
	    		matin+="0";
	    		apresmidi+="0";
	    		nuit+="0";
	    	}
	    }
	    
    	if(indexTime == 3)
    	{
            planMessage.setText("Planning: Checkboxes pas séléctionées en jour");
    		return;
    	}
    	if(indexJour >= 6)
    	{
            planMessage.setText("Planning: Faut ajouter au moins 2 jours");
    		return;
    	}
    	
    	
    	DatabaseConnector connectNow = new DatabaseConnector();
    	Connection connectDB = connectNow.getConnection();

    	String DisplayQuery = "INSERT INTO planning(JourPresence,Matin,Apresmidi,Nuit) VALUES (?, ?, ?, ?)";
    	String selectQuery = "SELECT * FROM planning ORDER BY code DESC LIMIT 1";
    	String updateQuery = "UPDATE coach SET planningid = ? WHERE id = ?";
    	int plan_id = -1;
    	try {
    		
            PreparedStatement statement = connectDB.prepareStatement(DisplayQuery);
            statement.setString(1, jour);
            statement.setString(2, matin);
            statement.setString(3, apresmidi);
            statement.setString(4, nuit);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Insert successful");
            } else {
                System.out.println("Insert failed");
            }
            
            PreparedStatement selectStatement = connectDB.prepareStatement(selectQuery);
            ResultSet resultSet = selectStatement.executeQuery();
            resultSet.next();
            plan_id = resultSet.getInt("code");
            PreparedStatement updateStatement = connectDB.prepareStatement(updateQuery);
            updateStatement.setInt(1,resultSet.getInt("code"));
            updateStatement.setInt(2,Integer.parseInt(IdcoachAff.getText()));
            updateStatement.executeUpdate();
            int rowsUpdated = updateStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Update successful");
            } else {
                System.out.println("Update failed");
            }
            
	    }
    	catch(Exception e)
    	{
	        e.printStackTrace();
	    }
    	
    	CoachShowData();
    	if(plan_id != -1)
    		JourPresenceShowData(plan_id);
	    System.out.println("Day : "+ jour+", matin: "+matin+", Apres midi: "+apresmidi+", nuit: "+nuit);
    }
	
	
	
	
	
	

//	public void getCheck(ActionEvent event){
//		Planning selectedPlanning = PlanningId.getValue();
//		int jourPresence = selectedPlanning.getJourPresence();
//		String PlanIdStr = String.valueOf(jourPresence);
//
//		if(PlanIdStr.charAt(0) == '0') { System.out.println("NoLu");} else {System.out.println("YesL");}
//		if(PlanIdStr.charAt(1) == '0') { System.out.println("NoMa");} else {System.out.println("YesMa");}
//		if(PlanIdStr.charAt(2) == '0') { System.out.println("NOMe");} else {System.out.println("YesMe");}
//		if(PlanIdStr.charAt(3) == '0') { System.out.println("NoJe");} else {System.out.println("YesJe");}
//		if(PlanIdStr.charAt(4) == '0') { System.out.println("NoVe");} else {System.out.println("YesVe");}
//		if(PlanIdStr.charAt(5) == '0') { System.out.println("NoSa");} else {System.out.println("YesSa");}
//		if(PlanIdStr.charAt(6) == '0') { System.out.println("NoDi");} else {System.out.println("YesDi");}
//		
//		
//		
//	    for (int i = 0; i < jour.length; i++) {
//	    	if(PlanIdStr.charAt(i) == '0') {
//		        for (int j = 0; j < 3; j++) {
//		            checkboxes[i * 3 + j].setVisible(true);
//		        }
//	    	}
//	    }
//		
//	}
	
	
	
	public ArrayList <Planning> PlanningListData(){
    	DatabaseConnector connectNow = new DatabaseConnector();
    	Connection connectDB = connectNow.getConnection();
    	
    	ArrayList<Planning> ListData = new ArrayList<>();
    	String DisplayQuery="SELECT * FROM planning";
    	try {
    		
            PreparedStatement statement = connectDB.prepareStatement(DisplayQuery);
            ResultSet resultSet = statement.executeQuery();
            Planning pl ;
     
            while(resultSet.next())
            {
            	pl = new Planning(resultSet.getInt("code"),resultSet.getString("JourPresence"),resultSet.getString("Matin"),resultSet.getString("Apresmidi"),resultSet.getString("Nuit"));
            	ListData.add(pl);
            }
    	
    }catch(Exception e) {
        e.printStackTrace();
    }
    	return ListData;
   
    }
}
