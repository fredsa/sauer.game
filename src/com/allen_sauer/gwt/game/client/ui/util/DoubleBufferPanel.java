package com.allen_sauer.gwt.game.client.ui.util;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class DoubleBufferPanel extends Composite {
  private static final native void setBuffer(Element elem, boolean show)
  /*-{
    elem.style.visibility = show ? "" : "hidden";
  }-*/;

  private AbsolutePanel container = new AbsolutePanel();
  private int currentIndex;

  private Element element[] = new Element[2];

  public DoubleBufferPanel(Widget panel0, Widget panel1) {
    initWidget(container);
    initBufferPanel(panel0);
    initBufferPanel(panel1);
    element[0] = panel0.getElement();
    element[1] = panel1.getElement();
    setBuffer(element[currentIndex ^ 0x01], false);
  }

  public void showBuffer(int index) {
    setBuffer(element[currentIndex], false);
    currentIndex = index;
    setBuffer(element[currentIndex], true);
  }

  private void initBufferPanel(Widget panel) {
    container.add(panel, 0, 0);
    DOM.setStyleAttribute(panel.getElement(), "width", "100%");
    DOM.setStyleAttribute(panel.getElement(), "height", "100%");
  }
}
