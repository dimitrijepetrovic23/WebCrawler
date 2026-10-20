import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String htmlResponse;
        String htmlStatuscode;
        StringBuffer response;
        Scanner scan = new Scanner(System.in);
        System.out.print("Input a URL: ");
        String URL = scan.next();
        Crawler crawler = new Crawler();
        System.out.println("Crawling through domain " + URL);
        try {
            String[] crawler_response = crawler.crawl(URL);
            htmlResponse = crawler_response[0];
            htmlStatuscode = crawler_response[1];
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        response = new StringBuffer(htmlResponse.length());
        response.insert(0,htmlResponse);
        String regex = URL + "[^\\s\"'<>\\\\()`;{}]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher;
        matcher = pattern.matcher(response);
        ArrayList<String> links = new ArrayList<>();
        while(matcher.find()){
            String link = matcher.group();
            if(links.contains(link)){
                continue;
            }
            links.add(link);
        }

        for(String link : links){
            try {
                String[] crawler_response = crawler.crawl(link);
                htmlResponse = crawler_response[0];
                htmlStatuscode = crawler_response[1];
            } catch (IOException | InterruptedException e) {
                System.out.println(e.getMessage());
                throw new RuntimeException(e);
            }
            System.out.println(link + " status code: " + htmlStatuscode);
        }
        scan.close();
    }
}
