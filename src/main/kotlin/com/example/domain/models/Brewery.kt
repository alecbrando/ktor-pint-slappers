package com.example.pintslappers.domain.models

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import kotlinx.serialization.Serializable
import org.mongodb.kbson.ObjectId
@Serializable
data class Brewery  (
    @PrimaryKey
    val _id: ObjectId? = null,
    val name: String = "",
    val city: String = "",
    val state: String = "",
    val country: String = "",
    val types: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
   ): RealmObject


