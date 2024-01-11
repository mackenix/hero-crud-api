package com.heroes.hero.controller;

import com.heroes.hero.dto.HeroDto;
import com.heroes.hero.dto.HeroResponse;
import com.heroes.hero.model.Hero;
import com.heroes.hero.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/")
public class Controller {
    private final HeroService heroService;

    @Autowired
    public Controller(HeroService heroService) {
        Assert.notNull(heroService, "The heroService cannot be null!");
        this.heroService = heroService;
    }

    @GetMapping("/")
    public String hello(){
        return "Hero CRUD Service v1.0.0";
    }

    @GetMapping("/hero")
    public ResponseEntity<HeroResponse> getHeroes(@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pagNo,
                                                  @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize){

      return new ResponseEntity<>(heroService.getAllHeroes(pagNo, pageSize), HttpStatus.OK);
    }

    @GetMapping("/hero/{id}")
    public ResponseEntity<HeroDto> getHeroDetails(@PathVariable int id){

        return new ResponseEntity<>(heroService.getHeroById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<HeroDto> createHero(@RequestBody HeroDto heroDto){

        return new ResponseEntity<>(heroService.createHero(heroDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<HeroDto> updateHero(@RequestBody HeroDto hero, @PathVariable("id") int id){

        return new ResponseEntity<>(heroService.updateHero(hero, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteHero(@PathVariable int id){

        heroService.deleteHero(id);
        return new ResponseEntity<>("Hero was deleted successfully", HttpStatus.OK);
    }
}
