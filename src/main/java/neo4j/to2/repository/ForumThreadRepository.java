package neo4j.to2.repository;

import neo4j.to2.domain.ForumThread;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForumThreadRepository extends Neo4jRepository<ForumThread, Long>{
}
