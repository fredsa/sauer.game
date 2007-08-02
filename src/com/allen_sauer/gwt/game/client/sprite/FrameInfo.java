package com.allen_sauer.gwt.game.client.sprite;

public class FrameInfo {
  public static final int ANIMATION_STYLE_BOUNCE = 2;
  public static final int ANIMATION_STYLE_SEQUENTIAL = 1;

  public final int animationInterval;
  public final int animationStyle;
  public final int frameHeight;
  public final int frameWidth;
  public final int[] frameXOffset;
  public final int[] frameYOffset;
  public final int horizontalFrames;
  public final String spriteUrl;
  public final int totalFrameCount;
  public final int verticalFrames;
  public final int virtualFrameCount;

  public FrameInfo(String spriteUrl, int horizontalFrames, int verticalFrames, int frameHeight,
      int frameWidth, int animationInterval, int animationStyle) {
    assert horizontalFrames >= 1;
    assert verticalFrames >= 1;
    assert frameWidth >= 1;
    assert frameHeight >= 1;
    assert animationInterval >= 1;
    assert animationStyle == ANIMATION_STYLE_BOUNCE || animationStyle == ANIMATION_STYLE_SEQUENTIAL;

    this.spriteUrl = spriteUrl;
    this.horizontalFrames = horizontalFrames;
    this.verticalFrames = verticalFrames;
    this.frameWidth = frameWidth;
    this.frameHeight = frameHeight;
    this.animationInterval = animationInterval;
    this.animationStyle = animationStyle;

    totalFrameCount = horizontalFrames * verticalFrames;

    virtualFrameCount = animationStyle == ANIMATION_STYLE_BOUNCE ? totalFrameCount * 2 - 2
        : totalFrameCount;
    frameXOffset = new int[virtualFrameCount];
    frameYOffset = new int[virtualFrameCount];
    for (int j = 0; j < verticalFrames; j++) {
      for (int i = 0; i < horizontalFrames; i++) {
        int virtualFrame = j * horizontalFrames + i;
        frameXOffset[virtualFrame] = -i * frameWidth;
        frameYOffset[virtualFrame] = -j * frameHeight;
      }
    }
    if (animationStyle == ANIMATION_STYLE_BOUNCE) {
      for (int virtualFrame = 1; virtualFrame < totalFrameCount - 1; virtualFrame++) {
        int frame = virtualFrameCount - virtualFrame;
        frameXOffset[frame] = frameXOffset[virtualFrame];
        frameYOffset[frame] = frameYOffset[virtualFrame];
      }
    }
  }
}
