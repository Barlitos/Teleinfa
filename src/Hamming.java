public class Hamming {
    public static double log2 (int n) {
        return Math.log10(n)/Math.log10(2);
    }

    public static void koduj_Hamming(String kod_wejsciowy)
    {
    int dlugosc = kod_wejsciowy.length(), dodatkowa_dlugosc=0, j=0, sprawdzam;
    int[] tablica = new int[dlugosc];

        for (int i = 0; i < dlugosc; i++) {
            tablica[i]=Character.getNumericValue(kod_wejsciowy.charAt(i));
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

        System.out.println("Zakodowany ciąg: ");
        for (int i=0; i<dlugosc; i++)
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

    }
}
