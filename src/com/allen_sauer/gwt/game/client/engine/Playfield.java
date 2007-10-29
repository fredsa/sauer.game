package com.allen_sauer.gwt.game.client.engine;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.FocusListener;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.KeyboardListener;
import com.google.gwt.user.client.ui.Widget;

import com.allen_sauer.gwt.game.client.ui.util.DOMUtil;

public class Playfield extends AbsolutePanel {
  private FocusPanel focusPanel = new FocusPanel();

  private boolean[] keyDown = new boolean[0xff];

  public Playfield() {
    focusPanel.addStyleName("game-layer focuspanel");
    add(focusPanel, 0, 0);
    addStyleName("game-layer game-playfield");

    focusPanel.addKeyboardListener(new KeyboardListener() {
      public void onKeyDown(Widget sender, char keyCode, int modifiers) {
        // Log.debug(keyCode + " down");
        keyDown[keyCode & 0xff] = true;
        if (keyCode == 'P') {
          Engine.setPaused(!Engine.isPaused());
        }
      }

      public void onKeyPress(Widget sender, char keyCode, int modifiers) {
      }

      public void onKeyUp(Widget sender, char keyCode, int modifiers) {
        // Log.debug(keyCode + " up");
        keyDown[keyCode & 0xff] = false;
      }
    });

    focusPanel.addFocusListener(new FocusListener() {
      public void onFocus(Widget sender) {
        Engine.setPaused(false);
      }

      public void onLostFocus(Widget sender) {
        Engine.setPaused(true);
      }
    });
  }

  public void add(Widget widget, int x, int y) {
    widget.removeFromParent();
    DOM.setStyleAttribute(widget.getElement(), "position", "absolute");
    setWidgetPosition(widget, x, y);
    super.add(widget);
  }

  public void addFocusListener(FocusListener focusListener) {
    focusPanel.addFocusListener(focusListener);
  }

  public boolean isKeyDown(int keyCode) {
    return keyDown[keyCode & 0xff];
  }

  public void setFocus(boolean focused) {
    focusPanel.setFocus(focused);
  }

  public void setWidgetPosition(Widget widget, int x, int y) {
    DOMUtil.setElementPosition(widget.getElement(), x, y);
  }
}
