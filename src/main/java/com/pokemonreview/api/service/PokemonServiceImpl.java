package com.pokemonreview.api.service;

import com.pokemonreview.api.dto.PokemonDto;
import com.pokemonreview.api.exception.PokemonNotFoundException;
import com.pokemonreview.api.model.Pokemon;
import com.pokemonreview.api.repository.PokemonRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PokemonServiceImpl implements PokemonSerivce{

    private final PokemonRepository pokemonRepository;

    @Override
    public PokemonDto createPokemon(PokemonDto pokemonDto) {
        Pokemon pokemon = new Pokemon(pokemonDto.getId(), pokemonDto.getName(),
            pokemonDto.getType());
        Pokemon p = pokemonRepository.save(pokemon);
        return new PokemonDto(p.getId(), p.getName(), p.getType());
    }

    @Override
    public List<PokemonDto> getAllPokemon() {
        List<Pokemon> pokemonList = pokemonRepository.findAll();
        return pokemonList.stream().map(pokemon -> mapToDto(pokemon)).toList();
    }

    @Override
    public PokemonDto findPokemon(int id) {
        Pokemon pokemon = pokemonRepository.findById(id).orElseThrow(() ->
            new PokemonNotFoundException("Pokemon could not be found by id"));
        return this.mapToDto(pokemon);
    }

    @Override
    public PokemonDto updatePokemon(PokemonDto pokemonDto, int id) {
        Pokemon pokemon = pokemonRepository.findById(id).orElseThrow(() ->
            new PokemonNotFoundException("Pokemon could not be found by id"));
        Pokemon pokemonUpdate = pokemonRepository.save(
            new Pokemon(pokemonDto.getId(), pokemonDto.getName(), pokemonDto.getType()));
        return this.mapToDto(pokemonUpdate);
    }

    @Override
    public void deletePokemon(int id) {
        pokemonRepository.deleteById(id);
    }

    private PokemonDto mapToDto(Pokemon pokemon) {
        PokemonDto pokemonDto = new PokemonDto(pokemon.getId(), pokemon.getName(),
            pokemon.getType());
        return pokemonDto;
    }

    private Pokemon mapToEntity(PokemonDto pokemonDto) {
        Pokemon pokemon = new Pokemon(pokemonDto.getId(), pokemonDto.getName(),
            pokemonDto.getType());
        return pokemon;
    }
}
