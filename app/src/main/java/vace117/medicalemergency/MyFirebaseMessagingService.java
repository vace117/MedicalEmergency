package vace117.medicalemergency;

import android.content.Intent;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by val on 14/06/17.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MEFirebaseMsgService";

    public static final String INTENT_PAYLOAD = "PAYLOAD";

    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());

            handleMessage( remoteMessage.getData().toString() );
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());

            handleMessage( remoteMessage.getNotification().getBody() );
        }
    }

    private void handleMessage(String message) {
        Intent raiseAlarmIntent = new Intent(this, AlarmActivity.class);
        raiseAlarmIntent.putExtra(INTENT_PAYLOAD, message);
        raiseAlarmIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(raiseAlarmIntent);
    }

}