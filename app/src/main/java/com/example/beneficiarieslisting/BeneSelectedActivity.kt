/* This is the second view for the Program, it will display the information regarding the selected beneficiary from the first activity*/

package com.example.beneficiarieslisting

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class BeneSelectedActivity : AppCompatActivity() {

    //local variables
    private var beneInfoText = "Beneficiary Information"

    //color variables
    private val lightBlue = "#ccffff"
    private val darkBlue = "#000066"
    private val white = "#ffffff"
    private val black = "#000000"

    //text size variables
    private val titleSize = 24F
    private val subTitleSize = 20F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Retrieving the item passed from MainActivity
        /* THIS CODE ONLY WORKS ON NEWER DEVICES - TO MAINTAIN FUNCTIONALITY WITH OLDER ONES I'M INCLUDING A DEPRECATED FUNCTION INSTEAD BELOW
        val selectedItem = intent.getSerializableExtra("selectedItem", Item::class.java)
         */
        val selectedItem = intent.getSerializableExtra("selectedItem") as? Item
        if (selectedItem != null) {
            //Creating the new table layout to hold the data
            val beneSelectedView = TableLayout(this)
            beneSelectedView.layoutParams = TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.MATCH_PARENT
            )
            beneSelectedView.gravity = Gravity.START
            //Sets background color for BeneSelectedActivity
            beneSelectedView.setBackgroundColor(Color.parseColor(lightBlue))

            //Creating a textView for the Title
            val titleText = TextView(this)
            titleText.text = beneInfoText
            titleText.textSize = titleSize
            titleText.gravity = Gravity.CENTER
            val titleTextParams = TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT
            )
            //Adding margins and giving it it's own colors to make the title look a bit more like a banner
            titleTextParams.setMargins(0,0,0,16)
            titleText.setTextColor(Color.parseColor(white))
            titleText.setBackgroundColor(Color.parseColor(darkBlue))

            //Display information regarding current bene
            ///Creating table row for name
            val nameRow = TableRow(this)

                //Creating two text views for columns inside Name Row
                //first textview for name String
                val nameTitleText = TextView(this)
                nameTitleText.text = "Name:"
                nameTitleText.textSize = subTitleSize
                nameTitleText.setTextColor(Color.parseColor(black))
                val nameTitleParams = TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT
                )
                nameTitleText.layoutParams = nameTitleParams

                //Second TextView for name data
                val nameTextData = TextView(this)
                nameTextData.text = selectedItem.firstName+ " " + selectedItem.lastName
                nameTextData.textSize = subTitleSize
                nameTextData.setTextColor(Color.parseColor(black))
                val nameDataParams = TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT
                )
                nameTextData.layoutParams = nameDataParams

            //Adding name text views to the name table row
            nameRow.addView(nameTitleText)
            nameRow.addView(nameTextData)

            //Creating SSN table row
            val ssnRow = TableRow(this)

                //Creating two text views for columns inside SSN Row
                //Creating first SSN textview to contain the string
                val ssnTitleText = TextView(this)
                ssnTitleText.text = "SSN: "
                ssnTitleText.textSize = subTitleSize
                ssnTitleText.setTextColor(Color.parseColor(black))
                val ssnTitleParams = TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT
                )
                ssnTitleText.layoutParams = ssnTitleParams

                //Creating second SSN textview to contain the data
                val ssnTextData = TextView(this)
                ssnTextData.text = selectedItem.socialSecurityNumber
                ssnTextData.textSize = subTitleSize
                ssnTextData.setTextColor(Color.parseColor(black))
                val ssnDataParams = TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT
                )
                ssnTextData.layoutParams = ssnDataParams

            //Adding ssn text views to the ssn table row
            ssnRow.addView(ssnTitleText)
            ssnRow.addView(ssnTextData)

            //Display DOB
            val dobRow = TableRow(this)

                //First text view for date of birth title
                val dobTitleText = TextView(this)
                dobTitleText.text = "Date of Birth:"
                dobTitleText.textSize = subTitleSize
                dobTitleText.setTextColor(Color.parseColor(black))
                val dobTitleParams = TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT
                )
                dobTitleText.layoutParams = dobTitleParams

                //Second text view for date of birth data
                val dobTextData = TextView(this)
                dobTextData.text = selectedItem.dateOfBirth
                dobTextData.textSize = subTitleSize
                dobTextData.setTextColor(Color.parseColor(black))
                val dobDataParams = TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT
                )
                dobTextData.layoutParams = dobDataParams

            //Adding date of birth TextViews to the date of birth TableRow
            dobRow.addView(dobTitleText)
            dobRow.addView(dobTextData)


            //Display Phone number
            val phoneNumberRow = TableRow(this)

                //First TextView for phone number title
                val phoneNumberTitleText = TextView(this)
                phoneNumberTitleText.text = "Phone:"
                phoneNumberTitleText.textSize = subTitleSize
                phoneNumberTitleText.setTextColor(Color.parseColor(black))
                val phoneNumberTitleParams = TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT
                )
                phoneNumberTitleText.layoutParams = phoneNumberTitleParams

                //Second TextView for phone number data
                val phoneNumberTextData = TextView(this)
                phoneNumberTextData.text = selectedItem.phoneNumber
                phoneNumberTextData.textSize = subTitleSize
                phoneNumberTextData.setTextColor(Color.parseColor(black))
                val phoneNumberDataParams = TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT
                )
                phoneNumberTextData.layoutParams = phoneNumberDataParams

            //Adding phone number TextViews to the phone number TableRow
            phoneNumberRow.addView(phoneNumberTitleText)
            phoneNumberRow.addView(phoneNumberTextData)

            //Display address
            val addressRow = TableRow(this)

                //First text view for address title
                val addressTitleText = TextView(this)
                addressTitleText.text = "Address:"
                addressTitleText.textSize = subTitleSize
                addressTitleText.setTextColor(Color.parseColor(black))
                val addressTitleParams = TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT
                )
                addressTitleText.layoutParams = addressTitleParams

                //Second Text view for address data
                val addressTextData = TextView(this)
                addressTextData.text = "${selectedItem.firstLineMailing}, ${selectedItem.scndLineMailing}, ${selectedItem.city}, ${selectedItem.zipCode}, ${selectedItem.stateCode}, ${selectedItem.country}"
                addressTextData.textSize = subTitleSize
                addressTextData.setTextColor(Color.parseColor(black))
                val addressDataParams = TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT
                )
                addressTextData.layoutParams = addressDataParams

            //Adding address Text views to the address row
            addressRow.addView(addressTitleText)
            addressRow.addView(addressTextData)

            //Adding Table rows to the TableLayout
            beneSelectedView.addView(titleText)
            beneSelectedView.addView(nameRow)
            beneSelectedView.addView(ssnRow)
            beneSelectedView.addView(phoneNumberRow)
            beneSelectedView.addView(addressRow)

            //Activate the view layout
            setContentView(beneSelectedView)

        } else {
            Log.d("BeneSelectedActivity", "The item is not being sent from MainActivity properly")
        }
    }
}