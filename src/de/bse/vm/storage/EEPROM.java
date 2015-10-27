package de.bse.vm.storage;

import de.bse.vm.var.Byte;

/**
 * EEPROM class which represents the EEPROM of the BS1
 * 
 * @author Elias Groll, Jonas Reichmann
 * @version 10.15
 */
public class EEPROM {

	public static int CMD = 204;
	private Byte myByte = new Byte();
	private final IEEPROMMonitor monitor;

	/**
	 * Constructor of the EEPROM class.
	 * 
	 * @param commands
	 *            already in the EEPROM
	 */
	public EEPROM(int commands, IEEPROMMonitor monitor) {
		this.monitor = monitor;
		this.storage = new Byte[256];
		for (int i = 0; i < storage.length; i++) {
			storage[i] = new Byte();
		}
		for (int i = 255; i >= 255 - commands; i--) {
			storage[i].setValue(CMD);
		}
	}

	private Byte[] storage;

	/**
	 * Writes a specific value into a specific location in the EEPROM.
	 * 
	 * @param location
	 *            of the value to be written
	 * @param value
	 *            to be written
	 * @return success
	 */
	public boolean write(long location, long value) {
		myByte.setValue(location);
		boolean retVal = (storage[(int) myByte.getValue()].getValue() == CMD);
		storage[(int) myByte.getValue()].setValue(value);
		if (monitor != null) {
			monitor.update(toString());
		}
		return retVal;
	}

	public long read(long location) {
		myByte.setValue(location);
		return storage[(int) myByte.getValue()].getValue();
	}

	/**
	 * Creates a complete Dump of the EEPROM and returns it as a string.
	 */
	public String toString() {
		String memDump = "";

		for (int i = 0; i < storage.length; i++) {
			if (i % 16 == 0 && i != 0) {
				memDump += "\n";
			}
			memDump += ((storage[i].toString().length() == 1) ? ("0" + storage[i]
					.toString()) : (storage[i].toString()))
					+ " ";

		}

		return memDump;
	}
}
