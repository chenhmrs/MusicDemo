package com.stream.mmusic.video.Data;

import android.util.Log;

import com.stream.mmusic.video.bean.HomePlaylist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;
import java.util.Set;

/**
 * Created by Well Wang on 2018/3/1.
 */

public class HomeData extends Observable {
    public HashMap<String,ArrayList<HomePlaylist>> dataList=new HashMap<>();
    public Set<Map.Entry<String,ArrayList<HomePlaylist>>> entrySet;
    int count=0;
    public static final HomeData singleInstance=new HomeData();

    public static HomeData getInstance(){
        if (singleInstance==null){
            return new HomeData();
        }
        return singleInstance;
    }
    public synchronized void addHomePlaylist(String title, ArrayList<HomePlaylist> result){
        dataList.put(title,result);
        entrySet=dataList.entrySet();
        //将关系集合entrySet进行迭代，存放到迭代器中
        Iterator<Map.Entry<String, ArrayList<HomePlaylist>>> it2 = entrySet.iterator();
       /* while(it2.hasNext()){
            Map.Entry<String, ArrayList<HomePlaylist>> me = it2.next();//获取Map.Entry关系对象me
            count++;
            Log.d("MainActivity", "coount=  " +count);
        }*/
        for (Map.Entry<String, ArrayList<HomePlaylist>> item : dataList.entrySet()) {
            if (item.getValue() != null) {
                count++;
                Log.d("MainActivity", "coount=  " +count);
            }
        }
        if (count==12){
            setChanged();
            notifyObservers();
        }else
            count=0;
    }

    public HashMap<String,ArrayList<HomePlaylist>> getDataList(){
        return dataList;
    }


}
