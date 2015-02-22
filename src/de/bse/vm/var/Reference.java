package de.bse.vm.var;

public class Reference implements IVariable {

  public Reference(IVariable referencedObject) {
    this.referencedObject = referencedObject;
  }

  private final IVariable referencedObject;

  @Override
  public long getValue() {
    return referencedObject.getValue();
  }

  @Override
  public void setValue(long value) {
    referencedObject.setValue(value);
  }

}
