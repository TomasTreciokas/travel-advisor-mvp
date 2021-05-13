package lt.traveladvisor.mvp.advisor.repository;

import lt.traveladvisor.mvp.advisor.model.entities.Advise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdviseRepository extends JpaRepository<Advise, Long> {
}
