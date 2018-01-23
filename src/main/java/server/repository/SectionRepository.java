package server.repository;

import neo4j.to2.domain.Section;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectionRepository extends Neo4jRepository<Section, Long>{

    @Query(value = "MATCH (c:Section) RETURN c")
    List<Section> getSections();
}
