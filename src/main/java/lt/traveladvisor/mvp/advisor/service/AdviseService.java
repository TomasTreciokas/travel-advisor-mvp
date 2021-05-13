package lt.traveladvisor.mvp.advisor.service;

import lt.traveladvisor.mvp.advisor.model.entities.Advise;
import lt.traveladvisor.mvp.advisor.repository.AdviseRepository;
import org.springframework.stereotype.Service;

@Service
public class AdviseService {

    private final AdviseRepository adviseRepository;

    public AdviseService(AdviseRepository adviseRepository) {
        this.adviseRepository = adviseRepository;
    }

    public Advise save(Advise advise){
        return adviseRepository.save(advise);
    }
}
