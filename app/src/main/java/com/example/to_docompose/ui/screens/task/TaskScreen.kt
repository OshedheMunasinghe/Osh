package com.example.to_docompose.ui.screens.task

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.example.to_docompose.data.models.Priority
import com.example.to_docompose.data.models.ToDoTask
import com.example.to_docompose.ui.viewmodels.SharedViewModel
import com.example.to_docompose.util.Action

@Composable
fun TaskScreen(
    selectedTask: ToDoTask?,
    navigateToListScreen: (Action) -> Unit,
    sharedViewModel: SharedViewModel
) {
//    for observing, this will save when user type in task
    val title: String by sharedViewModel.title
    val description: String by sharedViewModel.description
    val priority: Priority by sharedViewModel.priority

    Scaffold(
        topBar = {
            TaskAppBar(
                navigateToListScreen = navigateToListScreen,
                selectedTask = selectedTask,
            )
        },
        content = {
            TaskContent(
                title = title,
                onTitleChange = { sharedViewModel.title.value = it },
                description = description,
                onDescriptionChange = { sharedViewModel.description.value = it },
                priority = priority,
                onPrioritySelected = { sharedViewModel.priority.value = it }
            )
        }
    )


}