/**
 * Copyright 2007 Fred Sauer
 */
package com.allen_sauer.gwt.game.batblast.client;

import com.google.gwt.user.client.ui.HTML;

import com.allen_sauer.gwt.game.batblast.client.ui.BatLabel;
import com.allen_sauer.gwt.game.client.FrameListener;
import com.allen_sauer.gwt.game.client.Game;

public class BatPausedPanel extends BatOverlayPanel implements FrameListener {
  private final Game game;

  public BatPausedPanel(Game game) {
    this.game = game;

    BatLabel pausedLabel = new BatLabel("GAME PAUSED");
    pausedLabel.addStyleName("bat-game-state-label");
    add(pausedLabel);

    add(new HTML("&nbsp;"));

    add(new BatLabel("Press the [SPACE BAR] to start"));

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
