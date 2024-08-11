<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title><s:text name="label.titulo.pagina.cadastro" /></title>
<link rel='stylesheet'
	href='webjars/bootstrap/5.1.3/css/bootstrap.min.css'>
</head>
<body class="bg-secondary">

	<div class="container">
		<s:form action="novoExameFuncionario">

			<div class="card mt-5">
				<div class="card-header">
					<div class="row">
						<div class="col-sm-5">
							<s:url action="buscarExameFuncionario" var="exames">
								<s:param name="exameFuncionarioVo.funcionarioId"
									value="exameFuncionarioVo.funcionarioId"></s:param>
							</s:url>

							<a href="${exames}" class="btn btn-primary"
								class="col-sm-2 offset-sm-1"> <s:text name="label.exames" />
							</a>
						</div>

						<div class="col-sm">
							<h5 class="card-title">Cadastrar Exames do Funcionário</h5>
						</div>
					</div>
				</div>
				<input type="hidden" name="exameFuncionarioVo.funcionarioId" value="${funcionarioId}" />

				<div class="card-body">
					<div class="form-group row">
						<label for="exameId" class="col-sm-3 col-form-label">Selecione
							o Exame:</label>
						<div class="col-sm-9">
							<s:select id="exameId" name="exameFuncionarioVo.exameId"
								list="todosExames" listKey="rowid" listValue="nome"
								cssClass="form-control" />
						</div>
					</div>

					<div class="form-group row mt-3">
						<label for="dataRealizacao" class="col-sm-3 col-form-label">Data
							de Realização:</label>
						<div class="col-sm-9">
							<input type="date" id="dataRealizacao"
								name="exameFuncionarioVo.dataRealizacao" class="form-control" />
						</div>
					</div>
				</div>

				<div class="card-footer">
					<div class="form-row">
						<button class="btn btn-primary col-sm-4 offset-sm-1">Salvar</button>
					</div>
				</div>
			</div>
		</s:form>
	</div>

	<script src="webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
</body>
</html>
