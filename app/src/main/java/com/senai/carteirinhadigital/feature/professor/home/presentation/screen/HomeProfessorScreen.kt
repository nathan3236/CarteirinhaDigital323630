package com.senai.carteirinhadigital.feature.professor.home.presentation.screen


import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.senai.carteirinhadigital.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.senai.carteirinhadigital.feature.login.domain.model.UsuarioLogado

private val FundoProfessor = Color(0xFFF9FAEF)
private val VerdeProfessor = Color(0xFF4C662B)

@Composable
fun HomeProfessorScreen(
    usuarioLogado: UsuarioLogado,
    onTurmasClick: () -> Unit,
    onUnidadesCurricularesClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .paint(painterResource(R.drawable.fundo_flores), contentScale = ContentScale.Crop)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        Text(
            text = "Olá, professor!",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = VerdeProfessor
        )

        Text(
            text = "Bem-vindo à sua área acadêmica.",
            fontSize = 16.sp,
            color = Color(0xFF44483D)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Card(
            onClick = onTurmasClick,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFEEEFE3)
            )
        ) {
            Row(
                modifier = Modifier.padding(20.dp),
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Text("▦", fontSize = 28.sp, color = VerdeProfessor)

                Column {
                    Text(
                        text = "Minhas Turmas",
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1A1C16)
                    )
                    Text(
                        text = "Consulte suas turmas cadastradas.",
                        fontSize = 14.sp,
                        color = Color(0xFF44483D)
                    )
                }
            }
        }

        Card(
            onClick = onUnidadesCurricularesClick,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFEEEFE3)
            )
        ) {
            Row(
                modifier = Modifier.padding(20.dp),
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Text("▤", fontSize = 28.sp, color = VerdeProfessor)

                Column {
                    Text(
                        text = "Unidades Curriculares",
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1A1C16)
                    )
                    Text(
                        text = "Consulte as unidades do curso.",
                        fontSize = 14.sp,
                        color = Color(0xFF44483D)
                    )
                }
            }
        }
    }
}
