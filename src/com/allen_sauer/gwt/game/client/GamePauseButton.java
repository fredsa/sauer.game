/*
 * Copyright 2007 Fred Sauer
 */
package com.allen_sauer.gwt.game.client;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Widget;

public class GamePauseButton extends Button {
  private class GamePauseClickListener implements ClickListener {
    public void onClick(Widget sender) {
      gameTimer.setPaused(!gameTimer.isPaused());
    }
  }

  private ClickListener gamePauseClickListener = new GamePauseClickListener();
  private final GameTimer gameTimer;

  public GamePauseButton(GameTimer gameTimer) {
    super("Pause");
    this.gameTimer = gameTimer;
    DOM.setStyleAttribute(getElement(), "width", "5em");

    addClickListener(gamePauseClickListener);
  }
}
