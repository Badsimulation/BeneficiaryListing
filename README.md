BENEFICIARIES LISTING

This program will import a JSON file, and display the results in a RecyclerView with radio buttons. The user can select a beneficiary to see more information regarding the one selected by clicking the appropriate radio button and clicking the select button. The formatting is in progress, but it is functional at this time.

How It's Made:
Tech used: Kotlin, Android Studio

Improvements Planned:
1) Formatting of subtitle on the MainActivity to be improved, potentially will incorporate this to be the first row of the GridLayoutManager in the RecyclerView if I can figure out the logic.

2) Hardcoded text needs to be resolved to variables

3) Address is going off screen in BeneSelectedActivity, check all boundaries for all views because this is likely not the only one with the issue, even though it is the only one visible to me

4) Columns in RecyclerView are not aligned properly, will review margins and layout settings to see if this can be cleaned up

5) Remove unnecessary Logs that are left in the code from testing

6) Re-evaluate the declared variables, potential changes in location/scope, especially for ones that are duplicated or shared
 
7) Scope of methods needs to be reviewed, not optimized

8) Review TableLayout and LinearLayouts inside code to see if there is a better layout option available


Problems Resolved:
1) I broke Gradle, I was curious if it was needed since the task was to avoid all unnecessary extra software I attempted to delete the folders.
Resolution - Uninstalled Android Studio, reinstalled, deleted the remaining Gradle files and reconnected it to reinstall them

2) I have never interacted with a manifest file before, so I had to learn how those worked
Resolution - since everything I found online said that the manifest file was unavoidable I added the activities to the file

3) Learning how to create layouts manually and the different ones available
Resolution - not sure which ones are preferred yet but I have incorporated 3 into the program. LinearLayout, TableLayout, and GridLayoutManager for the RecyclerView. I still have a lot to learn on this topic.

4) Learning how to create a nested JSON array
Resolution - thankfully this ended up being easy! This was a quick learning exercise for me.

5) New directory to hold JSON file not showing in Android studio
Resolution - Realized, eventually, that it was in the wrong scope and I moved it to the Main folder, once there the program could see it

6) Issues loading data from JSON file for nested array
Resolution - realized I was trying to pull them the same was as the other JSON values and corrected it

7) Wanted to try Radio buttons + Button confirmation for selecting beneficiary but the logic for the select button wasn't firing
Resolution - realized I was missing a line of code in the onButtonClick method that was causing it to not fire correctly.

8) Had to learn how to pass the item object from the first activity to the second activity
Resolution - Made the item data class Serializable and passed it using intent
