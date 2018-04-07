package mcmaster.ca.appcore.datastore;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * This class represents a model of a person result
 */
public class PersonResult implements Parcelable {
    @SerializedName("firstName")
    public final String firstName;

    @SerializedName("lastName")
    public final String lastName;

    @SerializedName("score")
    public final int score;

    public PersonResult(String firstName, String lastName, int score) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.score = score;
    }

    protected PersonResult(Parcel in) {
        firstName = in.readString();
        lastName = in.readString();
        score = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeInt(score);
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
}
