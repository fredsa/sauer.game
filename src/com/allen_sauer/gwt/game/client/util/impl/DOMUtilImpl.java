/*
 * Copyright 2007 Fred Sauer
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.allen_sauer.gwt.game.client.util.impl;

import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Widget;

/**
 * {@link com.allen_sauer.gwt.dragdrop.client.util.DOMUtil} default
 * cross-browser implementation.
 */
public abstract class DOMUtilImpl {
  public boolean allowFocusChangeCurrentTarget(Event event) {
    return true;
  }

  /**
   * @see com.allen_sauer.gwt.dragdrop.client.util.DOMUtil#cancelAllDocumentSelections()
   */
  public native void cancelAllDocumentSelections()
  /*-{
    $doc.selection.empty();
  }-*/;

  /**
   * @see com.allen_sauer.gwt.dragdrop.client.util.DOMUtil#getBorderLeft(Element)
   */
  public abstract int getBorderLeft(Element elem);

  /**
   * @see com.allen_sauer.gwt.dragdrop.client.util.DOMUtil#getBorderTop(Element)
   */
  public abstract int getBorderTop(Element elem);

  /**
   * @see com.allen_sauer.gwt.dragdrop.client.util.DOMUtil#getClientHeight(Element)
   */
  public abstract int getClientHeight(Element elem);

  /**
   * @see com.allen_sauer.gwt.dragdrop.client.util.DOMUtil#getClientWidth(Element)
   */
  public abstract int getClientWidth(Element elem);

  /**
   * @see com.allen_sauer.gwt.dragdrop.client.util.DOMUtil#getHorizontalBorders(Widget)
   */
  public final int getHorizontalBorders(Widget widget) {
    return widget.getOffsetWidth() - getClientWidth(widget.getElement());
  }

  /**
   * @see com.allen_sauer.gwt.dragdrop.client.util.DOMUtil#getNodeName(Element)
   */
  public final native String getNodeName(Element elem)
  /*-{
    return elem.nodeName;
  }-*/;

  /**
   * @see com.allen_sauer.gwt.dragdrop.client.util.DOMUtil#getVerticalBorders(Widget)
   */
  public final int getVerticalBorders(Widget widget) {
    return widget.getOffsetHeight() - getClientHeight(widget.getElement());
  }

  /**
   * @see com.allen_sauer.gwt.dragdrop.client.util.DOMUtil#isOrContains(Element, Element)
   */
  public native boolean isOrContains(Element parent, Element child)
  /*-{
    return parent.contains(child);
  }-*/;

  public native boolean isWnd(Element elem)
  /*-{
    return elem === $wnd;
  }-*/;

  /**
   * @see com.allen_sauer.gwt.dragdrop.client.util.DOMUtil#setStatus(String)
   */
  public final native void setStatus(String text)
  /*-{
     $wnd.status = text;
  }-*/;
}
