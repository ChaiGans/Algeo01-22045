import java.util.Scanner;

public class Main {
    public static void SHOW_GREETINGS_TO_USER () {
        System.out.println("Welcome to our first Algeo Project");
        System.out.println("This is JUN HOK 88 's hardwork.");
        System.out.printf("\n");
    }

    public static void SHOW_MAIN_MENU () {
        System.out.println("MAIN MENU");
        System.out.println("============================");
        System.out.println("1. Input your matrix.");
        System.out.println("2. Need help.");
        System.out.println("3. Exit the program.");
        System.out.printf("\n");
    }

    public static void INPUT_MATRIX_SUBMENU () {
        System.out.println("How do you want to input your matrix?");
        System.out.println("1. By inputing manually via program.");
        System.out.println("2. By reading .txt file");
        System.out.printf("\n");
    }

    public static Matrix INPUT_MATRIX (int choice) {
        Scanner scanner = new Scanner (System.in);
        Matrix inputMatrix = null;
        if (choice == 1) {
            System.out.print("Input how many row is it going to be in the matrix: ");
            int jumlahBaris = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Input how many column is it going to be in the matrix: ");
            int jumlahKolom = scanner.nextInt();
            scanner.nextLine();

            inputMatrix = new Matrix(jumlahBaris, jumlahKolom);
            inputMatrix.bacaMatriks();
        } else if (choice == 2) {
            inputMatrix = new Matrix();
            String filename = scanner.nextLine();
            inputMatrix.bacaMatriksDariFile(filename);
        } 
        System.out.printf("\n");
        System.out.println("Here is the matrix read by the program :");
        inputMatrix.printMatriks();
        System.out.printf("\n");
        return inputMatrix;
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
        System.out.printf("\n");
    }

    public static void SHOW_DETERMINANT_SUBMENU () {
        System.out.println("Finding determinant of the matrix.");
        System.out.println("But... with what method ?");
        System.out.println("1. 'Reduksi Baris' Method");
        System.out.println("2. Cofactor Expansion Method");
        System.out.printf("\n");
    }

    public static void SHOW_LINEAR_EQUATION_SUBMENU () {
        System.out.println("Finding the linear equation solution");
        System.out.println("But... with what method?");
        System.out.println("1. Gauss Elimination Method");
        System.out.println("2. Gauss-Jordan Elimination Method");
        System.out.println("3. Inverse Matrix Method");
        System.out.println("4. Cramer's Rule Method");
        System.out.printf("\n");
    }

    public static void SHOW_INVERSE_SUBMENU () {
        System.out.println("Finding the inverse of the matrix.");
        System.out.println("But... with what method?");
        System.out.println("1. Adjoint Matrix Method");
        System.out.println("2. Elementary Row Transformation Method");
        System.out.printf("\n");
    }

    public static void WRONG_INPUT_REMINDER () {
        System.out.println("I gotta remind that the input must be precisely correct.");
        System.out.println("Your input must be a single integer number to go to specified submenu.");
        System.out.printf("\n");
    }

