package news.karan.example.com.newsapp2;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class NewsItemRepository {

    private NewsItemDao mNewsItemDao;
    private LiveData<List<NewsItem>> mNewsItems;

    public NewsItemRepository(Application application) {
        NewsDatabase db = NewsDatabase.getDatabase(application.getApplicationContext());
        mNewsItemDao = db.mNewsItemDao();
        mNewsItems = mNewsItemDao.loadAllNewsItems();
    }

    public LiveData<List<NewsItem>> getmNewsItems() {
        mNewsItems = mNewsItemDao.loadAllNewsItems();
        return mNewsItems;
    }

    public void syncDatabase() {
        new SyncDatabaseNewsItemAsyncTask(mNewsItemDao).execute();
    }

    private static class SyncDatabaseNewsItemAsyncTask extends AsyncTask<Void, Void, Void> {
        private NewsItemDao mNewsItemDaoAsyncTask;

        SyncDatabaseNewsItemAsyncTask(NewsItemDao mNewsItemDaoAsyncTask) {
            this.mNewsItemDaoAsyncTask = mNewsItemDaoAsyncTask;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mNewsItemDaoAsyncTask.clearAll();

            URL newsSearchUrl = NetworkUtils.buildURL();
            String newsSearchResult = null;
            try {
                newsSearchResult = NetworkUtils.getResponseFromHttpUrl(newsSearchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            ArrayList<NewsItem> mNewsItems = JsonUtils.parseNews(newsSearchResult);


            mNewsItemDaoAsyncTask.insert(mNewsItems);

            return null;
        }
    }

}