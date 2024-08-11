package br.com.soc.sistema.action.exameFuncionarios;

import java.util.ArrayList;
import java.util.List;

import br.com.soc.sistema.business.ExameBusiness;
import br.com.soc.sistema.business.ExameFuncionarioBusiness;
import br.com.soc.sistema.infra.Action;
import br.com.soc.sistema.vo.ExameFuncionarioVo;
import br.com.soc.sistema.vo.ExameVo;

public class ExameFuncionarioAction extends Action {
	private List<ExameFuncionarioVo> examesFuncionarios = new ArrayList<>();
	private ExameFuncionarioBusiness business = new ExameFuncionarioBusiness();
	private ExameBusiness exameBusiness = new ExameBusiness(); 
	private List<ExameVo> todosExames = new ArrayList<>(); 
	private ExameFuncionarioVo exameFuncionarioVo = new ExameFuncionarioVo();


	public String buscar() {
		examesFuncionarios = business.buscarExameFuncionarioPorCodigo(exameFuncionarioVo.getFuncionarioId());

		return SUCCESS;
	}

	public String novo() {
		business.salvarExameFuncionario(exameFuncionarioVo);

		return REDIRECT;
	}

	public String todos() {
		todosExames = exameBusiness.trazerTodosOsExames();
		if (exameFuncionarioVo.getRowid() == null) {
			return INPUT; 
		}
		else {
			return EDIT;
		}
	}


	public String editar() {
		business.salvarExameFuncionario(exameFuncionarioVo);

		return REDIRECT;
	}

	public String excluir() {
		business.excluirExameFuncionarioPor(exameFuncionarioVo.getRowid());

		return REDIRECT;
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

	// Getters e Setters
	public List<ExameVo> getTodosExames() {
		return todosExames;
	}

	public void setTodosExames(List<ExameVo> todosExames) {
		this.todosExames = todosExames;
	}

}