package com.joynme.utils.dropdown;

import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;

/**
 * Hidden Factory class that handles dropdown notifications for Android versions prior to 3.0.
 * Before Android 3.0 we need use NineOldAndroids library for backwards support
 * 
 * @author Victor Serbo (victor.serbo@joynme.com)
 *
 */
class NotificationViewFactoryPreV11 {

		
	static void showNotification(RelativeLayout parent, NotificationLayout notification, 
				long animationDuration, long notificationDuration, OnTapListener onTapListener, OnSwipeListener onSwipeListener) {

        final RelativeLayout finalParent = parent;
        
        if (onTapListener != null) notification.setOnTapListener(onTapListener);
        
        if (onSwipeListener != null) notification.setOnSwipeListener(onSwipeListener);
        
        final NotificationLayout nl = notification;
        
		RelativeLayout.LayoutParams params2 = 
    			new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT); 
		params2.addRule(RelativeLayout.ALIGN_PARENT_TOP, -1); 
		
		nl.setVisibility(View.INVISIBLE);
		
        parent.addView(nl, params2);
		
		nl.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
		nl.layout(0, 0, nl.getMeasuredWidth(), nl.getMeasuredHeight());
       
		int height = nl.getHeight();
		NotificationViewFactory.printDebug("PreV11", "height="+height);
				
    	// Before Android 3.0 we need use NineOldAndroids library for backwards support
    	
    	com.nineoldandroids.animation.ObjectAnimator objectAnimator0 = 
    			com.nineoldandroids.animation.ObjectAnimator.ofFloat ( nl, "translationY", 0, -height);
    	objectAnimator0.setDuration(0L);
        
    	final com.nineoldandroids.animation.ObjectAnimator objectAnimator1 = 
    			com.nineoldandroids.animation.ObjectAnimator.ofFloat ( nl, "translationY", -height, 0);
    	objectAnimator1.setDuration(animationDuration);
		nl.setObjectAnimator1(objectAnimator1);
		
		final com.nineoldandroids.animation.ObjectAnimator objectAnimator2 = 
    			com.nineoldandroids.animation.ObjectAnimator.ofFloat ( nl, "translationY", 0, -height);
		objectAnimator2.setDuration(animationDuration);
		objectAnimator2.setStartDelay(animationDuration+notificationDuration);
		nl.setObjectAnimator2(objectAnimator2);
		
		com.nineoldandroids.animation.ObjectAnimator objectAnimator3 = 
    			com.nineoldandroids.animation.ObjectAnimator.ofFloat ( nl, "translationY", 0, -height);
		objectAnimator3.setDuration(animationDuration);
		objectAnimator3.setStartDelay(0);
		nl.setObjectAnimator3(objectAnimator3);
		
    	com.nineoldandroids.animation.Animator.AnimatorListener listener1 = new com.nineoldandroids.animation.Animator.AnimatorListener() {

			public void onAnimationCancel(com.nineoldandroids.animation.Animator arg0) {
				NotificationViewFactory.printDebug("PreV11", "onAnimationCancel1");
			}

			public void onAnimationEnd(com.nineoldandroids.animation.Animator arg0) {
				NotificationViewFactory.printDebug("PreV11", "onAnimationEnd1");
			}

			public void onAnimationRepeat(com.nineoldandroids.animation.Animator arg0) {
				NotificationViewFactory.printDebug("PreV11", "onAnimationRepeat1");
			}

			public void onAnimationStart(com.nineoldandroids.animation.Animator arg0) {
				NotificationViewFactory.printDebug("PreV11", "onAnimationStart1");
			}
        };
        
    	objectAnimator1.addListener(listener1);

    	com.nineoldandroids.animation.Animator.AnimatorListener listener2 = new com.nineoldandroids.animation.Animator.AnimatorListener() {

			public void onAnimationCancel(com.nineoldandroids.animation.Animator arg0) {
				NotificationViewFactory.printDebug("PreV11", "onAnimationCancel2");
			}

			public void onAnimationEnd(com.nineoldandroids.animation.Animator arg0) {
				NotificationViewFactory.printDebug("PreV11", "onAnimationEnd2");
				
				nl.setVisibility(View.GONE);
				finalParent.removeView(nl);
			}

			public void onAnimationRepeat(com.nineoldandroids.animation.Animator arg0) {
				NotificationViewFactory.printDebug("PreV11", "onAnimationRepeat2");
			}

			public void onAnimationStart(com.nineoldandroids.animation.Animator arg0) {
				NotificationViewFactory.printDebug("PreV11", "onAnimationStart2");
			}
        };
        objectAnimator2.addListener(listener2);        

    	com.nineoldandroids.animation.Animator.AnimatorListener listener3 = new com.nineoldandroids.animation.Animator.AnimatorListener() {

			public void onAnimationCancel(com.nineoldandroids.animation.Animator arg0) {
				NotificationViewFactory.printDebug("PreV11", "onAnimationCancel3");
			}

			public void onAnimationEnd(com.nineoldandroids.animation.Animator arg0) {
				NotificationViewFactory.printDebug("PreV11", "onAnimationEnd3");
				nl.setVisibility(View.GONE);
				finalParent.removeView(nl);
			}

			public void onAnimationRepeat(com.nineoldandroids.animation.Animator arg0) {
				NotificationViewFactory.printDebug("PreV11", "onAnimationRepeat3");
			}

			public void onAnimationStart(com.nineoldandroids.animation.Animator arg0) {
				NotificationViewFactory.printDebug("PreV11", "onAnimationStart3");
			}
        };
        objectAnimator3.addListener(listener3);        
		
    	objectAnimator0.start();
    	nl.setVisibility(View.VISIBLE);
    	objectAnimator1.start();
    	objectAnimator2.start();
        
	}
			
	static void cancelAnimation(Object animator) {
		if (animator instanceof com.nineoldandroids.animation.ObjectAnimator) {
			((com.nineoldandroids.animation.ObjectAnimator) animator).cancel();
		} 
	}
	
	static void startAnimation(Object animator) {
		if (animator instanceof com.nineoldandroids.animation.ObjectAnimator) {
			((com.nineoldandroids.animation.ObjectAnimator) animator).setStartDelay(0);
			((com.nineoldandroids.animation.ObjectAnimator) animator).start();
		} 
	}

}
