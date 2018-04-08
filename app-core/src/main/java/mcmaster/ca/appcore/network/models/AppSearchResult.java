package mcmaster.ca.appcore.network.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import mcmaster.ca.appcore.datastore.PersonResult;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class AppSearchResult implements Parcelable {
    @SerializedName("date")
    public final String date;

    @SerializedName("results")
    public final ArrayList<PersonResult> results;

    public AppSearchResult(ArrayList<PersonResult> results) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.CANADA);
        date = sdf.format(new Date());
        this.results = results;
    }

    protected AppSearchResult(Parcel in) {
        date = in.readString();
        results = in.createTypedArrayList(PersonResult.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
        dest.writeTypedList(results);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AppSearchResult> CREATOR = new Creator<AppSearchResult>() {
        @Override
        public AppSearchResult createFromParcel(Parcel in) {
            return new AppSearchResult(in);
        }

        @Override
        public AppSearchResult[] newArray(int size) {
            return new AppSearchResult[size];
        }
    };
}
