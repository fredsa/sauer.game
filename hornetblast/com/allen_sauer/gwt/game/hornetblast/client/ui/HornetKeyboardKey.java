package com.allen_sauer.gwt.game.hornetblast.client.ui;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class HornetKeyboardKey extends Composite {
  public static Widget newArrowKeys() {
    VerticalPanel verticalPanel = new VerticalPanel();
    verticalPanel.add(newUp());
    HorizontalPanel horizontalPanel = new HorizontalPanel();
    horizontalPanel.setSpacing(3);
    verticalPanel.add(horizontalPanel);
    horizontalPanel.add(newLeft());
    horizontalPanel.add(newDown());
    horizontalPanel.add(newRight());
    return verticalPanel;
  }

  public static HornetKeyboardKey newDown() {
    return new HornetKeyboardKey("\u2193");
  }

  public static HornetKeyboardKey newLeft() {
    return new HornetKeyboardKey("\u2190");
  }

  public static HornetKeyboardKey newReturn() {
    return new HornetKeyboardKey("\u21b5");
  }

  public static HornetKeyboardKey newRight() {
    return new HornetKeyboardKey("\u2192");
  }

  public static HornetKeyboardKey newSpaceBar() {
    return new HornetKeyboardKey("&nbsp;&nbsp;&nbsp;SPACE&nbsp;BAR&nbsp;&nbsp;&nbsp;");
  }

  public static HornetKeyboardKey newUp() {
    return new HornetKeyboardKey("\u2191");
  }

  public HornetKeyboardKey(String text) {
    Grid grid = new Grid(1, 1);
    initWidget(grid);
    addStyleName("hornet-keyboard-key");

    HornetLabel label = new HornetLabel(text);
    grid.setWidget(0, 0, label);
    grid.getCellFormatter().addStyleName(0, 0, "hornet-keyboard-key-cell");
  }
}
