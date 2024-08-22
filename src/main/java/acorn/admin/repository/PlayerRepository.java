package acorn.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import acorn.admin.entity.Player;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
    

}
