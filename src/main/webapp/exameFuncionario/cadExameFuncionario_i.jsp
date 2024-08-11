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
		<s:form action="/novoExameFuncionario.action"
			onsubmit="prepararDados()">
			<div class="card mt-5">
				<div class="card-header">
					<div class="row">
						<div class="col-sm-5">
							<s:url action="buscarExameFuncionario" var="buscar">
								<s:param name="exameFuncionarioVo.funcionarioId"
									value="exameFuncionarioVo.funcionarioId"></s:param>
							</s:url>
							<a href="${buscar}" class="btn btn-success">Voltar</a>
						</div>

						<div class="col-sm">
							<h5 class="card-title">Novo Exame do Funcionario</h5>
						</div>
					</div>
				</div>

				<div class="card-body">
					<div class="row align-items-center">
						<label for="id" class="col-sm-2 col-form-label text-center">Código
							do Funcionario: </label>
						<div class="col-sm-2">
							<s:textfield cssClass="form-control" id="id"
								name="exameFuncionarioVo.funcionarioId" readonly="true" />
						</div>
					</div>

					<div class="form-group row mt-3">
						<label for="exameId" class="col-sm-2 col-form-label text-center">Selecione
							o Exame:</label>
						<div class="col-sm-2">
							<s:select id="exameId" name="exameFuncionarioVo.exameId"
								list="todosExames" listKey="rowid" listValue="nome"
								cssClass="form-control" />
						</div>
					</div>

					<div class="form-group row mt-3">
						<label for="dataRealizacao" class="col-sm-2 col-form-label">Data
							de Realização:</label>
						<div class="col-sm-2">
							<input type="date" id="dataRealizacao" name="dataRealizacao"
								class="form-control" />
						</div>
						<s:textfield type="hidden" id="dataRealizacaoFormatada"
							name="exameFuncionarioVo.dataRealizacao" />
					</div>
				</div>

				<div class="card-footer">
					<div class="form-row">
						<button class="btn btn-primary col-sm-4 offset-sm-1">Salvar</button>
						<button type="reset"
							class="btn btn-secondary col-sm-4 offset-sm-2">Limpar
							Formulário</button>
					</div>
				</div>
			</div>
		</s:form>
	</div>

	<script src="webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
	<script>
		function prepararDados() {
			const input = document.getElementById('dataRealizacao');
			const dataFormatada = document
					.getElementById('dataRealizacaoFormatada');

			let valor = input.value.replace(/\D/g, ''); // Remove caracteres não numéricos
			if (valor.length === 8) {
				let ano = valor.slice(0, 4);
				let mes = valor.slice(4, 6);
				let dia = valor.slice(6, 8);

				dataFormatada.value = dia + "-" + mes + "-" + ano; // Formato yyyy-MM-dd
			}
		}
	</script>
</body>
</html>
