/* This is the starting view for the Program and the main method, it will display the List of beneficiaries pulled from the JSON file to be selected*/

package com.example.beneficiarieslisting

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
//imported widgets
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

//import for compatibility
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //local variables
        val startingText = "BENEFICIARIES"
        val buttonText = "Select Beneficiary"
        val subTitle = "      Name     Relation     Designation" //FIXME I hate this, this was just to get my idea for how I wanted it to look on the program

        //Text Size Variables
        val titleSize = 24F
        val subTitleSize = 20F

        //Color Variables
        val lightBlue = "#ccffff"
        val white = "#ffffff"
        val darkBlue = "#000066"

        //Creating a linearLayout as the main view
        val mainView = LinearLayout(this)
        mainView.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        mainView.orientation = LinearLayout.VERTICAL
        mainView.gravity = Gravity.CENTER_HORIZONTAL
        //sets background color for MainView
        mainView.setBackgroundColor(Color.parseColor(lightBlue))

        //Creating a textView for the Title
        val titleText = TextView(this)
        //Setting title size and color
        titleText.text = startingText
        titleText.textSize = titleSize
        titleText.gravity = Gravity.CENTER_HORIZONTAL
        titleText.setTextColor(Color.parseColor(white))
        //Setting title layout parameters
        val titleTextParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        titleText.layoutParams = titleTextParams
        //Adding margins and giving it it's own colors to make the title look a bit more like a banner
        titleTextParams.setMargins(0,0,0,16)
        titleText.setTextColor(Color.parseColor(white))
        titleText.setBackgroundColor(Color.parseColor(darkBlue))

        //Creating a textView for the subTitle
        val subTitleText = TextView(this)
        subTitleText.text = subTitle
        //Setting subtitle size and color
        subTitleText.textSize = subTitleSize
        subTitleText.setTextColor(Color.parseColor(darkBlue))
        //Setting subtitle layout parameters
        subTitleText.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        //creating a RecyclerView to show the list of beneficiaries
        val recyclerView = RecyclerView(this)
        recyclerView.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        recyclerView.layoutManager = LinearLayoutManager(this)

        //load and display the recyclerView data
        val beneList = LoadBeneList(this, recyclerView)
        val adapter = BeneListAdapter(this, beneList.loadAndDisplayBeneList())
        recyclerView.adapter = adapter

        //Load the currently selected radio button information
        val selectedPosition = adapter.getSelectedPosition()
        Log.d("Main Activity", "Selected position: $selectedPosition")

        //Creating the Select Button
        val selectButton = Button(this)
        selectButton.text = buttonText
        selectButton.setBackgroundColor(Color.parseColor(darkBlue))
        selectButton.setTextColor(Color.parseColor(white))
        selectButton.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        //Select Button Click Handling
        //Grabs relevant Item based on what radio button is selected when the button is pressed
        selectButton.setOnClickListener {
            //Log to check if the button click listener is being triggered
            Log.d("Select Button Clicked!", "Button Clicked!")
            val selectedPosition = adapter.getSelectedPosition()
            if (selectedPosition != RecyclerView.NO_POSITION) {
                //Handling selection confirmation here
                val selectedItem = adapter.getItem(selectedPosition)
                Log.d("Radio Button View", "Item selected:  ${selectedItem.firstName}")

                //Switches to BeneSelectedActivity
                val startBeneSelectedActivity = Intent(this, BeneSelectedActivity::class.java)
                //Passes selected item to BeneSelectedActivity
                startBeneSelectedActivity.putExtra("selectedItem", selectedItem)
                startActivity(startBeneSelectedActivity)
            } else {
                Log.d("Radio Button View else", "No Item Selected!")
            }
        }

        //Add created views to the layout
        mainView.addView(titleText)
        mainView.addView(subTitleText)
        mainView.addView(recyclerView)
        mainView.addView(selectButton)

        //set the Linear Layout as the content view of the activity
        setContentView(mainView)
    }
}