package edu.tcu.cs.backend.system;

import edu.tcu.cs.backend.admin.Admin;
import edu.tcu.cs.backend.admin.AdminRepository;
import edu.tcu.cs.backend.crew.CrewMember;
import edu.tcu.cs.backend.crew.CrewMemberRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBDataInitializer implements CommandLineRunner {

    private final AdminRepository adminRepository;

    private final CrewMemberRepository crewMemberRepository;

    public DBDataInitializer(AdminRepository adminRepository, CrewMemberRepository crewMemberRepository) {
        this.adminRepository = adminRepository;
        this.crewMemberRepository = crewMemberRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Admin a1 = new Admin();
        a1.setFirstName("Olivia");
        a1.setLastName("Banks");
        adminRepository.save(a1);

        Admin a2 = new Admin();
        a2.setFirstName("Olivia");
        a2.setLastName("Banks");
        adminRepository.save(a2);

        CrewMember c1 = new CrewMember();
        c1.setFirstName("Evan");
        c1.setLastName("Eissler");
        crewMemberRepository.save(c1);

        CrewMember c2 = new CrewMember();
        c2.setFirstName("Evan");
        c2.setLastName("Eissler");
        crewMemberRepository.save(c2);


    }
}
