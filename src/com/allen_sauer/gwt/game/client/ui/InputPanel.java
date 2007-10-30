package com.allen_sauer.gwt.game.client.ui;

import com.google.gwt.user.client.ui.FocusListener;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.KeyboardListener;
import com.google.gwt.user.client.ui.Widget;

import com.allen_sauer.gwt.game.client.Game;

public class InputPanel extends FocusPanel {
  private boolean[] keyDown = new boolean[0xff];

  public InputPanel(final Game game) {
    addFocusListener(new FocusListener() {
      public void onFocus(Widget sender) {
        //        Log.debug(game + ": onfocus()");
        game.setPaused(false);
      }

      public void onLostFocus(Widget sender) {
        //        Log.debug(game + ": onblur()");
        game.setPaused(true);
      }
    });

    addKeyboardListener(new KeyboardListener() {
      public void onKeyDown(Widget sender, char keyCode, int modifiers) {
        // Log.debug(keyCode + " down");
        keyDown[keyCode & 0xff] = true;
        if (keyCode == 'P') {
          game.setPaused(!game.isPaused());
        }
      }

      public void onKeyPress(Widget sender, char keyCode, int modifiers) {
      }

      public void onKeyUp(Widget sender, char keyCode, int modifiers) {
        // Log.debug(keyCode + " up");
        keyDown[keyCode & 0xff] = false;
      }
    });
  }

  public boolean isKeyDown(int keyCode) {
    return keyDown[keyCode & 0xff];
  }
}
