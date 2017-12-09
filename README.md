# KotlinBeersRestful
**Beers Restful Services using Spring Boot and Kotlin**


### First Kotlin Based REST Service

**Main Entry Class**

```
@SpringBootApplication
class DemoApplication

fun main(args: Array<String>) {
    SpringApplication.run(DemoApplication::class.java, *args)
}

```

**Entity (POJO)**

```
@Entity
data class Beers(@Id @GeneratedValue val id:Long ?= null, var name: String?= null)
```


**DAO**
```
@RepositoryRestResource(collectionResourceRel = "Beers",path = "Beers")
interface BeersRepository: JpaRepository<Beers, Long>

```

**Simple Spring Component which Inserts records into db when application starts**
```

@Component
class BeerCommandLineRunner(val BeerRepository: BeersRepository) : CommandLineRunner {
    override fun run(vararg p0: String?) {
        listOf<String>("Heineken","Coors","Budlight","Budwieser","Blue Moon").forEach { it -> BeerRepository.save(Beers(name = it)); }
    }
}
```

**Main Rest Controller**
```
@RestController
class BeerController(val BeerRepository:BeersRepository) {

    @RequestMapping("/beers")
    fun getAllBeers() : List<Beers> {
       return BeerRepository.findAll();
    }
}
``` 
