package com.it321.finals.elsa.controllers

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.it321.finals.elsa.adapters.SectionsPageAdapter
import com.it321.finals.elsa.R
import com.it321.finals.elsa.fragments.SignUpFragment
import com.it321.finals.elsa.fragments.LoginFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var mAdapter: SectionsPageAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mAdapter = SectionsPageAdapter(supportFragmentManager)
        addFragmentTab()
        tabs.setupWithViewPager(viewPager)
    }

    private fun addFragmentTab() {
        viewPager.adapter = mAdapter
        mAdapter?.addFragment(LoginFragment(), "Log In")
        mAdapter?.addFragment(SignUpFragment(), "Sign Up")
        mAdapter?.notifyDataSetChanged()
    }

    fun hideKeyboard(view: View?) {
        val inputMethodManager = view?.context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun toast(text: String?) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}
