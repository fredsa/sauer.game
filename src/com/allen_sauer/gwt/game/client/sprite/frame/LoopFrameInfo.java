package com.allen_sauer.gwt.game.client.sprite.frame;

import com.allen_sauer.gwt.game.client.sprite.Sprite;

public class LoopFrameInfo extends FrameInfo {
  private int[] frameSequence;

  public LoopFrameInfo(Sprite sprite, String spriteUrl, int horizontalFrames,
      int verticalFrames, int frameWidth, int frameHeight, int animationInterval) {
    super(sprite, spriteUrl, horizontalFrames, verticalFrames, frameWidth,
        frameHeight, animationInterval);

    frameSequence = new int[horizontalFrames * verticalFrames];
    for (int i = 0; i < frameSequence.length; i++) {
      frameSequence[i] = i;
    }
    setFrameSequence(frameSequence);
  }
}
