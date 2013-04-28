package com.joynme.utils.dropdown;

import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;

/**
 * Hidden Factory class that handles dropdown notifications for Android versions 3.0 and higher.
 * 
 * @author Victor Serbo (victor.serbo@joynme.com)
 *
 */
class NotificationViewFactoryPostV11 {
		
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
		NotificationViewFactory.printDebug("PostV11", "height="+height);
				
		
		// Normal support for Animation starts from Android 3.0
    	android.animation.ObjectAnimator objectAnimator0 = 
    			android.animation.ObjectAnimator.ofFloat ( nl, "translationY", 0, -height);
    	objectAnimator0.setDuration(0L);
        
    	final android.animation.ObjectAnimator objectAnimator1 = 
    			android.animation.ObjectAnimator.ofFloat ( nl, "translationY", -height, 0);
    	objectAnimator1.setDuration(animationDuration);
		nl.setObjectAnimator1(objectAnimator1);
		
		final android.animation.ObjectAnimator objectAnimator2 = 
    			android.animation.ObjectAnimator.ofFloat ( nl, "translationY", 0, -height);
		objectAnimator2.setDuration(animationDuration);
		objectAnimator2.setStartDelay(animationDuration+notificationDuration);
		nl.setObjectAnimator2(objectAnimator2);
		
		android.animation.ObjectAnimator objectAnimator3 = 
    			android.animation.ObjectAnimator.ofFloat ( nl, "translationY", 0, -height);
		objectAnimator3.setDuration(animationDuration);
		objectAnimator3.setStartDelay(0);
		nl.setObjectAnimator3(objectAnimator3);
		
    	android.animation.Animator.AnimatorListener listener1 = new android.animation.Animator.AnimatorListener() {

			public void onAnimationCancel(android.animation.Animator arg0) {
				NotificationViewFactory.printDebug("PostV11", "onAnimationCancel1");
			}

			public void onAnimationEnd(android.animation.Animator arg0) {
				NotificationViewFactory.printDebug("PostV11", "onAnimationEnd1");
			}

			public void onAnimationRepeat(android.animation.Animator arg0) {
				NotificationViewFactory.printDebug("PostV11", "onAnimationRepeat1");
			}

			public void onAnimationStart(android.animation.Animator arg0) {
				NotificationViewFactory.printDebug("PostV11", "onAnimationStart1");
			}
        };
        
    	objectAnimator1.addListener(listener1);

    	android.animation.Animator.AnimatorListener listener2 = new android.animation.Animator.AnimatorListener() {

			public void onAnimationCancel(android.animation.Animator arg0) {
				NotificationViewFactory.printDebug("PostV11", "onAnimationCancel2");
			}

			public void onAnimationEnd(android.animation.Animator arg0) {
				NotificationViewFactory.printDebug("PostV11", "onAnimationEnd2");
				
				nl.setVisibility(View.GONE);
				finalParent.removeView(nl);
			}

			public void onAnimationRepeat(android.animation.Animator arg0) {
				NotificationViewFactory.printDebug("PostV11", "onAnimationRepeat2");
			}

			public void onAnimationStart(android.animation.Animator arg0) {
				NotificationViewFactory.printDebug("PostV11", "onAnimationStart2");
			}
        };
        objectAnimator2.addListener(listener2);        

    	android.animation.Animator.AnimatorListener listener3 = new android.animation.Animator.AnimatorListener() {

			public void onAnimationCancel(android.animation.Animator arg0) {
				NotificationViewFactory.printDebug("PostV11", "onAnimationCancel3");
			}

			public void onAnimationEnd(android.animation.Animator arg0) {
				NotificationViewFactory.printDebug("PostV11", "onAnimationEnd3");
				nl.setVisibility(View.GONE);
				finalParent.removeView(nl);
			}

			public void onAnimationRepeat(android.animation.Animator arg0) {
				NotificationViewFactory.printDebug("PostV11", "onAnimationRepeat3");
			}

			public void onAnimationStart(android.animation.Animator arg0) {
				NotificationViewFactory.printDebug("PostV11", "onAnimationStart3");
			}
        };
        objectAnimator3.addListener(listener3);        
		
    	objectAnimator0.start();
    	nl.setVisibility(View.VISIBLE);
    	objectAnimator1.start();
    	objectAnimator2.start();
	    	
    }
	
	static void cancelAnimation(Object animator) {
		if (animator instanceof android.animation.ObjectAnimator) {
			((android.animation.ObjectAnimator) animator).cancel();
		}
	}
	
	static void startAnimation(Object animator) {
		if (animator instanceof android.animation.ObjectAnimator) {
			((android.animation.ObjectAnimator) animator).setStartDelay(0);
			((android.animation.ObjectAnimator) animator).start();
		}
	}

}
