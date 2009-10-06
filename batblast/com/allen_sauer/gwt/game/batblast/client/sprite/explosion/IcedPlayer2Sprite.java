/**
 * Copyright 2007 Fred Sauer
 */
package com.allen_sauer.gwt.game.batblast.client.sprite.explosion;

import com.allen_sauer.gwt.game.batblast.client.sprite.resources.BatBlastClientBundle;
import com.allen_sauer.gwt.game.client.Game;
import com.allen_sauer.gwt.game.client.behavior.NullBehavior;
import com.allen_sauer.gwt.game.client.sprite.BoundingBoxInfo;
import com.allen_sauer.gwt.game.client.sprite.Sprite;
import com.allen_sauer.gwt.game.client.sprite.frame.OneTimeFrameInfo;
import com.allen_sauer.gwt.voices.client.Sound;

public class IcedPlayer2Sprite extends Sprite {
  private static final BoundingBoxInfo BOUNDING_BOX_INFO;
  private static final int FRAME_ANIMATE_INTERVAL_MILLIS = 500;
  private static final int FRAME_HEIGHT = BatBlastClientBundle.INSTANCE.icedPlayer1().getHeight() / 5;
  private static final int FRAME_WIDTH = BatBlastClientBundle.INSTANCE.icedPlayer1().getWidth() / 5;
  private static final int FRAMES_HORIZONTAL = 1;
  private static final int FRAMES_VERTICAL = 1;
  private static final String SPRITE_URL = BatBlastClientBundle.INSTANCE.icedPlayer1().getURL();

  static {
    BOUNDING_BOX_INFO = new BoundingBoxInfo(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
  }

  private final Sound sound;

  public IcedPlayer2Sprite(Game game) {
    super(game, BOUNDING_BOX_INFO);
    addStyleName("explosion-sprite");
    setFrameInfo(new OneTimeFrameInfo(this, SPRITE_URL, FRAMES_HORIZONTAL, FRAMES_VERTICAL,
        FRAME_WIDTH, FRAME_HEIGHT, FRAME_ANIMATE_INTERVAL_MILLIS));
    setBehavior(new NullBehavior(this));
    sound = game.getSoundController().createSound(Sound.MIME_TYPE_AUDIO_MPEG,
        "hornetblast-freesoundproject/21915__Halleck__neck_crack_pure.mp3"
    //BatBlastClientBundle.INSTANCE.halleckNeckCrackPure().getUrl()
    );
  }

  @Override
  public void doFirstFrame() {
    super.doFirstFrame();
    sound.play();
  }
}
