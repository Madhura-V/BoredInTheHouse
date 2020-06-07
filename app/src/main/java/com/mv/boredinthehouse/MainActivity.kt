package com.mv.boredinthehouse

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity(){

    //late-initialized property :- lateinit
    private lateinit var buttonBored: Button    //lateinit
    private lateinit var sendText: String       //lateinit

//    Array :- arrayOf
    private val activities = arrayOf("Exercise", "Food", "Games", "Chill", "Self Care", "Random")
    private val exerciseActivities = arrayOf("Full Body Exercise", "Arms Exercise", "Abs Exercise", "Leg Exercise", "Rest")
    private val foodActivities = arrayOf("Pizza", "Burger", "Sandwich", "Dosa", "Chocolate", "Diet")
    private val gamesActivities = arrayOf("Carrom", "Chess","Scrabble","Pictionary","Table tennis","Hide-and-seek")
    private val chillActivities = arrayOf("Watch a Movie", "Bake","Listen to soothing music","Read a book","Read a magazine")
    private val selfcareActivities = arrayOf("Start a Meditation", "Drink lots of Water", "Start a daily Gratitude Journal",
        "Deep breathing","Smile", "Dance like crazy")
    private val randomActivities = arrayOf("Sleep", "Home Spa", "Clean your closet", "Call a friend", "Play video games")
    private val changeColor = listOf("#ec407a","#ab47bc","#7e57c2","#e57373")
    var item = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinner = findViewById<Spinner>(R.id.spinner_activity)
        if (spinner != null) {
            val activityAdapter = ArrayAdapter(
                this, android.R.layout.simple_spinner_item, activities
            )
            activityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.setSelection(0, false)
            spinner.adapter = activityAdapter
            spinner.prompt = "Select activity"
            spinner.gravity = Gravity.CENTER
        }

            spinner_activity.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

            override fun onNothingSelected(parent: AdapterView<*>?) {
                //Required parameter for function
                showToast(message = "Nothing selected")

                //Named Argument
//                showToast(context = applicationContext, message = "Nothing selected",duration = Toast.LENGTH_SHORT)

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                item = position
                Log.e("TAG", item.toString())
            }
        }

        buttonBored = findViewById(R.id.button_bored)
        buttonBored.setOnClickListener {

//           Random function
            val exerciseRandomValue = Random.nextInt(exerciseActivities.size)
            val foodRandomValue = Random.nextInt(foodActivities.size)
            val gamesRandomValue = Random.nextInt(gamesActivities.size)
            val chillRandomValue = Random.nextInt(chillActivities.size)
            val selfCareRandomValue = Random.nextInt(selfcareActivities.size)
            val randomValue = Random.nextInt(randomActivities.size)

            val colorRandom = Random.nextInt(changeColor.size)
            val color:String = changeColor[colorRandom]
            val colorSet = Color.parseColor(color)
            textViewResult.setBackgroundColor(colorSet)

//           when Expression
            textViewResult.text =(when(item){
//               String templates
                0 -> "Let's do ${exerciseActivities[exerciseRandomValue]} today"  //String templates
                1 -> "Let's eat ${foodActivities[foodRandomValue]}"  //String templates
                2 -> "Let's play ${gamesActivities[gamesRandomValue]}"  //String templates
                3 -> "Let's ${chillActivities[chillRandomValue]}"  //String templates
                4 -> selfcareActivities[selfCareRandomValue]
                5 -> randomActivities[randomValue]
                else -> "Rest"
            })

            card_view.visibility = View.VISIBLE
            textViewResult.visibility = View.VISIBLE

            sendText =textViewResult.text.toString()
            image_share.visibility = View.VISIBLE
        }

        image_share.setOnClickListener {

            val shareText = "\n\nHello Friends.. Use 'Bored in the house' app. It's amazing to use when you are bored!"

//           Scope functions:- apply
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "My activity is $sendText$shareText")   //String template
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)

        }
    }

//    Required parameter for function is:- message : String
//    Default values for function is:- duration: Int = Toast.LENGTH_LONG ,
//    and context: Context = applicationContext
    private fun showToast(context: Context = applicationContext, message: String, duration: Int = Toast.LENGTH_LONG) {
        Toast.makeText(context, message, duration).show()
    }
}
