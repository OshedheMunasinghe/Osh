package com.example.to_docompose.components


import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.to_docompose.R
import com.example.to_docompose.data.models.Priority
import com.example.to_docompose.ui.theme.PRIORITY_DROP_DOWN_HEIGHT
import com.example.to_docompose.ui.theme.PRIORITY_INDICATOR_SIZE

//* make a dropDown and filter the piority
@Composable
fun PriorityDropDown(
    priority: Priority,
    onPrioritySelected: (Priority) -> Unit
) {
//    to expand the dropdown
    var expanded by remember {
        mutableStateOf(false)
    }
    val angel: Float by animateFloatAsState(targetValue = if (expanded) 180f else 0f)

    Row(
        modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.background)
                .height(PRIORITY_DROP_DOWN_HEIGHT)
                .clickable { expanded = true }
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled),
                    shape = MaterialTheme.shapes.small
                ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Canvas(
            modifier = Modifier
                    .size(PRIORITY_INDICATOR_SIZE)
                    .weight(1f)
        ) {
            drawCircle(color = priority.color)
        }
        Text(
            text = priority.name,
            style = MaterialTheme.typography.subtitle2,
            modifier = Modifier.weight(8f)
        )
        IconButton(
            onClick = { expanded = true },
            modifier = Modifier
                    .alpha(ContentAlpha.medium)
                    .rotate(degrees = angel)
                    .weight(1.5f)
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = stringResource(id = R.string.drop_down_arrow_icon)
            )

        }
//        When user has pressed the expand so it has to "inform" to drodownmenu parameter expanded that it is "while true"
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth(fraction = 0.94f)
        ) {
//           * LOW ITEM IN DROPDOWN
            DropdownMenuItem(
                onClick = {
                    expanded = false
                    onPrioritySelected(Priority.LOW)
                }) {
                PriorityItem(priority = Priority.LOW)
            }
            //           * MEDIUM ITEM IN DROPDOWN
            DropdownMenuItem(
                onClick = {
                    expanded = false
                    onPrioritySelected(Priority.MEDIUM)
                }) {
                PriorityItem(priority = Priority.MEDIUM)
            }
            //           * HIGH ITEM IN DROPDOWN
            DropdownMenuItem(
                onClick = {
                    expanded = false
                    onPrioritySelected(Priority.HIGH)
                }) {
                PriorityItem(priority = Priority.HIGH)
            }
        }
    }//end RowScopt
}

@Preview
@Composable
fun PriorityDropDownPreview() {
    PriorityDropDown(priority = Priority.LOW, onPrioritySelected = {})
}