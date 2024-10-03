package br.com.fama.PlanoEstrategico.dto;

import br.com.fama.PlanoEstrategico.model.Perspectiva;
import br.com.fama.PlanoEstrategico.model.PlanoEstrategico;

import java.util.List;

public record PlanoEstrategicoDTO(int horizonteDePlanejamento,
                                  String nomeEmpresaOuSetor, String descricaoDoPlano, String visao, String valores,
                                  List<Perspectiva> perspectiva, PlanoEstrategico planoEstrategicoPai) {

    public PlanoEstrategicoDTO(PlanoEstrategico dadosDoPlano){
        this(dadosDoPlano.getHorizonteDePlanejamento(), dadosDoPlano.getNomeEmpresaOuSetor(),
                dadosDoPlano.getDescricaoDoPlano(), dadosDoPlano.getVisao(), dadosDoPlano.getValores(),
                dadosDoPlano.getPerspectiva(), dadosDoPlano.getPlanoEstrategicoPai());
    }
}
