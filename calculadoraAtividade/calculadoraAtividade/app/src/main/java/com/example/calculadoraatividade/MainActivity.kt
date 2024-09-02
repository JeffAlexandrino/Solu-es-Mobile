import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() { //aaa
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Calculator()
        }
    }
}

@Composable
fun Calculator() {
    var display by remember { mutableStateOf("0") }
    var lastValue by remember { mutableStateOf(0.0) }
    var operation by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = display,
            fontSize = 48.sp,
            modifier = Modifier.align(Alignment.End)
        )

        val buttons = listOf(
            listOf("7", "8", "9", "/"),
            listOf("4", "5", "6", "*"),
            listOf("1", "2", "3", "-"),
            listOf("0", "C", "=", "+")
        )

        buttons.forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                row.forEach { symbol ->
                    Button(
                        onClick = {
                            when (symbol) {
                                "C" -> display = "0"
                                "=" -> {
                                    val result = when (operation) {
                                        "+" -> lastValue + display.toDouble()
                                        "-" -> lastValue - display.toDouble()
                                        "*" -> lastValue * display.toDouble()
                                        "/" -> lastValue / display.toDouble()
                                        else -> display.toDouble()
                                    }
                                    display = result.toString()
                                    operation = ""
                                }
                                "+", "-", "*", "/" -> {
                                    lastValue = display.toDouble()
                                    operation = symbol
                                    display = "0"
                                }
                                else -> {
                                    display = if (display == "0") symbol else display + symbol
                                }
                            }
                        },
                        modifier = Modifier.size(64.dp)
                    ) {
                        Text(text = symbol, fontSize = 24.sp)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorPreview() {
    Calculator()
}
