package com.allen_sauer.gwt.game.client.sound;

import com.allen_sauer.gwt.log.client.Log;
import com.allen_sauer.gwt.voices.client.Sound;

import java.util.ArrayList;
import java.util.Iterator;

public class GameSound extends Sound {
  private static final ArrayList soundList = new ArrayList();
  private static int volume;

  public static void setGameVolume(int volume) {
    GameSound.volume = volume;
    for (Iterator iterator = soundList.iterator(); iterator.hasNext();) {
      GameSound sound = (GameSound) iterator.next();
      sound.setVolume(volume);
    }
  }

  public GameSound(String url) {
    super(url, false);
    setVolume(volume);
    soundList.add(this);
  }
}
