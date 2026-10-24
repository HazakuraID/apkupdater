package com.github.rumboalla.apkupdater.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.rumboalla.apkupdater.ui.theme.GlassCard
import com.github.rumboalla.apkupdater.ui.theme.GlassButton
import com.github.rumboalla.apkupdater.data.model.UpdateModel

@Composable
fun UpdatesScreen(
    updates: List<UpdateModel>,
    onRefresh: () -> Unit,
    onInstallAll: () -> Unit,
    onInstallClick: (UpdateModel) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 📌 Baris Tombol Atas
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (updates.isEmpty()) "Tidak ada pembaruan" 
                       else "Ditemukan ${updates.size} pembaruan",
                style = MaterialTheme.typography.titleMedium
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                GlassButton(onClick = onRefresh) {
                    Text("🔄 Cek Lagi")
                }
                GlassButton(onClick = onInstallAll) {
                    Text("⬇️ Pasang Semua")
                }
            }
        }

        // 📋 Daftar Pembaruan
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(updates) { update ->
                GlassCard(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    UpdateItem(
                        update = update,
                        onInstall = { onInstallClick(update) },
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun UpdateItem(
    update: UpdateModel,
    onInstall: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = update.appName,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "${update.currentVersion} → ${update.newVersion}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = update.source,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.primary
            )
        }
        Button(
            onClick = onInstall,
            modifier = Modifier.padding(start = 12.dp)
        ) {
            Text("Pasang")
        }
    }
}
