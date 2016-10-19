package fr.lille1.json.json;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.lille1.json.domaine.Adresse;
import fr.lille1.json.domaine.Contact;

public class ManageActivity extends AppCompatActivity
{
    public static final String BUNDLE_KEY_GET_CONTACT = "contact";
    private EditText prenom, nom, numeroPorte, codePostal, rue, ville;
    private String editJson;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);

        this.prenom = ((EditText)super.findViewById(R.id.prenom));
        this.nom = ((EditText)super.findViewById(R.id.nom));
        this.numeroPorte = ((EditText)super.findViewById(R.id.numeroPorte));
        this.codePostal = ((EditText)super.findViewById(R.id.codePostal));
        this.rue = ((EditText)super.findViewById(R.id.rue));
        this.ville = ((EditText)super.findViewById(R.id.ville));

        /* Remplissage des EditText lorsqu'on demande cette activity afin de modifier un contact déjà existant */
        this.editJson = this.getIntent().getStringExtra(BUNDLE_KEY_GET_CONTACT);
        if (this.editJson != null)
        {
            try {
                Contact contact = Contact.jsonToContact(this.editJson);
                this.prenom.setText(contact.getPrenom());
                this.nom.setText(contact.getNom());

                this.numeroPorte.setText(String.valueOf(contact.getAdresse().getNumeroPorte()));
                this.codePostal.setText(String.valueOf(contact.getAdresse().getCodePostal()));
                this.ville.setText(contact.getAdresse().getVille());
                this.rue.setText(contact.getAdresse().getRue());

                for (String s : contact.getTelephones())
                    this.addTelephoneToView().setText(s);
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ((Button)super.findViewById(R.id.btnValider)).setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                int inumeroPorte; try { inumeroPorte = Integer.valueOf(numeroPorte.getText().toString());} catch(NumberFormatException e) {inumeroPorte = 0;}
                int icodePostal; try { icodePostal = Integer.valueOf(codePostal.getText().toString());} catch(NumberFormatException e) {icodePostal = 0;}

                try {
                    if (editJson != null)
                        getIntent().putExtra(MainActivity.BUNDLE_KEY_FOR_JSON_CONTACT_TO_EDIT, editJson);

                    /* création et sérialisation du contact en JSON pour le retour de contact */
                    getIntent().putExtra(MainActivity.BUNDLE_KEY_FOR_JSON_CONTACT_TO_ADD,
                        Contact.contactToJson(new Contact(
                            nom.getText().toString(),
                            prenom.getText().toString(),
                            new Adresse(rue.getText().toString(), ville.getText().toString(), inumeroPorte, icodePostal),
                            this.getTelephones()
                        ))
                    );
                    setResult(RESULT_OK, getIntent());
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finish();
            }

            /* permet de récupérer tous les numéros se trouvant dans le formulaire */
            private final List<String> getTelephones()
            {
                LinearLayout telephonesLinearLayout = (LinearLayout) ManageActivity.this.findViewById(R.id.linearLayoutTelephone);

                /* récupération des childs du LinearLayout (chaque child correspond à un EditText d'un numéro téléphone */
                List<String> result = new ArrayList<String>();
                for (int i = 0; i < telephonesLinearLayout.getChildCount(); i++)
                    result.add(((EditText)telephonesLinearLayout.getChildAt(i)).getText().toString());

                return result;
            }
        });

        ((Button)super.findViewById(R.id.btnAjouterNumero)).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                /* maximum 3 numéros */
                if (countTelephone == 3)
                    return;

                addTelephoneToView();
            }
        });
    }

    /* permet d'ajouter un textview telephone dynamiquement */
    private int countTelephone = 0;
    private EditText addTelephoneToView()
    {
        LinearLayout telephonesLayout = (LinearLayout) ManageActivity.this.findViewById(R.id.linearLayoutTelephone);
        EditText telephone = new EditText(this);
        telephone.setHint(String.format(ManageActivity.this.getString(R.string.telephoneInput), ++countTelephone));
        telephonesLayout.addView(telephone);

        return telephone;
    }
}
