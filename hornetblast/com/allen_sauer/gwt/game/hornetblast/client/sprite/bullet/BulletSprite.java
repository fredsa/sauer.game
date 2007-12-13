/**
 * Copyright 2007 Fred Sauer
 */
package com.allen_sauer.gwt.game.hornetblast.client.sprite.bullet;

import com.allen_sauer.gwt.game.client.Game;
import com.allen_sauer.gwt.game.client.behavior.VerticalBulletBehavior;
import com.allen_sauer.gwt.game.client.sprite.BoundingBoxInfo;
import com.allen_sauer.gwt.game.client.sprite.Sprite;
import com.allen_sauer.gwt.game.client.sprite.frame.LoopFrameInfo;
import com.allen_sauer.gwt.game.client.sprite.player.PlayerSprite;
import com.allen_sauer.gwt.voices.client.Sound;

public class BulletSprite extends Sprite {
  private static final BoundingBoxInfo BOUNDING_BOX_INFO;
  private static final int FRAME_ANIMATE_INTERVAL = 5;
  private static final int FRAME_HEIGHT = 25;
  private static final int FRAME_WIDTH = 25;
  private static final int FRAMES_HORIZONTAL = 4;
  private static final int FRAMES_VERTICAL = 1;
  private static final String SPRITE_URL = "images/archer bullet 25x25 4x1frames.gif";

  static {
    BOUNDING_BOX_INFO = new BoundingBoxInfo(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
  }

  private Sound sound;

  public BulletSprite(Game game, PlayerSprite playerSprite) {
    super(game, BOUNDING_BOX_INFO);
    addStyleName("bullet-sprite");
    setFrameInfo(new LoopFrameInfo(this, SPRITE_URL, FRAMES_HORIZONTAL, FRAMES_VERTICAL, FRAME_WIDTH, FRAME_HEIGHT,
        FRAME_ANIMATE_INTERVAL));
    setBehavior(new VerticalBulletBehavior(this, playerSprite));
    sound = game.getSoundController().createSound(Sound.MIME_TYPE_AUDIO_MPEG, "freesoundproject/28917__junggle__btn107.mp3");
    sound.setVolume((int) (sound.getVolume() * .15));
  }

  @Override
  public void doFirstFrame() {
    sound.play();
    super.doFirstFrame();
  }
}
