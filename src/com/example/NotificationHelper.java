package com.example;

import com.joynme.utils.dropdown.NotificationLayout;
import com.joynme.utils.dropdown.NotificationViewFactory;
import com.joynme.utils.dropdown.OnDoneListener;
import com.joynme.utils.dropdown.OnSwipeListener;
import com.joynme.utils.dropdown.OnTapListener;

import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Simplifies usage of NotificationViewFactory methods by defining durations
 * and layouts for four notification type: Note, Info, Warning, and Error
 * 
 * @author Victor Serbo (victor.serbo@joynme.com)
 *
 */
public class NotificationHelper {

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
	

	public static void showNotification(RelativeLayout parent, int type, String title, String message) {
		showNotification(parent, type, title, message, null, null, null);
	}
		
	public static void showNotification(RelativeLayout parent, int type, String title, String message, OnTapListener onTapListener) {
		showNotification(parent, type, title, message, onTapListener, null, null);
	}
			
	public static void showNotification(RelativeLayout parent, int type, String title, String message, OnSwipeListener onSwipeListener) {
		showNotification(parent, type, title, message, null, onSwipeListener, null);
	}
				
	public static void showNotification(RelativeLayout parent, int type, String title, String message, OnDoneListener onDoneListener) {
		showNotification(parent, type, title, message, null, null, onDoneListener);
	}
				
	public static void showNotification(RelativeLayout parent, int type, String title, String message, 
			OnTapListener onTapListener, OnSwipeListener onSwipeListener, OnDoneListener onDoneListener) {
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

        NotificationViewFactory.showNotification(parent, nl, animationDuration, notificationDuration, onTapListener, onSwipeListener, onDoneListener);
	}
	
}
