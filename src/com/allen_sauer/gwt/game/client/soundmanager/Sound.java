package com.allen_sauer.gwt.game.client.soundmanager;

import com.allen_sauer.gwt.log.client.Log;

import java.util.ArrayList;
import java.util.Iterator;

public class Sound {
  private static int counter = 0;
  private static boolean soundManagerReady = false;
  private static ArrayList uncreatedSounds = new ArrayList();

  static {
    init();
  }

  private static String generateId() {
    return "Sound" + ++counter;
  }

  private static native void init()
  /*-{
    $wnd.soundManager.debugMode = false;
//    $wnd.soundManager._writeDebug = function(sText, sType) {
//      @com.allen_sauer.gwt.log.client.Log::debug(Ljava/lang/String;)(sType + ": " + sText);
//    }
    $wnd.soundManager.onload = function() {
      @com.allen_sauer.gwt.game.client.soundmanager.Sound::onLoad()();
    }
    $wnd.soundManager.onError = function() {
      @com.allen_sauer.gwt.log.client.Log::debug(Ljava/lang/String;)("soundmanager onerror()");
    }
  }-*/;

  private static void onLoad() {
//    Log.debug("soundmanager onLoad()");
    for (Iterator iterator = uncreatedSounds.iterator(); iterator.hasNext();) {
      Sound sound = (Sound) iterator.next();
      sound.create();
    }
    soundManagerReady = true;
  }

  private final String name;
  private final String url;

  public Sound(String url) {
    this.url = url;
    name = generateId();
    if (soundManagerReady) {
      create();
    } else {
      uncreatedSounds.add(this);
    }
  }

  public native void play()
  /*-{
    if (@com.allen_sauer.gwt.game.client.soundmanager.Sound::soundManagerReady) {
      $wnd.soundManager.play(this.@com.allen_sauer.gwt.game.client.soundmanager.Sound::name);
    }
  }-*/;

  private native void create()
  /*-{
    $wnd.soundManager.createSound({
        'id': this.@com.allen_sauer.gwt.game.client.soundmanager.Sound::name,
        'url': this.@com.allen_sauer.gwt.game.client.soundmanager.Sound::url
      });
  }-*/;
}
