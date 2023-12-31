package application;

public class Paiement {
	private Integer code;
    private Integer Id_client;
    private String Type_Ab;
    private String Date_ab;
    private String Date_fin_ab;
    private String Statut;

	public Paiement(Integer code, Integer Id_client, String Type_Ab, String Date_ab,String Date_fin_ab, String Statut) {
	//	super();
		this.code = code;
		this.Id_client = Id_client;
		this.Type_Ab = Type_Ab;
		this.Date_ab = Date_ab;
		this.Date_fin_ab = Date_fin_ab;
		this.Statut = Statut;
	}

    // Getters
    public int getCode() {
        return code;
    }

    public int getId_client() {
        return Id_client;
    }

    public String getType_Ab() {
        return Type_Ab;
    }

    public String getDate_ab() {
        return Date_ab;
    }
    
    public String getDate_fin_ab() {
        return Date_fin_ab;
    }
    
    public String getStatut() {
        return Statut;
    }

}