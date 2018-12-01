package news.karan.example.com.newsapp2;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private NewsItemAdapter mAdapter;
    private ArrayList<NewsItem> mNews = new ArrayList<>();
    private NewsItemViewModel mNewsItemViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.news_recyclerview);
        mAdapter = new NewsItemAdapter(this, mNews);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mNewsItemViewModel = ViewModelProviders.of(this).get(NewsItemViewModel.class);
        mNewsItemViewModel.getmAllNewsItems().observe(this, new Observer<List<NewsItem>>() {
            @Override
            public void onChanged(@Nullable final List<NewsItem> mNewsItems) {
                // Update the cached copy of the news in the adapter.
                mAdapter.setmNews(mNewsItems);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();
        if (itemThatWasClickedId == R.id.action_search) {
            mNewsItemViewModel.syncDatabase();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.get_news, menu);
        return true;
    }

}
