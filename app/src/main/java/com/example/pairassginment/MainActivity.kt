package com.example.pairassginment

import android.content.ContentValues.TAG
import android.content.Intent
import android.content.SharedPreferences
import android.nfc.Tag
import android.os.Bundle
import android.text.TextUtils.isEmpty
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pairassginment.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth


import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.firestore.FirebaseFirestore


class MainActivity : AppCompatActivity(){
    private lateinit var bindingMain: ActivityMainBinding
    private var mAuthListener: AuthStateListener? = null
    private var mEmail : EditText? = null
    private var mPassword : EditText? = null
    private val SHARED_PREFS: String = "sharedPrefs"
    private var sharedPreferences: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null
    private var mDB: FirebaseFirestore = FirebaseFirestore.getInstance()
    private var circleProgress: RelativeLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMain = ActivityMainBinding.inflate(layoutInflater)
        mEmail = bindingMain.emailLogin
        mPassword = bindingMain.passwordLogin
        circleProgress = bindingMain.circleCenterLayout
        circleProgress!!.visibility = View.GONE

        setContentView(bindingMain.root)

        Log.d("dfsdfadf", "asdfsadf")

        // set the authorized listener
        setupFirebaseAuth();

        // to keep the email and password
        sharedPreferences = this.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)
        editor = sharedPreferences!!.edit();

        // get the email and password that store in shared preferences
        val autoEmail = sharedPreferences!!.getString("email","").toString()
        val autoPassword = sharedPreferences!!.getString("password","").toString()

//        Log.d("Tag", autoEmail)
//        Log.d("Tag", autoPassword)

        // if the stored email is not empty then auto fill up the input field and sign in
        if(autoEmail.isNotEmpty()){
            autoLogin(autoEmail, autoPassword);
        }

        bindingMain.signInButton.setOnClickListener{
            signIn()
        }
    }

    /*
        ----------------------------- Firebase setup ---------------------------------
     */

    // this is the authorized listener, once the auth success than redirect to another activity
    private fun setupFirebaseAuth() {
        Log.d(TAG, "setupFirebaseAuth: started.")
        mAuthListener = AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
            if (user != null) {
                Log.d(TAG, "onAuthStateChanged:signed_in:" + user.uid)
                Toast.makeText(
                    this@MainActivity,
                    "Authenticated with: " + user.email,
                    Toast.LENGTH_SHORT
                ).show()

                mDB.collection("Users")
                    .document(user.uid)
                    .get()
                    .addOnSuccessListener { document ->
                        if (document != null){
                            val role = document.data!!.getValue("Role").toString()

                            when(role){
                                "student" ->{
                                    val intent = Intent(this@MainActivity, com.example.pairassginment.student.Dashboard::class.java)
                                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    intent.putExtra("role_id", document.data!!.getValue("Role_ID").toString())
                                    startActivity(intent)
                                    finish()
                                }
                                "coordinator" ->{
                                    val intent = Intent(this@MainActivity, com.example.pairassginment.coordinator.Dashboard::class.java)
                                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    intent.putExtra("role_id", document.data!!.getValue("Role_ID").toString())
                                    startActivity(intent)
                                    finish()
                                }
                                "supervisor" ->{
                                    Log.d("Role" , role)
                                }
                            }
                        }
                    }
            } else {
                // User is signed out
                Log.d(TAG, "onAuthStateChanged:signed_out")
            }
            // ...
        }
    }

    override fun onStart() {
        super.onStart()
        FirebaseAuth.getInstance().addAuthStateListener(mAuthListener!!)
    }

    override fun onStop() {
        super.onStop()
        if (mAuthListener != null) {
            FirebaseAuth.getInstance().removeAuthStateListener(mAuthListener!!)
        }
    }

    private fun signIn() {
        circleProgress!!.visibility = View.VISIBLE
        //check if the fields are filled out
        if (!isEmpty(mEmail!!.text.toString()) && !isEmpty(mPassword!!.text.toString())
        ) {
            Log.d(TAG, "onClick: attempting to authenticate.")
            Log.d("User", mEmail!!.text.toString())
            Log.d("Pass", mPassword!!.text.toString())

            FirebaseAuth.getInstance().signInWithEmailAndPassword(
                mEmail!!.text.toString(), mPassword!!.text.toString()
            )
                .addOnCompleteListener{
                    circleProgress!!.visibility = View.GONE
                }.addOnSuccessListener {

                    // for first time if that user success to login then store the email and password to shared preferences
                    if(sharedPreferences!!.getString("email","").toString().isEmpty()){
                        editor!!.putString("email", mEmail!!.text.toString());
                        editor!!.putString("password", mPassword!!.text.toString());
                        editor!!.apply();
                    }

//                    val intent = Intent(this, Dashboard::class.java)
//                    startActivity(intent)
//                    finish()

                }.addOnFailureListener {
                    Toast.makeText(this@MainActivity, "Authentication Failed", Toast.LENGTH_SHORT)
                        .show()
                }
        } else {
            Toast.makeText(
                this@MainActivity,
                "You didn't fill in all the fields.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun autoLogin(email: String, password: String){
        mEmail!!.setText(email)
        mPassword!!.setText(password)

        signIn()
    }

}