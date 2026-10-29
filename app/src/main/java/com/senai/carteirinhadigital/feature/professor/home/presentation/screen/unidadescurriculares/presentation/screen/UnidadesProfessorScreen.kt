package com.senai.carteirinhadigital.feature.professor.unidadescurriculares.presentation.screen

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
import com.senai.carteirinhadigital.feature.professor.data.remote.dto.UnidadeProfessorDto
import com.senai.carteirinhadigital.feature.professor.domain.repository.ProfessorRepository

private val FundoUnidades = Color(0xFFF9FAEF)
private val FundoCardUnidades = Color(0xFFEEEFE3)

@Composable
fun TelaUnidadesProfessor(
    repository: ProfessorRepository,
    onVoltar: () -> Unit,
    modifier: Modifier = Modifier
) {
    var unidades by remember { mutableStateOf<List<UnidadeProfessorDto>>(emptyList()) }
    var carregando by remember { mutableStateOf(true) }
    var erro by remember { mutableStateOf<String?>(null) }
    var recarga by remember { mutableStateOf(0) }

    LaunchedEffect(recarga) {
        carregando = true
        erro = null
        repository.listarUnidadesCurriculares().onSuccess { unidades = it }.onFailure { erro = it.message ?: "Erro ao carregar unidades curriculares." }
        carregando = false
    }

    Column(modifier.fillMaxSize().paint(painterResource(R.drawable.fundo_flores), contentScale = ContentScale.Crop).padding(horizontal = 20.dp)) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onVoltar) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar", tint = Color(0xFF1A1C16))
            }
            Text("Unidades Curriculares", style = MaterialTheme.typography.titleLarge, color = Color(0xFF1A1C16))
        }
        when {
            carregando -> CircularProgressIndicator(Modifier.padding(24.dp))
            erro != null -> Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(erro!!, color = MaterialTheme.colorScheme.error)
                TextButton(onClick = { recarga++ }) { Text("Tentar novamente") }
            }
            unidades.isEmpty() -> Text("Nenhuma unidade curricular encontrada para este professor.", modifier = Modifier.padding(16.dp))
            else -> LazyColumn(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(16.dp), contentPadding = PaddingValues(top = 12.dp, bottom = 24.dp)) {
                items(unidades, key = { it.id }) { unidade ->
                    Card(Modifier.fillMaxWidth(), shape = RoundedCornerShape(14.dp), colors = CardDefaults.cardColors(containerColor = FundoCardUnidades)) {
                        Column(Modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(9.dp)) {
                            Text(unidade.nome, fontSize = 17.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF1A1C16))
                            Text("Turma: ${unidade.turma}", fontSize = 14.sp, color = Color(0xFF44483D))
                            Text("Carga horária: ${unidade.cargaHoraria} horas", fontSize = 14.sp, color = Color(0xFF44483D))
                            Text("Aulas realizadas: ${unidade.aulasRealizadas} de ${unidade.quantidadeAulas}", fontSize = 14.sp, color = Color(0xFF44483D))
                            if (unidade.diasSemana.isNotEmpty()) Text("Dias: ${unidade.diasSemana.joinToString(", ")}", fontSize = 14.sp, color = Color(0xFF44483D))
                            if (unidade.horarioInicio.isNotBlank() || unidade.horarioFim.isNotBlank()) Text("Horário: ${unidade.horarioInicio} - ${unidade.horarioFim}", fontSize = 14.sp, color = Color(0xFF44483D))
                        }
                    }
                }
            }
        }
    }
}
