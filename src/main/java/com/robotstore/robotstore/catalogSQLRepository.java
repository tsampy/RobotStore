package com.robotstore.robotstore;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;
import java.util.List;

public interface catalogSQLRepository extends JpaRepository<catalogSQL, Long> {

    // DELETE SQL request : Spring Data repository method, annotated with @Query
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM catalog c WHERE c.idrobot = :robotID", nativeQuery = true)
    int deleteRobot(@Param("robotID") int robot_ID);

    // INSERT SQL request : Spring Data repository method, annotated with @Query
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO catalog(idrobot, price, promotion, deliveryprice, nb, available) " + // int
            "VALUES(:idrobot,:price,:promotion,:deliveryprice,:nb,:available)", nativeQuery = true)
    void addRobot(@Param("idrobot") int idrobot,
                  @Param("price") int price,
                  @Param("promotion") int promotion,
                  @Param("deliveryprice") int deliveryprice,
                  @Param("nb") int nb,
                  @Param("available") int available);

    // retrieving a portion of the catalog rows
    @Query(value = "SELECT * FROM catalog LIMIT :number", nativeQuery = true)
    List<catalogSQL> getCatalog(@Param("number") int number);

    // retrieving a robot from the catalog rows
    // SELECT EXISTS(SELECT * FROM catalog WHERE id=?);
    @Query(value = "SELECT EXISTS(SELECT * FROM catalog c WHERE c.idrobot=:idrobot)", nativeQuery = true)
    boolean isRobotInCatalog(@Param("idrobot") int idrobot);
}