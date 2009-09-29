/**
 * Copyright 2009 Fred Sauer
 */
package com.allen_sauer.gwt.game.batblast.client;

import com.google.gwt.user.client.ui.HTML;

import com.allen_sauer.gwt.game.batblast.client.ui.BatLabel;
import com.allen_sauer.gwt.game.client.Game;

public class BatSuspendedPanel extends BatOverlayPanel {
  public BatSuspendedPanel(Game game) {
    BatLabel pausedLabel = new BatLabel("GAME PLAY SUSPENDED");
    pausedLabel.addStyleName("bat-game-state-label");
    add(pausedLabel);

    add(new HTML("&nbsp;"));

    add(new BatLabel("Click on game to resume"));
  }
}
