package com.example.saiyoshimisusumu.webcrawlerdemon;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saiyoshimisusumu.webcrawlerdemon.Adapter.NewsDataAdapter;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment {
    public RecyclerView recycler_view;
    public NewsDataAdapter NWAdapter;

    // private Spinner spinner;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        crawler("https://www.cbssports.com/nba/");
        View rootView = inflater.inflate(R.layout.news, container, false); /////news->news

        recycler_view = (RecyclerView) rootView.findViewById(R.id.recycleview);

        recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));

        recycler_view.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        //spinner = rootView.findViewById(R.id.spinner);
        //ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.stats, android.R.layout.simple_spinner_item);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spinner.setAdapter(adapter);
        //spinner.setOnItemSelectedListener(this);

        return rootView;
    }

    protected List<NewsData> convertElementToNewData(Elements ThemeContext) {

        List<NewsData> GroupStateData = new ArrayList<NewsData>();
        //todo:分析ＵＲＬ 擷取出必要資訊

        for (Element domElement : ThemeContext) {
            NewsData newsData = new NewsData();
            System.out.println("domElement = " + domElement);

            newsData.NewsTitle = domElement.getElementsByClass("article-list-pack-title").select("a").text();
            newsData.content = domElement.select("figcaption.article-list-pack-dek").text();
            newsData.ImgUrl = domElement.getElementsByTag("img").first().attr("data-lazy");
            newsData.linkUrl = domElement.getElementsByTag("a").first().attr("href");
            newsData.linkUrl = "https://www.cbssports.com" + newsData.linkUrl;

            System.out.println("linkUrl = " + newsData.linkUrl);
            GroupStateData.add(newsData);
        }
        return GroupStateData;
    }

    protected void getUrlhref() {
        //todo: 取的網頁 href 的連結部分
        List<String> AhrefList = new ArrayList<String>();
    }

    public boolean isNetworkAvailable(Activity activity) {
        Context context = activity.getApplicationContext();
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (cm == null) return false;
        else {
            NetworkInfo[] networkInfo = cm.getAllNetworkInfo();
            if (networkInfo != null && networkInfo.length > 0) {
                for (int i = 0; i < networkInfo.length; i++)
                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED) return true;
            }
        }
        return false;
    }


    // public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
    //       crawler("https://www.cbssports.com/nba/standings/regular/conference/");
    //}


    public void crawler(final String URL) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Connection conn = Jsoup.connect(URL);
                    conn.header("User-Agent", "Mozilla/5.0 (X11; Linux x86_64; rv:32.0) Gecko/    20100101 Firefox/32.0");
                    final Document docs = conn.get();
                    Elements ElementsByClass = docs.getElementsByClass("col-4 article-list-pack-item");
                    final List<NewsData> GroupStateData = convertElementToNewData(ElementsByClass);

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            NWAdapter = new NewsDataAdapter(GroupStateData, getContext());
                            recycler_view.setAdapter(NWAdapter);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        new Thread(runnable).start();
    }
}
