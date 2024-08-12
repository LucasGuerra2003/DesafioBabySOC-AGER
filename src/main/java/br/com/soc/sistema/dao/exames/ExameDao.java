package br.com.soc.sistema.dao.exames;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.soc.sistema.dao.Dao;
import br.com.soc.sistema.vo.ExameVo;

public class ExameDao extends Dao {
	
	private static final String FOI_INFORMADO_UM_ID_DE_EXAME_COM_FUNCIONARIO_CADASTRADO = "Foi informado o ID de um exame com funcionário ainda cadastrado";


	public void insertExame(ExameVo exameVo){
		StringBuilder query = new StringBuilder("INSERT INTO exame (nm_exame) values (?)");
		try(
				Connection con = getConexao();
				PreparedStatement  ps = con.prepareStatement(query.toString())){

			int i=1;
			ps.setString(i++, exameVo.getNome());
			ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<ExameVo> findAllExames(){
		StringBuilder query = new StringBuilder("SELECT rowid id, nm_exame nome FROM exame");
		try(
				Connection con = getConexao();
				PreparedStatement  ps = con.prepareStatement(query.toString());
				ResultSet rs = ps.executeQuery()){

			ExameVo vo =  null;
			List<ExameVo> exames = new ArrayList<>();
			while (rs.next()) {
				vo = new ExameVo();
				vo.setRowid(rs.getString("id"));
				vo.setNome(rs.getString("nome"));	

				exames.add(vo);
			}
			return exames;
		}catch (SQLException e) {
			e.printStackTrace();
		}

		return Collections.emptyList();
	}

	public List<ExameVo> findAllByNome(String nome){
		StringBuilder query = new StringBuilder("SELECT rowid id, nm_exame nome FROM exame ")
				.append("WHERE lower(nm_exame) like lower(?)");

		try(Connection con = getConexao();
				PreparedStatement ps = con.prepareStatement(query.toString())){
			int i = 1;

			ps.setString(i, "%"+nome+"%");

			try(ResultSet rs = ps.executeQuery()){
				ExameVo vo =  null;
				List<ExameVo> exames = new ArrayList<>();

				while (rs.next()) {
					vo = new ExameVo();
					vo.setRowid(rs.getString("id"));
					vo.setNome(rs.getString("nome"));	

					exames.add(vo);
				}
				return exames;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}		
		return Collections.emptyList();
	}

	public ExameVo findByCodigo(Integer codigo){
		StringBuilder query = new StringBuilder("SELECT rowid id, nm_exame nome FROM exame ")
				.append("WHERE rowid = ?");

		try(Connection con = getConexao();
				PreparedStatement ps = con.prepareStatement(query.toString())){
			int i = 1;

			ps.setInt(i, codigo);

			try(ResultSet rs = ps.executeQuery()){
				ExameVo vo =  null;

				while (rs.next()) {
					vo = new ExameVo();
					vo.setRowid(rs.getString("id"));
					vo.setNome(rs.getString("nome"));	
				}
				return vo;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}		
		return null;
	}

	public ExameVo updateExame(ExameVo exameVo) {
	    StringBuilder query = new StringBuilder("UPDATE exame SET nm_exame = ? ")
	            .append("WHERE rowid = ?");

	    try (Connection con = getConexao();
	         PreparedStatement ps = con.prepareStatement(query.toString())) {
	        
	        ps.setString(1, exameVo.getNome());
	        ps.setString(2, exameVo.getRowid());

	        ps.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return exameVo; 
	}
	
	public ExameVo deleteByCodigo(Integer id) {
		StringBuilder query = new StringBuilder("DELETE FROM exame ")
				.append("WHERE rowid = ?");

		try (	Connection con = getConexao();
				PreparedStatement ps = con.prepareStatement(query.toString())) {
			ps.setInt(1, id);
			ps.execute();
		} catch (SQLException e) {
			throw new RuntimeException(FOI_INFORMADO_UM_ID_DE_EXAME_COM_FUNCIONARIO_CADASTRADO);
		}
		return null;
	}
}