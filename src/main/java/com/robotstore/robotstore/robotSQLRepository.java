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
    /*
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM catalog c WHERE c.id = :robotID", nativeQuery = true)
    void deleteRobot(@Param("robotID") int robot_ID);
    */

    // INSERT SQL request : Spring Data repository method, annotated with @Query
    //   => table name is <robots>
    //   => table column is <info>
    //   => value is the named parameter <:robot>
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO robots(name, type, manufacturer, productcode, description," + // String
                "batterylife, depth, height, width, weight," + // int
                "wifi, bluetooth, usb) " + // boolean
            "VALUES(:name, :type, :manufacturer, :productcode, :description," +
                ":batterylife, :depth, :height, :width, :weight," +
                ":wifi, :bluetooth, :usb)", nativeQuery = true)
    void addRobot(@Param("name") String name,
                  @Param("type") String type,
                  @Param("manufacturer") String manufacturer,
                  @Param("productcode") String productcode,
                  @Param("description") String description,
                  @Param("batterylife") int batterylife,
                  @Param("depth") int depth,
                  @Param("height") int height,
                  @Param("width") int width,
                  @Param("weight") int weight,
                  @Param("wifi") boolean wifi,
                  @Param("bluetooth") boolean bluetooth,
                  @Param("usb") boolean usb);
}