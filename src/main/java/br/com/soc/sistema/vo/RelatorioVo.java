package br.com.soc.sistema.vo;

public class RelatorioVo extends ExameFuncionarioVo {
    private String dataRealizacaoInicial;
    private String dataRealizacaoFinal;

    public String getDataRealizacaoInicial() {
        return dataRealizacaoInicial;
    }

    public void setDataRealizacaoInicial(String dataRealizacaoInicial) {
        this.dataRealizacaoInicial = dataRealizacaoInicial;
    }

    public String getDataRealizacaoFinal() {
        return dataRealizacaoFinal;
    }

    public void setDataRealizacaoFinal(String dataRealizacaoFinal) {
        this.dataRealizacaoFinal = dataRealizacaoFinal;
    }


}