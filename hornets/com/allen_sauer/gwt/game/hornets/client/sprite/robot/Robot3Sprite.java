package com.allen_sauer.gwt.game.hornets.client.sprite.robot;

import com.allen_sauer.gwt.game.client.Game;
import com.allen_sauer.gwt.game.client.behavior.ParatrooperBehavior;
import com.allen_sauer.gwt.game.client.sprite.BoundingBoxInfo;
import com.allen_sauer.gwt.game.client.sprite.Sprite;
import com.allen_sauer.gwt.game.client.sprite.frame.FrameInfo;
import com.allen_sauer.gwt.game.client.sprite.frame.LoopFrameInfo;

public class Robot3Sprite extends Sprite {
  private static final BoundingBoxInfo BOUNDING_BOX_INFO;
  private static final int FRAME_ANIMATE_INTERVAL = 10;
  private static final int FRAME_HEIGHT = 104;
  private static int[] FRAME_SEQUENCE = {0, 1};
  private static final int FRAME_WIDTH = 100;
  private static final int FRAMES_HORIZONTAL = 2;
  private static final int FRAMES_VERTICAL = 1;
  private static final String SPRITE_URL = "images/archer gigantobot 100x104 2x1frames.png";

  static {
    BOUNDING_BOX_INFO = new BoundingBoxInfo(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
  }

  public Robot3Sprite(Game game) {
    super(game, BOUNDING_BOX_INFO);
    FrameInfo frameInfo = new LoopFrameInfo(this, SPRITE_URL, FRAMES_HORIZONTAL, FRAMES_VERTICAL, FRAME_WIDTH, FRAME_HEIGHT,
        FRAME_ANIMATE_INTERVAL);
    frameInfo.setFrameSequence(FRAME_SEQUENCE);
    setFrameInfo(frameInfo);
    setBehavior(new ParatrooperBehavior(this));
  }
}