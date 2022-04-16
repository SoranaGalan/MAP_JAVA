package repository;

import classes.*;
import repository.WinterGameRepositoryFile;

public class WinterGameRepository extends AbstractRepository<WinterGame, Integer> {
	public WinterGameRepository() {
	};
	
	public WinterGameRepository(WinterGameRepositoryFile current_repo) {
		for(WinterGame game : current_repo.findAll())
			this.add(game);
	}
};