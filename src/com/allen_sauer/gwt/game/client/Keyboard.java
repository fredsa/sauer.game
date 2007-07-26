package com.allen_sauer.gwt.game.client;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.KeyboardListener;
import com.google.gwt.user.client.ui.Widget;

import com.allen_sauer.gwt.game.client.engine.Engine;
import com.allen_sauer.gwt.log.client.Log;

public final class Keyboard {
  public static interface CODES extends KeyboardListener {
  }
  private static class DocumentWrapperWidget extends Widget {
    private static native Element getDocumentElement()
    /*-{
      return $doc;
    }-*/;

    private DocumentWrapperWidget() {
      setElement(getDocumentElement());
      onAttach();
      sinkEvents(Event.ONKEYDOWN | Event.ONKEYUP);
    }

    public void onBrowserEvent(Event event) {
      Log.debug("  onBrowserEvent(" + DOM.eventGetTypeString(event) + ")");
      char keyCode;
      // int modifiers = KeyboardListenerCollection.getKeyboardModifiers(event);
      int eventType = DOM.eventGetType(event);
      // Log.debug("eventType=" + eventType);
      switch (eventType) {

        case Event.ONKEYDOWN:
          keyCode = (char) DOM.eventGetKeyCode(event);
          //          Log.debug(keyCode + " down");
          keyDown[keyCode & 0xff] = true;
          if (keyCode == 'P') {
            Engine.setPaused(!Engine.isPaused());
          }
          break;

        case Event.ONKEYUP:
          keyCode = (char) DOM.eventGetKeyCode(event);
          //        Log.debug(keyCode + " up");
          keyDown[keyCode & 0xff] = false;
          break;

        case Event.ONKEYPRESS:
          keyCode = (char) DOM.eventGetKeyCode(event);
          //          Log.debug(keyCode + " press");
          break;
      }
    }
  }

  private static boolean[] keyDown = new boolean[0xff];

  static {
    Log.debug("BEFORE:" + debug());
    new DocumentWrapperWidget();
    Log.debug("AFTER:" + debug());
  }

  public static void clearKeyDown(int keyCode) {
    keyDown[keyCode & 0xff] = false;
  }

  public static boolean isKeyDown(int keyCode) {
    return keyDown[keyCode & 0xff];
  }

  private static native String debug()
  /*-{
    return ""
      + "\n$wnd.onfocus=" + $wnd.onfocus
      + "\n$doc.onfocus=" + $doc.onfocus
      ;
  }-*/;;
}
