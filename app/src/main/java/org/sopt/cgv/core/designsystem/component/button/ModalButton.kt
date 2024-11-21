package org.sopt.cgv.core.designsystem.component.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.cgv.core.common.norippleclick
import org.sopt.cgv.core.designsystem.theme.PrimaryRed400
import org.sopt.cgv.core.designsystem.theme.Typography
import org.sopt.cgv.core.designsystem.theme.White

data class ButtonStyle(
    val backgroundColor: Color,
    val border: BorderStroke?,
    val textColor: Color,
)

@Composable
fun getButtonStyle(buttonType: String): ButtonStyle {
    return remember(buttonType) {
        when (buttonType) {
            "Choice" -> ButtonStyle(
                backgroundColor = PrimaryRed400,
                border = null,
                textColor = White
            )
            "Back" -> ButtonStyle(
                backgroundColor = White,
                border = BorderStroke(1.dp, PrimaryRed400),
                textColor = PrimaryRed400
            )
            else -> ButtonStyle(
                backgroundColor = PrimaryRed400,
                border = null,
                textColor = White
            )
        }
    }

}

@Composable
fun ModalButton(
    modifier: Modifier = Modifier,
    buttonType: String,
    initialActivation: Boolean,
    content: String,
    length: String,
){

    var isActivated by remember { mutableStateOf(initialActivation) }
    val buttonStyle = getButtonStyle(buttonType)

    val (buttonWidth, buttonHeight) = remember(length) {
        when (length) {
            "long" -> Pair(324.dp, 54.dp)
            "half" -> Pair(156.dp, 54.dp)
            else -> Pair(324.dp, 54.dp)
        }
    }

    Box(
        modifier = Modifier
            .size(width = buttonWidth, height = buttonHeight)
            .clip(RoundedCornerShape(12.dp))
            .background(buttonStyle.backgroundColor)
            .then(
                if (buttonStyle.border != null)
                    Modifier.border(buttonStyle.border, RoundedCornerShape(12.dp))
                else
                    Modifier
            )
            .norippleclick() {
                /* Todo - behavior after cliking the button  */
            }
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            Text(
                text = content,
                color = buttonStyle.textColor,
                style = Typography.head6_b_17
            )

        }
    }
}



@Preview
@Composable
fun ButtonPreview(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ModalButton(
            modifier = Modifier,
            buttonType = "Choice",
            initialActivation = true,
            content = "좌석선택",
            length = "long"
        )
        ModalButton(
            modifier = Modifier,
            buttonType = "Back",
            initialActivation = false,
            content = "뒤로가기",
            length = "half"
        )
        ModalButton(
            modifier = Modifier,
            buttonType = "Choice",
            initialActivation = true,
            content = "확인",
            length = "half"
        )
    }
}