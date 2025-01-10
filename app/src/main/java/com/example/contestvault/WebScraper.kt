package com.example.contestvault
//
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.withContext
//import okhttp3.OkHttpClient
//import okhttp3.Request
//import org.jsoup.Jsoup
//import org.jsoup.nodes.Document
//
//class WebScraper {
//    private val client = OkHttpClient()
//
//    // Function to fetch and scrape website
//    suspend fun scrapeWebsite(url: String): List<String> {
//        return withContext(Dispatchers.IO) {
//            try {
//                // Fetch HTML using OkHttp
//                val request = Request.Builder().url(url).build()
//                val response = client.newCall(request).execute()
//                val htmlContent = response.body?.string()
//
//                // Parse HTML using jsoup
//                val document: Document = Jsoup.parse(htmlContent)
//
//                // Extract specific elements (e.g., titles in <h2> tags)
//                val elements = document.select("h2") // Adjust selector as needed
//                elements.map { it.text() } // Extract text from the selected elements
//            } catch (e: Exception) {
//                e.printStackTrace()
//                emptyList() // Return an empty list if scraping fails
//            }
//        }
//    }
//}