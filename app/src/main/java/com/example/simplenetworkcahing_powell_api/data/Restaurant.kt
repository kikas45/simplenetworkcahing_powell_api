package com.example.simplenetworkcahing_powell_api.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "restaurants")
data class Restaurant(
   @PrimaryKey val name: String,
    val type: String,
    val logo: String,
    val address: String,
)