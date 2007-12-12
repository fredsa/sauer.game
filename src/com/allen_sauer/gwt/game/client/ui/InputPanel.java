package com.allen_sauer.gwt.game.client.ui;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventPreview;
import com.google.gwt.user.client.ui.FocusPanel;

import com.allen_sauer.gwt.game.client.Game;
import com.allen_sauer.gwt.game.client.Game.State;
import com.allen_sauer.gwt.game.client.ui.util.DOMUtil;

import java.util.Arrays;

public class InputPanel extends FocusPanel {
  private boolean[] keyDown = new boolean[0xff];
  private boolean[] registeredKeys = new boolean[0xff];

  public InputPanel(final Game game) {
    DOM.addEventPreview(new EventPreview() {
      public boolean onEventPreview(Event event) {
        int keyCode;
        switch (DOM.eventGetType(event)) {
          case Event.ONKEYDOWN:
            keyCode = DOM.eventGetKeyCode(event);
            if (registeredKeys[keyCode]) {
              if (keyCode == ' ' && game.getState() == State.STATE_PAUSED_BY_USER) {
                game.setState(State.STATE_PLAYING);
              }
              if (keyCode == 'P' && game.getState() == State.STATE_PLAYING) {
                game.setState(State.STATE_PAUSED_BY_USER);
              }
              keyDown[keyCode & 0xff] = true;
              return false;
            }
            break;
          case Event.ONKEYUP:
            keyCode = DOM.eventGetKeyCode(event);
            if (registeredKeys[keyCode]) {
              keyDown[keyCode & 0xff] = false;
              return false;
            }
            break;
          case Event.ONBLUR:
            if (DOMUtil.allowFocusChangeCurrentTarget(event)) {
              if (game.getState() == State.STATE_PLAYING) {
                game.setState(State.STATE_SUSPENDED);
              }
              Arrays.fill(keyDown, false);
            }
            break;
          case Event.ONFOCUS:
            if (DOMUtil.allowFocusChangeCurrentTarget(event)) {
              if (game.getState() == State.STATE_SUSPENDED) {
                game.setState(State.STATE_PLAYING);
              }
            }
            break;
        }
        return true;
      }
    });
  }

  public boolean isKeyDown(int keyCode) {
    return keyDown[keyCode & 0xff];
  }

  public void registerKey(int keyCode) {
    registeredKeys[keyCode & 0xff] = true;
  }
}
