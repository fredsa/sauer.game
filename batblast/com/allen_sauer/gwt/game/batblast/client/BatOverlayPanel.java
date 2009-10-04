/**
 * Copyright 2009 Fred Sauer
 */
package com.allen_sauer.gwt.game.batblast.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

import com.allen_sauer.gwt.game.batblast.client.ui.BatKeyboardKey;
import com.allen_sauer.gwt.game.batblast.client.ui.BatLabel;

public abstract class BatOverlayPanel extends Composite {
  private FlowPanel flowPanel;

  public BatOverlayPanel() {
    flowPanel = new FlowPanel();
    initWidget(flowPanel);
    addStyleName("BatOverlayPanel");

    BatLabel gameTitle = new BatLabel("Bat Blast");
    gameTitle.addStyleName("game-title");
    add(gameTitle);
  }

  protected void add(Widget widget) {
    flowPanel.add(widget);
  }

  protected Widget newCreditPanel() {
    FlowPanel panel = new FlowPanel();
    panel.add(new BatLabel("Game by Fred Sauer"));
    panel.add(new BatLabel("Artwork by Skyler Sauer (age 5)"));
    //    panel.add(new BatLabel(
    //        "Explosion Annimation by Boris, author of <a href='http://www.13thmonkey.org/~boris/jgame/'>JGame</a>"));
    panel.add(new BatLabel(
        "Sound effects courtesy <a href='http://freesound.iua.upf.edu/'>The Freesound Project</a>"));
    panel.add(new BatLabel(
        "Developed with <a href='http://code.google.com/p/gwt-voices/'>gwt-voices</a> and <a href='http://code.google.com/webtoolkit/'>Google Web Toolkit</a> (GWT)"));
    return panel;
  }

  protected Widget newKeyLayout() {
    FlexTable flexTable = new FlexTable();
    flexTable.addStyleName("bat-key-layout");
    flexTable.setCellSpacing(10);
    newRow(flexTable, new BatKeyboardKey("P"), "Pause");
    newRow(flexTable, BatKeyboardKey.newArrowKeys(), "Ship movement");
    newRow(flexTable, BatKeyboardKey.newSpaceBar(), "Fire / Start Game");
    return flexTable;
  }

  private void newRow(FlexTable flexTable, Widget keyGraphic, String text) {
    int row = flexTable.getRowCount() + 1;
    flexTable.setWidget(row, 0, keyGraphic);
    flexTable.setWidget(row, 1, new BatLabel(text));
  }
}
