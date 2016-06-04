package apidez.com.samgu.view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

/**
 * Created by nongdenchet on 6/4/16.
 */

public class SamguLogoView extends ImageView {
    public SamguLogoView(Context context) {
        super(context);
    }

    public SamguLogoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public interface AnimationCallBack {
        void onAnimationEnd();
    }

    private Animator.AnimatorListener logoAnimListener(AnimationCallBack animationCallBack) {
        return new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (animationCallBack != null) {
                    animationCallBack.onAnimationEnd();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        };
    }

    public void runAnimation(AnimationCallBack animationCallBack) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(scaleDownAnimator(animationCallBack)).after(scaleUpAnimator());
        animatorSet.start();
    }

    private Animator scaleDownAnimator(AnimationCallBack animationCallBack) {
        ObjectAnimator scaleDownAnimator = ObjectAnimator.ofPropertyValuesHolder(this,
                PropertyValuesHolder.ofFloat("scaleX", 0f),
                PropertyValuesHolder.ofFloat("scaleY", 0f)
        );
        scaleDownAnimator.setDuration(240);
        scaleDownAnimator.addListener(logoAnimListener(animationCallBack));
        scaleDownAnimator.setInterpolator(new AccelerateInterpolator());
        return scaleDownAnimator;
    }

    private Animator scaleUpAnimator() {
        ObjectAnimator scaleUpAnimator = ObjectAnimator.ofPropertyValuesHolder(this,
                PropertyValuesHolder.ofFloat("scaleX", 1.1f),
                PropertyValuesHolder.ofFloat("scaleY", 1.1f)
        );
        scaleUpAnimator.setDuration(60);
        scaleUpAnimator.setStartDelay(1500);
        scaleUpAnimator.setInterpolator(new LinearInterpolator());
        return scaleUpAnimator;
    }
}
