package poke.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poke.api.model.Pokemon;
import poke.api.service.PokemonService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pokemons")
public class PokemonController {

    private PokemonService pokemonService;

    public PokemonController (PokemonService pokemonService){
        this.pokemonService = pokemonService;
    }

    @GetMapping
    public ResponseEntity<List<Pokemon>> buscarTodosPokemons(){
       return ResponseEntity.ok(this.pokemonService.buscarTodos());
    }

    @GetMapping({"/nome"})
    public ResponseEntity<Pokemon> buscarPokemonPeloNome(String nome){
        Pokemon pokemonBuscado = this.pokemonService.buscarPeloNome(nome);
        return ResponseEntity.ok(pokemonBuscado);
    }

    @PostMapping
    public ResponseEntity<Void> adicionarPokemon(@RequestBody Pokemon pokemon){
        pokemonService.adicionar(pokemon);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pokemon> removerPokemonPorId(@PathVariable("id")Long id ){
      Pokemon pokemonremovido =  pokemonService.removerPorId(id);
        return ResponseEntity.ok(pokemonremovido);
    }

    @DeleteMapping("/nome/{nomePokemon}")
    public ResponseEntity<Pokemon> removerPokemonPorNome(@PathVariable("nome")String nome ){
        Pokemon pokemonremovidoNome =  pokemonService.removerPorNome(nome);
        return ResponseEntity.ok(pokemonremovidoNome);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> alterarNomeTipo(@PathVariable Long id, @RequestBody Pokemon pokemonAtualizado) {
        if (pokemonService.existePokemon(id)) {
            Pokemon pokemonExistente = pokemonService.buscarPorId(id);

            pokemonExistente.setNome(pokemonAtualizado.getNome());
            pokemonExistente.setTipo(pokemonAtualizado.getTipo());

            pokemonService.adicionar(pokemonExistente);

            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    }
