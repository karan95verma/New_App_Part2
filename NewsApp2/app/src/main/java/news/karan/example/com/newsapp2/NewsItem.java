package news.karan.example.com.newsapp2;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "news_item")
public class NewsItem {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo()
    private String title;

    @ColumnInfo()
    private String description;

    @ColumnInfo()
    private String url;

    @ColumnInfo()
    private String newsDate;

    @Ignore
    public NewsItem() {}

    @Ignore
    public NewsItem(String title, String description, String url, String newsDate) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.newsDate = newsDate;
    }

    public NewsItem(int id, String title, String description, String url, String newsDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.url = url;
        this.newsDate = newsDate;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNewsDate() {
        return newsDate;
    }

    public void setNewsDate(String newsDate) {
        this.newsDate = newsDate;
    }

}