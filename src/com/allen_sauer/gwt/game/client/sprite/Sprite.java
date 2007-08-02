package com.allen_sauer.gwt.game.client.sprite;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SimplePanel;

import com.allen_sauer.gwt.game.client.Game;
import com.allen_sauer.gwt.game.client.behavior.Behavior;
import com.allen_sauer.gwt.game.client.engine.Engine;
import com.allen_sauer.gwt.game.client.engine.FrameListener;
import com.allen_sauer.gwt.game.client.ui.util.FastDOM;

public class Sprite extends Composite implements FrameListener {
  public final BoundingBoxInfo boundingBoxInfo;
  public final FrameInfo frameMetaData;
  private Behavior behavior;
  private int currentFrame;
  private int frameCounter;
  private Game game;
  private Image image;
  private boolean markedForRemoval = false;
  private AbsolutePanel panel = new AbsolutePanel();
  private int poolIndex;
  private SpritePool spritePool;
  private int x;
  private int y;

  public Sprite(Game game, FrameInfo frameMetaData, BoundingBoxInfo boundingBoxInfo) {
    this.game = game;
    this.frameMetaData = frameMetaData;
    this.boundingBoxInfo = boundingBoxInfo;

    initWidget(panel);

    panel.addStyleName("sprite");
    image = new Image(frameMetaData.spriteUrl);
    panel.add(image, frameMetaData.frameXOffset[currentFrame],
        frameMetaData.frameYOffset[currentFrame]);

    image.setPixelSize(frameMetaData.frameWidth * frameMetaData.horizontalFrames,
        frameMetaData.frameHeight * frameMetaData.verticalFrames);
    setPixelSize(frameMetaData.frameWidth, frameMetaData.frameHeight);
    setPixelSize(frameMetaData.frameWidth, frameMetaData.frameHeight);
    Engine.playfield.add(this, -500, -500);

    if (Engine.DEBUG) {
      SimplePanel boundingBoxPanel = new SimplePanel();
      boundingBoxPanel.addStyleName("bounding-box");
      boundingBoxPanel.setPixelSize(boundingBoxInfo.width, boundingBoxInfo.height);
      panel.add(boundingBoxPanel, boundingBoxInfo.offsetLeft, boundingBoxInfo.offsetTop);
    }
  }

  public void doFirstFrame() {
    behavior.doFirstFrame();
  }

  public FrameListenerRetention doFrame() {
    if (markedForRemoval) {
      markedForRemoval = false;
      return LISTENER_REMOVE;
    }
    FastDOM.setElementPosition(getElement(), x, y);
    if (frameMetaData.animationInterval > 0 && ++frameCounter == frameMetaData.animationInterval) {
      frameCounter = 0;
      setFrame((currentFrame + 1) % frameMetaData.virtualFrameCount);
    }
    return behavior.doFrame();
  }

  public void doLastFrame() {
    FastDOM.setElementPosition(getElement(), -500, -500);
    spritePool.destroy(this);
    behavior.doLastFrame();
  }

  public Game getGame() {
    return game;
  }

  public int getPoolIndex() {
    return poolIndex;
  }

  public SpritePool getSpritePool() {
    return spritePool;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public void markForRemoval() {
    markedForRemoval = true;
  }

  public void setBehavior(Behavior behavior) {
    this.behavior = behavior;
  }

  public void setFrame(int frame) {
    FastDOM.setElementPosition(image.getElement(), frameMetaData.frameXOffset[frame],
        frameMetaData.frameYOffset[frame]);
    currentFrame = frame;
  }

  public void setPoolIndex(int poolIndex) {
    this.poolIndex = poolIndex;
  }

  public void setSpritePool(SpritePool spritePool) {
    this.spritePool = spritePool;
  }

  public void setX(int x) {
    this.x = x;
  }

  public final void setXY(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public void setY(int y) {
    this.y = y;
  }
}
