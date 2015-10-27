package de.bse.prgm.err;

public class VariableNotReferenceableError extends LineSpecificError{

	public VariableNotReferenceableError(int lineNumber, int varSize) {
		super(lineNumber);
	}

	@Override
	public String errorMsg() {
		return prefix() + "Its not possible to reference variables with more than 16 bits";
	}

}
