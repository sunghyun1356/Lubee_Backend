package com.Lubee.Lubee.profile.repository;

import com.Lubee.Lubee.profile.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findByProfileId(Long profileId);
}
