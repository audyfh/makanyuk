package com.example.makanyuk.util

object Utility {

    fun extractJsonFromCodeBlock(response: String): String? {
        val regex = Regex("""```json\s*(\{.*?})\s*```""", RegexOption.DOT_MATCHES_ALL)
        val match = regex.find(response)
        return match?.groups?.get(1)?.value
    }

    fun extractJsonSimple(response: String): String {
        return response
            .substringAfter("```json")
            .substringBefore("```")
            .trim()
    }
}