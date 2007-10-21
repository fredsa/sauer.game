package com.allen_sauer.gwt.game.client;

import com.allen_sauer.gwt.game.client.sprite.player.Player;
import com.allen_sauer.gwt.voices.client.SoundController;

public interface Game {
  void clientResized(int clientWidth, int clientHeight);

  SoundController getSoundController();

  void init();

  void playerDied(Player player);
}
