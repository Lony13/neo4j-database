package neo4j.to2.repository;

import neo4j.to2.domain.Topic;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends Neo4jRepository<Topic, Long>{
}
