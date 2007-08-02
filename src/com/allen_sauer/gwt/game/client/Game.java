package com.allen_sauer.gwt.game.client;

import com.allen_sauer.gwt.game.space.client.sprite.player.Player;

public interface Game {
  void clientResized(int clientWidth, int clientHeight);

  void init();

  void playerDied(Player player);
}
