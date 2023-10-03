import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class maindriverBicubic {
    public static void main(String[] args){
    Matrix tes = new Matrix(16, 1);


        for (int j = 0; j<16; j++){
            tes.data[j][0] = 0;
        }
    
    Matrix x = BicubicSpline.makeX(tes);

    x.printMatriks();
        
}
}
