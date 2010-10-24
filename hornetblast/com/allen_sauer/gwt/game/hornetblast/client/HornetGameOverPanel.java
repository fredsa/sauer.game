/**
 * Copyright 2009 Fred Sauer
 */
package com.allen_sauer.gwt.game.hornetblast.client;

import com.allen_sauer.gwt.game.client.Game;
import com.allen_sauer.gwt.game.hornetblast.client.ui.HornetLabel;

import com.google.gwt.user.client.ui.HTML;

public class HornetGameOverPanel extends HornetOverlayPanel {
  public HornetGameOverPanel(Game game) {
    HornetLabel gameOver = new HornetLabel("GAME OVER");
    gameOver.addStyleName("hornet-game-state-label");
    add(gameOver);

    add(new HTML("&nbsp;"));

    add(newCreditPanel());

    add(newKeyLayout());
  }
}
