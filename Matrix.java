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
                System.out.printf("%f \t", this.data[i][j]);
            }
            System.out.println();
        }
    }

    public void bacaMatriks(Scanner scanner) {
        // Perulangan (Looping) untuk meminta dan mengisi isian matriks
        for (int i = 0; i < this.jumlahBaris; i++) {
            String[] inputValues = scanner.nextLine().split(" ");
            for (int j = 0; j < this.jumlahKolom; j++) {
                this.data[i][j] = Double.parseDouble(inputValues[j]);
            }
        }
    }

    public void bacaMatriksDariFile(String filename) {
        // Inisialisasi variabel dummyMatrix
        // float[][] dummyMatrix = null;

        try {
            File file = new File(filename);

            // Pembukaan atau pembacaan file pertama bertujuan untuk menghitung jumlah baris dan jumlah kolom inputan dari txt
            // Hal tersebut bertujuan agar dapat dilakukan inisialisasi dummyMatrix dengan panjang kolom dan baris yang fixed
            Scanner sc = new Scanner(file);

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
            System.out.println("File "+filename+" not found in this directory.");
            System.out.println("Program will shut down. Please re-run the program");
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
        Matrix dummyMatrix = new Matrix(this.jumlahBaris, this.jumlahKolom);
        int i, j;
        dummyMatrix.jumlahBaris = this.jumlahBaris;
        dummyMatrix.jumlahKolom = this.jumlahKolom;
        for (i = 0; i < this.jumlahBaris; i++) {
            for (j = 0; j < this.jumlahKolom; j++) {
                dummyMatrix.data[i][j] = this.data[i][j];
            }
        }
        return dummyMatrix;
    }

    public static Matrix multiplyMatrix (Matrix m1, Matrix m2) {
        Matrix dummyMatrix = new Matrix(m1.getBaris(), m2.getKolom());
        int i, j, k, sum;
        for (i=0; i<m1.getBaris(); i++) {
            for (j=0; j<m2.getKolom(); j++) {
                sum = 0;
                for (k=0; k<m1.getKolom(); k++) {
                    sum += m1.getElmt(i, k) * m2.getElmt(k, j);
                }
                dummyMatrix.setElmt(i, j, sum);
            }
        }
        return dummyMatrix;
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

    public void pchangeRow(int row1, int row2){
        
        for (int j = 0; j < this.jumlahKolom; j++) {
            double temp = this.data[row1][j];
            this.data[row1][j] = this.data[row2][j];
            this.data[row2][j] = temp;
        }
    }

    public Matrix changeCol(int col1, int col2) {
        Matrix m = new Matrix(this.jumlahBaris, this.jumlahKolom);
        for (int i = 0; i < this.jumlahBaris; i++) {
            for (int j = 0; j < this.jumlahKolom; j++) {
                if (j == col1) {
                    m.data[i][j] = this.data[i][col2];
                } else if (j == col2) {
                    m.data[i][j] = this.data[i][col1];
                } else {
                    m.data[i][j] = this.data[i][j];
                }
            }
        }
        return m;
    }

    public boolean isGotDeterminant() {
        int i, j, countRow, countCol;
        for (i=0; i<this.getBaris(); i++) {
            countRow = 0;
            countCol = 0;
            for (j=0; j<this.getKolom(); j++) {
                if (this.getElmt(i, j)  == 0) {
                    countRow += 1;
                }
                if (this.getElmt(j, i) == 0) {
                    countCol += 1;
                }
            }
            if (countRow == this.getKolom() || countCol == this.getBaris()) {
                return false;
            }
        }
        return true;
    }

    public Matrix subMatrix(int a, int b) {    
        int i, j, k, l;
        k = 0;
        l = 0;
        Matrix dummyMatrix = new Matrix(this.jumlahBaris-1, this.jumlahKolom-1);

        for (i = 0; i < this.jumlahBaris; i++) {
            if (i != a) {
                for (j = 0; j < this.jumlahKolom; j++) {
                    if (j != b) { 
                        dummyMatrix.data[k][l] = this.data[i][j];
                        l += 1;
                    } 
                }
                k += 1;
                l = 0;
            }
        }
        return dummyMatrix;
    }

    public double determinantWithCofExpansion() {
        int j;
        double sum = 0;

        Matrix dummyMatrix = this.copyMatrix();

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

    public void SPLwithCramerMethod () {
        int i;
        Matrix dummyMatrix = new Matrix ();
        dummyMatrix = this.copyMatrix();
        double save = dummyMatrix.makeItSquare().determinantWithCofExpansion();
        for (i = 0; i < this.getKolom()-1; i++) {
            dummyMatrix = dummyMatrix.changeCol(i, dummyMatrix.getKolom()-1);
            double determinantX = dummyMatrix.makeItSquare().determinantWithCofExpansion();
            System.out.printf("X%d bernilai %.4f\n", i, determinantX/save);
            dummyMatrix = dummyMatrix.changeCol(i, dummyMatrix.getKolom()-1);
        }
    }

    public Matrix gauss(){
        Matrix dummyMatrix = new Matrix();
        dummyMatrix = this.copyMatrix();
        int baris = dummyMatrix.getBaris();
        int kolom = dummyMatrix.getKolom();

        int a = 0;
        boolean tidakNol = false;
        for (int i = 0; i < baris ; i++){
            tidakNol = false;
            while(tidakNol == false && i+a <kolom){
                if(dummyMatrix.getElmt(i, i+a) == 0){         
                    for(int j = i+1; j< baris; j++){
                        if(dummyMatrix.getElmt(j, i+a) != 0){
                            dummyMatrix= dummyMatrix.changeRow(i, j);
                            tidakNol = true;
                        }
                    } 
                    if(tidakNol == false){
                        a += 1;
                    }
                 }else{
                    tidakNol = true;
                 }
            }

            if (i + a < kolom){
                double pembagi = dummyMatrix.getElmt(i, i+a);
                for( int k = (i +a) ; k < kolom; k++){
                    if(dummyMatrix.getElmt(i,k) != 0){
                        dummyMatrix.setElmt(i, k,(double)dummyMatrix.getElmt(i, k)/ (double) pembagi);
                    }
                    
                }

                for (int j = i +1 ; j < baris ; j ++){
                    double faktorPengali = dummyMatrix.getElmt(j, i+a);
                    for (int k = i +a; k < kolom ; k++){
                        double hasilTemp = dummyMatrix.getElmt(j, k) - (faktorPengali * dummyMatrix.getElmt(i,k));
                        dummyMatrix.setElmt(j, k, hasilTemp);
                    }
                }
            }
        }   
        return dummyMatrix;
    }

    public Matrix gaussJordan(){
        Matrix dummyMatrix = new Matrix();
        dummyMatrix = this.copyMatrix();
        int baris = dummyMatrix.getBaris();
        int kolom = dummyMatrix.getKolom();

        int a = 0;
        boolean tidakNol = false;
        for (int i = 0; i < baris ; i++){
            tidakNol = false;
            while(tidakNol == false && i+a <kolom){
                if(dummyMatrix.getElmt(i, i+a) == 0){         
                    for(int j = i+1; j< baris; j++){
                        if(dummyMatrix.getElmt(j, i+a) != 0){
                            dummyMatrix= dummyMatrix.changeRow(i, j);
                            tidakNol = true;
                        }
                    } 
                    if(tidakNol == false){
                        a += 1;
                    }
                    
                } else{
                    tidakNol = true;
                }

            }
            if (i + a < kolom){
                double pembagi = dummyMatrix.getElmt(i, i+a);
                for( int k = (i +a) ; k < kolom; k++){
                    if(dummyMatrix.getElmt(i,k) != 0D){
                        dummyMatrix.setElmt(i, k,(double)dummyMatrix.getElmt(i, k)/ (double) pembagi);
                    }
                    
                }

                for (int j = 0 ; j < baris ; j ++){
                    if(j != i){
                        double faktorPengali = dummyMatrix.getElmt(j, i+a);
                        for (int k = i +a; k < kolom ; k++){
                            double hasilTemp = dummyMatrix.getElmt(j, k) - (faktorPengali * dummyMatrix.getElmt(i,k));
                            dummyMatrix.setElmt(j, k, hasilTemp);
                        }
                    }
                }
            }
        }   

        return dummyMatrix;
    }

    public Matrix adjoinMatrix () {
        Matrix dummyMatrix = this.copyMatrix();
        int i, j;
        for (i=0; i<dummyMatrix.getBaris(); i++) {
            for (j=0; j<dummyMatrix.getKolom(); j++) {
                if ((i+j)%2 == 0) {
                    dummyMatrix.setElmt(i, j, subMatrix(i, j).determinantWithCofExpansion());
                } else {
                    dummyMatrix.setElmt(i, j, -1*subMatrix(i, j).determinantWithCofExpansion());
                }
            }
        }

        Matrix transposedMatrix = dummyMatrix.copyMatrix();
        for (i=0; i<dummyMatrix.getBaris(); i++) {
            for (j=0; j<dummyMatrix.getKolom(); j++) {
                transposedMatrix.setElmt(i, j, dummyMatrix.getElmt(j, i));
            }
        }

        return transposedMatrix;
    }

    public Matrix inverseWithAdjMethod () {
        Matrix dummyMatrix = this.copyMatrix();
        int i, j;
        if (dummyMatrix.determinantWithCofExpansion() == 0) {
            System.out.println("This matrix does not have inverse.");
        } else {
            double saveDeterminant = dummyMatrix.determinantWithCofExpansion();
            dummyMatrix = dummyMatrix.adjoinMatrix();
            for (i=0; i<dummyMatrix.getBaris(); i++) {
                for (j=0; j<dummyMatrix.getKolom(); j++) {
                    dummyMatrix.setElmt(i, j, 1/saveDeterminant * dummyMatrix.getElmt(i, j));
                }
            }
        }
        return dummyMatrix;
    }

    public void SPLwithInverseMethod() {
        Matrix dummyMatrix = this.copyMatrix();
        Matrix A = dummyMatrix.makeItSquare().inverseWithAdjMethod();
        A.printMatriks();
        Matrix B = new Matrix(dummyMatrix.getBaris(), 1);

        for (int i = 0; i < dummyMatrix.getBaris(); i++) {
            B.setElmt(i, 0, dummyMatrix.getElmt(i, dummyMatrix.getKolom() - 1));
        }
        B.printMatriks();

        Matrix solution = multiplyMatrix(A, B);
        solution.printMatriks();

        for (int i = 0; i < solution.getBaris(); i++) {
            System.out.printf("X%d bernilai %.4f\n", i + 1, solution.getElmt(i, 0));
        }
    }


    

    // public void operasiBarisElementer() {
    //     if (this.isGotDeterminant()) {
    //         int i, j, l;
    //         int k;

    //         k=1;

    //         for (i = 0; i<this.getKolom()-1; i++) {
    //             for (j = 1+i; j<this.getBaris(); j++) {
    //                 if (this.getElmt(j, i) != 0) {
    //                     double rasioPembuatNol = this.getElmt(j, i) / this.getElmt(j-k, i);

    //                     for (l = 0; l<this.getKolom(); l++) {
    //                         this.setElmt(j, l, getElmt(j, l)-rasioPembuatNol*this.getElmt((j-k), l));
    //                     }
    //                     this.printMatriks();
    //                     System.out.printf("\n");
    //                     k+=1;
    //                 } else {

    //                 }
    //             }
    //             k=1;
    //             this.printMatriks();
    //             System.out.printf("\n");
    //         }
    //     } else {
    //         System.out.println("This matrix do not have any determinant.");
    //     }
    // }
}