package com.example.json

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cities")
data class Cities(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val city: String,
    val state: String,
    val popular: Int
    ) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(city)
        parcel.writeString(state)
        parcel.writeInt(popular)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Cities> {
        override fun createFromParcel(parcel: Parcel): Cities {
            return Cities(parcel)
        }

        override fun newArray(size: Int): Array<Cities?> {
            return arrayOfNulls(size)
        }
    }
}