import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.lang.Math;


public class BicubicSpline{

    // Matrix Variable Declaration
    double[][] data;
    int jumlahBaris;
    int jumlahKolom;

    // Read File Bicubicinput -> Jadi Matriks 16x1

    public void getInput(String filename, Matrix mInput){
        // Mendapatkan Matriks Input
        // 


        // Inisialisasi variabel dummyMatrix
        // float[][] dummyMatrix = null;

         try {
            File file = new File(filename);

            Scanner sc = new Scanner(file);

            this.jumlahBaris = 16; // Karena sudah diketahui kalau matriks berukuran 4x4 dan mau dijadikan matriks 16x1
            this.jumlahKolom = 1;

            this.data = new double[jumlahBaris][jumlahKolom]; // Inisialisasi ukuran matriks
            
            // Pembacaan file untuk melakukan parsing yang bertujuan untuk memindahkan
            // hasil inputan dari txt ke dalam bentuk format matriks dan disimpan dalam variabel dummyMatrix
   
            // Then, read and fill the matrix sampai 16x1 => i = 15
            int i = 0;
            while (i<16) {
                String[] inputValues = sc.nextLine().split(" ");
                for (int k = 0; k < inputValues.length; k++) {
                    this.data[i][0] = Double.parseDouble(inputValues[k]);
                    i++;
                }
            }

            sc.close();

        // catch bertujuan untuk menampilkan error kepada user
        } catch (FileNotFoundException e) {
            System.out.println("File "+filename+" not found in this directory.");
            System.out.println("Program will shut down. Please re-run the program");
        }

    }


    // Baca Matriks X dari File (Karena Matriks X sudah pasti)

    public Matrix makeX (Matrix mInput){
        // Membuat matriks X berdasarkan nilai di matrix BicubicInput
        
        // mInput adalah matriks 16x1 
        int nBaris = mInput.getBaris();

        Matrix matriksX = new Matrix(nBaris, nBaris);

        //Mengisi Matriks
        
        int x; int y;
        for (int i = 0; i< 16; i++){
            // Menentukan nilai x dan y
            if(i%4 == 0){
                x = 0; y = 0;
            }
            else if (i%4 == 1){
                x = 0; y = 1;
            }
            else if (i%4 == 2){
                x = 1; y = 0;
            }
            else /*i%4 == 3 */{
                x = 1; y = 1;
            }

            // Pengisian matriks X
            if (i<4){
            // Tidak ada differensiasi
            for (int j = 0; j< 16; j++){
                matriksX.setElmt(i, j, Math.pow(x,i)*Math.pow(y, j));
                }   
            }         
            else if (i>=4 && i<8){
            // Partial Derivative respect to x
            for (int j = 0; j< 16; j++){
                matriksX.setElmt(i, j, i*Math.pow(x,i-1)*Math.pow(y, j));
                }
            }
            else if (i>=8 && i<12){
            // Partial Derivative respect to y
            for (int j = 0; j< 16; j++){
                matriksX.setElmt(i, j, j*Math.pow(x,i)*Math.pow(y, j-1));
                }
            }
            else /*i>=12 && i<16 */
            {
            // Second Partial Derivative respect to xy
            for (int j = 0; j< 16; j++){
                matriksX.setElmt(i, j, i*j*Math.pow(x,i-1)*Math.pow(y, j-1));
                }
            }
        }
   
        return matriksX;

    }


    // Inverse kan Matriks X

    public Matrix inverseX (Matrix X){
        // Inverse matriks X dengan metode bebas. Disini dipakai metode Adjoin Matrix
        Matrix mXInverse = new Matrix();

        mXInverse = X.inverseWithAdjMethod();

        return mXInverse;

    }


    // Kali Input x Inverse X -> Dapet matriks 1x16 dari a_00 -> a_33

    public Matrix kaliInputdanInverseX (Matrix inverseX, Matrix input){

        Matrix hasilKali = new Matrix();

        hasilKali = Matrix.multiplyMatrix(inverseX, input);


        return hasilKali;

    }


    // Baca nilai a dan b dari Bicubicinput -> Baca angka 1 dan 2 dari baris ke 5


    public Matrix getAandB (String filename){
    // Membaca file dan mereturn array berisi a dan b untuk f(a,b)
    Matrix aandb = new Matrix();
         try {
            File file = new File(filename);

            Scanner sc = new Scanner(file);

            aandb.jumlahBaris = 1; // Karena sudah diketahui kalau hanya ada 2 nilai yaitu a dan b
            aandb.jumlahKolom = 2;

            this.data = new double[jumlahBaris][jumlahKolom]; // Inisialisasi ukuran matriks
            
            // Pembacaan file untuk melakukan parsing yang bertujuan untuk memindahkan
            // hasil inputan dari txt ke dalam bentuk format matriks dan disimpan dalam variabel dummyMatrix
   
            // Then, read and fill the matrix
            int i = 0; int count = 1;
            while (count != 5) {
                sc.nextLine();
                count++;
            }
            // Saat count = 5 alias baris nilai a,b
            String[] inputValues = sc.nextLine().split(" ");

            for (int k = 0; k < inputValues.length; k++) {
                    aandb.data[0][i] = Double.parseDouble(inputValues[i]);
                    i++;
                }

            sc.close();

        // catch bertujuan untuk menampilkan error kepada user
        } catch (FileNotFoundException e) {
            System.out.println("File "+filename+" not found in this directory.");
            System.out.println("Program will shut down. Please re-run the program");
        }


        return aandb;
    }

    // Return nilai f(a,b)
    public double interpolationAtAandB(Matrix hasilKali, Matrix aandb){
        double fab = 0;
        double a = aandb.getElmt( 0, 0); double b = aandb.getElmt( 0, 1); // Dapatkan elemen a dan b

        int idx = 0; // Untuk akses nilai a_ij di matriks hasilKali
        for (int j = 0; j<4; j++){
            for (int i = 0; i<4; i++){
                fab = fab + hasilKali.getElmt(idx, 0)*Math.pow(a,i)*Math.pow(b,j);
                idx++;

            }
        }
        return fab;
    }




}