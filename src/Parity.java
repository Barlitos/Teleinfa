public class Parity {
    public static void encodeParity(int kod_wejsciowy)
    {
        String wejscie = Integer.toBinaryString(kod_wejsciowy);
        System.out.println(wejscie);
        int dlugosc = wejscie.length();
        int[] tablica = new int[dlugosc];

        for (int i = 0; i < dlugosc; i++) {
            tablica[i]=Character.getNumericValue(wejscie.charAt(i));
            System.out.print(tablica[i]);
        }
        System.out.println("\nd="+dlugosc);
        int dlugoscBajt = dlugosc/8;
        dlugosc+=dlugoscBajt;
        System.out.println("\nd="+dlugosc);
        int [] encodedData = new int[dlugosc];
        int count = 0;

        for(int i=0; i<dlugoscBajt; i++) {
            count=0;
            for(int j=0; j<8; j++) {
                encodedData[i*9+j+1] = tablica[i*8+j];
                count += tablica[i*8+j];
            }
            if(count%2==1)
                encodedData[i*9]=1;
            else
                encodedData[i*9]=0;
        }

        System.out.print("Pierwotny ciąg: ");
        for (int i=0; i<dlugosc-dlugoscBajt; i++)
            System.out.print(tablica[i]);
        System.out.print("\nZakodowany ciąg: ");
        for (int i=0; i<dlugosc; i++)
            System.out.print(encodedData[i]);
        decodeParity(encodedData, dlugosc);
    }

    public static void decodeParity(int tablica[], int dlugosc) {
        int dlugoscBajt = dlugosc/9;
        int count = 0;
        int error = 0;
        int[] decodedData = new int[dlugosc-dlugoscBajt];
        int[] poprawnosc = new int[dlugosc];

        for(int i=0; i<dlugoscBajt; i++) {
            count = 0;
            for(int j=0; j<8; j++) {
                decodedData[i*8+j] = tablica[i*9+j+1];
                count += tablica[i*9+j+1];
            }
            count += tablica[i*9]; //bit parzystosci
            if(count%2==0) { //nie wykryto bledow
                poprawnosc[i*9] = 3; //Poprawny bit parzystosci
                for(int j=1; j<9; j++)
                    poprawnosc[i*9+j]=0; //Poprawne bity danych
            }
            else {
                error++;
                poprawnosc[i*9]=5; //Niepewny bit kontrolny
                for(int j=1; j<9; j++)
                    poprawnosc[i*9+j]=2; //Niepewne bity danych
            }
        }
        System.out.print("\nDekodowane dane: ");
        for(int i=0; i<dlugosc-dlugoscBajt; i++)
            System.out.print(decodedData[i]);
        System.out.print("\nDane zakodowane po korekcji: ");
        for(int i=0; i<dlugosc; i++)
            System.out.print(poprawnosc[i]);
    }
}
