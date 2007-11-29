package com.allen_sauer.gwt.game.hornetblast.client.ui;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;

public class HornetLabel extends Composite {
  private HTML label1 = new HTML("", false);
  private HTML label2 = new HTML("", false);

  public HornetLabel() {
    FlexTable flexTable = new FlexTable();
    initWidget(flexTable);
    addStyleName("hornet-label");

    AbsolutePanel absolutePanel = new AbsolutePanel();
    flexTable.setWidget(0, 0, absolutePanel);
    DOM.setStyleAttribute(getElement(), "margin", "auto");

    label1.addStyleName("hornet-label1");
    label2.addStyleName("hornet-label2");
    absolutePanel.add(label1, 1, 1);
    absolutePanel.add(label2);
  }

  public HornetLabel(String text) {
    this();
    setHTML(text);
  }

  public void setHTML(String text) {
    label1.setHTML(text);
    label2.setHTML(text);
  }
}
