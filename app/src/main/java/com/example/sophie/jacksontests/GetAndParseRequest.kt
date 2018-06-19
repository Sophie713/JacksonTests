package com.example.sophie.jacksontests

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.android.synthetic.main.activity_get_and_parse_request.*


data class HowTo(val title: String = "", val subtitle: String = "", val desc: String = "", val footer: String = "", val steps: Array<HowToStep> = arrayOf<HowToStep>())
data class HowToStep(val num: String = "", val desc: String = "")


class GetAndParseRequest : AppCompatActivity() {

    val TAG = "xyz"
    val mapper = ObjectMapper();
    val url = "http://4m.to/apps/tesco/log.json"
    val jsonForTesting = "{\"title\": \"Hello World\", \"subtitle\": \"How are you?\", \"desc\": \"I am fine.\", \"footer\": \"And you?\", \"steps\": [ {\"num\": \"1\", \"desc\": \"one\" }, {\"num\": \"2\", \"desc\": \"two\" }, {\"num\": \"3\", \"desc\": \"three\" } ]}"
    //"{title: Hello World, subtitle: How are you?, desc: I am fine., footer: And you?, steps: [ {num: 1, desc: one }, {num: 2, desc: two }, {num: 3, desc: three } ]}"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_and_parse_request)

        button3.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                getJSON();
            }
        })
    }

    fun getJSON() {
        val request = JsonObjectRequest(Request.Method.GET, url, null,

                //success
                Response.Listener {
                    try {


                        var success = it.toString()
                        Log.e(TAG, success)
                        val testOne = mapper.readValue(jsonForTesting, HowTo::class.java)
                        Log.e(TAG, testOne.toString())
                        titleTV.setText(testOne.title)
                        Log.e(TAG, testOne.title)
                        subtitleTV.setText(testOne.subtitle)
                        text3TV.setText(testOne.desc)
                        text4TV.setText(testOne.footer)
                        val array = testOne.steps
                        val step1 = array[0]
                        stepNumber1.setText(step1.num.toString())
                        stepText1.setText(step1.desc)
                        val step2 = array[1]
                        stepNumber2.setText(step2.num.toString())
                        stepText2.setText(step2.desc)
                        val step3 = array[2]
                        stepNumber3.setText(step3.num.toString())
                        stepText3.setText(step3.desc)
                    } catch (e: Exception) {
                        Log.e(TAG, e.toString())
                    }
                }

                //error
                , Response.ErrorListener {
            var success = it.toString() + "error"
            Log.e(TAG, success)
        })
        val queue = Volley.newRequestQueue(this)
        queue.add(request)
    }

}
