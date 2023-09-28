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

    public boolean isNoSolution(int i){
        for (int j = 0; j < this.jumlahKolom; j++){
            if(j != this.jumlahKolom -1){
                if(this.data[i][j] != 0){
                    return false;
                }
            }else{
                if(this.data[i][j] == 0){
                    return false;
                }
            }
            
        }
        return true;
    }

    public boolean isAllZero(int i){
        for (int j = 0; j < this.jumlahKolom; j++){
            if(this.data[i][j] != 0){
                return false;
            }
        }
        return true;
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
        if (this.getBaris() == this.getKolom()) {
            return true;
        }
        return false;
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

    // Determinant
    public double determinantWithCofExpansion() {
        if (this.isGotDeterminant()) {
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
        } else {
            return Double.NaN;
        }
    }

    public double determinantWithReduksiBaris() {
        if (this.isGotDeterminant()) {
            Matrix dummyMatrix = new Matrix();
            dummyMatrix = this.copyMatrix();
            int baris = dummyMatrix.getBaris();
            int kolom = dummyMatrix.getKolom();

            
            int tukar = 0;
            boolean tidakNol = false;
            for (int i = 0; i < baris ; i++){
                tidakNol = false;
                // untuk cek apakah element i,i == 0
                while(tidakNol == false && i<kolom){
                    if(dummyMatrix.getElmt(i, i) == 0){         
                        for(int j = i+1; j< baris; j++){
                            if(dummyMatrix.getElmt(j, i) != 0){
                                dummyMatrix = dummyMatrix.changeRow(i, j);
                                tukar += 1;
                                tidakNol = true;
                                break;
                            }
                        } 
                        
                       if(i == kolom -1){
                         break;
                       }
                    }else{
                        tidakNol = true;
                    }
                }

                if (tidakNol == true){

                    for (int j = 1+i; j<this.getBaris(); j++) {
                        
                        double rasioPembuatNol = dummyMatrix.getElmt(j, i) / dummyMatrix.getElmt(i, i);

                        for (int l = 0; l<this.getKolom(); l++) {
                            dummyMatrix.setElmt(j, l, dummyMatrix.getElmt(j, l)-rasioPembuatNol*dummyMatrix.getElmt(i, l));
                        }
                        dummyMatrix.printMatriks();
                        System.out.printf("\n");
                            
                        
                    }
                }
            }
            
            double determinan = Math.pow(-1, tukar);
            for (int i = 0; i < baris; i++){
                determinan *= dummyMatrix.getElmt(i, i);
            }
            if(determinan == -0D){
                determinan = 0;
            }
            
            return determinan;

        } else {
            return Double.NaN;
        }
    }

    // Sistem Persamaan Linear
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



    // INVERSE MATRIX METHOD
    public Matrix inverseWithIdentity () {
  
        Matrix augmentedMatrix = new Matrix(this.getBaris(), this.getKolom()*2);
        int i, j;
        // Masukin matrix bersangkutan ke dalam augmentedMatrix
        for (i = 0; i < this.getBaris(); i++) {
            for (j = 0; j<this.getKolom(); j++) {
                augmentedMatrix.setElmt(i, j, this.getElmt(i, j));
            }
        }

        // Masukin matrix identitas ke bagian belakang augmentedMatrix
        int k, l;
        k = 0;
        l = augmentedMatrix.getKolom() / 2;
        for (i = 0; i < augmentedMatrix.getBaris(); i++) {
            for (j = augmentedMatrix.getKolom() / 2; j<augmentedMatrix.getKolom(); j++) {
                if (i == k && j ==l) {
                    augmentedMatrix.setElmt(i, j, 1);
                    k+=1;
                    l+=1;
                } else {
                    augmentedMatrix.setElmt(i, j, 0);
                }
            }
        }

        Matrix IAinverse = augmentedMatrix.gaussJordan();

        boolean isIdentity = true;
        for (i = 0; i < IAinverse.getBaris(); i++) {
            for (j = 0; j<IAinverse.getKolom()/2; j++) {
                if (i == j && IAinverse.getElmt(i, j) != 1) {
                    isIdentity = false;
                } 
                if (i !=j && IAinverse.getElmt(i, j) != 0) {
                    isIdentity = false;
                }
            }
        }

        if (!isIdentity) {
            return null;
        } else {
            Matrix inverseMatrix = this.copyMatrix();
            k = 0;
            l = 0;
            for (i = 0; i < IAinverse.getBaris(); i++) {
                for (j = IAinverse.getKolom() / 2; j<IAinverse.getKolom(); j++) {
                    inverseMatrix.setElmt(k, l, IAinverse.getElmt(i, j));
                    l += 1;
                }
                l = 0;
                k += 1;
            }
            return inverseMatrix;
        }
    }

    public Matrix inverseWithAdjMethod () {
        Matrix dummyMatrix = this.copyMatrix();
        int i, j;
        if (dummyMatrix.determinantWithCofExpansion() == 0) {
            return null;
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
    
    // Polinomial Interpolation
    public static void polinomialInterpolation (Scanner scanner) {
        System.out.print("Input the degree of the polinomial: ");
        int derajatPolinomial = scanner.nextInt();
        scanner.nextLine();
        int i, j;
        String[] xy;
        Matrix dummyMatrix = new Matrix(derajatPolinomial+1, derajatPolinomial+2);
        for (i = 0; i < derajatPolinomial + 1; i++) {
            // Mengelola input agar tersimpan pada nilai x dan y
            do {
                System.out.print("Input the coordinate (x"+i+",y"+i+") : ");
                String inputTitik = scanner.nextLine();
                xy = inputTitik.split(" ");
                if (xy.length != 2) {
                    System.out.println("Invalid input. The input format is X Y. Please do re-input.");
                    System.out.printf("\n");
                }
            } while (xy.length != 2);
            double x = Double.parseDouble(xy[0]);
            double y = Double.parseDouble(xy[1]);
            for (j = 0; j<derajatPolinomial + 2; j++) {
                if (j == derajatPolinomial + 1) {
                    dummyMatrix.setElmt(i, j, y);
                } else {
                    dummyMatrix.setElmt(i, j, Math.pow(x,j));
                }
            }
        }
        System.out.print("Nilai x yang hendak ditaksir: ");
        double xTaksir = scanner.nextDouble();
        scanner.nextLine(); // dummyMatrix=3x4 solution=2x3
        Double solution[][] = dummyMatrix.gauss().solutionOfGaussMethod();
        if (solution != null) {
            System.out.print("f(x) = ");
            for (i = 0; i < dummyMatrix.getBaris(); i++) { // solution 4x5 ketika polinomial = 3
                if (i==0){
                    System.out.print(solution[i][solution[i].length-1] + " + ");
                } else if (i == solution.length-1) {
                    System.out.print(solution[i][solution[i].length-1] + "x^" + i);
                } else {
                    System.out.print(solution[i][solution[i].length-1] + "x^" + i + " + ");
                }        
            }
            System.out.printf("\n");
            System.out.print("f("+xTaksir+") = ");
            for (i = 0; i < solution.length; i++) {
                if (i==0){
                    System.out.print(solution[i][solution[i].length-1] + " + ");
                } else if (i == solution.length-1) {
                    System.out.print(solution[i][solution[i].length-1] + "("+ xTaksir+")" + "^" + i);
                } else {
                    System.out.print(solution[i][solution[i].length-1] + "("+ xTaksir+")" + "^" + i + " + ");
                }        
            }
            double result;
            result = 0;
            for (i=0; i<solution.length; i++) {
                result += solution[i][solution[i].length-1]*Math.pow(xTaksir, i);
            }
            System.out.printf("\n");
            System.out.print("Hasil taksiran adalah : ");
            System.out.printf("%f", result);

        } else {
            System.out.println("The coordinate given do not produce any possible interpolation.");
        }

    }

    public boolean isAllNull(Double[] row){
        for (int i = 0; i < row.length ; i++ ){
            if(row[i] != null){
                return false;
            }            
        }
        return true;
    }

    public  String outputSolution(Double[][] ans){
        StringBuffer[] str = new StringBuffer[ans.length];
        String[] strSol = new String[ans[0].length];
        StringBuffer strReal = new StringBuffer();
        for(int i = ans.length -1; i >= 0; i--){
            str[i] = new StringBuffer();
            if(isAllNull(ans[i])){
                str[i].append("x" + (i + 1) + " = " + (char)(i+97) + "\n");
                strSol[i] = Character.toString( (char)(i+97));
            }else{
                str[i].append("x" + (i + 1) + " = ");
                boolean first = true;
                for (int j = 0; j < ans[i].length ; j++){
                    if(ans[i][j] != null ){
                        if(j != ans[i].length -1 ){
                            if( ans[i][j] != 0D){
                                
                                if(!first){
                                    str[i].append( " + " + String.format("%.2f",ans[i][j] )+ strSol[j] );
                                }else{
                                    str[i].append( String.format("%.2f",ans[i][j] )+ strSol[j] );
                                    first = false;
                                } 
                            }                           
                        }else{
                            if(!first){
                                if(ans[i][j] != 0D){
                                    str[i].append( " + " + String.format("%.2f",ans[i][j]));
                                }
                                 
                            }else{
                                str[i].append( String.format("%.2f",ans[i][j])); 
                                first = false;
                            }                                
                        }   
                    }   
                }
                str[i].append("\n");
            }
        }

        for (int k = 0; k <ans.length ; k++){
            strReal.append(str[k].toString());
        }
        return strReal.toString();
    }

    public void OperationMatrix(){
        Double[][] solution = new Double[this.getKolom()-1][this.getKolom()]; // Variable untuk nampung solution
        int idx = 0,z = 0;
        boolean noSolution = false;
        
        
        while(z < this.getBaris()){
            if(this.isNoSolution(z)){
                noSolution = true;
                break;
            }else{
                z += 1;
            }
        }
        if(noSolution){
            System.out.println("There are no solution.");
        }else{
            for (int i = this.getBaris() - 1; i >=0 ; i--){
                // Find index leading 1
                if(this.isAllZero(i)){
                    continue;
                }else{
                    idx = 0;
                    while( idx < this.getKolom()){
                        if(this.getElmt(i, idx) == 1){
                            break;
                        }else{
                            idx += 1;
                        }
                    }

                    
                    int k = idx + 1;
                    while(k <= this.getKolom()-1){
                        if(k == this.getKolom()-1){
                            solution[idx][k] = this.getElmt(i, k);
                        }else{
                            if(this.getElmt(i, k) !=0){
                                solution[idx][k] = -(this.getElmt(i, k));
                            }else{
                                solution[idx][k] = (this.getElmt(i, k));
                            }
                            
                        }
                        k += 1;
                    
                    }
                
                }
            }
            
            for (int x = this.getKolom()-2; x >= 0; x--){
                if(isAllNull(solution[x])){
                    continue;
                }else{
                    for (int b = 0; b < this.getKolom(); b++){
                        if(solution[x][b] != null){
                            if(b != this.getKolom()-1){
                                if(isAllNull(solution[b]) == false){
                                    
                                    for(int l = b +1; l < this.getKolom(); l++){
                                        if(solution[b][l] != null){
                                            solution[x][l] += (solution[x][b] * solution[b][l]);
                                            
                                        }            
                                    }
                                    solution[x][b]= null;
                                }
                            }
                            
                        }
                    }
                }
            }


            System.out.println(outputSolution(solution));

        }
    }

    public Double[][] solutionOfGaussMethod(){ // Input is already on Gauss Format
        Double[][] solution = new Double[this.getKolom()-1][this.getKolom()]; // Variable untuk nampung solution
        int idx = 0,z = 0;
        boolean noSolution = false;
        
        
        while(z < this.getBaris()){
            if(this.isNoSolution(z)){
                noSolution = true;
                break;
            }else{
                z += 1;
            }
        }
        if(noSolution){
            return null;
        }else{
            for (int i = this.getBaris() - 1; i >=0 ; i--){
                // Find index leading 1
                if(this.isAllZero(i)){
                    continue;
                }else{
                    idx = 0;
                    while( idx < this.getKolom()){
                        if(this.getElmt(i, idx) == 1){
                            break;
                        }else{
                            idx += 1;
                        }
                    }

                    
                    int k = idx + 1;
                    while(k <= this.getKolom()-1){
                        if(k == this.getKolom()-1){
                            solution[idx][k] = this.getElmt(i, k);
                        }else{
                            if(this.getElmt(i, k) !=0){
                                solution[idx][k] = -(this.getElmt(i, k));
                            }else{
                                solution[idx][k] = (this.getElmt(i, k));
                            }
                            
                        }
                        k += 1;
                    
                    }
                
                }
            }
            
            for (int x = this.getKolom()-2; x >= 0; x--){
                if(isAllNull(solution[x])){
                    continue;
                }else{
                    for (int b = 0; b < this.getKolom(); b++){
                        if(solution[x][b] != null){
                            if(b != this.getKolom()-1){
                                if(isAllNull(solution[b]) == false){
                                    
                                    for(int l = b +1; l < this.getKolom(); l++){
                                        if(solution[b][l] != null){
                                            solution[x][l] += (solution[x][b] * solution[b][l]);
                                            
                                        }            
                                    }
                                    solution[x][b]= null;
                                }
                            }
                            
                        }
                    }
                }
            }


            return solution;

        }
    }

    public void GaussMethod(){
        Matrix dummyMatrix = new Matrix();
        dummyMatrix = this.gauss(); // Membuat Matrix jadi eselon 
        System.out.println("\nThis is the the OBE result: ");
        this.printMatriks();
        System.out.println("\n");

        
        
        dummyMatrix.OperationMatrix();
        
    }

    public void GaussJordanMethod (){
        Matrix dummyMatrix = new Matrix();
        dummyMatrix = this.gaussJordan();
        System.out.println("\nThis is the the OBE result: ");
        this.printMatriks();
        System.out.println("\n");

        dummyMatrix.OperationMatrix();
    }
}