package com.example.quizgame

import android.os.Parcel
import android.os.Parcelable

data class Quiz(
    val pictureName: String,
    val question: String,
    val options: List<String>,
    val correctAnswer: String
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.createStringArrayList() ?: emptyList(),
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(pictureName)
        parcel.writeString(question)
        parcel.writeStringList(options)
        parcel.writeString(correctAnswer)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Quiz> {
        override fun createFromParcel(parcel: Parcel): Quiz {
            return Quiz(parcel)
        }

        override fun newArray(size: Int): Array<Quiz?> {
            return arrayOfNulls(size)
        }
    }
}

