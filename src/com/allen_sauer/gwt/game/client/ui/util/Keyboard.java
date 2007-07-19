package com.allen_sauer.gwt.game.client.ui.util;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public final class Keyboard {
  private static class BodyWidget extends Widget {
    public BodyWidget() {
      setElement(RootPanel.getBodyElement());
      onAttach();
      sinkEvents(Event.ONKEYDOWN | Event.ONKEYUP);
    }

    public void onBrowserEvent(Event event) {
      char keyCode = (char) DOM.eventGetKeyCode(event);
      // int modifiers = KeyboardListenerCollection.getKeyboardModifiers(event);
      switch (DOM.eventGetType(event)) {

        case Event.ONKEYDOWN:
          keyDown[keyCode & 0xff] = true;
          //          Log.debug(keyCode + " down");
          break;

        case Event.ONKEYUP:
          keyDown[keyCode & 0xff] = false;
          //        Log.debug(keyCode + " up");
          break;

        case Event.ONKEYPRESS:
          //      Log.debug(keyCode + " press");
          break;
      }
    }
  }

  private static boolean[] keyDown = new boolean[0xff];

  static {
    new BodyWidget();
  }

  public static boolean isKeyDown(int keyCode) {
    return keyDown[keyCode & 0xff];
  };
}
