/**
 * Copyright 2007 Fred Sauer
 */
package com.allen_sauer.gwt.game.hornetblast.client;

import com.google.gwt.user.client.ui.HTML;

import com.allen_sauer.gwt.game.client.Game;
import com.allen_sauer.gwt.game.hornetblast.client.ui.HornetLabel;

public class HornetGameOverPanel extends HornetOverlayPanel {
  private final Game game;

  public HornetGameOverPanel(Game game) {
    this.game = game;

    HornetLabel gameOver = new HornetLabel("GAME OVER");
    gameOver.addStyleName("hornet-game-state-label");
    add(gameOver);

    add(new HTML("&nbsp;"));

    add(newCreditPanel());

    add(newKeyLayout());
  }
}
