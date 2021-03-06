/**
 * Copyright 2009 Fred Sauer
 */
package com.allen_sauer.gwt.game.client.ui.util;

public final class MathUtil {
  public static final int sign(double val) {
    if (val > 0) {
      return 1;
    } else if (val < 0) {
      return -1;
    } else {
      return 0;
    }
  }

  /**
   * Prevent instantiation
   */
  private MathUtil() {
  }
}
