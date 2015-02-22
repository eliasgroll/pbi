package de.bse.prgm.parse.relational;

public class Less extends Relation {

  @Override
  protected void exec() {
    retVal.setValue(leftSide.getValue() < rightSide.getValue() ? 1 : 0);
  }

  @Override
  public Relation create() {
    return new Less();
  }

}
