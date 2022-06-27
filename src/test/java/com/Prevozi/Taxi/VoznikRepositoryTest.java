package com.Prevozi.Taxi;



import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;




@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class VoznikRepositoryTest {
    @Autowired private VoznikRepository repo;



    @Test
    public void testAddNew(){
        Voznik user = new Voznik();
        user.setIme("Stane");
        user.setPriimek("Kranjc");

        Voznik shranjenVoznik = repo.save(user);

      Assertions.assertThat(shranjenVoznik).isNotNull();
      Assertions.assertThat(shranjenVoznik.getId()).isGreaterThan(0);

    }

    @Test
    public void testListAll(){
        Iterable<Voznik> vozniki = repo.findAll();
        Assertions.assertThat(vozniki).hasSizeGreaterThan(0);

        for(Voznik voznik : vozniki){
            System.out.println(voznik);
        }
    }

    @Test
    public void testUpdate(){

        int userId = 3;
        Optional<Voznik> optionalUser = repo.findById(userId);
        Voznik voznik = optionalUser.get();
        voznik.setIme("Tone");
        repo.save(voznik);

        Voznik updateVoznik = repo.findById(userId).get();
        Assertions.assertThat(updateVoznik.getIme()).isEqualTo("Tone");
    }

    @Test
    public void testGet(){
        Integer userId = 2;
        Optional<Voznik> optionalVoznik = repo.findById(userId);
        Assertions.assertThat(optionalVoznik).isPresent();
        System.out.println(optionalVoznik.get());


    }

    @Test
    public void testDelete(){
        Integer userId = 3;
        repo.deleteById(userId);
        Optional<Voznik> optionalVoznik = repo.findById(userId);
        Assertions.assertThat(optionalVoznik).isNotPresent();
    }


}
