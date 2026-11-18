import java.io.File;
import java.util.Scanner;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class App {

    public static void main(String[] args) {
        new App().start();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Teller: ");
        String[] teller = scanner.nextLine().trim().split("\\s+");
        
        System.out.print("Perdeler: ");
        String[] perde = scanner.nextLine().trim().split("\\s+");
        
        System.out.print("Hız (ms): ");
        int hiz = scanner.nextInt();

        int uzunluk = Math.min(teller.length, perde.length);
        for (int i = 0; i < uzunluk; i++) {
            try {
                int telSayi = Integer.parseInt(teller[i]);
                int perdeSayi = Integer.parseInt(perde[i]);
                birleştirme(telSayi, perdeSayi, hiz);
            } catch (Exception e) {
            }
        }
        scanner.close();
    }

    public void birleştirme(int tel, int perde, int hiz) {
        if (tel == 0) {
            sesCal("bos.wav", hiz);
        } else if (tel >= 1 && tel <= 6 && perde >= 0 && perde <= 24) {
            String dosyaAdi = "tel_" + tel + "_perde_" + perde + ".wav";
            sesCal(dosyaAdi, hiz);
        } else {
            System.out.println("Geçersiz tel veya perde değeri: Tel " + tel + ", Perde " + perde);
        }
    }

    public void sesCal(String dosyaAdi, int hiz) {
        String dosyaYolu = System.getProperty("user.dir") + File.separator + "Sounds" + File.separator + dosyaAdi;
        File dosya = new File(dosyaYolu);

        if (dosya.exists()) {
            try {
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(dosya);
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);
                clip.start();
                Thread.sleep(hiz);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("Ses dosyası bulunamadı: " + dosyaYolu);
        }
    }
}
