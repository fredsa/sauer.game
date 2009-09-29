/**
 * Copyright 2009 Fred Sauer
 */
package com.allen_sauer.gwt.game.client.behavior;

import com.google.gwt.event.dom.client.KeyCodes;

import com.allen_sauer.gwt.game.client.Game;
import com.allen_sauer.gwt.game.client.sprite.player.PlayerSprite;

public class PlayerMovementBehavior extends Behavior {
  private final Game game;
  private final PlayerSprite playerSprite;
  private final double xDelta = 0.2;
  private final double yDelta = 0.2;

  public PlayerMovementBehavior(PlayerSprite sprite) {
    super(sprite);
    playerSprite = sprite;
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
  public boolean doFrame(double millis) {
    if (game.input.isKeyboardMode()) {
      if (game.input.isKeyDown(KeyCodes.KEY_LEFT)) {
        setXSpeed(-xDelta);
      } else if (game.input.isKeyDown(KeyCodes.KEY_RIGHT)) {
        setXSpeed(xDelta);
      } else {
        setXSpeed(0);
      }

      if (game.input.isKeyDown(KeyCodes.KEY_UP)) {
        setYSpeed(-yDelta);
      } else if (game.input.isKeyDown(KeyCodes.KEY_DOWN)) {
        setYSpeed(yDelta);
      } else {
        setYSpeed(0);
      }
    } else {
      double x = game.input.getClickX() - playerSprite.getBulletStartOffsetX();
      double y = game.input.getClickY() - playerSprite.getBulletStartOffsetY();

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
    return super.doFrame(millis);
  }
}
