package com.allen_sauer.gwt.game.client.sprite;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Image;

import com.allen_sauer.gwt.game.client.Game;
import com.allen_sauer.gwt.game.client.behavior.Behavior;
import com.allen_sauer.gwt.game.client.engine.Engine;
import com.allen_sauer.gwt.game.client.engine.FrameListener;
import com.allen_sauer.gwt.game.client.ui.util.FastDOM;

public class Sprite implements FrameListener {
  private static final int HIDDEN_FRAME = -1;
  private Behavior behavior;
  private int frame = HIDDEN_FRAME;
  private int frameHeight;
  private int frames;
  private int frameWidth;
  private Game game;
  private Image image;
  private AbsolutePanel panel = new AbsolutePanel();
  private int poolIndex;
  private SpritePool spritePool;
  private int x;
  private int y;

  public Sprite(Game game, String url, int frames, int frameWidth, int frameHeight) {
    this.game = game;
    this.frames = frames;
    this.frameWidth = frameWidth;
    this.frameHeight = frameHeight;
    image = new Image(url);
    panel.add(image, 0, 0);

    image.setPixelSize(frameWidth * frames, frameHeight);
    panel.setPixelSize(frameWidth, frameHeight);
    panel.setPixelSize(frameWidth, frameHeight);
    Engine.playfield.add(panel, -500, -500);
  }

  public void deinitialize() {
    setFrame(HIDDEN_FRAME);
    Engine.removeFrameListener(behavior);
  }

  public void doFrame() {
    FastDOM.setElementPosition(panel.getElement(), x, y);
    if (++frame >= frames * 5) {
      frame = 0;
    }
    setFrame(frame / 5);
  }

  public int getFrameHeight() {
    return frameHeight;
  }

  public int getFrameWidth() {
    return frameWidth;
  }

  public Game getGame() {
    return game;
  }

  public int getPoolIndex() {
    return poolIndex;
  }

  public SpritePool getSpritePool() {
    return spritePool;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public void initialize() {
    Engine.addFrameListener(behavior);
  }

  public void removeSelf() {
    spritePool.destroy(this);
  }

  public void setBehavior(Behavior behavior) {
    this.behavior = behavior;
  }

  public void setFrame(int frame) {
    FastDOM.setElementX(image.getElement(), -frame * frameWidth);
  }

  public void setPoolIndex(int poolIndex) {
    this.poolIndex = poolIndex;
  }

  public final void setPosition(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public void setSpritePool(SpritePool spritePool) {
    this.spritePool = spritePool;
  }
}
