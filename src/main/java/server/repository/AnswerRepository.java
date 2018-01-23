package server.repository;

import neo4j.to2.domain.Answer;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends Neo4jRepository<Answer, Long> {
}
