package news.karan.example.com.newsapp2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class NewsItemAdapter extends RecyclerView.Adapter<NewsItemAdapter.NewsItemViewHolder> {

    private Context mContext;
    private List<NewsItem> mNews;

    public NewsItemAdapter(Context context, ArrayList<NewsItem> mNews){
        this.mContext = context;
        this.mNews = mNews;
    }

    @Override
    public NewsItemAdapter.NewsItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(R.layout.news_item, parent, shouldAttachToParentImmediately);
        NewsItemAdapter.NewsItemViewHolder viewHolder = new NewsItemAdapter.NewsItemViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NewsItemAdapter.NewsItemViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mNews.size();
    }

    void setmNews(List<NewsItem> mNewsItems) {
        mNews = mNewsItems;
        notifyDataSetChanged();
    }

    class NewsItemViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView description;
        String url;
        TextView newsDate;

        public NewsItemViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            newsDate = (TextView) itemView.findViewById(R.id.newsDate);
        }

        void bind(final int listIndex) {
            title.setText("Title : ".concat(mNews.get(listIndex).getTitle()));
            description.setText("Description : ".concat(mNews.get(listIndex).getDescription()));
            url = mNews.get(listIndex).getUrl();
            newsDate.setText("Date : ".concat(mNews.get(listIndex).getNewsDate()));

            // Open news article in browser on click.
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String urlString = mNews.get(getAdapterPosition()).getUrl();
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlString));
                    mContext.startActivity(browserIntent);
                }
            });
        }
    }

}