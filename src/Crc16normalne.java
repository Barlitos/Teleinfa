import java.util.Random;
import java.util.Scanner;

public class Crc16normalne { // to jednak jest crc12
     long crc12norm(int input) {
         long dataIn = input;
         String wart_pocz = Long.toBinaryString(dataIn);
         long poly = 0b1100000001111;
         String temp = Long.toBinaryString(dataIn);
         temp += "000000000000";
         dataIn = Long.parseLong(temp, 2);
         int kres_petli = Long.toBinaryString(dataIn).length();
         String string_dataIn = "";
         long kawalek_dataIn = 0;
         for (int j = 12; j <= kres_petli - 1; j++) {
             if (j == 12) {
                 for (int i = 0; i <= 12; i++) {
                     string_dataIn += Long.toBinaryString(dataIn).charAt(i);
                 }
                 kawalek_dataIn = Long.parseLong(string_dataIn, 2);
                 kawalek_dataIn = kawalek_dataIn ^ poly;
                 string_dataIn = Long.toBinaryString(kawalek_dataIn);
             } else if (j >= 13) {
                 string_dataIn = Long.toBinaryString(kawalek_dataIn);
                 if (string_dataIn.length() < 12) {
                     int temp_licz = 0;
                     if (!(13 - string_dataIn.length() > kres_petli - j)) {
                         for (int k = 0; k <= 12 - Long.toBinaryString(kawalek_dataIn).length(); k++) {
                             string_dataIn += Long.toBinaryString(dataIn).charAt(j + k);
                             temp_licz++;
                         }
                         j = j + temp_licz - 1;
                         kawalek_dataIn = Long.parseLong(string_dataIn, 2);
                         kawalek_dataIn = kawalek_dataIn ^ poly;
                         string_dataIn = Long.toBinaryString(kawalek_dataIn);
                     } else if (13 - string_dataIn.length() > kres_petli - j) {
                         for (int k = 0; k < kres_petli - j; k++) {
                             string_dataIn += Long.toBinaryString(dataIn).charAt(j + k);
                             temp_licz++;
                         }
                         j = j + temp_licz - 1;
                     }
                 } else if (Long.toBinaryString(kawalek_dataIn).length() == 12) {
                     string_dataIn += Long.toBinaryString(dataIn).charAt(j - 1);
                     kawalek_dataIn = Long.parseLong(string_dataIn, 2);
                     kawalek_dataIn = kawalek_dataIn ^ poly;
                     string_dataIn = Long.toBinaryString(kawalek_dataIn);
                 }

             }

         }
         if (string_dataIn.length() < 12){
             String zera ="";
             for (int i = string_dataIn.length(); i < 12; i++) {
                 zera += "0";
             }
             zera += string_dataIn;

             wart_pocz += zera;
     } else wart_pocz += string_dataIn;
         //System.out.println( "Dane wejściowe zakodowane Crc12 = "+wart_pocz);
         return Long.parseLong(wart_pocz,2);
    }


