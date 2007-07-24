package com.allen_sauer.gwt.game.space.client;

import com.allen_sauer.gwt.game.client.Game;
import com.allen_sauer.gwt.game.client.behavior.VerticalBulletBehavior;
import com.allen_sauer.gwt.game.client.sprite.Sprite;

public class BulletSprite extends Sprite {
  private static final int FRAME_ANIMATE_INTERVAL = 5;
  private static final int FRAME_HEIGHT = 85;
  private static final int FRAME_WIDTH = 60 - 9;
  private static final int FRAMES = 3;
  private static final String SPRITE_URL = "images/burstbullet-tr.gif";

  public BulletSprite(Game game, Sprite playerSprite) {
    super(game, SPRITE_URL, FRAMES, FRAME_WIDTH, FRAME_HEIGHT, FRAME_ANIMATE_INTERVAL, ANIMATE_SEQUENCE_BOUNCE);
    setBehavior(new VerticalBulletBehavior(this, playerSprite));
  }

  public void setFrame(int frame) {
    super.setFrame(frame);
  }
}
