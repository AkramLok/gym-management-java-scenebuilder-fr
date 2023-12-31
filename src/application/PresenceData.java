package application;

public class PresenceData {
    private String jour;
    private char matin;
    private char apresmidi;
    private char nuit;

    public PresenceData(String jour, char matin, char apresmidi, char nuit) {
        this.jour = jour;
        this.matin = matin;
        this.apresmidi = apresmidi;
        this.nuit = nuit;
    }

    public String getJour() {
        return jour;
    }

    public char getMatin() {
        return matin;
    }

    public char getApresmidi() {
        return apresmidi;
    }

    public char getNuit() {
        return nuit;
    }
}
