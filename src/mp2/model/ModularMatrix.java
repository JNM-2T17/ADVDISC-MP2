package mp2.model;

import mp2.Driver;

public class ModularMatrix extends AbstractMatrix {
	public ModularMatrix(int[][] grid) {
		super(grid);
		for(int i = 0; i < matrix.length; i++) {
			for( int j = 0; j < matrix[0].length; j++ ) {
				matrix[i][j] = ModArith.modulo(matrix[i][j],Driver.MODULUS);
			}
		}
	}

	protected Matrix ofType(int[][] grid) {
		return new ModularMatrix(grid);
	}

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

	public void addColumn(int scalar, int row1, int row2 ) 
		throws IllegalArgumentException {
		if( row1 < 0 || row1 >= rowCount() || row2 < 0 || row2 >= rowCount() ) {
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

	public int determinant() {
		try {
			return CofactorExpansion.det(matrix);
		} catch( Exception e ) {
			e.printStackTrace();
		}
		return 0;
	}

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
	
	public Matrix reducedRowEchelon() {
		int[][] grid = new int[rowCount()][colCount()];
		for( int i = 0; i < rowCount(); i++ ) {
			for( int j = 0; j < colCount(); j++ ) {
				grid[i][j] = matrix[i][j];
				System.out.print("" + grid[i][j]);
			}
			System.out.println();
		}
		Matrix dummy = new ModularMatrix(grid);
		for( Matrix m: augments ) {
			dummy.augment(m);
		}

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

				if( nonzero ) {
					if(dummy.get(rowSwitch,col) == 1 && i != rowSwitch) {
						dummy.switchRow(i,rowSwitch);	
						System.out.println("R" + i + "<-> R" + rowSwitch + "\n" 
											+ dummy);
					} else if( dummy.get(i,col) != 1 ) {
						int temp = ModArith.modInverse(dummy.get(i,col)
														,Driver.MODULUS);
						dummy.scalarRow(temp,i);
						System.out.println(temp + "R" + i 
											+ "-> R" + rowSwitch + "\n" 
											+ dummy);
					}
					
					for(int j = i + 1; j < rowCount(); j++ ) {
						if( dummy.get(j,col) != 0 ) {
							int temp = dummy.get(j,col);
							dummy.addColumn(-temp,i,j);
							System.out.println(-temp + "R" + i 
												+ " + R" + j + "-> R" + j + "\n" 
												+ dummy);
						}
					}
				} 

				col++;
			}
		}

		return dummy;
	}
}