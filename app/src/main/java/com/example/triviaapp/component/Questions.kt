package com.example.triviaapp.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.triviaapp.model.QuestionItem
import com.example.triviaapp.screens.QuestionsViewModel

@Composable
fun Questions(viewModel: QuestionsViewModel, modifier: Modifier = Modifier) {
    val questions = viewModel.data.value.data?.toMutableList()
    val questionIndex = remember { mutableIntStateOf(0) }

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            if (viewModel.data.value.loading == true) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else if (questions != null) {
                val totalQuestions = questions.size
                if (questionIndex.intValue < totalQuestions) {
                    val question = questions[questionIndex.intValue]
                    QuestionDisplay(
                        question = question,
                        questionIndex = questionIndex,
                        viewModel = viewModel
                    ) {
                        questionIndex.intValue += 1
                    }
                } else {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        ShowProgress(score = totalQuestions, outOf = totalQuestions)
                        Spacer(modifier = Modifier.height(24.dp))
                        Text(
                            text = "Congratulations!",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = "You've completed all $totalQuestions questions.",
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun QuestionDisplay(
    question: QuestionItem,
    questionIndex: MutableState<Int>,
    viewModel: QuestionsViewModel,
    onNextClick: (Int) -> Unit
) {
    val choicesState = remember(question) { question.choices.toMutableList() }
    val answerState = remember(question) { mutableStateOf<Int?>(null) }
    val correctAnswerState = remember(question) { mutableStateOf<Boolean?>(null) }

    val updateAnswer: (Int) -> Unit = remember(question) {
        { index ->
            answerState.value = index
            correctAnswerState.value = choicesState[index] == question.answer
        }
    }

    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
    val totalQuestions = viewModel.data.value.data?.size ?: 0

    Column(modifier = Modifier.fillMaxHeight()) {
        if (questionIndex.value > 0) ShowProgress(score = questionIndex.value, outOf = totalQuestions)
        
        QuestionTracker(
            counter = questionIndex.value + 1,
            outOf = totalQuestions
        )
        DrawDottedLine(pathEffect = pathEffect)

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = question.question,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            fontWeight = FontWeight.Bold,
            lineHeight = 24.sp,
        )

        Spacer(modifier = Modifier.weight(1f))

        choicesState.forEachIndexed { index, answerText ->
            val isSelected = (answerState.value == index)
            val color = if (isSelected) {
                if (correctAnswerState.value == true) Color.Green else Color.Red
            } else {
                MaterialTheme.colorScheme.primary
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
                    .heightIn(min = 50.dp)
                    .border(
                        width = 2.dp,
                        brush = Brush.linearGradient(
                            colors = listOf(
                                color.copy(alpha = 0.5f),
                                color.copy(alpha = 0.5f)
                            )
                        ),
                        shape = RoundedCornerShape(15.dp)
                    )
                    .clip(RoundedCornerShape(15.dp))
                    .clickable { updateAnswer(index) }
                    .background(Color.Transparent)
                    .padding(horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = isSelected,
                    onClick = { updateAnswer(index) },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = color,
                        unselectedColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
                    )
                )
                Text(
                    text = answerText,
                    color = color,
                    fontSize = 16.sp
                )
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { onNextClick(questionIndex.value) },
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally),
            enabled = correctAnswerState.value == true,
            shape = RoundedCornerShape(34.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                disabledContentColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f)
            )
        ) {
            Text(
                text = "Next",
                modifier = Modifier.padding(4.dp),
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 18.sp
            )
        }
        
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun QuestionTracker(counter: Int = 1, outOf: Int = 100) {
    val text = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        ) {
            append("Question $counter/")
        }
        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f),
                fontWeight = FontWeight.Light,
                fontSize = 14.sp
            )
        ) {
            append("$outOf")
        }
    }
    Text(text = text, modifier = Modifier.padding(vertical = 12.dp))
}

@Composable
fun DrawDottedLine(pathEffect: PathEffect) {
    val color = MaterialTheme.colorScheme.primary.copy(alpha = 0.4f)
    Canvas(modifier = Modifier.fillMaxWidth()) {
        drawLine(
            color = color,
            start = Offset(0f, 0f),
            end = Offset(size.width, 0f),
            pathEffect = pathEffect
        )
    }
}

@Preview
@Composable
fun ShowProgress(score: Int = 0, outOf: Int = 100) {

    val gradient = Brush.linearGradient(
        colors = listOf(
            MaterialTheme.colorScheme.secondary,
            MaterialTheme.colorScheme.tertiary
        )
    )

    val progressFactor = if (outOf > 0) score.toFloat() / outOf else 0f

    Box(
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth()
            .height(45.dp)
            .border(
                width = 4.dp,
                brush = gradient,
                shape = RoundedCornerShape(34.dp)
            )
            .clip(RoundedCornerShape(50))
            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)),
        contentAlignment = Alignment.CenterStart
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(progressFactor)
                .fillMaxHeight()
                .background(brush = gradient)
        )
        Text(
            text = "${(progressFactor * 100).toInt()}%",
            modifier = Modifier.padding(horizontal = 12.dp).align(alignment = Alignment.Center),
            color = MaterialTheme.colorScheme.surfaceBright,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 22.sp
        )
    }
}
