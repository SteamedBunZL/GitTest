package com.rising.utils;

import android.content.Context;
import android.util.Log;

public class Utils {

	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	public static String getDisplayMetrics(Context context) {
		float xdpi = context.getResources().getDisplayMetrics().xdpi;
		float ydpi = context.getResources().getDisplayMetrics().ydpi;
		return "xdpi : " + xdpi + ", ydpi : " + ydpi;
	}

}
