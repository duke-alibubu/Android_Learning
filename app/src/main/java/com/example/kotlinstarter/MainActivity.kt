package com.example.kotlinstarter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val image:ImageView = findViewById(R.id.dice_image)
        val resultText: TextView = findViewById(R.id.result_text)

        val rollButton:Button = findViewById(R.id.roll_button)
        rollButton.setOnClickListener{rollDice(image,resultText)}

        val resetButton:Button = findViewById(R.id.reset_button)
        resetButton.setOnClickListener{reset(image,resultText)}
    }

    private fun rollDice(image: ImageView, resultText: TextView ){
        Toast.makeText(this, "Let's test your luck ...",
            Toast.LENGTH_SHORT).show()

        val rolled: Int = Random().nextInt(6) +1
        resultText.text = rolled.toString()

        when (rolled) {
            1 -> image.setImageResource(R.drawable.dice1)
            2 -> image.setImageResource(R.drawable.dice2)
            3 -> image.setImageResource(R.drawable.dice3)
            4 -> image.setImageResource(R.drawable.dice4)
            5 -> image.setImageResource(R.drawable.dice5)
            6 -> image.setImageResource(R.drawable.dice6)
        }
    }

    private fun reset(image: ImageView, resultText: TextView){
        image.setImageResource(R.drawable.blank)
        resultText.text = getString(R.string.start)
    }
}
