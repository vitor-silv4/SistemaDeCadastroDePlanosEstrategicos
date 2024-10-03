package br.com.fama.PlanoEstrategico.model;

import br.com.fama.PlanoEstrategico.dto.PlanoEstrategicoDTO;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "planoEstrategico")
public class PlanoEstrategico {

    public PlanoEstrategico() {
    }

    public PlanoEstrategico(PlanoEstrategicoDTO dadosDoPlano){
        this.horizonteDePlanejamento = dadosDoPlano.horizonteDePlanejamento();
        this.nomeEmpresaOuSetor = dadosDoPlano.nomeEmpresaOuSetor();
        this.descricaoDoPlano = dadosDoPlano.descricaoDoPlano();
        this.visao = dadosDoPlano.visao();
        this.valores = dadosDoPlano.valores();
        this.perspectiva = dadosDoPlano.perspectiva();
        this.planoEstrategicoPai = dadosDoPlano.planoEstrategicoPai();
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long idPlanoEstrategico;
    private int horizonteDePlanejamento;
    private String nomeEmpresaOuSetor;
    private String descricaoDoPlano;
    private String visao;
    private String valores;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "planoEstrategicoId") //Adicionar chave estranjeira
    private List<Perspectiva> perspectiva = new ArrayList<Perspectiva>();

    @ManyToOne
    private PlanoEstrategico planoEstrategicoPai; //Caso nao exista sera null


    public Long getIdPlanoEstrategico() {
        return idPlanoEstrategico;
    }

    public void setIdPlanoEstrategico(Long idPlanoEstrategico) {
        this.idPlanoEstrategico = idPlanoEstrategico;
    }

    public int getHorizonteDePlanejamento() {
        return horizonteDePlanejamento;
    }

    public void setHorizonteDePlanejamento(int horizonteDePlanejamento) {
        this.horizonteDePlanejamento = horizonteDePlanejamento;
    }

    public String getNomeEmpresaOuSetor() {
        return nomeEmpresaOuSetor;
    }

    public void setNomeEmpresaOuSetor(String nomeEmpresaOuSetor) {
        this.nomeEmpresaOuSetor = nomeEmpresaOuSetor;
    }

    public String getDescricaoDoPlano() {
        return descricaoDoPlano;
    }

    public void setDescricaoDoPlano(String descricaoDoPlano) {
        this.descricaoDoPlano = descricaoDoPlano;
    }

    public String getVisao() {
        return visao;
    }

    public void setVisao(String visao) {
        this.visao = visao;
    }

    public String getValores() {
        return valores;
    }

    public void setValores(String valores) {
        this.valores = valores;
    }

    public List<Perspectiva> getPerspectiva() {
        return perspectiva;
    }

    public void setPerspectiva(List<Perspectiva> perspectiva) {
        this.perspectiva = perspectiva;
    }

    public PlanoEstrategico getPlanoEstrategicoPai() {
        return planoEstrategicoPai;
    }

    public void setPlanoEstrategicoPai(PlanoEstrategico planoEstrategicoPai) {
        this.planoEstrategicoPai = planoEstrategicoPai;
    }
}


