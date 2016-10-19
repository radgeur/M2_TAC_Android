package com.example.sharedpreferences;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "MyPrefsFile";
    private TextView pseudo;
    private Switch volume_actif;
    private SeekBar volume_value;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.initialize();

        // On restaure les préférences à partir du fichier PREFS_NAME
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        this.initPrefs(prefs);

        // Fonction pour la gestion du clique sur le bouton
        this.onSavePrefs();
    }

    /**
     * Initialise les variables des différents widgets.
     */
    private void initialize() {
        this.pseudo = (TextView)findViewById(R.id.txt_pseudo);
        this.volume_actif = (Switch)findViewById(R.id.sw_volume);
        this.volume_value = (SeekBar)findViewById(R.id.sb_volume);
        this.btn = (Button)findViewById(R.id.btn_save);
    }

    /**
     * Récupère les préférences contenu dans <code>prefs</code>
     * et initialise les widgets avec les valeurs récupérées.
     * @param prefs contient les préférences
     */
    private void initPrefs(SharedPreferences prefs) {

        //TODO récupérer les valeurs dans le fichier de préférence PREFS_NAME

        //TODO placer les valeurs dans les différents widgets associés
    }

    /**
     * Enregistre les différentes valeurs des widgets dans le
     * fichier de préférence <code>PREFS_NAME</code> lors du clique
     * sur le bouton.
     */
    private void onSavePrefs() {
        this.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //TODO enregistrer les valeurs dans le fichier de préférence PREFS_NAME

                Toast.makeText(MainActivity.this,
                        "Preferences saved !",
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}
