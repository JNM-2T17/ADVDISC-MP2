package mp2.model;

import java.util.ArrayList;

import mp2.Driver;

public abstract class AbstractMatrix implements Matrix {
	protected int[][] matrix;
	protected ArrayList<Matrix> augments;

	public AbstractMatrix(int[][] matrix) {
		augments = new ArrayList<Matrix>();
		this.matrix = matrix;
	}

	public Matrix transpose() {
		int[][] grid = new int[colCount()][rowCount()];
		for( int i = 0; i < rowCount(); i++ ) {
			for( int j = 0; j < colCount(); i++ ) {
				grid[j][i] = matrix[i][j];
			}
		}
		return ofType(grid);
	}

	protected abstract Matrix ofType(int[][] grid);

	public void switchRow(int row1, int row2) throws IllegalArgumentException {
		if( row1 < 0 || row1 >= rowCount() || row2 < 0 
			|| row2 >= rowCount() ) {
			throw new IllegalArgumentException("Index out of bounds");
		}

		for( int i = 0; i < colCount(); i++ ) {
			int temp = matrix[row1][i];
			matrix[row1][i] = matrix[row2][i];
			matrix[row2][i] = temp;
		}

		for( Matrix m: augments ) {
			m.switchRow(row1,row2);
		}
	}

	public void augment(Matrix augmented) throws IllegalArgumentException {
		if( augmented.rowCount() != rowCount() ) {
			throw new IllegalArgumentException("Illegal augmentation");
		}
		augments.add(augmented);
	}

	public int rowCount() {
		return matrix.length;
	}

	public int colCount() {
		return matrix.length == 0 ? 0 : matrix[0].length;
	}


	public int get(int row, int col) throws IllegalArgumentException {
		if( row < 0 || row >= rowCount() || col < 0 
			|| col >= colCount() ) {
			throw new IllegalArgumentException("Index out of bounds");
		} else {
			return matrix[row][col];
		}
	}

	public Matrix getAugment(int index) throws IllegalArgumentException {
		if( index < 0 || index >= augments.size() ) {
			throw new IllegalArgumentException("Index out of bounds");
		} else {
			return augments.get(index);
		}
	}

	public String toString() {
		String ret = "";
		for( int i = 0; i < rowCount(); i++) {
			for( int j = 0; j < colCount(); j++ ) {
				ret += matrix[i][j] + "\t";
			}  
			ret += "\n";
		}
		return ret;
	}
}