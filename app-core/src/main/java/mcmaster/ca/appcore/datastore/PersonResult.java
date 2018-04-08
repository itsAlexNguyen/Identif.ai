package mcmaster.ca.appcore.datastore;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

/**
 * This class represents a model of a person result
 */
public class PersonResult implements Parcelable, Comparable<PersonResult> {
    @SerializedName("name")
    public final String name;

    @SerializedName("score")
    public int score;

    @Nullable
    @SerializedName("profileUrl")
    public final String profileUrl;

    protected PersonResult(Parcel in) {
        name = in.readString();
        score = in.readInt();
        profileUrl = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(score);
        dest.writeString(profileUrl);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PersonResult> CREATOR = new Creator<PersonResult>() {
        @Override
        public PersonResult createFromParcel(Parcel in) {
            return new PersonResult(in);
        }

        @Override
        public PersonResult[] newArray(int size) {
            return new PersonResult[size];
        }
    };

    public void increaseScore(int amount) {
        score = score + amount;
    }


    public PersonResult(String name, int score, @Nullable String profileUrl) {
        this.name = name.trim();
        this.score = score;
        this.profileUrl = profileUrl;
    }

    public PersonResult(String name, int score) {
        this(name, score, null);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PersonResult) {
            return name.equalsIgnoreCase(((PersonResult)obj).name);
        }
        return super.equals(obj);
    }

    @Override
    public int compareTo(@NonNull PersonResult personResult) {
        return Integer.compare(personResult.score, score);
    }
}
