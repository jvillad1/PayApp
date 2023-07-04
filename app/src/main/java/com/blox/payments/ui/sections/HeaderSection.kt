package com.blox.payments.ui.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.blox.payments.R
import com.blox.payments.ui.theme.Green40
import com.blox.payments.ui.utlis.forwardingPainter

@Composable
fun HeaderSection(
    title: String,
    iconUrl: String?,
    modifier: Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp)
            .shadow(elevation = 16.dp)
            .defaultMinSize(minHeight = 88.dp),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                modifier = Modifier
                    .padding(end = 16.dp)
                    .weight(1f),
                fontSize = 20.sp,
                color = Color.Black.copy(alpha = 0.9f),
                fontWeight = FontWeight.W600
//            fontFamily = FontFamily(Font(R.font.proxima nova)),
            )
            AsyncImage(
                model = iconUrl,
                contentDescription = "Header icon url $iconUrl",
                modifier = Modifier.size(56.dp),
                placeholder = forwardingPainter(
                    painter = painterResource(R.drawable.ic_bag),
                    colorFilter = ColorFilter.tint(Green40)
                ),
                error = forwardingPainter(
                    painter = painterResource(R.drawable.ic_bag),
                    colorFilter = ColorFilter.tint(Green40)
                ),
                contentScale = ContentScale.FillBounds
            )
        }
    }
}
