package com.stream.mmusic.video.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.stream.mmusic.video.bean.HomePlaylist;
import com.stream.mmusic.video.constant.CloudConfig;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;
import static com.stream.mmusic.video.constant.CloudConfig.COLLECTION_PLAYLIST;
import static com.stream.mmusic.video.constant.CloudConfig.MUSIC_DATABASE;

/**
 * Created by Well Wang on 2018/2/28.
 */

public class DataService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "MainActivity" +"dfd");
        loadData(CloudConfig.HOME_BANNER,5);
    }

    public void loadData(final String title, final int limit) {

        //  Log.d(TAG, "loadData: ------>" + title);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        if (CloudConfig.HOT_CHARTS.equals(title) || CloudConfig.TODAY_HITS.equals(title) || CloudConfig.HOME_BANNER.equals(title)) {
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

                                //mHomeData.addHomePlaylist(title, result);
                                Log.d(TAG, "MainActivity" + result.size());
                                Log.d(TAG, "onComplete: title =  " + title + " size = " + result.size());

                            } else {

                                // mHomeData.handleError(title, limit);
                                Log.d(TAG, "Error getting documents: " + title + "--->", task.getException());
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
                                //   mHomeData.addHomePlaylist(title, result);
                                Log.d(TAG, "onComplete: title =  " + title + " size = " + result.size());
                            } else {
                                Log.d(TAG, "Error getting documents: " + title + "--->", task.getException());
                                //  mHomeData.handleError(title, limit);
                            }

                        }
                    });
        }
    }


}
