/**
 * Copyright 2009 Fred Sauer
 */
package com.allen_sauer.gwt.game.client.ui;

import com.allen_sauer.gwt.game.client.Game;
import com.allen_sauer.gwt.game.client.ui.util.DOMUtil;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Widget;

public class Playfield extends AbsolutePanel {
  public Playfield(final Game game) {
  }

  @Override
  public void add(Widget widget, int x, int y) {
    widget.removeFromParent();
    DOM.setStyleAttribute(widget.getElement(), "position", "absolute");
    setWidgetPosition(widget, x, y);
    super.add(widget);
  }

  @Override
  public void setWidgetPosition(Widget widget, int x, int y) {
    DOMUtil.fastSetElementPosition(widget.getElement(), x, y);
  }
}
