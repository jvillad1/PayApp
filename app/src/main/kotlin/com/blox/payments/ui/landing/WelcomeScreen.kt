package com.blox.payments.ui.landing

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.blox.payments.BuildConfig
import com.blox.payments.R
import com.blox.payments.ui.NavigationRoute
import timber.log.Timber

@Composable
fun WelcomeScreen(
    onClick: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        if (BuildConfig.FLAVOR == "whitelabel") {
            Timber.d("Show Text for Logo")
            Text(
                text = "Your Logo here",
                color = MaterialTheme.colorScheme.onPrimary
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.bg_logo),
                contentDescription = stringResource(id = R.string.logo_accessibility),
                modifier = Modifier.padding(start = 20.dp, top = 60.dp)
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(start = 40.dp, end = 40.dp, bottom = 20.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    onClick(NavigationRoute.LOGIN)
                },
                modifier = Modifier.weight(1f).padding(end = 12.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.welcome_sign_in_cta),
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
            Button(
                onClick = {
                    onClick(NavigationRoute.REGISTRATION)
                },
                modifier = Modifier.weight(1f).padding(start = 12.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.welcome_register_cta),
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}
