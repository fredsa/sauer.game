/**
 * Copyright 2007 Fred Sauer
 */
package com.allen_sauer.gwt.game.client.behavior;

import com.allen_sauer.gwt.game.client.Game;
import com.allen_sauer.gwt.game.client.sprite.player.PlayerSprite;
import com.allen_sauer.gwt.game.client.ui.util.Page;

public class PlayerMovementBehavior extends Behavior {
  private Game game;
  private int xDelta = 4;
  private int yDelta = 4;
  private final PlayerSprite playerSprite;

  public PlayerMovementBehavior(PlayerSprite sprite) {
    super(sprite);
    this.playerSprite = sprite;
    game = sprite.getGame();
  }

  @Override
  public void doFirstFrame() {
    clientResized();
    //  setX(Random.nextInt(getXMax()));
    //  setY(Random.nextInt(getYMax()));
    setX(getXMax() / 2);
    setY(getYMax());
    super.doFirstFrame();
  }

  @Override
  public boolean doFrame() {
    if (game.input.isKeyboardMode()) {
      if (game.input.isKeyDown(Page.CODES.KEY_LEFT)) {
        setXSpeed(-xDelta);
      } else if (game.input.isKeyDown(Page.CODES.KEY_RIGHT)) {
        setXSpeed(xDelta);
      } else {
        setXSpeed(0);
      }

      if (game.input.isKeyDown(Page.CODES.KEY_UP)) {
        setYSpeed(-yDelta);
      } else if (game.input.isKeyDown(Page.CODES.KEY_DOWN)) {
        setYSpeed(yDelta);
      } else {
        setYSpeed(0);
      }
    } else {
      int x = game.input.getClickX() - playerSprite.getBulletStartOffsetX();
      int y = game.input.getClickY() - playerSprite.getBulletStartOffsetY();

      if (x < getX()) {
        setXSpeed(-xDelta);
      } else if (x > getX()) {
        setXSpeed(xDelta);
      } else {
        setXSpeed(0);
      }

      if (y < getY()) {
        setYSpeed(-yDelta);
      } else if (y > getY()) {
        setYSpeed(yDelta);
      } else {
        setXSpeed(0);
      }
    }
    return super.doFrame();
  }
}
