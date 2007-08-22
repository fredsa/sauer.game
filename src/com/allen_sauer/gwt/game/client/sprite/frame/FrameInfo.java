package com.allen_sauer.gwt.game.client.sprite.frame;

import com.allen_sauer.gwt.game.client.engine.FrameListener;
import com.allen_sauer.gwt.game.client.sprite.Sprite;

public class FrameInfo implements FrameListener {
  public final int animationInterval;
  public final int frameHeight;
  public final int frameWidth;
  public int[] frameXOffset;
  public int[] frameYOffset;
  public final int horizontalFrames;
  public final String spriteUrl;
  public final int verticalFrames;
  private int currentFrame;
  private int frameCounter;
  private int[] frameSequence;
  private final Sprite sprite;

  public FrameInfo(Sprite sprite, String spriteUrl, int horizontalFrames, int verticalFrames,
      int frameWidth, int frameHeight, int animationInterval) {
    this.sprite = sprite;
    this.spriteUrl = spriteUrl;
    this.horizontalFrames = horizontalFrames;
    this.verticalFrames = verticalFrames;
    this.frameWidth = frameWidth;
    this.frameHeight = frameHeight;
    this.animationInterval = animationInterval;

    assert horizontalFrames >= 1;
    assert verticalFrames >= 1;
    assert frameWidth >= 1;
    assert frameHeight >= 1;
    assert animationInterval >= 1;

    int frameCount = horizontalFrames * verticalFrames;
    frameXOffset = new int[frameCount];
    frameYOffset = new int[frameCount];
    for (int x = 0; x < horizontalFrames; x++) {
      for (int y = 0; y < verticalFrames; y++) {
        int frame = y * horizontalFrames + x;
        frameXOffset[frame] = -x * frameWidth;
        frameYOffset[frame] = -y * frameHeight;
      }
    }
  }

  public void doFirstFrame() {
    setCurrentFrame(0);
  }

  public boolean doFrame() {
    if (animationInterval > 0 && ++frameCounter == animationInterval) {
      frameCounter = 0;
      setCurrentFrame((getCurrentFrame() + 1) % frameSequence.length);
    }
    return true;
  }

  public void doLastFrame() {
  }

  public int getCurrentFrame() {
    return currentFrame;
  }

  public int[] getFrameSequence() {
    return frameSequence;
  }

  public void setCurrentFrame(int currentFrame) {
    this.currentFrame = currentFrame;
    sprite.setImagePosition(frameXOffset[frameSequence[currentFrame]],
        frameYOffset[frameSequence[currentFrame]]);
  }

  public void setFrameSequence(int[] frameSequence) {
    this.frameSequence = frameSequence;
  }
}
