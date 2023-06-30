package com.example.simplenetworkcahing_powell_api.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Restaurant::class], version = 1)
abstract class RestaurantDatabase : RoomDatabase() {

    abstract fun restaurantDao(): RestaurantDao

    // allow Hilt Injection to initialize the table
}