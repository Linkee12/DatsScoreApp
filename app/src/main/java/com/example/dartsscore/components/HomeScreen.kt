package com.example.dartsscore.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dartsscore.services.dataService


data class ButtonsObject(
    val value: Int,
    val bg: Int,
    val color: Int,
)

@Preview(showBackground = true)
@Composable
fun HomeScreen(onNavigate: (String) -> Unit = {}) {
    val ds = remember { dataService() }
    val buttons = listOf(
        ButtonsObject(301, 0xFF0E2744.toInt(), 0xFF0090FF.toInt()),
        ButtonsObject(501, 0xFF2E1C0B.toInt(), 0xFFFF801F.toInt()),
        ButtonsObject(701, 0xFF174933.toInt(), 0xFF33B074.toInt())
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        buttons.forEach { button ->
            Button(
                onClick = {
                    onNavigate("player")
                    ds.gameMode = button.value
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(button.bg)
                )
            ) {
                Text(
                    button.value.toString(),
                    fontSize = 150.sp,
                    fontWeight = FontWeight.Black,
                    color = Color(button.color)
                )
            }
        }
    }


}
