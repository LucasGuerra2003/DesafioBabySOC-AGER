package br.com.soc.sistema.action.relatorioExames;

import java.util.ArrayList;
import java.util.List;
import br.com.soc.sistema.business.ExameFuncionarioBusiness;
import br.com.soc.sistema.infra.Action;
import br.com.soc.sistema.vo.RelatorioVo;

public class RelatorioExamesAction extends Action {
    public List<RelatorioVo> relatorios = new ArrayList<>();
    private ExameFuncionarioBusiness business = new ExameFuncionarioBusiness();
    public RelatorioVo relatorioVo = new RelatorioVo();
    
    public String todo() {
        return SUCCESS;
    }

    public String gerar() {
        if (relatorioVo.getDataRealizacaoInicial() == null || relatorioVo.getDataRealizacaoFinal() == null) {
            return REDIRECT;
        }

        relatorios.addAll(business.buscarExamesFuncinariosPorData(
            relatorioVo.getDataRealizacaoInicial(),
            relatorioVo.getDataRealizacaoFinal()
        ));

        return SUCCESS;
    }

    public List<RelatorioVo> getRelatorios() {
        return relatorios;
    }

    public void setRelatorios(List<RelatorioVo> relatorios) {
        this.relatorios = relatorios;
    }

    public void setRelatorioVo(RelatorioVo relatorioVo) {
        this.relatorioVo = relatorioVo;
    }

    public RelatorioVo getRelatorioVo() {
        return relatorioVo;
    }

}
