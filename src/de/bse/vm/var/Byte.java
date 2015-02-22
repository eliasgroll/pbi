package de.bse.vm.var;

import de.bse.vm.var.Bit;

public class Byte extends ConglomerateVariable {

  public Byte(Bit bit0, Bit bit1, Bit bit2, Bit bit3, Bit bit4, Bit bit5,
      Bit bit6, Bit bit7) {
    super(new Bit[] { bit0, bit1, bit2, bit3, bit4, bit5, bit6, bit7 });
  }

  public Byte() {
    super(new Bit[] { new Bit(0), new Bit(0), new Bit(0), new Bit(0),
        new Bit(0), new Bit(0), new Bit(0), new Bit(0) });
  }

}
