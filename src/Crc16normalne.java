import java.util.Scanner;

public class Crc16normalne { // to jednak jest crc12
     long crc16norm(int input) {
        long dataIn = input;
        String wart_pocz = Long.toBinaryString(dataIn);
        long poly = 0b1100000001111;
        String temp = Long.toBinaryString(dataIn);
        temp += "000000000000";
        dataIn = Long.parseLong(temp,2);
        int kres_petli = Long.toBinaryString(dataIn).length();
         System.out.println("kres petli == " + kres_petli);
        String iloraz="";
        String string_dataIn = "";
        long kawalek_dataIn=0;
        for (int j = 12; j <= kres_petli-1;j++) {
            System.out.println("-------------------------------- \n \t J = "+j+"\n ---------------------------------");
            if(j==12) {
            for (int i = 0; i <= 12; i++) {
                string_dataIn += Long.toBinaryString(dataIn).charAt(i);
            }
                kawalek_dataIn = Long.parseLong(string_dataIn, 2);
                System.out.println("\n kawalek data in po petli = " + Long.toBinaryString(kawalek_dataIn));
                kawalek_dataIn = kawalek_dataIn ^ poly;
                string_dataIn = Long.toBinaryString(kawalek_dataIn);
                System.out.println("\n  WYNIKI TEGO CZEGOS = " + Long.toBinaryString(kawalek_dataIn) + "\t J =" + j);
            } else if (j >= 13) {
                    System.out.println("Wszedł do if >= 13");
                    string_dataIn = Long.toBinaryString(kawalek_dataIn);
                     if(string_dataIn.length() < 12 ){
                        System.out.println("Wszedł do if 2");
                        int temp_licz = 0;
                        System.out.println("12- Long.toBinaryString(kawalek_dataIn).length() =="+(12-Long.toBinaryString(kawalek_dataIn).length()));
                         System.out.println("13-string_dataIn.length() == "+(13-string_dataIn.length())+"  kres_petli-j=="+(kres_petli-j));
                        if(!(13-string_dataIn.length() > kres_petli-j)){
                            System.out.println("zwariowany if");
                        for (int k = 0; k <= 12-Long.toBinaryString(kawalek_dataIn).length();k++){
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
                        } else if(13-string_dataIn.length() > kres_petli-j){
                            System.out.println("13-string_dataIn.length() == "+(13-string_dataIn.length())+"  kres_petli-j=="+(kres_petli-j));
                            System.out.println("Wszedł do else 1");
                            for (int k = 0; k < kres_petli - j;k++){
                                System.out.println("Pętla iks de nr 2, j+k = "+(j+k));
                                string_dataIn += Long.toBinaryString(dataIn).charAt(j + k);
                                iloraz+="0";
                                temp_licz++;
                            }
                            j = j + temp_licz-1;
                        }
                    } else if(Long.toBinaryString(kawalek_dataIn).length() == 12){
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
        System.out.println("WYNIKI CRC12 = " +wart_pocz+ string_dataIn);
        wart_pocz += string_dataIn;
        return Long.parseLong(wart_pocz,2);
    }


    static void crc16normcheck(long input, long dane_pocz) {
       /* long dataIn = input;
        long poly = 0b1100000001111;
        // trzeba odjac od input dane_pocz żeby dowiedziec sie jak dlugioe wyszlo crc, jesli jest mniejsze niz 12, to trzeba je wyswietlic
        int kres_petli = Long.toBinaryString(dataIn).length(); // dlugosc input
        int dlugosc_dane_pocz = Long.toBinaryString(dane_pocz).length();
        int dlugosc_crc = kres_petli - dlugosc_dane_pocz;
        String iloraz="";
        String string_dataIn = "";
        if(dlugosc_crc < 12){
            String zera ="";
            System.out.println("kres petli = "+kres_petli+" dlugosc dane pocz ="+dlugosc_dane_pocz);
            for(int z = dlugosc_dane_pocz; z < kres_petli;z++){
                string_dataIn += Long.toBinaryString(dataIn).charAt(z);
            }
           for( int g = 0; g < 12 - string_dataIn.length();g++){
               zera+="0";
           }
           zera += string_dataIn;
            System.out.println("crc z zerami = "+zera);
        }else {
        long kawalek_dataIn=0;
        System.out.println("wartosc pocz= "+Long.toBinaryString(dataIn) );
        System.out.println("dane wklepane pocz= "+Long.toBinaryString(dataIn) );
        for (int j = 12; j < kres_petli;j++) {
            System.out.println("-------------------------------- \n \t J = "+j+"\n ---------------------------------");
            if(j==12) {
                for (int i = 0; i <= 12; i++) {
                    string_dataIn += Long.toBinaryString(dataIn).charAt(i);
                }
                kawalek_dataIn = Long.parseLong(string_dataIn, 2);
                System.out.println("\n kawalek data in po petli = " + Long.toBinaryString(kawalek_dataIn));
                kawalek_dataIn = kawalek_dataIn ^ poly;
                string_dataIn = Long.toBinaryString(kawalek_dataIn);
                System.out.println("\n  WYNIKI TEGO CZEGOS = " + Long.toBinaryString(kawalek_dataIn) + "\t J =" + j);
            } else if (j >= 13) {
                string_dataIn = Long.toBinaryString(kawalek_dataIn);
                if(string_dataIn.length() < 12) {
                    System.out.println("Wszedł do if");
                    int temp_licz = 0;
                    if (!(13 - string_dataIn.length() > kres_petli - j)) {
                        for (int k = 0; k <= 12 - Long.toBinaryString(kawalek_dataIn).length(); k++) {
                            System.out.println("Początek pętli w if, wartosc string data in = " + string_dataIn + " , a j+k = " + (j + k));
                            string_dataIn += Long.toBinaryString(dataIn).charAt(j + k);
                            System.out.println("Po dodaniu elementu nr " + k + " w if, wartosc string data in = " + string_dataIn);
                            iloraz += "0";
                            temp_licz++;
                        }
                        System.out.println("Po pętli w if 13 stoimy na tym == " + string_dataIn);
                        j = j + temp_licz - 1;
                        kawalek_dataIn = Long.parseLong(string_dataIn, 2);
                        System.out.println("\n kawalek data in po petli = " + Long.toBinaryString(kawalek_dataIn));
                        kawalek_dataIn = kawalek_dataIn ^ poly;
                        string_dataIn = Long.toBinaryString(kawalek_dataIn);
                        System.out.println("\n  WYNIKI TEGO CZEGOS = " + Long.toBinaryString(kawalek_dataIn) + "\t J =" + j);
                    } else if (13 - string_dataIn.length() > kres_petli - j) {
                        System.out.println("Wszedł do else 1");
                        System.out.println("(j+12-Long.toBinaryString(kawalek_dataIn).length()) < kres_petli) ==" + (j + 12 - Long.toBinaryString(kawalek_dataIn).length()) + "a kres pętli == " + kres_petli);
                        for (int k = 0; k < kres_petli - j; k++) {
                            System.out.println("Pętla iks de nr 2");
                            string_dataIn += Long.toBinaryString(dataIn).charAt(j + k);
                            iloraz += "0";
                            temp_licz++;
                        }
                        j += temp_licz - 1;
                    }
                } else if(Long.toBinaryString(kawalek_dataIn).length() == 12){
                    System.out.println("Wszedł do else i wrzuca mu = "+ Long.toBinaryString(dataIn).charAt(j));
                    string_dataIn += Long.toBinaryString(dataIn).charAt(j);
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
        }
        System.out.println("Iloraz ="+iloraz);
        System.out.println("WYNIKI CRC12 check = " + string_dataIn);
        if(Long.parseLong(string_dataIn,2) == 0b0) System.out.println("WYNIKI sprawdzenia CRC12 = " + string_dataIn);
        else System.out.println("coś nie tak == " + string_dataIn);
       // System.out.println("Iloraz ="+iloraz);
        // dla wartosci typu 0b0011 (w dec 3) nie dostaje tych zer co powinny być i zamiast 11000000011110 jest 1111110 przez co nie wchodzi nawet w obieg
    */}
}