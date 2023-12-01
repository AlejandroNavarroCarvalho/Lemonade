package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                LemonadeApp()
        }
    }
}

@Composable
fun LemonadeApp() {
    var contador by remember { mutableStateOf(1) }
    var contadorExprimir by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (contador) {
            1 -> {
                LemonTextAndImage(
                    frase = R.string.select,
                    imagen = R.drawable.lemon_tree,
                    descripcionContenido = R.string.tree,
                    clickEnImagen = {
                        contador++
                        contadorExprimir = (2..4).random()
                    }
                )
            }
            2 -> {
                LemonTextAndImage(
                    frase = R.string.squeeze,
                    imagen = R.drawable.lemon_squeeze,
                    descripcionContenido = R.string.lemon,
                    clickEnImagen = {
                        contadorExprimir--
                        if (contadorExprimir == 0) {
                            contador++
                        }
                    }
                )
            }
            3 -> {
                LemonTextAndImage(
                    frase = R.string.drink,
                    imagen = R.drawable.lemon_drink,
                    descripcionContenido = R.string.glass,
                    clickEnImagen = {
                        contador++
                    }
                )
            }
            4 -> {
                LemonTextAndImage(
                    frase = R.string.start,
                    imagen = R.drawable.lemon_restart,
                    descripcionContenido = R.string.empty,
                    clickEnImagen = {
                        contador = 1
                    }
                )
            }
        }
    }
}

@Composable
fun LemonTextAndImage(
    frase: Int,
    imagen: Int,
    descripcionContenido: Int,
    clickEnImagen: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = Modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(modifier = modifier.height(80.dp)
        ) {
            Text(
                text = "Lemonade",
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(249, 228, 75))
                    .padding(top = 20.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        ) {
            Button(
                onClick = clickEnImagen,
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(Color(195, 236, 210))
            ) {
                Image(
                    painter = painterResource(imagen),
                    contentDescription = stringResource(descripcionContenido),
                    modifier = Modifier
                        .size(120.dp)
                        .background(Color(195, 236, 210))
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = stringResource(frase))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LemonPreview() {
        LemonadeApp()
}