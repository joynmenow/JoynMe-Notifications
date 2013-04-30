package com.joynme.utils.dropdown;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * All notification components must be of that class (NotificationLayout),
 * or be descendants of that class.
 * 
 * @author Victor Serbo (victor.serbo@joynme.com)
 *
 */
public class NotificationLayout extends RelativeLayout implements NotificationView {

	/** Minimal length of swipe - anything less is considered just tap */
	protected float swipeMin_;
	protected OnTapListener onTapListener_;
	protected OnSwipeListener onSwipeListener_;
	protected Object objectAnimator1_;
	protected Object objectAnimator2_;
	protected Object objectAnimator3_;
	
	public NotificationLayout(Context context) {
		super(context);
		initNotificationView();
	}

	public NotificationLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		initNotificationView();
	}

	public NotificationLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initNotificationView();
	}

	void setObjectAnimator1(Object objectAnimator) { objectAnimator1_ = objectAnimator; }
	
	void setObjectAnimator2(Object objectAnimator) { objectAnimator2_ = objectAnimator; }
	
	void setObjectAnimator3(Object objectAnimator) { objectAnimator3_ = objectAnimator; }
	
	protected void initNotificationView() {
		// Set minimum Swipe length at 50
		setSwipeMinimum(50);
		
		// Set default OnTouchListener
		OnTouchListener onTouch = new OnTouchListener() {
			private float originX = 0;
			private float originY = 0;
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				int action=event.getAction();
				float x = event.getX();
				float y = event.getY();
				NotificationViewFactory.printDebug("NotificationView", "onTouch: "+action+" :: x="+x+", y="+y);
				if(action == MotionEvent.ACTION_DOWN) {
					originX = x;
					originY = y;
				} else if (action == MotionEvent.ACTION_UP) {
					float length = (x - originX)*(x - originX) + (y - originY)*(y - originY);
					if (length > swipeMin_) {
						finish();
						if (onSwipeListener_ != null) onSwipeListener_.onSwipe(event, x - originX, y - originY);
					} else {
						finish();
						if (onTapListener_ != null) onTapListener_.onTap(event);
					}
				}
				return true;
			}
		};
		super.setOnTouchListener(onTouch);
		
		// Set default OnSwipeListener to just finish notification
		onSwipeListener_ = new OnSwipeListener() {

			@Override
			public boolean onSwipe(MotionEvent event, float deltaX, float deltaY) {
				NotificationViewFactory.printDebug("NotificationView", "onSwipe");
				finish();
				return true;
			}
		};
		setOnSwipeListener(onSwipeListener_);
		
	}
	
	@Override
	public void setOnTouchListener(OnTouchListener l) {
		// do nothing here - can not change default listener
	}

	public void setOnTapListener(OnTapListener l) {
		onTapListener_ = l;
	}

	public void setOnSwipeListener(OnSwipeListener l) {
		onSwipeListener_ = l;
	}

	public void finish() {
		NotificationViewFactory.printDebug("NotificationView", "finish");
		if (Build.VERSION.SDK_INT >= 11) {
			NotificationViewFactoryPostV11.cancelAnimation(objectAnimator2_);
			NotificationViewFactoryPostV11.startAnimation(objectAnimator3_);
		} else {
			NotificationViewFactoryPreV11.cancelAnimation(objectAnimator2_);
			NotificationViewFactoryPreV11.startAnimation(objectAnimator3_);
		}
		
	}

	public void setSwipeMinimum(float swipeMin) {
		swipeMin_ = swipeMin * swipeMin;
	}
	
}
