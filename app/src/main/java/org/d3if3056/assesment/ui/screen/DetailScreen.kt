package org.d3if3056.assesment.ui.screen

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3056.assesment.R
import org.d3if3056.assesment.ui.theme.AssesmentTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavHostController) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    var judulRutinitas by remember{ mutableStateOf("") }
    var rutinitas by remember { mutableStateOf("") }
    var moods by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }
    var extraSteps by remember { mutableStateOf("") }

    val rutinitasOptions = listOf(
        stringResource(R.string.pagi),
        stringResource(R.string.malam)
    )

    val moodsOptions = arrayOf(
        stringResource(R.string.luar_biasa),
        stringResource(R.string.baik),
        stringResource(R.string.dapat_diterima),
        stringResource(R.string.buruk),
        stringResource(R.string.jelek),
    )

    val stepsOption = listOf(
        "Cleanser", "Toner", "Spot Treatment", "Serums",
        "Eye Cream", "Moisturizer", "Face Oil", "Sunscreen"
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                title = {
                    Text(
                        text = stringResource(id = R.string.tambah_jurnal),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.kembali)
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(2.dp)
        ) {
            item {
                FormCatatan(
                    title = judulRutinitas,
                    onTitleChange = { judulRutinitas = it },
                    rutinitas = rutinitas,
                    onRutinitasChange = { rutinitas = it },
                    onMoodsChange = { moods = it },
                    notes = notes,
                    onNotesChange = { notes = it },
                    extraSteps = extraSteps,
                    onExtraStepsChange = { extraSteps = it },
                    rutinitasOptions = rutinitasOptions,
                    moodsOptions = moodsOptions,
                    stepsOptions = stepsOption,
                    modifier = Modifier.padding(padding)
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormCatatan(
    title: String, onTitleChange: (String) -> Unit,
    rutinitas: String, onRutinitasChange: (String) -> Unit,
    onMoodsChange: (String) -> Unit,
    notes: String, onNotesChange: (String) -> Unit,
    extraSteps: String,
    onExtraStepsChange: (String) -> Unit, rutinitasOptions: List<String>,
    moodsOptions: Array<String>, stepsOptions: List<String>,
    modifier: Modifier
){
    val context = LocalContext.current
    var expanded by rememberSaveable { mutableStateOf(false) }
    var selectedItemIndex by remember{ mutableStateOf(0) }

    val (selectedSteps, setSelectedSteps) = remember { mutableStateOf(emptyList<String>()) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = title,
            onValueChange = { onTitleChange(it) },
            label = { Text(text = stringResource(id = R.string.judul_kondisi))},
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Box(modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(4.dp)
            )
        ){
            Column {
                Text(
                    text = stringResource(id = R.string.opsi_rutinitas),
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(76.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    rutinitasOptions.forEach{ option ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = rutinitas == option,
                                onClick = { onRutinitasChange(option) }
                            )
                            Text(
                                text = option,
                                modifier = Modifier.padding(start = 4.dp)
                            )
                        }
                    }
                }
            }
        }
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                value = moodsOptions[selectedItemIndex],
                onValueChange = onMoodsChange,
                label = { Text(text = stringResource(id = R.string.opsi_moods)) },
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                moodsOptions.forEachIndexed { index, item ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = item,
                                modifier = Modifier.padding(start = 4.dp),
                                fontWeight = if (index == selectedItemIndex) FontWeight.Bold else null
                            )
                        },
                        onClick = {
                            selectedItemIndex = index
                            expanded = false
                            Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }
        }
        OutlinedTextField(
            value = notes,
            onValueChange = { onNotesChange(it) },
            label = { Text(text = stringResource(id = R.string.notes))},
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Box(modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(4.dp)
            )
            .padding(8.dp)
        ){
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.padding(start = 16.dp, top = 16.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.steps),
                    textAlign = TextAlign.Justify
                )
                stepsOptions.forEach { option ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Checkbox(
                            checked = selectedSteps.contains(option),
                            onCheckedChange = { isChecked ->
                                if (isChecked) {
                                    setSelectedSteps(selectedSteps + option)
                                } else {
                                    setSelectedSteps(selectedSteps - option)
                                }
                            },
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                        Text(text = option)
                    }
                }
            }
        }
        OutlinedTextField(
            value = extraSteps,
            onValueChange = { onExtraStepsChange(it)},
            label = { Text(text = stringResource(id = R.string.extra_steps))},
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun DetailScreenPreview() {
    AssesmentTheme {
        DetailScreen(rememberNavController())
    }
}