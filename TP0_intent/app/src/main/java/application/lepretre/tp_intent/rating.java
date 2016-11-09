package application.lepretre.tp_intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;

public class rating extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
    }

    @Override
    protected void onStart() {
        super.onStart();
        RatingBar ratingBar = (RatingBar) findViewById(R.id.rate_me);

        if (getIntent().getExtras() != null) {
            float vote = getIntent().getExtras().getFloat("vote_initial");
            ratingBar.setRating(vote);
        }

       ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar rate,float rating, boolean fromUser){
                Intent intent = getIntent();
                intent.putExtra("vote", rating);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
