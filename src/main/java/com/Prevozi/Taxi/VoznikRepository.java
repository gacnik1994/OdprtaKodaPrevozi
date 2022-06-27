package com.Prevozi.Taxi;


import org.springframework.data.repository.CrudRepository;

public interface VoznikRepository extends CrudRepository <Voznik, Integer>{
    public Integer countById(Integer id);
}
