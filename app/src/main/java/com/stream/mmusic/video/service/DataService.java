package com.stream.mmusic.video.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.stream.mmusic.video.Data.HomeData;
import com.stream.mmusic.video.R;
import com.stream.mmusic.video.bean.HomePlaylist;

import java.util.ArrayList;

import static com.stream.mmusic.video.constant.CloudConfig.COLLECTION_PLAYLIST;
import static com.stream.mmusic.video.constant.CloudConfig.HOME_BANNER;
import static com.stream.mmusic.video.constant.CloudConfig.HOT_CHARTS;
import static com.stream.mmusic.video.constant.CloudConfig.MUSIC_DATABASE;
import static com.stream.mmusic.video.constant.CloudConfig.TODAY_HITS;

/**
 * Created by Well Wang on 2018/2/28.
 */

public class DataService extends Service {
    String[]  mMusicType;
    HomeData mHomeData;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mHomeData=HomeData.getInstance();
        mMusicType = getResources().getStringArray(R.array.music_type);
        //load music type
        for (int i = 0; i < mMusicType.length; i++) {
            loadData(mMusicType[i], 4);
        }
        //load head music
        loadData(HOME_BANNER, 5);
        loadData(HOT_CHARTS, 1);
        loadData(TODAY_HITS, 1);


    }

    public void loadData(final String title, final int limit) {

        //  Log.d(TAG, "loadData: ------>" + title);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        if (HOT_CHARTS.equals(title) || TODAY_HITS.equals(title) || HOME_BANNER.equals(title)) {
            db.collection(MUSIC_DATABASE)
                    .document(title)
                    .collection(COLLECTION_PLAYLIST)
                    .limit(limit)
                    .orderBy("date", Query.Direction.DESCENDING)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                ArrayList<HomePlaylist> result = new ArrayList<>();

                                for (DocumentSnapshot document : task.getResult()) {
                                    HomePlaylist homePlaylist = document.toObject(HomePlaylist.class);
                                    result.add(homePlaylist);
                                }

                                mHomeData.addHomePlaylist(title, result);
                            } else {
                                // mHomeData.handleError(title, limit);
                              //  Log.d("MainActivity", "Error getting documents: " + title + "--->", task.getException());
                            }
                        }
                    });
        } else {
            db.collection(MUSIC_DATABASE)
                    .document(title)
                    .collection(COLLECTION_PLAYLIST)
                    .limit(limit)
                    .orderBy("rank")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {

                            if (task.isSuccessful()) {
                                ArrayList<HomePlaylist> result = new ArrayList<>();
                                for (DocumentSnapshot document : task.getResult()) {
                                    HomePlaylist homePlaylist = document.toObject(HomePlaylist.class);
                                    result.add(homePlaylist);
                                }
                                   mHomeData.addHomePlaylist(title, result);
                              //  Log.d("MainActivity", "onComplete: title =  " + title + " size = " + result.size());
                            } else {
                                //Log.d("MainActivity", "Error getting documents: " + title + "--->", task.getException());
                                 // mHomeData.handleError(title, limit);
                            }

                        }
                    });
        }
    }


}
