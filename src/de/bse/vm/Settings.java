package de.bse.vm;

public class Settings {
  private boolean printInfo;
  private final boolean emulate4Mhz;
  private final boolean printInternComposition;
  private final boolean ignoreWarnings;

  /**
   * Create new Settings for a runtime environment.
   * 
   * @param printInfo
   *          print additional information for every cmd
   * @param emulate4mhz
   *          slow down the program to match the speed of a microcontroller
   * @param printInternComposition
   *          print the intern composition of the program (for developers)
   * @param ignoreWarnings
   *          ignore warnings
   */
  public Settings(boolean printInfo, boolean emulate4mhz, boolean printInternComposition,
      boolean ignoreWarnings) {
    this.printInfo = printInfo;
    emulate4Mhz = emulate4mhz;
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

  public boolean getEmulate4Mhz() {
    return emulate4Mhz;
  }

  public boolean getPrintInternComposition() {
    return printInternComposition;
  }

  public boolean getIngoreWarnings() {
    return ignoreWarnings;
  }

}
