package com.example.demo

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@SpringBootApplication
class DemoApplication

fun main(args: Array<String>) {
    SpringApplication.run(DemoApplication::class.java, *args)
}


@Entity
data class Beers(@Id @GeneratedValue val id:Long ?= null, var name: String?= null)

@RepositoryRestResource(collectionResourceRel = "Beers",path = "Beers")
interface BeersRepository: JpaRepository<Beers, Long>


@Component
class BeerCommandLineRunner(val BeerRepository: BeersRepository) : CommandLineRunner {
    override fun run(vararg p0: String?) {
        listOf<String>("Heineken","Coors","Budlight","Budwieser","Blue Moon").forEach { it -> BeerRepository.save(Beers(name = it)); }
    }
}


@RestController
class BeerController(val BeerRepository:BeersRepository) {

    @RequestMapping("/beers")
    fun getAllBeers() : List<Beers> {
       return BeerRepository.findAll();
    }
}


