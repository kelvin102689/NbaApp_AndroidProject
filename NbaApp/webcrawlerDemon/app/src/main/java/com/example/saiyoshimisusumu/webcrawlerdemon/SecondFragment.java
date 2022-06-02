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
import com.example.saiyoshimisusumu.webcrawlerdemon.Adapter.StateDataAdapter;
import com.example.saiyoshimisusumu.webcrawlerdemon.R;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SecondFragment extends Fragment  {
    public RecyclerView recycler_view;
    public StateDataAdapter SDAdapter;
   // private Spinner spinner;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        crawler("https://www.cbssports.com/nba/standings/regular/conference/");
        View rootView = inflater.inflate(R.layout.team_state, container, false);

        recycler_view = (RecyclerView) rootView.findViewById(R.id.recycleview);

        recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));

        recycler_view.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        //spinner = rootView.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.stats, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spinner.setAdapter(adapter);
        //spinner.setOnItemSelectedListener(this);

        return rootView;
    }

    protected List<StateData> anaylizeHtml(Elements ThemeContext)
    {
        int counter=0;
        List<StateData> GroupStateData = new ArrayList<StateData>();
        //todo:分析ＵＲＬ 擷取出必要資訊
        for (Element domElement: ThemeContext) {
            if(counter==0)
            {
                StateData stateData = new StateData();
                String TitlePicUrl = domElement.getElementsByClass("a").select("TeamLogo-image ").attr("src");
                //圖片網址
                stateData.ImgUrl = TitlePicUrl;
                //String Name1 =domElement.getElementsByClass("TableBase-bodyTd").select("TeamName");
                String Name = domElement.getElementsByClass("TableBase-bodyTd \n" +
                        "                \n" + "                \n"+
                                "                \n"+
                        "                ").get(0).text();
                stateData.ranking = "\n"+"\n"+Name;

                String Team = domElement.getElementsByClass("TableBase-bodyTd \n" +
                        "                \n" + "                \n"+
                        "                \n"+
                        "                ").get(1).text();
                stateData.team = "Eastern"+"\n"+"\n"+Team;

                String GP = domElement.getElementsByClass("TableBase-bodyTd \n" +
                        "                \n" +
                        "                TableBase-bodyTd--number\n" +
                        "                \n" +
                        "                ").get(0).text();
                stateData.win = "\n"+"\n"+GP;

                String PPG = domElement.getElementsByClass("TableBase-bodyTd \n" +
                        "                \n" +
                        "                TableBase-bodyTd--number\n" +
                        "                \n" +
                        "                ").get(1).text();
                stateData.lose = "\n"+"\n"+PPG;

                String FGP = domElement.getElementsByClass("TableBase-bodyTd \n" +
                        "                \n" +
                        "                TableBase-bodyTd--number\n" +
                        "                \n" +
                        "                ").get(3).text();
                stateData.GB = "\n"+"\n"+FGP;

                String ThreeFGM = domElement.getElementsByClass("TableBase-bodyTd \n" +
                        "                \n" +
                        "                TableBase-bodyTd--number\n" +
                        "                \n" +
                        "                ").get(2).text();
                stateData.win_percentage = "\n"+"\n"+ThreeFGM;

                String ThreeFGA = domElement.getElementsByClass("TableBase-bodyTd \n" +
                        "                \n" +
                        "                TableBase-bodyTd--number\n" +
                        "                \n" +
                        "                ").get(7).text();
                stateData.home = "\n"+"\n"+ThreeFGA;

                String ThreeFGP = domElement.getElementsByClass("TableBase-bodyTd \n" +
                        "                \n" +
                        "                TableBase-bodyTd--number\n" +
                        "                \n" +
                        "                ").get(8).text();
                stateData.away = "\n"+"\n"+ThreeFGP;



                GroupStateData.add(stateData);
            }
            else if(counter==15)
            {
                StateData stateData = new StateData();
                String TitlePicUrl = domElement.getElementsByClass("a").select("img").attr("src");
                //圖片網址
                stateData.ImgUrl = TitlePicUrl;

                String Name = domElement.getElementsByClass("TableBase-bodyTd \n" +
                        "                \n" + "                \n"+
                        "                \n"+
                        "                ").get(0).text();
                stateData.ranking = "\n"+"\n"+Name;

                String Team = domElement.getElementsByClass("TableBase-bodyTd \n" +
                        "                \n" + "                \n"+
                        "                \n"+
                        "                ").get(1).text();
                stateData.team = "Western"+"\n"+"\n"+Team;

                String GP = domElement.getElementsByClass("TableBase-bodyTd \n" +
                        "                \n" +
                        "                TableBase-bodyTd--number\n" +
                        "                \n" +
                        "                ").get(0).text();
                stateData.win = "\n"+"\n"+GP;

                String PPG = domElement.getElementsByClass("TableBase-bodyTd \n" +
                        "                \n" +
                        "                TableBase-bodyTd--number\n" +
                        "                \n" +
                        "                ").get(1).text();
                stateData.lose = "\n"+"\n"+PPG;

                String FGP = domElement.getElementsByClass("TableBase-bodyTd \n" +
                        "                \n" +
                        "                TableBase-bodyTd--number\n" +
                        "                \n" +
                        "                ").get(3).text();
                stateData.GB = "\n"+"\n"+FGP;

                String ThreeFGM = domElement.getElementsByClass("TableBase-bodyTd \n" +
                        "                \n" +
                        "                TableBase-bodyTd--number\n" +
                        "                \n" +
                        "                ").get(2 ).text();
                stateData.win_percentage = "\n"+"\n"+ThreeFGM;

                String ThreeFGA = domElement.getElementsByClass("TableBase-bodyTd \n" +
                        "                \n" +
                        "                TableBase-bodyTd--number\n" +
                        "                \n" +
                        "                ").get(7).text();
                stateData.home = "\n"+"\n"+ThreeFGA;

                String ThreeFGP = domElement.getElementsByClass("TableBase-bodyTd \n" +
                        "                \n" +
                        "                TableBase-bodyTd--number\n" +
                        "                \n" +
                        "                ").get(8).text();
                stateData.away = "\n"+"\n"+ThreeFGP;



                GroupStateData.add(stateData);
            }
            else
            {
                StateData stateData = new StateData();
                String TitlePicUrl = domElement.getElementsByClass("a").select("img").attr("src");
                //圖片網址
                stateData.ImgUrl = TitlePicUrl;

                String Name = domElement.getElementsByClass("TableBase-bodyTd \n" +
                        "                \n" + "                \n"+
                        "                \n"+
                        "                ").get(0).text();
                stateData.ranking = Name;

                String Team = domElement.getElementsByClass("TableBase-bodyTd \n" +
                        "                \n" + "                \n"+
                        "                \n"+
                        "                ").get(1).text();
                stateData.team = Team;

                String GP = domElement.getElementsByClass("TableBase-bodyTd \n" +
                        "                \n" +
                        "                TableBase-bodyTd--number\n" +
                        "                \n" +
                        "                ").get(0).text();
                stateData.win = GP;

                String PPG = domElement.getElementsByClass("TableBase-bodyTd \n" +
                        "                \n" +
                        "                TableBase-bodyTd--number\n" +
                        "                \n" +
                        "                ").get(1).text();
                stateData.lose = PPG;

                String FGP = domElement.getElementsByClass("TableBase-bodyTd \n" +
                        "                \n" +
                        "                TableBase-bodyTd--number\n" +
                        "                \n" +
                        "                ").get(3).text();
                stateData.GB = FGP;

                String ThreeFGM = domElement.getElementsByClass("TableBase-bodyTd \n" +
                        "                \n" +
                        "                TableBase-bodyTd--number\n" +
                        "                \n" +
                        "                ").get(2).text();
                stateData.win_percentage = ThreeFGM;

                String ThreeFGA = domElement.getElementsByClass("TableBase-bodyTd \n" +
                        "                \n" +
                        "                TableBase-bodyTd--number\n" +
                        "                \n" +
                        "                ").get(7).text();
                stateData.home = ThreeFGA;

                String ThreeFGP = domElement.getElementsByClass("TableBase-bodyTd \n" +
                        "                \n" +
                        "                TableBase-bodyTd--number\n" +
                        "                \n" +
                        "                ").get(8).text();
                stateData.away = ThreeFGP;



                GroupStateData.add(stateData);
            }


            counter++;
        }
        return  GroupStateData;

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


   // public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
     //       crawler("https://www.cbssports.com/nba/standings/regular/conference/");
    //}



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
                    final List<StateData> GroupStateData  = anaylizeHtml(ElementsByClass);

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            SDAdapter = new StateDataAdapter(GroupStateData ,getContext());
                            recycler_view.setAdapter(SDAdapter);
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
