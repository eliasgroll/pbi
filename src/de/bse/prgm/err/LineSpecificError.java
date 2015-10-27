package de.bse.prgm.err;

public abstract class LineSpecificError implements IError {

	public LineSpecificError(int lineNumber) {
		this.lineNumber = lineNumber;

	}

	protected final int lineNumber;

	protected String prefix() {
		return "[Error, line " + lineNumber + "]";
		
	}

}
