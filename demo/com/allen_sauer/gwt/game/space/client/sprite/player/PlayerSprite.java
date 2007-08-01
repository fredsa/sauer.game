package com.allen_sauer.gwt.game.space.client.sprite.player;

import com.allen_sauer.gwt.game.client.Game;
import com.allen_sauer.gwt.game.client.behavior.CursorKeyBehavior;
import com.allen_sauer.gwt.game.client.sprite.Sprite;

public class PlayerSprite extends Sprite {
  private static final int FRAME_HEIGHT = 113;
  private static final int FRAME_WIDTH = 51;
  private static final int FRAMES_HORIZONTAL = 1;
  private static final int FRAMES_VERTICAL = 1;
  private static final String SPRITE_URL = "images/SpaceShuttle-sm-tr.gif";

  public PlayerSprite(Game game) {
    super(game, SPRITE_URL, FRAMES_HORIZONTAL, FRAMES_VERTICAL, FRAME_WIDTH, FRAME_HEIGHT);
    setBehavior(new CursorKeyBehavior(this));
    addStyleName("player");
  }
}
