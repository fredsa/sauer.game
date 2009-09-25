/**
 * Copyright 2007 Fred Sauer
 */
package com.allen_sauer.gwt.game.batblast.client.ui;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class BatKeyboardKey extends Composite {
  public static Widget newArrowKeys() {
    VerticalPanel verticalPanel = new VerticalPanel();
    verticalPanel.addStyleName("bat-key-layout");
    verticalPanel.add(newUp());
    HorizontalPanel horizontalPanel = new HorizontalPanel();
    horizontalPanel.setSpacing(3);
    verticalPanel.add(horizontalPanel);
    horizontalPanel.add(newLeft());
    horizontalPanel.add(newDown());
    horizontalPanel.add(newRight());
    return verticalPanel;
  }

  public static BatKeyboardKey newDown() {
    return new BatKeyboardKey("\u2193");
  }

  public static BatKeyboardKey newLeft() {
    return new BatKeyboardKey("\u2190");
  }

  public static BatKeyboardKey newReturn() {
    return new BatKeyboardKey("\u21b5");
  }

  public static BatKeyboardKey newRight() {
    return new BatKeyboardKey("\u2192");
  }

  public static BatKeyboardKey newSpaceBar() {
    return new BatKeyboardKey("&nbsp;&nbsp;&nbsp;SPACE&nbsp;BAR&nbsp;&nbsp;&nbsp;");
  }

  public static BatKeyboardKey newUp() {
    return new BatKeyboardKey("\u2191");
  }

  public BatKeyboardKey(String text) {
    Grid grid = new Grid(1, 1);
    initWidget(grid);
    addStyleName("bat-keyboard-key");

    BatLabel label = new BatLabel(text);
    grid.setWidget(0, 0, label);
    grid.getCellFormatter().addStyleName(0, 0, "bat-keyboard-key-cell");
  }
}
