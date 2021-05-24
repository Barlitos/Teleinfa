import java.util.Scanner;

public class Crc16 { //to jest crc16 reverse
    static Scanner skaner = new Scanner(System.in);
    static void crc16(int input) {
        long dataIn = input;
        long poly = 0b1010000000000001;
        int reverse = 0b1111111111111111;
        long wynik = reverse ^ dataIn;
        char tem;
        int shiftcount = 1;
        while (shiftcount <= 8){
            int dlugosc = Long.toBinaryString(wynik).length();
            tem = Long.toBinaryString(wynik).charAt(dlugosc-1);
            wynik = wynik >>> 1;
            shiftcount++;
            if (tem == '1') {
                wynik = wynik ^ poly;
            }
        }
        long temp_licz=Long.toBinaryString(wynik).length();
        long licznikznakow=0;
        String pierwszykod = "";
        String drugikod = "";
        System.out.print("\nWynik CRC16 modbus = ");
        while(16 - temp_licz > 0) {
            System.out.print("0");
            pierwszykod = pierwszykod+"0";
            temp_licz++;
            licznikznakow++;
            }
        System.out.print(""+Long.toBinaryString(wynik));
        for(int i = 0; i < Long.toBinaryString(wynik).length(); i++){
            if(i+licznikznakow >= 8){
                drugikod = drugikod + Long.toBinaryString(wynik).charAt(i);
            } else pierwszykod = pierwszykod + Long.toBinaryString(wynik).charAt(i);
        }
        System.out.println("\n Pierwszy kod = "+pierwszykod + "\n Drugikod = "+drugikod);
        }
    }

