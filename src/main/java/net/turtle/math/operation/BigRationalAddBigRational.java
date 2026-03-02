package net.turtle.math.operation;

import net.turtle.math.core.BigRational;

public class BigRationalAddBigRational implements AddBiFunction<BigRational, BigRational, BigRational>{

  @Override
  public BigRational add(BigRational augend, BigRational addend) {
      final BigRational result;
      if (!augend.getDenominator().equals(addend.getDenominator())) {
        result =
            new BigRational(
        	    augend.getNumerator()
                    .multiply(addend.getDenominator())
                    .add(addend.getNumerator().multiply(augend.getDenominator())),
                    augend.getDenominator().multiply(addend.getDenominator()));
      } else {
        result = new BigRational(augend.getNumerator().add(addend.getNumerator()), augend.getDenominator());
      }
      return result;
    }
    
}
