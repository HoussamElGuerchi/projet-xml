<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"/>

	<div class="container mt-5">
		<h1>Modifer Produit</h1>
		<hr>
		<form method="post" action="controller">
			<div class="form-group">
				<label>Id</label>
				<input type="number" class="form-control" placeholder="" name="id" value="<%= request.getAttribute("id") %>" required>
			</div>
			<div class="form-group">
				<label>Libellé</label>
				<input type="text" class="form-control" placeholder="" name="label" value="<%= request.getAttribute("label") %>" required>
			</div>
			<div class="form-group">
				<label>Prix</label>
				<input type="number" class="form-control" placeholder="" name="price" value="<%= request.getAttribute("price") %>" required>
			</div>
			<div class="form-group">
				<label>Marque</label>
				<input type="text" class="form-control" placeholder="" name="brand" value="<%= request.getAttribute("brand") %>" required>
			</div>
			<div class="form-group">
				<label>Image</label>
				<input type="url" class="form-control" placeholder="" name="image" value="<%= request.getAttribute("image") %>">
			</div>
			<div class="center">
				<button class="btn btn-outline-primary" type="submit" name="modify">Modifier</button>
				<button type="submit" class="btn btn-outline-danger" name="delete">Supprimer</button>
			</div>
		</form>
	</div>

<jsp:include page="footer.jsp"/>