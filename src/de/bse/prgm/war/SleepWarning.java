package de.bse.prgm.war;

public class SleepWarning implements IWarning {

  private long time;

  public SleepWarning(long time) {
    this.time = time;
  }

  @Override
  public String warningMsg() {
    return "[Warn]The programm sleeps for ca. " + time / 1000
        + " seconds, wich is very long";
  }

}
