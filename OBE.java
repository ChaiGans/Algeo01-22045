public class OBE {
    public static Matrix gauss(Matrix mMasuk){
        Matrix mKeluar = new Matrix();
        mKeluar = mMasuk.copyMatrix();
        int baris = mKeluar.getBaris();
        int kolom = mKeluar.getKolom();

        // while(j < kolom && ketemu == false){
        //     for(i = 0;i < baris; i++){
        //         if(mKeluar.getElmt(i, j) == 1){
        //         ketemu = false;
        //     }
        //     }
        //     j += 1;
        // }

        // if(ketemu && i != 0){
        //     mKeluar = mKeluar.changeRow(0, i);
        // }

        // double pembagi;

        // pembagi = mKeluar.getElmt(0, 0)
        int a = 0;
        boolean tidakNol = false;
        for (int i = 0; i < baris ; i++){
            tidakNol = false;
            while(tidakNol == false && i+a <kolom){
                if(mKeluar.getElmt(i, i+a) == 0){         
                    for(int j = i+1; j< baris; j++){
                        if(mKeluar.getElmt(j, i+a) != 0){
                            mKeluar= mKeluar.changeRow(i, j);
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
                double pembagi = mKeluar.getElmt(i, i+a);
                for( int k = (i +a) ; k < kolom; k++){
                    if(mKeluar.getElmt(i,k) != 0D){
                        mKeluar.setElmt(i, k,(double)mKeluar.getElmt(i, k)/ (double) pembagi);
                    }
                    
                }

                for (int j = i +1 ; j < baris ; j ++){
                    double faktorPengali = mKeluar.getElmt(j, i+a);
                    for (int k = i +a; k < kolom ; k++){
                        double hasilTemp = mKeluar.getElmt(j, k) - (faktorPengali * mKeluar.getElmt(i,k));
                        mKeluar.setElmt(j, k, hasilTemp);
                    }
                }
            }
            
            
              
            
        }   


        return mKeluar;
    }

    public static Matrix gaussJordan(Matrix mMasuk){
         Matrix mKeluar = new Matrix();
        mKeluar = mMasuk.copyMatrix();
        int baris = mKeluar.getBaris();
        int kolom = mKeluar.getKolom();

        // while(j < kolom && ketemu == false){
        //     for(i = 0;i < baris; i++){
        //         if(mKeluar.getElmt(i, j) == 1){
        //         ketemu = false;
        //     }
        //     }
        //     j += 1;
        // }

        // if(ketemu && i != 0){
        //     mKeluar = mKeluar.changeRow(0, i);
        // }

        // double pembagi;

        // pembagi = mKeluar.getElmt(0, 0)
        int a = 0;
        boolean tidakNol = false;
        for (int i = 0; i < baris ; i++){
            tidakNol = false;
            while(tidakNol == false && i+a <kolom){
                if(mKeluar.getElmt(i, i+a) == 0){         
                    for(int j = i+1; j< baris; j++){
                        if(mKeluar.getElmt(j, i+a) != 0){
                            mKeluar= mKeluar.changeRow(i, j);
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
                double pembagi = mKeluar.getElmt(i, i+a);
                for( int k = (i +a) ; k < kolom; k++){
                    if(mKeluar.getElmt(i,k) != 0D){
                        mKeluar.setElmt(i, k,(double)mKeluar.getElmt(i, k)/ (double) pembagi);
                    }
                    
                }

                for (int j = 0 ; j < baris ; j ++){
                    if(j != i){
                        double faktorPengali = mKeluar.getElmt(j, i+a);
                        for (int k = i +a; k < kolom ; k++){
                            double hasilTemp = mKeluar.getElmt(j, k) - (faktorPengali * mKeluar.getElmt(i,k));
                            mKeluar.setElmt(j, k, hasilTemp);
                        }
                    }
                    
                }
            }
            
            
              
            
        }   


        return mKeluar;
    }
}
