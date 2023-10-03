public class Bonus {
    //Meresize gambar dengan skala a menggunakan bicubic spline interpolation
    //Asumsi nilai grayscale dari image sudah dibuat menjadi matriks

    public void ubahImage (Matrix Image){
        // Mengubah 
    }

    public Matrix getD(Matrix Image){
    // Misal I(x,y) adalah nilai grayscale dari image yang juga berkorespon dengan baris-x kolom-y dari Matrix Image
    // Menggunakan persamaan
    // f(x,y) = I(x,y)
    // f_x(x,y) = ( I(x+1,y) - I(x-1,y))/2 dengan f_x(x,y) = Partial derivative of f(x,y) in respect to x
    // f_y(x,y) = ( I(x,y+1) - I(x,y-1))/2
    // f_xy(x,y) = (I(x+1,y+1) - I(x-1,y) - I(x,y-1) - I(x,y))/4
    // x,y = 0,1

        Matrix matrixD = new Matrix();




        return matrixD;
    }
}
