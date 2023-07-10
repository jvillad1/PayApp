package com.blox.payments.ui.utlis // ktlint-disable filename

import com.blox.uicomponents.model.Country
import java.util.Locale

enum class Countries(val countryName: String, val countryCode: String) {
    BRAZIL("Brazil", "+55"),
    COLOMBIA("Colombia", "+57"),
    MEXICO("Mexico", "+52"),
    PERU("Peru", "+51"),
    UNITED_STATES("United States", "+1");

    companion object {
        fun toCountriesList(): List<Country> = values().map { country ->
            Country(
                country.countryName,
                country.countryCode
            )
        }

        fun getCountryByName(countryName: String): Country {
            val enumValue = enumValueOf<Countries>(countryName.uppercase(Locale.ROOT))

            return Country(
                enumValue.countryName,
                enumValue.countryCode
            )
        }
    }
}
