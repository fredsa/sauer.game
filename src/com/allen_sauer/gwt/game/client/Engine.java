/*
 * Copyright 2007 Fred Sauer
 */
package com.allen_sauer.gwt.game.client;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.WindowResizeListener;
import com.google.gwt.user.client.ui.RootPanel;

import java.util.ArrayList;
import java.util.Iterator;

public class Engine {
  private int clientHeight;
  private int clientWidth;
  private EngineTimer engineTimer;
  private Game game;
  private ArrayList sprites = new ArrayList();

  public Engine() {
  }

  public void doFrame() {
    game.doFrame();
    for (Iterator iterator = sprites.iterator(); iterator.hasNext();) {
      Sprite sprite = (Sprite) iterator.next();
      sprite.doFrame();
    }
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

    engineTimer = new EngineTimer(this);
    engineTimer.setPaused(false);
    GamePauseButton gamePauseButton = new GamePauseButton(engineTimer);
    RootPanel.get().add(gamePauseButton, 10, 40);
    //    FocusImpl.getFocusImplForPanel().focus(RootPanel.getBodyElement());
    gamePauseButton.setFocus(true);
  }

  public void registerSprite(Sprite sprite) {
    sprites.add(sprite);
  }

  private void clientResized(int clientWidth, int clientHeight) {
    this.clientWidth = clientWidth;
    this.clientHeight = clientHeight;
    game.clientResized(clientWidth, clientHeight);
    assert Window.getClientWidth() != 0;
    assert Window.getClientHeight() != 0;
  }
}
