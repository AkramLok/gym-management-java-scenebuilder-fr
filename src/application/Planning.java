package application;

public class Planning {
	private Integer code;
    private String JourPresence;
    private String Matin;
    private String Apresmidi;
    private String Nuit;


	public Planning(Integer code, String JourPresence, String Matin, String Apresmidi,String Nuit) {
	//	super();
		this.code = code;
		this.JourPresence = JourPresence;
		this.Matin = Matin;
		this.Apresmidi = Apresmidi;
		this.Nuit = Nuit;
	}	
    
    // Getters
    public int getCode() {
        return code;
    }

    public String getJourPresence() {
        return JourPresence;
    }

    public String getMatin() {
        return Matin;
    }

    public String getApresmidi() {
        return Apresmidi;
    }
    
    public String Nuit() {
        return Nuit;
    }
}