package com.allen_sauer.gwt.game.client;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.ClickListenerCollection;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.MouseListenerAdapter;
import com.google.gwt.user.client.ui.SourcesClickEvents;
import com.google.gwt.user.client.ui.Widget;

public class Sprite extends Composite {
  private Image image;
  private AbsolutePanel panel = new AbsolutePanel();
  private int frameWidth;

  public Sprite(String url, int fullImageWidth, int fullImageHeight, int left, int top, int frameWidth, int frameHeight) {
    this.frameWidth = frameWidth;
    initWidget(panel);
    image = new Image(url);
    panel.add(image, -left, -top);

    image.setPixelSize(fullImageWidth, fullImageHeight);
    panel.setPixelSize(frameWidth, frameHeight);
  }
  
  public void setFrame(int frame) {
    DOM.setStyleAttribute(image.getElement(), "left", (-frame * frameWidth) + "px");
  }
}
