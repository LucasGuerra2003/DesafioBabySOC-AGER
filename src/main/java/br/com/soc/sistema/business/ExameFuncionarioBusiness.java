package br.com.soc.sistema.business;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.soc.sistema.dao.examesFuncionario.ExameFuncionarioDao;
import br.com.soc.sistema.exception.BusinessException;
import br.com.soc.sistema.vo.ExameFuncionarioVo;
import br.com.soc.sistema.vo.RelatorioVo;

public class ExameFuncionarioBusiness {

	private static final String FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO = "Foi informado um caracter no lugar de um numero";
	private ExameFuncionarioDao dao;

	public ExameFuncionarioBusiness() {
		this.dao = new ExameFuncionarioDao();
	}

	public List<ExameFuncionarioVo> buscarExameFuncionarioPorCodigo(String funcionarioId) {
		List<ExameFuncionarioVo> vo = new ArrayList<>();
		try {
			Integer cod = Integer.parseInt(funcionarioId);
			vo = dao.findExamesByFuncionarioId(cod);
		}catch (NumberFormatException e) {
			throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
		}
		return vo;
	}

	public void salvarExameFuncionario(ExameFuncionarioVo exameFuncionarioVo) {
		try {
			if (exameFuncionarioVo.getDataRealizacao() == null) {
				throw new IllegalArgumentException("A data de realização nao pode ser em branco");
			}

			if (dao.findExameRegistrado(exameFuncionarioVo)) {
				System.out.println("Exame já registrado.");
				throw new BusinessException("O exame para o funcionário nesta data já está registrado.");
			}
			if (exameFuncionarioVo.getRowid() == null) {
				dao.insertExameFuncionario(exameFuncionarioVo);
			}
			else {
				dao.updateExameFuncionario(exameFuncionarioVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ExameFuncionarioVo excluirExameFuncionarioPor(String codigo) {
		try {
			Integer cod = Integer.parseInt(codigo);
			return dao.deleteByCodigo(cod);
		}catch (NumberFormatException e) {
			throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
		}
	}
	
	
	public List<RelatorioVo> buscarTodosExamesFuncinarios(){
		return dao.findAllFuncionarioExames();
	}

	public List<RelatorioVo> buscarExamesFuncinariosPorData(String dataRealizacaoInicial, String dataRealizacaoFinal){
		return dao.findAllFuncionarioExamesPorData(dataRealizacaoInicial,dataRealizacaoFinal);
	}
}