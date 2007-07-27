package com.allen_sauer.gwt.game.client.sprite;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;

import com.allen_sauer.gwt.game.client.Game;
import com.allen_sauer.gwt.game.client.behavior.Behavior;
import com.allen_sauer.gwt.game.client.engine.Engine;
import com.allen_sauer.gwt.game.client.engine.FrameListener;
import com.allen_sauer.gwt.game.client.ui.util.FastDOM;

public class Sprite extends Composite implements FrameListener {
  public static final int ANIMATE_SEQUENCE_BOUNCE = 2;
  public static final int ANIMATE_SEQUENCE_SEQUENTIAL = 1;
  private final int animateFrameSequence;
  private Behavior behavior;
  private int currentFrame;
  private final int frameAnimateInterval;
  private int frameCounter;
  private int frameHeight;
  private int[] frameOffset;
  private int frames;
  private int frameWidth;
  private Game game;
  private Image image;
  private AbsolutePanel panel = new AbsolutePanel();
  private int poolIndex;
  private SpritePool spritePool;
  private int x;
  private int y;

  public Sprite(Game game, String spriteUrl, int frames, int frameWidth, int frameHeight) {
    this(game, spriteUrl, frames, frameWidth, frameHeight, 1, ANIMATE_SEQUENCE_SEQUENTIAL);
  }

  public Sprite(Game game, String spriteUrl, int frames, int frameWidth, int frameHeight, int frameAnimateInterval,
      int animateFrameSequence) {
    assert frames >= 1;
    assert frameWidth >= 1;
    assert frameHeight >= 1;
    assert frameAnimateInterval >= 1;

    this.game = game;
    this.frames = frames;
    this.frameWidth = frameWidth;
    this.frameHeight = frameHeight;
    this.frameAnimateInterval = frameAnimateInterval;
    this.animateFrameSequence = animateFrameSequence;

    initFrameData();

    initWidget(panel);
    image = new Image(spriteUrl);
    panel.add(image, frameOffset[currentFrame], 0);

    image.setPixelSize(frameWidth * frames, frameHeight);
    setPixelSize(frameWidth, frameHeight);
    setPixelSize(frameWidth, frameHeight);
    Engine.playfield.add(this, -500, -500);
  }

  public void doFirstFrame() {
    Engine.addFrameListener(behavior);
  }

  public void doFrame() {
    FastDOM.setElementPosition(getElement(), x, y);
    if (frameAnimateInterval > 0 && ++frameCounter == frameAnimateInterval) {
      frameCounter = 0;
      setFrame((currentFrame + 1) % frameOffset.length);
    }
  }

  public void doLastFrame() {
    FastDOM.setElementPosition(getElement(), -500, -500);
    Engine.removeFrameListener(behavior);
  }

  public int getBottom() {
    return y + frameHeight;
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

  public int getRight() {
    return x + frameWidth;
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

  public void removeSelf() {
    spritePool.destroy(this);
  }

  public void setBehavior(Behavior behavior) {
    this.behavior = behavior;
  }

  public void setFrame(int frame) {
    FastDOM.setElementX(image.getElement(), frameOffset[frame]);
    currentFrame = frame;
  }

  public void setPoolIndex(int poolIndex) {
    this.poolIndex = poolIndex;
  }

  public void setSpritePool(SpritePool spritePool) {
    this.spritePool = spritePool;
  }

  public void setX(int x) {
    this.x = x;
  }

  public final void setXY(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public void setY(int y) {
    this.y = y;
  }

  private void initFrameData() {
    frameOffset = new int[animateFrameSequence == ANIMATE_SEQUENCE_BOUNCE ? frames * 2 - 2 : frames];
    for (int i = 0; i < frames; i++) {
      frameOffset[i] = -i * frameWidth;
    }
    if (animateFrameSequence == ANIMATE_SEQUENCE_BOUNCE) {
      for (int i = 1; i < frames - 1; i++) {
        frameOffset[frameOffset.length - i] = frameOffset[i];
      }
    }
  }
}
