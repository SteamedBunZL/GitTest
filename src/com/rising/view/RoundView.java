package com.rising.view;

import java.util.Timer;

import com.rising.gittest.R;
import com.rising.utils.Utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.PathEffect;
import android.graphics.RectF;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;

public class RoundView extends View {

	private Context mContext;

	private int mMaxValue;

	private int mCurrentValue;

	private int mSweepDegree = 60;

	private final int ANIMATION_DURATION = 1000;

	private boolean mTimerStatus = false;

	private Timer mTimer;

	public RoundView(Context context) {
		super(context);
		mContext = context;
		initTimer();
	}

	public RoundView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		initTimer();
	}

	private void initTimer() {
		if (null == mTimer) {
			mTimer = new Timer(true);
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		Paint paint = new Paint();

		paint.setAntiAlias(true);

		paint.setColor(getResources().getColor(R.color.graduation_bg_color));

		paint.setStyle(Style.FILL);

		int cx = getWidth() / 2;

		int cy = getHeight() / 2;

		int radius = cx;

		canvas.drawCircle(cx, cy, radius, paint);

		float ringWidthDP = getResources().getDimension(
				R.dimen.graduation_inner_width);
		int ringWidthPX = Utils.dip2px(mContext, ringWidthDP);
		paint.setColor(getResources().getColor(
				R.color.graduation_inner_bg_color));
		canvas.drawCircle(cx, cy, radius - ringWidthPX, paint);

		paint.setStyle(Style.STROKE);
		paint.setColor(Color.WHITE);

		paint.setStrokeWidth(ringWidthPX / 2);

		PathEffect effects = new DashPathEffect(new float[] { 3, 6, 3, 6 }, 1);
		paint.setPathEffect(effects);
		canvas.drawCircle(cx, cy, radius - ringWidthPX / 2 - ringWidthPX / 4,
				paint);

		RectF rect = new RectF();

		rect.left = cx - getWidth() / 2;

		rect.top = cy - getWidth() / 2;

		rect.right = cx + getWidth() / 2;

		rect.bottom = cy + getWidth() / 2;

		rect.inset(ringWidthPX / 4, ringWidthPX / 4);

		drawDraduate(paint, canvas, ringWidthPX / 2, rect);

		String widthString = "width : " + String.valueOf(getWidth());

		String heightString = "height : " + String.valueOf(getHeight());

		Paint textPaint = new Paint();

		textPaint.setAntiAlias(true);

		textPaint.setStyle(Style.STROKE);

		textPaint.setTextSize(30);

		FontMetrics fontMetrics = textPaint.getFontMetrics();
		canvas.drawText(widthString, cx, cy, textPaint);
		canvas.drawText(heightString, cx, cy
				+ (fontMetrics.descent - fontMetrics.ascent), textPaint);

	}

	private void drawDraduate(Paint paint, Canvas canvas, float width,
			RectF rect) {
		paint.setColor(getResources().getColor(
				R.color.graduation_focus_bg_color));
		paint.setStyle(Paint.Style.STROKE);
		paint.setPathEffect(null);
		paint.setStrokeWidth(width);

		canvas.drawArc(rect, 90, mSweepDegree, false, paint);
	}

}
