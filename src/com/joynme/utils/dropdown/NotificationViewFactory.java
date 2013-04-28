package com.joynme.utils.dropdown;

import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * NotificationViewFactory is main factory class that is used to show dropdown notifications.
 * Notification is shown at the top of the provided parent component (must be RelativeLayout).
 * After notification hides back it removes itself from the parent so you don't have 
 * to worry about the cleanup. 
 * 
 * There are two ways to show notification:
 * 
 *    1. By providing predefined notification type: INFO_NOTIFICATION, WARNING_NOTIFICATION, etc.
 *    
 *    2. By providing custom notification component (must be of NotificationLayout class).
 *       NotificationLayout is just a subclass of RelativeLayout.
 * 
 * You can also provide optional OnTapListener and/or OnSwipeListener to handle cases when 
 * some action is needed when user taps or Swipes notification panel. Default actions for  
 * Tap and Swipe are to immediately hide notification.
 * 
 * @author Victor Serbo (victor.serbo@joynme.com)
 *
 */
public class NotificationViewFactory {

	/** Set this flag to true to see the debug output */
	static boolean debug_ = false;

	static long animationDuration 				= 300L;
	static long notificationNoteDuration 		= 2000L;
	static long notificationInfoDuration 		= 4000L;
	static long notificationWarningDuration 	= 5000L;
	static long notificationErrorDuration 		= 7000L;
	
	public static final int NOTE_NOTIFICATION 		= 1;
	public static final int INFO_NOTIFICATION 		= 2;
	public static final int WARNING_NOTIFICATION 	= 3;
	public static final int ERROR_NOTIFICATION 		= 4;
	
	public static void printDebug(String tag, String message) {
		if (debug_) Log.d(tag, message);
	}
	
	public static void printDebug(String tag, String message, Throwable t) {
		if (debug_) Log.d(tag, message, t);
	}
	
	public static void showNotification(RelativeLayout parent, int type, String title, String message) {
		showNotification(parent, type, title, message, null, null);
	}
		
	public static void showNotification(RelativeLayout parent, int type, String title, String message, OnTapListener onTapListener) {
		showNotification(parent, type, title, message, onTapListener, null);
	}
			
	public static void showNotification(RelativeLayout parent, int type, String title, String message, OnSwipeListener onSwipeListener) {
		showNotification(parent, type, title, message, null, onSwipeListener);
	}
				
	public static void showNotification(RelativeLayout parent, int type, String title, String message, 
			OnTapListener onTapListener, OnSwipeListener onSwipeListener) {
		int layout = R.layout.dropdown_info_layout;
		long notificationDuration = 0;
		switch (type) {
		case NOTE_NOTIFICATION:
			layout = R.layout.dropdown_note_layout;
			notificationDuration = notificationInfoDuration;
			break;
		case INFO_NOTIFICATION:
			layout = R.layout.dropdown_info_layout;
			notificationDuration = notificationInfoDuration;
			break;
		case WARNING_NOTIFICATION:
			layout = R.layout.dropdown_warning_layout;
			notificationDuration = notificationWarningDuration;
			break;
		case ERROR_NOTIFICATION:
			layout = R.layout.dropdown_error_layout;
			notificationDuration = notificationErrorDuration;
			break;
		}
		
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        NotificationLayout nl = (NotificationLayout) inflater.inflate(layout, null);
        
        TextView tv = (TextView) nl.findViewById(R.id.ddTitle);
        tv.setText(title);
        
        TextView dv = (TextView) nl.findViewById(R.id.ddDetail);
        dv.setText(message);

        showNotification(parent, nl, animationDuration, notificationDuration, onTapListener, onSwipeListener);
	}
	
	public static void showNotification(RelativeLayout parent, NotificationLayout notification, 
			long animationDuration, long notificationDuration) {
		
		showNotification(parent, notification, animationDuration, notificationDuration, null, null);
	}

	public static void showNotification(RelativeLayout parent, NotificationLayout notification, 
			long animationDuration, long notificationDuration, OnTapListener onTapListener) {

		showNotification(parent, notification, animationDuration, notificationDuration, onTapListener, null);
	}
		
	public static void showNotification(RelativeLayout parent, NotificationLayout notification, 
			long animationDuration, long notificationDuration, OnSwipeListener onSwipeListener) {

		showNotification(parent, notification, animationDuration, notificationDuration, null, onSwipeListener);
	}
		
	public static void showNotification(RelativeLayout parent, NotificationLayout notification, 
				long animationDuration, long notificationDuration, OnTapListener onTapListener, OnSwipeListener onSwipeListener) {

		// We need to delegate to different classes for different Android versions  
		// here to avoid "Class not found" messages during execution
		
		if (Build.VERSION.SDK_INT >= 11) {
			NotificationViewFactoryPostV11.showNotification(parent, notification, 
					animationDuration, notificationDuration, onTapListener, onSwipeListener);
		} else {
			NotificationViewFactoryPreV11.showNotification(parent, notification, 
					animationDuration, notificationDuration, onTapListener, onSwipeListener);
		}
		
	}
			
}
