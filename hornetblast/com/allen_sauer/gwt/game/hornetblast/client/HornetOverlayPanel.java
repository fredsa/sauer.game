/**
 * Copyright 2009 Fred Sauer
 */
package com.allen_sauer.gwt.game.hornetblast.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

import com.allen_sauer.gwt.game.hornetblast.client.ui.HornetKeyboardKey;
import com.allen_sauer.gwt.game.hornetblast.client.ui.HornetLabel;

public abstract class HornetOverlayPanel extends Composite {
  private FlowPanel flowPanel;

  public HornetOverlayPanel() {
    flowPanel = new FlowPanel();
    initWidget(flowPanel);
    addStyleName("HornetOverlayPanel");

    HornetLabel gameTitle = new HornetLabel("Hornet Blast");
    gameTitle.addStyleName("game-title");
    add(gameTitle);
  }

  protected void add(Widget widget) {
    flowPanel.add(widget);
  }

  protected Widget newCreditPanel() {
    FlowPanel panel = new FlowPanel();
    panel.add(new HornetLabel("Game by Fred Sauer"));
    panel.add(new HornetLabel("Pencil Artwork by Archer Sauer (age 5)"));
    panel.add(new HornetLabel(
        "Explosion Annimation by Boris, author of <a href='http://www.13thmonkey.org/~boris/jgame/'>JGame</a>"));
    panel.add(new HornetLabel(
        "Sound effects courtesy <a href='http://freesound.iua.upf.edu/'>The Freesound Project</a>"));
    panel.add(new HornetLabel(
        "Developed with <a href='http://code.google.com/p/gwt-voices/'>gwt-voices</a> and <a href='http://code.google.com/webtoolkit/'>Google Web Toolkit</a> (GWT)"));
    return panel;
  }

  protected Widget newKeyLayout() {
    FlexTable flexTable = new FlexTable();
    flexTable.addStyleName("hornet-key-layout");
    flexTable.setCellSpacing(10);
    newRow(flexTable, new HornetKeyboardKey("P"), "Pause");
    newRow(flexTable, HornetKeyboardKey.newArrowKeys(), "Ship movement");
    newRow(flexTable, HornetKeyboardKey.newSpaceBar(), "Fire / Start Game");
    return flexTable;
  }

  private void newRow(FlexTable flexTable, Widget keyGraphic, String text) {
    int row = flexTable.getRowCount() + 1;
    flexTable.setWidget(row, 0, keyGraphic);
    flexTable.setWidget(row, 1, new HornetLabel(text));
  }
}
