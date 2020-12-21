package lt.verbus.svblog.repository;

import lt.verbus.svblog.model.Agree;
import lt.verbus.svblog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgreeRepository extends JpaRepository<Agree, Long> {
    List<Agree> findAllByUser(User user);
}
