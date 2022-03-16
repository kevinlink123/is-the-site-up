package demo.isthesiteup.controllers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlCheckerController {
    
    private final String SITE_UP = "Site is up!";
    private final String SITE_DOWN = "Site is down!";
    private final String INCORRECT_URL = "URL IS INCORRECT!";


    @GetMapping("/check")
    public String getUrlStatusMessage(@RequestParam String url){
        String returnMessage = "";
        try {
            //A correct connection to the url means that the site is up
            URL urlObj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCodeCategory = conn.getResponseCode() / 100;
            System.out.println(responseCodeCategory);

            if (responseCodeCategory != 2 || responseCodeCategory != 3){
                returnMessage = SITE_UP;
            } else {
                returnMessage = SITE_DOWN;
            }

        } catch (MalformedURLException e) {
            returnMessage = INCORRECT_URL;
        } catch (IOException e) {
            //This error means that the site is down
            returnMessage = SITE_DOWN;
        }
        
        return returnMessage;
    }
}
