package de.bse.prgm.war;

public class SoundWarning implements IWarning {

  @Override
  public String warningMsg() {
    return "[Warn]The emulated speaker does not work in any environnement";
  }

}
