import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Podaj kod wejściowy: ");
        Scanner scanner=new Scanner(System.in);
        String wejscie=scanner.nextLine();
        Hamming.koduj_Hamming(wejscie);
    }
}
