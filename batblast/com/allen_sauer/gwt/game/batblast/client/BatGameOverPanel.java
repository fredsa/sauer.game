/**
 * Copyright 2009 Fred Sauer
 */
package com.allen_sauer.gwt.game.batblast.client;

import com.allen_sauer.gwt.game.batblast.client.ui.BatLabel;
import com.allen_sauer.gwt.game.client.Game;

import com.google.gwt.user.client.ui.HTML;

public class BatGameOverPanel extends BatOverlayPanel {
  public BatGameOverPanel(Game game) {
    BatLabel gameOver = new BatLabel("GAME OVER");
    gameOver.addStyleName("bat-game-state-label");
    add(gameOver);

    add(new HTML("&nbsp;"));

    add(newCreditPanel());

    add(newKeyLayout());
  }
}
