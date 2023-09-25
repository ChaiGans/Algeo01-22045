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
            if(mKeluar.getElmt(i, i) == 0){
                for(int j = i+1; j< baris; j++){
                    if(mKeluar.getElmt(j, i) != 0){
                        mKeluar= mKeluar.changeRow(i, j);
                        tidakNol = true;
                    }
                } 
                if(tidakNol == false){
                    a += 1;
                }
            }
            if (i + a < kolom){
                double pembagi = mKeluar.getElmt(i, i+a);
                for( int k = (i +a) ; k < kolom; k++){
                mKeluar.setElmt(i, k,(double)mKeluar.getElmt(i, k)/ (double) pembagi);
                }

                for (int j = i +a +1 ; j < baris ; j ++){
                    double faktorPengali = mKeluar.getElmt(j, i+a);
                    for (int k = i +a; k < kolom ; k++){
                        double hasilTemp = mKeluar.getElmt(j, k) - (faktorPengali*mKeluar.getElmt(i,k));
                        mKeluar.setElmt(j, k, hasilTemp);
                    }
                }
            }
            
            
              
            
        }   


        return mKeluar;
    }
}
