package lt.traveladvisor.mvp.advisor.repository;

import lt.traveladvisor.mvp.advisor.model.entities.RestDestination;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestDestinationRepository extends JpaRepository<RestDestination, Long> {
}
