package com.handson.chatbot.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.io.IOException;

@Service//for injection from/to spring
public class ChuckJokeService {
    OkHttpClient client = new OkHttpClient().newBuilder().build();
    @Autowired
    ObjectMapper om;
    public String getJoke(String keyword) throws IOException {

        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("https://api.chucknorris.io/jokes/search?query="+keyword)
                .method("GET", null)
                .addHeader("authority", "www.imdb.com")
                .addHeader("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
                .addHeader("accept-language", "en-US,en;q=0.9")
                .addHeader("cache-control", "max-age=0")
                .addHeader("cookie", "session-id=144-0265659-8151565; session-id-time=2082787201l; ubid-main=130-4886731-6330529; ad-oo=0; session-token=C5ycUUby577b/rkPQ7QvIQ1rShXFsRyijQ/LtfFJKQlQt3mwGclakjp77NvmMUdMl47FjDAtbTFbP5foaTvUp0Fijfua8w2gSz1OTQKVwhpGOAjTwJdpSmUUhxFU0iNoX0GnnujdPeLj2rbVB4RSjRzwHMecY3jrgr9M9iSF6o/k3nB5gwFdEEIUbLuZvKI9vHWEa3+ImW+xp6PVKDxZoTIqpkDtZNu0X1DPOJeDl0EBzDGBYUnZBGqZfq2fZVZ30QFMJY28Zza7ewKA6r58G2Dq2ZPr57StW/8uPAjPuN25AMUJkkaEuOxR9aY0oSW2OhgqCjFUgscXwZAMZIgh9vXT49Oa6QTR; __gads=ID=eff9150935945622:T=1725195522:RT=1725213421:S=ALNI_MZdieI8QvcflOjIXbEMMG0nN767BA; __gpi=UID=00000ee456894d64:T=1725195522:RT=1725213421:S=ALNI_MYfqMO9eW4HWJJ0rJWabz0AQvw6Og; __eoi=ID=20a1316f385ce146:T=1725195522:RT=1725213421:S=AA-AfjaGFXXTRpxMGGFl92JFYF5b; ci=e30; csm-hit=tb:FS9SJ8SS259VASAG09E4+s-FS9SJ8SS259VASAG09E4|1725213618854&t:1725213618854&adb:adblk_no")
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

        String res = response.body().string();
        JokeQueryResponse jqr = om.readValue(res, JokeQueryResponse.class);
        return jqr.getParsedJson(jqr.getData());
    }

        static private class JokeQueryResponse {
            Integer total;
            @JsonProperty("result")
            List<Joke> result;
            List<Joke> getData(){
                return result;
            }
            String getParsedJson(List<Joke> input)
            {
                String output="";
                int i=0;
                for(Joke j : input)
                {
                    output+=i+1 + ") " + j.getValue();
                    output+="\n";
                    i++;
                }
                return output;
            }
        }
       static public class Joke {
           List<String> categories;
           String id;
           String value;
           String createdAt;
           String iconUrl;
           String url;
           String UpdatedAt;
           public List<String> getCategories() {
               return categories;
           }

           public String getId() {
               return id;
           }
           public String getValue() {
               return value;
           }

           public String getCreatedAt() {
               return createdAt;
           }

           public String getIconUrl() {
               return iconUrl;
           }

           public String getUrl() {
               return url;
           }

           public String getUpdatedAt() {
               return UpdatedAt;
           }
       }
}
