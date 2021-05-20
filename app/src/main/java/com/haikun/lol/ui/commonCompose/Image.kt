package com.haikun.lol.ui.commonCompose

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.coil.rememberCoilPainter
import com.haikun.lol.R

@Composable
fun NetImage(url:String,modifier: Modifier = Modifier,previewPlaceholder:Int= R.drawable.icon_head){

    Image(
        painter = rememberCoilPainter(
            url,
            previewPlaceholder = previewPlaceholder
        ),
        contentDescription = null,
        modifier=modifier
    )
}