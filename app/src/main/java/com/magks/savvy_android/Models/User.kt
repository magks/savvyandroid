package com.magks.savvy_android.Models

abstract class User  {
    abstract var user_id: String
    abstract var first_name: String?            // optional
    abstract var phone_number: String
    abstract var last_name: String?             // optional
    abstract var profile_status: String
    abstract var instagram_id: String           // optional
    abstract var instagram_username: String?    // optional
    abstract var instagram_token: String?       // optional
    abstract var state: String?                 // optional
    abstract var country: String?               // optional
    abstract var city: String?                  // optional
    abstract var about: String?                 // optional
    abstract var is_active: Boolean
    abstract var number_of_time_reported: Int
    abstract var preference_from_age: Int
    abstract var preference_to_age: Int
    abstract var preference_distance: Int
    abstract var notification_message: Int
    abstract var notification_message_like: Int
    abstract var notification_game_beginning: Int
    abstract var notification_questions_present: Int
    abstract var notification_match_expiring: Int
    abstract var notification_answer_submitted: Int
    abstract var savvy_score: Int
    abstract var dob: String?                   // optional
    abstract var age: Int?                      // optional
    abstract var gender: Int?
    abstract var is_ambassador: Boolean
    abstract var school_name: String?           // optional
    abstract var images: List<UserImage>?       // optional
    abstract var meta: UserMeta?                // optional
}