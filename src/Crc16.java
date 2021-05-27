import java.util.Scanner;

public class Crc16 { //to jest crc16 reverse
    static Scanner skaner = new Scanner(System.in);
    static void crc16(int input) {
        /*long dataIn = input;
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

         */
        long dataIn = input;
        String wart_pocz = Long.toBinaryString(dataIn);
        long poly = 0b11000000000000101;
        String temp = Long.toBinaryString(dataIn);
        temp += "0000000000000000";
        dataIn = Long.parseLong(temp,2);
        int kres_petli = Long.toBinaryString(dataIn).length();
        System.out.println("kres petli == " + kres_petli);
        String iloraz="";
        String string_dataIn = "";
        long kawalek_dataIn=0;
        for (int j = 16; j <= kres_petli-1;j++) {
            System.out.println("-------------------------------- \n \t J = "+j+"\n ---------------------------------");
            if(j==16) {
                for (int i = 0; i <= 16; i++) {
                    string_dataIn += Long.toBinaryString(dataIn).charAt(i);
                }
                kawalek_dataIn = Long.parseLong(string_dataIn, 2);
                System.out.println("\n kawalek data in po petli = " + Long.toBinaryString(kawalek_dataIn));
                kawalek_dataIn = kawalek_dataIn ^ poly;
                string_dataIn = Long.toBinaryString(kawalek_dataIn);
                System.out.println("\n  WYNIKI TEGO CZEGOS = " + Long.toBinaryString(kawalek_dataIn) + "\t J =" + j);
            } else if (j >= 17) {
                System.out.println("Wszedł do if >= 13");
                string_dataIn = Long.toBinaryString(kawalek_dataIn);
                if(string_dataIn.length() < 16 ){
                    System.out.println("Wszedł do if 2");
                    int temp_licz = 0;
                    System.out.println("12- Long.toBinaryString(kawalek_dataIn).length() =="+(17-Long.toBinaryString(kawalek_dataIn).length()));
                    System.out.println("13-string_dataIn.length() == "+(17-string_dataIn.length())+"  kres_petli-j=="+(kres_petli-j));
                    if(!(13-string_dataIn.length() > kres_petli-j)){
                        System.out.println("zwariowany if");
                        for (int k = 0; k <= 16-Long.toBinaryString(kawalek_dataIn).length();k++){
                            string_dataIn += Long.toBinaryString(dataIn).charAt(j + k);
                            iloraz+="0";
                            temp_licz++;
                        }
                        System.out.println("Po pętli w if 13 stoimy na tym == "+string_dataIn);
                        j = j + temp_licz-1;
                        kawalek_dataIn = Long.parseLong(string_dataIn, 2);
                        System.out.println("\n kawalek data in po petli = " + Long.toBinaryString(kawalek_dataIn));
                        kawalek_dataIn = kawalek_dataIn ^ poly;
                        string_dataIn = Long.toBinaryString(kawalek_dataIn);
                        System.out.println("\n  WYNIKI TEGO CZEGOS = " + Long.toBinaryString(kawalek_dataIn) + "\t J =" + j);
                    } else if(17-string_dataIn.length() > kres_petli-j){
                        System.out.println("13-string_dataIn.length() == "+(17-string_dataIn.length())+"  kres_petli-j=="+(kres_petli-j));
                        System.out.println("Wszedł do else 1");
                        for (int k = 0; k < kres_petli - j;k++){
                            System.out.println("Pętla iks de nr 2, j+k = "+(j+k));
                            string_dataIn += Long.toBinaryString(dataIn).charAt(j + k);
                            iloraz+="0";
                            temp_licz++;
                        }
                        j = j + temp_licz-1;
                    }
                } else if(Long.toBinaryString(kawalek_dataIn).length() == 16){
                    System.out.println("Wszedł do else 2");
                    string_dataIn += Long.toBinaryString(dataIn).charAt(j - 1);
                    iloraz+="1";
                    System.out.println("Wszedł do else i stoi na tym == "+string_dataIn);
                    kawalek_dataIn = Long.parseLong(string_dataIn, 2);
                    System.out.println("\n kawalek data in po petli = " + Long.toBinaryString(kawalek_dataIn));
                    kawalek_dataIn = kawalek_dataIn ^ poly;
                    string_dataIn = Long.toBinaryString(kawalek_dataIn);
                    System.out.println("\n  WYNIKI TEGO CZEGOS = " + Long.toBinaryString(kawalek_dataIn) + "\t J =" + j);
                }

            }

        }
        System.out.println("Iloraz ="+iloraz);
        System.out.println("WYNIKI CRC16 = " +wart_pocz+ string_dataIn);
        }
    }

