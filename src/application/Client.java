package application;

import java.time.LocalDate;

public class Client {
	private String cin;
	private Integer id;
    private String nom;
    private String prenom;
    private String adresse;
    private String tel;
    private String Gender;

	public Client(Integer id,String cin, String nom, String prenom, String adresse, String tel,String gender) {
//		super();
		this.id = id;
		this.nom = nom;
		this.cin=cin;
		this.prenom = prenom;
		this.adresse = adresse;
		this.tel = tel;
		this.Gender=gender;
	}
	
   


    
    // Getters
    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getTel() {
        return tel;
    }
    public String getCin() {
        return cin;
    }
    public String getGender() {
        return Gender;
    }
    

    
    
}

