package com.allen_sauer.gwt.game.client.engine;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.WindowResizeListener;
import com.google.gwt.user.client.ui.RootPanel;

import com.allen_sauer.gwt.game.client.Game;
import com.allen_sauer.gwt.game.client.sprite.SpritePool;

import java.util.ArrayList;
import java.util.Iterator;

public class Engine {
  private static int clientHeight;
  private static int clientWidth;
  private static EngineTimer engineTimer;
  private static Game game;
  private static ArrayList spritePools = new ArrayList();

  public static void addSpritePool(SpritePool pool) {
    spritePools.add(pool);
  }

  public static void doFrame() {
    game.doFrame();
    for (Iterator iterator = spritePools.iterator(); iterator.hasNext();) {
      SpritePool pool = (SpritePool) iterator.next();
      pool.doFrame();
    }
  }

  public static int getClientHeight() {
    return clientHeight;
  }

  public static int getClientWidth() {
    return clientWidth;
  }

  public static void init(Game game) {
    Engine.game = game;
    game.init();

    Window.addWindowResizeListener(new WindowResizeListener() {
      public void onWindowResized(int width, int height) {
        clientResized(width, height);
      }
    });

    clientResized(Window.getClientWidth(), Window.getClientHeight());

    engineTimer = new EngineTimer();
    engineTimer.setPaused(false);
    EnginePauseButton enginePauseButton = new EnginePauseButton(engineTimer);
    RootPanel.get().add(enginePauseButton, 10, 40);
    //    FocusImpl.getFocusImplForPanel().focus(RootPanel.getBodyElement());
    enginePauseButton.setFocus(true);
  }

  private static void clientResized(int clientWidth, int clientHeight) {
    Engine.clientWidth = clientWidth;
    Engine.clientHeight = clientHeight;
    game.clientResized(clientWidth, clientHeight);
    assert Window.getClientWidth() != 0;
    assert Window.getClientHeight() != 0;
  }

  private Engine() {
  }
}
