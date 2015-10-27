package de.bse.prgm.cmd.io;


public class Serin extends AnalogSimulationCommand {

  private final String myVarString;
  
  public Serin(String num, String varString, String pin) {
    super(num, varString, pin);
    this.myVarString = varString;
  }

  
  public String infoMsg() {
    return "[Info]Simulate data recievement on \"" + myVarString + "\"";
  }

}
