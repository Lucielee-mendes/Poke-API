package poke.api.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import poke.api.controller.PokemonController;
import poke.api.model.Pokemon;
import poke.api.repository.PokemonRepository;

import java.util.List;

@Service
public class PokemonService {

    private PokemonRepository pokemonRepository;

    public  PokemonService(PokemonRepository pokemonRepository){
        this.pokemonRepository = pokemonRepository;
    }
    public List<Pokemon> buscarTodos(){
        return pokemonRepository.findAll();
    }

    public Pokemon buscarPeloNome(String nome){
        return pokemonRepository.findByNome(nome);
    }

    public boolean existePokemon(Long id) {
        return pokemonRepository.existsById(id);
    }

    public Pokemon alterarNomeETipo(Long id, Pokemon pokemon) {
        if (existePokemon(id)) {
            Pokemon pokemonParaAlterar = buscarPorId(id);

            pokemonParaAlterar.setNome(pokemon.getNome());
            pokemonParaAlterar.setTipo(pokemon.getTipo());
            pokemonRepository.save(pokemonParaAlterar);

            return pokemonParaAlterar;
        }
        return null;
    }

    public Pokemon buscarPorId(Long id) {
        return pokemonRepository.findById(id).orElse(null);
    }

    public void adicionar(Pokemon pokemon){
        pokemonRepository.save(pokemon);
    }

    public Pokemon removerPorId(Long id){
        Pokemon pokemonParaRemover = pokemonRepository.findById(id).get();
        pokemonRepository.delete(pokemonParaRemover);
       return pokemonParaRemover;
    }

    public Pokemon removerPorNome(String nome){
        Pokemon pokemonRemoverPorNome = pokemonRepository.findByNome(nome);
        pokemonRepository.delete(pokemonRemoverPorNome);
        return pokemonRemoverPorNome;
    }


}
