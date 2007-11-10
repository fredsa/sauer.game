package com.allen_sauer.gwt.game.hornets.client.ui;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;

public class HornetLabel extends Composite {
  private Label label1 = new Label("", false);
  private Label label2 = new Label("", false);

  public HornetLabel() {
    AbsolutePanel container = new AbsolutePanel();
    initWidget(container);
    DOM.setStyleAttribute(container.getElement(), "overflow", "");
    addStyleName("hornet-label");

    label1.addStyleName("hornet-label1");
    label2.addStyleName("hornet-label2");
    container.add(label1, 0, 0);
    container.add(label2, 0, 0);
  }

  public void setText(String text) {
    label1.setText(text);
    label2.setText(text);
  }
}
