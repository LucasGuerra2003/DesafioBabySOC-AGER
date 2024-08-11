package br.com.soc.sistema.dao.examesFuncionario;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.soc.sistema.dao.Dao;
import br.com.soc.sistema.vo.ExameFuncionarioVo;

public class ExameFuncionarioDao extends Dao {

	public void insertExameFuncionario(ExameFuncionarioVo exameFuncionarioVo) {
	    StringBuilder query = new StringBuilder("INSERT INTO funcionario_exame (funcionario_id, exame_id, data_realizacao) VALUES (?, ?, ?)");
	    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	    java.util.Date dataUtil = null;

	    try {
	        dataUtil = sdf.parse(exameFuncionarioVo.getDataRealizacao());
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }

	    java.sql.Date sqlDate = (dataUtil != null) ? new java.sql.Date(dataUtil.getTime()) : null;

	    try (
	        Connection con = getConexao();
	        PreparedStatement ps = con.prepareStatement(query.toString())
	    ) {
	        ps.setString(1, exameFuncionarioVo.getFuncionarioId());
	        ps.setString(2, exameFuncionarioVo.getExameId());
	        ps.setDate(3, sqlDate);

	        ps.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new RuntimeException("Erro ao inserir o exame do funcionário", e);
	    }
	}


	public ExameFuncionarioVo updateExameFuncionario(ExameFuncionarioVo exameFuncionarioVo) {
	    StringBuilder query = new StringBuilder("UPDATE funcionario_exame SET exame_id = ?, data_realizacao = ? ")
	            .append("WHERE rowid = ?");
	    
	    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); 
	    java.util.Date dataUtil = null;
	    

	    try {
	        dataUtil = sdf.parse(exameFuncionarioVo.getDataRealizacao());
	    } catch (ParseException e) {
	        e.printStackTrace();
	        throw new RuntimeException("Erro ao converter a data", e);
	    }

	    java.sql.Date sqlDate = (dataUtil != null) ? new java.sql.Date(dataUtil.getTime()) : null;


	    try (Connection con = getConexao();
	         PreparedStatement ps = con.prepareStatement(query.toString())) {

	        ps.setString(1, exameFuncionarioVo.getExameId());
	        ps.setDate(2, sqlDate);
	        ps.setString(3, exameFuncionarioVo.getRowid());

	        ps.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return exameFuncionarioVo; 
	}

	 

	public ExameFuncionarioVo deleteByCodigo(Integer id) {
		StringBuilder query = new StringBuilder("DELETE FROM funcionario_exame ")
				.append("WHERE rowid = ?");

		try (	Connection con = getConexao();
				PreparedStatement ps = con.prepareStatement(query.toString())) {
			ps.setInt(1, id);
			ps.execute();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return null;
	}

	public List<ExameFuncionarioVo> findExamesByFuncionarioId(Integer funcionarioId) {
	    StringBuilder query = new StringBuilder("SELECT fe.rowid AS funcionario_exame_id, e.rowid AS exame_id, e.nm_exame, fe.data_realizacao, f.nm_funcionario ")
	            .append("FROM funcionario_exame fe ")
	            .append("INNER JOIN exame e ON fe.exame_id = e.rowid ")
	            .append("INNER JOIN funcionario f ON fe.funcionario_id = f.rowid ")
	            .append("WHERE fe.funcionario_id = ?");

	    List<ExameFuncionarioVo> examesFuncionarios = new ArrayList<>();
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 

	    try (Connection con = getConexao();
	         PreparedStatement ps = con.prepareStatement(query.toString())) {

	        ps.setInt(1, funcionarioId);
	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                ExameFuncionarioVo vo = new ExameFuncionarioVo();
	                vo.setRowid(rs.getString("funcionario_exame_id")); 
	                vo.setExameId(rs.getString("exame_id"));
	                vo.setNmExame(rs.getString("nm_exame"));
	                vo.setNmFuncionario(rs.getString("nm_funcionario"));
	                vo.setFuncionarioId("" + funcionarioId);

	                Date sqlDate = rs.getDate("data_realizacao");
	                if (sqlDate != null) {
	                    vo.setDataRealizacao(sdf.format(sqlDate));
	                }

	                examesFuncionarios.add(vo);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return examesFuncionarios;
	}

	public boolean findExameRegistrado(ExameFuncionarioVo exameFuncionarioVo) {
	    StringBuilder query = new StringBuilder("SELECT 1 FROM funcionario_exame ")
	            .append("WHERE funcionario_id = ? AND exame_id = ? AND data_realizacao = ?");
	    
	    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); 
	    java.util.Date dataUtil = null;

	    try {
	        dataUtil = sdf.parse(exameFuncionarioVo.getDataRealizacao());
	    } catch (ParseException e) {
	        e.printStackTrace();
	        throw new RuntimeException("Erro ao converter a data", e);
	    }

	    java.sql.Date sqlDate = (dataUtil != null) ? new java.sql.Date(dataUtil.getTime()) : null;

	    try (Connection con = getConexao();
	         PreparedStatement ps = con.prepareStatement(query.toString())) {
	        
	        ps.setString(1, exameFuncionarioVo.getFuncionarioId());
	        ps.setString(2, exameFuncionarioVo.getExameId());
	        ps.setDate(3, sqlDate);

	        try (ResultSet rs = ps.executeQuery()) {
	            return rs.next(); 
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new RuntimeException("Erro ao verificar se o exame já está registrado", e);
	    }
	}

	public List<ExameFuncionarioVo> findAllFuncionarioExames() {
		
		
		return null;
	}

}