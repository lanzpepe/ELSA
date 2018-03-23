package com.it321.finals.elsa.controllers

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.it321.finals.elsa.R
import com.it321.finals.elsa.adapters.EventsAdapter
import com.it321.finals.elsa.models.EventDetails
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_event_details.*

class EventDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_details)
        supportActionBar?.title = "Event Details"
        val eventDetails = intent.getSerializableExtra(EventsAdapter.KEY_EVENT) as EventDetails

        Picasso.with(this).load(eventDetails.event.image).into(ivEventDetailPicture)
        tvEventDetailName.text = eventDetails.event.name
        tvEventOrganizer.append(eventDetails.business)
        tvEventDescription.append(eventDetails.description)
        tvEventDate.append(eventDetails.dateStarted)
        tvEventFinish.append(eventDetails.dateEnded)
        tvFee.append(" ${eventDetails.fee}")
        tvCapacity.append(" ${eventDetails.capacity}")
        tvStatus.append(" ${eventDetails.status}")
    }
}
