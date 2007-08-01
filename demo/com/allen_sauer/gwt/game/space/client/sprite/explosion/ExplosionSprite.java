package com.allen_sauer.gwt.game.space.client.sprite.explosion;

import com.allen_sauer.gwt.game.client.Game;
import com.allen_sauer.gwt.game.client.behavior.ParatrooperBehavior;
import com.allen_sauer.gwt.game.client.sprite.Sprite;

public class ExplosionSprite extends Sprite {
  private static final int FRAME_ANIMATE_INTERVAL = 1;
  private static final int FRAME_HEIGHT = 96;
  private static final int FRAME_WIDTH = 96;
  private static final int FRAMES_HORIZONTAL = 5;
  private static final int FRAMES_VERTICAL = 4;
  private static final String SPRITE_URL = "images/GasExplosion-bb-tr.gif";

  public ExplosionSprite(Game game) {
    super(game, SPRITE_URL, FRAMES_HORIZONTAL, FRAMES_VERTICAL, FRAME_WIDTH, FRAME_HEIGHT, FRAME_ANIMATE_INTERVAL,
        ANIMATE_SEQUENCE_SEQUENTIAL);
    setBehavior(new ParatrooperBehavior(this));
  }
}
