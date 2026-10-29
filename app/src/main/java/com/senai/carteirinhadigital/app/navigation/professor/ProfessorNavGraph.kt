
package com.senai.carteirinhadigital.app.navigation.professor

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.senai.carteirinhadigital.app.navigation.GraphRoutes
import com.senai.carteirinhadigital.app.navigation.ProfileGuard
import com.senai.carteirinhadigital.app.ui.AppShell
import com.senai.carteirinhadigital.feature.login.domain.model.PerfilAcesso
import com.senai.carteirinhadigital.feature.login.domain.model.UsuarioLogado
import com.senai.carteirinhadigital.feature.professor.domain.repository.ProfessorRepository
import com.senai.carteirinhadigital.feature.professor.home.presentation.screen.HomeProfessorScreen
import com.senai.carteirinhadigital.feature.professor.turmas.presentation.screen.MinhasTurmasScreen
import com.senai.carteirinhadigital.feature.professor.unidadescurriculares.presentation.screen.TelaUnidadesProfessor
import kotlinx.coroutines.flow.StateFlow

fun NavGraphBuilder.professorNavGraph(
    navController: NavHostController,
    usuarioLogadoFlow: StateFlow<UsuarioLogado?>,
    professorRepository: ProfessorRepository,
    darkTheme: Boolean,
    onDarkThemeChange: (Boolean) -> Unit,
    onLogout: () -> Unit,
    onSessaoAusente: () -> Unit,
    onPerfilInvalido: (PerfilAcesso) -> Unit
) {
    navigation(
        startDestination = ProfessorRoutes.HOME,
        route = GraphRoutes.PROFESSOR
    ) {
        composable(route = ProfessorRoutes.HOME) {
            ProfileGuard(
                usuarioFlow = usuarioLogadoFlow,
                perfilEsperado = PerfilAcesso.PROFESSOR,
                onSessaoAusente = onSessaoAusente,
                onPerfilInvalido = onPerfilInvalido
            ) { usuario ->
                AppShell(
                    title = "Início",
                    usuarioLogado = usuario,
                    canNavigateBack = false,
                    darkTheme = darkTheme,
                    onDarkThemeChange = onDarkThemeChange,
                    onNavigateBack = {},
                    onLogout = onLogout
                ) { innerPadding ->
                    HomeProfessorScreen(
                        usuarioLogado = usuario,
                        onTurmasClick = {
                            navController.navigate(ProfessorRoutes.TURMAS)
                        },
                        onUnidadesCurricularesClick = {
                            navController.navigate(ProfessorRoutes.UNIDADES)
                        },
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }
            }
        }

        composable(route = ProfessorRoutes.TURMAS) {
            MinhasTurmasScreen(
                repository = professorRepository,
                onVoltar = {
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxSize()
            )
        }

        composable(route = ProfessorRoutes.UNIDADES) {
            TelaUnidadesProfessor(
                repository = professorRepository,
                onVoltar = {
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
