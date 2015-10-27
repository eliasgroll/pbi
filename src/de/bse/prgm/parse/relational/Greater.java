package de.bse.prgm.parse.relational;


public class Greater extends Relation {

  
  protected void exec() {
    retVal.setValue(leftSide.getValue() > rightSide.getValue() ? 1 : 0);
  }

  
  public Relation create() {
    return new Greater();
  }

 

  

}
