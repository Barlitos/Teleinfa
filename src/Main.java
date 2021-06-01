import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Podaj kod wejściowy (system dziesietny): ");
        Scanner scanner=new Scanner(System.in);
        int wejscie=scanner.nextInt();
        Hamming.koduj_Hamming(wejscie);
        Crc16 crc16 = new Crc16();
        System.out.println("Dane zakodowane CRC16 = " + Long.toBinaryString(Crc16.crc16(wejscie)));
        crc16.czy_zaklocic_crc16(Crc16.crc16check(Crc16.crc16(wejscie)));
        Crc16normalne crc12 = new Crc16normalne();
        System.out.println("Dane zakodowane CRC12 = " + Long.toBinaryString(crc12.crc12norm(wejscie)));
        crc12.czy_zaklocic_crc12(Crc16normalne.crc12normcheck(crc12.crc12norm(wejscie)));
        SDLC sdlc=new SDLC();
        System.out.println("Dane zakodowane SDLC =" + Long.toBinaryString(SDLC.sdlc_koduj(wejscie)));
        sdlc.czy_zaklocic_SDLC(SDLC.sdlcCheck(SDLC.sdlc_koduj(wejscie)));
    }
}
