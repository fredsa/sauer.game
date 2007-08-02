package com.allen_sauer.gwt.game.space.client.sprite.player;

import com.allen_sauer.gwt.game.client.Game;
import com.allen_sauer.gwt.game.client.behavior.CursorKeyBehavior;
import com.allen_sauer.gwt.game.client.sprite.BoundingBoxInfo;
import com.allen_sauer.gwt.game.client.sprite.FrameInfo;
import com.allen_sauer.gwt.game.client.sprite.Sprite;

public class PlayerSprite extends Sprite {
  private static final int ANIMATION_STYLE = FrameInfo.ANIMATION_STYLE_SEQUENTIAL;
  private static final BoundingBoxInfo BOUNDING_BOX_INFO;
  private static final int FRAME_ANIMATE_INTERVAL = 1;
  private static final int FRAME_HEIGHT = 113;
  private static final FrameInfo FRAME_INFO;
  private static final int FRAME_WIDTH = 51;
  private static final int FRAMES_HORIZONTAL = 1;
  private static final int FRAMES_VERTICAL = 1;
  private static final String SPRITE_URL = "images/SpaceShuttle-sm-tr.gif";

  static {
    FRAME_INFO = new FrameInfo(SPRITE_URL, FRAMES_HORIZONTAL, FRAMES_VERTICAL, FRAME_HEIGHT,
        FRAME_WIDTH, FRAME_ANIMATE_INTERVAL, ANIMATION_STYLE);
    BOUNDING_BOX_INFO = new BoundingBoxInfo(5, 15, 41, 97);
  }

  private Player player;

  public PlayerSprite(Game game) {
    super(game, FRAME_INFO, BOUNDING_BOX_INFO);

    setBehavior(new CursorKeyBehavior(this));
    addStyleName("player");
  }

  public Player getPlayer() {
    return player;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }
}
