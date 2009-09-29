/**
 * Copyright 2009 Fred Sauer
 */
package com.allen_sauer.gwt.game.client.sprite.frame;

import com.allen_sauer.gwt.game.client.sprite.Sprite;

public class OneTimeFrameInfo extends LoopFrameInfo {
  private int framesSeen;

  public OneTimeFrameInfo(Sprite sprite, String spriteUrl, int horizontalFrames,
      int verticalFrames, int frameWidth, int frameHeight, int animationInterval) {
    super(sprite, spriteUrl, horizontalFrames, verticalFrames, frameWidth, frameHeight,
        animationInterval);
  }

  @Override
  public void doFirstFrame() {
    super.doFirstFrame();
    framesSeen = 0;
  }

  @Override
  public boolean doFrame(double millis) {
    boolean again = super.doFrame(millis);
    again &= framesSeen++ == 0 || getCurrentFrame() > 0;
    return again;
  }
}
