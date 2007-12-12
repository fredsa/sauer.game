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

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;

import com.allen_sauer.gwt.game.client.ui.util.DOMUtil;

/**
 * {@link com.allen_sauer.gwt.dragdrop.client.util.DOMUtil} implementation for
 * IE.
 */
public class DOMUtilImplIE6 extends DOMUtilImpl {
  @Override
  public boolean allowFocusChangeCurrentTarget(Event event) {
    return !DOM.compare(DOMUtil.getWnd(), DOM.eventGetCurrentTarget(event));
  }

  @Override
  public native void cancelAllDocumentSelections()
  /*-{
    $doc.selection.empty();
  }-*/;

  @Override
  public native int getBorderLeft(Element elem)
  /*-{
    return elem.clientLeft;
  }-*/;

  @Override
  public native int getBorderTop(Element elem)
  /*-{
    return elem.clientTop;
  }-*/;

  @Override
  public native int getClientHeight(Element elem)
  /*-{
    return elem.clientHeight;
  }-*/;

  @Override
  public native int getClientWidth(Element elem)
  /*-{
    return elem.clientWidth;
  }-*/;
}
