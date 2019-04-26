package com.robotstore.robotstore;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;
import java.util.List;

public interface clientSQLRepository extends JpaRepository<clientSQL, Long> {

    // INSERT SQL request : Spring Data repository method, annotated with @Query
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO client(login, pw, name, lastname, address, zip, city, country, mail) " +
            "VALUES(:login,:pw,:name,:lastname,:address,:zip,:city,:country,:mail)", nativeQuery = true)
    int addClient(@Param("login") String login,
                  @Param("pw") String pw,
                  @Param("name") String name,
                  @Param("lastname") String lastname,
                  @Param("address") String address,
                  @Param("zip") String zip,
                  @Param("city") String city,
                  @Param("country") String country,
                  @Param("mail") String mail);

    // retrieving a client
    @Query(value = "SELECT * FROM client c WHERE c.login=:login AND c.pw=:pw", nativeQuery = true)
    clientSQL getClient(@Param("login") String login,
                        @Param("pw") String pw);

    // test the existence of a client
    @Query(value = "SELECT EXISTS(SELECT * FROM client c WHERE c.login=:login AND c.pw=:pw)", nativeQuery = true)
    boolean clientExists(@Param("login") String login,
                         @Param("pw") String pw);

    // test the existence of a client
    @Query(value = "SELECT EXISTS(SELECT * FROM client c WHERE c.login=:login)", nativeQuery = true)
    boolean clientExists(@Param("login") String login);
}