package com.blox.payments.ui.myscreen.result

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.blox.payments.ui.sections.FooterSection
import com.blox.payments.ui.sections.HeaderSection
import com.blox.payments.ui.sections.body.BodySection

@Composable
fun ResultScreen(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val resultViewModel = hiltViewModel<ResultViewModel>()
    val uiState = resultViewModel.uiState

    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
    ) {
        val (type, header, body, footer) = createRefs()

        Text(
            text = uiState.myScreenModel?.type?.name.orEmpty(),
            modifier = modifier.constrainAs(type) {
                top.linkTo(parent.top, margin = 20.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            color = uiState.myScreenModel?.type?.toColor() ?: Color.Black.copy(alpha = 0.9f),
            fontWeight = FontWeight.W600
        )

        HeaderSection(
            title = uiState.myScreenModel?.headerModel?.title ?: "Default title",
            iconUrl = uiState.myScreenModel?.headerModel?.iconUrl,
            modifier = modifier.constrainAs(header) {
                top.linkTo(type.bottom, margin = 16.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        BodySection(
            body = uiState.myScreenModel?.body,
            modifier = modifier.constrainAs(body) {
                top.linkTo(header.bottom, margin = 16.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(footer.top, margin = 16.dp)
                height = Dimension.fillToConstraints
            }
        )

        FooterSection(
            buttonModelList = uiState.myScreenModel?.footerModel?.buttonModelList,
            modifier = modifier.constrainAs(footer) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom, margin = 20.dp)
            },
            onClick = { onClick.invoke() }
        )
    }
}
