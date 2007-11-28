package com.allen_sauer.gwt.game.hornets.client;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

import com.allen_sauer.gwt.game.hornets.client.ui.HornetKeyboardKey;
import com.allen_sauer.gwt.game.hornets.client.ui.HornetLabel;

public class HornetGameOverPanel extends Composite {
  public HornetGameOverPanel() {
    FlowPanel flowPanel = new FlowPanel();
    initWidget(flowPanel);
    DOM.setStyleAttribute(getElement(), "textAlign", "center");
    DOM.setStyleAttribute(getElement(), "margin", "3em");

    HornetLabel gameOver = new HornetLabel("GAME OVER");
    gameOver.addStyleName("hornet-game-over");
    flowPanel.add(gameOver);
    flowPanel.add(new HTML("&nbsp;"));

    FlexTable flexTable = new FlexTable();
    flexTable.addStyleName("hornet-key-layout");
    flexTable.setCellSpacing(10);
    newRow(flexTable, new HornetKeyboardKey("P"), "Pause");
    newRow(flexTable, HornetKeyboardKey.newArrowKeys(), "Ship movement");
    newRow(flexTable, HornetKeyboardKey.newSpaceBar(), "Fire");
    flowPanel.add(flexTable);
  }

  private void newRow(FlexTable flexTable, Widget keyGraphic, String text) {
    int row = flexTable.getRowCount() + 1;
    flexTable.setWidget(row, 0, keyGraphic);
    flexTable.setWidget(row, 1, new HornetLabel(text));
  }
}
