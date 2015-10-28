package mp2;

import mp2.model.Matrix;
import mp2.model.ModularMatrix;

public class Driver {
	public static final int MODULUS = 127;

	public static void main(String[] args) {
		int[][] matrix = new int[][] {
			{999,1,687},
			{372,23,120},
			{500,90,4}
		};
		Matrix mm = new ModularMatrix(matrix);
		Matrix m = mm.invert();
		System.out.println("A = \n" + mm);
		System.out.println("A^-1 = \n" + m);
		System.out.println("AA^-1 = \n" + mm.multiply(m));
	}
}