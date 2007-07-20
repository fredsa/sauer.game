package com.allen_sauer.gwt.game.client.engine;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

public class DoubleBuffer {
  public static final AbsolutePanel[] buffer = {new AbsolutePanel(), new AbsolutePanel(),};
  private static AbsolutePanel backBuffer;
  private static int backBufferIndex = 1;
  private static final DeckPanel container = new DeckPanel();
  private static int currentBufferIndex = 0;

  static {
    RootPanel.get().add(container, 0, 0);
    DOM.setStyleAttribute(container.getElement(), "width", "100%");
    DOM.setStyleAttribute(container.getElement(), "height", "100%");
    container.add(buffer[0]);
    container.add(buffer[1]);
    buffer[0].add(new Label("0"));
    buffer[1].add(new Label("1"));
    swap();
  }

  public static AbsolutePanel getBackBuffer() {
    return backBuffer;
  }

  public static int getBackBufferIndex() {
    return backBufferIndex;
  }

  public static void swap() {
    backBuffer = buffer[backBufferIndex = currentBufferIndex];
    container.showWidget(currentBufferIndex ^= 0x01);
  }
}
