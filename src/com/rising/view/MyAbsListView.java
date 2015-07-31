package com.rising.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;

public class MyAbsListView extends AdapterView<ListAdapter> {

	public MyAbsListView(Context context) {
		super(context);
	}

	public MyAbsListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public ListAdapter getAdapter() {
		return null;
	}

	@Override
	public void setAdapter(ListAdapter adapter) {

	}

	@Override
	public View getSelectedView() {
		return null;
	}

	@Override
	public void setSelection(int position) {

	}

	class RecycleBin {

		private int mFirstActivePosition;

		private View[] mActiveViews = new View[0];

		void fillActiveViews(int childCount, int firstActivePosition) {
			if (mActiveViews.length < childCount) {
				mActiveViews = new View[childCount];
			}
			mFirstActivePosition = firstActivePosition;

			final View[] activeViews = mActiveViews;
			for (int i = 0; i < childCount; i++) {
				View child = getChildAt(i);
				MyAbsListView.LayoutParams lp = (MyAbsListView.LayoutParams) child
						.getLayoutParams();
				if (lp != null
						&& lp.viewType != ITEM_VIEW_TYPE_HEADER_OR_FOOTER) {
					activeViews[i] = child;
				}
			}

		}
		View getActiveView(int position){
			int index = position - mFirstActivePosition;
			final View[] activeViews = mActiveViews;
			if (index>=0&&index<activeViews.length) {
				final View match = activeViews[index];
				activeViews[index] = null;
				return match;
			}
			return null;
		}
	}

	public static class LayoutParams extends ViewGroup.LayoutParams {
		int viewType;

		public LayoutParams(Context c, AttributeSet attrs) {
			super(c, attrs);
		}

		public LayoutParams(int w, int h) {
			super(w, h);
		}

		public LayoutParams(int w, int h, int viewType) {
			super(w, h);
			this.viewType = viewType;
		}

		public LayoutParams(ViewGroup.LayoutParams sources) {
			super(sources);
		}
	}

}
