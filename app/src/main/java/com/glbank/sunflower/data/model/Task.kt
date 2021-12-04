package com.glbank.sunflower.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Task(
    @PrimaryKey val id: Int? = null,
    @ColumnInfo val title: String,
    @ColumnInfo val des: String
) : Parcelable