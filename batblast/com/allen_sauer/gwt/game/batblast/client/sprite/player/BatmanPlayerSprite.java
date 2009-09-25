/**
 * Copyright 2007 Fred Sauer
 */
package com.allen_sauer.gwt.game.batblast.client.sprite.player;

import com.allen_sauer.gwt.game.batblast.client.sprite.resources.BatBlastRobotClientBundle;
import com.allen_sauer.gwt.game.client.Game;
import com.allen_sauer.gwt.game.client.behavior.PlayerMovementBehavior;
import com.allen_sauer.gwt.game.client.sprite.BoundingBoxInfo;
import com.allen_sauer.gwt.game.client.sprite.frame.LoopFrameInfo;
import com.allen_sauer.gwt.game.client.sprite.player.PlayerSprite;

public class BatmanPlayerSprite extends PlayerSprite {
  private static final BoundingBoxInfo BOUNDING_BOX_INFO;
  private static final int FRAME_ANIMATE_INTERVAL_MILLIS = 1;
  private static final int FRAME_HEIGHT = BatBlastRobotClientBundle.INSTANCE.skylerBatman().getHeight() * 0 + 64;
  private static final int FRAME_WIDTH = BatBlastRobotClientBundle.INSTANCE.skylerBatman().getWidth() * 0 + 64;
  private static final int FRAMES_HORIZONTAL = 1;
  private static final int FRAMES_VERTICAL = 1;
  private static String SPRITE_URL = BatBlastRobotClientBundle.INSTANCE.skylerBatman().getURL();

  static {
    BOUNDING_BOX_INFO = new BoundingBoxInfo(5, 15, 40, 46);
  }
  private BatmanPlayer player;

  public BatmanPlayerSprite(Game game) {
    super(game, BOUNDING_BOX_INFO, 29, 7);
    addStyleName("player-sprite");
    setFrameInfo(new LoopFrameInfo(this, SPRITE_URL, FRAMES_HORIZONTAL, FRAMES_VERTICAL,
        FRAME_WIDTH, FRAME_HEIGHT, FRAME_ANIMATE_INTERVAL_MILLIS));

    setBehavior(new PlayerMovementBehavior(this));
    addStyleName("player");
  }

  public BatmanPlayer getPlayer() {
    return player;
  }

  public void setPlayer(BatmanPlayer player) {
    this.player = player;
  }

}
