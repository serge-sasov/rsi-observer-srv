package org.example.rsiobserversrv

import org.example.rsiobserversrv.service.CsvService
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.ResourceLoader
import org.springframework.stereotype.Component
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import java.util.Objects

data class Product(val id: Int, val name: String, val price: Double)

@Controller
class HtmlController {

    @GetMapping("/overview")
    fun blog(model: Model): String {
        model["title"] = "RSI overview"
        val items = mutableListOf<String?>("Item 1", "Item 2", "Item 3")
        model.addAttribute("myItems", items) // "myItems" is the key for the array in the template
        return "rsi-overview"
    }

    @GetMapping("/products")
    fun showProducts(model: Model): String {
        val products = listOf(
            Product(1, "Laptop", 1200.00),
            Product(2, "Mouse", 25.00),
            Product(3, "Keyboard", 75.00)
        )
        model.addAttribute("products", products)
        return "product-table" // Refers to product-table.mustache
    }
}

@Controller
class MainController(
    private val csvService: CsvService
) {

    @GetMapping("/names")
    fun showTable(model: Model): String {
        val folderPath = System.getenv("FOLDERPATH")
        val fileName =  System.getenv("FILENAME")
        val persons = csvService.load(fileName, folderPath)
        model.addAttribute("persons", persons)
        return "table"
    }
}
