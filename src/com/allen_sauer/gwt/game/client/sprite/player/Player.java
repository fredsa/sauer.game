/**
 * Copyright 2007 Fred Sauer
 */
package com.allen_sauer.gwt.game.client.sprite.player;

import com.allen_sauer.gwt.game.client.FrameListener;
import com.allen_sauer.gwt.game.client.Game;

public abstract class Player implements FrameListener {
  private final Game game;
  private int score;

  public Player(Game game) {
    this.game = game;
    game.getPlayingFrameListenerCollection().addFrameListener(this);
  }

  public abstract void die();

  public void doFirstFrame() {
  }

  public boolean doFrame(double millis) {
    return true;
  }

  public void doLastFrame() {
  }

  public int getScore() {
    return score;
  }

  public boolean hit() {
    die();
    return true;
  }

  public void incrementScore(int points) {
    setScore(score + points);
  }

  public void reset() {
    score = 0;
  }

  public void setScore(int score) {
    this.score = score;
    game.updatePlayerText();
  }
}
