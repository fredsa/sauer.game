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

/**
 * {@link com.allen_sauer.gwt.dragdrop.client.util.DOMUtil} implementation for
 * Mozilla.
 */
public class DOMUtilImplMozilla extends DOMUtilImplStandard {
  public native boolean isOrContains(Element parent, Element child)
  /*-{
    return child.isSameNode(parent) || !!(parent.compareDocumentPosition(child) & 16);
  }-*/;
}
