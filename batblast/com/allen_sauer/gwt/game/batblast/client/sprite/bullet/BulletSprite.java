/**
 * Copyright 2009 Fred Sauer
 */
package com.allen_sauer.gwt.game.batblast.client.sprite.bullet;

import com.allen_sauer.gwt.game.batblast.client.sprite.resources.BatBlastRobotClientBundle;
import com.allen_sauer.gwt.game.client.Game;
import com.allen_sauer.gwt.game.client.behavior.VerticalBulletBehavior;
import com.allen_sauer.gwt.game.client.sprite.BoundingBoxInfo;
import com.allen_sauer.gwt.game.client.sprite.Sprite;
import com.allen_sauer.gwt.game.client.sprite.frame.LoopFrameInfo;
import com.allen_sauer.gwt.game.client.sprite.player.PlayerSprite;
import com.allen_sauer.gwt.voices.client.Sound;

public class BulletSprite extends Sprite {
  static int delay;
  private static final BoundingBoxInfo BOUNDING_BOX_INFO;
  private static final int FRAME_ANIMATE_INTERVAL = 500;
  private static final int FRAME_HEIGHT = BatBlastRobotClientBundle.INSTANCE.iceCube().getHeight() * 0 + 32;
  private static final int FRAME_WIDTH = BatBlastRobotClientBundle.INSTANCE.iceCube().getWidth() * 0 + 32;
  private static final int FRAMES_HORIZONTAL = 1;
  private static final int FRAMES_VERTICAL = 1;

  private static final String SPRITE_URL = BatBlastRobotClientBundle.INSTANCE.iceCube().getURL();

  static {
    BOUNDING_BOX_INFO = new BoundingBoxInfo(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
  }
  private final Sound sound;

  public BulletSprite(Game game, PlayerSprite playerSprite) {
    super(game, BOUNDING_BOX_INFO);
    addStyleName("bullet-sprite");
    setFrameInfo(new LoopFrameInfo(this, SPRITE_URL, FRAMES_HORIZONTAL, FRAMES_VERTICAL,
        FRAME_WIDTH, FRAME_HEIGHT, FRAME_ANIMATE_INTERVAL));
    setBehavior(new VerticalBulletBehavior(this, playerSprite));
    sound = game.getSoundController().createSound(Sound.MIME_TYPE_AUDIO_MPEG,
        "hornetblast-freesoundproject/28917__junggle__btn107.mp3");
    sound.setVolume((int) (sound.getVolume() * .15));
  }

  @Override
  public void doFirstFrame() {
    sound.play();
    super.doFirstFrame();
  }
}
