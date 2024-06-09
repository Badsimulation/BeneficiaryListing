/* This is the adapter for the recyclerview and also contains the logic that makes the radio buttons and button work properly, I also included some getters that
came to mind as potentially being useful to me during my efforts, though I would remove them if not needed later in development*/

package com.example.beneficiarieslisting

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.content.Context
import android.graphics.Color
import android.widget.LinearLayout
import android.widget.RadioButton


//This adapter class is responsible for managing the RecyclerView data and binding it to the RecyclerView

class BeneListAdapter(private val context: Context, private val items: List<Item>) : RecyclerView.Adapter<BeneListAdapter.ViewHolder>() {

    //Local variables
    //text size values
    private val bodySize = 18F

    //ViewHolder variable to hold the selected position for the Radio button
    private var selectedPosition = RecyclerView.NO_POSITION

    //Color Variables
    private val bodyColor = "#000000"

    //Padding array
    private val paddingValues = intArrayOf(10,10,10,10)

    //Creating ViewHolder class to hold references to the views
    class ViewHolder(val radioButtonView: RadioButton) : RecyclerView.ViewHolder(radioButtonView)

    //Creating new ViewHolder instances
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        //Creating radioButtons to hold and display String data from the JSON file
        val radioButtonView = RadioButton(context)
            radioButtonView.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1f
            )

        //Returns radioButtonView
        return ViewHolder(radioButtonView)
    }

    //Binding data to ViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        //if statement to determine what gets displayed for designation code
        if (item.designationCode == "P") {
            holder.radioButtonView.text = "${item.firstName.trim()} ${item.lastName.trim()} \t\t ${item.beneType.trim()} \t\t " + "Primary"
            //Setting text size and color for items
            holder.radioButtonView.textSize = bodySize
            holder.radioButtonView.setTextColor(Color.parseColor(bodyColor))
            //Setting padding to separate Items
            holder.radioButtonView.setPadding(paddingValues[0],paddingValues[1],paddingValues[2],paddingValues[3])

            } else if (item.designationCode == "C") {
                holder.radioButtonView.text = "${item.firstName.trim()} ${item.lastName.trim()} \t\t ${item.beneType.trim()} \t\t " + "Contingent"
                //Setting text size and color for items
                holder.radioButtonView.textSize = bodySize
                holder.radioButtonView.setTextColor(Color.parseColor(bodyColor))
                //Setting padding to separate items
                holder.radioButtonView.setPadding(paddingValues[0],paddingValues[1],paddingValues[2],paddingValues[3])
                }

        //Creates logic for when RadioButton is clicked
        holder.radioButtonView.isChecked = position == selectedPosition
        holder.radioButtonView.setOnClickListener {
            if (selectedPosition != holder.bindingAdapterPosition) {
                val previousSelectedPosition = selectedPosition
                selectedPosition = holder.bindingAdapterPosition
                notifyItemChanged(previousSelectedPosition)
                notifyItemChanged(selectedPosition)
            }
        }

    }




    //Function to get the currently selected Radio Button
    fun getSelectedPosition(): Int {
        return selectedPosition
    }

    //Return total number of items
    override fun getItemCount(): Int {
        return items.size
    }

    //A getter for retrieving an item if the position in the list is known
    fun getItem(position: Int): Item {
        return items[position]
    }
}