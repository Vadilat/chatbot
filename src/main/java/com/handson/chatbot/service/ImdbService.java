package com.handson.chatbot.service;

import okhttp3.*;
import org.springframework.stereotype.Service;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.IOException;

@Service
public class ImdbService {
    private String test(String keyword) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("https://www.imdb.com/chart/top/")
                .method("GET", body)
                .addHeader("Cookie", "session-id=142-5680021-3534708; session-id-time=2082787201l; session-token=5zz92Vn+VBCkMaqEiFhcuS7FzTDt5El5pcl2RIdQPHViCsbnH7QhahSZVLPMibuLM4d57EUrXvuMX7dKRnEHDW9cxaT7Wu8+0Qd/DatYplaA0cHq0x4lexfSHd2RS0bCWNYAq7kjmaKfPNFKiGZF7DLKKh+NOMmsz1NVF06ZuxOQXm/fTyTUhoGYYzxoSQ7uNlUrGEptD+hXzD10LHeSXtsKua4sJQ5M8TWJTVNLdjLCMZjXBn9/d8gQtQ9AxTpd0sapWyZ2p8UkH8dSLkSma0udedXsjbHQJEy6E3TuqdGhHXHCgOmkZCyVc/lmKU5eQS3LEqsNSDlpJeq9S5m2jJUf9MUVf2BF; ubid-main=132-1441737-7321412")
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    private String getProductHtml() throws IOException {

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("https://www.imdb.com/chart/top/")
                .method("GET", null)
                .addHeader("authority", "www.imdb.com")
                .addHeader("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
                .addHeader("accept-language", "en-US,en;q=0.9")
                .addHeader("cache-control", "max-age=0")
                .addHeader("cookie", "session-id=144-0265659-8151565; session-id-time=2082787201l; ubid-main=130-4886731-6330529; ad-oo=0; session-token=C5ycUUby577b/rkPQ7QvIQ1rShXFsRyijQ/LtfFJKQlQt3mwGclakjp77NvmMUdMl47FjDAtbTFbP5foaTvUp0Fijfua8w2gSz1OTQKVwhpGOAjTwJdpSmUUhxFU0iNoX0GnnujdPeLj2rbVB4RSjRzwHMecY3jrgr9M9iSF6o/k3nB5gwFdEEIUbLuZvKI9vHWEa3+ImW+xp6PVKDxZoTIqpkDtZNu0X1DPOJeDl0EBzDGBYUnZBGqZfq2fZVZ30QFMJY28Zza7ewKA6r58G2Dq2ZPr57StW/8uPAjPuN25AMUJkkaEuOxR9aY0oSW2OhgqCjFUgscXwZAMZIgh9vXT49Oa6QTR; __gads=ID=eff9150935945622:T=1725195522:RT=1725213421:S=ALNI_MZdieI8QvcflOjIXbEMMG0nN767BA; __gpi=UID=00000ee456894d64:T=1725195522:RT=1725213421:S=ALNI_MYfqMO9eW4HWJJ0rJWabz0AQvw6Og; __eoi=ID=20a1316f385ce146:T=1725195522:RT=1725213421:S=AA-AfjaGFXXTRpxMGGFl92JFYF5b; ci=e30; csm-hit=tb:FS9SJ8SS259VASAG09E4+s-FS9SJ8SS259VASAG09E4|1725213618854&t:1725213618854&adb:adblk_no; session-id=144-0265659-8151565; session-id-time=2082787201l; session-token=/8nsjjmuE75LX6ItlisHcDsKkPMVZE1Er2tCIVjmWmV73YV45Qd4jQpKfHFdTWTjqKWM/EogEXOpMsP9CbK1aABSLA28HKncL4TP9YPUCToXMG4YHL1V6UGfuRrix2i1ZGC+VkVYA+P/kBeUtWa+/HBMXQDrW5lazfApUxLm9unQnMM8wRWXiF2EOfRJ3DIu8ExT8ca0aGvO3zmT+n8py/8Jbdv1miiRMjKqyax5B4QRSgReguxuwOZosiFo3Qymw77hcYN6YABcEMELXf9d7IPYB1O84YsmDPcHemvFg/DCXDDZJKN0CoXqds33BNh8IOvtx01AbqiFX2fQNwEMy6Q2kbdOMhgA")
                .addHeader("sec-ch-ua", "\"Not_A Brand\";v=\"99\", \"Google Chrome\";v=\"109\", \"Chromium\";v=\"109\"")
                .addHeader("sec-ch-ua-mobile", "?0")
                .addHeader("sec-ch-ua-platform", "\"Linux\"")
                .addHeader("sec-fetch-dest", "document")
                .addHeader("sec-fetch-mode", "navigate")
                .addHeader("sec-fetch-site", "same-origin")
                .addHeader("sec-fetch-user", "?1")
                .addHeader("upgrade-insecure-requests", "1")
                .addHeader("user-agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36")
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }


    public static final Pattern PRODUCT_PATTERN = Pattern.compile("\\{\"@type\":\"([^\"]+)\",\"url\":\"([^\"]+)\",\"name\":\"([^\"]+)\".*?(?=image\")image\":\"([^\"]+).*?(?=ratingValue)ratingValue\":([^,]+).*?(?=genre\")genre\":\"([^\"]+)");

    public String searchProducts(String keyword) throws IOException {
        return parseProductHtml(getProductHtml());
    }

    private String parseProductHtml(String html) {
        String res = "";
        Matcher matcher = PRODUCT_PATTERN.matcher(html);
        while (matcher.find()) {
            res += "Type :" + matcher.group(1) + " Link" + matcher.group(2) +
                    " Name :" +matcher.group(3)+ " Image" + matcher.group(4)+
                    " Rating "+matcher.group(5)+ " Genre :"+ matcher.group(6)+ "<br>\n";
        }
        return res;
    }
}

