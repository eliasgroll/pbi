package de.bse.util;

public class Stopwatch {
  private static long start;
  private static long stop;

  public static void start() {
    start = System.currentTimeMillis();
  }

  public static long stop() {
    stop = System.currentTimeMillis();
    return stop - start;
  }
}
