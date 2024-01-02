package com.example.metmuseum.ui.artScreen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.metmuseum.model.Department
import com.example.metmuseum.ui.artScreen.state.DepartmentApiState
import com.example.metmuseum.ui.artScreen.viewModels.DepartmentViewModel
import com.example.metmuseum.ui.components.DepartmentGrid
import com.example.metmuseum.ui.components.ErrorScreen
import com.example.metmuseum.ui.components.LoadingScreen

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE, heightDp = 650)
@Composable
fun DepartmentPreview() {
    DepartmentScreen(onDepartmentClick = {})
}
@Composable
fun DepartmentScreen(
    onDepartmentClick: (department: Department) -> Unit,
    modifier: Modifier = Modifier,
    departmentViewModel: DepartmentViewModel = viewModel(factory = DepartmentViewModel.Factory)
) {
    Log.i("vm inspection", "DepartmentScreen composition")
    val departmentUiState by departmentViewModel.uiState.collectAsState()

    //use the ApiState
    val departmentApiState = departmentViewModel.departmentApiState
    Column (
        modifier = modifier
            .fillMaxSize()
    ){
        when(departmentApiState) {
            is DepartmentApiState.Loading -> {
                LoadingScreen()
            }
            is DepartmentApiState.Error -> {
                ErrorScreen()
            }
            is DepartmentApiState.Success -> {
                DepartmentGrid(
                    departments = departmentUiState.currentDepartments,
                    onDepartmentClick = onDepartmentClick,
                )
            }
        }
    }
}