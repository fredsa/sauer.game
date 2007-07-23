package com.allen_sauer.gwt.game.client;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.impl.FocusImpl;

import com.allen_sauer.gwt.game.client.engine.Engine;

public final class Keyboard {
  private static class BodyWidget extends Widget {
    public BodyWidget() {
      setElement(RootPanel.getBodyElement());
      onAttach();
      sinkEvents(Event.ONKEYDOWN | Event.ONKEYUP);
      FocusImpl.getFocusImplForPanel().focus(getElement());
    }

    public void onBrowserEvent(Event event) {
      char keyCode = (char) DOM.eventGetKeyCode(event);
      // int modifiers = KeyboardListenerCollection.getKeyboardModifiers(event);
      switch (DOM.eventGetType(event)) {

        case Event.ONKEYDOWN:
          //          Log.debug(keyCode + " down");
          keyDown[keyCode & 0xff] = true;
          if (keyCode == 'P') {
            Engine.setPaused(!Engine.isPaused());
          }
          break;

        case Event.ONKEYUP:
          //        Log.debug(keyCode + " up");
          keyDown[keyCode & 0xff] = false;
          break;

        case Event.ONKEYPRESS:
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
