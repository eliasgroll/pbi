package de.bse.run;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import de.bse.prgm.Interpreter;
import de.bse.prgm.parse.Lexer;
import de.bse.prgm.struct.Program;
import de.bse.run.app.IConsole;
import de.bse.util.Stopwatch;
import de.bse.vm.Machine;
import de.bse.vm.Settings;
import de.bse.vm.storage.IEEPROMMonitor;

/**
 * The runner to be used in plugins.
 * 
 * @author Elias Groll
 * @version 10.15
 *
 */
public class PluginRunner {
	private Scanner scanner;

	public void run(File file, IConsole console, IEEPROMMonitor monitor,
			Settings settings) {
		try {
			compile(new String(Files.readAllBytes(Paths.get(file
					.getAbsolutePath()))), file.getAbsolutePath(), console,
					settings, monitor);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * The application of the project.
	 * 
	 * @param args
	 */
	public void compile(String code, String filepath, IConsole console,
			Settings settings, IEEPROMMonitor monitor) {
		try {
			console.printLn("PiBasic VM [Version 2.15] \n by Elias Groll, Jonas Reichmann.\n");

			Program program = Lexer.createProgramFromString(code);
			Machine machine = new Machine(program, settings, monitor);
			Interpreter interpreter = new Interpreter(machine, console);
			int execs;
			machine.setExecutionIndex(0);
			console.printLn("[Execute, " + filepath + "]");
			Stopwatch.start();
			execs = interpreter.run();
			console.printLn("[End of file, " + Stopwatch.stop()
					+ " milliseconds, " + execs + " instructions]\n");

		} catch (Exception e) {
			console.printLn("[Error, program]Sorry! Something went wrong");
		}
	}

	@SuppressWarnings("unused")
	private String getCodeFromFile(File file) throws FileNotFoundException {
		scanner = new Scanner(file);
		String retVal = "";
		while (scanner.hasNextLine()) {
			retVal += scanner.nextLine() + "\n";
		}
		return retVal;
	}
}
