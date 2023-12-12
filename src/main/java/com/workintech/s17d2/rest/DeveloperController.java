package com.workintech.s17d2.rest;

import com.workintech.s17d2.dto.DeveloperResponse;
import com.workintech.s17d2.model.Developer;
import com.workintech.s17d2.model.DeveloperFactory;
import com.workintech.s17d2.tax.Taxable;
import com.workintech.s17d2.validation.DeveloperValidation;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/developers")
public class DeveloperController {
    private Map<Integer, Developer> developers;
    private Taxable taxable;
    @PostConstruct
    public void init(){
        developers = new HashMap<>();
    }
    @Autowired
    public DeveloperController(Taxable taxable) {
        this.taxable = taxable;
    }
    @GetMapping("/")
    public List<Developer> readAllDevelopers(){
        return developers.values().stream().toList();
    }
    @GetMapping("/{id}")
    public DeveloperResponse readDeveloper(@PathVariable int id){
        if(DeveloperValidation.isExist(developers, id)){
           return new DeveloperResponse(developers.get(id),"Successful", 200);
        }
        return new DeveloperResponse(null, "Developer is not exist", 404);
    }
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public DeveloperResponse saveDeveloper(@RequestBody Developer developer){
        if(DeveloperValidation.isExist(developers, developer.getId())){
            return new DeveloperResponse(null, "Developer already exist.", 404);
        }
        Developer saved = DeveloperFactory.create(developer, taxable);
        if(saved != null) {
            developers.put(saved.getId(), saved);
        }
        return new DeveloperResponse(developers.get(saved.getId()),"Successful", 200);
    }
    @PutMapping("/{id}")
    public DeveloperResponse updateDeveloper(@PathVariable int id, @RequestBody Developer developer){
        //guard
        if(!DeveloperValidation.isExist(developers, id)){
            return new DeveloperResponse(null, "Developer is not exist.", 404);
        }
        developer.setId(id);
        Developer updated = DeveloperFactory.create(developer, taxable);
        developers.put(id,updated);
        return new DeveloperResponse(updated,"Successful", 200);
    }
    @DeleteMapping("/{id}")
    public Developer deleteDeveloper(@PathVariable int id){
        return developers.remove(id);
    }
}
