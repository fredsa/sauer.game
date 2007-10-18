package com.allen_sauer.gwt.game.space.client.sprite.robot;

import com.allen_sauer.gwt.game.client.Game;
import com.allen_sauer.gwt.game.client.behavior.ParatrooperBehavior;
import com.allen_sauer.gwt.game.client.sprite.BoundingBoxInfo;
import com.allen_sauer.gwt.game.client.sprite.Sprite;
import com.allen_sauer.gwt.game.client.sprite.frame.FrameInfo;
import com.allen_sauer.gwt.game.client.ui.util.Direction;

public class RobotSprite extends Sprite {
  private static final BoundingBoxInfo BOUNDING_BOX_INFO;
  private static final int FRAME_ANIMATE_INTERVAL = 4;
  private static final int FRAME_HEIGHT = 90;
  private static final int FRAME_WIDTH = 65;
  private static final int FRAMES_HORIZONTAL = 6;
  private static final int FRAMES_VERTICAL = 2;
  private static int[] LEFT_FRAME_SEQUENCE = {11, 10, 9, 8, 7, 6};
  private static int[] RIGHT_FRAME_SEQUENCE = {0, 1, 2, 3, 4, 5};
  private static final String SPRITE_URL = "images/robots-03-map-tr.gif";

  static {
    BOUNDING_BOX_INFO = new BoundingBoxInfo(8, 6, 46, 75);
  }

  public RobotSprite(Game game) {
    super(game, BOUNDING_BOX_INFO);
    FrameInfo frameInfo = new FrameInfo(this, SPRITE_URL, FRAMES_HORIZONTAL, FRAMES_VERTICAL, FRAME_WIDTH, FRAME_HEIGHT,
        FRAME_ANIMATE_INTERVAL);
    frameInfo.setFrameSequence(LEFT_FRAME_SEQUENCE);
    setFrameInfo(frameInfo);
    setBehavior(new ParatrooperBehavior(this));
  }

  public void setDirection(int direction) {
    if (Direction.isEast(direction)) {
      getFrameInfo().setFrameSequence(RIGHT_FRAME_SEQUENCE);
    } else {
      getFrameInfo().setFrameSequence(LEFT_FRAME_SEQUENCE);
    }
  }
}
