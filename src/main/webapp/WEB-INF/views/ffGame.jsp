<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="ISO-8859-1"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="<c:url value="/resources/css/ffGame.css" />" rel="stylesheet">
<title>Test Roleplay Js</title>
</head>
<body>
	<audio controls id="ready">
		<source src="/gameproject/resources/image/BGM/Cursor_Ready.mp4" type="audio/mpeg">
	</audio>
	<audio controls id="magic">
		<source src="/gameproject/resources/image/BGM/Use_Magic.mp3" type="audio/mpeg">
	</audio>
	<audio controls id="foudre">
		<source src="/gameproject/resources/image/BGM/Foudre.mp3" type="audio/mpeg">
	</audio>
	<audio controls id="hit">
		<source src="/gameproject/resources/image/BGM/Hit.mp3" type="audio/mpeg">
	</audio>
	<audio controls id="victory">
		<source src="/gameproject/resources/image/BGM/Victory_Fanfare.mp3" type="audio/mpeg">
	</audio>
	<audio controls id="soin">
		<source src="/gameproject/resources/image/BGM/Soin.mp3" type="audio/mpeg">
	</audio>
	<audio controls id="fight">
		<source src="/gameproject/resources/image/BGM/Fight_Theme.mp3" type="audio/mpeg">
	</audio>
	<div id="combattant">

		<img src="/gameproject/resources/image/img/cloud_sprite.gif" id="joueur" />
		<!-- 	<img src="img/cloud_magicSB.gif" id="test" name="magicSB" />
		<img src="img/cloud_magicAtk.gif" id="test" name="magicAtk"  />
		<img src="img/cloud_attack.gif" id="test" name="attack" /> -->

		<img src="/gameproject/resources/image/img/sephiroth_sprite.gif" id="ennemi" />
	</div>
	<section id="stats">

		<div id="myStats">

			<h3 id="Player">Cloud</h3>
			<div id="backG">
				<div id="infoStats">

					<div id="backGV">
						<p id="Hp"></p>
						<div id="pVie"></div>
					</div>


					<div id="backGM">
						<p id="Mp">Mp: 100/100</p>
						<p id="pMagie"></p>
					</div>

					<div id="backGC">
						<p id="infoCharge">Ready !</p>
						<div id="charge"></div>
					</div>
				</div>



			</div>
		</div>
		<div id="ennemiStats">
			<div id="backG">
				<p id="pVieE"></p>
				<p id="pMagieE"></p>
				<div id="chargeE"></div>
			</div>
		</div>
	</section>
	<div id="all">
		<section id="information">

			<h3>Action</h3>
			<article id="choix">
				<div id="bouton">
					<button id="attaque">Attaque</button>
					<button id="magie">Magie</button>
					<button id="objet">Objet</button>
					<button id="fuir">Fuir</button>
				</div>
			</article>
		</section>

		<section id="journal">
			<h3>Journal de combat</h3>
			<article id="infoCombat"></article>
		</section>
	</div>

	<script src="<c:url value="/resources/js/jquery-3.4.1.js" />"></script>
	<script src="<c:url value="/resources/js/ffGame.js" />"></script>
</body>
</html>