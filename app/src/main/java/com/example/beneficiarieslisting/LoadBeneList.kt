/* This is code needed to load and parse the JSON file into variables that can be used by the program. It will return an empty string for the appropriate variable
if the data is missing or null*/

package com.example.beneficiarieslisting

//importing needed resources
import android.util.Log
import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import org.json.JSONObject

class LoadBeneList(private val context: Context, private val recyclerView: RecyclerView) {

    //local variables
    private var lastName: String = ""
    private var firstName: String = ""
    private var designationCode: String = ""
    private var beneType: String = ""
    private var socialSecurityNumber: String = ""
    private var dateOfBirth: String = ""
    private var middleName: String = ""
    private var phoneNumber = ""
    private var firstLineMailing: String = ""
    private var scndLineMailing: String = ""
    private var city: String = ""
    private var zipCode: String = ""
    private var stateCode: String = ""
    private var country: String = ""


    //Function for loading the and displaying the BeneList
    fun loadAndDisplayBeneList(): List<Item> {
        recyclerView.layoutManager = GridLayoutManager(context, 1).apply {
            spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return when (position % 2) {
                        0 -> 1 //First item in each row will span the entire column -- this is messy but I wanted to see if I could use it for the title row
                        else -> 1 //other items all span 1 column
                    }
                }
            }
        }
        val adapter = BeneListAdapter(context, getItemsFromJson())
        recyclerView.adapter = adapter
        return getItemsFromJson()
    }

    //Function for loading the data from the file and parsing
    private fun getItemsFromJson(): List<Item> {
        val items = mutableListOf<Item>()
        try {
            //read JSON file
            val jsonString = loadJSON("Beneficiaries.json")

            //parse JSON array
            val jsonArray = JSONArray(jsonString.toString())

            //Log to check progress
            Log.d("LoadBeneList", "Total items: ${jsonArray.length()}")


            //checks each value to see if it does not exist or is null, true returns a blank string, false returns the string data
            for (i in 0 until jsonArray.length()) {
                val jsonObject: JSONObject = jsonArray.getJSONObject(i)

                //LAST NAME
                if (!jsonObject.has("lastName") || jsonObject.isNull("lastName")) {
                    /*Nothing, the string is set to empty be default*/
                } else {
                    lastName = jsonObject.getString("lastName")
                }
                //FIRST NAME
                if (!jsonObject.has("firstName") || jsonObject.isNull("firstName")) {
                    /*Nothing, the string is set to empty be default*/
                } else {
                    firstName = jsonObject.getString("firstName")
                }
                //DESIGNATION CODE
                if (!jsonObject.has("designationCode") || jsonObject.isNull("designationCode")) {
                    /*Nothing, the string is set to empty be default*/
                } else {
                    designationCode = jsonObject.getString("designationCode")
                }
                //BENE TYPE
                if (!jsonObject.has("beneType") || jsonObject.isNull("beneType")) {
                    /*Nothing, the string is set to empty be default*/
                } else {
                    beneType = jsonObject.getString("beneType")
                }
                //SOCIAL SECURITY NUMBER
                if (!jsonObject.has("socialSecurityNumber") || jsonObject.isNull("socialSecurityNumber")) {
                    /*Nothing, the string is set to empty be default*/
                } else {
                    socialSecurityNumber = jsonObject.getString("socialSecurityNumber")
                }
                //DATE OF BIRTH
                if (!jsonObject.has("dateOfBirth") || jsonObject.isNull("dateOfBirth")) {
                    /*Nothing, the string is set to empty be default*/
                } else {
                    dateOfBirth = jsonObject.getString("dateOfBirth")
                }
                //MIDDLE NAME
                if (!jsonObject.has("middleName") || jsonObject.isNull("middleName")) {
                    /*Nothing, the string is set to empty be default*/
                } else {
                    middleName = jsonObject.getString("middleName")
                }
                //PHONE NUMBER
                if (!jsonObject.has("phoneNumber") || jsonObject.isNull("phoneNumber")) {
                    /*Nothing, the string is set to empty be default*/
                } else {
                    phoneNumber = jsonObject.getString("phoneNumber")
                }

                //parses nested array for address information
                val beneficiaryAddressJson: JSONObject = jsonObject.getJSONObject("beneficiaryAddress")

                //FIRST LINE MAILING
                if (!beneficiaryAddressJson.has("firstLineMailing") || beneficiaryAddressJson.getString("firstLineMailing").equals(null)){
                    /*Nothing, the string is set to empty be default*/
                } else {
                    firstLineMailing = beneficiaryAddressJson.getString("firstLineMailing")
                }
                //SECOND LINE MAILING
                if (!beneficiaryAddressJson.has("scndLineMailing") || beneficiaryAddressJson.getString("scndLineMailing").equals(null)){
                    /*Nothing, the string is set to empty be default*/
                } else {
                    scndLineMailing = beneficiaryAddressJson.getString("scndLineMailing")
                }
                //CITY
                if (!beneficiaryAddressJson.has("city") || beneficiaryAddressJson.getString("city").equals(null)){
                    /*Nothing, the string is set to empty be default*/
                } else {
                    city = beneficiaryAddressJson.getString("city")
                }
                //ZIPCODE
                if (!beneficiaryAddressJson.has("zipCode") || beneficiaryAddressJson.getString("zipCode").equals(null)){
                    /*Nothing, the string is set to empty be default*/
                } else {
                    zipCode = beneficiaryAddressJson.getString("zipCode")
                }
                //STATE CODE
                if (!beneficiaryAddressJson.has("stateCode") || beneficiaryAddressJson.getString("stateCode").equals(null)){
                    /*Nothing, the string is set to empty be default*/
                } else {
                    stateCode = beneficiaryAddressJson.getString("stateCode")
                }
                //COUNTRY
                if (!beneficiaryAddressJson.has("country") || beneficiaryAddressJson.getString("country").equals(null)){
                    /*Nothing, the string is set to empty be default*/
                } else {
                    country = beneficiaryAddressJson.getString("country")
                }

                //Print sample item to confirm progress
                Log.d("LoadBeneList", "Item $i: $lastName, $firstName, $designationCode, $beneType, $socialSecurityNumber, $dateOfBirth, $middleName, $phoneNumber," +
                                                "$firstLineMailing, $scndLineMailing, $city, $zipCode, $stateCode, $country")

                //adds parsed information as items
                items.add(Item(lastName,firstName,designationCode,beneType,socialSecurityNumber,dateOfBirth,middleName,phoneNumber,
                                firstLineMailing, scndLineMailing, city, zipCode, stateCode, country))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return items
    }

    //Loads the JSON file -- I know this gives a warning because the filename is always Beneficiaries.json, but I prefer to leave it in case another file was ever wanted to be loaded
    private fun loadJSON(filename: String): String? {
        var json: String? = null
        try {
            //checking file location
            val files = context.assets.list("")
            //Log to check that I'm getting to this point in the code
            Log.d("AssetFiles", "Files in assets directory: ${files?.joinToString(", ")}")

            val inputStream = context.assets.open(filename)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, Charsets.UTF_8)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return json
    }
}