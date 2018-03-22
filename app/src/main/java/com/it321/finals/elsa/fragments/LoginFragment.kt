package com.it321.finals.elsa.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.it321.finals.elsa.R
import com.it321.finals.elsa.controllers.HomeFeed
import com.it321.finals.elsa.controllers.MainActivity
import com.it321.finals.elsa.models.User
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment(), View.OnClickListener, OnCompleteListener<AuthResult> {

    private var mBtnLogIn: Button? = null
    private var mFirebaseAuth: FirebaseAuth? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView = inflater!!.inflate(R.layout.fragment_login, container, false)

        mFirebaseAuth = FirebaseAuth.getInstance()
        activity.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        findViews(rootView)
        mBtnLogIn?.setOnClickListener(this)

        return rootView
    }

    private fun findViews(view: View?) {
        mBtnLogIn = view?.findViewById(R.id.btnLogIn)
    }

    override fun onClick(view: View?) {
        val userName = etUsernameLogin?.text.toString().trim()
        val password = etPasswordLogin?.text.toString().trim()

        when {
            TextUtils.isEmpty(userName) -> etUsernameLogin?.error = "Please input username"
            TextUtils.isEmpty(password) -> etPasswordLogin?.error = "Please input password"
            else -> {
                login(userName, password); progressBar.visibility = View.VISIBLE
                (activity as MainActivity).hideKeyboard(view)
                etUsernameLogin.isFocusable = false; etPasswordLogin.isFocusable = false
            }
        }
    }

    private fun login(userName: String?, password: String?) {
        val user = User(userName, password)
        mFirebaseAuth?.signInWithEmailAndPassword(user.userName!!, user.password!!)?.addOnCompleteListener(activity, this)
    }

    override fun onComplete(task: Task<AuthResult>) {
        if (task.isSuccessful) {
            (activity as MainActivity).toast("Successfully logged in.")
            context.startActivity(Intent(context, HomeFeed::class.java))
        }
        else
            (activity as MainActivity).toast("Username and/or password is incorrect.")

        progressBar.visibility = View.GONE
        etUsernameLogin.isFocusableInTouchMode = true; etPasswordLogin.isFocusableInTouchMode = true
    }

}// Required empty public constructor
