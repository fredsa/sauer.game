/**
 * Copyright 2007 Fred Sauer
 */
package com.allen_sauer.gwt.game.client.sprite.frame;

import com.allen_sauer.gwt.game.client.sprite.Sprite;

public class OneTimeFrameInfo extends LoopFrameInfo {
  public OneTimeFrameInfo(Sprite sprite, String spriteUrl, int horizontalFrames,
      int verticalFrames, int frameWidth, int frameHeight, int animationInterval) {
    super(sprite, spriteUrl, horizontalFrames, verticalFrames, frameWidth, frameHeight,
        animationInterval);
  }

  @Override
  public boolean doFrame(double millis) {
    boolean again = super.doFrame(millis);
    again &= getCurrentFrame() < getFrameSequence().length - 1;
    return again;
  }
}
