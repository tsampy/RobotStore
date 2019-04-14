package com.robotstore.robotstore;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;
import java.util.List;

public interface mediasSQLRepository extends JpaRepository<mediasSQL, Long> {

    // INSERT SQL request : Spring Data repository method, annotated with @Query
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO medias(idrobot, link, photo, caption) " +
            "VALUES(:idrobot,:link,:photo,:caption)", nativeQuery = true)
    void addLink(@Param("idrobot") int idrobot,
                  @Param("link") String link,
                  @Param("photo") boolean photo,
                  @Param("caption") String caption);

    // retrieving every medias rows for a given idrobot
    @Query(value = "SELECT * FROM medias WHERE idrobot = :idrobot", nativeQuery = true)
    List<mediasSQL> getMedia(@Param("idrobot") int idrobot);

    // DELETE SQL request : Spring Data repository method, annotated with @Query
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM medias m WHERE m.id = :id", nativeQuery = true)
    void deleteRobot(@Param("id") int id);
}