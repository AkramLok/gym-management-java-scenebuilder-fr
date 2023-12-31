package application;

public class Coach  {
	private Integer id;
    private String nom;
    private String prenom;
    private String adresse;
    private String tel;
    private Double salaire;
    private Integer planningid;
    
    
    
	public Coach(Integer id, String nom, String prenom, String adresse, String tel, Double salaire,Integer planningid) {
		
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.tel = tel;
		this.salaire = salaire;
		this.planningid = planningid;

	}

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
	
	public String getSalaire() {
		return "" +salaire;
	}
	
	public int getPlanningid() {
		return planningid;
	}
	

    
}