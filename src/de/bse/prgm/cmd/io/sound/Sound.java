package de.bse.prgm.cmd.io.sound;

import de.bse.prgm.cmd.io.IOCommand;
import de.bse.prgm.war.IWarning;
import de.bse.run.app.IConsole;
import de.bse.vm.Machine;
import de.bse.vm.Speaker;

/**
 * Sound class which uses vm.Speaker to give out sounds
 * similar to the BS1 sounds whilst using the same syntax.
 * @author Elias Groll, Jonas Reichmann
 * @version 2.15
 */
public class Sound extends IOCommand {

  /**
   * Constructor of the Sound class used to create sounds
   * similar to the BS1
   * @param num pin on which the sound shall be played on (only virtual)
   * @param note to be played (freq = 1 / 0.000095 + ((127-note) * 0,000083))
   * @param duration of the note to be played
   */
  public Sound(String num, String note, String duration) {
    super(num);
    this.noteString = note;
    this.durationString = duration;
  }

  private String noteString;
  private String durationString;
  private int duration;
  private int frequency;

  
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
    int note = (int) machine.parseIVariable(noteString).getValue();
    this.frequency = (int) (1 / (0.000095 + ((127 - note) * 0.000083)));
  }

  
  public String infoMsg() {
    return "[Info]Sound(" + frequency + "hz) on P" + num + " for " + duration
        * 12 + " milliseconds";
  }

  
  public String toString() {
    return "SOUND";
  }

}
