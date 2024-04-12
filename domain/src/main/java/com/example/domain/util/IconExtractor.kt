package com.example.domain.util

object IconExtractor {

    fun extractIcon(icon: String) =
        StringBuilder()
            .append(Constants.ICON_PREFIX)
            .append(icon)
            .append(Constants.ICON_SUFFIX)
            .toString()
}