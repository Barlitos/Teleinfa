import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Podaj kod wejściowy (system dziesietny): ");
        Scanner scanner=new Scanner(System.in);
        int wejscie=scanner.nextInt();
        Hamming.koduj_Hamming(wejscie);
        Crc16 crc16 = new Crc16();
        System.out.println("\n\nDane zakodowane CRC16 = " + Long.toBinaryString(crc16.crc16(wejscie)));
        crc16.czy_zaklocic_crc16(crc16.crc16check(crc16.crc16(wejscie)));
        Crc16normalne crc12 = new Crc16normalne();
        System.out.println("\n\nDane zakodowane CRC12 = " + Long.toBinaryString(crc12.crc12norm(wejscie)));
        crc12.czy_zaklocic_crc12(crc12.crc12normcheck(crc12.crc12norm(wejscie)));

    }
}
