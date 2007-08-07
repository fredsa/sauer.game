package com.allen_sauer.gwt.game.space.client.sprite.bullet;

import com.allen_sauer.gwt.game.client.Game;
import com.allen_sauer.gwt.game.client.behavior.VerticalBulletBehavior;
import com.allen_sauer.gwt.game.client.sprite.BoundingBoxInfo;
import com.allen_sauer.gwt.game.client.sprite.Sprite;
import com.allen_sauer.gwt.game.client.sprite.frame.BounceFrameInfo;

public class BulletSprite extends Sprite {
  private static final BoundingBoxInfo BOUNDING_BOX_INFO;
  private static final int FRAME_ANIMATE_INTERVAL = 5;
  private static final int FRAME_HEIGHT = 85;
  private static final int FRAME_WIDTH = 60;
  private static final int FRAMES_HORIZONTAL = 3;
  private static final int FRAMES_VERTICAL = 1;
  private static final String SPRITE_URL = "images/burstbullet-tr.gif";

  static {
    BOUNDING_BOX_INFO = new BoundingBoxInfo(8, 32, 48, 48);
  }

  public BulletSprite(Game game, Sprite playerSprite) {
    super(game, BOUNDING_BOX_INFO);
    setFrameInfo(new BounceFrameInfo(this, SPRITE_URL, FRAMES_HORIZONTAL, FRAMES_VERTICAL,
        FRAME_WIDTH, FRAME_HEIGHT, FRAME_ANIMATE_INTERVAL));
    setBehavior(new VerticalBulletBehavior(this, playerSprite));
  }
}
