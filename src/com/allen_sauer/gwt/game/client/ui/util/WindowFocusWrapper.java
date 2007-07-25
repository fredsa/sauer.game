package com.allen_sauer.gwt.game.client.ui.util;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Widget;

import com.allen_sauer.gwt.log.client.Log;

import java.util.ArrayList;
import java.util.Iterator;

public class WindowFocusWrapper extends Widget {
  private static ArrayList focusListeners = new ArrayList();
//  static {
//    new WindowFocusWrapper();
//  }

  public static void addWindowFocusListener(WindowFocusListener listener) {
    focusListeners.add(listener);
  }

  public static void removeWindowFocusListener(WindowFocusListener listener) {
    focusListeners.remove(listener);
  }

  private static native String debugElement(Element elem)
  /*-{
    return //"elem=" + elem + "\n"
    + "elem.onfocus=" + elem.onfocus + "\n" 
    + "elem.onblur=" + elem.onblur + "\n" 
    ;
  }-*/;

  private static native void focusElement(Element elem)
  /*-{
    elem.focus();
  }-*/;

  private static native Element getWindowElement()
  /*-{
    return $wnd;
  }-*/;

  private boolean focused = true;

  public WindowFocusWrapper() {
    Element windowElement = getWindowElement();
    setElement(windowElement);
    onAttach();
    sinkEvents(Event.ONBLUR | Event.ONFOCUS);
    focusElement(windowElement);
    //    Log.debug(debugElement(windowElement));
  }

  public void onBrowserEvent(Event event) {
    int eventType = DOM.eventGetType(event);
    switch (eventType) {
      case Event.ONFOCUS:
        Log.debug("ONFOCUS");
        if (!focused) {
          focused = true;
          for (Iterator iterator = focusListeners.iterator(); iterator.hasNext();) {
            WindowFocusListener listener = (WindowFocusListener) iterator.next();
            listener.onFocus();
          }
        }
        break;
      case Event.ONBLUR:
        Log.debug("ONBLUR");
        if (focused) {
          focused = false;
          for (Iterator iterator = focusListeners.iterator(); iterator.hasNext();) {
            WindowFocusListener listener = (WindowFocusListener) iterator.next();
            listener.onLostFocus();
          }
        }
        break;
    }
  }
}
