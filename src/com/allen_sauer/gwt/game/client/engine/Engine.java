package com.allen_sauer.gwt.game.client.engine;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.WindowResizeListener;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.RootPanel;

import com.allen_sauer.gwt.game.client.Game;
import com.allen_sauer.gwt.game.client.sprite.SpritePool;
import com.allen_sauer.gwt.game.client.ui.util.Page;

import java.util.ArrayList;
import java.util.Iterator;

public final class Engine {
  public static final AbsolutePanel playfield = new AbsolutePanel();

  private static int clientHeight;
  private static int clientWidth;
  private static EngineTimer engineTimer;
  private static final ArrayList frameListeners = new ArrayList();
  private static Game game;
  private static ArrayList newFrameListeners = new ArrayList();
  private static boolean paused = true;
  private static ArrayList removeFrameListeners = new ArrayList();
  private static ArrayList spritePools = new ArrayList();

  static {
    playfield.addStyleName("playfield");
  }

  public static void addFrameListener(FrameListener listener) {
    newFrameListeners.add(listener);
    listener.doFirstFrame();
  }

  public static void addSpritePool(SpritePool pool) {
    spritePools.add(pool);
  }

  /**
   * Main loop executed via callback by {@link EngineTimer}.
   */
  public static void doFrame() {
    updateFrameListenerCollections();
    doFrameListenerFrames();
  }

  public static int getClientHeight() {
    return clientHeight;
  }

  public static int getClientWidth() {
    return clientWidth;
  }

  public static String info() {
    return "frameListeners=" + frameListeners.size() + ", spritePools=" + spritePools.size();
  }

  public static void init(Game game) {
    Engine.game = game;
    setClientSize(Window.getClientWidth(), Window.getClientHeight());
    game.init();

    Window.addWindowResizeListener(new WindowResizeListener() {
      public void onWindowResized(int width, int height) {
        clientResized(width, height);
      }
    });

    RootPanel.get().add(playfield, 0, 0);

    engineTimer = new EngineTimer();

    // add hooks, force page focus and trigger game start
    Page.forceStaticInit();
  }

  public static boolean isPaused() {
    return paused;
  }

  public static void removeFrameListener(FrameListener listener) {
    removeFrameListeners.add(listener);
    listener.doLastFrame();
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

  private static void doFrameListenerFrames() {
    for (Iterator iterator = frameListeners.iterator(); iterator.hasNext();) {
      FrameListener listener = (FrameListener) iterator.next();
      listener.doFrame();
    }
  }

  private static void setClientSize(int clientWidth, int clientHeight) {
    Engine.clientWidth = clientWidth;
    Engine.clientHeight = clientHeight;
    assert Window.getClientWidth() != 0;
    assert Window.getClientHeight() != 0;
  }

  /**
   * Use multiple collections to avoid ConcurrentModificationException
   */
  private static void updateFrameListenerCollections() {
    if (frameListeners.removeAll(removeFrameListeners)) {
      removeFrameListeners.clear();
    }
    if (frameListeners.addAll(newFrameListeners)) {
      newFrameListeners.clear();
    }
  }

  private Engine() {
  }
}
