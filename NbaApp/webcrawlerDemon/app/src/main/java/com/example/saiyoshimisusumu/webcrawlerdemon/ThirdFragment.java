package com.example.saiyoshimisusumu.webcrawlerdemon;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saiyoshimisusumu.webcrawlerdemon.Adapter.CardDataAdapter;
import com.example.saiyoshimisusumu.webcrawlerdemon.R;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ThirdFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    public RecyclerView recycler_view;
    public CardDataAdapter CDadapter;
    private Spinner spinner;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.player_state, container, false);

        recycler_view = (RecyclerView) rootView.findViewById(R.id.recycleview);

        recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));

        recycler_view.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        spinner = rootView.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.stats, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        return rootView;
    }

    protected List<CardData> anaylizeHtml(Elements ThemeContext)
    {
        List<CardData> GroupCardData = new ArrayList<CardData>();
        //todo:分析ＵＲＬ 擷取出必要資訊
        for (Element domElement: ThemeContext) {
            CardData cardData = new CardData();
            String TitlePicUrl = domElement.getElementsByClass("a").select("img").attr("src");
            //圖片網址
            cardData.ImgUrl = TitlePicUrl;

            String Name = domElement.select("span.CellPlayerName--short").select("a").text();
            cardData.Name = Name;

            String Team = domElement.select("span.CellPlayerName--short").select("span.CellPlayerName-team").text();
            cardData.Team = Team;

            String GP = domElement.getElementsByClass("TableBase-bodyTd \n" +
                    "                \n" +
                    "                TableBase-bodyTd--number\n" +
                    "                \n" +
                    "                ").get(0).text();
            cardData.GP = GP;

            String PPG = domElement.getElementsByClass("TableBase-bodyTd \n" +
                    "                \n" +
                    "                TableBase-bodyTd--number\n" +
                    "                \n" +
                    "                ").get(3).text();
            cardData.PPG = PPG;

            String FGP = domElement.getElementsByClass("TableBase-bodyTd \n" +
                    "                \n" +
                    "                TableBase-bodyTd--number\n" +
                    "                \n" +
                    "                ").get(6).text();
            cardData.FGP = FGP;

            String ThreeFGM = domElement.getElementsByClass("TableBase-bodyTd \n" +
                    "                \n" +
                    "                TableBase-bodyTd--number\n" +
                    "                \n" +
                    "                ").get(7).text();
            cardData.ThreeFGM = ThreeFGM;

            String ThreeFGA = domElement.getElementsByClass("TableBase-bodyTd \n" +
                    "                \n" +
                    "                TableBase-bodyTd--number\n" +
                    "                \n" +
                    "                ").get(8).text();
            cardData.ThreeFGA = ThreeFGA;

            String ThreeFGP = domElement.getElementsByClass("TableBase-bodyTd \n" +
                    "                \n" +
                    "                TableBase-bodyTd--number\n" +
                    "                \n" +
                    "                ").get(9).text();
            cardData.ThreeFGP = ThreeFGP;

            String FTP = domElement.getElementsByClass("TableBase-bodyTd \n" +
                    "                \n" +
                    "                TableBase-bodyTd--number\n" +
                    "                \n" +
                    "                ").get(12).text();
            cardData.FTP = FTP;

            GroupCardData.add(cardData);

        }
        return  GroupCardData;

    }

    protected void getUrlhref()
    {
        //todo: 取的網頁 href 的連結部分
        List<String> AhrefList = new ArrayList<String>();
    }

    public boolean isNetworkAvailable(Activity activity)
    {
        Context context = activity.getApplicationContext();
        ConnectivityManager cm = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (cm == null)
            return false;
        else
        {
            NetworkInfo[] networkInfo = cm.getAllNetworkInfo();
            if (networkInfo != null && networkInfo.length > 0)
            {
                for (int i = 0; i < networkInfo.length; i++)
                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED)
                        return true;
            }
        }
        return false;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (l == 0) {
            crawler("https://www.cbssports.com/nba/stats/player/scoring/nba/regular/all-pos/qualifiers/?sortcol=ppg&sortdir=descending");
        }
        if (l == 1) {
            crawler("https://www.cbssports.com/nba/stats/player/scoring/nba/regular/all-pos/qualifiers/?sortcol=3fgm&sortdir=descending");
        }
        if (l == 2) {
            crawler("https://www.cbssports.com/nba/stats/player/scoring/nba/regular/all-pos/qualifiers/?sortcol=3fga&sortdir=descending");
        }
        if (l == 3) {
            crawler("https://www.cbssports.com/nba/stats/player/scoring/nba/regular/all-pos/qualifiers/?sortcol=3fg%25&sortdir=descending");
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void crawler(final String URL) {
        Runnable runable = new Runnable() {
            @Override
            public void run() {
                try {
                    String NetUrl = URL;
                    Connection conn = Jsoup.connect(NetUrl);
                    conn.header("User-Agent", "Mozilla/5.0 (X11; Linux x86_64; rv:32.0) Gecko/    20100101 Firefox/32.0");
                    final Document docs = conn.get();
                    Elements ElementsByClass = docs.getElementsByClass("TableBase-bodyTr");
                    final List<CardData> GroupCardData  = anaylizeHtml(ElementsByClass);

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            CDadapter = new CardDataAdapter(GroupCardData ,getContext());
                            recycler_view.setAdapter(CDadapter);
                        }
                    });

                    int stop = 0;

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        new Thread(runable).start();
    }
}
