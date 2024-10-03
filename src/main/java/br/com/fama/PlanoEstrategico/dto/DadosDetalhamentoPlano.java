package br.com.fama.PlanoEstrategico.dto;

import br.com.fama.PlanoEstrategico.model.Perspectiva;
import br.com.fama.PlanoEstrategico.model.PlanoEstrategico;

import java.util.List;

public record DadosDetalhamentoPlano(Long idPlanoEstrategico, int horizonteDePlanejamento,
                                     String nomeEmpresaOuSetor, String descricaoDoPlano, String visao, String valores,
                                     List<Perspectiva> perspectiva, PlanoEstrategico planejamentoEstrategicoPai) {

    public DadosDetalhamentoPlano(PlanoEstrategico dadosDoPlano){
        this(dadosDoPlano.getIdPlanoEstrategico(), dadosDoPlano.getHorizonteDePlanejamento(),
                dadosDoPlano.getNomeEmpresaOuSetor(), dadosDoPlano.getDescricaoDoPlano(), dadosDoPlano.getVisao(),
                dadosDoPlano.getValores(), dadosDoPlano.getPerspectiva(), dadosDoPlano.getPlanoEstrategicoPai());
    }
}
