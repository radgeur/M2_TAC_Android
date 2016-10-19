package fr.lille1.json.domaine;

import android.util.JsonReader;
import android.util.JsonWriter;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class Contact
{
    /* Constante des noms des clés à utiliser */
    public static final String KEY_PRENOM = "prenom", KEY_NOM = "nom";
    public static final String KEY_ADRESSE = "adresse", KEY_ADRESSE_NUMPORTE = "numero", KEY_ADRESSE_RUE = "rue", KEY_ADRESSE_VILLE = "ville", KEY_ADRESSE_CP = "codePostal";
    public static final String KEY_TELEPHONES = "telephones";

    /* Code à compléter : tranformer une instance contact en string JSON */
    public static String contactToJson(Contact contact) throws JSONException, IOException
    {
        /* TP avec org.json */
        JSONObject parentObject = new JSONObject(); /* création de l'objet parent */
        parentObject.put(KEY_PRENOM, contact.getPrenom()); /* put de "nom": "valeur" au json */

        /*
         * A compléter
         * Type de résultat attendu :
         * {
                "prenom": "Khaled",
                "nom": "Belkadi",
                "adresse": {
                    "numero": 10,
                    "rue": "Hum",
                    "codePostal": 59260,
                    "ville": "Villeneuve D'Ascq"
                },
                "telephones": ["06 10 10 15 23", "06 78 17 94 44"]
            }
         */

        return parentObject.toString(); /* Retour de l'instance contact au format JSON en String */




//        /* TP avec la version android.util.JsonWriter (selon votre choix) */
//        /* création du stream qui va contenir l'objet json que nous allons créer */
//        StringWriter resultToStringWriter = new StringWriter();
//        /* instance d'un manageur JsonWriter utilisant le flux de donnée resultToStringWriter*/
//        JsonWriter jsonWriter = new JsonWriter(resultToStringWriter);
//
//        jsonWriter.beginObject(); /* ecriture de l'objet principal */
//        jsonWriter.name(KEY_PRENOM).value(contact.getPrenom()); /* ecriture de la pair nom/prenom */
//
//        /*
//         * A compléter
//         * Type de résultat attendu :
//         * {
//                "prenom": "Khaled",
//                "nom": "Belkadi",
//                "adresse": {
//                    "numero": 10,
//                    "rue": "Hum",
//                    "codePostal": 59260,
//                    "ville": "Villeneuve D'Ascq"
//                },
//                "telephones": ["06 10 10 15 23", "06 78 17 94 44"]
//            }
//         */
//
//        /* fermeture de l'object parent */
//        jsonWriter.endObject();
//
//        /* fermeture des streams */
//        jsonWriter.close();
//        resultToStringWriter.close();
//
//        return resultToStringWriter.toString();
    }

    /*
     * Code à compléter : convertir la string JSON en Objet contact
     */
    public static Contact jsonToContact(String json) throws JSONException, IOException
    {
        /* TP version org.json */
        JSONObject jsonObject = new JSONObject(json); /* conversion de la string Json en Map<String,JsonValue> */

        Contact contact = new Contact(); /* le contact à alimenter via les setters */
        contact.setPrenom(jsonObject.getString(KEY_PRENOM)); /* il suffit de lire dans la map et d'alimenter l'instance contact */

        /* A compléter */

        return contact;


        /* TP version android.util.JsonReader (plus complexe) */
//        Contact contact = new Contact();
//        StringReader sr = new StringReader(json);
//        JsonReader jsonReader = new JsonReader(sr);
//
//        jsonReader.beginObject(); /* lecture de l'objet parent */
//        while (jsonReader.hasNext()) /* lecture de clé tanqu'il y a en une non lue */
//        {
//            String key = jsonReader.nextName(); /* lecture de la clé suivante */
//            switch (key)
//            {
//                case KEY_PRENOM: /* quand la clé est prenom */
//                    contact.setPrenom(jsonReader.nextString()); /* alors garnir prenom par la chaine de caractère qui suit */
//                    break;
//
//                case KEY_ADRESSE: /* quand la clé est adresse */
//                    Adresse adresse = new Adresse();
//                    jsonReader.beginObject(); /* alors lecture de l'objet json adresse */
//                    while (jsonReader.hasNext()) /* tanqu'il y a une clé non lue dans l'objet adresse */
//                    {
//                        key = jsonReader.nextName();
//                        switch (key)
//                        {
//                            case KEY_ADRESSE_NUMPORTE: /* si la clé est num de porte */
//                                adresse.setNumeroPorte(jsonReader.nextInt());
//                                break;
//                        }
//                    }
//                    jsonReader.endObject(); /* fermeture de l'objet json adresse */
//                    contact.setAdresse(adresse);
//                    break;
//
//                case KEY_TELEPHONES:
//                    jsonReader.beginArray();
//                    /* A compléter parcourir les valeurs de jsonReader et les ajoutés à contact.getTelephones() */
//                    jsonReader.endArray();
//                    break;
//            }
//        }
//        jsonReader.endObject(); /* fermeture de l'objet parent */
//
//        jsonReader.close(); sr.close(); /* fermeture des streams */
//
//        return contact;
    }

    private String nom, prenom;
    private Adresse adresse;
    private List<String> telephones;
    public Contact() {
        this.setTelephones(new ArrayList<String>());
    }

    public Contact(String nom, String prenom, Adresse adresse, List<String> telephones) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephones = telephones;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public List<String> getTelephones() {
        return telephones;
    }

    public void setTelephones(List<String> telephones) {
        this.telephones = telephones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (nom != null ? !nom.equals(contact.nom) : contact.nom != null) return false;
        if (prenom != null ? !prenom.equals(contact.prenom) : contact.prenom != null) return false;
        if (adresse != null ? !adresse.equals(contact.adresse) : contact.adresse != null)
            return false;
        return telephones != null ? telephones.equals(contact.telephones) : contact.telephones == null;

    }

    @Override
    public int hashCode() {
        int result = nom != null ? nom.hashCode() : 0;
        result = 31 * result + (prenom != null ? prenom.hashCode() : 0);
        result = 31 * result + (adresse != null ? adresse.hashCode() : 0);
        result = 31 * result + (telephones != null ? telephones.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", adresse=" + adresse +
                ", telephones=" + telephones +
                '}';
    }

}
