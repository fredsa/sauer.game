package com.allen_sauer.gwt.game.client;

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
import com.allen_sauer.gwt.game.client.ui.util.Page;
import com.allen_sauer.gwt.voices.client.SoundController;

import java.util.ArrayList;

public abstract class Game extends Composite {
  public static final boolean DEBUG = false;

  public final AbsolutePanel background = new AbsolutePanel();
  public final Playfield playfield = new Playfield(this);

  private FrameListenerCollection collisionFrameListeners = new FrameListenerCollection();
  private EngineTimer engineTimer;
  private FrameListenerCollection frameListenerCollection = new FrameListenerCollection();
  private AbsolutePanel mainPanel = new AbsolutePanel();
  private boolean paused = true;
  private int playfieldHeight;
  private int playfieldWidth;
  private FrameListenerCollection spriteFrameListeners = new FrameListenerCollection();

  private ArrayList<SpritePool> spritePools = new ArrayList<SpritePool>();

  public Game() {
    initWidget(mainPanel);
    mainPanel.setSize("100%", "100%");
    background.addStyleName("game-layer game-background");
    mainPanel.add(background, 0, 0);
    mainPanel.add(playfield, 0, 0);
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
    playfield.setFocus(focused);
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
  }

  protected abstract void playerDied(Player player);

  private void clientResized() {
    playfieldWidth = playfield.getOffsetWidth();
    playfieldHeight = playfield.getOffsetHeight();
    assert playfieldWidth != 0;
    assert playfieldHeight != 0;
  }
}
