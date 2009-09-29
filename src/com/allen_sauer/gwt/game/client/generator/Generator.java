/**
 * Copyright 2009 Fred Sauer
 */
package com.allen_sauer.gwt.game.client.generator;

import com.allen_sauer.gwt.game.client.FrameListener;

public interface Generator extends FrameListener {
  boolean doFrame(double millis);
}
