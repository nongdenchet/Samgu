package apidez.com.samgu.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import apidez.com.samgu.R;
import apidez.com.samgu.view.SamguLogoView;
import butterknife.Bind;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    @Bind(R.id.logo)
    SamguLogoView samguLogoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        startAnimation();
    }

    private void startAnimation() {
        new Handler().postDelayed(()
                -> samguLogoView.runAnimation(()
                -> startActivity(LoginActivity.getIntent(SplashActivity.this))), 240);
    }
}
