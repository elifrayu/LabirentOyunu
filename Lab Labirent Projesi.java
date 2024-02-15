/*
@file Labirent Projesi

@date 30.12.2023
@assignment birinci proje ödevi
@author Elif Uyar /elif.uyar@stu.fsm.edu.tr
*/
import java.util.Scanner;
public class Main {
    public static int adimsayisi = 0;
    public static int hamlesayısı = 0;

    public static void main(String[] args) {
        char[][] labirent = {{'#', '!', '.', '.', 'R', '.', '.', '.', '.', '.', '.', '#', '.', '.', '.'},
        {'.', '.', '#', '.', '.', '.', '#', '.', 'H', '.', '.', '.', '.', '.', '!'},
        {'F', '.', '.', '.', '#', '.', '!', '.', '.', 'R', '.', '.', '#', '#', '.'},
        {'.', '.', '#', '.', '.', '#', '.', '.', '.', '.', 'F', '.', '.', '.', '.'},
        {'.', '!', '.', '.', '#', '.', '#', '.', '#', '.', '.', '#', '.', '.', '.'},
        {'.', '.', 'H', '.', '.', '!', '.', '.', 'H', '.', '.', 'F', '.', '.', 'R'},
        {'#', '#', '#', '#', '.', '.', '#', '.', '.', '.', 'T', '.', '.', '.', 'E'},
        {'.', '.', '#', '.', 'F', '.', '#', '#', '.', '#', '#', '#', '#', '.', '.'},
        {'.', '#', '.', '.', '.', '.', '!', '.', '#', '.', '.', '.', '#', '.', '.'},
        {'.', 'T', 'T', '.', '#', '#', '.', '.', '.', '.', 'T', '.', '.', '.', 'R'},
        {'.', '.', '.', '#', '.', '.', '.', '#', '.', '#', '.', '#', '.', 'T', '.'},
        {'B', '.', '#', '.', '.', '!', '.', '!', '.', '.', '.', '.', '.', '.', '#'},
        {'.', '.', '.', 'F', '!', '.', '.', '.', 'H', '.', '.', 'R', '.', '.', '.'},
        {'.', '.', 'H', '.', '.', '.', '!', '.', '.', '.', '#', '.', '.', '#', '.'},
        {'.', '.', '.', '#', '.', '.', '#', '.', '#', '.', '#', '.', '.', '#', '#'}};
int[] baslangıc = baslamanoktaları(labirent);
        int[] bıtıs = bitişnoktaları(labirent);
        int[] konum = new int[2];

        konum = baslamanoktaları(labirent);
        int[] counters = new int[4];
        Scanner scanner = new Scanner(System.in);

        char hareketYonu;
        while (true) {
            labirentyazdır(labirent, konum);
            System.out.println("Son Konum : ( " + konum[0] + " , " + konum[1] + " )");
            System.out.print("Yön W, A, S, D girin veya çıkmak için E,bonus kullanmak için + tuşuna basınız. ");
            String hareket = scanner.next().toUpperCase();
            hareketYonu = hareket.charAt(0);
            if (hareketYonu == 'E') {
                System.out.println("oyundan çıktınız.");
                break;
            }
            if (hareketYonu != 'E') {
                hareket(labirent, konum, hareketYonu, counters);
                
                if (labirent[konum[0]][konum[1]] == '!') {
                    mayınilekarsılasma(labirent, counters, konum);
                }
                adimsayisi++;
            }
            System.out.println("Toplam adım sayısı: " + adimsayisi);
            System.out.println("Hamle sayısı: " + hamlesayısı);

            if (adimsayisi == 5) {
                System.out.println(" ");
                randomshuffle(labirent);
                adimsayisi = 0;
                System.out.println("  ");
            }

            if (labirent[konum[0]][konum[1]] == '#') {
                duvarIleKarsilasma(labirent, counters, konum, hareketYonu);
            }
        }
    }

