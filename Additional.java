public class Additional {
    public static void main(String[] args) {
        try {
            // Define the loading symbols
            char[] symbols = {'-', '\\', '|', '/'};

            // Loop for a fixed number of iterations (or until the task is completed)
            int iterations = 20;
            for (int i = 0; i < iterations; i++) {
                // Print the loading symbol
                System.out.print(symbols[i % symbols.length] + " ");

                // Sleep for a short time to control the animation speed
                Thread.sleep(200);

                // Clear the line by printing a carriage return and spaces
                System.out.print("\r            ");
            }

            // Print a message after the loading animation is done
        } catch (InterruptedException e) {
            // Handle the InterruptedException (e.g., logging or re-throwing)
            e.printStackTrace();
        }
    }
}
