package org.example.rsiobserversrv.service

import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import org.springframework.stereotype.Service
import org.springframework.util.ResourceUtils
import java.io.FileReader
import java.nio.file.Paths
import kotlin.io.path.exists

data class Person(
    val id: Int,
    val name: String,
    val email: String,
    val phone: String
)

@Service
class CsvService {

    fun readCsv(filename: String): List<Person> {

        val resource = ResourceUtils.getFile("classpath:$filename")

        return CSVFormat.DEFAULT
            .withFirstRecordAsHeader()
            .withIgnoreHeaderCase()
            .parse(resource.reader())
            .map { record ->
                Person(
                    id = record.get("id").toInt(),
                    name = record.get("name"),
                    email = record.get("email"),
                    phone = record.get("phone")
                )
            }.toList()
    }
    fun load(filename: String, folderPath: String = "data"): List<Person> {

        val filePath = "$folderPath/$filename"
        val persons = mutableListOf<Person>()

        val path = Paths.get(filePath)
        if (path.exists()) {
            FileReader(filePath).use { reader ->
                val format = CSVFormat.DEFAULT.builder()
                    .setHeader() // Or use withFirstRecordAsHeader() for older versions
                    .setSkipHeaderRecord(true) // Skips the header row from being included in the records
                    .build()

                val parser = CSVParser(reader, format)
                for (record in parser) {
                    persons.add(
                        Person(
                            id = record.get("id").toInt(),
                            name = record.get("name"),
                            email = record.get("email"),
                            phone = record.get("phone")
                        )
                    )
                }
            }
            println("Файл $filename успешно загружен")
        }else{
            println("Файл $filename не найден в $folderPath")
        }
        return persons
    }

}
