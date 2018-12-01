package news.karan.example.com.newsapp2;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import java.util.List;

public class NewsItemViewModel extends AndroidViewModel {

    private NewsItemRepository mNewsItemRepository;
    private LiveData<List<NewsItem>> mAllNewsItems;

    public NewsItemViewModel(Application application) {
        super(application);
        mNewsItemRepository = new NewsItemRepository(application);
        mAllNewsItems = mNewsItemRepository.getmNewsItems();
    }

    public LiveData<List<NewsItem>> getmAllNewsItems() {
        return mAllNewsItems;
    }

    public void syncDatabase() {
        mNewsItemRepository.syncDatabase();
    }

}