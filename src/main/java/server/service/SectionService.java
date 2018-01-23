package server.service;

import neo4j.to2.domain.Section;
import neo4j.to2.domain.Topic;
import neo4j.to2.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SectionService {

    @Autowired
    private SectionRepository sectionRepository;

    public Section createSection(Section section){
        return sectionRepository.save(section);
    }

    public Section updateSection(Section section){
        return sectionRepository.save(section);
    }

    public void deleteSection(Section section){
        sectionRepository.delete(section);
    }

    public Section getSection(long sectionID){
        Optional<Section> optAnswer = sectionRepository.findById(sectionID);
        return optAnswer.orElse(null);
    }

    public List<Topic> getTopicsForSection(long sectionID, int from, int to) {
        Optional<Section> optCategory = sectionRepository.findById(sectionID);
        if(!optCategory.isPresent())
            return null;

        List<Topic> topics = optCategory.get().getTopics();

        if(topics.size() < to)
            return null;
        return topics.subList(from, to);
    }

    public List<Section> getSections(int number) {
        return sectionRepository.getSections();
    }
}
