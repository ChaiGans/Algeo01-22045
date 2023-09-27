public class SPL {

    public static boolean isAllNull(Double[] row){
        for (int i = 0; i < row.length ; i++ ){
            if(row[i] != null){
                return false;
            }            
        }
        return true;
    }

    public static String outputSolution(Double[][] ans){
        StringBuffer[] str = new StringBuffer[ans.length];
        String[] strSol = new String[ans[0].length];
        StringBuffer strReal = new StringBuffer();
        for(int i = ans.length -1; i >= 0; i--){
            str[i] = new StringBuffer();
            if(isAllNull(ans[i])){
                str[i].append("x" + (i + 1) + " = " + (char)(i+65) + "\n");
                strSol[i] = Character.toString( (char)(i+65));
            }else{
                str[i].append("x" + (i + 1) + " = ");
                boolean first = true;
                for (int j = 0; j < ans[i].length ; j++){
                    if(ans[i][j] != null ){
                        if(j != ans[i].length -1 ){
                            if( ans[i][j] != 0D){
                                
                                if(!first){
                                    str[i].append( " + " + String.format("%.2f",ans[i][j] )+ strSol[j] );
                                }else{
                                    str[i].append( String.format("%.2f",ans[i][j] )+ strSol[j] );
                                    first = false;
                                } 
                            }                           
                        }else{
                            if(!first){
                                if(ans[i][j] != 0D){
                                    str[i].append( " + " + String.format("%.2f",ans[i][j]));
                                }
                                 
                            }else{
                                str[i].append( String.format("%.2f",ans[i][j])); 
                                first = false;
                            }                                
                        }   
                    }   
                }
                str[i].append("\n");
            }
        }

        for (int k = 0; k <ans.length ; k++){
            strReal.append(str[k].toString());
        }
        return strReal.toString();
    }

    public static void OperationMatrix(Matrix mMasuk){
        Double[][] solution = new Double[mMasuk.getKolom()-1][mMasuk.getKolom()]; // Variable untuk nampung solution
        int idx = 0,z = 0;
        boolean noSolution = false;
        
        
        while(z < mMasuk.getBaris()){
            if(mMasuk.isNoSolution(z)){
                noSolution = true;
                break;
            }else{
                z += 1;
            }
        }
        if(noSolution){
            System.out.println("There are no solution.");
        }else{
            for (int i = mMasuk.getBaris() - 1; i >=0 ; i--){
                // Find index leading 1
                if(mMasuk.isAllZero(i)){
                    continue;
                }else{
                    idx = 0;
                    while( idx < mMasuk.getKolom()){
                        if(mMasuk.getElmt(i, idx) == 1){
                            break;
                        }else{
                            idx += 1;
                        }
                    }

                    
                    int k = idx + 1;
                    while(k <= mMasuk.getKolom()-1){
                        if(k == mMasuk.getKolom()-1){
                            solution[idx][k] = mMasuk.getElmt(i, k);
                        }else{
                            if(mMasuk.getElmt(i, k) !=0){
                                solution[idx][k] = -(mMasuk.getElmt(i, k));
                            }else{
                                solution[idx][k] = (mMasuk.getElmt(i, k));
                            }
                            
                        }
                        k += 1;
                    
                    }
                
                }
            }
            
            for (int x = mMasuk.getKolom()-2; x >= 0; x--){
                if(isAllNull(solution[x])){
                    continue;
                }else{
                    for (int b = 0; b < mMasuk.getKolom(); b++){
                        if(solution[x][b] != null){
                            if(b != mMasuk.getKolom()-1){
                                if(isAllNull(solution[b]) == false){
                                    
                                    for(int l = b +1; l < mMasuk.getKolom(); l++){
                                        if(solution[b][l] != null){
                                            solution[x][l] += (solution[x][b] * solution[b][l]);
                                            
                                        }            
                                    }
                                    solution[x][b]= null;
                                }
                            }
                            
                        }
                    }
                }
            }


            System.out.println(outputSolution(solution));

        }
    }


    public static void GaussMethod(Matrix mMasuk){
        
        mMasuk = OBE.gauss(mMasuk); // Membuat Matrix jadi eselon 
        System.out.println("\nThis is the the OBE result: ");
        mMasuk.printMatriks();
        System.out.println("\n");

        
        
        OperationMatrix(mMasuk);
        
    }


    public static void GaussJordanMethod (Matrix mMasuk){

        mMasuk = OBE.gaussJordan(mMasuk);
        System.out.println("\nThis is the the OBE result: ");
        mMasuk.printMatriks();
        System.out.println("\n");

        OperationMatrix(mMasuk);
    }
}
