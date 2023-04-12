package com.pokemonreview.api.service;

import com.pokemonreview.api.dto.PokemonDto;
import java.util.List;

public interface PokemonSerivce {

    PokemonDto createPokemon(PokemonDto pokemonDto);

    List<PokemonDto> getAllPokemon();

    PokemonDto findPokemon(int id);

    PokemonDto updatePokemon(PokemonDto pokemonDto, int id);

    void deletePokemon(int id);
}
