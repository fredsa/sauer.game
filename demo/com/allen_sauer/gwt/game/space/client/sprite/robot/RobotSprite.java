package com.allen_sauer.gwt.game.space.client.sprite.robot;

import com.allen_sauer.gwt.game.client.Game;
import com.allen_sauer.gwt.game.client.behavior.ParatrooperBehavior;
import com.allen_sauer.gwt.game.client.sprite.Sprite;

public class RobotSprite extends Sprite {
  private static final int FRAME_ANIMATE_INTERVAL = 4;
  private static final int FRAME_HEIGHT = 90;
  private static final int FRAME_WIDTH = 65;
  private static final int FRAMES_HORIZONTAL = 6;
  private static final int FRAMES_VERTICAL = 1;
  private static final String SPRITE_URL = "images/robots-03-map-tr.gif";

  public RobotSprite(Game game) {
    super(game, SPRITE_URL, FRAMES_HORIZONTAL, FRAMES_VERTICAL, FRAME_WIDTH, FRAME_HEIGHT, FRAME_ANIMATE_INTERVAL,
        ANIMATE_SEQUENCE_SEQUENTIAL);
    setBehavior(new ParatrooperBehavior(this));
  }
}
