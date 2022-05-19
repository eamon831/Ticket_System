package com.example.ticketsystem.Users;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity
public class Model implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    public int uid;
    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    public String lastName;

    @ColumnInfo(name = "Date Of Birth")
    public String DOB;

    @ColumnInfo(name = "Nhi Number")
    public String nhi;


    @ColumnInfo(name = "Bookings")
    public ArrayList<String> v=new ArrayList<String>();

    public Model(int uid, String firstName, String lastName, String DOB, String nhi, ArrayList<String> v) {
        this.uid = uid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.DOB = DOB;
        this.nhi = nhi;
        this.v = v;
    }

    protected Model(Parcel in) {
        uid = in.readInt();
        firstName = in.readString();
        lastName = in.readString();
        DOB = in.readString();
        nhi = in.readString();
        v = in.createStringArrayList();
    }

    public static final Creator<Model> CREATOR = new Creator<Model>() {
        @Override
        public Model createFromParcel(Parcel in) {
            return new Model(in);
        }

        @Override
        public Model[] newArray(int size) {
            return new Model[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(uid);
        parcel.writeString(firstName);
        parcel.writeString(lastName);
        parcel.writeString(DOB);
        parcel.writeString(nhi);
        parcel.writeStringList(v);
    }
}