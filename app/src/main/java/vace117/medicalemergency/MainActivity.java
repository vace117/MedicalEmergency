package vace117.medicalemergency;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MEActivity";
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    public static final String TOPIC_MOTHER_ALERT = "mother_alert";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if ( checkPlayServices() ) {
            // Subscribe to Firebase Messaging Topic
            //
            FirebaseMessaging.getInstance().subscribeToTopic(TOPIC_MOTHER_ALERT);
            Toast.makeText(MainActivity.this, String.format("Subscribed to %s", TOPIC_MOTHER_ALERT), Toast.LENGTH_LONG).show();

            String registrationToken = FirebaseInstanceId.getInstance().getToken();
            TextView t=(TextView)findViewById(R.id.registrationTokenTextId);
            t.setText("Registration Token: " + registrationToken);

            Log.d(TAG, "Registration Token: " + registrationToken);
        }

    }

    private boolean checkPlayServices() {
        GoogleApiAvailability gApi = GoogleApiAvailability.getInstance();
        int resultCode = gApi.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (gApi.isUserResolvableError(resultCode)) {
                gApi.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Toast.makeText(this, "Your device is not supported", Toast.LENGTH_LONG).show();
                finish();
            }
            return false;
        }
        return true;
    }
}
