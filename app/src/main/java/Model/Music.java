package Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Music implements Parcelable {
    private int img;
    private int song;
    private String name;

    public Music(int img, String name) {
        this.img = img;
        this.name = name;
    }

    public Music(int img, String name, int song) {
        this.img = img;
        this.name = name;
        this.song = song;
    }

    protected Music(Parcel in) {
        img = in.readInt();
        song = in.readInt();
        name = in.readString();
    }

    public static final Creator<Music> CREATOR = new Creator<Music>() {
        @Override
        public Music createFromParcel(Parcel in) {
            return new Music(in);
        }

        @Override
        public Music[] newArray(int size) {
            return new Music[size];
        }
    };

    public int getImg() {
        return img;
    }

    public int getSong() {
        return song;
    }

    public String getName() {
        return name;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(img);
        dest.writeInt(song);
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        return name;
    }
}