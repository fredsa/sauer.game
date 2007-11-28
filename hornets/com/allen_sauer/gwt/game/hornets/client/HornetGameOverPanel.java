package com.allen_sauer.gwt.game.hornets.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

import com.allen_sauer.gwt.game.client.FrameListener;
import com.allen_sauer.gwt.game.client.Game;
import com.allen_sauer.gwt.game.hornets.client.ui.HornetKeyboardKey;
import com.allen_sauer.gwt.game.hornets.client.ui.HornetLabel;

public class HornetGameOverPanel extends Composite implements FrameListener {
  private final Game game;

  public HornetGameOverPanel(Game game) {
    this.game = game;
    FlowPanel flowPanel = new FlowPanel();
    initWidget(flowPanel);
    addStyleName("HornetGameOverPanel");

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

    game.getGameOverFrameListenerCollection().addFrameListener(this);
  }

  public void doFirstFrame() {
  }

  public boolean doFrame() {
    if (game.input.isKeyDown(' ')) {
      game.setState(Game.State.STATE_PLAYING);
    }
    return true;
  }

  public void doLastFrame() {
  }

  private void newRow(FlexTable flexTable, Widget keyGraphic, String text) {
    int row = flexTable.getRowCount() + 1;
    flexTable.setWidget(row, 0, keyGraphic);
    flexTable.setWidget(row, 1, new HornetLabel(text));
  }
}
