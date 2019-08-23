package com.magks.savvy_android.Models

abstract class UserMeta {
    abstract var user_meta_id: Int
    abstract var device_token: String
    abstract var device_type: Int
    // What is the difference between long,lat vs curr-long,curr-lat
    abstract var longitude: String
    abstract var latitude: String
    abstract var curr_latitude: String
    abstract var curr_longitude: String
}