package server.repository;

import neo4j.to2.domain.ProfilePost;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfilePostRepository extends Neo4jRepository<ProfilePost, Long>{
}
