package mp2.model;

public interface Matrix {
	public Matrix transpose();
	public void switchRow(int row1, int row2) throws IllegalArgumentException ;
	public void scalarRow(int scalar, int row) throws IllegalArgumentException ;
	public void addColumn(int scalar, int row1, int row2 ) 
		throws IllegalArgumentException ;
	public void augment(Matrix augmented) throws IllegalArgumentException;
	public int get(int row, int col) throws IllegalArgumentException;
	public int rowCount();
	public int colCount();
	public Matrix getAugment(int index) throws IllegalArgumentException;
	public int determinant();
	public Matrix invert();
	public Matrix multiply(Matrix m);
	public Matrix reducedRowEchelon();
}