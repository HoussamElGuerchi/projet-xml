<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<html>
			<head>
				<title>Lander</title>
				<link rel="stylesheet"
					href="https://bootswatch.com/4/lux/bootstrap.min.css" />
				<style>
					#footer {
					padding: 7% 15%;
					}

					.logo {
					font-family: 'Times New
					Roman', serif;
					Display SC', serif;
					}

					.white {
					color: #ffffff;
					}

					.center {
					text-align:
					center;
					margin: auto;
					}

					.landing{
					background-image:
					url("images/landing-bg.jpg");
					background-size: cover;
					background-position: top;
					padding: 10% 15%;
					}

					.features{
					height: 50%;
					}

					.features-col {
					height: 100%;
					width: 50%;
					}

					.maker {
					background-image:
					url("images/watch-maker.png");
					background-size: cover;
					background-position: center;
					}

					.fixer {
					background-image:
					url("images/watch-fixer.png");
					background-size: cover;
					background-position: center;
					}
				</style>

				<!-- Icons -->
				<script src="https://kit.fontawesome.com/56420e11fd.js"
					crossorigin="anonymous"></script>
			</head>
			<body>
				<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
					<a class="navbar-brand logo" href="index.jsp">Lander</a>
					<button class="navbar-toggler" type="button"
						data-toggle="collapse" data-target="#navbarNav"
						aria-controls="navbarNav" aria-expanded="false"
						aria-label="Toggle navigation">
						<span class="navbar-toggler-icon"></span>
					</button>

					<div class="collapse navbar-collapse" id="navbarNav">
						<ul class="navbar-nav ml-auto mr-4">
							<li class="nav-item mr-3 ml-3">
								<a class="nav-link" href="index.jsp">
									<i class="fas fa-home"></i>
									Acceuil
								</a>
							</li>
							<li class="nav-item mr-3 ml-3">
								<a class="nav-link" href="/controller">
									<i class="fas fa-store-alt"></i>
									Boutique
								</a>
							</li>
							<li class="nav-item mr-3 ml-3">
								<a class="nav-link" href="ajouter.jsp">
									<i class="fas fa-plus"></i>
									Ajouter
								</a>
							</li>
						</ul>
					</div>
				</nav>

				<div class="container mt-5">
					<h1>Collections:</h1>
					<hr />
					<div class="row">
						<div class="card-deck">
							<xsl:for-each select="/products/product">
								<div class="col-lg-4 col-md-6 mb-5">
									<div class="card">
										<img class="card-img-top p-2"
											style="height: 300px; width: 180px; margin: auto;">
											<xsl:attribute name="src">
												<xsl:value-of select='image' />
											</xsl:attribute>
										</img>
										<div class="card-body">
											<h5 class="card-title">
												<a>
													<xsl:variable name="path" select="'controller?id='"  />
													<xsl:attribute name="href">
														<xsl:value-of select="concat($path,@id)" />
													</xsl:attribute>
													<xsl:value-of select="label" />
												</a>
											</h5>
											<hr />
											<h5 class="card-title">
												<xsl:value-of select="price" />
												dh
											</h5>
											<hr />
											<p class="card-text">
												Marque:
												<xsl:value-of select="brand" />
											</p>
										</div>
									</div>
								</div>
							</xsl:for-each>
						</div>
					</div>
				</div>
				<section id="footer">
					<div class="container center">
						<h3>Suivez nous sur:</h3>
						<p>
							<i class="fab fa-instagram p-3"></i>
							<i class="fab fa-facebook-f p-3"></i>
							<i class="fab fa-twitter p-3"></i>
							<i class="fab fa-youtube p-3"></i>
						</p>
						<p>© Réalisé par EL GUERCHI Houssam &amp; ELBAGAR Hamza - MQL,
							FSDM - 2021</p>
					</div>
				</section>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>