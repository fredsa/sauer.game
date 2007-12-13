/**
 * Copyright 2007 Fred Sauer
 */
package com.allen_sauer.gwt.game.client.util.impl;

import com.google.gwt.user.client.Element;

/**
 * {@link com.allen_sauer.gwt.dragdrop.client.util.DOMUtil} implementation for
 * Mozilla.
 */
public class DOMUtilImplMozilla extends DOMUtilImplStandard {
  @Override
  public native boolean isOrContains(Element parent, Element child)
  /*-{
    return child.isSameNode(parent) || !!(parent.compareDocumentPosition(child) & 16);
  }-*/;
}
