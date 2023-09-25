import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Matrix {
    private double[][] data; // The matrix data
    private int jumlahBaris; // Number of rows
    private int jumlahKolom; // Number of columns

    public Matrix(int rows, int columns) {
        this.jumlahBaris = rows;
        this.jumlahKolom = columns;
        this.data = new double[rows][columns];
    }

    public Matrix() {
        this.jumlahBaris = 0;
        this.jumlahKolom = 0;
        this.data = new double[0][0];
    }

    public void printMatriks() {
        // Prosedur untuk mencetak matriks kepada user
        for (int i = 0; i < this.jumlahBaris; i++) {
            for (int j = 0; j < this.jumlahKolom; j++) {
                System.out.print(this.data[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void bacaMatriks() {

        Scanner scanner = new Scanner(System.in);

        // Inisialisasi matriks
        // Matrix dummyMatrix = new Matrix(n, m);

        // Perulangan (Looping) untuk meminta dan mengisi isian matriks
        for (int i = 0; i < this.jumlahBaris; i++) {
            String[] inputValues = scanner.nextLine().split(" ");
            for (int j = 0; j < this.jumlahKolom; j++) {
                this.data[i][j] = Double.parseDouble(inputValues[j]);
            }
        }
        scanner.close();
    }

    public void bacaMatriksDariFile(String filename) {
        // Inisialisasi variabel dummyMatrix
        // float[][] dummyMatrix = null;

        try {
            File file = new File(filename);

            // Pembukaan atau pembacaan file pertama bertujuan untuk menghitung jumlah baris dan jumlah kolom inputan dari txt
            // Hal tersebut bertujuan agar dapat dilakukan inisialisasi dummyMatrix dengan panjang kolom dan baris yang fixed
            Scanner sc = new Scanner(file);

            // int jumlahBaris = 0;
            // int jumlahKolom = 0;
            this.jumlahBaris = 0;
            this.jumlahKolom = 0;

            
            while (sc.hasNextLine()) {
                this.jumlahBaris++;
                String[] inputValues = sc.nextLine().split(" ");
                this.jumlahKolom = inputValues.length;
            }

            this.data = new double[jumlahBaris][jumlahKolom];

            sc.close();

            // Pembukaan atau pembacaan file yang sama kedua kalinya, untuk melakukan parsing yang bertujuan untuk memindahkan
            // hasil inputan dari txt ke dalam bentuk format matriks dan disimpan dalam variabel dummyMatrix
            sc = new Scanner(file);
            // dummyMatrix = new float[jumlahBaris][jumlahKolom];

            // Then, read and fill the matrix
            int i = 0;
            while (sc.hasNextLine()) {
                String[] inputValues = sc.nextLine().split(" ");
                for (int j = 0; j < inputValues.length; j++) {
                    this.data[i][j] = Double.parseDouble(inputValues[j]);
                }
                i++;
            }

            sc.close();

        // catch bertujuan untuk menampilkan error kepada user
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public double getElmt(int i, int j) {
        return this.data[i][j];
    }

    public void setElmt(int i, int j, double val) {
        this.data[i][j] = val;
    }

    public int getBaris(){
        return this.jumlahBaris;
    }

    public int getKolom(){
        return this.jumlahKolom;
    }

    public Matrix changeRow(int i1, int i2){
        Matrix m = new Matrix(this.jumlahBaris,this.jumlahKolom);

        int i,j;
        for(i = 0; i < this.jumlahBaris ; i++){
            for (j = 0; j < this.jumlahKolom ; j++){
                if(i == i1 ){
                    m.setElmt(i, j, this.getElmt(i2, j));
                }else if(i == i2){
                    m.setElmt(i, j, this.getElmt(i1, j));
                }else{
                    m.setElmt(i, j, this.getElmt(i, j));
                }
            }
        }

        return m;
    }

    public Matrix subMatrix(int a, int b) {    
        int i, j, k, l;
        k = 0;
        l = 0;
        Matrix dummyMatrix = new Matrix(this.jumlahBaris-1, this.jumlahKolom-1);

        for (i=0; i<this.jumlahBaris; i++) {
            for (j=0; j<this.jumlahKolom; j++) {
                if (i!=a && j!=b) {
                    dummyMatrix.data[k][l] = this.data[i][j];
                    l += 1;
                } 
            }
            if (l == dummyMatrix.jumlahKolom) {
                k+=1;
                l=0;
            }
        }
        return dummyMatrix;
    }

    public double determinantWithCofExpansion() {
        int j;
        double sum = 0;

        Matrix dummyMatrix = new Matrix(this.jumlahBaris, this.jumlahKolom);
        int i;
        for (i=0; i<dummyMatrix.jumlahBaris; i++) {
            for (j=0; j<dummyMatrix.jumlahKolom; j++) {
                dummyMatrix.data[i][j] = this.data[i][j];
            }
        }

        /* Menghitung nilai determinan m */
        if (this.jumlahBaris == 2 && this.jumlahKolom == 2) {
            return dummyMatrix.data[0][0] * dummyMatrix.data[1][1] - dummyMatrix.data[1][0] * dummyMatrix.data[0][1];
        } else {
            for (j = 0; j < this.jumlahKolom; j++) {
                if (j % 2 == 0) {
                    sum += dummyMatrix.data[0][j] * this.subMatrix(0, j).determinantWithCofExpansion();
                } else {
                    sum -= dummyMatrix.data[0][j] * this.subMatrix(0, j).determinantWithCofExpansion();
                }
            }
        }
        return sum;
    }

    public Matrix makeItSquare () {
        Matrix dummyMatrix = new Matrix(this.jumlahBaris, this.jumlahKolom-1);
        int i, j;
        for (i=0; i<dummyMatrix.jumlahBaris; i++) {
            for (j=0; j<dummyMatrix.jumlahKolom; j++) {
                dummyMatrix.setElmt(i, j, this.getElmt(i, j));
            }
        }

        return dummyMatrix;
    }
    public Matrix copyMatrix() {
        Matrix mKeluar = new Matrix(this.jumlahBaris, this.jumlahKolom);
        int i, j;
        mKeluar.jumlahBaris = this.jumlahBaris;
        mKeluar.jumlahKolom = this.jumlahKolom;
        for (i = 0; i < this.jumlahBaris; i++) {
            for (j = 0; j < this.jumlahKolom; j++) {
                mKeluar.data[i][j] = this.data[i][j];
            }
        }
        return mKeluar;
    }
}