    static long crc12normcheck(long input) {
        long dataIn = input;
        long poly = 0b1100000001111;
        int kres_petli = Long.toBinaryString(dataIn).length(); // dlugosc input
        String string_dataIn = "";

        long kawalek_dataIn=0;
       // System.out.println("wartosc pocz= "+Long.toBinaryString(dataIn) );
       // System.out.println("dane wklepane pocz= "+Long.toBinaryString(dataIn) );
        for (int j = 12; j < kres_petli;j++) {
           // System.out.println("-------------------------------- \n \t J = "+j+"\n ---------------------------------");
            if(j==12) {
                for (int i = 0; i <= 12; i++) {
                    string_dataIn += Long.toBinaryString(dataIn).charAt(i);
                }
                kawalek_dataIn = Long.parseLong(string_dataIn, 2);
                //System.out.println("\n kawalek data in po petli = " + Long.toBinaryString(kawalek_dataIn));
                kawalek_dataIn = kawalek_dataIn ^ poly;
                string_dataIn = Long.toBinaryString(kawalek_dataIn);
                //System.out.println("\n  WYNIKI TEGO CZEGOS = " + Long.toBinaryString(kawalek_dataIn) + "\t J =" + j);
            } else if (j >= 13) {
                string_dataIn = Long.toBinaryString(kawalek_dataIn);
                if(string_dataIn.length() < 12) {
                   // System.out.println("Wszedł do if");
                    int temp_licz = 0;
                    if (!(13 - string_dataIn.length() > kres_petli - j)) {
                        for (int k = 0; k <= 12 - Long.toBinaryString(kawalek_dataIn).length(); k++) {
                           // System.out.println("Początek pętli w if, wartosc string data in = " + string_dataIn + " , a j+k = " + (j + k));
                            string_dataIn += Long.toBinaryString(dataIn).charAt(j + k);
                           // System.out.println("Po dodaniu elementu nr " + k + " w if, wartosc string data in = " + string_dataIn);
                            temp_licz++;
                        }
                        //System.out.println("Po pętli w if 13 stoimy na tym == " + string_dataIn);
                        j = j + temp_licz - 1;
                        kawalek_dataIn = Long.parseLong(string_dataIn, 2);
                       // System.out.println("\n kawalek data in po petli = " + Long.toBinaryString(kawalek_dataIn));
                        kawalek_dataIn = kawalek_dataIn ^ poly;
                        string_dataIn = Long.toBinaryString(kawalek_dataIn);
                        //System.out.println("\n  WYNIKI TEGO CZEGOS = " + Long.toBinaryString(kawalek_dataIn) + "\t J =" + j);
                    } else if (13 - string_dataIn.length() > kres_petli - j) {
                        //System.out.println("Wszedł do else 1");
                       // System.out.println("(j+12-Long.toBinaryString(kawalek_dataIn).length()) < kres_petli) ==" + (j + 12 - Long.toBinaryString(kawalek_dataIn).length()) + "a kres pętli == " + kres_petli);
                        for (int k = 0; k < kres_petli - j; k++) {
                          //  System.out.println("Pętla iks de nr 2");
                            string_dataIn += Long.toBinaryString(dataIn).charAt(j + k);
                            temp_licz++;
                        }
                        j += temp_licz - 1;
                    }
                } else if(Long.toBinaryString(kawalek_dataIn).length() == 12){
                   // System.out.println("Wszedł do else i wrzuca mu = "+ Long.toBinaryString(dataIn).charAt(j));
                    string_dataIn += Long.toBinaryString(dataIn).charAt(j);
                   // System.out.println("Wszedł do else i stoi na tym == "+string_dataIn);
                    kawalek_dataIn = Long.parseLong(string_dataIn, 2);
                  //  System.out.println("\n kawalek data in po petli = " + Long.toBinaryString(kawalek_dataIn));
                    kawalek_dataIn = kawalek_dataIn ^ poly;
                    string_dataIn = Long.toBinaryString(kawalek_dataIn);
                   // System.out.println("\n  WYNIKI TEGO CZEGOS = " + Long.toBinaryString(kawalek_dataIn) + "\t J =" + j);
                }

            }

        }
        if(kawalek_dataIn == 0b0) System.out.println("Sprawdzenie CRC12 udane, dane nie zostały zakłamane");
        else System.out.println("Coś nie tak, zakłamana informacja/nieudane odkodowanie == " + string_dataIn);
        return input;
     }

    void czy_zaklocic_crc12(long zakodowane) {
        int dlugosc = Long.toBinaryString(zakodowane).length();
        char[] kod = Long.toBinaryString(zakodowane).toCharArray();
        String zaklamane ="";
        int odp, zaklocenie, odp2;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("\n\n\nCzy chcesz zakłócić sygnał?");
            System.out.println("TAK - 1");
            System.out.println("NIE - 0");
            System.out.print("Wybor: ");
            odp = scanner.nextInt();
        } while (odp > 1 || odp < 0);

        if (odp == 1) {
            do {
                System.out.println("\nWybierz opcje zakłócenia:");
                System.out.println("Losowo - 1");
                System.out.println("Wybrany bit - 2");
                System.out.print("Wybor: ");
                odp2 = scanner.nextInt();
            } while (odp2 < 1 || odp2 > 2);

            if (odp2 == 1) {
                Random random = new Random();
                zaklocenie = random.nextInt(dlugosc);
                if (kod[dlugosc - zaklocenie - 1] == '0') {
                    kod[dlugosc - zaklocenie - 1] = '1';
                } else if (kod[dlugosc - zaklocenie - 1] == '1') {
                    kod[dlugosc - zaklocenie - 1] = '0';
                }
                for (int i = 0; i < dlugosc; i++)
                    zaklamane += kod[i];
                System.out.println("\nZakodowany ciag po zakloceniu: "+ zaklamane);
                crc12normcheck(Long.parseLong(zaklamane,2));
            } else if (odp2 == 2) {
                do {
                    System.out.println("\nPodaj numer bitu do zakłócenia:");
                    System.out.print("Wybor: ");
                    zaklocenie = scanner.nextInt();
                } while (zaklocenie < 0 || zaklocenie > dlugosc - 1);
                if (kod[dlugosc - zaklocenie - 1] == '0') {
                    kod[dlugosc - zaklocenie - 1] = '1';
                } else if (kod[dlugosc - zaklocenie - 1] == '1'){
                    kod[dlugosc - zaklocenie - 1] = '0';
                }
                for (int i = 0; i < dlugosc; i++)
                    zaklamane += kod[i];
                System.out.println("\nZakodowany ciag po zakloceniu: "+ zaklamane);
                crc12normcheck(Long.parseLong(zaklamane,2));
            }

        }
    }
}