    public static void ASK_FOR_CHOICE_MESSAGE () {
        System.out.print("Which do you like to do proceed? ");
    }

public static void main(String[] args) {
        SHOW_GREETINGS_TO_USER();
        SHOW_MAIN_MENU();
        Scanner scanner = new Scanner(System.in);
        int mainMenuChoice;
        
        do {
            ASK_FOR_CHOICE_MESSAGE();
            mainMenuChoice = scanner.nextInt();
            scanner.nextLine();
            System.out.printf("\n");
            if (mainMenuChoice == 1) {
                INPUT_MATRIX_SUBMENU();
                int matrixSubMenuChoice;
                do {
                    ASK_FOR_CHOICE_MESSAGE();
                    matrixSubMenuChoice = scanner.nextInt();
                    scanner.nextLine();
                    System.out.printf("\n");
                    if (matrixSubMenuChoice == 1 || matrixSubMenuChoice == 2) {
                        Matrix currentMatrix = INPUT_MATRIX(matrixSubMenuChoice);
                        SHOW_SUBMENU_1();
                        int submenu1Choice;
                        do {
                            ASK_FOR_CHOICE_MESSAGE();
                            submenu1Choice = scanner.nextInt();
                            scanner.nextLine();
                            System.out.printf("\n");
                            if (submenu1Choice == 1) {
                                SHOW_LINEAR_EQUATION_SUBMENU();
                                int linearEqSubMenuChoice;
                                do {
                                    ASK_FOR_CHOICE_MESSAGE();
                                    linearEqSubMenuChoice = scanner.nextInt();
                                    scanner.nextLine();
                                    if (linearEqSubMenuChoice == 1) {
                                        // Gauss elimination method
                                        System.out.println("The result of Gauss method for the system of linear equation is the following :");
                                        Matrix gaussMatrix = new Matrix(currentMatrix.getBaris(), currentMatrix.getKolom());
                                        gaussMatrix = currentMatrix.gauss();
                                        gaussMatrix.printMatriks();
                                    } else if (linearEqSubMenuChoice == 2) {
                                        // Gauss Jordan elimination method
                                        System.out.println("The result of Gauss-Jordan method for the system of linear equation is the following :");
                                        Matrix gaussJordanMatrix = new Matrix(currentMatrix.getBaris(), currentMatrix.getKolom());
                                        gaussJordanMatrix = currentMatrix.gaussJordan();
                                        gaussJordanMatrix.printMatriks();
                                    } else if (linearEqSubMenuChoice == 3) {
                                        // Inverse Matrix Method
                                    } else if (linearEqSubMenuChoice == 4) {
                                        // Cramer's Method
                                    } else {
                                        WRONG_INPUT_REMINDER();
                                    }
                                } while (linearEqSubMenuChoice <= 0 || linearEqSubMenuChoice > 4);
                            } else if (submenu1Choice == 2) {
                                SHOW_DETERMINANT_SUBMENU();
                                int determinantSubMenuChoice;
                                do {
                                    ASK_FOR_CHOICE_MESSAGE();
                                    determinantSubMenuChoice = scanner.nextInt();
                                    scanner.nextLine();
                                    if (determinantSubMenuChoice == 1) {
                                        // "Reduksi Baris" Method
                                    } else if (determinantSubMenuChoice == 2) {
                                        // Cofactor Expansion Method
                                        System.out.printf("The determinant of the matrix using cofactor expansion is %f", currentMatrix.makeItSquare().determinantWithCofExpansion());
                                    } else if (determinantSubMenuChoice == 3) {
                                        // Inverse Matrix Method
                                    } else if (determinantSubMenuChoice == 4) {
                                        // Cramer's Method
                                    } else {
                                        WRONG_INPUT_REMINDER();
                                    }
                                } while (determinantSubMenuChoice <= 0 || determinantSubMenuChoice > 4);
                            } else if (submenu1Choice == 3) {
                                SHOW_INVERSE_SUBMENU();
                                int inverseSubMenuChoice;
                                do {
                                    ASK_FOR_CHOICE_MESSAGE();
                                    inverseSubMenuChoice = scanner.nextInt();
                                    scanner.nextLine();
                                    if (inverseSubMenuChoice == 1) {
                                        // Adjoint Matrix Method
                                    } else if (inverseSubMenuChoice == 2) {
                                        // Row Elementary Transformation Method
                                    } else {
                                        WRONG_INPUT_REMINDER();
                                    }
                                } while (inverseSubMenuChoice <= 0 || inverseSubMenuChoice > 2);
                            } else if (submenu1Choice == 4) {
                                System.out.println("Coming soon. Interpolation.");
                            } else if (submenu1Choice == 5) {
                                System.out.println("Coming soon. Multiple linear regression.");
                            } else if (submenu1Choice == 6) {
                                System.out.println("Coming soon. bicubic spline interpolation.");
                            } else if (submenu1Choice == 7) {
                                System.exit(0);
                            } else {
                                WRONG_INPUT_REMINDER();
                            }
                        } while (submenu1Choice <= 0 || submenu1Choice > 7);
                    } else {
                        WRONG_INPUT_REMINDER();
                    }
                } while (matrixSubMenuChoice <= 0 || matrixSubMenuChoice > 2);
            } else if (mainMenuChoice == 2) {
                SHOW_MAIN_MENU();
            } else if (mainMenuChoice == 3)  {
                System.exit(0);
            } else {
                WRONG_INPUT_REMINDER();
            }
        } while (mainMenuChoice <= 0 || mainMenuChoice > 3 || mainMenuChoice == 2);
        scanner.close();
    }
}