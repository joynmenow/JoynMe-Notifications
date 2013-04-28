package com.joynme.utils.dropdown;

/**
 * Marker Interface to specify all additional public methods of NotificationLayout class
 * 
 * @author Victor Serbo (victor.serbo@joynme.com)
 *
 */
public interface NotificationView {

	/**
	 * Set minimal Swipe length, anything less is considered just tap
	 * @param swipeMin minimal Swipe length in pixels, default is 50
	 */
	void setSwipeMinimum(float swipeMin);
	
	/**
	 * Set additional listener to Tap actions, default is to hide notification.
	 * There can be only one additional listener, setting listener second time 
	 * wipes out the first one. 
	 * @param listener additional listener to Tap actions
	 */
	void setOnTapListener(OnTapListener listener);
	
	/**
	 * Set additional listener to Swipe actions, default is to hide notification
	 * There can be only one additional listener, setting listener second time 
	 * wipes out the first one. 
	 * @param listener additional listener to Swipe actions
	 */
	void setOnSwipeListener(OnSwipeListener listener);
	
	/**
	 * Can be called at any time to start hiding animation and close notification
	 */
	void finish();
}
