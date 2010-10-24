/**
 * Copyright 2009 Fred Sauer
 */
package com.allen_sauer.gwt.game.client;

import com.allen_sauer.gwt.game.client.sprite.SpritePool;
import com.allen_sauer.gwt.game.client.sprite.player.Player;
import com.allen_sauer.gwt.game.client.ui.InputPanel;
import com.allen_sauer.gwt.game.client.ui.Playfield;
import com.allen_sauer.gwt.game.client.ui.util.Page;
import com.allen_sauer.gwt.voices.client.SoundController;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;

import java.util.ArrayList;

public abstract class Game extends Composite {
  public enum State {
    STATE_GAME_OVER, STATE_PAUSED_BY_USER, STATE_PLAYING, STATE_SUSPENDED, STATE_UNKNOW,
  }

  public static final boolean DEBUG = false;

  public final AbsolutePanel background = new AbsolutePanel();
  public final InputPanel input = new InputPanel(this);

  /**
   * Must be a FocusPanel for proper event capture in IE.
   */
  public final FocusPanel overlay = new FocusPanel();
  private FrameListenerCollection collisionFrameListeners = new FrameListenerCollection();

  private GameTimer engineTimer;
  private FrameListenerCollection gameOverFrameListenerCollection = new FrameListenerCollection();
  private AbsolutePanel mainPanel = new AbsolutePanel();
  private final Playfield playfield = new Playfield(this);
  private int playfieldHeight;
  private int playfieldWidth;

  private FrameListenerCollection playingFrameListenerCollection = new FrameListenerCollection();

  private FrameListenerCollection spriteFrameListeners = new FrameListenerCollection();
  private ArrayList<SpritePool> spritePools = new ArrayList<SpritePool>();

  private State state = State.STATE_UNKNOW;

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

  public FrameListenerCollection getGameOverFrameListenerCollection() {
    return gameOverFrameListenerCollection;
  }

  public Playfield getPlayfield() {
    return playfield;
  }

  public int getPlayfieldHeight() {
    return playfieldHeight;
  }

  public int getPlayfieldWidth() {
    return playfieldWidth;
  }

  public FrameListenerCollection getPlayingFrameListenerCollection() {
    return playingFrameListenerCollection;
  }

  public abstract SoundController getSoundController();

  public State getState() {
    return state;
  }

  public void setFocus(boolean focused) {
    input.setFocus(focused);
  }

  public void setFrameListenerCollection(FrameListenerCollection frameListenerCollection) {
    playingFrameListenerCollection = frameListenerCollection;
  }

  public void setPlayfieldHeight(int playfieldHeight) {
    this.playfieldHeight = playfieldHeight;
  }

  public void setPlayfieldWidth(int playfieldWidth) {
    this.playfieldWidth = playfieldWidth;
  }

  public State setState(State state) {
    State oldState = this.state;
    //    Log.debug("setPaused(" + paused + ")");
    if (this.state != state) {
      this.state = state;
      engineTimer.setPaused(state == State.STATE_PAUSED_BY_USER || state == State.STATE_SUSPENDED);
    }
    return oldState;
  }

  @Override
  public String toString() {
    return getClass().getName();
  }

  public abstract void updatePlayerText();

  @Override
  protected void onLoad() {
    super.onLoad();

    getPlayingFrameListenerCollection().addFrameListener(spriteFrameListeners);
    getPlayingFrameListenerCollection().addFrameListener(collisionFrameListeners);

    Window.addResizeHandler(new ResizeHandler() {
      public void onResize(ResizeEvent event) {
        clientResized();
      }
    });
    clientResized();

    engineTimer = new GameTimer(this);

    // add hooks, force page focus and trigger game start
    Page.forceStaticInit();

    setState(State.STATE_GAME_OVER);
  }

  protected abstract void playerDied(Player player);

  FrameListenerCollection getCurrentStateFrameListenerCollection() {
    return state == State.STATE_GAME_OVER ? gameOverFrameListenerCollection
        : playingFrameListenerCollection;
  }

  /**
   * Add layers from the bottom up.
   */
  private void addGameLayers() {
    mainPanel.setSize("100%", "100%");

    background.setSize("100%", "100%");
    background.addStyleName("game-layer-background");
    mainPanel.add(background, 0, 0);

    getPlayfield().setSize("100%", "100%");
    getPlayfield().addStyleName("game-layer-playfield");
    mainPanel.add(getPlayfield(), 0, 0);

    overlay.setSize("100%", "100%");
    overlay.addStyleName("game-layer-overlay");
    mainPanel.add(overlay, 0, 0);

    input.setSize("100%", "100%");
    input.addStyleName("game-layer-inputPanel");
    mainPanel.add(input, 0, 0);
  }

  private void clientResized() {
    setPlayfieldWidth(getPlayfield().getOffsetWidth());
    setPlayfieldHeight(getPlayfield().getParent().getParent().getOffsetHeight());
    assert getPlayfieldWidth() > 0;
    assert getPlayfieldHeight() > 0;
  }
}
