package com.pokemonreview.api.controller;

import com.pokemonreview.api.dto.PokemonDto;
import com.pokemonreview.api.model.Pokemon;
import com.pokemonreview.api.service.PokemonSerivce;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class PokemonController {

    private final PokemonSerivce pokemonSerivce;


    @GetMapping("pokemon")
    public ResponseEntity<List<PokemonDto>> getPokemons() {
        return ResponseEntity.ok().body(pokemonSerivce.getAllPokemon());
    }

    @GetMapping("pokemon/{id}")
    public ResponseEntity<?> pokemonDetail(@PathVariable int id) {
        return ResponseEntity.ok().body(pokemonSerivce.findPokemon(id));

    }

    @PostMapping("pokemon/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createPokemon(@RequestBody PokemonDto pokemonDto) {
        return ResponseEntity.created(URI.create("/pokemon/" + pokemonDto.getId()))
            .body(pokemonSerivce.createPokemon(pokemonDto));
    }

    @PutMapping("pokemon/{id}/update")
    public ResponseEntity<?> updatePokemon(@RequestBody PokemonDto pokemonDto, @PathVariable("id") int pokemonId) {
        return ResponseEntity.ok().body(pokemonSerivce.updatePokemon(pokemonDto, pokemonId));
    }

    @DeleteMapping("pokemon/{id}/delete")
    public ResponseEntity deletePokemon(@PathVariable("id") int pokemonId) {
        pokemonSerivce.deletePokemon(pokemonId);
        return ResponseEntity.ok().body(pokemonId + " delete");
    }
}
