package apidez.com.samgu.activity;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import apidez.com.samgu.R;
import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by nongdenchet on 6/4/16.
 */
public class ListenMusicActivity extends AppCompatActivity {

    @Bind(R.id.ivCircle)
    CircleImageView ivCircle;

    @Bind(R.id.ivMusic)
    ImageView ivMusic;

    @Bind(R.id.tvBrowseMusic)
    TextView tvBrowseMusic;

    @Bind(R.id.tvResult)
    TextView tvResult;

    @Bind(R.id.constraintLayout)
    ConstraintLayout constraintLayout;

    public static Intent getIntent(Context context) {
        return new Intent(context, ListenMusicActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listen_music);
        ButterKnife.bind(this);
        initializeView();
    }

    private void initializeView() {
        runAnimation();
        setUpListener();
    }

    private void setUpListener() {
        tvBrowseMusic.setOnClickListener(v -> startActivity(BrowseMusicActivity.getIntent(this)));
    }

    private void runAnimation() {
        Observable.interval(500, 500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.computation())
                .subscribe(time -> {
                    createNewCircle();
                });
    }

    public void preAnimation(CircleImageView circleImageView) {
        constraintLayout.removeView(ivMusic);
        constraintLayout.addView(circleImageView);
        constraintLayout.addView(ivMusic);
    }

    @Override
    protected void onResume() {
        super.onResume();
        tvResult.setText(getString(R.string.listening));
    }

    private void createNewCircle() {
        CircleImageView circleImageView = new CircleImageView(this);
        circleImageView.setLayoutParams(ivCircle.getLayoutParams());
        circleImageView.setImageDrawable(new ColorDrawable(Color.parseColor("#E32136")));
        circleImageView.animate()
                .setInterpolator(new LinearInterpolator())
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        preAnimation(circleImageView);
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        constraintLayout.removeView(circleImageView);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                    }
                })
                .setDuration(3000)
                .scaleX(3.2f)
                .scaleY(3.2f)
                .alpha(0)
                .start();
    }
}
