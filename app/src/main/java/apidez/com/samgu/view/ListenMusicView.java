package apidez.com.samgu.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by nongdenchet on 6/4/16.
 */

public class ListenMusicView extends View {
    private Paint paint;
    private int color = Color.parseColor("#E32136");

    public ListenMusicView(Context context) {
        super(context);
        this.initialize();
    }

    public ListenMusicView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.initialize();
    }

    private void initialize() {
        paint = new Paint();
        paint.setColor(color);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(50, 50, 100, paint);
    }
}
