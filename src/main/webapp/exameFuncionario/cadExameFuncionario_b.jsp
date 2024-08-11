<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Exames do Funcionário</title>
<link rel='stylesheet'
	href='webjars/bootstrap/5.1.3/css/bootstrap.min.css'>
</head>
<body class="bg-secondary">
	<div class="container">
		<s:form action="buscarExameFuncionario.action">
			<div class="card mt-5">
				<div class="card-header">
					<div class="row">
						<div class="col-sm-5">
							<s:url action="todosFuncionarios" var="todos" />
							<a href="${todos}" class="btn btn-success">Voltar</a>
						</div>
						<div class="col-sm">
							<h5 class="card-title">Exames do Funcionário</h5>
						</div>
						<div class="col-sm d-flex justify-content-end">
							<div class="btn-group" role="group">
								<s:url action="?" var="relatorio">
									<s:param name="exameFuncionarioVo.funcionarioId"
										value="exameFuncionarioVo.funcionarioId"></s:param>
										<s:param name="exameFuncionarioVo.funcionarioId"
										value="exameFuncionarioVo.funcionarioId"></s:param>
								</s:url>
								<a href="${relatorio}" class="btn btn-warning me-2">RELATORIO
									DOS EXAMES</a>

								<s:url action="todosExameFuncionario" var="exames">
									<s:param name="exameFuncionarioVo.funcionarioId"
										value="exameFuncionarioVo.funcionarioId"></s:param>
								</s:url>
								<a href="${exames}" class="btn btn-primary">NOVO EXAME</a>
							</div>
						</div>
					</div>
				</div>
				<div class="card-body">
					<s:iterator value="examesFuncionarios" status="status">
						<s:if test="%{#status.index == 0}">
							<div class="row align-items-center">
								<label for="funcionarioId"
									class="col-sm-2 col-form-label text-center">ID:</label>
								<div class="col-sm-3">
									<s:textfield cssClass="form-control" id="funcionarioId"
										name="exameFuncionarioVo.funcionarioId" readonly="true"
										value="%{funcionarioId}" />
								</div>
							</div>

							<div class="row align-items-center mt-3">
								<label for="nmFuncionario"
									class="col-sm-2 col-form-label text-center">Funcionario:</label>
								<div class="col-sm-3">
									<s:textfield cssClass="form-control" id="nmFuncionario"
										readonly="true" value="%{nmFuncionario}" />
								</div>
							</div>
						</s:if>
					</s:iterator>
					<table class="table table-striped text-center mt-3">
						<thead>
							<tr>
								<th style="width: 30%">ID Exame</th>
								<th style="width: 40%">Nome Exame</th>
								<th style="width: 30%">Data do Exame</th>
								<th style="width: 40%">Ações</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="examesFuncionarios">
								<tr>
									<td><s:property value="exameId" /></td>
									<td><s:property value="nmExame" /></td>
									<td><s:property value="dataRealizacao" /></td>
									<td class="text-end">
										<div class="btn-group" role="group" aria-label="Ações">
											<s:url action="todosExameFuncionario" var="editar">
												<s:param name="exameFuncionarioVo.rowid" value="rowid"></s:param>
												<s:param name="exameFuncionarioVo.funcionarioId"
													value="%{funcionarioId}"></s:param>
											</s:url>
											<a href="${editar}" class="btn btn-warning text-white"> <s:text
													name="label.editar" />
											</a>

											<s:url action="excluirExame" var="excluir">
												<s:param name="exameId" value="%{exameId}" />
												<s:param name="funcionarioId" value="%{funcionarioId}" />
											</s:url>
											<a href="${excluir}" class="btn btn-danger"
												data-bs-toggle="modal" data-bs-target="#confirmarExclusao"
												data-rowid="<s:property value='rowid' />"
												data-funcionarioId="<s:property value='%{funcionarioId}' />">
												EXCLUIR </a>
										</div>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
				<div class="modal fade" id="confirmarExclusao"
					data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
					aria-labelledby="staticBackdropLabel" aria-hidden="true">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title">
									<s:text name="label.modal.titulo" />
								</h5>
								<button type="button" class="btn-close" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>
							<div class="modal-body">
								<span><s:text name="label.modal.corpo" /></span>
							</div>
							<div class="modal-footer">
								<a class="btn btn-secondary" data-bs-dismiss="modal"
									aria-label="Close"><s:text name="label.nao" /></a> <a href="#"
									id="excluir" class="btn btn-primary" style="width: 75px;"><s:text
										name="label.sim" /></a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</s:form>
	</div>
	<script src="webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
	<script>
		document.addEventListener("DOMContentLoaded", function() {
			var modal = document.getElementById("confirmarExclusao");
			modal.addEventListener("show.bs.modal", function(event) {
				var button = event.relatedTarget; // Button that triggered the modal
				var rowid = button.getAttribute('data-rowid');
				var funcionarioId = button.getAttribute('data-funcionarioId');
				var url = "<s:url action='excluirExameFuncionario'/>"; // Get the URL of the exclusion action
				url += "?exameFuncionarioVo.rowid=" + rowid
						+ "&exameFuncionarioVo.funcionarioId=" + funcionarioId; // Append rowid and funcionarioId as parameters
				var excluirLink = document.getElementById("excluir");
				excluirLink.href = url; // Set the href of the delete button
			});
		});
	</script>
</body>
</html>
