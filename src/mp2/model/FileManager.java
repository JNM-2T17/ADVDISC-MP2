package mp2.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * handles file input/output
 * @author Austin Fernandez
 */
public class FileManager {
	/**
	 * writes a text file
	 * @param text text to save
	 * @param filename file to save to
	 */
	public static void writeText(String text,String filename) {
		
		try(PrintWriter pw = new PrintWriter(
								new BufferedWriter(
									new FileWriter(new File(filename))))) {
			String[] parts = text.split("\n");
			for( int i = 0; i < parts.length; i++) {
				if( i < parts.length - 1) {
					pw.println(parts[i]);
				} else {
					pw.print(parts[i]);
				}
			}
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}

	/**
	 * writes a cipher to a binary file
	 * @param m matrix to write
	 * @param filename file ot write to
	 */
	public static void writeCipher(Matrix m, String filename) {
		
		try (DataOutputStream dos = new DataOutputStream(
										new FileOutputStream(
											new File(filename)))) {
			dos.writeInt(m.rowCount());
			dos.writeInt(m.colCount());
			for( int i = 0; i < m.rowCount(); i++ ) {
				for( int j = 0; j < m.colCount(); j++ ) {
					dos.writeInt(m.get(i,j));
				}
			}
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}

	/**
	 * reads a text from a file
	 * @param filename file to save to
	 * @return text read from file
	 */
	public static String readText(String filename) {
		try (BufferedReader br = new BufferedReader(
									new FileReader(new File(filename)))) {
			String ret = "";
			String temp;
			do {
				temp = br.readLine();
				if( temp != null ) {
					ret += (ret.length() == 0 ? "" : "\n") + temp;
				}
			} while(temp != null );
			return ret;
		} catch(IOException ioe) {
			ioe.printStackTrace();
			return null;
		}
	}

	/**
	 * reads a matrix from a file
	 * @param filename file to save to
	 * @return matrix read from file
	 */
	public static Matrix readCipher(String filename) {
		try( DataInputStream dis = new DataInputStream(
									new FileInputStream(new File(filename)))) {
			int row = dis.readInt();
			int col = dis.readInt();
			int[][] matrix = new int[row][col];

			for( int i = 0; i < row; i++ ) {
				for( int j = 0; j < col; j++ ) {
					matrix[i][j] = dis.readInt();
				}
			}
			return new ModularMatrix(matrix);
		} catch(IOException ioe) {
			ioe.printStackTrace();
			return null;
		}
	}
}