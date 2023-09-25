import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void SHOW_GREETINGS_TO_USER () {
        System.out.println("Welcome to our first Algeo Project");
        System.out.println("This is JUN HOK 88 's hardwork.");
    }

    public static void SHOW_MAIN_MENU () {
        System.out.println("MAIN MENU");
        System.out.println("============================");
        System.out.println("1. Input your matrix.");
        System.out.println("2. Need help.");
        System.out.println("3. Exit the program.");
    }

    public static void INPUT_MATRIX_SUBMENU () {
        System.out.println("How do you want to input your matrix?");
        System.out.println("1. By inputing manually via program.");
        System.out.println("2. By reading .txt file");
        System.out.println("3. Go back to the previous menu.");
    }
    
    public static void SHOW_SUBMENU_1 () {
        System.out.println("Mark this as SUBMENU.");
        System.out.println("What would you like to do with the matrix you input?");
        System.out.println("1. Solving the system linear of equation");
        System.out.println("2. Finding the determinant");
        System.out.println("3. Finding the inverse of the matrix");
        System.out.println("4. Do polinomial interpolation");
        System.out.println("5. Finding the multiple linear regression");
        System.out.println("6. Do bicubic spline interpolation");
        System.out.println("7. Exit the program.");
    }

    public static void SHOW_DETERMINANT_SUBMENU () {
        System.out.println("Finding determinant of the matrix.");
        System.out.println("But... with what method ?");
        System.out.println("1. 'Reduksi Baris' Method");
        System.out.println("2. Cofactor Expansion Method");
        System.out.println("3. Go back to the previous menu.");
    }

    public static void SHOW_LINEAR_EQUATION_SUBMENU () {
        System.out.println("Finding the linear equation solution");
        System.out.println("But... with what method?");
        System.out.println("1. Gauss Elimination Method");
        System.out.println("2. Gauss-Jordan Elimination Method");
        System.out.println("3. Inverse Matrix Method");
        System.out.println("4. Cramer's Rule Mthod");
        System.out.println("5. Go back to the previous menu.");
    }

    public static void SHOW_INVERSE_SUBMENU () {
        System.out.println("Finding the inverse of the matrix.");
        System.out.println("But... with what method?");
        System.out.println("1. Adjoint Matrix Method");
        System.out.println("2. Elementary Row Transformation Method");
        System.out.println("3. Go back to the previous menu.");
    }

    public static void WRONG_INPUT_REMINDER () {
        System.out.println("I gotta remind that the input must be precisely correct.");
        System.out.println("Your input must be a single integer number to go to specified submenu.");
    }

    public static void ASK_FOR_CHOICE_MESSAGE () {
        System.out.print("Which do you like to do proceed? ");
    }

    

    public static void main(String[] args) {
        SHOW_GREETINGS_TO_USER();
        SHOW_MAIN_MENU();
        Scanner scanner = new Scanner (System.in);
        int choice;
        ASK_FOR_CHOICE_MESSAGE();

         
    }
}


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

        // Matrix dummyMatrix = new Matrix();
        // dummyMatrix.bacaMatriksDariFile("input.txt");
        // dummyMatrix.printMatriks();
        // dummyMatrix.printMatriks();
        // Matrix pola = new Matrix(2,3);
        // pola = 
        // pola.printMatriks();
        // double determinan = dummyMatrix.determinantWithCofExpansion();
        // System.out.printf("%.2f%n", determinan);
        
        // printMatriks(matrix, jumlahBaris, jumlahKolom);

        // scanner.close();
        