    public static void labirentyazdır(char[][] labirent, int[] konum) {

        for (int i = 0; i < labirent.length; i++) {
            for (int j = 0; j < labirent[i].length; j++) {
                if (i == konum[0] && j == konum[1]) {
                    System.out.print("* ");
                } else {
                    System.out.print(labirent[i][j] + " ");
                }

            }
            System.out.println();
        }
    }

    public static int[] baslamanoktaları(char[][] labirent) {
        int[] baslangıc = new int[2];

        for (int i = 0; i < labirent.length; i++) {
            for (int j = 0; j < labirent[i].length; j++) {
                if (labirent[i][j] == 'B') {
                    baslangıc[0] = i;
                    baslangıc[1] = j;
                }
            }
        }

        return baslangıc;

    }

    public static int[] bitişnoktaları(char[][] labirent) {

        int[] bıtıs = new int[labirent.length];
        for (int i = 0; i < labirent.length; i++) {
            for (int j = 0; j < labirent[i].length; j++) {
                if (labirent[i][j] == 'E') {
                    int bıtıssatır = i;
                    int bıtıssutun = j;

                }
            }

        }
        return bıtıs;
    }
        

    public static void bonusTkullanma(char[][] labirent, int counters[], int[] konum) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            if (counters[0] > 0) {

                System.out.println("Işınlanmak istediğiniz değerleri giriniz: ");
                System.out.print("x girin: ");
                int yenıx = scanner.nextInt();
                System.out.print("y girin: ");
                int yenıy = scanner.nextInt();

                if (labirent[yenıx][yenıy] != '!' || labirent[yenıx][yenıy] != '#'||yenıx>0 ||yenıy>0||yenıx<=labirent.length||yenıy<=labirent[0].length) {
                    konum[0] = yenıx;
                    konum[1] = yenıy;
                    counters[0]--;
                    break;
                } else {
                    System.out.println("Işınlanmak istediğiniz yer # veya !, başka bir ışınlanma noktası seçin.");

                }

            }
        }
    }

//  
    public static int[] bonusRkullanma(char[][] labirent, int counters[], int[] konum, Scanner scanner) {
        for (int i = 0; i < labirent.length; i++) {
            for (int j = 0; j < labirent[i].length; j++) {

                if (labirent[i][j] == '#' && counters[1] > 0) {
                    System.out.println("Duvar ile karşılaşıldı.");
                    System.out.print("Duvar Kaldırma Bonusu Kullanılsın mı? (E/H): ");

                    char cevap = scanner.next().charAt(0);
                    char upperCevap = Character.toUpperCase(cevap);

                    while (upperCevap != 'E' && upperCevap != 'H') {
                        System.out.println("Geçersiz bir seçenek. Tekrar deneyin.");
                        System.out.print("Duvar Kaldırma Bonusu Kullanılsın mı? (E/H): ");
                        cevap = scanner.next().charAt(0);
                        upperCevap = Character.toUpperCase(cevap);
                    }

                    if (upperCevap == 'E') {
                        labirent[i][j] = '.';
                        counters[1]--;
                        System.out.println("Duvar Kaldırma Bonusu Kullanıldı! Duvar Kaldırıldı.");
                    }

                    konum[0] = i;
                    konum[1] = j;
                }
            }
        }
        return konum;
    }

    public static int[] bonusHkullanma(int[] counters) {
        if (counters[2] > 0) {
            System.out.println("Hamle sayısı azaltma bonusu kullanılıyor.");
            hamlesayısı -= 2;
            counters[2]--;
            System.out.println("Hamle sayısı 2 azaltıldı. Yeni hamle sayısı: " + hamlesayısı);
        } else {
            System.out.println("Hamle sayısı azaltma bonusunuz kalmadı.");
        }

        return new int[]{adimsayisi, counters[2]};
    }

    public static void duvarIleKarsilasma(char[][] labirent, int[] counters, int[] konum, char hareketYonu) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Duvar ile karşılaştınız. Ne yapmak istersiniz?");
        System.out.println("1. R bonusunu kullan");
        System.out.println("2. Başka yöne git");
        System.out.print("Seçiminiz: ");

        int secim = scanner.nextInt();

        switch (secim) {
            case 1:
                if (counters[1] <= 0) {
                    System.out.println("R bonusunuz yok");
                    System.out.print("Yeni yönlendirme yapın (W, S, A, D): ");
                    if (hareketYonu == '#') {
                        duvarIleKarsilasma(labirent, counters, konum, hareketYonu);
                    } else if (hareketYonu == 'D') {
                        konum[1]--;
                    } else if (hareketYonu == 'W') {
                        konum[0]--;
                    } else if (hareketYonu == 'S') {
                        konum[0]--;
                    } else if (hareketYonu == 'A') {
                        konum[1]++;
                    }
                    hareketYonu = scanner.next().charAt(0);
                    hareket(labirent, konum, hareketYonu, counters);
                    break;
                }
                if (counters[1] > 0) {
                    bonusRkullanma(labirent, counters, konum, scanner);
                    System.out.println("R bonusu kullanıldı");
                    counters[1]--;
                } else {
                    System.out.println("Geçersiz bir seçim yaptınız. Lütfen tekrar deneyin.");
                }

            case 2:
                System.out.print("Yeni yönlendirme yapın (W, S, A, D): ");
                if (hareketYonu == 'D') {
                    konum[1]--;
                } else if (hareketYonu == 'W') {
                    konum[0]--;
                } else if (hareketYonu == 'S') {
                    konum[0]--;
                } else if (hareketYonu == 'A') {
                    konum[1]++;
                }
                hareketYonu = scanner.next().charAt(0);
                hareket(labirent, konum, hareketYonu, counters);
                break;

            default:
                System.out.println("Geçersiz bir seçim yaptınız. Lütfen tekrar deneyin.");
                duvarIleKarsilasma(labirent, counters, konum, hareketYonu);
                break;
        }
    }

