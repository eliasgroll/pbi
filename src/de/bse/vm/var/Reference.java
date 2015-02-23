package de.bse.vm.var;

/**
 * Represents a reference in the BS1 architecture
 * @author Elias Groll
 * @version 2.15
 */
public class Reference implements IVariable {

  /**
   * Creates a new reference to a given object.
   * @param referencedObject to be referenced
   */
  public Reference(IVariable referencedObject) {
    this.referencedObject = referencedObject;
  }

  private final IVariable referencedObject;

  /**
   * Gets the value of the reference.
   * @return value of the reference
   */
  @Override
  public long getValue() {
    return referencedObject.getValue();
  }

  /**
   * Sets the value of the reference.
   * @param value to be set to
   */
  @Override
  public void setValue(long value) {
    referencedObject.setValue(value);
  }

}
