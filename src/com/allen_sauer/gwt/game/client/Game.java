package com.allen_sauer.gwt.game.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.WindowResizeListener;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;

import com.allen_sauer.gwt.game.client.engine.EngineTimer;
import com.allen_sauer.gwt.game.client.engine.FrameListener;
import com.allen_sauer.gwt.game.client.engine.FrameListenerCollection;
import com.allen_sauer.gwt.game.client.engine.Playfield;
import com.allen_sauer.gwt.game.client.sprite.SpritePool;
import com.allen_sauer.gwt.game.client.sprite.player.Player;
import com.allen_sauer.gwt.game.client.ui.util.DOMUtil;
import com.allen_sauer.gwt.game.client.ui.util.Page;
import com.allen_sauer.gwt.log.client.Log;
import com.allen_sauer.gwt.voices.client.SoundController;

import java.util.ArrayList;

public abstract class Game extends Composite {
  public static final boolean DEBUG = false;

  public final AbsolutePanel background = new AbsolutePanel();
  public final Playfield playfield = new Playfield(this);

  private FrameListenerCollection collisionFrameListeners = new FrameListenerCollection();
  private EngineTimer engineTimer;
  private FrameListenerCollection frameListenerCollection = new FrameListenerCollection();
  private boolean paused = true;
  private int playfieldHeight;
  private int playfieldWidth;
  private AbsolutePanel mainPanel = new AbsolutePanel();
  private FrameListenerCollection spriteFrameListeners = new FrameListenerCollection();

  private ArrayList<SpritePool> spritePools = new ArrayList<SpritePool>();

  public Game() {
    initWidget(mainPanel);
    mainPanel.setSize("100%", "100%");
    DOM.setStyleAttribute(getElement(), "border", "10px solid green");
    background.addStyleName("game-layer game-background");
    DOM.setElementAttribute(background.getElement(), "id", "BACKGROUND");
    DOM.setElementAttribute(playfield.getElement(), "id", "PLAYFIELD");
    DOM.setElementAttribute(mainPanel.getElement(), "id", "MAINPANEL");
    DOM.setStyleAttribute(background.getElement(), "border", "10px solid blue");
    DOM.setStyleAttribute(playfield.getElement(), "border", "5px solid red");
    mainPanel.add(background, 0, 0);
    mainPanel.add(playfield, 0, 0);
    Log.debug("Game()" + GWT.getTypeName(this));
    Log.debug(" offsetWidth=" + getOffsetWidth());
    Log.debug(" rootPanel.offsetWidth=" + mainPanel.getOffsetWidth());
    Log.debug(" playfield.offsetWidth=" + playfield.getOffsetWidth());
  }

  public void addCollisionFrameListener(FrameListener frameListener) {
    collisionFrameListeners.addFrameListener(frameListener);
  }

  public void addSpriteFrameListener(FrameListenerCollection frameListener) {
    spriteFrameListeners.addFrameListener(frameListener);
  }

  public void addSpritePool(SpritePool pool) {
    spritePools.add(pool);
  }

  public int getClientHeight() {
    return playfieldHeight;
  }

  public int getClientWidth() {
    return playfieldWidth;
  }

  public FrameListenerCollection getFrameListenerCollection() {
    return frameListenerCollection;
  }

  public abstract SoundController getSoundController();

  public String info() {
    return "paused=" + isPaused() + ", frameListenerCollections=" + getFrameListenerCollection().size() + ", spritePools="
        + spritePools.size();
  }

  public boolean isPaused() {
    return paused;
  }

  public void setFrameListenerCollection(FrameListenerCollection frameListenerCollection) {
    this.frameListenerCollection = frameListenerCollection;
  }

  public void setPaused(boolean paused) {
    //    Log.debug("setPaused(" + paused + ")");
    if (this.paused != paused) {
      this.paused = paused;
      engineTimer.setPaused(paused);
    }
  }

  public abstract void updatePlayerText();

  @Override
  protected void onLoad() {
    super.onLoad();

    getFrameListenerCollection().addFrameListener(spriteFrameListeners);
    getFrameListenerCollection().addFrameListener(collisionFrameListeners);

    Window.addWindowResizeListener(new WindowResizeListener() {
      public void onWindowResized(int width, int height) {
        clientResized();
      }
    });
    clientResized();

    engineTimer = new EngineTimer(this);

    // add hooks, force page focus and trigger game start
    Page.forceStaticInit();

    Log.debug("init() " + GWT.getTypeName(this));
    Log.debug(" offset: rootPanel=" + mainPanel.getOffsetWidth() + " " + mainPanel.getOffsetHeight());
    Log.debug(" offset: playfield=" + playfield.getOffsetWidth() + " " + playfield.getOffsetHeight());
    Log.debug(" offset: game=" + getOffsetWidth() + " " + getOffsetHeight());

    Log.debug(" client: rootPanel=" + DOMUtil.getClientWidth(mainPanel.getElement()) + " " + DOMUtil.getClientHeight(mainPanel.getElement()));
    Log.debug(" client: playfield=" + DOMUtil.getClientWidth(playfield.getElement()) + " " + DOMUtil.getClientHeight(playfield.getElement()));
    Log.debug(" client: game=" + DOMUtil.getClientWidth(getElement()) + " " + DOMUtil.getClientHeight(getElement()));
  }

  protected abstract void playerDied(Player player);

  private void clientResized() {
    playfieldWidth = playfield.getOffsetWidth();
    playfieldHeight = playfield.getOffsetHeight();
    //    assert playfieldWidth != 0;
    //    assert playfieldHeight != 0;
  }
}
