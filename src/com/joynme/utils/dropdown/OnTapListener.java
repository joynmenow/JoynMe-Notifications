package com.joynme.utils.dropdown;

import android.view.MotionEvent;

/**
 * Interface to handle tapping on notification
 * 
 * @author Victor Serbo (victor.serbo@joynme.com)
 *
 */
public interface OnTapListener {

    /**
     * Called when a tap event is dispatched to a view. This allows listeners to
     * get a chance to respond before the target view.
     *
     * @param event The MotionEvent object containing full information about
     *        the event.
     * @return True if the listener has consumed the event, false otherwise.
     */
    boolean onTap(MotionEvent event);
    
}
