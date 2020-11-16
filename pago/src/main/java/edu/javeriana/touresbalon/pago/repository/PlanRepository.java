package edu.javeriana.touresbalon.pago.repository;

import edu.javeriana.touresbalon.entities.Plan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PlanRepository extends CrudRepository<Plan, Integer> {

}