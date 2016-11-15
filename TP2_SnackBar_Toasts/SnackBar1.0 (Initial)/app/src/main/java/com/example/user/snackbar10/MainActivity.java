package com.example.user.snackbar10;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //snackBar
        Button snackBarButton = (Button) findViewById(R.id.snackBarButton);
        snackBarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar snackbar = Snackbar.make(coordinatorLayout, "Download failed", Snackbar.LENGTH_LONG);
                snackbar.setAction("Retry", new View.OnClickListener(){
                   @Override
                   public void onClick(View view) {
                       Snackbar snackbar = Snackbar.make(coordinatorLayout, "DOwnload completed", Snackbar.LENGTH_LONG);
                       snackbar.show();
                   }
                });
                snackbar.setActionTextColor(Color.BLUE);
                snackbar.show();
            }
        });

        //Toast
        Button toastButton = (Button) findViewById(R.id.showToast);
        toastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(getApplicationContext(), "Some toast", Toast.LENGTH_LONG);

                LinearLayout toastContainer = (LinearLayout)toast.getView();
                ImageView imV = new ImageView(getApplicationContext());
                imV.setImageResource(R.drawable.notification_icon);
                toastContainer.addView(imV,0);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();
            }
        });

        //Send Text
        final EditText editText= (EditText) findViewById(R.id.editText);
        Button sendTextButton = (Button) findViewById(R.id.sendTextButton);
        sendTextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String textMessage = editText.getText().toString();
                Snackbar snackbar = Snackbar.make(coordinatorLayout, textMessage,Snackbar.LENGTH_LONG);
                TextView textView = (TextView) snackbar.getView().findViewById(android.support.design.R.id.snackbar_text);
                textView.setBackgroundColor(Color.CYAN);
                textView.setTextColor(Color.GREEN);
                snackbar.show();
            }
        });

    }

}




