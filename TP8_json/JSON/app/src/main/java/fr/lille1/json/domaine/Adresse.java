package fr.lille1.json.domaine;

public class Adresse
{
    private String rue, ville;
    private int numeroPorte, codePostal;

    public Adresse(String rue, String ville, int numeroPorte, int codePostal) {
        this.rue = rue;
        this.ville = ville;
        this.numeroPorte = numeroPorte;
        this.codePostal = codePostal;
    }

    public Adresse() {

    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public int getNumeroPorte() {
        return numeroPorte;
    }

    public void setNumeroPorte(int numeroPorte) {
        this.numeroPorte = numeroPorte;
    }

    public int getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Adresse adresse = (Adresse) o;

        if (numeroPorte != adresse.numeroPorte) return false;
        if (codePostal != adresse.codePostal) return false;
        if (rue != null ? !rue.equals(adresse.rue) : adresse.rue != null) return false;
        return ville != null ? ville.equals(adresse.ville) : adresse.ville == null;

    }

    @Override
    public int hashCode() {
        int result = rue != null ? rue.hashCode() : 0;
        result = 31 * result + (ville != null ? ville.hashCode() : 0);
        result = 31 * result + numeroPorte;
        result = 31 * result + codePostal;
        return result;
    }

    @Override
    public String toString() {
        return "Adresse{" +
                "rue='" + rue + '\'' +
                ", ville='" + ville + '\'' +
                ", numeroPorte=" + numeroPorte +
                ", codePostal=" + codePostal +
                '}';
    }
}