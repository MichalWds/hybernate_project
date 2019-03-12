package hibernate.repository;

import hibernate.model.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface PersonRepository extends
            CrudRepository<Person,Integer>{


//p to ze wszystkie po id,  Query to odnosnik do HyberNate SQL
 @Query("SELECT p FROM Person p ORDER BY p.id")
List<Person> findAllSortById();

 @Query ("SELECT p FROM Person p WHERE p.age=?1")
 List<Person>findByAgeEighteen(Integer age);
    //skladnia z ?  to tak jest,  odnosi sie do naszego age, czyli bedzie find by age eighteeen, po wieku ktory wpiszemy, "age"

@Query("SELECT p FROM Person p WHERE p.name=?1 OR p.age=?2")
 List<Person> FindByNameOrAge(String name, Integer age);

}
