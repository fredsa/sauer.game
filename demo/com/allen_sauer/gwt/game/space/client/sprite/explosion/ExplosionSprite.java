package com.allen_sauer.gwt.game.space.client.sprite.explosion;

import com.allen_sauer.gwt.game.client.Game;
import com.allen_sauer.gwt.game.client.behavior.NullBehavior;
import com.allen_sauer.gwt.game.client.sprite.BoundingBoxInfo;
import com.allen_sauer.gwt.game.client.sprite.Sprite;
import com.allen_sauer.gwt.game.client.sprite.frame.OneTimeFrameInfo;

public class ExplosionSprite extends Sprite {
  private static final BoundingBoxInfo BOUNDING_BOX_INFO;
  private static final int FRAME_ANIMATE_INTERVAL = 2;
  private static final int FRAME_HEIGHT = 96;
  private static final int FRAME_WIDTH = 96;
  private static final int FRAMES_HORIZONTAL = 5;
  private static final int FRAMES_VERTICAL = 4;
  private static final String SPRITE_URL = "images/GasExplosion-bb-tr.gif";

  static {
    BOUNDING_BOX_INFO = new BoundingBoxInfo(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
  }

  public ExplosionSprite(Game game) {
    super(game, BOUNDING_BOX_INFO);
    setFrameInfo(new OneTimeFrameInfo(this, SPRITE_URL, FRAMES_HORIZONTAL, FRAMES_VERTICAL,
        FRAME_WIDTH, FRAME_HEIGHT, FRAME_ANIMATE_INTERVAL));
    setBehavior(new NullBehavior(this));
  }
}