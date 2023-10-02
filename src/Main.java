import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void SHOW_GREETINGS_TO_USER () {
        System.out.println("==================================================");
        System.out.println("Welcome to our first Algeo Project");
        System.out.println("This is JUN HOK 88 's hardwork.");
        System.out.println("==================================================");
        
    }
    public static void CLEAR_TERMINAL() {
        String operatingSystem = System.getProperty("os.name").toLowerCase();

        try {
            if (operatingSystem.contains("windows")) {
                // If the operating system is Windows, clear the terminal using the "cls" command.
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else if (operatingSystem.contains("mac") || operatingSystem.contains("unix") || operatingSystem.contains("linux") || operatingSystem.contains("bsd")) {
                // If the operating system is macOS, Linux, Unix, or BSD, clear the terminal using the "clear" command.
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            } else {
                // For other operating systems, simply print a newline character to simulate clearing the terminal.
                System.out.println();
            }
        } catch (IOException | InterruptedException e) {
            // Handle any potential exceptions by printing a newline character.
            System.out.println();
        }
    }

    public static Matrix INPUT_MATRIX_SUBMENU(Scanner scanner, int sq) {
        int choice;

        System.out.println("How do you want to input your matrix?");
        System.out.println("1. By inputting manually via program.");
        System.out.println("2. By reading .txt file");
        System.out.println();

        choice = GET_VALID_CHOICE(scanner, 1, 2, 5, 1);
        Matrix currentMatrix = INPUT_MATRIX(choice, scanner, sq);
        System.out.println();
        return currentMatrix;
    }



public static Matrix INPUT_MATRIX(int choice, Scanner scanner, int sq) {
    //sq==0, not square ; sq==1, is square
    Matrix inputMatrix = null;
    
    if (choice == 1) {
        int jumlahBaris = 0;
        int jumlahKolom = 0;

        if (sq == 0) {
            boolean validInput = false;

            while (!validInput) {
                try {
                    System.out.print("Input how many rows the matrix will have (enter a positive integer): ");
                    jumlahBaris = scanner.nextInt();
                    scanner.nextLine();
                    if (jumlahBaris > 0) {
                        validInput = true;
                    } else {
                        System.out.println("Row of matrix can't ever be zero or lower.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter an integer.");
                    scanner.nextLine();
                }
            }

            validInput = false;

            while (!validInput) {
                try {
                    System.out.print("Input how many columns the matrix will have (enter a positive integer): ");
                    jumlahKolom = scanner.nextInt();
                    scanner.nextLine();
                    if (jumlahKolom > 0) {
                        validInput = true;
                    } else {
                        System.out.println("Column of matrix can't ever be zero or lower.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter an integer.");
                    scanner.nextLine();
                }
            }
        } else if (sq==1) {
            boolean validInput = false;

            while (!validInput) {
                try {
                    System.out.print("Input the matrix size NxN, Input N: ");
                    jumlahBaris = scanner.nextInt();
                    scanner.nextLine();
                    if (jumlahBaris > 0) {
                        validInput = true;
                    } else {
                        System.out.println("Row of matrix can't ever be zero or lower.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter an integer.");
                    scanner.nextLine();
                }
            }

            jumlahKolom = jumlahBaris;
        }

     

        boolean inputIsValid = false;

        while (!inputIsValid) {
            System.out.println("Enter your matrix with " + jumlahBaris + "x" + jumlahKolom + " matrix size.");
            inputMatrix = new Matrix(jumlahBaris, jumlahKolom);
            try {
                inputMatrix.bacaMatriks(scanner);
                inputIsValid = true;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("You enter less than you must do. Please reenter the matrix.");
            } catch (NumberFormatException e) {
                System.out.println("The matrix element must be integer. Please reenter the matrix.");
            }
        }

    } else if (choice == 2) {
        System.out.print("Input the filename and don't forget to include .txt : ");
        String filename;
        do {
            filename = scanner.nextLine();
            if (!filename.endsWith(".txt")) {
                System.out.print("Please include '.txt' in the filename. Re-enter the filename: ");
            }
        } while (!filename.endsWith(".txt"));

        inputMatrix = new Matrix();
        inputMatrix.bacaMatriksDariFile(filename);
    }
    
    System.out.println("\nHere is the matrix read by the program:");
    inputMatrix.printMatriks();
    return inputMatrix;
}
    
    public static void SHOW_MAIN_MENU () {
        System.out.println("JUN HOK 88'S MAIN MENU");
        System.out.println("==================================================");
        System.out.println("Which of these operations would you like to do?");
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
        System.out.println("==================================================");
        System.out.println("Finding determinant of the matrix.");
        System.out.println("But... with what method ?");
        System.out.println("==================================================");
        System.out.println("1. 'Reduksi Baris' Method"); // DONE
        System.out.println("2. Cofactor Expansion Method"); // DONE
        System.out.println("3. Back to previous menu."); 
        System.out.printf("\n");
    }

    public static void SHOW_LINEAR_EQUATION_SUBMENU () {
        System.out.println("==================================================");
        System.out.println("Finding the linear equation solution");
        System.out.println("But... with what method?");
        System.out.println("==================================================");
        System.out.println("1. Gauss Elimination Method"); // DONE
        System.out.println("2. Gauss-Jordan Elimination Method"); // DONE
        System.out.println("3. Inverse Matrix Method"); // DONE
        System.out.println("4. Cramer's Rule Method"); // DONE
        System.out.println("5. Back to previous menu.");
        System.out.printf("\n");
    }


    public static void SHOW_INVERSE_SUBMENU () {
        System.out.println("==================================================");
        System.out.println("Finding the inverse of the matrix.");
        System.out.println("But... with what method?"); 
        System.out.println("==================================================");
        System.out.println("1. Adjoint Matrix Method"); // DONE
        System.out.println("2. Elementary Row Transformation Method"); // DONE
        System.out.println("3. Back to previous menu."); 
        System.out.printf("\n");
    }
    public static void WRONG_INPUT_REMINDER () {
        CLEAR_TERMINAL();
        System.out.println("I gotta remind you that the input must be within the specified range.");
        System.out.println("Your input must be a valid integer number to proceed.");
        System.out.printf("\n");
    }

    public static void ASK_FOR_CHOICE_MESSAGE () {
        System.out.print("Which option do you like to proceed? ");
    }

    public static boolean REUSE_CONFIRMATION (Scanner scanner) {
        System.out.printf("\n");
        System.out.println("Do you want to reuse the program or exit the program?");
        System.out.println("1. Wanted to do another operation");
        System.out.println("2. Exit program");
        System.out.printf("\n");
        int choice;
        do {
            ASK_FOR_CHOICE_MESSAGE();
            choice = scanner.nextInt();
            scanner.nextLine();
            if (choice <= 0 || choice > 2) {
                WRONG_INPUT_REMINDER();
            }
        } while (choice <= 0 || choice > 2);
        if (choice == 1) {
            CLEAR_TERMINAL();
            return true;
        } else if (choice == 2) {
            CLEAR_TERMINAL();
            System.out.println("God bless you ! Hope you enjoy JUN HOK 88's work.");
            System.out.println("Signing out...");
            Additional.main(null);
            try {
                // Sleep for 1000 milliseconds (1 second)
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                // Handle the InterruptedException (e.g., logging or re-throwing)
            }
            System.out.println("Program terminated");
            System.exit(0);
        }
        return false;
    }

    public static int GET_VALID_CHOICE(Scanner scanner, int leastChoice, int highestChoice, int menu, int sq) {
        int choice = -1; // Initialize choice to an invalid value
        do {
            try {
                ASK_FOR_CHOICE_MESSAGE();
                choice = scanner.nextInt();
                scanner.nextLine();
                if (choice > highestChoice || choice < leastChoice) {
                    WRONG_INPUT_REMINDER();
                    if (menu == 1) {
                        SHOW_MAIN_MENU();
                    } else if (menu == 2) {
                        SHOW_LINEAR_EQUATION_SUBMENU();
                    } else if (menu == 3) {
                        SHOW_DETERMINANT_SUBMENU();
                    } else if (menu == 4) {
                        SHOW_INVERSE_SUBMENU();
                    } else if (menu == 5) {
                        INPUT_MATRIX_SUBMENU(scanner, sq);
                    } else if (menu == 6) {
                        WRONG_INPUT_REMINDER();
                        System.out.println("How do you want to input your coordinates?");
                        System.out.println("1. By inputting manually via program.");
                        System.out.println("2. By reading .txt file");
                        System.out.println();
                    }
                }
            } catch (InputMismatchException e) {
                scanner.nextLine(); // Consume the invalid input
                CLEAR_TERMINAL();
                System.out.println("Oops! Looks like you didn't enter a valid integer.");
                System.out.println("Please enter a valid integer to proceed.");
                System.out.printf("\n");
                if (choice > highestChoice || choice < leastChoice) {
                    if (menu == 1) {
                        SHOW_MAIN_MENU();
                    } else if (menu == 2) {
                        SHOW_LINEAR_EQUATION_SUBMENU();
                    } else if (menu == 3) {
                        SHOW_DETERMINANT_SUBMENU();
                    } else if (menu == 4) {
                        SHOW_INVERSE_SUBMENU();
                    } else if (menu == 5) {
                        INPUT_MATRIX_SUBMENU(scanner, sq);
                    } else if (menu == 6) {
                        System.out.println("How do you want to input your coordinates?");
                        System.out.println("1. By inputting manually via program.");
                        System.out.println("2. By reading .txt file");
                        System.out.println();
                    }
                }
                choice = 0;
            }       
        } while (choice < leastChoice || choice > highestChoice);
        return choice;
    }


    public static void main(String[] args) {
        int mainMenuChoice = 0;
        Scanner scanner = new Scanner(System.in);
        boolean previousMenu = false;
        boolean reuseConfirmation;
        CLEAR_TERMINAL();
        do {     
            SHOW_GREETINGS_TO_USER();
            SHOW_MAIN_MENU();
            reuseConfirmation = false;         
            mainMenuChoice = GET_VALID_CHOICE(scanner, 1, 7, 1, 1);
            if (mainMenuChoice == 1) { // Linear of Equation System
                int linearEqSubMenuChoice = 0;
                CLEAR_TERMINAL();
                do {
                    SHOW_GREETINGS_TO_USER();
                    SHOW_LINEAR_EQUATION_SUBMENU();
                    previousMenu = false;
                        linearEqSubMenuChoice = GET_VALID_CHOICE(scanner, 1, 5, 2, 1);
                        CLEAR_TERMINAL();
                        if (linearEqSubMenuChoice == 1) {
                            Matrix currentMatrix = INPUT_MATRIX_SUBMENU(scanner, 0);
                            System.out.println("The result of Gauss method for the system of linear equation is the following :");
                            currentMatrix.GaussMethod(scanner);

                            reuseConfirmation = REUSE_CONFIRMATION(scanner);
                            
                        } else if (linearEqSubMenuChoice == 2) {
                            Matrix currentMatrix = INPUT_MATRIX_SUBMENU(scanner, 0);
                            System.out.println("The result of Gauss-Jordan method for the system of linear equation is the following :");
                            currentMatrix.GaussJordanMethod(scanner);

                            reuseConfirmation = REUSE_CONFIRMATION(scanner);

                        } else if (linearEqSubMenuChoice == 3) {
                            Matrix currentMatrix = INPUT_MATRIX_SUBMENU(scanner, 0);
                            System.out.println("The result of Inverse Method (AX=B -> X=(A^-1)B) method for the system of linear equation is the following :");
                            currentMatrix.SPLwithInverseMethod(scanner);

                            reuseConfirmation = REUSE_CONFIRMATION(scanner);

                        } else if (linearEqSubMenuChoice == 4) {
                            Matrix currentMatrix = INPUT_MATRIX_SUBMENU(scanner, 0);
                            System.out.println("The result of Cramer method for the system of linear equation is the following :");
                            currentMatrix.SPLwithCramerMethod(scanner);

                            reuseConfirmation = REUSE_CONFIRMATION(scanner);

                        } else if (linearEqSubMenuChoice == 5) {
                            previousMenu = true;
                            CLEAR_TERMINAL();
                        } 
                } while (linearEqSubMenuChoice <= 0 || linearEqSubMenuChoice > 5);
                // Handle choice 1
            } else if (mainMenuChoice == 2) { // Finding the determinant
                CLEAR_TERMINAL();
                int determinantSubMenuChoice = 0;
                do {
                    SHOW_GREETINGS_TO_USER();
                    SHOW_DETERMINANT_SUBMENU();
                    previousMenu = false;
                    determinantSubMenuChoice = GET_VALID_CHOICE(scanner, 1, 3, 3, 1);
                    CLEAR_TERMINAL();
                    if (determinantSubMenuChoice == 1) {
                        Matrix currentMatrix = INPUT_MATRIX_SUBMENU(scanner, 1);

                        System.out.println("The result of matrix determinant by using 'Reduksi Baris' Method is :");
                        System.out.println(currentMatrix.determinantWithReduksiBaris());

                        Matrix.OutputToFile(scanner, "The result of matrix determinant by using 'Reduksi Baris' Method is :" + currentMatrix.determinantWithReduksiBaris());
                        
                        reuseConfirmation = REUSE_CONFIRMATION(scanner);

                    } else if (determinantSubMenuChoice == 2) {
                        Matrix currentMatrix = INPUT_MATRIX_SUBMENU(scanner, 1);

                        System.out.println("The result of matrix determinant by using Cofactor Expansion Method is :");
                        System.out.println(currentMatrix.determinantWithCofExpansion());
                        Matrix.OutputToFile(scanner, "The result of matrix determinant by using Cofactor Expansion Method is :" + currentMatrix.determinantWithCofExpansion());
                        reuseConfirmation = REUSE_CONFIRMATION(scanner);

                    } else if (determinantSubMenuChoice == 3) {
                        previousMenu = true;
                        CLEAR_TERMINAL();
                    } 
                } while (determinantSubMenuChoice <= 0 || determinantSubMenuChoice > 3);
                // Handle choice 2
            } else if (mainMenuChoice == 3) { // Finding inverse of the matrix
                int inverseSubMenuChoice = 0;
                CLEAR_TERMINAL();
                do {
                    SHOW_GREETINGS_TO_USER();
                    SHOW_INVERSE_SUBMENU();
                    previousMenu = false;                
                    inverseSubMenuChoice = GET_VALID_CHOICE(scanner, 1, 3, 4, 1);
                    CLEAR_TERMINAL();
                    if (inverseSubMenuChoice == 1) {
                        Matrix currentMatrix = INPUT_MATRIX_SUBMENU(scanner, 1);
                        currentMatrix = currentMatrix.inverseWithAdjMethod();
                        if (currentMatrix == null) {
                            System.out.println("Your matrix input does not have inverse.");
                            Matrix.OutputToFile(scanner, "Your matrix input does not have inverse.");
                        } else {
                            System.out.println("The result of matrix inverse using Adjoint Matrix Method is :");
                            currentMatrix.printMatriks();
                            Matrix.OutputToFile(scanner,  currentMatrix.MatrixToString());
                        }
                        reuseConfirmation = REUSE_CONFIRMATION(scanner);

                    } else if (inverseSubMenuChoice == 2) {
                        Matrix currentMatrix = INPUT_MATRIX_SUBMENU(scanner, 1);
                        currentMatrix = currentMatrix.inverseWithIdentity();
                        if (currentMatrix == null) {
                            System.out.println("Your matrix input does not have inverse.");
                            Matrix.OutputToFile(scanner, "Your matrix input does not have inverse.");
                        } else {
                            System.out.println("The result of matrix inverse using Elementary Row Transformation Method is :");
                            currentMatrix.printMatriks();
                            Matrix.OutputToFile(scanner,currentMatrix.MatrixToString() );
                        }
                        reuseConfirmation = REUSE_CONFIRMATION(scanner);

                    } else if (inverseSubMenuChoice == 3) {
                        previousMenu = true;
                        CLEAR_TERMINAL();
                    } 
                } while (inverseSubMenuChoice <= 0 || inverseSubMenuChoice > 3);
                // Handle choice 3
            } else if (mainMenuChoice == 4) { // Polinomial Interpolation
                // Handle choice 4
                CLEAR_TERMINAL();
                SHOW_GREETINGS_TO_USER();
                System.out.println("Welcome to polinomial interpolation !");
                System.out.println("==================================================");
                System.out.println("How do you want to input your coordinates?");
                System.out.println("1. By inputting manually via program.");
                System.out.println("2. By reading .txt file");
                System.out.println();
                int interpolationChoice = GET_VALID_CHOICE(scanner, 1, 2, 6, 1);
                if (interpolationChoice == 1) {
                    Matrix.polinomialInterpolation(scanner);
                } else if (interpolationChoice == 2) {
                    System.out.print("Input the filename and don't forget to include .txt : ");
                    String filename;
                    do {
                        filename = scanner.nextLine();
                        if (!filename.endsWith(".txt")) {
                            System.out.print("Please include '.txt' in the filename. Re-enter the filename: ");
                        }
                    } while (!filename.endsWith(".txt"));
                    Matrix.polinomialInterpolationByFile(filename);;
                }
                reuseConfirmation = REUSE_CONFIRMATION(scanner);
            } else if (mainMenuChoice == 5) { // Multiple Linear Regression
                // Handle choice 5
                CLEAR_TERMINAL();
                SHOW_GREETINGS_TO_USER();
                System.out.println("Welcome to Multiple Linear Regression !");
                System.out.println("==================================================");
                Matrix.MultipleLinearRegression(scanner);
                reuseConfirmation = REUSE_CONFIRMATION(scanner);
            } else if (mainMenuChoice == 6) { // Bicubic Spline Interpolation
                // Handle choice 6
            } else if (mainMenuChoice == 7) { // Terminate the program
                System.out.println("Thank you for using JUN HOK 88's program. Hope you are helped");
                System.exit(0);
            } 
        } while (mainMenuChoice <= 0 || mainMenuChoice > 7 || previousMenu || reuseConfirmation);
        scanner.close();
    }
}