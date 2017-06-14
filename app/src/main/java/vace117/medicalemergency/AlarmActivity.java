package vace117.medicalemergency;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class AlarmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        String messageData = getIntent().getStringExtra(MyFirebaseMessagingService.INTENT_PAYLOAD);
        Toast.makeText(AlarmActivity.this, messageData, Toast.LENGTH_LONG).show();
    }

}
