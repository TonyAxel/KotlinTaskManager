package com.example.lab4.model

import java.io.Serializable

data class SomethingData(
    var id: Int,
    var title: String,
    var description: String
): Serializable
