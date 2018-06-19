package com.example.sophie.jacksontests

import android.app.VoiceInteractor
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.android.synthetic.main.activity_main.*

class SimpleVolleyGetRequest : AppCompatActivity() {
    /** create a mapper for Jackson */
    val TAG = "xyz"
        val mapper = ObjectMapper();
        val url = "http://4m.to/apps/tesco/log.json"
        val jsonForTesting = "{title: Hello World, subtitle: How are you?, desc: I am fine., footer: And you?, steps: [ {num: 1, desc: one }, {num: 2, desc: two }, {num: 3, desc: three } ]}"

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                getJSON();
            }
        })

        button2.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(applicationContext, GetAndParseRequest::class.java)
                startActivity(intent)
            }
        })
    }

    fun myToast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun getJSON(){
        val request = JsonObjectRequest(Request.Method.GET, url, null,
                Response.Listener {
                    var success = it.toString()
                    Log.e(TAG, success)
                }, Response.ErrorListener {
                    var success = it.toString() + "error"
                    Log.e(TAG, success)
        })
        val queue = Volley.newRequestQueue(this)
        queue.add(request)
    }
    /** jackson */

}