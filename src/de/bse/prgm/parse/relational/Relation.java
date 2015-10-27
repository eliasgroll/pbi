package de.bse.prgm.parse.relational;

import de.bse.vm.var.Bit;
import de.bse.vm.var.IVariable;

public abstract class Relation implements IVariable {

  protected IVariable leftSide;
  protected IVariable rightSide;
  protected IVariable retVal = new Bit(0);

  /**
   * Compares the left side of the Relation to the right side of the relation.
   * @param leftSide of the relation
   * @param rightSide of the relation
   * @return the result
   */
  public IVariable compare(IVariable leftSide, IVariable rightSide) {
    init(this.leftSide, this.rightSide);
    exec();
    return retVal;
  }

  void init(IVariable leftSide, IVariable rightSide) {
    this.leftSide = leftSide;
    this.rightSide = rightSide;
  }

  protected abstract void exec();

  
  public long getValue() {
    return compare(leftSide, rightSide).getValue();
  }

  
  public void setValue(long value) {
    throw new RuntimeException("Cannot change a constant");
  }

  public abstract Relation create();

}
