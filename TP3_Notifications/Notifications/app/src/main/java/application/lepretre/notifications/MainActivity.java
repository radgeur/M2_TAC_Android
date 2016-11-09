package application.lepretre.notifications;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        inboxStyle.setBigContentTitle("Expanded layout Title");
        inboxStyle.setSummaryText("Expanded layout Text");
        inboxStyle.addLine("Line 1");
        inboxStyle.addLine("Line 2");
        inboxStyle.addLine("Line 3");
        inboxStyle.addLine("Line 4");

        final NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setSmallIcon(R.drawable.notification_icon);
        mBuilder.setContentTitle("My notification");
        mBuilder.setContentText("This is my notification text");
        mBuilder.setStyle(inboxStyle);

        final int notificationID = 1;
        final NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


        final Button button = (Button) findViewById(R.id.button_notification);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mNotificationManager.notify(notificationID, mBuilder.build());
            }
        });


    }
}
