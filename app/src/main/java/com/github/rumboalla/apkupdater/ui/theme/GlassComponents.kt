package com.github.rumboalla.apkupdater.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

// ────────────────────────────────────────────────────
// 💎 Glass Card — Kartu dengan efek kaca transparan
// ────────────────────────────────────────────────────
@Composable
fun GlassCard(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(20.dp),
    alpha: Float = 0.65f,
    cornerRadius: Dp = 20.dp,
    content: @Composable BoxScope.() -> Unit
) {
    val isDark = MaterialTheme.colorScheme.background.isDark()
    
    val bgGradient = Brush.linearGradient(
        colors = when {
            isDark -> listOf(
                Color.White.copy(alpha = 0.12f),
                Color.White.copy(alpha = 0.05f)
            )
            else -> listOf(
                Color.White.copy(alpha = 0.70f),
                Color.White.copy(alpha = 0.45f)
            )
        }
    )
    
    val borderGradient = Brush.linearGradient(
        colors = when {
            isDark -> listOf(
                Color.White.copy(alpha = 0.25f),
                Color.White.copy(alpha = 0.06f)
            )
            else -> listOf(
                Color.White.copy(alpha = 0.80f),
                Color.White.copy(alpha = 0.30f)
            )
        }
    )

    Box(
        modifier = modifier
            .clip(shape)
            .background(bgGradient, shape = shape)
            .border(
                border = BorderStroke(
                    width = 1.dp,
                    brush = borderGradient
                ),
                shape = shape
            ),
        content = content
    )
}

// ────────────────────────────────────────────────────
// 🪟 Glass Button — Tombol gaya kaca transparan
// ────────────────────────────────────────────────────
@Composable
fun GlassButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = RoundedCornerShape(16.dp),
    contentPadding: PaddingValues = PaddingValues(horizontal = 20.dp, vertical = 12.dp),
    content: @Composable RowScope.() -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isDark = MaterialTheme.colorScheme.background.isDark()
    
    val bgGradient = Brush.linearGradient(
        colors = when {
            !enabled -> listOf(
                Color.Gray.copy(alpha = 0.3f),
                Color.Gray.copy(alpha = 0.15f)
            )
            isDark -> listOf(
                Color.White.copy(alpha = 0.15f),
                Color.White.copy(alpha = 0.07f)
            )
            else -> listOf(
                Color.White.copy(alpha = 0.65f),
                Color.White.copy(alpha = 0.40f)
            )
        }
    )

    Row(
        modifier = modifier
            .clip(shape)
            .background(bgGradient, shape)
            .clickable(
                enabled = enabled,
                onClick = onClick,
                role = Role.Button,
                interactionSource = interactionSource,
                indication = null
            )
            .padding(contentPadding),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        content = content
    )
}

// ────────────────────────────────────────────────────
// 🔍 Helper: Cek apakah tema gelap
// ────────────────────────────────────────────────────
private fun Color.isDark(): Boolean {
    val l = (0.299f * red + 0.587f * green + 0.114f * blue)
    return l < 0.5f
}

// ────────────────────────────────────────────────────
// 📱 Contoh Penggunaan
// ────────────────────────────────────────────────────
@Composable
fun GlassPreviewExample() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // Contoh Glass Card
        GlassCard(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
        ) {
            Column(
                Modifier.padding(20.dp).fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "APKUpdater 💎",
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = "Liquid Glass Style",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        // Contoh Glass Button
        GlassButton(onClick = { /* refresh updates */ }) {
            Text("🔍 Cari Pembaruan", style = MaterialTheme.typography.labelLarge)
        }
    }
}
