package br.com.soc.sistema.action.relatorioExames;

import java.util.ArrayList;
import java.util.List;

import br.com.soc.sistema.business.ExameFuncionarioBusiness;
import br.com.soc.sistema.infra.Action;
import br.com.soc.sistema.vo.ExameFuncionarioVo;

public class RelatorioExamesAction extends Action {
	private List<ExameFuncionarioVo> examesFuncionarios = new ArrayList<>();
	private ExameFuncionarioBusiness business = new ExameFuncionarioBusiness();
	private ExameFuncionarioVo exameFuncionarioVo = new ExameFuncionarioVo();


	public String todo() {
		examesFuncionarios.addAll(business.buscarTodosExamesFuncinarios());

		return SUCCESS;
	}

	public List<ExameFuncionarioVo> getExamesFuncionarios() {
		return examesFuncionarios;
	}

	public void setExamesFuncionarios(List<ExameFuncionarioVo> examesFuncionarios) {
		this.examesFuncionarios = examesFuncionarios;
	}


	public ExameFuncionarioVo getExameFuncionarioVo() {
		return exameFuncionarioVo;
	}

	public void setExameFuncionarioVo(ExameFuncionarioVo exameFuncionarioVo) {
		this.exameFuncionarioVo = exameFuncionarioVo;
	}

}