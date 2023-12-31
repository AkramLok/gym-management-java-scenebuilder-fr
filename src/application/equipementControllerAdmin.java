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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;


import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javafx.scene.Node;

public class equipementControllerAdmin implements Initializable{
	private Stage stage;
	private Scene scene;
	private Parent root;
 	@FXML
    private Button btnReclamer;

   

    @FXML
    private Button btndeleteequip;

	@FXML
    private TextField Nbrequipement;

   

    @FXML
    private Button btnaddequip;

   

    @FXML
    private TextField libelleequipement;

  

    @FXML
    private Button showdataequip;

    @FXML
    private TableColumn<Equipement, String> code_equip;

    @FXML
    private TableColumn<Equipement, String> libelle_equip;
    @FXML
    private TableColumn<Equipement, Integer> nombre_equip;
    @FXML
    private TableColumn<Equipement, String> status_equip;

    @FXML
    private TableView<Equipement> tableEquipement;

    @FXML
    private Label errorAdde;
    @FXML
    void Addequipement(ActionEvent event) {
    	DatabaseConnector connectNow = new DatabaseConnector();
    	Connection connectDB = connectNow.getConnection();
    	if (libelleequipement.getText().isEmpty() ||Nbrequipement.getText().isEmpty()) {
   		 errorAdde.setText("Veuillez remplir tous les champs");
	            return; 
	        }
    	
        String UpdateQuery = "Update  équipement SET nombre=? WHERE libellé=?";
        try {
            PreparedStatement statement = connectDB.prepareStatement(UpdateQuery);
            
            statement.setString(2, libelleequipement.getText());
            statement.setString(1, Nbrequipement.getText());
           
         
            int rowsAffected = statement.executeUpdate();
            EquipementShowData();
            clear();

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
    private Button gobackbtn;


    @FXML
    void goBack(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("ManagpageAdmin.fxml"));	
 		root = loader.load();	
 		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
 		scene = new Scene(root);
 		stage.setScene(scene);
 		stage.show();
    }

    
    
    
    
    
    public ObservableList<Equipement> EquipListData(){
    	DatabaseConnector connectNow = new DatabaseConnector();
    	Connection connectDB = connectNow.getConnection();
    	
    	ObservableList<Equipement> ListData = FXCollections.observableArrayList();
    	String DisplayQuery="SELECT * FROM équipement";
    	try {
    		
            PreparedStatement statement = connectDB.prepareStatement(DisplayQuery);
            ResultSet resultSet = statement.executeQuery();
            
            Equipement eqp ;
            
            while(resultSet.next())
            {
            	eqp = new Equipement(resultSet.getString("code"),resultSet.getString("libellé"), resultSet.getString("status"),resultSet.getInt("nombre"));
            	ListData.add(eqp);
            }
    	
    }catch(Exception e) {
        e.printStackTrace();
    }
    	return ListData;
   
}
    
    
    private ObservableList<Equipement> Equipement;
    public void EquipementShowData()
    {
    	Equipement = EquipListData();
    	code_equip.setCellValueFactory(new PropertyValueFactory<>("code"));
    	libelle_equip.setCellValueFactory(new PropertyValueFactory<>("libelle"));
    	nombre_equip.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    	status_equip.setCellValueFactory(new PropertyValueFactory<>("status"));
    	
    	tableEquipement.setItems(Equipement);
    	
    }
 
    @FXML
    void handleMouseAction(MouseEvent event) {
    	Equipement eq = tableEquipement.getSelectionModel().getSelectedItem();
    	 Nbrequipement.setText(eq.getNombre());
    	 libelleequipement.setText(eq.getLibelle());
    	
        
        
    }
    private String[] Etat= {"endommagé","bon-état"};
    
   
    @FXML
    void reclamerEquip(ActionEvent event) {
    	DatabaseConnector connectNow = new DatabaseConnector();
    	Connection connectDB = connectNow.getConnection();
    	if (Statusequipement.getValue() == null) {
      		 errorAdde.setText("Veuillez choisir l'état !!");
   	            return; 
   	        }
    	
        String UpdateQuery = "Update  équipement SET status=? WHERE libellé=?";
        try {
            PreparedStatement statement = connectDB.prepareStatement(UpdateQuery);
            statement.setString(1,Statusequipement.getValue() );
            statement.setString(2, libelleequipement.getText());
           
           
         
            int rowsAffected = statement.executeUpdate();
            EquipementShowData();
            clear();

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
    void deleteEquip(ActionEvent event) {
    	DatabaseConnector connectNow = new DatabaseConnector();
    	Connection connectDB = connectNow.getConnection();
    	String DeletQuery = "DELETE FROM  équipement WHERE libellé=?";
    	try {
            PreparedStatement statement = connectDB.prepareStatement(DeletQuery);
            statement.setString(1, libelleequipement.getText());
            statement.executeUpdate();
           
            
	       
            
        } catch (Exception e) {
        e.printStackTrace();
        }
    	 EquipementShowData();
         clear();

    }




    @FXML
    private ChoiceBox<String> Statusequipement;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		EquipementShowData();
		Statusequipement.getItems().addAll(Etat);
		
	}
	public void clear() {
		libelleequipement.setText("");
        Nbrequipement.setText("");
        errorAdde.setText("");
        Statusequipement.setValue(null);
        
        
	}

}
