package com.example.testroom.entities.relation

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Department(
    @PrimaryKey
    val id: Long,
    val name: String
)