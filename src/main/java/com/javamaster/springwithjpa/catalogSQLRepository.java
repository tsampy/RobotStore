package com.javamaster.springwithjpa;

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
    int addRobot(@Param("idrobot") int idrobot,
                 @Param("price") int price,
                 @Param("promotion") int promotion,
                 @Param("deliveryprice") int deliveryprice,
                 @Param("nb") int nb,
                 @Param("available") int available);

    // retrieving a portion of the catalog rows
    @Query(value = "SELECT * FROM catalog LIMIT :number", nativeQuery = true)
    List<catalogSQL> getCatalog(@Param("number") int number);

    // retrieving the description of a specified robot
    @Query(value = "SELECT * FROM catalog c WHERE c.idrobot = :idrobot", nativeQuery = true)
    catalogSQL getDescriptionInCatalog(@Param("idrobot") int idrobot);

    // retrieving a robot from the catalog rows
    @Query(value = "SELECT EXISTS(SELECT * FROM catalog c WHERE c.idrobot = :idrobot)", nativeQuery = true)
    boolean isRobotInCatalog(@Param("idrobot") int idrobot);

    // purchasing a robot leads to decrease the number of robots in the catalog
    @Transactional
    @Modifying
    @Query(value = "UPDATE catalog SET nb = nb - :nbPurchased WHERE idrobot = :idrobot", nativeQuery = true)
    int purchaseRobot(@Param("idrobot") int idrobot,
                      @Param("nbPurchased") int nbPurchased);
}