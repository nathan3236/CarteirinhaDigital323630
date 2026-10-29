package com.senai.carteirinhadigital.feature.professor.turmas.presentation.screen

import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.senai.carteirinhadigital.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.senai.carteirinhadigital.feature.professor.data.remote.dto.TurmaProfessorDto
import com.senai.carteirinhadigital.feature.professor.domain.repository.ProfessorRepository

private val Fundo = Color(0xFFF9FAEF)
private val FundoCard = Color(0xFFEEEFE3)

@Composable
fun MinhasTurmasScreen(
    repository: ProfessorRepository,
    onVoltar: () -> Unit,
    modifier: Modifier = Modifier
) {
    var turmas by remember { mutableStateOf<List<TurmaProfessorDto>>(emptyList()) }
    var carregando by remember { mutableStateOf(true) }
    var erro by remember { mutableStateOf<String?>(null) }
    var recarga by remember { mutableStateOf(0) }

    LaunchedEffect(recarga) {
        carregando = true
        erro = null
        repository.listarTurmas().onSuccess { turmas = it }.onFailure { erro = it.message ?: "Erro ao carregar turmas." }
        carregando = false
    }

    Column(modifier.fillMaxSize().paint(painterResource(R.drawable.fundo_flores), contentScale = ContentScale.Crop).padding(horizontal = 20.dp)) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onVoltar) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar", tint = Color(0xFF1A1C16))
            }
            Text("Minhas Turmas", style = MaterialTheme.typography.titleLarge, color = Color(0xFF1A1C16))
        }
        when {
            carregando -> CircularProgressIndicator(Modifier.padding(24.dp))
            erro != null -> Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(erro!!, color = MaterialTheme.colorScheme.error)
                TextButton(onClick = { recarga++ }) { Text("Tentar novamente") }
            }
            turmas.isEmpty() -> Text("Nenhuma turma encontrada para este professor.", modifier = Modifier.padding(16.dp))
            else -> LazyColumn(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(16.dp), contentPadding = PaddingValues(top = 12.dp, bottom = 24.dp)) {
                items(turmas, key = { it.id }) { turma ->
                    Card(Modifier.fillMaxWidth(), shape = RoundedCornerShape(14.dp), colors = CardDefaults.cardColors(containerColor = FundoCard)) {
                        Column(Modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(9.dp)) {
                            Text(turma.codigo, fontSize = 17.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF1A1C16))
                            Text(turma.curso, fontSize = 14.sp, color = Color(0xFF44483D))
                            Text("Semestre: ${turma.semestre}", fontSize = 14.sp, color = Color(0xFF44483D))
                            Text("Alunos: ${turma.quantidadeAlunos}", fontSize = 14.sp, color = Color(0xFF44483D))
                            if (turma.diasSemana.isNotEmpty()) Text("Dias: ${turma.diasSemana.joinToString(", ")}", fontSize = 14.sp, color = Color(0xFF44483D))
                            if (turma.horarioInicio.isNotBlank() || turma.horarioFim.isNotBlank()) Text("Horário: ${turma.horarioInicio} - ${turma.horarioFim}", fontSize = 14.sp, color = Color(0xFF44483D))
                        }
                    }
                }
            }
        }
    }
}
