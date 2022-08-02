package com.example.to_docompose.ui.screens.list

import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.to_docompose.data.models.ToDoTask
import com.example.to_docompose.ui.theme.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextOverflow
import com.example.to_docompose.data.models.Priority
import com.example.to_docompose.util.RequestState

@ExperimentalMaterialApi
@Composable
fun ListContent(
    tasks: RequestState<List<ToDoTask>>,
    navigateToTaskScreen: (taskId: Int) -> Unit
) {
//    * If Task is empty then show user it is empty
  if(tasks is RequestState.Sucess){
      if (tasks.data.isEmpty()) {
          EmptyContent()
      } else {
          DisplayTask(tasks = tasks.data, navigateToTaskScreen = navigateToTaskScreen)
      }
  }

}

@ExperimentalMaterialApi
@Composable
fun DisplayTask(
    tasks: List<ToDoTask>,
    navigateToTaskScreen: (taskId: Int) -> Unit
) {
    LazyColumn {
        items(
            items = tasks,
            key = { task ->
                task.id
            }

        ) { task ->
//        * This one does dynamically add the task
            TaskItem(
                toDoTask = task,
                navigateToTaskScreen = navigateToTaskScreen
            )

        }
    }
}

@ExperimentalMaterialApi
@Composable
fun TaskItem(
    toDoTask: ToDoTask,
    navigateToTaskScreen: (taskId: Int) -> Unit
) {
//    * THE WHOLE WHITE BOX
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colors.taskItemBackgroundColor,
        shape = RectangleShape,
        elevation = TASK_ITEM_ELEVATION,
        onClick = {
            navigateToTaskScreen(toDoTask.id)
        }
    ) {
        Column(
            modifier = Modifier
                    .padding(all = LARGE_PADDING)
                    .fillMaxWidth()
        ) {
            Row {
//                * TEXT TITLE
                Text(
                    text = toDoTask.title,
                    color = MaterialTheme.colors.taskItemTextColor,
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    modifier = Modifier.weight(8f)
                )
//                * INDICATOR HIGH MEDIUM OR LOW CIRCLE
                Box(
                    modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                    contentAlignment = Alignment.TopEnd
                ) {
                    Canvas(
                        modifier = Modifier
                                .size(PRIORITY_INDICATOR_SIZE)
                    ) {
                        drawCircle(color = toDoTask.priority.color)
                    }


                }

            }
//            * TEXT DESCRIPTION
            Text(
                text = toDoTask.description,
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colors.taskItemTextColor,
                style = MaterialTheme.typography.subtitle1,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

        }

    }

}

@ExperimentalMaterialApi
@Preview
@Composable
fun TaskItemPreview() {
    TaskItem(toDoTask = ToDoTask(
        0,
        "Title",
        "Remember to go out shopping",
        Priority.HIGH
    ), navigateToTaskScreen = {})
}

