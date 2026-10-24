package com.github.rumboalla.apkupdater.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.rumboalla.apkupdater.ui.theme.GlassCard
import com.github.rumboalla.apkupdater.ui.theme.GlassButton

// ────────────────────────────────────────────────────
// ⚙️ LAYAR PENGATURAN
// ────────────────────────────────────────────────────
@Composable
fun SettingsScreen(
    theme: String,
    onThemeChange: (String) -> Unit,
    updateFrequency: String,
    onFrequencyChange: (String) -> Unit,
    sources: List<SourceItem>,
    onSourceToggle: (String, Boolean) -> Unit,
    useExperimentalInstaller: Boolean,
    onExperimentalInstallerChange: (Boolean) -> Unit,
    excludeSystemApps: Boolean,
    onExcludeSystemAppsChange: (Boolean) -> Unit,
    onAboutClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Pengaturan",
            style = MaterialTheme.typography.headlineSmall
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // 🎨 Bagian: Tema
            item {
                GlassCard(modifier = Modifier.fillMaxWidth()) {
                    Column(Modifier.padding(20.dp)) {
                        Text("Tampilan", style = MaterialTheme.typography.titleMedium)
                        Spacer(Modifier.height(16.dp))

                        Text("Tema", style = MaterialTheme.typography.bodyMedium)
                        Spacer(Modifier.height(8.dp))
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            listOf("system" to "Ikuti Sistem", "light" to "Terang", "dark" to "Gelap").forEach {
                                FilterChip(
                                    selected = theme == it.first,
                                    onClick = { onThemeChange(it.first) },
                                    label = { Text(it.second) }
                                )
                            }
                        }
                    }
                }
            }

            // ⏰ Bagian: Frekuensi Pembaruan
            item {
                GlassCard(modifier = Modifier.fillMaxWidth()) {
                    Column(Modifier.padding(20.dp)) {
                        Text("Pembaruan Otomatis", style = MaterialTheme.typography.titleMedium)
                        Spacer(Modifier.height(16.dp))

                        Text("Frekuensi Cek", style = MaterialTheme.typography.bodyMedium)
                        Spacer(Modifier.height(8.dp))
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            listOf("daily" to "Harian", "3day" to "3 Hari", "weekly" to "Mingguan").forEach {
                                FilterChip(
                                    selected = updateFrequency == it.first,
                                    onClick = { onFrequencyChange(it.first) },
                                    label = { Text(it.second) }
                                )
                            }
                        }
                    }
                }
            }

            // 📦 Bagian: Sumber Pembaruan
            item {
                GlassCard(modifier = Modifier.fillMaxWidth()) {
                    Column(Modifier.padding(20.dp)) {
                        Text("Sumber Aplikasi", style = MaterialTheme.typography.titleMedium)
                        Spacer(Modifier.height(12.dp))

                        sources.forEach { source ->
                            SourceToggleRow(
                                name = source.name,
                                enabled = source.enabled,
                                onToggle = { onSourceToggle(source.id, it) }
                            )
                            if (source != sources.last()) HorizontalDivider(
                                modifier = Modifier.padding(vertical = 10.dp)
                            )
                        }
                    }
                }
            }

            // 🔧 Bagian: Opsi Lanjutan
            item {
                GlassCard(modifier = Modifier.fillMaxWidth()) {
                    Column(Modifier.padding(20.dp)) {
                        Text("Opsi Lanjutan", style = MaterialTheme.typography.titleMedium)
                        Spacer(Modifier.height(12.dp))

                        SwitchRow(
                            title = "Pemasang Baru Eksperimental",
                            checked = useExperimentalInstaller,
                            onCheckedChange = onExperimentalInstallerChange
                        )
                        HorizontalDivider(modifier = Modifier.padding(vertical = 10.dp))
                        SwitchRow(
                            title = "Kecualikan Aplikasi Sistem",
                            checked = excludeSystemApps,
                            onCheckedChange = onExcludeSystemAppsChange
                        )
                    }
                }
            }

            // ℹ️ Bagian: Tentang
            item {
                GlassCard(modifier = Modifier.fillMaxWidth()) {
                    Column(Modifier.padding(20.dp)) {
                        Text("Tentang", style = MaterialTheme.typography.titleMedium)
                        Spacer(Modifier.height(12.dp))

                        GlassButton(onClick = onAboutClick, modifier = Modifier.fillMaxWidth()) {
                            Text("📖 Tentang APKUpdater", modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }
    }
}

// ────────────────────────────────────────────────────
// 📦 Model Data untuk Sumber
// ────────────────────────────────────────────────────
data class SourceItem(
    val id: String,
    val name: String,
    val enabled: Boolean
)

// ────────────────────────────────────────────────────
// 🔘 Baris Toggle Sumber
// ────────────────────────────────────────────────────
@Composable
private fun SourceToggleRow(
    name: String,
    enabled: Boolean,
    onToggle: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(name, style = MaterialTheme.typography.bodyMedium)
        Switch(
            checked = enabled,
            onCheckedChange = onToggle
        )
    }
}

// ────────────────────────────────────────────────────
// 🔘 Baris Switch Sederhana
// ────────────────────────────────────────────────────
@Composable
private fun SwitchRow(
    title: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(title, style = MaterialTheme.typography.bodyMedium)
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
    }
}
