package br.com.soc.sistema.vo;

public class ExameFuncionarioVo {
	private String rowid;
    private String funcionarioId;          
    private String exameId;         
    private String nmFuncionario; 
    private String nmExame; 
    private String dataRealizacao; 
    
    public ExameFuncionarioVo() {}

    public ExameFuncionarioVo(String funcionarioId, String exameId) {
        this.funcionarioId = funcionarioId;
        this.exameId = exameId;
    }

    public String getFuncionarioId() {
        return funcionarioId;
    }

    public void setFuncionarioId(String funcionarioId) {
        this.funcionarioId = funcionarioId;
    }

    public String getExameId() {
        return exameId;
    }

    public void setExameId(String exameId) {
        this.exameId = exameId;
    }

    public String getNmFuncionario() {
        return nmFuncionario;
    }

    public void setNmFuncionario(String nmFuncionario) {
        this.nmFuncionario = nmFuncionario;
    }

    public String getNmExame() {
        return nmExame;
    }

    public void setNmExame(String nmExame) {
        this.nmExame = nmExame;
    }

    public String getDataRealizacao() {
        return dataRealizacao;
    }

    public void setDataRealizacao(String dataRealizacao) {
        this.dataRealizacao = dataRealizacao;
    }
	public String getRowid() {
		return rowid;
	}

	public void setRowid(String rowid) {
		this.rowid = rowid;
	}

    @Override
    public String toString() {
        return "ExameFuncionarioVo [funcionarioId=" + funcionarioId + ", exameId=" + exameId + 
               ", nmFuncionario=" + nmFuncionario + ", nmExame=" + nmExame + 
               ", dataRealizacao=" + dataRealizacao + "]";
    }


}