/**
 * Copyright 2009 Fred Sauer
 */
package com.allen_sauer.gwt.game.client.ui;

import com.allen_sauer.gwt.game.client.Game;
import com.allen_sauer.gwt.game.client.Game.State;
import com.allen_sauer.gwt.game.client.ui.util.DOMUtil;

import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.ui.FocusPanel;

import java.util.Arrays;

public class InputPanel extends FocusPanel {
  private int clickX;
  private int clickY;
  private boolean keyboardMode;
  private boolean[] keyDown = new boolean[0xff];
  private int pendingClicks;
  private boolean[] registeredKeys = new boolean[0xff];

  public InputPanel(final Game game) {
    Event.addNativePreviewHandler(new Event.NativePreviewHandler() {

      public void onPreviewNativeEvent(NativePreviewEvent event) {
        int keyCode;
        switch (event.getTypeInt()) {
          case Event.ONKEYDOWN:
            keyCode = event.getNativeEvent().getKeyCode();
            if (registeredKeys[keyCode]) {
              if (keyCode == ' '
                  && (game.getState() == State.STATE_PAUSED_BY_USER || game.getState() == State.STATE_GAME_OVER)) {
                game.setState(State.STATE_PLAYING);
              }
              if (keyCode == 'P' && game.getState() == State.STATE_PLAYING) {
                game.setState(State.STATE_PAUSED_BY_USER);
              }
              keyDown[keyCode & 0xff] = true;
              keyboardMode = true;
              //              return false;
            }
            break;
          case Event.ONKEYUP:
            keyCode = event.getNativeEvent().getKeyCode();
            if (registeredKeys[keyCode]) {
              keyDown[keyCode & 0xff] = false;
              //              return false;
            }
            break;
          case Event.ONBLUR:
            //            Log.debug("BLUR...: "
            //                + DOMUtil.getNodeName((Element) event.getNativeEvent().getCurrentEventTarget().cast())
            //                + "/"
            //                + DOMUtil.getNodeName((Element) event.getNativeEvent().getCurrentEventTarget().cast()));
            if (DOMUtil.allowFocusChangeCurrentTarget(event.getNativeEvent())) {
              if (game.getState() == State.STATE_PLAYING) {
                game.setState(State.STATE_SUSPENDED);
              }
              Arrays.fill(keyDown, false);
            }
            break;
          case Event.ONFOCUS:
            //            Log.debug("focus: "
            //                + DOMUtil.getNodeName((Element) event.getNativeEvent().getCurrentEventTarget().cast())
            //                + "/"
            //                + DOMUtil.getNodeName((Element) event.getNativeEvent().getCurrentEventTarget().cast()));
            if (DOMUtil.allowFocusChangeCurrentTarget(event.getNativeEvent())) {
              if (game.getState() == State.STATE_SUSPENDED) {
                game.setState(State.STATE_PLAYING);
              }
            }
            break;
          case Event.ONMOUSEDOWN:
            keyboardMode = false;
            if (game.getState() == State.STATE_PAUSED_BY_USER
                || game.getState() == State.STATE_GAME_OVER) {
              game.setState(State.STATE_PLAYING);
            } else {
              clickX = event.getNativeEvent().getClientX();
              clickY = event.getNativeEvent().getClientY();
              pendingClicks++;
            }
            break;
        }
        //        return true;
      }
    });
  }

  public boolean getClick() {
    if (pendingClicks > 0) {
      pendingClicks--;
      return true;
    }
    return false;
  }

  public int getClickX() {
    return clickX;
  }

  public int getClickY() {
    return clickY;
  }

  public boolean isKeyboardMode() {
    return keyboardMode;
  }

  public boolean isKeyDown(int keyCode) {
    return keyDown[keyCode & 0xff];
  }

  public void registerKey(int keyCode) {
    registeredKeys[keyCode & 0xff] = true;
  }
}
