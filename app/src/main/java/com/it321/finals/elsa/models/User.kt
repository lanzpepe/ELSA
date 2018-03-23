package com.it321.finals.elsa.models

import java.io.Serializable

data class User(val userName: String? = null, val password: String? = null, val status: Boolean = false)

data class Customer(val user: User, val id: Int, val name: String, val gender: String, val address: String, val email: String)

data class Business(val user: User, val id: Int, val name: String, val type: String, val description: String, val address: String, val email: String)

data class Admin(val user: User, val name: String, val email: String)

data class Category(val events: ArrayList<Event>, val name: String, val type: String)

data class Event(val id: Int, val image: String, val name: String, val type: String, val location: String)

data class EventDetails(val event: Event, val description: String, val dateStarted: String, val dateEnded: String, val fee: Double, val capacity: Int, val business: String, val status: Boolean) : Serializable

data class EventMap(val event: Event, val name: String, val address: String)

data class EventRequest(val id: Int, val name: String, val type: String)

data class Attendance(val event: Event, val dataJoined: String, val remarks: String)