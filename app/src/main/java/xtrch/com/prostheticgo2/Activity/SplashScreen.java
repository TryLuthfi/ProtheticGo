package xtrch.com.prostheticgo2.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import xtrch.com.prostheticgo2.R;
import xtrch.com.prostheticgo2.Request.Utils;

public class SplashScreen extends AppCompatActivity {
    ImageView mainLogo;
    TextView version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getFindView();
        setAnime();
        setTimmer();
    }

    private void setAnime() {
        Utils.darkenStatusBar(this, R.color.colorGrey);

        Animation fade_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.mytransition);
        Animation from_bottom = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.frombottom);
        mainLogo.setAnimation(fade_out);
        version.setAnimation(from_bottom);
    }

    private void setTimmer() {
        final Intent i = new Intent(getApplicationContext(), HomePage.class);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    startActivity(i);
                    finish();
                }
            }
        };
        timer.start();
    }

    private void getFindView() {
        mainLogo = findViewById(R.id.SplashScreen_mainlogo);
        version = findViewById(R.id.SplashScreen_version);
    }
}