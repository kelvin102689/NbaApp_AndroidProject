package com.example.saiyoshimisusumu.webcrawlerdemon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Half;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.saiyoshimisusumu.webcrawlerdemon.Adapter.CardDataAdapter;
import com.example.saiyoshimisusumu.webcrawlerdemon.Adapter.ViewPagerAdapter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.Connection;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ViewPagerAdapter pagerAdapter;
    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getSupportActionBar().hide();

        pager = findViewById(R.id.pager);

        pagerAdapter = new ViewPagerAdapter(this.getSupportFragmentManager());
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        setupViewPager(pager);
    }

    public void setupViewPager(ViewPager viewPager) {
        pagerAdapter.addFragment(new FirstFragment(), "頁面1");
        pagerAdapter.addFragment(new SecondFragment(), "頁面2");
        pagerAdapter.addFragment(new ThirdFragment(), "頁面3");
        viewPager.setAdapter(pagerAdapter);

    }
}
