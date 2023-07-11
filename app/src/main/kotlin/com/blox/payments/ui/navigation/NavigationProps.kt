package com.blox.payments.ui.navigation

import androidx.annotation.StringRes
import com.blox.payments.R

sealed class NavigationGraph(val route: String) {
    object Welcome : NavigationGraph("welcome")
    object Login : NavigationGraph("login")
    object Registration : NavigationGraph("registration")
    object Main : NavigationGraph("main")
}

sealed class LoginNavigationRoute(val route: String) {
    object SignIn : LoginNavigationRoute("sign_in")
    object ForgotPassword : LoginNavigationRoute("forgot_password")
}

sealed class RegistrationNavigationRoute(val route: String) {
    object RefCode : RegistrationNavigationRoute("ref_code")
    object LegalName : RegistrationNavigationRoute("legal_name")
    object BirthDate : RegistrationNavigationRoute("birth_date")
    object Country : RegistrationNavigationRoute("country")
    object PhoneNumber : RegistrationNavigationRoute("phone_number")
    object Email : RegistrationNavigationRoute("email")
    object Password : RegistrationNavigationRoute("password")
}

sealed class MainNavigationRoute(val route: String, @StringRes val resourceId: Int) {
    object Home : MainNavigationRoute("home", R.string.home_title)
    object Send : MainNavigationRoute("send", R.string.send_title)
    object Deposit : MainNavigationRoute("deposit", R.string.deposit_title)
    object Withdraw : MainNavigationRoute("withdraw", R.string.withdraw_title)
}

object NavigationArgument {
    const val TBD = "tbd"
}
