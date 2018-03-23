package com.it321.finals.elsa.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.it321.finals.elsa.R
import com.it321.finals.elsa.controllers.MainActivity
import com.it321.finals.elsa.models.User
import kotlinx.android.synthetic.main.fragment_sign_up.*

class SignUpFragment : Fragment(), OnCompleteListener<AuthResult>, AdapterView.OnItemSelectedListener, View.OnClickListener {

    private var mSpnUser: Spinner? = null
    private var mSpnBusinessType: Spinner? = null
    private var mSpnGender: Spinner? = null
    private var mBtnBusinessSignUp: Button? = null
    private var mBtnCustomerSignUp: Button? = null
    private var mFirebaseAuth: FirebaseAuth? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView = inflater!!.inflate(R.layout.fragment_sign_up, container, false)

        mFirebaseAuth = FirebaseAuth.getInstance()
        findViews(rootView)
        setViews()
        mBtnBusinessSignUp?.setOnClickListener(this)
        mBtnCustomerSignUp?.setOnClickListener(this)

        return rootView
    }

    private fun findViews(view: View?) {
        mSpnUser = view?.findViewById(R.id.spnUser)
        mSpnBusinessType = view?.findViewById(R.id.spnBusinessType)
        mSpnGender = view?.findViewById(R.id.spnGender)
        mBtnBusinessSignUp = view?.findViewById(R.id.btnBusinessSignUp)
        mBtnCustomerSignUp = view?.findViewById(R.id.btnCustomerSignUp)
    }

    private fun setViews() {
        val userAdapter = getArrayAdapter(arrayOf("<choose one>", "Customer", "Business"))
        val businessTypeAdapter = getArrayAdapter(arrayOf("<choose one>", "Department Store", "Club", "Private Company"))
        val genderAdapter = getArrayAdapter(arrayOf("<choose one>", "Male", "Female"))

        mSpnUser?.adapter = userAdapter
        mSpnBusinessType?.adapter = businessTypeAdapter
        mSpnGender?.adapter = genderAdapter

        mSpnUser?.setSelection(mSpnUser?.selectedItemPosition!!, false)
        mSpnBusinessType?.setSelection(mSpnBusinessType?.selectedItemPosition!!, false)
        mSpnGender?.setSelection(mSpnGender?.selectedItemPosition!!, false)

        mSpnUser?.onItemSelectedListener = this
        mSpnBusinessType?.onItemSelectedListener = this
        mSpnGender?.onItemSelectedListener = this
    }

    override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (adapterView?.id) {
            R.id.spnUser -> {
                when (adapterView.selectedItemPosition) {
                    0 -> { businessContainer.visibility = View.GONE; customerContainer.visibility = View.GONE }
                    1 -> { businessContainer.visibility = View.GONE; customerContainer.visibility = View.VISIBLE }
                    2 -> { customerContainer.visibility = View.GONE; businessContainer.visibility = View.VISIBLE }
                }
            }
            R.id.spnBusinessType -> {}
            R.id.spnGender -> {}
        }
    }

    override fun onNothingSelected(adapterView: AdapterView<*>?) {
        (activity as MainActivity).toast("Nothing selected.")
    }

    override fun onClick(view: View?) {
        val userEmail = etEmailAddress?.text.toString().trim()
        val password = etPassword?.text.toString().trim()

        when {
            TextUtils.isEmpty(userEmail) -> etEmailAddress?.error = "Username is required"
            TextUtils.isEmpty(password) -> etPassword?.error = "Password is required"
            else -> {
                when (view?.id) {
                    R.id.btnBusinessSignUp -> register(userEmail, password)
                    R.id.btnCustomerSignUp -> register(userEmail, password)
                }
                progressBar1.visibility = View.VISIBLE; progressBar2.visibility = View.VISIBLE
            }
        }
    }

    private fun register(userEmail: String?, password: String?) {
        val user = User(userEmail, password)
        mFirebaseAuth?.createUserWithEmailAndPassword(user.userName!!, user.password!!)?.addOnCompleteListener(activity, this)
    }

    override fun onComplete(task: Task<AuthResult>) {
        if (task.isSuccessful)
            (activity as MainActivity).toast("Registered successfully!")
        else
            (activity as MainActivity).toast("Could not register. Please try again.")

        progressBar1.visibility = View.GONE; progressBar2.visibility = View.GONE
    }

    private fun getArrayAdapter(strings: Array<String>) : ArrayAdapter<String> =
            ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, strings)

}// Required empty public constructor
