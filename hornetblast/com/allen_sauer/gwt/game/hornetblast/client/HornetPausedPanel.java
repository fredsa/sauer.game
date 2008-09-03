/**
 * Copyright 2007 Fred Sauer
 */
package com.allen_sauer.gwt.game.hornetblast.client;

import com.google.gwt.user.client.ui.HTML;

import com.allen_sauer.gwt.game.client.FrameListener;
import com.allen_sauer.gwt.game.client.Game;
import com.allen_sauer.gwt.game.hornetblast.client.ui.HornetLabel;

public class HornetPausedPanel extends HornetOverlayPanel implements FrameListener {
  private final Game game;

  public HornetPausedPanel(Game game) {
    this.game = game;

    HornetLabel pausedLabel = new HornetLabel("GAME PAUSED");
    pausedLabel.addStyleName("hornet-game-state-label");
    add(pausedLabel);

    add(new HTML("&nbsp;"));

    add(new HornetLabel("Press the [SPACE BAR] to start"));

    add(new HTML("&nbsp;"));

    add(newKeyLayout());

    add(new HTML("&nbsp;"));

    add(newCreditPanel());

    game.getGameOverFrameListenerCollection().addFrameListener(this);
  }

  public void doFirstFrame() {
  }

  public boolean doFrame(double millis) {
    if (game.input.isKeyDown(' ')) {
      game.setState(Game.State.STATE_PLAYING);
    }
    return true;
  }

  public void doLastFrame() {
  }
}
