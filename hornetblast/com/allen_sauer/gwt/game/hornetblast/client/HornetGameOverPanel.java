package com.allen_sauer.gwt.game.hornetblast.client;

import com.google.gwt.user.client.ui.HTML;

import com.allen_sauer.gwt.game.client.FrameListener;
import com.allen_sauer.gwt.game.client.Game;
import com.allen_sauer.gwt.game.hornetblast.client.ui.HornetLabel;

public class HornetGameOverPanel extends HornetOverlayPanel implements FrameListener {
  private final Game game;

  public HornetGameOverPanel(Game game) {
    this.game = game;

    HornetLabel gameOver = new HornetLabel("GAME OVER");
    gameOver.addStyleName("hornet-game-state-label");
    add(gameOver);

    add(new HTML("&nbsp;"));

    add(newCreditPanel());

    add(newKeyLayout());

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
}
