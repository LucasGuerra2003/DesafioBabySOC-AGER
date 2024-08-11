<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title><s:text name="label.titulo.pagina.consulta" /></title>
<link rel='stylesheet'
	href='webjars/bootstrap/5.1.3/css/bootstrap.min.css'>
</head>
<body class="bg-secondary">	
	<h1>Bem-vindo ao sistema!</h1>

	<p>Selecione uma opção abaixo:</p>

	<ul>
		<li><s:a action="exames">Exames</s:a></li>
		<li><s:a action="funcionarios">Funcionários</s:a></li>
	</ul>

	<script src="webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
</body>
</html>