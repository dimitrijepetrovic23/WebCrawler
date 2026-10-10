import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String htmlResponse;
        Scanner scan = new Scanner(System.in);
        System.out.print("Input a URL: ");
        String URL = scan.next();
        Crawler crawler = new Crawler(URL);
        System.out.println("Crawling through domain " + URL);
        htmlResponse = crawler.crawl();
        System.out.println(htmlResponse);
        scan.close();
    }
}
