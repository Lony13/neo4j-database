package server.repository;

import neo4j.to2.domain.Chat;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends Neo4jRepository<Chat, Long>{
}
