package mp2.model;

public interface Matrix {
	public Matrix transpose();
	public void switchRow(int row1, int row2);
	public void scalarRow(int scalar, int row);
	public void addColumn(int scalar, int row1, int row2 );
	public void augment(Matrix augmented);
	public int get(int row, int col) throws IllegalArgumentException;
	public Matrix getAugment(int index) throws IllegalArgumentException;
	public int determinant();
	public Matrix invert();
	public Matrix multiply(Matrix m);
	public Matrix reducedRowEchelon();
}