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
  private static final int HIDDEN_FRAME = 0;
  private final int animateFrameSequence;
  private Behavior behavior;
  private int currentFrame = HIDDEN_FRAME;
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

    initWidget(panel);
    image = new Image(spriteUrl);
    panel.add(image, 0, 0);

    image.setPixelSize(frameWidth * frames, frameHeight);
    setPixelSize(frameWidth, frameHeight);
    setPixelSize(frameWidth, frameHeight);
    Engine.playfield.add(this, -500, -500);
    initFrameData();
  }

  public void doFirstFrame() {
    setFrame(1);
    Engine.addFrameListener(behavior);
  }

  public void doFrame() {
    FastDOM.setElementPosition(getElement(), x, y);
    if (frameAnimateInterval > 0 && ++frameCounter == frameAnimateInterval) {
      frameCounter = 0;
      setFrame(currentFrame == frameOffset.length - 1 ? 1 : currentFrame + 1);
    }
  }

  public void doLastFrame() {
    setFrame(HIDDEN_FRAME);
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
    frameOffset = new int[animateFrameSequence == ANIMATE_SEQUENCE_BOUNCE ? frames * 2 - 1 : frames + 1];
    frameOffset[HIDDEN_FRAME] = frameWidth;
    for (int i = 0; i < frames; i++) {
      frameOffset[i + 1] = -i * frameWidth;
    }
    if (animateFrameSequence == ANIMATE_SEQUENCE_BOUNCE) {
      for (int i = 2; i < frames; i++) {
        frameOffset[frameOffset.length - i + 1] = frameOffset[i];
      }
    }
  }
}
