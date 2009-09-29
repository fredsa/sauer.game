/**
 * Copyright 2007 Fred Sauer
 */
package com.allen_sauer.gwt.game.batblast.client.sprite.robot;

import com.allen_sauer.gwt.game.batblast.client.sprite.resources.BatBlastRobotClientBundle;
import com.allen_sauer.gwt.game.client.Game;
import com.allen_sauer.gwt.game.client.behavior.SpiralBehavior;
import com.allen_sauer.gwt.game.client.sprite.BoundingBoxInfo;
import com.allen_sauer.gwt.game.client.sprite.Sprite;
import com.allen_sauer.gwt.game.client.sprite.frame.FrameInfo;
import com.allen_sauer.gwt.game.client.sprite.frame.LoopFrameInfo;

public class JokerSprite extends Sprite {
  private static final BoundingBoxInfo BOUNDING_BOX_INFO;
  private static final int FRAME_ANIMATE_INTERVAL_MILLIS = 250;
  private static final int FRAME_HEIGHT = BatBlastRobotClientBundle.INSTANCE.joker().getHeight() * 0 + 64;
  private static final int FRAME_WIDTH = BatBlastRobotClientBundle.INSTANCE.joker().getWidth() * 0 + 64;
  private static final int FRAMES_HORIZONTAL = 1;
  private static final int FRAMES_VERTICAL = 1;
  private static final String SPRITE_URL = BatBlastRobotClientBundle.INSTANCE.joker().getURL();
  static {
    BOUNDING_BOX_INFO = new BoundingBoxInfo(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
  }

  public JokerSprite(Game game) {
    super(game, BOUNDING_BOX_INFO);
    FrameInfo frameInfo = new LoopFrameInfo(this, SPRITE_URL, FRAMES_HORIZONTAL, FRAMES_VERTICAL,
        FRAME_WIDTH, FRAME_HEIGHT, FRAME_ANIMATE_INTERVAL_MILLIS);
    setFrameInfo(frameInfo);
    setBehavior(new SpiralBehavior(this));
  }
}
