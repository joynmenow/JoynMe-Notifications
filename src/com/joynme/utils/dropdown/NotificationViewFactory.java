package com.joynme.utils.dropdown;

import android.util.Log;
import android.widget.RelativeLayout;

/**
 * NotificationViewFactory is main factory class that is used to show dropdown notifications.
 * Notification is shown at the top of the provided parent component (must be RelativeLayout).
 * After notification hides back it removes itself from the parent so you don't have 
 * to worry about the cleanup. 
 * 
 * You can also provide optional OnTapListener, OnSwipeListener, and/or OnDoneListener to  
 * handle cases when some action is needed when user taps or Swipes notification panel.   
 * Default actions for Tap and Swipe are to immediately hide notification.
 * 
 * @author Victor Serbo (victor.serbo@joynme.com)
 *
 */
public class NotificationViewFactory {

	/** Set this flag to true to see the debug output */
	static boolean debug_ = false;

	
	public static void printDebug(String tag, String message) {
		if (debug_) Log.d(tag, message);
	}
	
	public static void printDebug(String tag, String message, Throwable t) {
		if (debug_) Log.d(tag, message, t);
	}
	
	public static void showNotification(RelativeLayout parent, NotificationLayout notification, 
			long animationDuration, long notificationDuration) {
		
		showNotification(parent, notification, animationDuration, notificationDuration, null, null, null);
	}

	public static void showNotification(RelativeLayout parent, NotificationLayout notification, 
			long animationDuration, long notificationDuration, OnTapListener onTapListener) {

		showNotification(parent, notification, animationDuration, notificationDuration, onTapListener, null, null);
	}
		
	public static void showNotification(RelativeLayout parent, NotificationLayout notification, 
			long animationDuration, long notificationDuration, OnSwipeListener onSwipeListener) {

		showNotification(parent, notification, animationDuration, notificationDuration, null, onSwipeListener, null);
	}
		
	public static void showNotification(RelativeLayout parent, NotificationLayout notification, 
			long animationDuration, long notificationDuration, OnDoneListener onDoneListener) {

		showNotification(parent, notification, animationDuration, notificationDuration, null, null, onDoneListener);
	}
		
	public static void showNotification(RelativeLayout parent, NotificationLayout notification, 
				long animationDuration, long notificationDuration, OnTapListener onTapListener, 
				OnSwipeListener onSwipeListener, OnDoneListener onDoneListener) {

		// In the future we might need to delegate to different classes for different Android versions  
		NotificationViewFactoryPreV11.showNotification(parent, notification, 
				animationDuration, notificationDuration, onTapListener, onSwipeListener, onDoneListener);
	}
			
}
