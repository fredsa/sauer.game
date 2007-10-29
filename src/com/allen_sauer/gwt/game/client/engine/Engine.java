package com.allen_sauer.gwt.game.client.engine;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.WindowResizeListener;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.RootPanel;

import com.allen_sauer.gwt.game.client.Game;
import com.allen_sauer.gwt.game.client.sprite.SpritePool;
import com.allen_sauer.gwt.game.client.ui.util.DOMUtil;
import com.allen_sauer.gwt.game.client.ui.util.Page;

import java.util.ArrayList;

public final class Engine {
  public static final AbsolutePanel background = new AbsolutePanel();
  public static final boolean DEBUG = false;
  public static final Playfield playfield = new Playfield();

  static FrameListenerCollection frameListenerCollection = new FrameListenerCollection();

  private static int rootPanelHeight;
  private static int rootPanelWidth;
  private static FrameListenerCollection collisionFrameListeners = new FrameListenerCollection();
  private static EngineTimer engineTimer;
  private static Game game;
  private static boolean paused = true;
  private static FrameListenerCollection spriteFrameListeners = new FrameListenerCollection();
  private static ArrayList<SpritePool> spritePools = new ArrayList<SpritePool>();

  static {
    background.addStyleName("game-layer game-background");
    DOM.setElementAttribute(background.getElement(), "id", "BACKGROUND");
    DOM.setElementAttribute(playfield.getElement(), "id", "PLAYFIELD");
    DOM.setStyleAttribute(background.getElement(), "border", "10px solid blue");
    DOM.setStyleAttribute(playfield.getElement(), "border", "5px solid red");
  }

  public static void addCollisionFrameListener(FrameListener frameListener) {
    collisionFrameListeners.addFrameListener(frameListener);
  }

  public static void addSpriteFrameListener(FrameListenerCollection frameListener) {
    spriteFrameListeners.addFrameListener(frameListener);
  }

  public static void addSpritePool(SpritePool pool) {
    spritePools.add(pool);
  }

  public static int getClientHeight() {
    return rootPanelHeight;
  }

  public static int getClientWidth() {
    return rootPanelWidth;
  }

  public static String info() {
    return "frameListenerCollections=" + frameListenerCollection.size() + ", spritePools=" + spritePools.size();
  }

  public static void init(Game game, RootPanel rootPanel) {
    Engine.game = game;

    DOM.setInnerHTML(rootPanel.getElement(), "");

    frameListenerCollection.addFrameListener(spriteFrameListeners);
    frameListenerCollection.addFrameListener(collisionFrameListeners);

    setRootPanelSize(DOMUtil.getClientWidth(rootPanel.getElement()), DOMUtil.getClientHeight(rootPanel.getElement()));
    rootPanel.add(background, 0, 0);
    rootPanel.add(playfield, 0, 0);
    game.init();

    Window.addWindowResizeListener(new WindowResizeListener() {
      public void onWindowResized(int width, int height) {
        clientResized(width, height);
      }
    });

    engineTimer = new EngineTimer();

    playfield.setFocus(true);

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
    setRootPanelSize(clientWidth, clientHeight);
    game.clientResized(clientWidth, clientHeight);
  }

  private static void setRootPanelSize(int rootPanelWidth, int rootPanelHeight) {
    assert rootPanelWidth != 0;
    assert rootPanelHeight != 0;
    Engine.rootPanelWidth = rootPanelWidth;
    Engine.rootPanelHeight = rootPanelHeight;
  }

  private Engine() {
  }
}
