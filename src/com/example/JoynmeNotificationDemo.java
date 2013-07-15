package com.example;

import com.joynme.utils.dropdown.NotificationLayout;
import com.joynme.utils.dropdown.NotificationViewFactory;
import com.joynme.utils.dropdown.OnSwipeListener;
import com.joynme.utils.dropdown.OnTapListener;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Simple Android App to show various possible uses of the Joynme Dropdown Notification package
 * 
 * @author Victor Serbo (victor.serbo@joynme.com)
 *
 */
public class JoynmeNotificationDemo extends Activity {

	private RelativeLayout topLayout_;
	private RelativeLayout internalLayout_;
	private Resources res_;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dropdown_notification_demo);
		
		topLayout_ = (RelativeLayout) findViewById(R.id.topLayout);
		
		internalLayout_ = (RelativeLayout) findViewById(R.id.internalLayout);
		
		res_ = getResources();
	}


	public void onNote(View view) {
		NotificationHelper.showNotification(topLayout_, NotificationHelper.NOTE_NOTIFICATION, 
				res_.getString(R.string.note_title), res_.getString(R.string.note_message));
	}
	
	
	public void onInfo(View view) {
		NotificationHelper.showNotification(topLayout_, NotificationHelper.INFO_NOTIFICATION, 
				res_.getString(R.string.info_title), res_.getString(R.string.info_message));
	}
	
	
	public void onWarning(View view) {
		NotificationHelper.showNotification(topLayout_, NotificationHelper.WARNING_NOTIFICATION, 
				res_.getString(R.string.warning_title), res_.getString(R.string.warning_message));
	}
	
	
	public void onError(View view) {
		NotificationHelper.showNotification(topLayout_, NotificationHelper.ERROR_NOTIFICATION, 
				res_.getString(R.string.error_title), res_.getString(R.string.error_message));
	}
	
	
	public void onCustom(View view) {
		
		// First create custom component - must be com.joynme.utils.dropdown.NotificationLayout
        LayoutInflater inflater = LayoutInflater.from(topLayout_.getContext());
        NotificationLayout customComponent = (NotificationLayout) inflater.inflate(R.layout.dropdown_custom_layout, null);
        
        // Set tile and messages on the custom component
        TextView tv = (TextView) customComponent.findViewById(R.id.title);
        tv.setText(res_.getString(R.string.custom_title));
        
        TextView mv1 = (TextView) customComponent.findViewById(R.id.message1);
        mv1.setText(res_.getString(R.string.custom_message1));

        TextView mv2 = (TextView) customComponent.findViewById(R.id.message2);
        mv2.setText(res_.getString(R.string.custom_message2));

        // Create OnTapListener 
        OnTapListener onTapListener = new OnTapListener() {

			@Override
			public boolean onTap(MotionEvent event) {
				// Just show confirm dialog on Tap action
		        new AlertDialog.Builder(JoynmeNotificationDemo.this)
		        .setIcon(android.R.drawable.ic_dialog_alert)
		        .setTitle(R.string.custom_tap_title)
		        .setMessage(R.string.custom_tap_message)
		        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {

		            @Override
		            public void onClick(DialogInterface dialog, int which) {
		            	dialog.dismiss();    
		            }

		        })
		        .show();
				return true;
			}};
        
        // Create OnSwipeListener
		OnSwipeListener onSwipeListener = new OnSwipeListener() {

			@Override
			public boolean onSwipe(MotionEvent event, float deltaX, float deltaY) {
				String message = res_.getString(R.string.custom_swipe_message) +
						", deltaX  =  "+deltaX+", deltaY  =  "+deltaY;
				
				// Just show confirm dialog on Swipe action
		        new AlertDialog.Builder(JoynmeNotificationDemo.this)
		        .setIcon(android.R.drawable.ic_dialog_alert)
		        .setTitle(R.string.custom_swipe_title)
		        .setMessage(message)
		        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {

		            @Override
		            public void onClick(DialogInterface dialog, int which) {
		            	dialog.dismiss();    
		            }

		        })
		        .show();
				return true;
			}};
			
        // Show custom component with two listeners 
			NotificationViewFactory.showNotification(topLayout_, customComponent, 
					NotificationHelper.animationDuration, 
					NotificationHelper.notificationErrorDuration, 
        		onTapListener, onSwipeListener, null);
	}
	
	
	
	public void onInternal(View view) {
		NotificationHelper.showNotification(internalLayout_, NotificationHelper.INFO_NOTIFICATION, 
				res_.getString(R.string.internal_title), res_.getString(R.string.internal_message));
	}
	
	
}
