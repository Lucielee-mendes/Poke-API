package poke.api.integration.response;

import lombok.Data;

import java.util.List;

@Data
public class PokemonResponse {

    private int id;
    private String name;
    private int height;
    private int weight;

    private List<MovesResponse> moves;

    private SpritesResponse sprites;



}
