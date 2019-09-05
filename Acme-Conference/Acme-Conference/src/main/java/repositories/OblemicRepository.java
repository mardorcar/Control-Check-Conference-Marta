
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Oblemic;

@Repository
public interface OblemicRepository extends JpaRepository<Oblemic, Integer> {

	@Query("select o from Oblemic o where o.mode='FINAL' and o.conference.id=?1")
	Collection<Oblemic> findByConference(int id);

	@Query("select o from Oblemic o where o.mode='FINAL'")
	Collection<Oblemic> findFinalOblemics();

	@Query("select o from Oblemic o where o.mode='DRAFT'")
	Collection<Oblemic> findDraftOblemics();

	@Query("select o from Oblemic o where o.mode='FINAL' and o.administrator.id=?1")
	Collection<Oblemic> findFinalOblemicsByAdmin(int adminId);

	@Query("select o from Oblemic o where o.mode='DRAFT' and o.administrator.id=?1")
	Collection<Oblemic> findDraftOblemicsByAdmin(int adminId);

	// Oblemics per conference
	@Query("select avg(1.0*(select count(o) from Oblemic o where o.conference.id = c.id)),stddev(1.0*(select count(o) from Oblemic o where o.conference.id = c.id)) from Conference c")
	Collection<Double> statsNumberOblemics();

	@Query("select sum(case when o.mode='FINAL' then 1.0 else 0.0 end)/count(o) from Oblemic o")
	Double publishedRatio();

	@Query("select sum(case when o.mode='DRAFT' then 1.0 else 0.0 end)/count(o) from Oblemic o")
	Double unpublishedRatio();

}
