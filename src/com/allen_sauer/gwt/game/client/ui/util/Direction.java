package com.allen_sauer.gwt.game.client.ui.util;

public final class Direction {
  public static final int EAST = 0x01;
  public static final int NORTH = 0x02;
  public static final int SOUTH = 0x04;
  public static final int WEST = 0x08;
  private static final int EAST_WEST;

  static {
    EAST_WEST = EAST | WEST;
  }

  public static boolean isEast(int direction) {
    return (direction & EAST) != 0;
  }

  public static boolean isEastWest(int direction) {
    return (direction & EAST_WEST) != 0;
  }

  /**
   * Prevent instantiation.
   */
  private Direction() {
  }
}
