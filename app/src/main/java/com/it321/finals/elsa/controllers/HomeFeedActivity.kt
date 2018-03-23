package com.it321.finals.elsa.controllers

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.it321.finals.elsa.R
import com.it321.finals.elsa.adapters.EventsAdapter
import com.it321.finals.elsa.models.Event
import com.it321.finals.elsa.models.EventDetails
import kotlinx.android.synthetic.main.activity_home_feed.*
import kotlinx.android.synthetic.main.app_bar_home_feed.*
import kotlinx.android.synthetic.main.content_home_feed.*
import java.util.*
import kotlin.collections.ArrayList

class HomeFeedActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var mAdapter: EventsAdapter? = null
    private var mEventList: ArrayList<EventDetails>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_feed)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Event Feed"

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        mEventList = ArrayList()
        mAdapter = EventsAdapter(this, mEventList)
        recyclerView.adapter = mAdapter
        mAdapter?.add(EventDetails(Event((100..999).random(), tableTennis, " Table Tennis", " Ball Games", " Cebu City"),
                " Table Tennis Commissioner's Cup 2018", " March 20, 2018",
                " March 21, 2018", 1000.00, 100, " Pongathon", true))
        mAdapter?.add(EventDetails(Event((100..999).random(), concert, " Coldplay", " Concert", " Cebu City"),
                " Coldplay concert live in Manila", " March 30, 2018", " March 31, 2018",
                50000.00, 1000, " EMI", true))
        /*mAdapter?.add(Event((100..999).random(), eSports, " TI 2018", " E-sports", "Mandaue City"))*/

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home_feed, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun ClosedRange<Int>.random() = Random().nextInt(endInclusive - start) + start

    private val tableTennis = "https://fthmb.tqn.com/dsMtpAOPTfuLycvJ7Ytf8sDSZw8=/768x0/filters:no_upscale()/two-colleagues-playing-table-tennis-in-office-break-room-673117017-592611993df78cbe7e959740.jpg"
    private val concert = "https://www.residentadvisor.net/images/news/2017/moderat-hiatus-last-berlin-show-modeselektor-apparat-august-2017.jpg"
    private val eSports = "https://arcanebet.com/content/img/esports/esports-4.jpg"
}
