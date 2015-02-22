package de.bse.prgm.cmd.io.sound;

import de.bse.prgm.cmd.io.IOCommand;
import de.bse.prgm.war.IWarning;
import de.bse.run.app.IConsole;
import de.bse.vm.Machine;
import de.bse.vm.Speaker;

public class Sound extends IOCommand {

  public Sound(String num, String note, String duration) {
    super(num);
    this.noteString = note;
    this.durationString = duration;
//    this.duration = duration;
//    this.frequency = (int) (1 / (0.000095 + ((127 - note) * 0.000083)));
  }

  private String noteString;
  private String durationString;
  private int duration;
  private int frequency;

  @Override
  public void execute(Machine machine, IConsole console) {
    super.execute(machine, console);
    initVars(machine);
    if (dir != null) {
      machine.parseIVariable("DIR" + num).setValue(1);
      try {
        Speaker.tone(frequency, duration * 12);
      } catch (Exception e) {
        try {
          Thread.sleep(duration * 12);
        } catch (InterruptedException e1) {
          machine.getProgram().addWarning(new IWarning() {
            @Override
            public String warningMsg() {
                     return "[Warn]The audio output could have been interrupted";
            }
          });
        }
      }
    }
  }
  
  private void initVars(Machine machine) {
    this.duration = (int) machine.parseIVariable(durationString).getValue();
    this.frequency = (int) (1 / (0.000095 + ((127 - (int) machine.parseIVariable(noteString).getValue()) * 0.000083)));
  }

  @Override
  public String infoMsg() {
    return "[Info]Sound(" + frequency + "hz) on P" + num + " for " + duration
        * 12 + " milliseconds";
  }

  @Override
  public String toString() {
    return "SOUND";
  }

}
