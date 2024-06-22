package dev.dhanush.FileSharing.Repository;

import dev.dhanush.FileSharing.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role, Long> {
}
