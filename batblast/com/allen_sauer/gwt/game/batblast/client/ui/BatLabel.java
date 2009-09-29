/**
 * Copyright 2009 Fred Sauer
 */
package com.allen_sauer.gwt.game.batblast.client.ui;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;

public class BatLabel extends Composite {
  private HTML label1 = new HTML("", false);
  private HTML label2 = new HTML("", false);

  public BatLabel() {
    FlexTable flexTable = new FlexTable();
    initWidget(flexTable);
    addStyleName("bat-label");

    AbsolutePanel absolutePanel = new AbsolutePanel();
    flexTable.setWidget(0, 0, absolutePanel);
    DOM.setStyleAttribute(getElement(), "margin", "auto");

    label1.addStyleName("bat-label1");
    label2.addStyleName("bat-label2");
    absolutePanel.add(label1, 1, 1);
    absolutePanel.add(label2);
  }

  public BatLabel(String text) {
    this();
    setHTML(text);
  }

  public void setHTML(String text) {
    label1.setHTML(text);
    label2.setHTML(text);
  }
}
