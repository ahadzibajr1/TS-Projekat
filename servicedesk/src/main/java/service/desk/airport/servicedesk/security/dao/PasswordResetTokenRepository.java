package service.desk.airport.servicedesk.security.dao;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import service.desk.airport.servicedesk.security.entity.PasswordResetToken;
import service.desk.airport.servicedesk.security.entity.User;

import java.time.LocalDateTime;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Integer> {

    public PasswordResetToken findByToken(String token);

    @Transactional
    @Modifying
    @Query("DELETE FROM PasswordResetToken t WHERE (t.expiryDate < :currentDateAndTime)")
    void deleteExpiredTokens(@Param("currentDateAndTime") LocalDateTime currentDateAndTime);


    @Transactional
    @Modifying
    @Query("DELETE FROM PasswordResetToken t WHERE t.user = :user")
    void deleteByUser(@Param("user") User user);
}
