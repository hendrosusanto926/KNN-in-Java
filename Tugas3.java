import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Tugas3 {
    static int k =7;
    static double euclidan(String[] test, String[] train){
        return Math.sqrt( 
                   Math.pow( (Double.parseDouble(train[1])-Double.parseDouble(test[1])),2)+
                   Math.pow( (Double.parseDouble(train[2])-Double.parseDouble(test[2])),2)+
                   Math.pow( (Double.parseDouble(train[3])-Double.parseDouble(test[3])),2)+
                   Math.pow( (Double.parseDouble(train[4])-Double.parseDouble(test[4])),2)+
                   Math.pow( (Double.parseDouble(train[5])-Double.parseDouble(test[5])),2) );
    }
    static double[][] check(double[][] kterdekat, double a, double b){
        int idmax=0;
        for(int i=0; i<kterdekat.length; i++){
            if(kterdekat[idmax][0]<kterdekat[i][0]) idmax=i;
        }
        if(kterdekat[idmax][0]>a){
            kterdekat[idmax][0]=a;
            kterdekat[idmax][1]=b;
        }
        return kterdekat;
    }
    static double[][] resetK(){
        double[][] kterdekat = new double[k][k];
        for (int i=0; i<k; i++){
            kterdekat[i][0]=1000000;
            kterdekat[i][1]=0;
        }
        return kterdekat;
    }
    static double voting(double[][] kterdekat){
        double nol=0; double satu=0; double dua=0; double tiga=0;
        for (int i = 0; i < kterdekat.length; i++) {
            if (kterdekat[i][1]==0.0) nol++;
            else if (kterdekat[i][1]==1.0) satu++;
            else if (kterdekat[i][1]==2.0) dua++;
            else tiga++;
        }
        if (nol>=satu && nol>=dua && nol>=tiga) return 0;
        else if (satu>=nol && satu>=dua && satu>=tiga)return 1;
        else if (dua>=satu && dua>=satu && dua>=tiga) return 2;
        else return 3;
    }
            
    public static void main(String[] args) throws FileNotFoundException, IOException {
        double[][] kterdekat = resetK();        
        File filetest= new File("DataTest_Tugas3_AI.csv");
        File filetrain= new File("DataTrain_Tugas3_AI.csv");
        Scanner test= new Scanner(filetest);
        test.nextLine();
        Scanner train = new Scanner(filetrain);
        train.nextLine();
        FileWriter fw = new FileWriter("TebakanTugas3.csv");
        while (test.hasNext()){
           String t = test.nextLine();
           while (train.hasNext()){
               String[] x=train.nextLine().split(",");
               kterdekat=check(kterdekat, euclidan(t.split(","),x),Double.parseDouble(x[6]));
           }
           train = new Scanner(filetrain);
           train.nextLine();
           try{
               fw.append(String.valueOf((int) voting(kterdekat))+'\n');
           }catch(Exception e){
           }
           kterdekat=resetK();
        }
        fw.flush();
        fw.close();
    }
}