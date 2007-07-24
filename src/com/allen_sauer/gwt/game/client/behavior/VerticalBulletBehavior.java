package com.allen_sauer.gwt.game.client.behavior;

import com.allen_sauer.gwt.game.client.sprite.Sprite;

public class VerticalBulletBehavior implements Behavior {
  private final Sprite playerSprite;
  private final Sprite sprite;
  //  private int yMax;
  private int yMin;
  private int ySpeed;

  public VerticalBulletBehavior(Sprite sprite, Sprite playerSprite) {
    this.sprite = sprite;
    this.playerSprite = playerSprite;
  }

  public void deinitialize() {
  }

  public void doFrame() {
    int y = sprite.getY() + ySpeed;

    if (y < yMin) {
      sprite.removeSelf();
    }

    sprite.setY(y);
  }

  public void initialize() {
    yMin = -sprite.getFrameHeight();
    //    yMax = Engine.getClientHeight() - sprite.getFrameHeight();
    sprite.setXY(playerSprite.getX(), playerSprite.getY());

    ySpeed = -10;
  }
}
