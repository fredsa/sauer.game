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
  private int frameWidth;
  private int[] frameXOffset;
  private int[] frameYOffset;
  private Game game;
  private int horizontalFrames;
  private Image image;
  private AbsolutePanel panel = new AbsolutePanel();
  private int poolIndex;
  private SpritePool spritePool;
  private int totalFrameCount;
  private final int verticalFrames;
  private int virtualFrameCount;
  private int x;
  private int y;

  public Sprite(Game game, String spriteUrl, int horizontalFrames, int verticalFrames, int frameWidth, int frameHeight) {
    this(game, spriteUrl, horizontalFrames, verticalFrames, frameWidth, frameHeight, 1, ANIMATE_SEQUENCE_SEQUENTIAL);
  }

  public Sprite(Game game, String spriteUrl, int horizontalFrames, int verticalFrames, int frameWidth, int frameHeight,
      int frameAnimateInterval, int animateFrameSequence) {
    assert horizontalFrames >= 0;
    assert verticalFrames >= 0;
    assert horizontalFrames + verticalFrames >= 1;
    assert frameWidth >= 1;
    assert frameHeight >= 1;
    assert frameAnimateInterval >= 1;

    this.game = game;
    this.horizontalFrames = horizontalFrames;
    this.verticalFrames = verticalFrames;
    totalFrameCount = horizontalFrames * verticalFrames;
    this.frameWidth = frameWidth;
    this.frameHeight = frameHeight;
    this.frameAnimateInterval = frameAnimateInterval;
    this.animateFrameSequence = animateFrameSequence;

    initFrameData();

    initWidget(panel);
    image = new Image(spriteUrl);
    panel.add(image, frameXOffset[currentFrame], frameYOffset[currentFrame]);

    image.setPixelSize(frameWidth * horizontalFrames, frameHeight * verticalFrames);
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
      setFrame((currentFrame + 1) % virtualFrameCount);
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
    FastDOM.setElementPosition(image.getElement(), frameXOffset[frame], frameYOffset[frame]);
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
    virtualFrameCount = animateFrameSequence == ANIMATE_SEQUENCE_BOUNCE ? totalFrameCount * 2 - 2 : totalFrameCount;
    frameXOffset = new int[virtualFrameCount];
    frameYOffset = new int[virtualFrameCount];
    for (int j = 0; j < verticalFrames; j++) {
      for (int i = 0; i < horizontalFrames; i++) {
        int virtualFrame = j * horizontalFrames + i;
        frameXOffset[virtualFrame] = -i * frameWidth;
        frameYOffset[virtualFrame] = -j * frameHeight;
      }
    }
    if (animateFrameSequence == ANIMATE_SEQUENCE_BOUNCE) {
      for (int virtualFrame = 1; virtualFrame < totalFrameCount - 1; virtualFrame++) {
        int frame = virtualFrameCount - virtualFrame;
        frameXOffset[frame] = frameXOffset[virtualFrame];
        frameYOffset[frame] = frameYOffset[virtualFrame];
      }
    }
  }
}
