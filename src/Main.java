import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String htmlResponse;
        StringBuffer response;
        String regex = "https://www\\.famnit\\.upr\\.si/[^\\s\"']*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher;
        Scanner scan = new Scanner(System.in);
        System.out.print("Input a URL: ");
        String URL = scan.next();
        Crawler crawler = new Crawler();
        System.out.println("Crawling through domain " + URL);
        try {
            htmlResponse = crawler.crawl(URL);
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        response = new StringBuffer(htmlResponse.length());
        response.insert(0,htmlResponse);
        matcher = pattern.matcher(response);
        ArrayList<String> links = new ArrayList<>();
        while(matcher.find()){
            String link = matcher.group();
            if(links.contains(link)){
                continue;
            }
            links.add(link);
        }
        for(String i : links){
            System.out.println(i);
        }
        scan.close();
    }
}
