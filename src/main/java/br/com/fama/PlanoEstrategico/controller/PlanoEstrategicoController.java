package br.com.fama.PlanoEstrategico.controller;

import br.com.fama.PlanoEstrategico.dto.DadosDetalhamentoPlano;
import br.com.fama.PlanoEstrategico.model.PlanoEstrategico;
import br.com.fama.PlanoEstrategico.dto.PlanoEstrategicoDTO;
import br.com.fama.PlanoEstrategico.repository.PlanoEstrategicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("/planos")
public class PlanoEstrategicoController {

    @Autowired
    private PlanoEstrategicoRepository repository;

    @PostMapping
    public ResponseEntity criarPlano(@RequestBody PlanoEstrategicoDTO dto, UriComponentsBuilder uriComponentsBuilder){
        var planoEstrategico = new PlanoEstrategico(dto);
        repository.save(planoEstrategico);

        var uri = uriComponentsBuilder.path("/planos/{id}").buildAndExpand(planoEstrategico.getIdPlanoEstrategico()).toUri(); //para coletar o id do plano criado

        return ResponseEntity.created(uri).body(new DadosDetalhamentoPlano(planoEstrategico));//posso selecionar o que retornar no corpo da resposta com DadosDetalhamento
    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoPlano>> listarPlanos(@PageableDefault(size = 10, sort = {"idPlanoEstrategico"}) Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosDetalhamentoPlano::new); //lambda equivalente - (planoEstrategico) -> new DadosDetalhamentoPlano(planoEstrategico)
        return ResponseEntity.ok(page);
    }

    @DeleteMapping("/{idPlanoEstrategico}")
    public ResponseEntity ecluirPlano(@PathVariable Long idPlanoEstrategico){
        Optional<PlanoEstrategico> planoEstrategicoOpcional = repository.findById(idPlanoEstrategico);
        if (planoEstrategicoOpcional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(idPlanoEstrategico);
        return ResponseEntity.ok().build(); //apenas e repondido 200 ok pode ser pensado um corpo de reposta apos a exclusao
    }

    @PutMapping("/{idPlanoEstrategico}")
    public ResponseEntity editarPlanoEstrategico(@PathVariable Long idPlanoEstrategico, @RequestBody PlanoEstrategicoDTO dto){
        var planoEstrategicoOptional = repository.findById(idPlanoEstrategico);

        if(planoEstrategicoOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        PlanoEstrategico planoEstrategico = planoEstrategicoOptional.get();

        if (dto.horizonteDePlanejamento() != 0) {
            planoEstrategico.setHorizonteDePlanejamento(dto.horizonteDePlanejamento());
        }
        if (dto.nomeEmpresaOuSetor() != null) {
            planoEstrategico.setNomeEmpresaOuSetor(dto.nomeEmpresaOuSetor());
        }
        if (dto.descricaoDoPlano() != null) {
            planoEstrategico.setDescricaoDoPlano(dto.descricaoDoPlano());
        }
        if (dto.visao() != null) {
            planoEstrategico.setVisao(dto.visao());
        }
        if (dto.valores() != null) {
            planoEstrategico.setValores(dto.valores());
        }
        if (dto.perspectiva() != null) {
            planoEstrategico.setPerspectiva(dto.perspectiva());
        }
        if (dto.planoEstrategicoPai() != null) {
            planoEstrategico.setPlanoEstrategicoPai(dto.planoEstrategicoPai());
        }

        repository.save(planoEstrategico);
        return ResponseEntity.ok().body(new DadosDetalhamentoPlano(planoEstrategico));
    }










}
