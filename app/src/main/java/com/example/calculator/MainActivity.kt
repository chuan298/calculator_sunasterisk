package com.example.calculator

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Double.parseDouble
import java.lang.Exception


class MainActivity : AppCompatActivity(), View.OnClickListener{
    @SuppressLint("UseCompatLoadingForDrawables")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)


    var fragmentManager: FragmentManager? = supportFragmentManager
    var text_expression = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide();

        bt_1.setOnClickListener(this)
        bt_2.setOnClickListener(this)
        bt_3.setOnClickListener(this)
        bt_4.setOnClickListener(this)
        bt_5.setOnClickListener(this)
        bt_6.setOnClickListener(this)
        bt_7.setOnClickListener(this)
        bt_8.setOnClickListener(this)
        bt_9.setOnClickListener(this)
        bt_0.setOnClickListener(this)
        bt_add.setOnClickListener(this)
        bt_sub.setOnClickListener(this)
        bt_mul.setOnClickListener(this)
        bt_div.setOnClickListener(this)
        bt_bracket_open.setOnClickListener(this)
        bt_bracket_close.setOnClickListener(this)
        bt_del.setOnClickListener(this)
        bt_ac.setOnClickListener(this)
        bt_dot.setOnClickListener(this)
        bt_equal.setOnClickListener(this)
        //

        supportFragmentManager?.beginTransaction()?.replace(R.id.fragment, ResultFragment())?.addToBackStack(null)?.commit()



    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.bt_1 -> {
                text_expression += "1"
            }
            R.id.bt_2 -> {
                text_expression += "2"
            }
            R.id.bt_3 -> {
                text_expression += "3"
            }
            R.id.bt_4 -> {
                text_expression += "4"
            }
            R.id.bt_5 -> {
                text_expression += "5"
            }
            R.id.bt_6 -> {
                text_expression += "6"
            }
            R.id.bt_7 -> {
                text_expression += "7"
            }
            R.id.bt_8 -> {
                text_expression += "8"
            }
            R.id.bt_9 -> {
                text_expression += "9"
            }
            R.id.bt_0 -> {
                text_expression += "0"
            }
            R.id.bt_add -> {
                text_expression += "+"
            }
            R.id.bt_sub -> {
                text_expression += "-"
            }
            R.id.bt_mul -> {
                text_expression += "*"
            }
            R.id.bt_div -> {
                text_expression += "/"
            }
            R.id.bt_bracket_open -> {
                text_expression += "("
            }
            R.id.bt_bracket_close -> {
                text_expression += ")"
            }
            R.id.bt_dot -> {
                text_expression += "."
            }
            R.id.bt_del -> {
                text_expression=  text_expression.dropLast(1)
            }
            R.id.bt_ac -> {
                text_expression = ""
            }
            R.id.bt_equal -> {
                try {
                    var result = CalculatorUtil().calculate(ed_expression.text.toString())
                    result = parseDouble(result).toString()
                    if(result.matches("-?\\d+(\\.\\d+)?".toRegex())){
                        var fragment = ResultFragment()
                        var bundle = Bundle()
                        bundle.putString("text", result)
                        fragment.arguments = bundle
                        fragmentManager?.beginTransaction()?.replace(R.id.fragment, fragment)?.addToBackStack(null)?.commit()
                    }
                    else{
                        Toast.makeText(applicationContext,"error: " + result, Toast.LENGTH_SHORT).show()
                    }
                }
                catch (e: Exception){
                    println(e)
                }
            }

        }
        if(p0?.id != R.id.bt_equal){
            ed_expression.setText(text_expression)
        }


    }
}
