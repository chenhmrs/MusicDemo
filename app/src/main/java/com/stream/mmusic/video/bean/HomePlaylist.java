package com.stream.mmusic.video.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import java.util.UUID;

/**
 * Created by Well Wang on 2018/2/28.
 */

public class HomePlaylist implements Parcelable {

    private String uniqueId;
    private Long date;
    private String title;
    private String thumbnail;
    private String channelTitle;
    private Integer rank;
    private String playlistId;
    private List<String> videoListId;


    public HomePlaylist(String title, String thumbnail, List<String> videoListId) {
        this.title = title;
        this.thumbnail = thumbnail;
        this.videoListId = videoListId;
        this.uniqueId = UUID.randomUUID().toString();
    }


    public HomePlaylist(String title, String thumbnail, String playlistId) {
        this.title = title;
        this.thumbnail = thumbnail;
        this.playlistId = playlistId;
        this.uniqueId = UUID.randomUUID().toString();
    }
    public HomePlaylist() {
    }
    protected HomePlaylist(Parcel in) {
        uniqueId = in.readString();
        if (in.readByte() == 0) {
            date = null;
        } else {
            date = in.readLong();
        }
        title = in.readString();
        thumbnail = in.readString();
        channelTitle = in.readString();
        if (in.readByte() == 0) {
            rank = null;
        } else {
            rank = in.readInt();
        }
        playlistId = in.readString();
        videoListId = in.createStringArrayList();
    }

    public static final Creator<HomePlaylist> CREATOR = new Creator<HomePlaylist>() {
        @Override
        public HomePlaylist createFromParcel(Parcel in) {
            return new HomePlaylist(in);
        }

        @Override
        public HomePlaylist[] newArray(int size) {
            return new HomePlaylist[size];
        }
    };

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getChannelTitle() {
        return channelTitle;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
    }

    public List<String> getVideoListId() {
        return videoListId;
    }

    public void setVideoListId(List<String> videoListId) {
        this.videoListId = videoListId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(uniqueId);
        if (date == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(date);
        }
        dest.writeString(title);
        dest.writeString(thumbnail);
        dest.writeString(channelTitle);
        if (rank == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(rank);
        }
        dest.writeString(playlistId);
        dest.writeStringList(videoListId);
    }
}
