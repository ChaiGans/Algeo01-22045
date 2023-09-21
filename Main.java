import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        // int [][] matriks = bacaMatriksDariFile("input.txt");

        // // Meminta masukan banyaknya baris pada matriks
        // Scanner scanner = new Scanner(System.in);
        // System.out.print("Input the number of rows of the matrix: ");
        // int jumlahBaris = scanner.nextInt();
        // scanner.nextLine();

        // // Meminta masukan banyaknya kolom pada matriks
        // System.out.print("Input the number of columns of the matrix: ");
        // int jumlahKolom = scanner.nextInt();
        // scanner.nextLine();

        // Matrix dummyMatrix = new Matrix();
        // dummyMatrix.bacaMatriksDariFile("input.txt");
        // dummyMatrix.printMatriks();

        Matrix dummyMatrix = new Matrix(3, 3);
        dummyMatrix.bacaMatriks();
        dummyMatrix.printMatriks();
        
        // printMatriks(matrix, jumlahBaris, jumlahKolom);

        // scanner.close();
        
    }







}