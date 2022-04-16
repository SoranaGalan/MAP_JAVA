package repository;

import classes.*;
import repository.EnrollmentsRepositoryFile;

public class EnrollmentsRepository extends AbstractRepository<Enrollments, Integer> {
	public EnrollmentsRepository() {
	};
	
	public EnrollmentsRepository(EnrollmentsRepositoryFile current_repo) {
		for(Enrollments enrollment : current_repo.findAll())
			this.add(enrollment);
	}
};