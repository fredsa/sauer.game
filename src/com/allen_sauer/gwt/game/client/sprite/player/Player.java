package com.allen_sauer.gwt.game.client.sprite.player;

import com.allen_sauer.gwt.game.client.Game;

public abstract class Player {
  private final Game game;
  private int score;

  public Player(Game game) {
    this.game = game;
  }

  public abstract void die();

  public int getScore() {
    return score;
  }

  public void incrementScore(int points) {
    setScore(score + points);
  }

  public void setScore(int score) {
    this.score = score;
    game.updatePlayerText();
  }
}
