package com.example.dartsscore.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dartsscore.R
import com.example.dartsscore.ui.theme.DartsScoreTheme


@Composable
fun Players() {
    val playerName = rememberTextFieldState(initialText = "")
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceDim),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Background logo",
            modifier = Modifier
                .alpha(0.15f)
                .fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 24.dp, top = 48.dp, end = 24.dp, bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colorScheme.surfaceContainer,
                        shape = MaterialTheme.shapes.large
                    )
                    .padding(20.dp),

                ) {
                Text(
                    text = "Player name",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = Bold,
                    modifier = Modifier.padding(bottom = 8.dp),
                    textAlign = TextAlign.Center
                )

                OutlinedTextField(
                    state = playerName,
                    textStyle = TextStyle(
                        fontSize = 24.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    ),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        unfocusedIndicatorColor = MaterialTheme.colorScheme.outline,
                        focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                if (playerName.text.isNotEmpty()) {
                    Text(
                        text = "✓  ${playerName.text}",
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = Bold,
                        modifier = Modifier.padding(top = 12.dp)
                    )
                }
                Button(
                    onClick = {
                        Log.d("TAG", "Button clicked")
                    }, shape = RoundedCornerShape(10.dp), modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp)

                ) {

                    Text("Add player", fontWeight = Bold, fontSize = 20.sp)
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PlayersPreview() {
    DartsScoreTheme(
        darkTheme = true,
        dynamicColor = false
    ) {
        Players()
    }
}
