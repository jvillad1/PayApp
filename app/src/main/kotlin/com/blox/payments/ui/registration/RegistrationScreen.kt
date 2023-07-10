package com.blox.payments.ui.registration

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.blox.payments.R
import com.blox.uicomponents.commons.BodyLargeText
import com.blox.uicomponents.commons.FullWidthButton
import com.blox.uicomponents.commons.FullWidthTextField
import com.blox.uicomponents.commons.ScreenTitle
import java.util.Calendar
import java.util.Date

@Composable
fun RegistrationScreen() {
    val viewModel = hiltViewModel<RegistrationViewModel>()
    val uiState = viewModel.uiState

    LaunchedEffect(uiState) {
//        launch {
//            when {
//                uiState.firstNameError -> onClick(uiState.myScreenIdentifier)
//            }
//
//            fetchViewModel.navigationHandled()
//        }
    }

    when {
        !uiState.legalNameCompleted -> LegalNameScreen(viewModel)
        uiState.legalNameCompleted -> BirthDateScreen(viewModel)
    }
}

@Composable
fun LegalNameScreen(viewModel: RegistrationViewModel) {
    Column {
        ScreenTitle(
            title = stringResource(id = R.string.registration_legal_name_title),
            paddingTop = 40.dp
        )
        BodyLargeText(text = stringResource(id = R.string.registration_legal_name_description))
        FullWidthTextField(
            value = viewModel.firstName,
            hint = stringResource(id = R.string.registration_legal_name_first_name_label),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        ) {
            viewModel.updateFirstName(it)
        }
        FullWidthTextField(
            value = viewModel.lastName,
            hint = stringResource(id = R.string.registration_legal_name_last_name_label),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            )
        ) {
            viewModel.updateLastName(it)
        }
        FullWidthButton(text = stringResource(id = R.string.registration_legal_name_continue_cta)) {
            viewModel.validateLegalName()
        }
    }
}

@Composable
fun BirthDateScreen(viewModel: RegistrationViewModel) {
    Column {
        ScreenTitle(
            title = stringResource(id = R.string.registration_birth_date_title),
            paddingTop = 40.dp
        )
        BodyLargeText(text = stringResource(id = R.string.registration_birth_date_description))

        // Fetching the Local Context
        val mContext = LocalContext.current

        // Declaring integer values
        // for year, month and day
        val mYear: Int
        val mMonth: Int
        val mDay: Int

        // Initializing a Calendar
        val mCalendar = Calendar.getInstance()

        // Fetching current year, month and day
        mYear = mCalendar.get(Calendar.YEAR)
        mMonth = mCalendar.get(Calendar.MONTH)
        mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

        mCalendar.time = Date()

        // Declaring a string value to
        // store date in string format
        val mDate = remember { mutableStateOf("") }

        // Declaring DatePickerDialog and setting
        // initial values as current values (present year, month and day)
        val mDatePickerDialog = DatePickerDialog(
            mContext,
            { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
                mDate.value = "$mDayOfMonth/${mMonth + 1}/$mYear"
            },
            mYear,
            mMonth,
            mDay
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Creating a button that on
            // click displays/shows the DatePickerDialog
            Button(
                onClick = {
                    mDatePickerDialog.show()
                }
            ) {
                Text(text = "Open Date Picker", color = Color.White)
            }

            // Adding a space of 100dp height
            Spacer(modifier = Modifier.size(100.dp))

            // Displaying the mDate value in the Text
            Text(
                text = "Selected Date: ${mDate.value}",
                fontSize = 30.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}
