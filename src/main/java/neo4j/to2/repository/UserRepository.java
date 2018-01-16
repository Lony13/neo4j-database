package neo4j.to2.repository;

import neo4j.to2.domain.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends Neo4jRepository<User, Long> {

    @Query("MATCH (n) WHERE id(n)={0} RETURN n")
    User getUserFromId(Long userID);

    @Query("MATCH (u:User {firstName:{0}}) RETURN u")
    List<User> getUserFromFirstName(String firstName);

    @Query("MATCH (u:User {lastName:{0}}) RETURN u")
    List<User> getUserFromLastName(String lastName);

    @Query("MATCH (u:User {country:{0}}) RETURN u")
    List<User> getUserFromCountry(String country);

    @Query("MATCH (u:User {city:{0}}) RETURN u")
    List<User> getUserFromCity(String city);

    @Query("MATCH (u:User {address:{0}}) RETURN u")
    List<User> getUserFromAddress(String address);

    @Query("MATCH (n:User) WHERE single(x IN n.languages WHERE x={0})  RETURN n")
    List<User> getUserFromLanguage(String language);

    @Query("MATCH (u:User {login:{0}, password:{1}}) RETURN u")
    User loginUser(String login, String password);

    @Query("MATCH (u1:User), (u2:User), " +
            "path = shortestPath((u1)-[*..2]-(u2)) " +
            "WHERE u1 <> u2 AND NOT ((u1)-[:FRIEND_OF]-(u2)) AND id(u1)={0} " +
            "RETURN u2 " +
            "ORDER BY LENGTH(path) DESC " +
            "LIMIT {1}")
    List<User> findSimilarUsers(long userID, int maxUsers);
}
