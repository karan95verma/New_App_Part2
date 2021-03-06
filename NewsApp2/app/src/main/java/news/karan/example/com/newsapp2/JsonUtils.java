package news.karan.example.com.newsapp2;

import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class JsonUtils {

    private static final String TAG = "JsonUtils";

    public static ArrayList<NewsItem> parseNews(String JSONString) {
        ArrayList<NewsItem> newsList = new ArrayList<>();

        try {
            JSONObject mainJSONObject = new JSONObject(JSONString);
            JSONArray articles = mainJSONObject.getJSONArray("articles");

            for ( int i = 0; i < articles.length(); i++ ) {
                JSONObject article = articles.getJSONObject(i);
                newsList.add( new NewsItem( article.getString("title"), article.getString("description"), article.getString("url"), article.getString("publishedAt")));
            }
        } catch (JSONException e) {
            Log.d(TAG, "JSON string parsing error.");
            e.printStackTrace();
        }
        return newsList;
    }

}