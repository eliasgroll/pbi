package de.bse.prgm.war;

public class LabelWarning implements IWarning {

  private int position;
  private String name;

  public LabelWarning(int position, String name) {
    this.position = position;
    this.name = name;
  }

  
  public String warningMsg() {
    return "[Warn]There is a label called \"" + name + "\" in line "
        + position;
  }

}
