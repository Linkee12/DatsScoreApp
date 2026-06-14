package com.example.dartsscore.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.clearText
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dartsscore.R
import com.example.dartsscore.ui.theme.DartsScoreTheme
import com.example.dartsscore.viewmodel.PlayersViewModel

@Composable
fun Players(
    viewModel: PlayersViewModel = viewModel()
) {
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
                        color = MaterialTheme.colorScheme.surfaceContainer.copy(alpha = 0.6f),
                        shape = MaterialTheme.shapes.large,
                    )
                    .padding(20.dp)

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
                        color = MaterialTheme.colorScheme.onSurface,

                        ),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        unfocusedIndicatorColor = MaterialTheme.colorScheme.outline,
                        focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words,
                        imeAction = ImeAction.Done
                    ),
                    onKeyboardAction = {
                        if (playerName.text.isNotBlank()) {
                            viewModel.addPlayer(playerName.text.toString())
                            playerName.clearText()
                        }
                    }
                )

            }
            LazyColumn {
                items(viewModel.playerNames) { name ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 6.dp)
                            .background(
                                color = MaterialTheme.colorScheme.surfaceContainer.copy(alpha = 0.6f),
                                shape = MaterialTheme.shapes.large,
                            )
                            .padding(5.dp), verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "✓ $name",
                            modifier = Modifier.padding(8.dp),
                            fontSize = 28.sp,
                            fontWeight = Bold,
                            color = MaterialTheme.colorScheme.primary,
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        IconButton(
                            onClick = { viewModel.deletePlayer(name) }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete player",
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier
                                    .padding(end = 8.dp)
                                    .size(36.dp)
                            )
                        }
                    }

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
    ) {
        Players()
    }
}
