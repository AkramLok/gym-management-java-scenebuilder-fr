package application;

import java.io.IOException;
import java.util.Date;
import java.util.Calendar;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import javafx.scene.Node;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AlerteController implements Initializable{
	private Stage stage;
	private Scene scene;
	private Parent root;
	 @FXML
	    void gobacktobilan(ActionEvent event) throws IOException{
	      	
	      	FXMLLoader loader = new FXMLLoader(getClass().getResource("Bilan.fxml"));	
	  		root = loader.load();	
	  		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	  		scene = new Scene(root);
	  		stage.setScene(scene);
	  		stage.show();
	      }
	 @FXML
	    private TableColumn<Equipement, String> codeEnd;

	    @FXML
	    private TableColumn<Equipement, String> etatEnd;

	    @FXML
	    private TableColumn<Equipement, String> libelleEnd;

	 

	    @FXML
	    private TableView<Equipement> tableEquipementEndomage;

	    public ObservableList<Equipement> EquipListData(){
	    	DatabaseConnector connectNow = new DatabaseConnector();
	    	Connection connectDB = connectNow.getConnection();
	    	
	    	ObservableList<Equipement> ListData = FXCollections.observableArrayList();
	    	String DisplayQuery="SELECT * FROM équipement where status=?";
	    	try {
	    		
	            PreparedStatement statement = connectDB.prepareStatement(DisplayQuery);
	            statement.setString(1, "endommagé");
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
	    	codeEnd.setCellValueFactory(new PropertyValueFactory<>("code"));
	    	libelleEnd.setCellValueFactory(new PropertyValueFactory<>("libelle"));
	    	etatEnd.setCellValueFactory(new PropertyValueFactory<>("status"));
	    	
	    	tableEquipementEndomage.setItems(Equipement);
	    	
	    }
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			EquipementShowData();
			
		}
	 

}
