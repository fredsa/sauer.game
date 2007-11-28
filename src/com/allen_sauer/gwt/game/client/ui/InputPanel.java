package com.allen_sauer.gwt.game.client.ui;

import com.google.gwt.user.client.ui.FocusListener;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.KeyboardListener;
import com.google.gwt.user.client.ui.Widget;

import com.allen_sauer.gwt.game.client.Game;
import com.allen_sauer.gwt.game.client.Game.State;

public class InputPanel extends FocusPanel {
  private boolean[] keyDown = new boolean[0xff];

  public InputPanel(final Game game) {
    addFocusListener(new FocusListener() {
      public void onFocus(Widget sender) {
        //        Log.debug(game + ": onFocus()");
        if (game.getState() == State.STATE_PAUSED) {
          game.setState(State.STATE_PLAYING);
        }
      }

      public void onLostFocus(Widget sender) {
        //        Log.debug(game + ": onblur() ");
        if (game.getState() == State.STATE_PLAYING) {
          game.setState(State.STATE_PAUSED);
        }
      }
    });

    addKeyboardListener(new KeyboardListener() {
      public void onKeyDown(Widget sender, char keyCode, int modifiers) {
        // Log.debug(keyCode + " down");
        keyDown[keyCode & 0xff] = true;
        if (keyCode == 'P') {
          if (game.getState() == State.STATE_PAUSED) {
            game.setState(State.STATE_PLAYING);
          } else if (game.getState() == State.STATE_PLAYING) {
            game.setState(State.STATE_PAUSED);
          }
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
