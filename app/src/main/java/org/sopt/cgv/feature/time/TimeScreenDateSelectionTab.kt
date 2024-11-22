package org.sopt.cgv.feature.time

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.cgv.core.designsystem.theme.Black
import org.sopt.cgv.core.designsystem.theme.CGVTheme
import org.sopt.cgv.core.designsystem.theme.Gray700
import org.sopt.cgv.core.designsystem.theme.PrimaryRed400
import org.sopt.cgv.core.designsystem.theme.White

@Composable
fun TimeScreenDateSelectionTab() {
    val dateList: List<Date> = listOf(
        Date("11.28", "목"),
        Date("11.29", "금"),
        Date("11.30", "토"),
        Date("12.1", "일"),
        Date("12.2", "월"),
        Date("12.3", "화"),
        Date("12.4", "수"),
        Date("12.5", "목"),
        Date("12.6", "금"),
        Date("12.7", "토")
    )
    val selectedDate = remember { mutableStateOf("11.28") }
    val selectedDay = remember { mutableStateOf("목") }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "2024.${selectedDate.value} (${selectedDay.value})",
            style = CGVTheme.typography.body3_m_14,
            color = Black
        )

        Spacer(modifier = Modifier.height(14.dp))

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(items = dateList) { date ->
                ClickableDate(
                    date = date,
                    Modifier.fillParentMaxWidth(0.13f),
                    selectedDate = selectedDate,
                    selectedDay = selectedDay,
                    dateList = dateList
                )
            }
        }
    }
}

@Composable
fun ClickableDate(
    date: Date,
    modifier: Modifier = Modifier,
    selectedDate: MutableState<String>,
    selectedDay: MutableState<String>,
    dateList: List<Date>
) {
    Column(
        modifier = modifier
            .wrapContentHeight()
            .background(White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clickable {
                    selectedDate.value = date.date
                    selectedDay.value = date.day
                }
                .background(
                    shape = RoundedCornerShape(6.dp),
                    color = if (date.date == selectedDate.value) PrimaryRed400 else White
                )
        ) {
            Text(
                text = date.date.slice(3 until date.date.length),
                modifier = Modifier.align(Alignment.Center),
                style = CGVTheme.typography.head5_b_16,
                color = if (date.date == selectedDate.value) White else Black
            )
        }

        Text(
            text = when (dateList.indexOf(date)) {
                0 -> "오늘"
                1 -> "내일"
                else -> date.day
            },
            style = if (date.date == selectedDate.value) CGVTheme.typography.head1_b_12 else CGVTheme.typography.body1_m_12,
            color = if (date.date == selectedDate.value) PrimaryRed400 else Gray700
        )
    }
}

@Preview
@Composable
private fun ClickableDatePreview() {
    CGVTheme {
        ClickableDate(
            date = Date("11.28", "목"),
            selectedDate = remember { mutableStateOf("11.28") },
            selectedDay = remember { mutableStateOf("목") },
            dateList = listOf(
                Date("11.28", "목"),
                Date("11.29", "금"),
                Date("11.30", "토"),
                Date("12.1", "일"),
                Date("12.2", "월"),
                Date("12.3", "화"),
                Date("12.4", "수"),
                Date("12.5", "목"),
                Date("12.6", "금"),
                Date("12.7", "토")
            )
        )
    }
}

@Preview
@Composable
private fun TimeScreenDateSelectionTabPreview() {
    CGVTheme {
        TimeScreenDateSelectionTab()
    }
}

data class Date(
    val date: String,
    val day: String
)