package mp2.model;

public abstract class AbstractMatrix {
	protected int[][] matrix;
	protected Matrix[] augments;
	public Matrix transpose() {

	}
	
	public void switchRow(int row1, int row2) {

	}
	
	public void augment(Matrix augmented) {

	}
	
	public int get(int row, int col) throws IllegalArgumentException {

	}
	
	public Matrix getAugment(int index) throws IllegalArgumentException {

	}
	
	public int determinant() {

	}
	
}