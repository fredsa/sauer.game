/**
 * Copyright 2009 Fred Sauer
 */
package com.allen_sauer.gwt.game.space.client.sprite.player;

import com.allen_sauer.gwt.game.client.Game;
import com.allen_sauer.gwt.game.client.behavior.PlayerMovementBehavior;
import com.allen_sauer.gwt.game.client.sprite.BoundingBoxInfo;
import com.allen_sauer.gwt.game.client.sprite.frame.LoopFrameInfo;
import com.allen_sauer.gwt.game.client.sprite.player.PlayerSprite;

public class SpacePlayerSprite extends PlayerSprite {
  private static final BoundingBoxInfo BOUNDING_BOX_INFO;
  private static final int FRAME_ANIMATE_INTERVAL_MILLIS = 1;
  private static final int FRAME_HEIGHT = 113;
  private static final int FRAME_WIDTH = 51;
  private static final int FRAMES_HORIZONTAL = 1;
  private static final int FRAMES_VERTICAL = 1;
  private static final String SPRITE_URL = "space-images/SpaceShuttle-sm-tr.gif";

  static {
    BOUNDING_BOX_INFO = new BoundingBoxInfo(5, 15, 41, 97);
  }

  private SpacePlayer player;

  public SpacePlayerSprite(Game game) {
    super(game, BOUNDING_BOX_INFO, 17, -5);
    addStyleName("player-sprite");
    setFrameInfo(new LoopFrameInfo(this, SPRITE_URL, FRAMES_HORIZONTAL, FRAMES_VERTICAL,
        FRAME_WIDTH, FRAME_HEIGHT, FRAME_ANIMATE_INTERVAL_MILLIS));

    setBehavior(new PlayerMovementBehavior(this));
    addStyleName("player");
  }

  public SpacePlayer getPlayer() {
    return player;
  }

  public void setPlayer(SpacePlayer player) {
    this.player = player;
  }
}
