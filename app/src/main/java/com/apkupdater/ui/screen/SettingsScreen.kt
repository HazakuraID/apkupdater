package com.apkupdater.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.apkupdater.R
import com.apkupdater.ui.component.SliderSetting
import com.apkupdater.ui.component.SwitchSetting
import com.apkupdater.ui.component.TitleText
import com.apkupdater.viewmodel.SettingsViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun SettingsScreen(viewModel: SettingsViewModel = koinViewModel()) = Column {
	SettingsTopBar()
	TitleText("UI", Modifier.padding(horizontal = 8.dp))
	SliderSetting(
		{ viewModel.getPortraitColumns().toFloat() },
		{ viewModel.setPortraitColumns(it.toInt()) },
		stringResource(R.string.setting_portrait_columns),
		1f..4f,
		2
	)
	SliderSetting(
		{ viewModel.getLandscapeColumns().toFloat() },
		{ viewModel.setLandscapeColumns(it.toInt()) },
		stringResource(R.string.setting_landscape_columns),
		1f..8f,
		6
	)
	TitleText("ApkMirror", Modifier.padding(horizontal = 8.dp, vertical = 8.dp))
	SwitchSetting(
		{ viewModel.getIgnoreAlpha() },
		{ viewModel.setIgnoreAlpha(it) },
		stringResource(R.string.ignore_alpha)
	)
	SwitchSetting(
		{ viewModel.getIgnoreBeta() },
		{ viewModel.setIgnoreBeta(it) },
		stringResource(R.string.ignore_beta)
	)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsTopBar() = TopAppBar(
	title = { Text(stringResource(R.string.tab_settings)) }
)