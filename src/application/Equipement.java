package application;


public class Equipement {
    private String code;
    private String libelle;
    private String status;
    private Integer nombre;

    // Constructor
    public Equipement(String code, String libelle, String status,Integer nombre) {
        this.code = code;
        this.libelle = libelle;
        this.status = status;
        this.nombre=nombre;
    }

    // Getters and setters
    public String getCode() {
        return code;
    }



    public String getLibelle() {
        return libelle;
    }

  

    public String getStatus() {
        return status;
    }

   
    public String getNombre() {
		return ""+nombre;
	}

	
	
}

