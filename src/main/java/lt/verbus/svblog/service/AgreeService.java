package lt.verbus.svblog.service;

import lt.verbus.svblog.repository.AgreeRepository;
import org.springframework.stereotype.Service;


@Service
public class AgreeService {

    private final AgreeRepository agreeRepository;

    public AgreeService(AgreeRepository agreeRepository) {
        this.agreeRepository = agreeRepository;
    }

}
