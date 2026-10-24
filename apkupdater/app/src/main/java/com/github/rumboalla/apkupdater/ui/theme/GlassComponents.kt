package com.github.rumboalla.apkupdater.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.kyant.backdrop.drawBackdrop
import io.github.kyant.backdrop.effects.blur
import io.github.kyant.backdrop.effects.lens
import io.github.kyant.backdrop.rememberLayerBackdrop

@Composable
fun LiquidGlassCard(
    modifier: Modifier = Modifier,
    blurRadius: Float = 25f,
    lensSize: Float = 18f,
    cornerRadius: Int = 24,
    content: @Composable BoxScope.() -> Unit
) {
    val backdrop = rememberLayerBackdrop()
    val isDark = MaterialTheme.colorScheme.background.isDarkCompat()
    
    val borderAlpha = if (isDark) 0.22f else 0.55f
    val bgTint = if (isDark) 
        Color.Black.copy(alpha = 0.35f) 
    else Color.White.copy(alpha = 0.40f)

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(cornerRadius))
            .drawBackdrop(
                backdrop = backdrop,
                shape = { RoundedCornerShape(cornerRadius) },
                effects = {
                    blur(radius = blurRadius)       // Efek kabur/kaca buram
                    lens(lensSize, lensSize - 6f)   // Efek pembiasan lensa cahaya
                }
            )
            .background(bgTint)
            .padding(1.dp)
    ) {
        // Border halus seperti tepi kaca
        Box(
            Modifier
                .matchParentSize()
                .clip(RoundedCornerShape(cornerRadius))
                .then(
                    if (borderAlpha > 0f) Modifier else Modifier
                )
                .padding(1.dp),
            content = content
        )
    }
}

// 🔘 Liquid Glass Button — Interaktif!

@Composable
fun LiquidGlassButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String,
    icon: (@Composable () -> Unit)? = null
) {
    val backdrop = rememberLayerBackdrop()
    
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(18.dp))
            .drawBackdrop(
                backdrop = backdrop,
                shape = { RoundedCornerShape(18.dp) },
                effects = {
                    blur(radius = 18f)
                    lens(12f, 8f)
                }
            )
            .clickableNoRipple(onClick = onClick)
            .padding(horizontal = 20.dp, vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            icon?.invoke()
            Text(text, style = MaterialTheme.typography.labelLarge)
        }
    }
}

// 📱 Navbar / BottomBar Liquid Glass

@Composable
fun LiquidGlassBottomBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    val backdrop = rememberLayerBackdrop()
    
    Row(
        modifier = modifier
            .drawBackdrop(
                backdrop = backdrop,
                shape = { RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp) },
                effects = { blur(radius = 22f) }
            )
            .statusBarsPadding()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        content = content
    )
}

// 🛠️ Helper Functions
private fun Color.isDarkCompat(): Boolean {
    val l = 0.299f * red + 0.587f * green + 0.114f * blue
    return l < 0.5f
}

private fun Modifier.clickableNoRipple(
    onClick: () -> Unit
) = this then androidx.compose.foundation.clickable(
    interactionSource = remember { androidx.compose.foundation.interaction.MutableInteractionSource() },
    indication = null,
    onClick = onClick
)
