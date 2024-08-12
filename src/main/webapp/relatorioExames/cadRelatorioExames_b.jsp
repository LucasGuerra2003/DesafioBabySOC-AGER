<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Relatorio de Exames</title>
    <link rel='stylesheet' href='webjars/bootstrap/5.1.3/css/bootstrap.min.css'>
</head>
<body class="bg-secondary">
    <div class="container">
        <s:form action="gerarRelatoriosExame" method="post" onsubmit="prepararDados()">
            <div class="card mt-5">
                <div class="card-header">
                    <div class="row">
                        <div class="col-sm-2">
                            <s:url action="todosFuncionarios" var="todos"></s:url>
                            <a href="${todos}" class="btn btn-success">Voltar</a>
                        </div>
                        <div class="col-sm-8 text-center">
                            <h5 class="card-title">Exames dos Funcion�rios</h5>
                        </div>
                        <div class="col-sm-2 text-end">
                            <s:url action="receberRelatorioXLS" var="gerar"></s:url>
                            <a href="${gerar}" class="btn btn-success">GERAR XLS</a>
                        </div>
                    </div>
                </div>
                <div class="card-body">
                    <div class="form-group row mt-3">
                        <label for="dataRealizacaoInicial" class="col-sm-2 col-form-label">Data Inicial:</label>
                        <div class="col-sm-3">
                            <input type="date" id="dataRealizacaoInicial" name="dataRealizacaoInicial" class="form-control" />
                        </div>
                    </div>
                    <div class="form-group row mt-3">
                        <label for="dataRealizacaoFinal" class="col-sm-2 col-form-label">Data Final:</label>
                        <div class="col-sm-3">
                            <input type="date" id="dataRealizacaoFinal" name="dataRealizacaoFinal" class="form-control" />
                        </div>
                        <div class="col-sm-2">
                            <input type="hidden" name="outputFormat" value="html" />
                            <button type="submit" class="btn btn-primary">GERAR RELAT�RIO</button>
                        </div>
                    </div>
                    <s:textfield type="hidden" id="dataRealizacaoInicialFormatada" name="relatorioVo.dataRealizacaoInicial" />
                    <s:textfield type="hidden" id="dataRealizacaoFinalFormatada" name="relatorioVo.dataRealizacaoFinal" />
                </div>
                <table class="table table-striped text-center mt-3">
                    <thead>
                        <tr>
                            <th>ID Funcion�rio</th>
                            <th>Nome do Funcion�rio</th>
                            <th>ID Exame</th>
                            <th>Nome Exame</th>
                            <th>Data do Exame</th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="relatorios">
                            <tr>
                                <td><s:property value="funcionarioId" /></td>
                                <td><s:property value="nmFuncionario" /></td>
                                <td><s:property value="exameId" /></td>
                                <td><s:property value="nmExame" /></td>
                                <td><s:property value="dataRealizacao" /></td>
                            </tr>
                        </s:iterator>
                    </tbody>
                </table>
            </div>
        </s:form>
    </div>
    <script src="webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
    <script>
        function prepararDados() {
            const dataInicialInput = document.getElementById('dataRealizacaoInicial');
            const dataInicialFormatada = document.getElementById('dataRealizacaoInicialFormatada');

            let valorInicial = dataInicialInput.value.replace(/\D/g, '');
            if (valorInicial.length === 8) {
                let anoInicial = valorInicial.slice(0, 4);
                let mesInicial = valorInicial.slice(4, 6);
                let diaInicial = valorInicial.slice(6, 8);

                dataInicialFormatada.value = diaInicial + "-" + mesInicial + "-" + anoInicial;
            }
            const dataFinalInput = document.getElementById('dataRealizacaoFinal');
            const dataFinalFormatada = document.getElementById('dataRealizacaoFinalFormatada');

            let valorFinal = dataFinalInput.value.replace(/\D/g, '');
            if (valorFinal.length === 8) {
                let anoFinal = valorFinal.slice(0, 4);
                let mesFinal = valorFinal.slice(4, 6);
                let diaFinal = valorFinal.slice(6, 8);

                dataFinalFormatada.value = diaFinal + "-" + mesFinal + "-" + anoFinal;
            }

            // Atualiza os campos ocultos do formul�rio
            document.getElementById('dataRealizacaoInicialHidden').value = dataInicialFormatada.value;
            document.getElementById('dataRealizacaoFinalHidden').value = dataFinalFormatada.value;
        }
    </script>
</body>
</html>
