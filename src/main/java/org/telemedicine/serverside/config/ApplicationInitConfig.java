package org.telemedicine.serverside.config;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.telemedicine.serverside.entity.MedicalStaff;
import org.telemedicine.serverside.enums.Roles;
import org.telemedicine.serverside.enums.Status;
import org.telemedicine.serverside.repository.MedicalStaffRepository;

import java.time.LocalDate;
import java.util.HashSet;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {

    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(MedicalStaffRepository staffRepository) {
        return args -> {
            if (staffRepository.findAll().isEmpty()) {
                var role = new HashSet<Roles>();
                role.add(Roles.ADMIN);

                MedicalStaff staff = new MedicalStaff();
                staff.setName("Admin");
                staff.setEmail("admin@gmail.com");
                staff.setPassword(passwordEncoder.encode("admin"));
                staff.setGender(true);
                staff.setDob(LocalDate.of(2003, 7, 12));
                staff.setStatus(Status.ACTIVE);
                staff.setAddress("Trường Đại Học Cần Thơ - Cần Thơ");
                staff.setPhone("0944653870");
                staff.setStartDate(LocalDate.now());
                staff.setRoles(role);
                staff.setEnabled(true);
                staffRepository.save(staff);

                log.warn("Admin has been created with default password: admin, please change it");
                log.warn("Admin roles: " + staff.getRoles());
            }

        };
    }
}
