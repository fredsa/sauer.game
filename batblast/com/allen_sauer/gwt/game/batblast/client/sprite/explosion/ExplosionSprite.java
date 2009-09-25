/**
 * Copyright 2007 Fred Sauer
 */
package com.allen_sauer.gwt.game.batblast.client.sprite.explosion;

import com.allen_sauer.gwt.game.batblast.client.sprite.resources.BatBlastRobotClientBundle;
import com.allen_sauer.gwt.game.client.Game;
import com.allen_sauer.gwt.game.client.behavior.NullBehavior;
import com.allen_sauer.gwt.game.client.sprite.BoundingBoxInfo;
import com.allen_sauer.gwt.game.client.sprite.Sprite;
import com.allen_sauer.gwt.game.client.sprite.frame.OneTimeFrameInfo;
import com.allen_sauer.gwt.voices.client.Sound;

public class ExplosionSprite extends Sprite {
  private static final BoundingBoxInfo BOUNDING_BOX_INFO;
  private static final int FRAME_ANIMATE_INTERVAL_MILLIS = 400;
  private static final int FRAME_HEIGHT = BatBlastRobotClientBundle.INSTANCE.skylerTwoFace().getHeight() * 0 + 128;
  private static final int FRAME_WIDTH = BatBlastRobotClientBundle.INSTANCE.skylerTwoFace().getWidth() * 0 + 128;
  private static final int FRAMES_HORIZONTAL = 2;
  private static final int FRAMES_VERTICAL = 1;
  private static final String SPRITE_URL = BatBlastRobotClientBundle.INSTANCE.skylerTwoFace().getURL();

  static {
    BOUNDING_BOX_INFO = new BoundingBoxInfo(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
  }

  private final Sound sound;

  public ExplosionSprite(Game game) {
    super(game, BOUNDING_BOX_INFO);
    addStyleName("explosion-sprite");
    setFrameInfo(new OneTimeFrameInfo(this, SPRITE_URL, FRAMES_HORIZONTAL, FRAMES_VERTICAL,
        FRAME_WIDTH, FRAME_HEIGHT, FRAME_ANIMATE_INTERVAL_MILLIS));
    setBehavior(new NullBehavior(this));
    sound = game.getSoundController().createSound(Sound.MIME_TYPE_AUDIO_MPEG,
        "hornetblast-freesoundproject/34200_themfish_bamf.mp3");
  }

  @Override
  public void doFirstFrame() {
    super.doFirstFrame();
    sound.play();
  }

  @Override
  public boolean doFrame(double millis) {
    return super.doFrame(millis);
    // TODO
  }
}
