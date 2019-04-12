package com.robotstore.robotstore;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;

public interface robotSQLRepository extends JpaRepository<robotSQL, Long> {

    // DELETE SQL request : Spring Data repository method, annotated with @Query
    //   => table name is <robots r>
    //   => id is the named parameter <:robotID>
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM robots r WHERE r.id = :robotID", nativeQuery = true)
    void deleteRobot(@Param("robotID") int robot_ID);

    // INSERT SQL request : Spring Data repository method, annotated with @Query
    //   => table name is <robots>
    //   => table column is <info>
    //   => value is the named parameter <:robot>
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO robots(info) VALUES(:robot)", nativeQuery = true)
    void addRobot(@Param("robot") String robot);
}