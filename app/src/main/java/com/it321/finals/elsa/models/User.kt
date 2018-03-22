package com.it321.finals.elsa.models

data class User(val userName: String? = null, val password: String? = null, val status: Boolean = false)

data class Customer(val user: User, val id: Int, val name: String, val gender: String, val address: String, val email: String)

data class Business(val user: User, val id: Int, val name: String, val type: String, val description: String, val address: String, val email: String)

data class Admin(val user: User, val name: String, val email: String)

data class Category(val events: ArrayList<Event>, val name: String, val type: String)

data class Event(val customers: ArrayList<Customer>, val business: Business, val name: String, val type: String, val location: String, val description: String)

data class EventDetails(val event: Event, val dateStarted: String, val dateEnded: String, val price: Double, val capacity: Int, val status: Boolean)

data class EventMap(val event: Event, val name: String, val address: String)

data class EventRequest(val id: Int, val name: String, val type: String)

data class Attendance(val event: Event, val dataJoined: String, val remarks: String)