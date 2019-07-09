package com.android.example.colormyviews

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var boxOneText:TextView
    private lateinit var boxTwoText:TextView
    private lateinit var boxThreeText:TextView
    private lateinit var boxFourText:TextView
    private lateinit var boxFiveText:TextView
    private lateinit var rootConstraintLayout:View
    private lateinit var redButton:Button
    private lateinit var greenButton:Button
    private lateinit var yellowButton:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        boxOneText = findViewById(R.id.box_one_text)
        boxTwoText = findViewById(R.id.box_two_text)
        boxThreeText = findViewById(R.id.box_three_text)
        boxFourText = findViewById(R.id.box_four_text)
        boxFiveText = findViewById(R.id.box_five_text)
        rootConstraintLayout = findViewById(R.id.constraint_layout)
        redButton = findViewById(R.id.red_button)
        greenButton = findViewById(R.id.green_button)
        yellowButton = findViewById(R.id.yellow_button)

        setListeners()

    }
    
    private fun makeColored(view: View){
        when (view.id){
            R.id.box_one_text -> view.setBackgroundColor(Color.DKGRAY)
            R.id.box_two_text -> view.setBackgroundColor(Color.GRAY)
            R.id.box_three_text -> view.setBackgroundColor(Color.BLUE)
            R.id.box_four_text -> view.setBackgroundColor(Color.MAGENTA)
            R.id.box_five_text -> view.setBackgroundColor(Color.BLUE)
            R.id.red_button -> {
                boxOneText.setBackgroundResource(R.color.my_red)
                boxTwoText.setBackgroundResource(R.color.my_red)
                boxThreeText.setBackgroundResource(R.color.my_red)
                boxFourText.setBackgroundResource(R.color.my_red)
                boxFiveText.setBackgroundResource(R.color.my_red)
            }
            R.id.green_button -> {
                boxOneText.setBackgroundResource(R.color.my_green)
                boxTwoText.setBackgroundResource(R.color.my_green)
                boxThreeText.setBackgroundResource(R.color.my_green)
                boxFourText.setBackgroundResource(R.color.my_green)
                boxFiveText.setBackgroundResource(R.color.my_green)
            }
            R.id.yellow_button -> {
                boxOneText.setBackgroundResource(R.color.my_yellow)
                boxTwoText.setBackgroundResource(R.color.my_yellow)
                boxThreeText.setBackgroundResource(R.color.my_yellow)
                boxFourText.setBackgroundResource(R.color.my_yellow)
                boxFiveText.setBackgroundResource(R.color.my_yellow)
            }
            else -> view.setBackgroundColor(Color.LTGRAY)
        }
    }

    private fun setListeners(){
        val clickableViews: List<View> = listOf(boxOneText, boxTwoText, boxThreeText, boxFourText, boxFiveText, rootConstraintLayout, redButton, greenButton, yellowButton)
        for (item in clickableViews)
        {
            item.setOnClickListener{makeColored(it)}
        }
    }
}
