import java.util.Random;
import java.util.Scanner;

public class SDLC {
    static long sdlc_koduj(int input) {
        System.out.println("==========KODOWANIE SDLC=========\n");
        long dataIn = input;
        String wart_pocz = Long.toBinaryString(dataIn);
        long poly = 0b10001000000100101;
        String temp = Long.toBinaryString(dataIn);
        temp += "0000000000000000";
        dataIn = Long.parseLong(temp,2);
        int kres_petli = Long.toBinaryString(dataIn).length();
        String string_dataIn = "";
        long kawalek_dataIn=0;
        for (int j = 16; j <= kres_petli-1;j++) {
            if(j==16) {
                for (int i = 0; i <= 16; i++) {
                    string_dataIn += Long.toBinaryString(dataIn).charAt(i);
                }
                kawalek_dataIn = Long.parseLong(string_dataIn, 2);
                kawalek_dataIn = kawalek_dataIn ^ poly;
                string_dataIn = Long.toBinaryString(kawalek_dataIn);
            } else if (j >= 17) {
                string_dataIn = Long.toBinaryString(kawalek_dataIn);
                if(string_dataIn.length() < 16 ){
                    int temp_licz = 0;
                    if(!(17-string_dataIn.length() > kres_petli-j)){
                        for (int k = 0; k <= 16-Long.toBinaryString(kawalek_dataIn).length();k++){
                            string_dataIn += Long.toBinaryString(dataIn).charAt(j + k);
                            temp_licz++;
                        }
                        j = j + temp_licz-1;
                        kawalek_dataIn = Long.parseLong(string_dataIn, 2);
                        kawalek_dataIn = kawalek_dataIn ^ poly;
                        string_dataIn = Long.toBinaryString(kawalek_dataIn);
                    } else if(17-string_dataIn.length() > kres_petli-j){
                        for (int k = 0; k < kres_petli - j;k++){
                            string_dataIn += Long.toBinaryString(dataIn).charAt(j + k);
                            temp_licz++;
                        }
                        j = j + temp_licz-1;
                    }
                } else if(Long.toBinaryString(kawalek_dataIn).length() == 16){
                    string_dataIn += Long.toBinaryString(dataIn).charAt(j - 1);
                    kawalek_dataIn = Long.parseLong(string_dataIn, 2);
                    kawalek_dataIn = kawalek_dataIn ^ poly;
                    string_dataIn = Long.toBinaryString(kawalek_dataIn);
                }

            }

        }
        if (string_dataIn.length() < 16){
            String zera ="";
            for (int i = string_dataIn.length(); i < 16; i++) {
                zera += "0";
            }
            zera += string_dataIn;

            wart_pocz += zera;
        } else wart_pocz += string_dataIn;
        return Long.parseLong(wart_pocz,2);
    }

    static long sdlcCheck(long input) {
        long dataIn = input;
        long poly = 0b10001000000100101;
        int kres_petli = Long.toBinaryString(dataIn).length();
        String string_dataIn = "";
        long kawalek_dataIn=0;
        for (int j = 16; j < kres_petli;j++) {
            if(j==16) {
                for (int i = 0; i <= 16; i++) {
                    string_dataIn += Long.toBinaryString(dataIn).charAt(i);
                }
                kawalek_dataIn = Long.parseLong(string_dataIn, 2);
                kawalek_dataIn = kawalek_dataIn ^ poly;
                string_dataIn = Long.toBinaryString(kawalek_dataIn);
            } else if (j >= 17) {
                string_dataIn = Long.toBinaryString(kawalek_dataIn);
                if(string_dataIn.length() < 16) {
                    int temp_licz = 0;
                    if (!(17 - string_dataIn.length() > kres_petli - j)) {
                        for (int k = 0; k <= 16 - Long.toBinaryString(kawalek_dataIn).length(); k++) {
                            string_dataIn += Long.toBinaryString(dataIn).charAt(j + k);
                            temp_licz++;
                        }
                        j = j + temp_licz - 1;
                        kawalek_dataIn = Long.parseLong(string_dataIn, 2);
                        kawalek_dataIn = kawalek_dataIn ^ poly;
                        string_dataIn = Long.toBinaryString(kawalek_dataIn);
                    } else if (17 - string_dataIn.length() > kres_petli - j) {
                        for (int k = 0; k < kres_petli - j; k++) {
                            string_dataIn += Long.toBinaryString(dataIn).charAt(j + k);
                            temp_licz++;
                        }
                        j += temp_licz - 1;
                    }
                } else if(Long.toBinaryString(kawalek_dataIn).length() == 16){
                    string_dataIn += Long.toBinaryString(dataIn).charAt(j);
                    kawalek_dataIn = Long.parseLong(string_dataIn, 2);
                    kawalek_dataIn = kawalek_dataIn ^ poly;
                    string_dataIn = Long.toBinaryString(kawalek_dataIn);
                }
            }
        }

        if(kawalek_dataIn == 0b0) System.out.println("Sprawdzenie SDLC udane, dane nie zosta??y zak??amane");
        else System.out.println("co?? nie tak == " + string_dataIn);
        return input;
    }

    void czy_zaklocic_SDLC(long zakodowane) {
        int dlugosc = Long.toBinaryString(zakodowane).length();
        char[] kod = Long.toBinaryString(zakodowane).toCharArray();
        String zaklamane ="";
        int odp, zaklocenie, odp2;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("\n\n\nCzy chcesz zak????ci?? sygna???");
            System.out.println("TAK - 1");
            System.out.println("NIE - 0");
            System.out.print("Wybor: ");
            odp = scanner.nextInt();
        } while (odp > 1 || odp < 0);

        if (odp == 1) {
            do {
                System.out.println("\nWybierz opcje zak????cenia:");
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
                sdlcCheck(Long.parseLong(zaklamane,2));
            } else if (odp2 == 2) {
                do {
                    System.out.println("\nPodaj numer bitu do zak????cenia:");
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
                sdlcCheck(Long.parseLong(zaklamane,2));
            }

        }
        System.out.println("\n======== KONIEC KODOWANIA SDLC=======\n");
    } // do t??d
}
