package com.allen_sauer.gwt.game.client.engine;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.WindowResizeListener;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.impl.FocusImpl;

import com.allen_sauer.gwt.game.client.Game;
import com.allen_sauer.gwt.game.client.sprite.SpritePool;

import java.util.ArrayList;
import java.util.Iterator;

public class Engine {
  private static int clientHeight;
  private static int clientWidth;
  private static EngineTimer engineTimer;
  private static final ArrayList frameListeners = new ArrayList();
  private static Game game;
  private static ArrayList newFrameListeners = new ArrayList();
  private static ArrayList spritePools = new ArrayList();

  public static void addFrameListener(FrameListener listener) {
    newFrameListeners.add(listener);
  }

  public static void addSpritePool(SpritePool pool) {
    spritePools.add(pool);
  }

  public static void doFrame() {
    // Avoid ConcurrentModificationException
    if (!newFrameListeners.isEmpty()) {
      frameListeners.addAll(newFrameListeners);
      newFrameListeners = new ArrayList();
    }

    DoubleBuffer.swap();

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

  public static void init(Game game) {
    Engine.game = game;
    setClientSize(Window.getClientWidth(), Window.getClientHeight());
    game.init();

    Window.addWindowResizeListener(new WindowResizeListener() {
      public void onWindowResized(int width, int height) {
        clientResized(width, height);
      }
    });

    engineTimer = new EngineTimer();
    engineTimer.setPaused(false);
    EnginePauseButton enginePauseButton = new EnginePauseButton(engineTimer);
    RootPanel.get().add(enginePauseButton, 10, 40);

    FocusImpl.getFocusImplForPanel().focus(RootPanel.getBodyElement());
    enginePauseButton.setFocus(true);
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
