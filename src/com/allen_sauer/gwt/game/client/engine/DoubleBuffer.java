package com.allen_sauer.gwt.game.client.engine;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

import com.allen_sauer.gwt.game.client.ui.util.DoubleBufferPanel;

public class DoubleBuffer {
  public static final AbsolutePanel[] buffer = {new AbsolutePanel(), new AbsolutePanel(),};
  public static final boolean USE_DOUBLE_BUFFERING = true;
  private static AbsolutePanel backBuffer;
  private static int backBufferIndex = 1;
  private static DoubleBufferPanel container;
  private static int currentBufferIndex = 0;

  static {
    buffer[0].add(new Label("0"));
    buffer[1].add(new Label("1"));
    container = new DoubleBufferPanel(buffer[0], buffer[1]);
    RootPanel.get().add(container, 0, 0);
    DOM.setStyleAttribute(container.getElement(), "width", "100%");
    DOM.setStyleAttribute(container.getElement(), "height", "100%");
    container.showWidget(USE_DOUBLE_BUFFERING ? currentBufferIndex : backBufferIndex);
  }

  public static AbsolutePanel getBackBuffer() {
    return backBuffer;
  }

  public static int getBackBufferIndex() {
    return backBufferIndex;
  }

  public static void swap() {
    if (USE_DOUBLE_BUFFERING) {
      backBuffer = buffer[backBufferIndex = currentBufferIndex];
      container.showWidget(currentBufferIndex ^= 0x01);
    }
  }
}
