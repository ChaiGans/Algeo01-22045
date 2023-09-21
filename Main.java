import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        // int [][] matriks = bacaMatriksDariFile("input.txt");

        // Meminta masukan banyaknya baris pada matriks
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input the number of rows of the matrix: ");
        int jumlahBaris = scanner.nextInt();
        scanner.nextLine();

        // Meminta masukan banyaknya kolom pada matriks
        System.out.print("Input the number of columns of the matrix: ");
        int jumlahKolom = scanner.nextInt();
        scanner.nextLine();
        int[][] matrix = bacaMatriks(jumlahBaris, jumlahKolom);
        printMatriks(matrix);
        // printMatriks(matrix, jumlahBaris, jumlahKolom);

        scanner.close();
        
    }

    public static int[][] bacaMatriksDariFile(String filename) {
        // Inisialisasi variabel dummyMatrix
        int[][] dummyMatrix = null;

        try {
            File file = new File(filename);

            // Pembukaan atau pembacaan file pertama bertujuan untuk menghitung jumlah baris dan jumlah kolom inputan dari txt
            // Hal tersebut bertujuan agar dapat dilakukan inisialisasi dummyMatrix dengan panjang kolom dan baris yang fixed
            Scanner sc = new Scanner(file);

            int jumlahBaris = 0;
            int jumlahKolom = 0;

            
            while (sc.hasNextLine()) {
                jumlahBaris++;
                String[] inputValues = sc.nextLine().split(" ");
                jumlahKolom = inputValues.length;
            }

            sc.close();

            // Pembukaan atau pembacaan file yang sama kedua kalinya, untuk melakukan parsing yang bertujuan untuk memindahkan
            // hasil inputan dari txt ke dalam bentuk format matriks dan disimpan dalam variabel dummyMatrix
            sc = new Scanner(file);
            dummyMatrix = new int[jumlahBaris][jumlahKolom];

            // Then, read and fill the matrix
            int i = 0;
            while (sc.hasNextLine()) {
                String[] inputValues = sc.nextLine().split(" ");
                for (int j = 0; j < inputValues.length; j++) {
                    dummyMatrix[i][j] = Integer.parseInt(inputValues[j]);
                }
                i++;
            }

            sc.close();

        // catch bertujuan untuk menampilkan error kepada user
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return dummyMatrix;
    }

    public static int[][] bacaMatriks(int jumlahBaris, int jumlahKolom) {

        int[][] dummyMatrix;
        Scanner scanner = new Scanner(System.in);
        

        // Inisialisasi matriks
        dummyMatrix = new int[jumlahBaris][jumlahKolom+1];

        // Perulangan (Looping) untuk meminta dan mengisi isian matriks
        for (int i = 0; i < jumlahBaris; i++) {
            String[] inputValues = scanner.nextLine().split(" ");
            for (int j = 0; j < jumlahKolom+1; j++) {
                dummyMatrix[i][j] = Integer.parseInt(inputValues[j]);
            }
        }
        scanner.close();
        return dummyMatrix;
    }

    public static void printMatriks(int[][] matriks) {
        // Prosedur untuk mencetak matriks kepada user
        for (int i = 0; i < matriks.length; i++) {
            for (int j = 0; j < matriks[i].length; j++) {
                System.out.print(matriks[i][j] + " ");
            }
            System.out.println();
        }
    }

}