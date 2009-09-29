/**
 * Copyright 2009 Fred Sauer
 */
package com.allen_sauer.gwt.game.hornetblast.client;

import com.google.gwt.user.client.ui.HTML;

import com.allen_sauer.gwt.game.client.Game;
import com.allen_sauer.gwt.game.hornetblast.client.ui.HornetLabel;

public class HornetSuspendedPanel extends HornetOverlayPanel {
  public HornetSuspendedPanel(Game game) {
    HornetLabel pausedLabel = new HornetLabel("GAME PLAY SUSPENDED");
    pausedLabel.addStyleName("hornet-game-state-label");
    add(pausedLabel);

    add(new HTML("&nbsp;"));

    add(new HornetLabel("Click on game to resume"));
  }
}
