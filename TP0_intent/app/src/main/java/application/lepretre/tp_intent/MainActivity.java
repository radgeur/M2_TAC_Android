package application.lepretre.tp_intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((Button) findViewById(R.id.next)).setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //startActivityForResult permet de savoir si le retour c'est bien ou mal passé
                //put extra permet de récupérer la valeur
                Intent i_rate = new Intent(v.getContext(), rating.class);
                startActivity(i_rate);
            }
        });
    }
}
