package com.allen_sauer.gwt.game.space.client.sprite.explosion;

import com.allen_sauer.gwt.game.client.Game;
import com.allen_sauer.gwt.game.client.behavior.ExplosionBehavior;
import com.allen_sauer.gwt.game.client.sprite.BoundingBoxInfo;
import com.allen_sauer.gwt.game.client.sprite.FrameInfo;
import com.allen_sauer.gwt.game.client.sprite.Sprite;

public class ExplosionSprite extends Sprite {
  private static final int ANIMATION_STYLE = FrameInfo.ANIMATION_STYLE_SEQUENTIAL;
  private static final BoundingBoxInfo BOUNDING_BOX_INFO;
  private static final int FRAME_ANIMATE_INTERVAL = 1;
  private static final int FRAME_HEIGHT = 96;
  private static final FrameInfo FRAME_INFO;
  private static final int FRAME_WIDTH = 96;
  private static final int FRAMES_HORIZONTAL = 5;
  private static final int FRAMES_VERTICAL = 4;
  private static final String SPRITE_URL = "images/GasExplosion-bb-tr.gif";

  static {
    FRAME_INFO = new FrameInfo(SPRITE_URL, FRAMES_HORIZONTAL, FRAMES_VERTICAL, FRAME_HEIGHT,
        FRAME_WIDTH, FRAME_ANIMATE_INTERVAL, ANIMATION_STYLE);
    BOUNDING_BOX_INFO = new BoundingBoxInfo(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
  }

  public ExplosionSprite(Game game) {
    super(game, FRAME_INFO, BOUNDING_BOX_INFO);
    setBehavior(new ExplosionBehavior(this));
  }
}
