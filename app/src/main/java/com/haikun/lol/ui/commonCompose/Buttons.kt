package com.haikun.lol.ui.commonCompose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun MainButton(onClick: () -> Unit, text: String, modifier: Modifier = Modifier) {
    Button(onClick = {
        onClick()
    }, shape = RoundedCornerShape(12.dp), modifier = modifier) {
        Text(modifier = Modifier.padding(horizontal = 32.dp, vertical = 5.dp), text = text)
    }
}

@Composable
fun FillButton(onClick: () -> Unit, text: String, modifier: Modifier = Modifier) {
    Button(onClick = {
        onClick()
    }, shape = RoundedCornerShape(12.dp), modifier = modifier) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            textAlign = TextAlign.Center,
            text = text
        )
    }
}