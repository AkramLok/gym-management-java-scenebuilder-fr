package application;
public class Admin{
    private String login;
    private String passwd;
    private Integer id;
    private String nom;
    private String prenom;
    private String adresse;
    private String tel;

    
    public Admin(Integer id, String nom, String prenom, String adresse, String tel, String login, String passwd) {
        this.id=id;
        this.nom=nom;
        this.prenom=prenom;
        this.adresse=adresse;
        this.tel=tel;
        this.login = login;
        this.passwd = passwd;
    }

    // Getters
    public String getLogin() {
        return login;
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

	public String getPassword() {
        return passwd;
    }

   

   
}

