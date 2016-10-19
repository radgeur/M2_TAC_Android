package fr.lille1.json.json;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;


import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.lille1.json.domaine.Adresse;
import fr.lille1.json.domaine.Contact;

public class MainActivity extends AppCompatActivity
{
    public static final String BUNDLE_KEY_FOR_JSON_CONTACT_TO_ADD = "result", BUNDLE_KEY_FOR_JSON_CONTACT_TO_EDIT = "oldContact";
    public static final int REQUEST_CODE_ADD_USER = 1, REQUEST_CODE_EDIT_USER = 2;
    private static List<Contact> contacts;
    private ContactsAdapater contactsAdapater;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Mock de base */
        this.contacts = new ArrayList<>();
        this.contacts.add(new Contact("Belkadi", "Khaled", new Adresse("Hum", "Villeneuve D'Ascq", 10, 59260), new ArrayList<String>() {{ add("06 10 10 15 23"); add("06 78 17 94 44");}}));

        /* Set de l'adapter pour la liste des contacts */
        this.contactsAdapater = new ContactsAdapater(this, this.contacts);
        ((ListView)findViewById(R.id.listView)).setAdapter(this.contactsAdapater);

        ((Button)super.findViewById(R.id.btnAjouter)).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivityForResult(new Intent(MainActivity.this, ManageActivity.class), REQUEST_CODE_ADD_USER);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (resultCode == RESULT_OK)
        {
            switch (requestCode)
            {
                case REQUEST_CODE_EDIT_USER:
                    /* l'edit est en réalité une supression et un rajout, on évite de jouer avec des ID pour ce TP */
                    try {
                        this.contactsAdapater.remove(Contact.jsonToContact(data.getStringExtra(BUNDLE_KEY_FOR_JSON_CONTACT_TO_EDIT)));
                    } catch (JSONException e) {
                        e.printStackTrace();
                        break;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                case REQUEST_CODE_ADD_USER:
                    try
                    {
                        this.contactsAdapater.add(Contact.jsonToContact(data.getStringExtra(BUNDLE_KEY_FOR_JSON_CONTACT_TO_ADD)));
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }
}
