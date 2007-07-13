package com.allen_sauer.gwt.game.client;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Widget;

public class GamePauseButton extends Button {
  private class GamePauseClickListener implements ClickListener {
    public void onClick(Widget sender) {
      GamePauseButton.this.game.setPaused(!GamePauseButton.this.game.isPaused());
    }
  }

  private final Game game;
  private ClickListener gamePauseClickListener = new GamePauseClickListener();
  
  public GamePauseButton(Game game) {
    super("Pause");
    this.game = game;
    DOM.setStyleAttribute(getElement(), "width", "5em");

    addClickListener(gamePauseClickListener);
  }
}
