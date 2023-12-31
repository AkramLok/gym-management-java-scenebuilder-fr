package application;
import javafx.scene.text.Font;
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
import javafx.geometry.Side;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import javafx.scene.Node;

public class BilanController implements Initializable{
	private Stage stage;
	private Scene scene;
	private Parent root;

	 @FXML
	    private LineChart<String,Number> shartData;
	 
	 @FXML
	    private PieChart dataAbonnement;
	 @FXML
	    private Label numberofCLient;
	 @FXML
	    private Label NbrCoach;
	
	 @FXML
	    void goBack(ActionEvent event) throws IOException {
		 FXMLLoader loader = new FXMLLoader(getClass().getResource("Managpage.fxml"));	
	 		root = loader.load();	
	 		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	 		scene = new Scene(root);
	 		stage.setScene(scene);
	 		stage.show();

	    }
	 
	
	 private ObservableList data;
	 private ObservableList data1;
	 private ObservableList data2;
	 
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		piechart();
		clientNumber();
		coachNumber();
		revenue();
		piechartrevenue();
		piechartnumber();
		reclamer();
	}
	private int cptAlerte=0;
	public void clientNumber() {
		DatabaseConnector connectNow = new DatabaseConnector();
    	Connection connectDB = connectNow.getConnection();
    	data = FXCollections.observableArrayList();
    	
    	String sql = "SELECT COUNT(id) AS clientnumber FROM client";
    	try {
            PreparedStatement statement = connectDB.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
          if(resultSet.next())
          {
        	  int clientnumber = resultSet.getInt("clientnumber");
        	  numberofCLient.setText("+" + clientnumber + " membres");
          }
           
           
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		
	}
	public void coachNumber() {
		DatabaseConnector connectNow = new DatabaseConnector();
    	Connection connectDB = connectNow.getConnection();
    	data = FXCollections.observableArrayList();
    	
    	String sql = "SELECT COUNT(id) AS coachnumber FROM coach";
    	try {
            PreparedStatement statement = connectDB.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
          if(resultSet.next())
          {
        	  int coachnumber = resultSet.getInt("coachnumber");
        	  NbrCoach.setText("+" + coachnumber + " entraineurs");
          }
           
           
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		
	}
	public void piechart() {
		
    	DatabaseConnector connectNow = new DatabaseConnector();
    	Connection connectDB = connectNow.getConnection();
    	data = FXCollections.observableArrayList();
    	
    	String sql = "SELECT Type_Ab, COUNT(DISTINCT Id_client) AS clientCount FROM paiement GROUP BY Type_Ab";
    	try {
            PreparedStatement statement = connectDB.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

           while(resultSet.next())
           {
        	   data.add(new PieChart.Data(resultSet.getString(1),resultSet.getInt(2)));
        	   
           }
           dataAbonnement.getData().addAll(data);
           dataAbonnement.setTitle("Répartition des différentes types d'abonnements");
           
           dataAbonnement.setClockwise(true);
           dataAbonnement.setLabelsVisible(false);
           dataAbonnement.setLegendSide(Side.LEFT);
           
           
           
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	 public void piechartnumber() {
		 DatabaseConnector connectNow = new DatabaseConnector();
	    	Connection connectDB = connectNow.getConnection();
	    	data2 = FXCollections.observableArrayList();
	    	
	    	String sql = "SELECT Gender, COUNT(DISTINCT id) AS clientGenderCount FROM client GROUP BY Gender ";
	    	try {
	            PreparedStatement statement = connectDB.prepareStatement(sql);
	            ResultSet resultSet = statement.executeQuery();
	           
	           while(resultSet.next())
	           {
	        	   data2.add(new PieChart.Data(resultSet.getString(1),resultSet.getInt(2)));
	        	  
	        	   
	           }
	           nbrdata.getData().addAll(data2);
	  	    	 nbrdata.setTitle("Répartition des clients selon leur genre ");
	  	    	 nbrdata.setClockwise(true);
	  	    	 nbrdata.setLabelsVisible(false);
	  	    	 nbrdata.setLegendSide(Side.LEFT);
	           
	           
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	         
	           
	
		}
	
	private double SommeAvance, SommeEnCours, SommePasse;
	int nbrEnAvance=0,nbrEnCours=0,nbrPasse=0;
	public void revenue() {
		DatabaseConnector connectNow = new DatabaseConnector();
    	Connection connectDB = connectNow.getConnection();
    	String DisplayQueryA = "SELECT * FROM Abonnement ";
    	String DisplayQuery = "SELECT * FROM Paiement";
    	
    	try {
      
            
            PreparedStatement statement = connectDB.prepareStatement(DisplayQuery);
            ResultSet resultSet = statement.executeQuery();
            double som=0.0,som1=0.0,som2=0.0,som0=0.0;
           
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
                		som+=resultSetA.getDouble("tarif");
                	  int duree = resultSetA.getInt("durée");
                	  System.out.println("Paiement in id " + resultSet.getInt("code")+", client n°"+ resultSet.getInt("Id_client") +" a abonnement " + resultSetA.getString("type")+" "+ resultSet.getString("Type_Ab")+ " avec duree "+ duree+" et la date est: "+ resultSet.getString("Date_ab"));
                	  d1 = resultSet.getString("Date_ab");  
                	  System.out.println("Date avant l'addition: "+d1);
                	  
                	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                	  Calendar cal = Calendar.getInstance();
                	  try{
                	     
                	     cal.setTime(sdf.parse(d1));
                	  }catch(ParseException e){
                	    e.printStackTrace();
                	   }
                	     
                	  
                	  cal.add(Calendar.DAY_OF_MONTH, duree);  
                	  
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
                    	  som0+=resultSetA.getDouble("tarif");
                          System.out.println("The date "+sdf.format(dateAbonnee)+" en cours");
                          statut = "En cours";
                          nbrEnCours++;
                      }
                      else if(dateAbonnee.after(startDate) && !dateAbonnee.before(endDate))
                      {
                    	  som1+=resultSetA.getDouble("tarif");
	              			System.out.println(sdf.format(dateAbonnee)+" passé");
	                        statut = "Passé";
	                        nbrPasse++;
                      }
                      else if(!dateAbonnee.after(startDate) && dateAbonnee.before(endDate))
                      {
                    	  som2+=resultSetA.getDouble("tarif");
                			System.out.println(sdf.format(dateAbonnee)+" abonnée en avance");
	                        statut = "En avance";
	                        nbrEnAvance++;
                      }
                      else
                      {
              			System.out.println("Impossible");
                      }
                      
                	}
                }
                
            }
            SommePasse=som1;
            SommeEnCours=som0;
            SommeAvance=som2;
            revenue.setText("+"+som+" DH");
            

	    }
    	catch(Exception e)
	    {
	        e.printStackTrace();
	    }
	}
	 @FXML
	    private Label revenue;
	 @FXML
	    private PieChart chartRevenu;

	 
	 public void piechartrevenue() {
			
	    	
	    	data1 = FXCollections.observableArrayList();
	    	
	    	data1.add(new PieChart.Data("en avance "+SommeAvance+" DH",SommeAvance));
	    	data1.add(new PieChart.Data("en cours "+SommeEnCours+" DH",SommeEnCours));
	    	data1.add(new PieChart.Data("passe "+SommePasse+" DH",SommePasse));
	        	   
	         
	    	chartRevenu.getData().addAll(data1);
	    	chartRevenu.setTitle("Répartition des revenus selon statut d'abonnements");
	    	chartRevenu.setClockwise(true);
	    	chartRevenu.setLabelsVisible(false);
	    	chartRevenu.setLegendSide(Side.LEFT);
	    	
	
		}
	  @FXML
	    private PieChart nbrdata;

	 
	  @FXML
	    private Label NbrAlerte;
	  public void reclamer()  {
		  DatabaseConnector connectNow = new DatabaseConnector();
	    	Connection connectDB = connectNow.getConnection();
	    	String sql = "SELECT libellé,status FROM équipement where status=?";
	    	try {
	            PreparedStatement statement = connectDB.prepareStatement(sql);
	            statement.setString(1,"endommagé");
	            ResultSet resultSet = statement.executeQuery();
	          while(resultSet.next())
	          {
	        	  cptAlerte++;
	          }
	           
	           
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    	
	    	NbrAlerte.setText("  "+cptAlerte+" Alertes");
            
	    	
		  
	  }
	 

	  @FXML
	    private Label AlertesMessage;
	  
	  @FXML
	    void gotoAlertes(MouseEvent event) throws IOException{
      	
      	FXMLLoader loader = new FXMLLoader(getClass().getResource("alertePage.fxml"));	
  		root = loader.load();	
  		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  		scene = new Scene(root);
  		stage.setScene(scene);
  		stage.show();
      }
	  
	  
	  
}
