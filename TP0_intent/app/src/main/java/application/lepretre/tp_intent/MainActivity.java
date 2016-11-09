package application.lepretre.tp_intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int MONCODE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((Button) findViewById(R.id.next)).setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent i_rate = new Intent(v.getContext(), rating.class);
                float vote_initial = 2;
                i_rate.putExtra("vote_initial", vote_initial);
                startActivityForResult(i_rate, MONCODE);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        float vote;

		switch (requestCode) {
            case MONCODE:
                switch (resultCode) {
                    case RESULT_OK:
                        Toast.makeText(getApplicationContext(), "Merci d'avoir voté",
                                Toast.LENGTH_SHORT).show();

                        vote = data.getFloatExtra("vote", -100);
                        Toast.makeText(getApplicationContext(),
                                "vous avez voté " + vote, Toast.LENGTH_SHORT).show();
                        break;
                    case RESULT_CANCELED:
                        // pour solution 2, 3 et 4
                        Toast.makeText(getApplicationContext(),
                                "Pourquoi ne pas avoir voté ?", Toast.LENGTH_SHORT)
                                .show();
                        break;
                    default:
                        break;
                }
                break;

            default:
                break;
        }
    }
}
