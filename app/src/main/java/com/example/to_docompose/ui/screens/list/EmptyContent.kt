package com.example.to_docompose.ui.screens.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.to_docompose.R
import com.example.to_docompose.ui.theme.MediumGray

/*
* This will tell user when there is no item in the list
*
* */

@Composable
fun EmptyContent() {
    Column(
        modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(120.dp),
            painter = painterResource(id = R.drawable.ic_sad_face),
            contentDescription = stringResource(id = R.string.icon_sad_face),
            tint = MediumGray
        )
        Text(
            text = stringResource(id = R.string.empty_content),
            color = MediumGray,
            fontSize = MaterialTheme.typography.h6.fontSize
        )
    }

}

@Preview
@Composable
fun EmptyContentPreview() {
    EmptyContent()
}