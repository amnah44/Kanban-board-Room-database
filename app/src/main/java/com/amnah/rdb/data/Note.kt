package com.amnah.rdb.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "NOTE_TABLE")
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "Name") val name: String?,
    @ColumnInfo(name = "Description") val description: String?,
    @ColumnInfo(name = "AssignedTo") val assignedTo: String?,
    @ColumnInfo(name = "StateOfTask") var stateOfTask: String?,
    @ColumnInfo(name = "Date") val date: Date,
    val isImportant: Boolean,
)
