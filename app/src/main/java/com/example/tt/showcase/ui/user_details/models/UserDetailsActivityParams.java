package com.example.tt.showcase.ui.user_details.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.tt.showcase.ui.user_details.UserDetailsActivity;

/**
 * Created by TT on 11.3.2018..
 * Showcase
 *
 * Contains parameters necessary to launch {@link UserDetailsActivity}.
 */

public class UserDetailsActivityParams implements Parcelable {

    private final int id;
    private final String name;

    public UserDetailsActivityParams(int id, String name) {
        this.id = id;
        this.name = name;
    }

    private UserDetailsActivityParams(Parcel in) {
        id = in.readInt();
        name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UserDetailsActivityParams> CREATOR = new Creator<UserDetailsActivityParams>() {
        @Override
        public UserDetailsActivityParams createFromParcel(Parcel in) {
            return new UserDetailsActivityParams(in);
        }

        @Override
        public UserDetailsActivityParams[] newArray(int size) {
            return new UserDetailsActivityParams[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
