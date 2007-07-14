/*
 * Copyright 2007 Fred Sauer
 */
package com.allen_sauer.gwt.game.client;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.WindowResizeListener;
import com.google.gwt.user.client.ui.RootPanel;

public class Engine {
  private int clientHeight;
  private int clientWidth;
  private Game game;
  private GameTimer gameTimer;

  public Engine() {
  }

  public int getClientHeight() {
    return clientHeight;
  }

  public int getClientWidth() {
    return clientWidth;
  }

  public void init(Game game) {
    this.game = game;
    game.init(this);

    Window.addWindowResizeListener(new WindowResizeListener() {
      public void onWindowResized(int width, int height) {
        clientResized(width, height);
      }
    });

    clientResized(Window.getClientWidth(), Window.getClientHeight());

    gameTimer = new GameTimer(game);
    RootPanel.get().add(new GamePauseButton(gameTimer), 10, 40);
    gameTimer.setPaused(false);
  }

  private void clientResized(int clientWidth, int clientHeight) {
    this.clientWidth = clientWidth;
    this.clientHeight = clientHeight;
    game.handleClientResized(clientWidth, clientHeight);
    assert Window.getClientWidth() != 0;
    assert Window.getClientHeight() != 0;
  }
}
