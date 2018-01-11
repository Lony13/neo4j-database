package neo4j.to2.repository;

import neo4j.to2.domain.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends Neo4jRepository<User, Long> {

    @Query("MATCH (n) WHERE id(n)={0} RETURN n")
    User getUserFromId(Long userID);
}
