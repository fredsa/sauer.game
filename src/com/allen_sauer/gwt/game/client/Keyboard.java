package com.allen_sauer.gwt.game.client;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.KeyboardListener;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.impl.FocusImpl;

import com.allen_sauer.gwt.game.client.engine.Engine;
import com.allen_sauer.gwt.log.client.Log;

public final class Keyboard {
  public static interface CODES extends KeyboardListener {
  }
  private static class BodyWidget extends Widget {
    private static native void focus(Element elem)
    /*-{
      elem.tabIndex = 0;
      elem.focus();
    }-*/;

    public BodyWidget() {
      Element bodyElement = RootPanel.getBodyElement();
      setElement(bodyElement);
      onAttach();
      sinkEvents(Event.ONKEYDOWN | Event.ONKEYUP | Event.ONCLICK);// | Event.ONBLUR | Event.ONFOCUS);

      FocusImpl focusImpl = FocusImpl.getFocusImplForPanel();

      // TODO Replace with focusImpl.focus(getElement()) after both parts of GWT issue 1425 have been fixed
      focus(getElement());
    }

    public void onBrowserEvent(Event event) {
      char keyCode;
      // int modifiers = KeyboardListenerCollection.getKeyboardModifiers(event);
      switch (DOM.eventGetType(event)) {
//
//        case Event.ONFOCUS:
//          Log.debug("focus");
//          Engine.setPaused(true);
//          break;
//
//        case Event.ONBLUR:
//          Log.debug("blur");
//          Engine.setPaused(false);
//          break;

        case Event.ONCLICK:
          //          Log.debug("click");
          focus(getElement());
          break;

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
    new BodyWidget();
  }

  public static void clearKeyDown(int keyCode) {
    keyDown[keyCode & 0xff] = false;
  }

  public static boolean isKeyDown(int keyCode) {
    return keyDown[keyCode & 0xff];
  };
}
