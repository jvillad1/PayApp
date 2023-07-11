package com.blox.payments.ui

object NavigationGraph {
    const val AUTH = "auth"
    const val MAIN = "main"
}

object AuthNavigationRoute {
    const val WELCOME = "welcome"
    const val LOGIN = "login"
    const val FORGOT_PASSWORD = "forgot_password"
    const val REGISTRATION_REF_CODE = "registration_ref_code"
    const val REGISTRATION_LEGAL_NAME = "registration"
    const val REGISTRATION_BIRTH_DATE = "registration_birth_date"
    const val REGISTRATION_COUNTRY = "registration_country"
    const val REGISTRATION_PHONE_NUMBER = "registration_phone_number"
    const val REGISTRATION_EMAIL = "registration_email"
    const val REGISTRATION_PASSWORD = "registration_password"
}

object MainNavigationRoute {
    const val HOME = "home"
}

object NavigationArgument {
    const val TBD = "tbd"
}
