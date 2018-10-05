package client;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {

    public static Logger logger;

    public static void main(String [] args){

        //creates logger
        logger = Logger.getLogger("MyLog");
        FileHandler fh;
        try {
            //creates log file
            fh = new FileHandler(System.getProperty("user.dir")+"/log.log");
            //add filename to logg
            logger.addHandler(fh);
            //creates formatter
            SimpleFormatter formatter = new SimpleFormatter();
            //add formatter
            fh.setFormatter(formatter);
            //disable notifications in console
            logger.setUseParentHandlers(false);
            for(;;) {
                scrap();

                Thread.sleep(10000);
            }
        }
        catch (InterruptedException | SecurityException | IOException e) {
            e.printStackTrace();
        }


    }

    private static void scrap(){
            Document doc;
            Element video;
            String source = null;
            try{
                //create Jsoup connection
                doc = Jsoup.connect("https://webtv.feratel.com/webtv/?cam=5132&design=v3&c0=0&c2=1&lg=en&s=0").userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0").get();
                //create element("fer_video")
                video = doc.getElementById("fer_video");
                //search for element in html
                source = video.select("source").first().attr("src");
            } catch (IOException e) {
                e.printStackTrace();
            }
            //prints element in consoles
            System.out.println(source);
            //prints element in log
            logger.info(source);
    }

}

