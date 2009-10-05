/**
 * Copyright 2009 Fred Sauer
 */
package com.allen_sauer.gwt.game.batblast.client.sprite.player;

import com.allen_sauer.gwt.game.batblast.client.sprite.resources.BatBlastClientBundle;
import com.allen_sauer.gwt.game.client.Game;
import com.allen_sauer.gwt.game.client.behavior.PlayerMovementBehavior;
import com.allen_sauer.gwt.game.client.sprite.BoundingBoxInfo;
import com.allen_sauer.gwt.game.client.sprite.frame.LoopFrameInfo;
import com.allen_sauer.gwt.game.client.sprite.player.PlayerSprite;

public class MrFreezeTallPlayerSprite extends PlayerSprite {
  private static final BoundingBoxInfo BOUNDING_BOX_INFO;
  private static final int FRAME_ANIMATE_INTERVAL_MILLIS = 1;
  private static final int FRAME_HEIGHT = BatBlastClientBundle.INSTANCE.mrFreezeTall().getHeight() / 12;
  private static final int FRAME_WIDTH = BatBlastClientBundle.INSTANCE.mrFreezeTall().getWidth() / 12;
  private static final int FRAMES_HORIZONTAL = 1;
  private static final int FRAMES_VERTICAL = 1;
  private static String SPRITE_URL = BatBlastClientBundle.INSTANCE.mrFreezeTall().getURL();

  static {
    BOUNDING_BOX_INFO = new BoundingBoxInfo(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
  }
  private MrFreezeTallPlayer player;

  public MrFreezeTallPlayerSprite(Game game) {
    super(game, BOUNDING_BOX_INFO, 29, 7);
    addStyleName("player-sprite");
    setFrameInfo(new LoopFrameInfo(this, SPRITE_URL, FRAMES_HORIZONTAL, FRAMES_VERTICAL,
        FRAME_WIDTH, FRAME_HEIGHT, FRAME_ANIMATE_INTERVAL_MILLIS));

    setBehavior(new PlayerMovementBehavior(this));
    addStyleName("player");
  }

  public MrFreezeTallPlayer getPlayer() {
    return player;
  }

  public void setPlayer(MrFreezeTallPlayer player) {
    this.player = player;
  }

}
