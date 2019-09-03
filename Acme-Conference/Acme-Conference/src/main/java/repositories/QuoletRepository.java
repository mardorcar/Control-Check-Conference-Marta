
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Quolet;

@Repository
public interface QuoletRepository extends JpaRepository<Quolet, Integer> {

	@Query("select q from Quolet q where q.mode='FINAL' and q.conference.id=?1")
	Collection<Quolet> findByConference(int id);

	@Query("select q from Quolet q where q.mode='FINAL'")
	Collection<Quolet> findFinalQuolets();

	@Query("select q from Quolet q where q.mode='DRAFT'")
	Collection<Quolet> findDraftQuolets();

}
