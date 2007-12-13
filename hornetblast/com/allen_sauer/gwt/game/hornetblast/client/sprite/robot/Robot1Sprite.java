/**
 * Copyright 2007 Fred Sauer
 */
package com.allen_sauer.gwt.game.hornetblast.client.sprite.robot;

import com.allen_sauer.gwt.game.client.Game;
import com.allen_sauer.gwt.game.client.behavior.ParatrooperBehavior;
import com.allen_sauer.gwt.game.client.sprite.BoundingBoxInfo;
import com.allen_sauer.gwt.game.client.sprite.Sprite;
import com.allen_sauer.gwt.game.client.sprite.frame.FrameInfo;
import com.allen_sauer.gwt.game.client.sprite.frame.LoopFrameInfo;
import com.allen_sauer.gwt.game.client.ui.util.Direction;

public class Robot1Sprite extends Sprite {
  private static final BoundingBoxInfo BOUNDING_BOX_INFO;
  private static final int FRAME_ANIMATE_INTERVAL = 1;
  private static final int FRAME_HEIGHT = 63;
  private static final int FRAME_WIDTH = 60;
  private static final int FRAMES_HORIZONTAL = 1;
  private static final int FRAMES_VERTICAL = 1;
  private static int[] LEFT_FRAME_SEQUENCE = {0};
  private static int[] RIGHT_FRAME_SEQUENCE = {0};
  private static final String SPRITE_URL = "images/archer alien1 60x63.gif";
  static {
    BOUNDING_BOX_INFO = new BoundingBoxInfo(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
  }

  public Robot1Sprite(Game game) {
    super(game, BOUNDING_BOX_INFO);
    FrameInfo frameInfo = new LoopFrameInfo(this, SPRITE_URL, FRAMES_HORIZONTAL, FRAMES_VERTICAL,
        FRAME_WIDTH, FRAME_HEIGHT, FRAME_ANIMATE_INTERVAL);
    frameInfo.setFrameSequence(LEFT_FRAME_SEQUENCE);
    setFrameInfo(frameInfo);
    setBehavior(new ParatrooperBehavior(this));
  }

  @Override
  public void setDirection(int direction) {
    if (Direction.isEast(direction)) {
      getFrameInfo().setFrameSequence(RIGHT_FRAME_SEQUENCE);
    } else {
      getFrameInfo().setFrameSequence(LEFT_FRAME_SEQUENCE);
    }
  }
}
