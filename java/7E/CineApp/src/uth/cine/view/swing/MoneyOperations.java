package uth.cine.view.swing;

import java.math.BigDecimal;
import java.math.MathContext;

public final class MoneyOperations {
	
	private final static int NUMBER_OF_DECIMALS = 2;
	
	private MoneyOperations(){}

	public final static BigDecimal getDecimalReduction(BigDecimal money){
		
		return money.setScale(NUMBER_OF_DECIMALS, BigDecimal.ROUND_HALF_UP);
		
	}

    public final static BigDecimal getDecimalReduction(double money){

		return getDecimalReduction(new BigDecimal(money));

	}

    public final static BigDecimal getDecimalReduction(double val, int pos){

        return new BigDecimal(Math.round(val * Math.pow(10, pos)) / Math.pow(10, pos)).round(new MathContext(pos));
	}
	
	public final static BigDecimal getPriceWithVat(BigDecimal value, BigDecimal vat) {
		
		BigDecimal valueVat = (value.multiply(vat)).divide(new BigDecimal(100));
		
		return value.add(valueVat);
		
	}
	
}
