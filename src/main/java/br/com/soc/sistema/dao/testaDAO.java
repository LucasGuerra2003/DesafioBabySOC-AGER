package br.com.soc.sistema.dao;

import java.util.List;

import br.com.soc.sistema.business.ExameFuncionarioBusiness;
import br.com.soc.sistema.business.FuncionarioBusiness;
import br.com.soc.sistema.vo.ExameFuncionarioVo;

public class testaDAO {

    public static void main(String[] args) {
        ExameFuncionarioBusiness business = new ExameFuncionarioBusiness();
        FuncionarioBusiness funcionarioBusiness = new FuncionarioBusiness();
        ExameFuncionarioVo exameFuncionarioVo = new ExameFuncionarioVo();
        
        exameFuncionarioVo.setFuncionarioId("5"); 
		exameFuncionarioVo.setRowid("1");
		exameFuncionarioVo.setExameId("2");
		exameFuncionarioVo.setDataRealizacao("10-06-2024");


        /*ExameFuncionarioVo novoExameFuncionario = new ExameFuncionarioVo();

        novoExameFuncionario.setFuncionarioId("2"); 
        novoExameFuncionario.setExameId("2");
        novoExameFuncionario.setDataRealizacao("10-06-2024");

        System.out.println("Dados a serem salvos:");
        System.out.println("Funcionario ID: " + novoExameFuncionario.getFuncionarioId());
        System.out.println("Exame ID: " + novoExameFuncionario.getExameId());
        System.out.println("Data de Realização: " + novoExameFuncionario.getDataRealizacao());
        System.out.println("-----------------------------");

        business.salvarExameFuncionario(novoExameFuncionario);
*/
                
        System.out.println("Exames do Funcionário antes da Edição:");
        List<ExameFuncionarioVo> examesFuncionarios = business.buscarExameFuncionarioPorCodigo("1");
        for (ExameFuncionarioVo vo : examesFuncionarios) {
        	System.out.println("ID do Exame do Funcionario: " + vo.getRowid());
            System.out.println("Exame ID: " + vo.getExameId());
            System.out.println("Funcionario ID: " + vo.getFuncionarioId());
            System.out.println("Data de Realização: " + vo.getDataRealizacao());
            System.out.println("-----------------------------");
        }
        
        business.salvarExameFuncionario(exameFuncionarioVo);

		System.out.println("Exames do Funcionário após a Edição:");
		examesFuncionarios = business.buscarExameFuncionarioPorCodigo("1");
		for (ExameFuncionarioVo vo : examesFuncionarios) {
			System.out.println("ID do Exame do Funcionario: " + vo.getRowid());
			System.out.println("Exame ID: " + vo.getExameId());
			System.out.println("Funcionario ID: " + vo.getFuncionarioId());
			System.out.println("Data de Realização: " + vo.getDataRealizacao());
			System.out.println("-----------------------------");
		}
        
        /*
        String codigoParaExcluir = "5";
        business.excluirExameFuncionarioPor(codigoParaExcluir);
        
        System.out.println("Exames do Funcionário após a Exclusão:");
        examesFuncionarios = business.buscarExameFuncionarioPorCodigo("2");
        for (ExameFuncionarioVo vo : examesFuncionarios) {
        	System.out.println("ID do Exame do Funcionario: " + vo.getRowid());
            System.out.println("Exame ID: " + vo.getExameId());
            System.out.println("Funcionario ID: " + vo.getFuncionarioId());
            System.out.println("Data de Realização: " + vo.getDataRealizacao());
            System.out.println("-----------------------------");
        }
        */
       
    }
}
