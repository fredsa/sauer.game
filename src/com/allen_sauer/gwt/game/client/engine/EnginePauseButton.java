package com.allen_sauer.gwt.game.client.engine;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Widget;

public class EnginePauseButton extends Button {
  private class GamePauseClickListener implements ClickListener {
    public void onClick(Widget sender) {
      gameTimer.setPaused(!gameTimer.isPaused());
    }
  }

  private ClickListener gamePauseClickListener = new GamePauseClickListener();
  private final EngineTimer gameTimer;

  public EnginePauseButton(EngineTimer gameTimer) {
    super("Pause");
    this.gameTimer = gameTimer;
    DOM.setStyleAttribute(getElement(), "width", "5em");

    addClickListener(gamePauseClickListener);
  }
}