public static void mayınilekarsılasma(char[][] labirent, int[] counters, int[] konum) {
    Scanner scanner = new Scanner(System.in);

    if (counters[3] > 0) {
        System.out.println("Mayın ile karşılaşıldı.");
        System.out.print("Mayın Çözme Bonusu Kullanılsın mı? (E/H): ");

        char cevap;

        while (true) {
            cevap = Character.toUpperCase(scanner.next().charAt(0));

            if (cevap == 'E' || cevap == 'H') {
                break;
            } else {
                System.out.println("Geçersiz bir seçenek. Tekrar deneyin.");
                System.out.print("Mayın Çözme Bonusu Kullanılsın mı? (E/H): ");
            }
        }

        if (cevap == 'E') {
            labirent[konum[0]][konum[1]] = '.';
            counters[3]--;
            System.out.println("Mayın Çözme Bonusu Kullanıldı! Mayın Çözüldü.");
        } else {
            System.out.println("Mayına Çarptınız, bonus kullanmadınız. Hamle sayınız 5 arttı.");
            labirent[konum[0]][konum[1]] = '.';
        }

    } else {
        System.out.println("Mayın kaldırma bonusunuz yok. Mayın patladı. Hamle sayınız 5 arttı.");
        labirent[konum[0]][konum[1]] = '.';
        hamlesayısı+=5; // counters dizisinin beşinci elemanı hamlesayısı ise
    }
}

    public static void randomshuffle(char[][] labirent) {

        for (int i = 0; i < labirent.length; i++) {
            for (int j = 0; j < labirent[i].length; j++) {
                if (labirent[i][j] == 'T' || labirent[i][j] == 'H' || labirent[i][j] == 'F' || labirent[i][j] == 'R') {
                    int i1, j1;
                    do {
                        i1 = (int) (Math.random() * labirent.length);
                        j1 = (int) (Math.random() * labirent[i].length);
                        char temp = labirent[i][j];
                        labirent[i][j] = labirent[i1][j1];
                        labirent[i1][j1] = temp;
                    } while (labirent[i1][j1] == '#' || labirent[i1][j1] == '!' || labirent[i1][j1] == 'E' || labirent[i1][j1] == 'B');

                    if (labirent[i][j] == '!') {

                        int i2, j2;
                        do {
                            i2 = (int) (Math.random() * labirent.length);
                            j2 = (int) (Math.random() * labirent[i2].length);
                        } while (labirent[i2][j2] == '#' || labirent[i2][j2] == '!' || labirent[i2][j2] == 'E' || labirent[i2][j2] == 'B' || labirent[i2][j2] == 'T' || labirent[i2][j2] == 'H' || labirent[i2][j2] == 'F' || labirent[i2][j2] == 'R');

                        char temp = labirent[i][j];
                        labirent[i][j] = labirent[i2][j2];
                        labirent[i2][j2] = temp;
                    }
                }
            }
        }

    }
   public static void hareket(char[][] labirent, int[] konum, char hareketYonu, int[] counters) {
        int yenıi = konum[0];
        int yenıj = konum[1];

        switch (hareketYonu) {
            case 'W':
                yenıi--;
                if(gecerliHareket(labirent, yenıi,yenıj)){
                konum[0] = yenıi;
                hamlesayısı++;
                    System.out.println("Yukarıya gittiniz.");
                }
                break;
            case 'A':
                yenıj--;
                if(gecerliHareket(labirent, yenıi,yenıj)){
                konum[1] = yenıj;
                hamlesayısı++;
                    System.out.println("Sola gittiniz.");
                }
                break;
            case 'S':
                yenıi++;
                if(gecerliHareket(labirent, yenıi,yenıj)){
                konum[0] = yenıi;
                hamlesayısı++;
                    System.out.println("Aşağı gittiniz");
                }
                break;
            case 'D':
               
                yenıj++;
                if(gecerliHareket(labirent, yenıi,yenıj)){
                konum[1] = yenıj;
                hamlesayısı++;
                    System.out.println("Sağa gittiniz.");
                }
                break;
            case '+':
                Scanner scanner = new Scanner(System.in);
                System.out.print("lütfen kullanmak istediğiniz bonusu giriniz: T,R,H,F \n ");

                System.out.println("Bonus T=" + counters[0]);
                System.out.println("Bonus R=" + counters[1]);
                System.out.println("Bonus H=" + counters[2]);
                System.out.println("Bonus F=" + counters[3]);

                char bonus = Character.toUpperCase(scanner.next().charAt(0));

                if (bonus == 'T') {
                    bonusTkullanma(labirent, counters, konum);
                } if (bonus == 'R') {
                    bonusRkullanma(labirent, counters, konum, scanner);
                } if (bonus == 'H') {
                    bonusHkullanma(counters);
                } if (bonus == 'F') {
                    mayınilekarsılasma(labirent, counters, konum);
                } else {
                    System.out.println("Geçersiz bonus türü.");
                }
                break;

            default:
                System.out.println("Geçersiz hareket yönü! Lütfen W, A, S, D veya + tuşlarından birini girin.");
        }

        char bulundugumyer = labirent[konum[0]][konum[1]];

        if (bulundugumyer == 'T') {
            counters[0]++;
            System.out.println("T bonusu listeye eklendi.");
            bulundugumyer = '.';
            System.out.println(counters[0]);

        }

        if (bulundugumyer == 'R') {
            counters[1]++;
            System.out.println("R bonusu listeye eklendi.");
            bulundugumyer = '.';
            System.out.println(counters[1]);

        }
        if (bulundugumyer == 'H') {
            counters[2]++;
            bulundugumyer = '.';
            System.out.println("H bonusu listeye eklendi.");
            System.out.println(counters[2]);
        }
        if (bulundugumyer == 'F') {
            counters[3]++;
            bulundugumyer = '.';
            System.out.println("F bonusu listeye eklendi.");
            System.out.println(counters[3]);
        }
        labirent[konum[0]][konum[1]] = bulundugumyer;

    }

    
    public static boolean gecerliHareket(char[][]labirent, int x, int y) {
        if (x >= 0 && x < labirent[0].length && y >= 0 && y < labirent.length) {
           return true;
        } else {
            System.out.println("Geçersiz konum. Geçerli bir hareket değil.");
            return false;
        }
    }
    
}

    }
}
