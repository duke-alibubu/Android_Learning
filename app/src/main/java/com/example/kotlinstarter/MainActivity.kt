package com.example.kotlinstarter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var image: ImageView
    private lateinit var resultText: TextView
    private lateinit var rollButton:Button
    private lateinit var resetButton:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        image = findViewById(R.id.dice_image)
        resultText = findViewById(R.id.result_text)

        rollButton = findViewById(R.id.roll_button)
        rollButton.setOnClickListener{rollDice(image,resultText)}

        resetButton  = findViewById(R.id.reset_button)
        resetButton.setOnClickListener{reset(image,resultText)}
    }

    private fun rollDice(image: ImageView, resultText: TextView ){
        Toast.makeText(this, "Let's test your luck ...",
            Toast.LENGTH_SHORT).show()

        val rolled: Int = Random().nextInt(6) +1
        resultText.text = rolled.toString()

        when (rolled) {
            1 -> image.setImageResource(R.drawable.dice_1)
            2 -> image.setImageResource(R.drawable.dice_2)
            3 -> image.setImageResource(R.drawable.dice_3)
            4 -> image.setImageResource(R.drawable.dice_4)
            5 -> image.setImageResource(R.drawable.dice_5)
            6 -> image.setImageResource(R.drawable.dice_6)
        }
    }

    private fun reset(image: ImageView, resultText: TextView){
        image.setImageResource(R.drawable.empty_dice)
        resultText.text = getString(R.string.start)
    }
}
