<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Загрузить конфигурационный файл</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript" src="resources/js/jquery-3.0.0.min.js"></script>
	<link rel="stylesheet"  href="resources/css/mycss/hello.css" />
	<link rel="stylesheet"  href="resources/css/bootstrap.css" />
	<link rel="shortcut icon" href="/resources/images/favicon.png" type="image/png"/>
</head>
<body>
<div class="container">
	<div class="row">
		<form action="upload-json-conf" method="post" enctype="multipart/form-data">
			<div>
				<h6>JSON</h6>
				<input type="text" name="frakciya" maxlength="25" placeholder="Название фракции"/>
				<input type="file" id="file" name="file" accept="application/json"/>
			</div>
			<input value="Сохранить" class="btn btn-primary btn-sm" type="submit" />
		</form>
	</div>
</div>
</body>
</html>