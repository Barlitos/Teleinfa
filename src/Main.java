import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Podaj kod wejściowy (system dziesietny): ");
        Scanner scanner=new Scanner(System.in);
        int wejscie=scanner.nextInt();
        Hamming.koduj_Hamming(wejscie);
        //Crc16 crc16 = new Crc16();
        //crc16.crc16();
    }
}
