package com.haikun.lol.ui.commonCompose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SubTitleTextButton(onClick: () -> Unit,text:String,value:String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement=Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        Text(text = text,style = MaterialTheme.typography.subtitle1)
        Text(text = value,style = MaterialTheme.typography.subtitle2)
    }
}