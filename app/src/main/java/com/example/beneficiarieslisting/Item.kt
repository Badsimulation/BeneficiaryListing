/* This is a data set that relates to the information being pulled from the JSON file*/

package com.example.beneficiarieslisting

import java.io.Serializable

data class Item(
    val lastName: String,
    val firstName: String,
    val designationCode: String,
    val beneType: String,
    val socialSecurityNumber: String,
    val dateOfBirth: String,
    val middleName: String,
    val phoneNumber: String,
    val firstLineMailing: String,
    val scndLineMailing: String,
    val city: String,
    val zipCode: String,
    val stateCode: String,
    val country: String
) : Serializable
