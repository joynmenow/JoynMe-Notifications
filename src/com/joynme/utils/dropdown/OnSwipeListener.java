package com.joynme.utils.dropdown;

import android.view.MotionEvent;

/**
 * Interface to handle swiping on notification view
 * 
 * @author Victor Serbo (victor.serbo@joynme.com)
 *
 */
public interface OnSwipeListener {

    /**
     * Called when a tap event is dispatched to a view. This allows listeners to
     * get a chance to respond before the target view.
     *
     * @param event The MotionEvent object containing full information about
     *        the event.
     * @param deltaX The length of the swipe in horizontal direction.
     * @param deltaY The length of the swipe in vertical direction.
     * @return True if the listener has consumed the event, false otherwise.
     */
    boolean onSwipe(MotionEvent event, float deltaX, float deltaY);
    
}
