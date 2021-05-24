import java.util.Scanner;

public class Crc16normalne {
    static Scanner skaner = new Scanner(System.in);
    static void crc16norm(int input) {
        int dataIn = input;
        int poly = 0b1000000000000101;
        int reverse = 0b1111111111111111;
        int wynik = reverse ^ dataIn;
        char tem;
        int shiftcount = 1;
        while (shiftcount <= 8){
            int dlugosc = Integer.toBinaryString(wynik).length();
            tem = Integer.toBinaryString(wynik).charAt(dlugosc-1);
            wynik = wynik >>> 1;
            shiftcount++;
            if (tem == '1') {
                wynik = wynik ^ poly;
            }
        }

        int temp_licz=Integer.toBinaryString(wynik).length();
        int licznikznakow=0;
        String pierwszykod = "";
        String drugikod = "";
        System.out.print("\nWynik CRC16 = ");
        while(16 - temp_licz > 0) {
            System.out.print("0");
            pierwszykod = pierwszykod+"0";
            temp_licz++;
            licznikznakow++;
        }
        System.out.print(""+Integer.toBinaryString(wynik));
        for(int i = 0; i < Integer.toBinaryString(wynik).length(); i++){
            if(i+licznikznakow >= 8){
                drugikod = drugikod + Integer.toBinaryString(wynik).charAt(i);
            } else pierwszykod = pierwszykod + Integer.toBinaryString(wynik).charAt(i);
        }
        System.out.println("\n Pierwszy kod = "+pierwszykod + "\n Drugikod = "+drugikod);
    }
}