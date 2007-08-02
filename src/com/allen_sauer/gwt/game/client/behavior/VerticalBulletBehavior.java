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

  public void doFirstFrame() {
    yMin = -sprite.frameMetaData.frameHeight;
    //    yMax = Engine.getClientHeight() - sprite.getFrameHeight();
    sprite.setXY(playerSprite.getX(), playerSprite.getY());

    ySpeed = -10;
  }

  public FrameListenerRetention doFrame() {
    int y = sprite.getY() + ySpeed;
    sprite.setY(y);

    if (y < yMin) {
      return LISTENER_REMOVE;
    }
    return LISTENER_CONTINUE;
  }

  public void doLastFrame() {
  }
}
