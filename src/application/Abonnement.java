package application;

public class Abonnement {
	private Integer code;
    private String type;
    private Integer durée;
    private Double tarif;

	public Abonnement(Integer code, String type, Integer durée, Double tarif) {
	//	super();
		this.code = code;
		this.type = type;
		this.durée = durée;
		this.tarif = tarif;
	}	
    
    // Getters
    public int getCode() {
        return code;
    }

    public String getType() {
        return type;
    }

    public int getDurée() {
        return durée;
    }

    public double getTarif() {
        return tarif;
    }

    
}
