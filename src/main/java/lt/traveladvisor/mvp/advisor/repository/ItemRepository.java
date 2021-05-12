package lt.traveladvisor.mvp.advisor.repository;

import lt.traveladvisor.mvp.advisor.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query(value = "SELECT * FROM Item as i WHERE i.type = :type ORDER BY random() LIMIT :limit", nativeQuery = true)
    Set<Item> getRandomItemsByTypeWithLimit(@Param("limit") long limit, @Param("type") String itemType);
}
