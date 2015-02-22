package de.bse.prgm.cmd.io;

public class Pulsin extends AnalogSimulationCommand {

  public Pulsin(String num, String varString, String pin) {
    super(num, varString, pin);
  }

  @Override
  public String infoMsg() {
    return "[Info]Simulate a pulse-measurement on PIN" + num;
  }

}
