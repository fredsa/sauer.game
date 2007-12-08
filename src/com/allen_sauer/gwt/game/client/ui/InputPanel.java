package com.allen_sauer.gwt.game.client.ui;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventPreview;
import com.google.gwt.user.client.ui.FocusListener;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Widget;

import com.allen_sauer.gwt.game.client.Game;
import com.allen_sauer.gwt.game.client.Game.State;

import java.util.Arrays;

public class InputPanel extends FocusPanel {
  private boolean[] keyDown = new boolean[0xff];

  public InputPanel(final Game game) {
    addFocusListener(new FocusListener() {
      public void onFocus(Widget sender) {
        //        Log.debug(game + ": onFocus()");
        if (game.getState() == State.STATE_SUSPENDED) {
          game.setState(State.STATE_PLAYING);
        }
      }

      public void onLostFocus(Widget sender) {
        //        Log.debug(game + ": onblur() ");
        if (game.getState() == State.STATE_PLAYING) {
          game.setState(State.STATE_SUSPENDED);
        }
        Arrays.fill(keyDown, false);
      }
    });

    DOM.addEventPreview(new EventPreview() {
      public boolean onEventPreview(Event event) {
        switch (DOM.eventGetType(event)) {
          case Event.ONKEYDOWN:
            int keyCode = DOM.eventGetKeyCode(event);
            if (keyCode == ' ' && game.getState() == State.STATE_PAUSED_BY_USER) {
              game.setState(State.STATE_PLAYING);
            }
            if (keyCode == 'P' && game.getState() == State.STATE_PLAYING) {
              game.setState(State.STATE_PAUSED_BY_USER);
            }
            keyDown[keyCode & 0xff] = true;
            return false;
          case Event.ONKEYUP:
            keyDown[DOM.eventGetKeyCode(event) & 0xff] = false;
            return false;
        }
        return true;
      }
    });
  }

  public boolean isKeyDown(int keyCode) {
    return keyDown[keyCode & 0xff];
  }
}
