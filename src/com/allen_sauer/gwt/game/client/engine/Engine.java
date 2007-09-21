package com.allen_sauer.gwt.game.client.engine;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.WindowResizeListener;
import com.google.gwt.user.client.ui.RootPanel;

import com.allen_sauer.gwt.game.client.Game;
import com.allen_sauer.gwt.game.client.sprite.SpritePool;
import com.allen_sauer.gwt.game.client.ui.util.Page;

import java.util.ArrayList;

public final class Engine {
  public static final GameLayer background = new GameLayer();
  public static final boolean DEBUG = true;
  public static final GameLayer playfield = new GameLayer();

  static FrameListenerCollection frameListenerCollection = new FrameListenerCollection();

  private static int clientHeight;
  private static int clientWidth;
  private static FrameListenerCollection collisionFrameListeners = new FrameListenerCollection();
  private static EngineTimer engineTimer;
  private static Game game;
  private static boolean paused = true;
  private static FrameListenerCollection spriteFrameListeners = new FrameListenerCollection();
  private static ArrayList spritePools = new ArrayList();

  static {
    playfield.addStyleDependentName("playfield");
    background.addStyleDependentName("background");
  }

  public static void addCollisionFrameListener(FrameListener frameListener) {
    collisionFrameListeners.addFrameListener(frameListener);
  }

  public static void addSpriteFrameListener(
      FrameListenerCollection frameListener) {
    spriteFrameListeners.addFrameListener(frameListener);
  }

  public static void addSpritePool(SpritePool pool) {
    spritePools.add(pool);
  }

  public static int getClientHeight() {
    return clientHeight;
  }

  public static int getClientWidth() {
    return clientWidth;
  }

  public static String info() {
    return "frameListenerCollections=" + frameListenerCollection.size()
        + ", spritePools=" + spritePools.size();
  }

  public static void init(Game game) {
    Engine.game = game;

    frameListenerCollection.addFrameListener(spriteFrameListeners);
    frameListenerCollection.addFrameListener(collisionFrameListeners);

    setClientSize(Window.getClientWidth(), Window.getClientHeight());
    RootPanel.get().add(background, 0, 0);
    RootPanel.get().add(playfield, 0, 0);
    game.init();

    Window.addWindowResizeListener(new WindowResizeListener() {
      public void onWindowResized(int width, int height) {
        clientResized(width, height);
      }
    });

    engineTimer = new EngineTimer();

    // add hooks, force page focus and trigger game start
    Page.forceStaticInit();
  }

  public static boolean isPaused() {
    return paused;
  }

  public static void setPaused(boolean paused) {
    if (Engine.paused != paused) {
      Engine.paused = paused;
      engineTimer.setPaused(paused);
    }
  }

  private static void clientResized(int clientWidth, int clientHeight) {
    setClientSize(clientWidth, clientHeight);
    game.clientResized(clientWidth, clientHeight);
  }

  private static void setClientSize(int clientWidth, int clientHeight) {
    Engine.clientWidth = clientWidth;
    Engine.clientHeight = clientHeight;
    assert Window.getClientWidth() != 0;
    assert Window.getClientHeight() != 0;
  }

  private Engine() {
  }
}
