package com.t3h.chatapp.model

import com.google.gson.annotations.SerializedName

data class Account(
    @SerializedName("user_name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("pass_word")
    val pass_word: String,
)
