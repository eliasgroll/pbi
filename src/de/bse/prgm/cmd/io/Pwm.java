package de.bse.prgm.cmd.io;

import de.bse.prgm.cmd.ICommand;
import de.bse.prgm.cmd.time.Pause;
import de.bse.run.app.IConsole;
import de.bse.vm.Machine;

public class Pwm implements ICommand {
/**
 * Simulate analog pwm.
 * 
 * @param pin
 * @param duty
 * @param duration
 */
  /**
   * Constructor of the PWM class which simulates a PWM
   * command on the BS1.
   * @param pin on which the PWM signal shall be created
   * @param duty time of the PWM signal
   * @param duration of the whole PWM signal
   */
  public Pwm(String pin, int duty, int duration) {
    pause = new Pause(String.valueOf(duration));
    input = new Input(pin);
    this.pin = pin;
    this.duty = duty;
    this.duration = duration;
  }

  private final int duty;
  private final String pin;
  private final int duration;
  private final Pause pause;
  private final Input input;

  @Override
  public void execute(Machine machine, IConsole console) {
    pause.execute(machine, console);
    input.execute(machine, console);
  }

  @Override
  public String infoMsg() {
    return "[Info]Simulate PWM on PIN" + pin + " with duty " + duty
        + " and duration " + duration;
  }

}
