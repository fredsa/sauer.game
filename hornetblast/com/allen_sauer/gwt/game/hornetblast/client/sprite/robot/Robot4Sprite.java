/**
 * Copyright 2009 Fred Sauer
 */
package com.allen_sauer.gwt.game.hornetblast.client.sprite.robot;

import com.allen_sauer.gwt.game.client.Game;
import com.allen_sauer.gwt.game.client.behavior.SpiralBehavior;
import com.allen_sauer.gwt.game.client.sprite.BoundingBoxInfo;
import com.allen_sauer.gwt.game.client.sprite.Sprite;
import com.allen_sauer.gwt.game.client.sprite.frame.FrameInfo;
import com.allen_sauer.gwt.game.client.sprite.frame.PalindromeFrameInfo;

public class Robot4Sprite extends Sprite {
  private static final BoundingBoxInfo BOUNDING_BOX_INFO;
  private static final int FRAME_ANIMATE_INTERVAL_MILLIS = 250;
  private static final int FRAME_HEIGHT = 80;
  private static int[] FRAME_SEQUENCE = {0, 1, 3, 2};
  private static final int FRAME_WIDTH = 80;
  private static final int FRAMES_HORIZONTAL = 4;
  private static final int FRAMES_VERTICAL = 1;
  private static final String SPRITE_URL = "hornetblast-images/archer-zurg-attacker-80x80-4x1frames.gif";

  static {
    BOUNDING_BOX_INFO = new BoundingBoxInfo(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
  }

  public Robot4Sprite(Game game) {
    super(game, BOUNDING_BOX_INFO);
    FrameInfo frameInfo = new PalindromeFrameInfo(this, SPRITE_URL, FRAMES_HORIZONTAL,
        FRAMES_VERTICAL, FRAME_WIDTH, FRAME_HEIGHT, FRAME_ANIMATE_INTERVAL_MILLIS);
    frameInfo.setFrameSequence(FRAME_SEQUENCE);
    setFrameInfo(frameInfo);
    setBehavior(new SpiralBehavior(this));
  }
}
