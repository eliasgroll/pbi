package de.bse.prgm.err;


public class InvalidAllocationError  extends LineSpecificError  {

  public InvalidAllocationError(String allocation, int lineNumber) {
   super(lineNumber);
	  this.allocation = allocation;
  }

  String allocation;

  
  public String errorMsg() {
    return prefix() + "The allocation \"" + allocation
        + "\" is not executable";
  }

}
