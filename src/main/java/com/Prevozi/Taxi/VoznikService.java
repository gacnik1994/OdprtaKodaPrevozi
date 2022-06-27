package com.Prevozi.Taxi;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoznikService {
    @Autowired private VoznikRepository repo;

    public List<Voznik> listAll(){
        return (List<Voznik>) repo.findAll();
    }
    public void save(Voznik voznik){
        repo.save(voznik);
    }

    public Voznik get(int id) throws  VoznikNotFoundException{
        Optional<Voznik> byId = repo.findById(id);
        if(byId.isPresent()){
            return byId.get();
        }
        throw new VoznikNotFoundException("Nismo našli voznikov z Id: "+ id);
    }

    public void delete(int id){
        //int count = 0;
       // if (count == null || count == 0){
          //  throw new VoznikNotFoundException("Nismo našli voznikov z Id: "+ id);
        //}
        repo.deleteById(id);
    }
}
