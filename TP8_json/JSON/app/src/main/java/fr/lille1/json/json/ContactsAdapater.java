package fr.lille1.json.json;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

import fr.lille1.json.domaine.Contact;

public class ContactsAdapater extends ArrayAdapter<Contact>
{
    private Context context;
    private List<Contact> contacts;

    public ContactsAdapater(Context context, List<Contact> contacts)
    {
        super(context, 0, contacts);
        this.context = context;
        this.contacts = contacts;
    }

    @Override
    public int getCount() {
        return this.contacts.size();
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent)
    {
        final Contact contact = getItem(position);

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.contact_list, parent, false);

        /* Afficher les valeurs du nom et prénom de la current view en provenance de la listview */
        ((TextView)convertView.findViewById(R.id.nom)).setText(contact.getNom());
        ((TextView)convertView.findViewById(R.id.prenom)).setText(contact.getPrenom());


        ((Button)convertView.findViewById(R.id.btnEdit)).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
               try {
                   /* Je souhaites éditer le contact 'contact' request code: MainActivity.REQUEST_CODE_EDIT_USER */
                    Intent intent = new Intent(context, ManageActivity.class);
                   /* Serialiser les données du contact en String JSON */
                    intent.putExtra(ManageActivity.BUNDLE_KEY_GET_CONTACT, Contact.contactToJson(contact));
                   /* Call de l'activity */
                    ((Activity) context).startActivityForResult(intent, MainActivity.REQUEST_CODE_EDIT_USER);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                   e.printStackTrace();
               }
            }
        });

        return convertView;
    }
}
