import java.util.Random;
import java.util.Scanner;

public class Hamming {
    public static double log2 (int n) {
        return Math.log10(n)/Math.log10(2);
    }

    public static void koduj_Hamming(int kod_wejsciowy) {
        String wejscie=Integer.toBinaryString(kod_wejsciowy);
        int dlugosc = wejscie.length(), dodatkowa_dlugosc = 0, j = 0, sprawdzam;
        int[] tablica = new int[dlugosc];
        System.out.println("Binarny kod wejsciowy: ");
        for (int i = 0; i < dlugosc; i++) {
            tablica[i] = Character.getNumericValue(wejscie.charAt(i));
            System.out.print(tablica[i]);
        }


        for (int i=0; i<dlugosc; i++) {     //Sprawdzenie ile dodatkowych pozycji
            if(log2(i+1) - (int) log2(i+1)==0)
                dodatkowa_dlugosc++;
        }

    dlugosc += dodatkowa_dlugosc;
        if(log2(dlugosc) - (int) log2(dlugosc)==0) {  //dodaj 1 pozycje jezeli ostatnia pozycja to miejsce na kod
            dlugosc++;
            dodatkowa_dlugosc++;
        }

        int[] kod = new int[dlugosc];

        for (int i=0; i<dlugosc; i++) {     //Wstawiam 0 tam gdzie sa pozycje do policzenia
            if(log2(i+1) - (int) log2(i+1)==0) {
                kod[i] = 0;
            }
            else {
                kod[i]=tablica[j];
                j++;
            }
        }

    j = 0;
        for (int i=0; i<dlugosc; i++) {
            if(log2(i+1) - (int) log2(i+1)==0) {

                int k=i;
                while (k<dlugosc) {
                    sprawdzam = 0;
                    for(int l=0; l<i+1; l++) {

                        if(k+l==dlugosc)
                            sprawdzam=1;

                        if ((log2(k + 1 + l) - (int) log2(k + 1 + l) != 0) && (sprawdzam==0)) {
                            kod[i] += kod[k+l];
                        }
                    }
                    k=k+(2*(i+1));
                }
                kod[i] = kod[i]%2;
            } else {
                kod[i] = tablica[j];
                j++;
            }
        }

        System.out.println("\nKodowanie Hamminga: ");
        for (int i = 0; i < dlugosc; i++)
            System.out.print(kod[i]);

        int[] dekodowanie = new int[dlugosc-dodatkowa_dlugosc];
        j=0;
        for (int i=0; i<dlugosc; i++) {
            if(log2(i+1) - (int) log2(i+1)!=0) {
                dekodowanie[j] = kod[i];
                j++;
            }
        }

        System.out.println("\nZdekodowany ciag:");
        for (int i=0; i<dlugosc-dodatkowa_dlugosc; i++)
            System.out.print(dekodowanie[i]);

        int odp, zaklocenie, odp2;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("\n\n\nCzy chcesz zakłócić sygnał?");
            System.out.println("TAK - 1");
            System.out.println("NIE - 0");
            System.out.print("Wybor: ");
            odp = scanner.nextInt();
        }while (odp > 1 || odp < 0);

        if (odp==1) {
            do {
                System.out.println("\nWybierz opcje zakłócenia:");
                System.out.println("Losowo - 1");
                System.out.println("Wybrany bit - 2");
                System.out.print("Wybor: ");
                odp2 = scanner.nextInt();
            }while (odp2 < 1 || odp2 > 2);

            if (odp2==1) {
                Random random = new Random();
                zaklocenie = random.nextInt(dlugosc);
                //System.out.println(zaklocenie);

                if (kod[dlugosc-zaklocenie-1]==0) {
                    kod[dlugosc-zaklocenie-1]=1;
                }
                else if (kod[dlugosc-zaklocenie-1]==1) {
                    kod[dlugosc-zaklocenie-1]=0;
                }

                System.out.println("\nZakodowany ciag po zakloceniu:");
                for (int i=0; i<dlugosc; i++)
                    System.out.print(kod[i]);
            }
            else if(odp2==2) {
                do {
                    System.out.println("\nPodaj numer bitu do zakłócenia:");
                    System.out.print("Wybor: ");
                    zaklocenie = scanner.nextInt();
                }while(zaklocenie<0 || zaklocenie>dlugosc-1);

                if (kod[dlugosc-zaklocenie-1]==0) {
                    kod[dlugosc-zaklocenie-1]=1;
                }
                else if (kod[dlugosc-zaklocenie-1]==1) {
                    kod[dlugosc-zaklocenie-1]=0;
                }

                System.out.println("\nZakodowany ciag po zakloceniu:");
                for (int i=0; i<dlugosc; i++)
                    System.out.print(kod[i]);
            }

            j=0;
            for (int i=0; i<dlugosc; i++) {
                if(log2(i+1) - (int) log2(i+1)!=0) {
                    dekodowanie[j] = kod[i];
                    j++;
                }
            }

            System.out.println("\nZdekodowany ciag:");
            for (int i=0; i<dlugosc-dodatkowa_dlugosc; i++)
                System.out.print(dekodowanie[i]);
        }
    }
}
