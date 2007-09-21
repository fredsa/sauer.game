package com.allen_sauer.gwt.game.client.sprite;

public class BoundingBoxInfo {
  public final int height;
  public final int offsetLeft;
  public final int offsetTop;
  public final int width;

  public BoundingBoxInfo(int offsetLeft, int offsetTop, int width, int height) {
    assert width > 0;
    assert height > 0;
    this.offsetLeft = offsetLeft;
    this.offsetTop = offsetTop;
    this.width = width;
    this.height = height;
  }

  public String toString() {
    return "[ (" + offsetLeft + ", " + offsetTop + ") - ("
        + (offsetLeft + width) + ", " + (offsetTop + height) + ") ]";
  }
}
