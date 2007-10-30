package com.allen_sauer.gwt.game.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.WindowResizeListener;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;

import com.allen_sauer.gwt.game.client.sprite.SpritePool;
import com.allen_sauer.gwt.game.client.sprite.player.Player;
import com.allen_sauer.gwt.game.client.ui.InputPanel;
import com.allen_sauer.gwt.game.client.ui.Playfield;
import com.allen_sauer.gwt.game.client.ui.util.Page;
import com.allen_sauer.gwt.voices.client.SoundController;

import java.util.ArrayList;

public abstract class Game extends Composite {
  public static final boolean DEBUG = false;

  public final AbsolutePanel background = new AbsolutePanel();
  public final InputPanel input = new InputPanel(this);
  public final AbsolutePanel overlay = new AbsolutePanel();
  public final Playfield playfield = new Playfield(this);

  private FrameListenerCollection collisionFrameListeners = new FrameListenerCollection();
  private GameTimer engineTimer;
  private FrameListenerCollection frameListenerCollection = new FrameListenerCollection();
  private AbsolutePanel mainPanel = new AbsolutePanel();
  private boolean paused = true;
  private int playfieldHeight;
  private int playfieldWidth;
  private FrameListenerCollection spriteFrameListeners = new FrameListenerCollection();
  private ArrayList<SpritePool> spritePools = new ArrayList<SpritePool>();

  public Game() {
    initWidget(mainPanel);
    addStyleName("game");
    addGameLayers();
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

  public void setFocus(boolean focused) {
    input.setFocus(focused);
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

  @Override
  public String toString() {
    return GWT.getTypeName(this);
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

    engineTimer = new GameTimer(this);

    // add hooks, force page focus and trigger game start
    Page.forceStaticInit();
  }

  protected abstract void playerDied(Player player);

  /**
   * Add layers from the bottom up.
   */
  private void addGameLayers() {
    mainPanel.setSize("100%", "100%");
    
    background.setSize("100%", "100%");
    background.addStyleName("game-layer-background");
    mainPanel.add(background, 0, 0);

    playfield.setSize("100%", "100%");
    playfield.addStyleName("game-layer-playfield");
    mainPanel.add(playfield, 0, 0);

    overlay.setSize("100%", "100%");
    overlay.addStyleName("game-layer-overlay");
    mainPanel.add(overlay, 0, 0);

    input.setSize("100%", "100%");
    input.addStyleName("game-layer-inputPanel");
    mainPanel.add(input, 0, 0);
  }

  private void clientResized() {
    playfieldWidth = playfield.getOffsetWidth();
    playfieldHeight = playfield.getOffsetHeight();
    assert playfieldWidth != 0;
    assert playfieldHeight != 0;
  }
}
