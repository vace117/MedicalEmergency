package vace117.medicalemergency;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

/**
 * @author Val Blant
 */
public class AlarmActivity extends AppCompatActivity {

    private static final String TAG = "MEAlarmActivity";

    private static final float ALARM_VOLUME = 0.2f;

    private SoundPool soundPool;
    private int soundId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String messageData = getIntent().getStringExtra(MyFirebaseMessagingService.INTENT_PAYLOAD);
        Log.i(TAG, "Alarm message received: " + messageData);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        initAndPlaySound();
    }

    private void soundLoaded() {
        Log.i(TAG, "Alarm sound loaded successfully. Playing the alarm...");
        soundPool.play(soundId, ALARM_VOLUME, ALARM_VOLUME, 0, -1, 1.0f);
    }

    private void initAndPlaySound() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            soundPool = new SoundPool.Builder()
                    .setMaxStreams(1)
                    .setAudioAttributes(new AudioAttributes.Builder()
                            .setUsage(AudioAttributes.USAGE_MEDIA)
                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                            .build())
                    .build();
        } else {
            soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        }

        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                if ( status == 0 ) {
                    soundLoaded();
                }
                else {
                    Log.e(TAG, "ERROR: Failed to load alarm sound!");
                    Toast.makeText(AlarmActivity.this, "ERROR: Failed to load alarm sound!", Toast.LENGTH_LONG).show();
                }
            }
        });

        soundId = soundPool.load(this, R.raw.alert10, 1);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        if (soundPool != null) {
            soundPool.release();
            soundPool = null;
        }
    }
}
