package bx.util;

import java.math.BigDecimal;

public class DoubleValueArith {
    
    private static final int DEFAULT_DIV_SCALE = 10;

    public static double add(double d1, double d2) {
		BigDecimal db1 = new BigDecimal(d1);
		BigDecimal db2 = new BigDecimal(d2);
		return db1.add(db2).doubleValue();
	}

	public static double subtract(double d1, double d2) {
		BigDecimal db1 = new BigDecimal(d1);
		BigDecimal db2 = new BigDecimal(d2);
		return db1.subtract(db2).doubleValue();
	}

	public static double multiply(double d1, double d2) {
		BigDecimal db1 = new BigDecimal(d1);
		BigDecimal db2 = new BigDecimal(d2);
		return db1.multiply(db2).doubleValue();
	}

	public static double divide(double d1, double d2) {
		BigDecimal db1 = new BigDecimal(d1);
		BigDecimal db2 = new BigDecimal(d2);
		return db1.divide(db2, DEFAULT_DIV_SCALE, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public static double divide(double d1, double d2, int scale) {
		BigDecimal db1 = new BigDecimal(d1);
		BigDecimal db2 = new BigDecimal(d2);
		return db1.divide(db2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public static double remainder(double d1, double d2) {
		BigDecimal db1 = new BigDecimal(d1);
		BigDecimal db2 = new BigDecimal(d2);
		return db1.remainder(db2).doubleValue();
	}

}
