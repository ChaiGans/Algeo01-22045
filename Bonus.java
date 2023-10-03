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

         public int checkPositionX(int cols){
        if(cols==0 || cols == 2){
            return 0;
        }
        else{
            return 1;
        }
    }
    public int checkPositionY(int cols){
        if(cols==0 || cols == 1){
            return 0;
        }
        else{
            return 1;
        }
    }
    public Matrix expansionMatrix(){
        int col = this.columns;
        int row = this.rows;
        int checkrow = 0;  //checkcol dan checkrow berupa penanda pada matrix hasil inputan
        int checkcol = 0;
        int expansionX = 0;
        int expansionY = 0;
        double val = 0;
        int turunan = 0;
        Matrix A = new Matrix(16,16);
        for(int a=0;a<16;a++){
            for(int b=0;b<16;b++){
                int x =this.checkPositionX(checkcol);
                int y =this.checkPositionY(checkcol);
                int j=expansionY;
                int i =expansionX;
                if (turunan ==0){
                    val = (Math.pow(x,i))*(Math.pow(y,j));
                }
                else if (turunan == 1){
                    if (i==0){
                        val = 0;
                    }
                    else{
                    val = i*(Math.pow(x,(i-1))*(Math.pow(y,j)));
                    }
                }
                else if (turunan == 2){
                    if (j==0){
                        val = 0;
                    }
                    else{
                    val = j*(Math.pow(x,i))*(Math.pow(y,(j-1)));
                    }
                }
                else{
                    if (j==0 || i ==0){
                        val =0;
                    }
                    else{
                    val = i*j*(Math.pow(x,(i-1)))*(Math.pow(y,(j-1)));
                    }
                }
                if(expansionX == col-1){
                    expansionY +=1;
                    expansionX = -1;
                }
                expansionX +=1;
                A.setELmt(a,b,val);
            }
            expansionY=0;
            if (checkcol == col-1){ //Setiap 1 baris sudah terisi pada matrix ekspansi, maka penanda bergeser
                checkrow +=1;
                checkcol =-1;
                turunan +=1;
            }
            checkcol+=1;
        }
        System.out.println("Didapat nilai hasil ekspansi atau A yaitu:");
        A.printMatrix();
        System.out.println();
        return A;
    }
    
    public Matrix multiply(Matrix m1,Matrix m2){
        int row = m1.rows;
        int cols = m2.columns;
        int cols1 = m1.columns;
        double m1value;
        double m2value;
        Matrix m3 = new Matrix(row,cols);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < cols; j++) {
                double sum=0.0;
                for (int k = 0; k < cols1; k++) {
                    m1value = m1.getElmt(i,k);
                    m2value = m2.getElmt(k,j);
                    sum += m1value * m2value;
                }
                m3.setELmt(i, j, sum);
            }
        }
        return m3;
    }

    public Matrix MatrixTurunan(){
        Matrix turunan = new Matrix (16,1);
        double Nilaiturunan;
        int p=0;
        int q=0;
        for(int i=0;i<16;i++){
            for (int j=0;j<1;j++){
                turunan.setELmt(i,j,this.getElmt(q,p));
                p+=1;
                if(p==4){
                    p=0;
                    q+=1;
                }
            }
        }
        System.out.println();
        System.out.println("Matrix Inputan:");
        turunan.printMatrix();
        System.out.println();
        return turunan;
    }

    public Matrix InverseExpansion(){
        Matrix expansion = this.expansionMatrix();
        expansion = expansion.InverseIdentitasTanpaCara();
        System.out.println("Matrix A invers :");
        expansion.printMatrix();
        return expansion;
    }
    public double bicubicSplineInterpolation(double x,double y){
        Matrix expansion = InverseExpansion();
        Matrix turunan = this.MatrixTurunan();
        Matrix alpha = this.multiply(expansion,turunan);
        System.out.println();
        System.out.println("Menghitung nilai alpha dengan rumus y=A.alpha");
        Matrix alphaconvert = new Matrix(4,4);
        int u=0;
        for (int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                alphaconvert.setELmt(j,i,alpha.getElmt(u,0));
                u++;
            }
        }
        alphaconvert.printMatrix();
        int newX;
        double val =1;
        int newY;
        Matrix newMatrixX = new Matrix(1,4);
        Matrix newMatrixY = new Matrix(4,1);
        for (int i=0;i<1;i++){ // Deply the X elements
            for (int j=0;j<4;j++){
                newMatrixX.setELmt(i,j,val);
                val = val*x;
            }
        }
        val = 1;
        for (int a=0;a<4;a++){ //Deploy Y elements
            for (int b=0;b<1;b++){
                newMatrixY.setELmt(a,b,val);
                val = val*y;
            }
        }
        System.out.println();
        System.out.println("Matrix X:");
        newMatrixX.printMatrix();
        System.out.println();
        System.out.println("Matrix Y:");
        newMatrixY.printMatrix();
        System.out.println();
        Matrix hasil = multiply(multiply(newMatrixX,alphaconvert),newMatrixY);
        System.out.println("Nilai f("+x+","+y+") adalah "+hasil.getElmt(0,0));
        return hasil.getElmt(0,0);

    }
    }
}
