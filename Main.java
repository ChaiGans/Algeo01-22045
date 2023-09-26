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

    public static Matrix INPUT_MATRIX_SUBMENU(Scanner scanner) {
        int choice;

        System.out.println("How do you want to input your matrix?");
        System.out.println("1. By inputting manually via program.");
        System.out.println("2. By reading .txt file");
        System.out.println();

        choice = GET_VALID_CHOICE(scanner, 1, 2, 5);
        Matrix currentMatrix = INPUT_MATRIX(choice, scanner);
        System.out.println();
        return currentMatrix;
    }



public static Matrix INPUT_MATRIX(int choice, Scanner scanner) {
    Matrix inputMatrix = null;
    
    if (choice == 1) {
        System.out.print("Input how many rows the matrix will have: ");
        int jumlahBaris = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Input how many columns the matrix will have: ");
        int jumlahKolom = scanner.nextInt();
        scanner.nextLine();

        inputMatrix = new Matrix(jumlahBaris, jumlahKolom);
        inputMatrix.bacaMatriks(scanner);
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
        System.out.println("1. 'Reduksi Baris' Method"); // TARGET
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
        System.out.println("3. Inverse Matrix Method");
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
        System.out.println("2. Elementary Row Transformation Method"); // TARGET
        System.out.println("3. Back to previous menu."); // TARGET
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

    public static int GET_VALID_CHOICE(Scanner scanner, int leastChoice, int highestChoice, int menu) {
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
                        INPUT_MATRIX_SUBMENU(scanner);
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
                        INPUT_MATRIX_SUBMENU(scanner);
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
            mainMenuChoice = GET_VALID_CHOICE(scanner, 1, 7, 1);
            if (mainMenuChoice == 1) { // Linear of Equation System
                int linearEqSubMenuChoice = 0;
                CLEAR_TERMINAL();
                do {
                    SHOW_GREETINGS_TO_USER();
                    SHOW_LINEAR_EQUATION_SUBMENU();
                    previousMenu = false;
                        linearEqSubMenuChoice = GET_VALID_CHOICE(scanner, 1, 5, 2);
                        CLEAR_TERMINAL();
                        if (linearEqSubMenuChoice == 1) {
                            Matrix currentMatrix = INPUT_MATRIX_SUBMENU(scanner);
                            System.out.println("The result of Gauss method for the system of linear equation is the following :");
                            currentMatrix.gauss().printMatriks();

                            reuseConfirmation = REUSE_CONFIRMATION(scanner);
                            
                        } else if (linearEqSubMenuChoice == 2) {
                            Matrix currentMatrix = INPUT_MATRIX_SUBMENU(scanner);
                            System.out.println("The result of Gauss-Jordan method for the system of linear equation is the following :");
                            currentMatrix.gaussJordan().printMatriks();

                            reuseConfirmation = REUSE_CONFIRMATION(scanner);

                        } else if (linearEqSubMenuChoice == 3) {
                            Matrix currentMatrix = INPUT_MATRIX_SUBMENU(scanner);
                            System.out.println("The result of Inverse Method (AX=B -> X=(A^-1)B) method for the system of linear equation is the following :");
                            currentMatrix.SPLwithInverseMethod();

                            reuseConfirmation = REUSE_CONFIRMATION(scanner);

                        } else if (linearEqSubMenuChoice == 4) {
                            Matrix currentMatrix = INPUT_MATRIX_SUBMENU(scanner);
                            System.out.println("The result of Gauss-Jordan method for the system of linear equation is the following :");
                            currentMatrix.SPLwithCramerMethod();

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
                    determinantSubMenuChoice = GET_VALID_CHOICE(scanner, 1, 3, 3);
                    CLEAR_TERMINAL();
                    if (determinantSubMenuChoice == 1) {
                        Matrix currentMatrix = INPUT_MATRIX_SUBMENU(scanner);
                        // currentMatrix.operasiBarisElementer();
                        reuseConfirmation = REUSE_CONFIRMATION(scanner);

                    } else if (determinantSubMenuChoice == 2) {
                        Matrix currentMatrix = INPUT_MATRIX_SUBMENU(scanner);
                        System.out.println("The result of matrix determinant by using Cofactor Expansion Method is :");
                        System.out.println(currentMatrix.determinantWithCofExpansion());

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
                    inverseSubMenuChoice = GET_VALID_CHOICE(scanner, 1, 3, 4);
                    CLEAR_TERMINAL();
                    if (inverseSubMenuChoice == 1) {
                        Matrix currentMatrix = INPUT_MATRIX_SUBMENU(scanner);
                        currentMatrix = currentMatrix.inverseWithAdjMethod();
                        currentMatrix.printMatriks();
                        reuseConfirmation = REUSE_CONFIRMATION(scanner);

                    } else if (inverseSubMenuChoice == 2) {
                        Matrix currentMatrix = INPUT_MATRIX_SUBMENU(scanner);

                        reuseConfirmation = REUSE_CONFIRMATION(scanner);

                    } else if (inverseSubMenuChoice == 3) {
                        previousMenu = true;
                        CLEAR_TERMINAL();
                    } 
                } while (inverseSubMenuChoice <= 0 || inverseSubMenuChoice > 3);
                // Handle choice 3
            } else if (mainMenuChoice == 4) { // Polinomial Interpolation
                // Handle choice 4
            } else if (mainMenuChoice == 5) { // Multiple Linear Regression
                // Handle choice 5
            } else if (mainMenuChoice == 6) { // Bicubic Spline Interpolation
                // Handle choice 6
            } else if (mainMenuChoice == 7) { // Terminate the program
                System.out.println("Thank you for using JUN HOK 88's program. Hope you are helped");
                System.exit(0);
            } 
        } while (mainMenuChoice <= 0 || mainMenuChoice > 7 || previousMenu || reuseConfirmation);
        scanner.close();

        // SHOW_GREETINGS_TO_USER();
        // SHOW_MAIN_MENU();
        
        // Scanner scanner = new Scanner(System.in);
        // int matrixSubMenuChoice;
        // do {
        //     ASK_FOR_CHOICE_MESSAGE();
        //     matrixSubMenuChoice = scanner.nextInt();
        //     scanner.nextLine();
        //     System.out.printf("\n");
        //     if (matrixSubMenuChoice == 1 || matrixSubMenuChoice == 2) {
        //         Matrix currentMatrix = INPUT_MATRIX(matrixSubMenuChoice, scanner);
        //         int submenu1Choice;
        //         boolean system;
        //         do {
        //             system = false;
        //             SHOW_SUBMENU_1();
        //             ASK_FOR_CHOICE_MESSAGE();
        //             submenu1Choice = scanner.nextInt();
        //             scanner.nextLine();
        //             System.out.printf("\n");
        //             if (submenu1Choice == 1) {
        //                 int linearEqSubMenuChoice;
        //                 boolean submenu_system;
        //                 do {
        //                     SHOW_LINEAR_EQUATION_SUBMENU();
        //                     submenu_system = false;
        //                     ASK_FOR_CHOICE_MESSAGE();
        //                     linearEqSubMenuChoice = scanner.nextInt();
        //                     scanner.nextLine();
        //                     if (linearEqSubMenuChoice == 1) {
        //                         // Gauss elimination method
        //                         System.out.println("The result of Gauss method for the system of linear equation is the following :");
        //                         Matrix gaussMatrix = new Matrix(currentMatrix.getBaris(), currentMatrix.getKolom());
        //                         gaussMatrix = currentMatrix.gauss();
        //                         gaussMatrix.printMatriks();
        //                         submenu_system = true;
        //                     } else if (linearEqSubMenuChoice == 2) {
        //                         // Gauss Jordan elimination method
        //                         System.out.println("The result of Gauss-Jordan method for the system of linear equation is the following :");
        //                         Matrix gaussJordanMatrix = new Matrix(currentMatrix.getBaris(), currentMatrix.getKolom());
        //                         gaussJordanMatrix = currentMatrix.gaussJordan();
        //                         gaussJordanMatrix.printMatriks();
        //                         submenu_system = true;
        //                     } else if (linearEqSubMenuChoice == 3) {
        //                         // Inverse Matrix Method
        //                     } else if (linearEqSubMenuChoice == 4) {
        //                         // Cramer's Method
        //                     } else if (linearEqSubMenuChoice == 5) {
                                
        //                         system = true;
        //                     } else {
        //                         WRONG_INPUT_REMINDER();
        //                     }
        //                 } while (linearEqSubMenuChoice <= 0 || linearEqSubMenuChoice > 5 || submenu_system);
        //             } else if (submenu1Choice == 2) {
        //                 int determinantSubMenuChoice;
        //                 boolean submenu_system;
        //                 do {
        //                     SHOW_DETERMINANT_SUBMENU();
        //                     submenu_system = false;
        //                     ASK_FOR_CHOICE_MESSAGE();
        //                     determinantSubMenuChoice = scanner.nextInt();
        //                     scanner.nextLine();
        //                     if (determinantSubMenuChoice == 1) {
        //                         // "Reduksi Baris" Method
        //                         System.out.println("The result of 'Reduksi Baris' method for the system of linear equation is the following :");
        //                         Matrix reduksiBarisMatrix = new Matrix(currentMatrix.getBaris(), currentMatrix.getKolom());
        //                         reduksiBarisMatrix = currentMatrix.operasiBarisElementer();
        //                         reduksiBarisMatrix.printMatriks();
        //                         submenu_system = true;
        //                     } else if (determinantSubMenuChoice == 2) {
        //                         // Cofactor Expansion Method
        //                         System.out.printf("The determinant of the matrix using cofactor expansion is %f", currentMatrix.makeItSquare().determinantWithCofExpansion());
        //                         submenu_system = true;
        //                     } else if (determinantSubMenuChoice == 3) {
        //                         system = true;
        //                     } else {
        //                         WRONG_INPUT_REMINDER();
        //                     }
        //                 } while (determinantSubMenuChoice <= 0 || determinantSubMenuChoice > 5 || submenu_system);
        //             } else if (submenu1Choice == 3) {
        //                 boolean submenu_system;
        //                 int inverseSubMenuChoice;
        //                 do {
        //                     SHOW_INVERSE_SUBMENU();
        //                     submenu_system = false;
        //                     ASK_FOR_CHOICE_MESSAGE();
        //                     inverseSubMenuChoice = scanner.nextInt();
        //                     scanner.nextLine();
        //                     if (inverseSubMenuChoice == 1) {
        //                         // Adjoint Matrix Method
        //                         submenu_system = true;
        //                     } else if (inverseSubMenuChoice == 2) {
        //                         // Row Elementary Transformation Method
        //                         submenu_system = true;
        //                     } else if (inverseSubMenuChoice == 3) {
                                
        //                         system = true;
        //                     } else {
        //                         WRONG_INPUT_REMINDER();
        //                     }
        //                 } while (inverseSubMenuChoice <= 0 || inverseSubMenuChoice > 3 || submenu_system);
        //             } else if (submenu1Choice == 4) {
        //                 System.out.println("Coming soon. Interpolation.");
        //             } else if (submenu1Choice == 5) {
        //                 System.out.println("Coming soon. Multiple linear regression.");
        //             } else if (submenu1Choice == 6) {
        //                 System.out.println("Coming soon. bicubic spline interpolation.");
        //             } else if (submenu1Choice == 7) {
        //                 System.exit(0);
        //             } else {
        //                 WRONG_INPUT_REMINDER();
        //             }
        //         } while (submenu1Choice <= 0 || submenu1Choice > 7 || system);
        //     } else {
        //         WRONG_INPUT_REMINDER();
        //     }

        
        //     scanner.close();
        
    }
}