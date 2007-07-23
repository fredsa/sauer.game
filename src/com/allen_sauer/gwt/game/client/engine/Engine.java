package com.allen_sauer.gwt.game.client.engine;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.WindowResizeListener;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.RootPanel;

import com.allen_sauer.gwt.game.client.Game;
import com.allen_sauer.gwt.game.client.sprite.SpritePool;

import java.util.ArrayList;
import java.util.Iterator;

public class Engine {
  public static final AbsolutePanel playfield = new AbsolutePanel();

  private static int clientHeight;
  private static int clientWidth;
  private static EngineTimer engineTimer;
  private static final ArrayList frameListeners = new ArrayList();
  private static Game game;
  private static ArrayList newFrameListeners = new ArrayList();
  private static ArrayList removeFrameListeners = new ArrayList();
  private static ArrayList spritePools = new ArrayList();

  static {
    playfield.setSize("100%", "100%");
  }

  public static void addFrameListener(FrameListener listener) {
    newFrameListeners.add(listener);
    listener.initialize();
  }

  public static void addSpritePool(SpritePool pool) {
    spritePools.add(pool);
  }

  public static void doFrame() {
    // Avoid ConcurrentModificationException
    if (frameListeners.removeAll(removeFrameListeners)) {
      removeFrameListeners.clear();
    }
    if (frameListeners.addAll(newFrameListeners)) {
      newFrameListeners.clear();
    }

    game.doFrame();
    for (Iterator iterator = frameListeners.iterator(); iterator.hasNext();) {
      FrameListener listener = (FrameListener) iterator.next();
      listener.doFrame();
    }
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
    engineTimer.setPaused(false);
  }

  public static boolean isPaused() {
    return engineTimer.isPaused();
  }

  public static void removeFrameListener(FrameListener listener) {
    removeFrameListeners.add(listener);
    listener.deinitialize();
  }

  public static void setPaused(boolean paused) {
    engineTimer.setPaused(paused);
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
