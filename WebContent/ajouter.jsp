<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"/>

	<div class="container mt-5">
		<h1>Nouveau Produit:</h1>
		<hr>
		<form method="post" action="controller">
			<div class="form-group">
				<label>Id</label>
				<input type="number" class="form-control" placeholder="" name="id" required>
			</div>
			<div class="form-group">
				<label>Libellé</label>
				<input type="text" class="form-control" placeholder="" name="label" required>
			</div>
			<div class="form-group">
				<label>Prix</label>
				<input type="number" class="form-control" placeholder="" name="price" required>
			</div>
			<div class="form-group">
				<label>Marque</label>
				<input type="text" class="form-control" placeholder="" name="brand" required>
			</div>
			<div class="form-group">
				<label>Image</label>
				<input type="url" class="form-control" placeholder="" name="image">
			</div>
			<div class="center">
				<button class="btn btn-primary" type="submit" name="create">Ajouter</button>
			</div>
		</form>
	</div>

<jsp:include page="footer.jsp"/>