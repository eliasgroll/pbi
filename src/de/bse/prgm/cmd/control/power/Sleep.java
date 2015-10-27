package de.bse.prgm.cmd.control.power;

import de.bse.prgm.cmd.time.Pause;

/**
 * Sets the controller to standby (time in [s]). Simulated by a pause.
 * 
 * @author Jonas Reichmann
 * @version 2.15
 */
public class Sleep extends Pause {

  public Sleep(String time) {
    super(time, 1000);
  }

  
  public String infoMsg() {
    return "[Info]Paused the execution for ca." + (time * 1000)
        + " milliseconds";
  }
}
