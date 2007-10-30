package com.allen_sauer.gwt.game.client.ui;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Widget;

import com.allen_sauer.gwt.game.client.Game;
import com.allen_sauer.gwt.game.client.ui.util.DOMUtil;

public class Playfield extends AbsolutePanel {
  public Playfield(final Game game) {
  }

  public void add(Widget widget, int x, int y) {
    widget.removeFromParent();
    DOM.setStyleAttribute(widget.getElement(), "position", "absolute");
    setWidgetPosition(widget, x, y);
    super.add(widget);
  }

  public void setWidgetPosition(Widget widget, int x, int y) {
    DOMUtil.fastSetElementPosition(widget.getElement(), x, y);
  }
}
