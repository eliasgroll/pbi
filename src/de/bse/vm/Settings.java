package de.bse.vm;

public class Settings {
  private boolean printInfo;
  private final boolean emulate4MHZ;
  private final boolean printInternComposition;
  private final boolean ignoreWarnings;

  public Settings(boolean printInfo, boolean emulate4mhz,
      boolean printInternComposition, boolean ignoreWarnings) {
    this.printInfo = printInfo;
    emulate4MHZ = emulate4mhz;
    this.printInternComposition = printInternComposition;
    this.ignoreWarnings = ignoreWarnings;
  }

  public void activateInfo() {
    this.printInfo = true;
  }

  public void deactivateInfo() {
    this.printInfo = true;
  }

  public boolean getPrintInfo() {
    return printInfo;
  }

  public boolean getEmulate4MHZ() {
    return emulate4MHZ;
  }

  public boolean getPrintInternComposition() {
    return printInternComposition;
  }

  public boolean getIngoreWarnings() {
    return ignoreWarnings;
  }

}
