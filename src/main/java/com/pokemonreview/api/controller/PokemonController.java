package com.pokemonreview.api.controller;

import com.pokemonreview.api.model.Pokemon;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PokemonController {

    @GetMapping("pokemon")
    public ResponseEntity<List<Pokemon>> getPokemons() {
        List<Pokemon> pokemons = new ArrayList<>();

        pokemons.add(new Pokemon(1, "sq", "water"));
        pokemons.add(new Pokemon(1, "Pi", "electric"));
        pokemons.add(new Pokemon(1, "Char", "fire"));

        return ResponseEntity.ok(pokemons);
    }


}
