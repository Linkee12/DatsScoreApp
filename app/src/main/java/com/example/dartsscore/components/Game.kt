package com.example.dartsscore.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.clearText
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dartsscore.viewmodel.PlayersViewModel

@Composable
fun Game(
    viewModel: PlayersViewModel
) {
    var currentIdx by remember { mutableIntStateOf(0) }
    val score = rememberTextFieldState(initialText = "")
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceDim)
            .padding(16.dp),

        ) {
        Column() {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colorScheme.surfaceContainer.copy(alpha = 0.6f),
                        shape = MaterialTheme.shapes.large,
                    )
                    .wrapContentHeight()
                    .padding(15.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                if (viewModel.players.isNotEmpty()) {
                    Text(
                        text = viewModel.players[currentIdx].name,
                        fontSize = 70.sp,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = Bold,
                    )
                    Text(
                        text = "${viewModel.players[currentIdx].score.value}",
                        fontSize = 130.sp,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = Bold,
                        textAlign = TextAlign.Center,
                    )
                    Row {

                        Text(
                            text = "Avg: ",
                            fontSize = 30.sp,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = Bold,
                        )
                        Text(
                            text = "${viewModel.players[currentIdx].avg.value}",
                            fontSize = 30.sp,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = Bold,
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(44.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(
                            onClick = {},
                            modifier = Modifier.weight(0.7f),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                                contentColor = MaterialTheme.colorScheme.primary
                            ),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Refresh,
                                contentDescription = "Play game",
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(40.dp)
                            )
                        }
                        OutlinedTextField(
                            state = score,
                            textStyle = TextStyle(
                                color = MaterialTheme.colorScheme.onSurface,
                                fontSize = 40.sp,
                                textAlign = TextAlign.Center,
                            ), contentPadding = PaddingValues(start = 5.dp),
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = Color.Transparent,
                                focusedContainerColor = Color.Transparent,
                                unfocusedIndicatorColor = MaterialTheme.colorScheme.outline,
                                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                            ),
                            modifier = Modifier.weight(1f),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.NumberPassword,
                                autoCorrectEnabled = false,
                                imeAction = ImeAction.Done
                            ),
                            onKeyboardAction = {
                                if (score.text.isNotBlank()) {
                                    viewModel.hit(currentIdx, score.text.toString().toInt())
                                    score.clearText()
                                }
                                currentIdx = (currentIdx + 1) % viewModel.players.size

                            }
                        )
                        Button(
                            onClick = {
                                currentIdx = if (currentIdx - 1 < 0) {
                                    viewModel.players.size
                                } else {
                                    currentIdx - 1
                                }
                                viewModel.back(currentIdx)
                            },
                            modifier = Modifier.weight(0.7f),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                                contentColor = MaterialTheme.colorScheme.primary
                            ),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Play game",
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(40.dp)
                            )
                        }
                    }
                }
            }
            LazyColumn {
                items(viewModel.players) { player ->
                    Row(
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .fillMaxWidth()
                            .background(
                                color = MaterialTheme.colorScheme.surfaceContainer.copy(alpha = 0.6f),
                                shape = MaterialTheme.shapes.large,
                            )
                            .wrapContentHeight()
                            .padding(15.dp), verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = player.name,
                            fontSize = 40.sp,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = Bold,
                        )
                        Column() {
                            Text(
                                text = "${player.score.value}",
                                fontSize = 50.sp,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = Bold,
                                textAlign = TextAlign.Center,
                            )
                            Row {
                                Text(
                                    text = "Avg: ",
                                    fontSize = 20.sp,
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = Bold,
                                )
                                Text(
                                    text = "${player.avg.value}",
                                    fontSize = 20.sp,
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = Bold,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
