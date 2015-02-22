package de.bse.prgm.cmd.control.power;

import de.bse.prgm.cmd.time.Pause;

/**
 * Sets the controller to standby for a time * 50 ms. Simulated by a pause.
 * 
 * @author Jonas Reichmann
 * @version 2.15
 */
public class Nap extends Pause {

  public Nap(String time) {
    super(time, 50);
  }

  @Override
  public String infoMsg() {
    return "[Info]Paused the execution for ca." + (time * 50) + " milliseconds";
  }

}
