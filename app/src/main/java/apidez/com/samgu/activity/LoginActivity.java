package apidez.com.samgu.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import apidez.com.samgu.R;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nongdenchet on 6/4/16.
 */
public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.btnFacebookLogin)
    View btnFacebookLogin;

    @Bind(R.id.btnGoogleLogin)
    View btnGoogleLogin;

    @Bind(R.id.bgView)
    ImageView bgView;

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        btnFacebookLogin.setOnClickListener(v -> startListenMusicActivity());
        btnGoogleLogin.setOnClickListener(v -> startListenMusicActivity());
    }

    private void startListenMusicActivity() {
        startActivity(ListenMusicActivity.getIntent(this));
    }
}
