package com.restrao.items.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.restrao.items.entities.Items;

@Repository
public interface ItemsRepo extends JpaRepository<Items, Long>{

}
