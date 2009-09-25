/**
 * Copyright 2007 Fred Sauer
 */
package com.allen_sauer.gwt.game.batblast.client.sprite.robot;

import com.allen_sauer.gwt.game.batblast.client.sprite.resources.BatBlastRobotClientBundle;
import com.allen_sauer.gwt.game.client.Game;
import com.allen_sauer.gwt.game.client.behavior.ParatrooperBehavior;
import com.allen_sauer.gwt.game.client.sprite.BoundingBoxInfo;
import com.allen_sauer.gwt.game.client.sprite.Sprite;
import com.allen_sauer.gwt.game.client.sprite.frame.FrameInfo;
import com.allen_sauer.gwt.game.client.sprite.frame.LoopFrameInfo;

public class BaineSprite extends Sprite {
  private static final BoundingBoxInfo BOUNDING_BOX_INFO;
  private static final int FRAME_ANIMATE_INTERVAL_MILLIS = 250;
  private static final int FRAME_HEIGHT = BatBlastRobotClientBundle.INSTANCE.skylerBaine().getHeight() * 0 + 64;
  private static final int FRAME_WIDTH = BatBlastRobotClientBundle.INSTANCE.skylerBaine().getWidth() * 0 + 64;
  private static final int FRAMES_HORIZONTAL = 1;
  private static final int FRAMES_VERTICAL = 1;
  private static final String SPRITE_URL = BatBlastRobotClientBundle.INSTANCE.skylerBaine().getURL();
  static {
    BOUNDING_BOX_INFO = new BoundingBoxInfo(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
  }

  public BaineSprite(Game game) {
    super(game, BOUNDING_BOX_INFO);
    FrameInfo frameInfo = new LoopFrameInfo(this, SPRITE_URL, FRAMES_HORIZONTAL, FRAMES_VERTICAL,
        FRAME_WIDTH, FRAME_HEIGHT, FRAME_ANIMATE_INTERVAL_MILLIS);
    setFrameInfo(frameInfo);
    setBehavior(new ParatrooperBehavior(this));
  }
}
