package mp2.model;

import mp2.Driver;

/**
 * This class handles the modular operations in a matrix
 * @author Austin Fernandez
 */
public class ModularMatrix extends AbstractMatrix {
	/**
	 * sets all values modulo modulus in Driver
	 * @param grid contents of matrix
	 */
	public ModularMatrix(int[][] grid) {
		super(grid);
		for(int i = 0; i < matrix.length; i++) {
			for( int j = 0; j < matrix[0].length; j++ ) {
				matrix[i][j] = ModArith.modulo(matrix[i][j],Driver.MODULUS);
			}
		}
	}

	/**
	 * returns a ModularMatrix given the grid
	 * @param grid grid with matrix contents
	 * @return ModularMatrix given the grid
	 */
	protected Matrix ofType(int[][] grid) {
		return new ModularMatrix(grid);
	}

	/**
	 * multiplies a row by a scalar
	 * @param scalar scalar to multiply with
	 * @param row to multiply scalar with
	 * @throws IllegalArgumentException if row out of bounds
	 */
	public void scalarRow(int scalar, int row) 
		throws IllegalArgumentException {
		if( row < 0 || row >= rowCount() ) {
			throw new IllegalArgumentException("Row out of bounds");
		} else {
			for(int i = 0; i < colCount(); i++ ) {
				matrix[row][i] = ModArith.modulo(matrix[row][i] * scalar
					,Driver.MODULUS);
			}

			for( Matrix m: augments ) {
				m.scalarRow(scalar, row);
			}
		}
	}

	/**
	 * adds a multiple of a column to another column
	 * @param scalar scalar for row to add
	 * @param row1 row to multiply by zero-indexed
	 * @param row2 row to replace zero-indexed
	 * @throws IllegalArgumentException if row out of bounds
	 */
	public void addColumn(int scalar, int row1, int row2 ) 
		throws IllegalArgumentException {
		if( row1 < 0 || row1 >= rowCount() || row2 < 0 || row2 >= rowCount() 
			|| row1 == row2) {
			throw new IllegalArgumentException("Row out of bounds");
		} else {
			for(int i = 0; i < colCount(); i++ ) {
				matrix[row2][i] = ModArith.modulo(matrix[row1][i] * scalar 
													+ matrix[row2][i]
													,Driver.MODULUS);
			}

			for( Matrix m: augments ) {
				m.addColumn(scalar, row1, row2);
			}
		}	
	}

	/**
	 * gets the inverse of this matrix
	 * @return null if singular, inverse otherwise
	 */
	public Matrix invert() {
		if( rowCount() != colCount() || determinant() == 0 ) {
			return null;
		} else {
			try {
				int[][] invert = new int[colCount()][rowCount()];
				int det = determinant();
				int detInv = ModArith.modInverse(det,Driver.MODULUS);
				
				for( int i = 0; i < rowCount(); i++ ) {
					for( int j = 0; j < colCount(); j++ ) {
						invert[j][i] = ModArith
										.modulo(((i + j) % 2 == 0 ? 1 : -1) 
													* CofactorExpansion
													.det(CofactorExpansion
														.minor(matrix,i,j))
													,Driver.MODULUS);
						invert[j][i] = ModArith.modulo(invert[j][i] * detInv
														,Driver.MODULUS);
					}
				}
				Matrix m = new ModularMatrix(invert);
				return m;
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * returns the product of this matrix with another matrix
	 * @param m multiplier matrix
	 * @return product of the matrices or null if cannot perform operation
	 */
	public Matrix multiply(Matrix m) {
		if( colCount() != m.rowCount() ) {
			return null;
		} else {
			int[][] newMatrix = new int[rowCount()][m.colCount()];
			for( int i = 0; i < rowCount(); i++ ) {
				for(int j = 0; j < m.colCount(); j++ ) {
					newMatrix[i][j] = 0;
					for(int k = 0; k < colCount(); k++ ) {
						newMatrix[i][j] += get(i,k) * m.get(k,j);
					}
				}
			}
			return new ModularMatrix(newMatrix);
		}
	}
	
	/**
	 * returns the reduced row echelon form of this matrix
	 * @return reduced row echelon form of this matrix
	 */
	public Matrix reducedRowEchelon() {
		int[][] grid = new int[rowCount()][colCount()];
		for( int i = 0; i < rowCount(); i++ ) {
			for( int j = 0; j < colCount(); j++ ) {
				grid[i][j] = matrix[i][j];
			}
		}
		Matrix dummy = new ModularMatrix(grid);
		for( Matrix m: augments ) {
			dummy.augment(m);
		}

		// System.out.println(dummy);

		int col = 0;
		//for each row
		for( int i = 0; i < rowCount(); i++ ) {
			//initialize row to select
			int rowSwitch = i;
			boolean nonzero = false;
			//puts a 1 in the top of the remaining matrix
			while(!nonzero && col < colCount()) {
				nonzero = dummy.get(i,col) != 0;

				//for all remaining rows
				for( int j = i + 1; j < rowCount(); j++ ) {
					nonzero = nonzero || dummy.get(j,col) != 0;
					//if current row to switch with is a 1 and 
					if( dummy.get(rowSwitch,col) == 0 && dummy.get(j,col) != 0
						|| dummy.get(rowSwitch,col) != 1 
						&& dummy.get(j,col) == 1) {
						rowSwitch = j;
					}
				}

				//if nonzero column
				if( nonzero ) {
					if(dummy.get(rowSwitch,col) == 1 && i != rowSwitch) {
						dummy.switchRow(i,rowSwitch);	
						// System.out.println("R" + (i + 1) + "<-> R" + (rowSwitch + 1) + "\n" 
						// 					+ dummy);
					} else if( dummy.get(i,col) != 1 ) {
						int temp = ModArith.modInverse(dummy.get(i,col)
														,Driver.MODULUS);
						dummy.scalarRow(temp,i);
						// System.out.println(temp + "R" + (i + 1) 
						// 					+ "-> R" + (rowSwitch + 1) + "\n" 
						// 					+ dummy);
					}
					
					for(int j = i + 1; j < rowCount(); j++ ) {
						if( dummy.get(j,col) != 0 ) {
							int temp = dummy.get(j,col);
							dummy.addColumn(-temp,i,j);
							// System.out.println(-temp + "R" + (i + 1) 
							// 					+ " + R" + (j + 1) + "-> R" + (j + 1) + "\n" 
							// 					+ dummy);
						}
					}
				} 

				col++;
			}
		}

		for( int i = 0; i < dummy.rowCount(); i++ ) {
			int leader = 0;
			for( leader = 0; leader < dummy.colCount(); leader++ ) {
				if( dummy.get(i,leader) == 1 ) {
					break;
				}
			}
			if( leader < dummy.colCount()) {
				for( int j = i - 1; j >= 0; j-- ) {
					int temp = dummy.get(j,leader);
					dummy.addColumn(-temp,i,j);
					// System.out.println(-temp + "R" + (i + 1) 
					// 							+ " + R" + (j + 1) + "-> R" + (j + 1) + "\n" 
					// 							+ dummy);
				}
			}
		}

		return dummy;
	}
}