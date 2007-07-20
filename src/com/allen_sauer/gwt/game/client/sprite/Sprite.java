package com.allen_sauer.gwt.game.client.sprite;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Image;

import com.allen_sauer.gwt.game.client.Game;
import com.allen_sauer.gwt.game.client.behavior.Behavior;
import com.allen_sauer.gwt.game.client.engine.DoubleBuffer;
import com.allen_sauer.gwt.game.client.engine.Engine;
import com.allen_sauer.gwt.game.client.engine.FrameListener;
import com.allen_sauer.gwt.game.client.ui.util.FastDOM;

public class Sprite implements FrameListener {
  private Behavior behavior;
  private int frame;
  private int frameHeight;
  private int frames;
  private int frameWidth;
  private Game game;
  private Image[] image = new Image[2];
  private AbsolutePanel[] panel = {new AbsolutePanel(), new AbsolutePanel(),};
  private SpritePool spritePool;
  private int x;
  private int y;

  public Sprite(Game game, String url, int frames, int frameWidth, int frameHeight) {
    this.game = game;
    this.frames = frames;
    this.frameWidth = frameWidth;
    this.frameHeight = frameHeight;
    image[0] = new Image(url);
    image[1] = new Image(url);
    panel[0].add(image[0], 0, 0);
    panel[1].add(image[1], 0, 0);

    image[0].setPixelSize(frameWidth * frames, frameHeight);
    image[1].setPixelSize(frameWidth * frames, frameHeight);

    panel[0].setPixelSize(frameWidth, frameHeight);
    panel[1].setPixelSize(frameWidth, frameHeight);

    panel[0].setPixelSize(frameWidth, frameHeight);
    panel[1].setPixelSize(frameWidth, frameHeight);

    //    DOM.setStyleAttribute(panel[0].getElement(), "position", "absolute");
    //    DOM.setStyleAttribute(panel[1].getElement(), "position", "absolute");
  }

  public void deinit() {
    DoubleBuffer.buffer[0].remove(panel[0]);
    DoubleBuffer.buffer[1].remove(panel[1]);
  }

  public void doFrame() {
    if (++frame >= frames * 5) {
      frame = 0;
    }
    setFrame(frame / 5);
    FastDOM.setElementPosition(panel[DoubleBuffer.getBackBufferIndex()].getElement(), x, y);
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

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public void init() {
    Engine.addFrameListener(this);
    DoubleBuffer.buffer[0].add(panel[0], -500, -500);
    DoubleBuffer.buffer[1].add(panel[1], -500, -500);
    behavior.init();
  }

  public void removeSelf() {
    spritePool.destroy(this);
  }

  public void setBehavior(Behavior behavior) {
    this.behavior = behavior;
  }

  public void setFrame(int frame) {
    FastDOM.setElementX(image[DoubleBuffer.getBackBufferIndex()].getElement(), -frame * frameWidth);
  }

  public final void setPosition(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public void setSpritePool(SpritePool spritePool) {
    this.spritePool = spritePool;
  }
}
