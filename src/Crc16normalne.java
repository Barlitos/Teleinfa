import java.util.Scanner;

public class Crc16normalne {
    static void crc16norm(int input) {
        long dataIn = input;
        long poly = 0b1100000001111;
        long gdy_zero = 0;
        String temp = Long.toBinaryString(dataIn);
        temp += "000000000000";
        dataIn = Long.parseLong(temp,2);
        temp = Long.toBinaryString(dataIn);
        int kres_petli = Long.toBinaryString(dataIn).length();
        String iloraz="0";
        String string_dataIn = "";
        long kawalek_dataIn=0;
        for (int j = 12; j < kres_petli-1;j++) {
            if(j==12) {
            for (int i = 0; i <= 12; i++) {
                string_dataIn += Long.toBinaryString(dataIn).charAt(i);
            }
            }
                if (j >= 13) {
                    string_dataIn = Long.toBinaryString(kawalek_dataIn);
                    iloraz+="1";
                    if(Long.toBinaryString(kawalek_dataIn).length() < 12){
                        for (int k = 0; k <= 12-Long.toBinaryString(kawalek_dataIn).length();k++){
                            string_dataIn += Long.toBinaryString(dataIn).charAt(j + 1 + k);
                            iloraz+="0";
                        }
                        j = j + (12-Long.toBinaryString(kawalek_dataIn).length());
                    } else if(Long.toBinaryString(kawalek_dataIn).length() == 12) string_dataIn += Long.toBinaryString(dataIn).charAt(j + 1);

                }
                kawalek_dataIn = Long.parseLong(string_dataIn, 2);
                //System.out.println("\n kawalek data in po petli = " + Long.toBinaryString(kawalek_dataIn));
                kawalek_dataIn = kawalek_dataIn ^ poly;
                string_dataIn = Long.toBinaryString(kawalek_dataIn);
                //System.out.println("\n  WYNIKI TEGO CZEGOS = " + Long.toBinaryString(kawalek_dataIn) + "\t J =" + j);
        }
        System.out.println("Iloraz ="+iloraz);
        System.out.println("WYNIKI CRC12 = " + Long.toBinaryString(kawalek_dataIn));
    }
}