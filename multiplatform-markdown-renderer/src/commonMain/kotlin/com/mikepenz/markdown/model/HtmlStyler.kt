package com.mikepenz.markdown.model

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.SpanStyle

interface HtmlStyler {
    val style: ((String) -> SpanStyle?)?
}

@Immutable
class DefaultHtmlStyler(override val style: ((String) -> SpanStyle?)?) : HtmlStyler

@Composable
fun htmlStyler(style: ((String) -> SpanStyle?)? = null): HtmlStyler =
    DefaultHtmlStyler(style)
