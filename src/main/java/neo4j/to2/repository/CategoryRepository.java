package neo4j.to2.repository;

import neo4j.to2.domain.Category;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends Neo4jRepository<Category, Long>{

    @Query(value = "MATCH (c:Category) RETURN c")
    List<Category> getCategories();
}
