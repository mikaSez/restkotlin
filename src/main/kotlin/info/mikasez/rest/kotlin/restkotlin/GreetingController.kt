package info.mikasez.rest.kotlin.restkotlin

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.concurrent.atomic.AtomicLong

@RestController
class GreetingController {

    val replacements = mutableMapOf<String, String>()

    val counter = AtomicLong()

    @GetMapping("/greeting")
    fun greeting(@RequestParam(value = "name", defaultValue = "World") name: String) =
            Greeting(counter.incrementAndGet(), "Hello, ${replacements.getOrDefault(name, name)}")

    @PostMapping("/replaceName")
    fun change(@RequestParam(value = "oldName") oldName: String, @RequestParam(value = "newName") newName: String) : ResponseEntity<String> {
        replacements[oldName] = newName
        return ResponseEntity("Now $oldName is called $newName", HttpStatus.ACCEPTED)
    }
}