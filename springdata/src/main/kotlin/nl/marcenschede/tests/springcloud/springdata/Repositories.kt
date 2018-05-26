package nl.marcenschede.tests.springcloud.springdata

import org.springframework.data.repository.CrudRepository
import javax.persistence.*
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.data.rest.core.annotation.RepositoryRestResource

/**
 * Annotatie @RepositoryRestResource is optioneel, zonder deze regel werkt search ook wel. Dan is naam van entity het uitgangspunt.
 *
 * collectionResourceRel is naam van de collectie in de output
 * path is naam in de URL om op te vragen
 */
@RepositoryRestResource(collectionResourceRel = "cities", path = "citis")
interface UserRepository : CrudRepository<City, Long> {

    /**
     * Test with
     * curl http://localhost:8080/citis
     * curl http://localhost:8080/citis/1
     * curl http://localhost:8080/citis/search/findByCity?city=enschede
     */

    fun findByCity(@Param("city") city: String): List<City>
}

@Entity
@Table(name = "cities")
data class City(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id:Long?=null,
                var city:String?=null, var lattitude:Double?=null, var longitude:Double?=null) {

}