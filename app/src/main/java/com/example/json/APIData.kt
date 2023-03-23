package com.example.json

data class APIData(
    val code: String,
    val data: LocationDetails
)

data class LocationDetails(
    val popular: Map<String, String>,
    val other: Map<String, String>
)

