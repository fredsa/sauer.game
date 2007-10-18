package com.allen_sauer.gwt.game.client.sprite.frame;

import com.allen_sauer.gwt.game.client.sprite.Sprite;

public class BounceFrameInfo extends FrameInfo {
  private int[] frameSequence;

  public BounceFrameInfo(Sprite sprite, String spriteUrl, int horizontalFrames, int verticalFrames, int frameWidth,
      int frameHeight, int animationInterval) {
    super(sprite, spriteUrl, horizontalFrames, verticalFrames, frameWidth, frameHeight, animationInterval);

    int baseCount = horizontalFrames * verticalFrames;
    frameSequence = new int[baseCount * 2 - 2];
    for (int i = 0; i < baseCount; i++) {
      frameSequence[i] = i;
    }
    for (int i = 1; i < baseCount - 1; i++) {
      frameSequence[frameSequence.length - i] = i;
    }

    setFrameSequence(frameSequence);
  }
}
