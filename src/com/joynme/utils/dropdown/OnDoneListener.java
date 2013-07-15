package com.joynme.utils.dropdown;

import android.view.MotionEvent;

/**
 * Interface to handle actions after notification is done
 * 
 * @author Victor Serbo (victor.serbo@joynme.com)
 *
 */
public interface OnDoneListener {

    /**
     * Called when NotificationView is done with all animation, Tap/Swide actions, and is 
     * removed from its parent. This allows users to specify additional "after-notification" 
     * action, like cleanup, finish activity, or start new activity.
     */
    void onDone();
    
}
