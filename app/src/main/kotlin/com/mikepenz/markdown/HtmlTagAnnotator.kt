package com.mikepenz.markdown

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.style.BaselineShift
import com.mikepenz.markdown.model.MarkdownAnnotator
import org.intellij.markdown.MarkdownTokenTypes
import org.intellij.markdown.ast.ASTNode
import kotlin.collections.contains

@Composable
fun subSupAnnotator() = HtmlTagAnnotator(
    "sub" to SpanStyle(baselineShift = BaselineShift.Subscript),
    "sup" to SpanStyle(baselineShift = BaselineShift.Superscript)
)

@Immutable
class HtmlTagAnnotator(vararg stylers: Pair<String, SpanStyle>) : MarkdownAnnotator {

    override val annotate: AnnotatedString.Builder.(String, ASTNode) -> Boolean =
        { content, child ->
            val map = stylers.toMap()
            if (child.type == MarkdownTokenTypes.HTML_TAG) {
                val tag = content.substring(child.startOffset + 1, child.endOffset - 1)
                map[tag]?.let(::pushStyle)
                if (tag.startsWith('/')) {
                    if (map.contains(tag.substring(1))) {
                        pop()
                    }
                }
            }
            false
        }
}