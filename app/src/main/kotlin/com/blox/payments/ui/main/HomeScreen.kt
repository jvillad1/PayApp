package com.blox.payments.ui.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.blox.payments.R
import com.blox.payments.domain.home.model.Transaction
import com.blox.uicomponents.commons.BodyLargeText
import com.blox.uicomponents.commons.ScreenTitle

@Suppress("UnusedPrivateMember")
@Composable
fun HomeScreen(
    navController: NavHostController
) {
    val transactions = getMockedTransactions()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        BodyLargeText(text = stringResource(id = R.string.home_balance_label), paddingTop = 40.dp)
        ScreenTitle(title = stringResource(id = R.string.home_balance_value, "1.422,60"), paddingTop = 10.dp)
        LazyColumn(
            modifier = Modifier.padding(vertical = 20.dp)
        ) {
            itemsIndexed(transactions) { index, transaction ->
                TransactionItemView(transaction)

                if (index < transactions.size - 1) {
                    Divider(color = Color.LightGray)
                }
            }
        }
    }
}

@Composable
fun TransactionItemView(transaction: Transaction) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = transaction.transactionType,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = transaction.amount,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.bodyLarge
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = transaction.creationTime,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = transaction.balance,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

private fun getMockedTransactions() = listOf(
    Transaction(
        id = 1,
        transactionType = "Deposit",
        amount = "US$ 10,00",
        creationTime = "may. 21, 2023 22:50:36",
        balance = "US$ 1.422,60"
    ),
    Transaction(
        id = 2,
        transactionType = "Deposit",
        amount = "US$ 10,00",
        creationTime = "may. 21, 2023 22:45:36",
        balance = "US$ 1.412,60"
    ),
    Transaction(
        id = 3,
        transactionType = "Deposit",
        amount = "US$ 2,60",
        creationTime = "may. 21, 2023 22:40:36",
        balance = "US$ 1.402,60"
    ),
    Transaction(
        id = 4,
        transactionType = "Deposit",
        amount = "US$ 2,60",
        creationTime = "may. 21, 2023 22:35:36",
        balance = "US$ 1.400,60"
    ),
    Transaction(
        id = 5,
        transactionType = "Deposit",
        amount = "US$ 100,00",
        creationTime = "may. 21, 2023 22:30:36",
        balance = "US$ 1.300,00"
    ),
    Transaction(
        id = 6,
        transactionType = "Withdraw",
        amount = "US$ 100,00",
        creationTime = "may. 21, 2023 22:25:36",
        balance = "US$ 1.400,00"
    ),
    Transaction(
        id = 7,
        transactionType = "Deposit",
        amount = "US$ 100,00",
        creationTime = "may. 21, 2023 22:25:36",
        balance = "US$ 1.400,00"
    ),
    Transaction(
        id = 6,
        transactionType = "Withdraw",
        amount = "US$ 100,00",
        creationTime = "may. 21, 2023 22:25:36",
        balance = "US$ 1.400,00"
    ),
    Transaction(
        id = 6,
        transactionType = "Withdraw",
        amount = "US$ 100,00",
        creationTime = "may. 21, 2023 22:25:36",
        balance = "US$ 1.400,00"
    ),
    Transaction(
        id = 6,
        transactionType = "Withdraw",
        amount = "US$ 100,00",
        creationTime = "may. 21, 2023 22:25:36",
        balance = "US$ 1.400,00"
    ),
    Transaction(
        id = 6,
        transactionType = "Withdraw",
        amount = "US$ 100,00",
        creationTime = "may. 21, 2023 22:25:36",
        balance = "US$ 1.400,00"
    )
)
