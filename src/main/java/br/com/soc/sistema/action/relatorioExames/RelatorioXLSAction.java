package br.com.soc.sistema.action.relatorioExames;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import br.com.soc.sistema.business.ExameFuncionarioBusiness;
import br.com.soc.sistema.infra.Action;
import br.com.soc.sistema.vo.RelatorioVo;

public class RelatorioXLSAction extends Action {
    public List<RelatorioVo> relatorios = new ArrayList<>();
    private ExameFuncionarioBusiness business = new ExameFuncionarioBusiness();
    public RelatorioVo relatorioVo = new RelatorioVo();
    
    public String receber(){
        return SUCCESS;
    }

    public String gerar() throws IOException {
        relatorios.addAll(business.buscarExamesFuncinariosPorData(
            relatorioVo.getDataRealizacaoInicial(),
            relatorioVo.getDataRealizacaoFinal()
        ));

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Relatório de Exames");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("ID Exame Funcionario");
        header.createCell(1).setCellValue("ID Funcionário");
        header.createCell(2).setCellValue("Nome Funcionário");
        header.createCell(3).setCellValue("ID Exame");
        header.createCell(4).setCellValue("Nome Exame");
        header.createCell(5).setCellValue("Data de Realização");

        int rowNum = 1;
        for (RelatorioVo relatorio : relatorios) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(relatorio.getRowid());
            row.createCell(1).setCellValue(relatorio.getFuncionarioId());
            row.createCell(2).setCellValue(relatorio.getNmFuncionario());
            row.createCell(3).setCellValue(relatorio.getExameId());
            row.createCell(4).setCellValue(relatorio.getNmExame());
            row.createCell(5).setCellValue(relatorio.getDataRealizacao().toString());
        }

        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=Relatorio_Exames.xlsx");

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ServletOutputStream out = response.getOutputStream()) {
            workbook.write(baos);
            baos.writeTo(out);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return ERROR;
        } finally {
            workbook.close();
        }

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
