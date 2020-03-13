
/****************************** VARIABLE JOUEUR / ENNEMI ******************************/

var attaque = document.getElementById("attaque");
var magie = document.getElementById("magie");
var objet = document.getElementById("objet");
var fuir = document.getElementById("fuir");
var pVie = document.getElementById("Hp");
var pMagie = document.getElementById("Mp");


var pVieE = document.getElementById("pVieE");
var pMagieE = document.getElementById("pMagieE");

var x = document.getElementById("fight"); 
x.volume = 0.1;
x.autoplay = true;

/****************************** CONSTRUCTEUR PERSONNAGE (JOUEUR/ENNEMI)  ******************************/

function Personnage(nom, hp, mp, dmg){
	this.nom = nom;
	this.hp = hp;
	this.mp = mp;
	this.dmg = dmg;
}
/****************************** CREATION JOUEUR/ENNEMI  ******************************/

var playerOne = new Personnage("Cloud", 300, 100, 50);
var enemyOne = new Personnage("Sephiroth", 400, 100, 30);

/*
 *  Ajout d'un text correspondant au pVie et pMagie du joueur / ennemi 
 */
pVie.textContent = "Hp : "+playerOne.hp+" / 300";
pMagie.textContent = "Mp : "+playerOne.mp+"/100";

pVieE.textContent = "Hp : "+enemyOne.hp+ "/400";
pMagieE.textContent = "Mp : "+enemyOne.mp+"/100";


/****************************** FONCTION JOUEUR ******************************/


/******************* CHARGEMENT DE LA BARRE D'ACTION  *******************/

/*
 * 1er function : ajout de la couleur marron (permettant de voir la progression de la barre)
 * Démarrage de la function frame à interval de 40 milisecondes, stocké dans la variable 'id'
 * Si cette barre atteinds 100 (Soit la totalité de la barre : width = 96)
 * - Activation des différentes action du joueur
 * - La barre devient jaune ( indiquant qu'elle est chargé au maximum)
 * - Arrêt de l'interval 'id' et remise à 0
 * 
 * Sinon
 * - width incrémente de 1 ( toute les 40 milisecondes soit +25/sec
 * - on modifie (en direct) la largeur (=width) en lui ajoutant la valeur de ' width+"%" '
 */
function move() {
	var a = document.getElementById("infoCharge");
	var i = 0;
	$(function() {
		$('#charge').css('background-color','#ff8040');
	});
	if (i == 0) {
		i = 1;
		var elem = document.getElementById("charge");
		var width = 0;
		var id = setInterval(frame, 20);
		function frame() {
			if(width == 70){
				var ready = document.getElementById("ready");
				ready.volume = 0.2;
				ready.play();
			}
			if (width >= 97) {
				$(function(){
					$(':button').prop('disabled', false);
				});
				$(function() {
					$('#charge').css('background-color','yellow');
				});
				a.textContent = "Ready !";
				clearInterval(id);
				i = 0;
			} else {
				width++;
				elem.style.width = width + "%";
				
				a.textContent = width+3 +"%";
			}
		}
	}
}
/******************* EFFET CLIGNOTEMENT SUR L'ENNEMI  *******************/

/*
 *  function permettant un effet clignotement basique, après chaque action infligeant des dégats à l'ennemi 
 */
function fade(){ 
	$(function() {
		$("#ennemi").fadeOut("medium",function(){
			$(this).fadeIn("medium");
		}); 
	});
}

/******************* DESACTIVE BOUTON (ACTION DU JOUEUR)  *******************/

/*
 * function qui désactive tout les boutons (les actions du joueur en sommes)
 */

function disableBtn(){
	$(function(){
		$(':button').prop('disabled', true);
	});
}
/******************* CHARGEMENT DE LA BARRE DE CAST *******************/
/*
 * Suit la même logique que la barre d'action à quelque détails près
 * - On remplace le sprite de base du joueur par magicSB (=Magic Stand By)
 * - Interval plus rapide
 * - Appel de la function positionChange()
 * 
 */
function chargement(){
	$(function() {
		$('#joueur').replaceWith('<img id="joueur" src="/gameproject/resources/image/img/cloud_magicSB.gif">');
		var magie = document.getElementById("magic");
		magie.playbackRate = 2;
		magie.play();
		
		
		var i = 0;
		$(function() {
			$('#charge').css('background-color','purple');
		});
		if (i == 0) {
			i = 1;
			var elem = document.getElementById("charge");
			var width = 0;
			var id = setInterval(frame, 10);
			function frame() {
				if (width >= 100) {
					positionChange();
					clearInterval(id);
					i = 0;
					var foudre = document.getElementById("foudre");
					foudre.play();
				} else {
					width++;
					elem.style.width = width + "%";
				}
			}
		}
	});
}
/******************* CHANGEMENT DE SPRITE APRES INCANTATION  *******************/
/*
 * - On remplace le sprite magicSB du joueur par magicAtk
 * - On crée élement à ajouté au journal contenant l'action utilisé, ainsi que son nombre de dégat 
 * - Modification des Hp Ennemi et des Mp du joueur
 * - Actualisation de l'affichage de chacun via .textContent
 * - Après 1 seconde, on remplace le sprite magicAtk par celui de base
 * 
 * Si Hp ennemi <= 0
 * - l'opacité de l'ennemi diminue lentement jusqu'à disparition
 *  Après 1.5 secondes :
 * - On remplace le sprite de base par celui de victoire (=win)
 * - Ajout d'un message d'alert bloquant ainsi toute action en arrière plan
 * Sinon appel des différentes function (fade, move) pour poursuivre le combat
 * 
 */
function healthBar(){
	
	var a = (playerOne.hp*100)/300;
	$("#pVie").css({"width": a+"%"});
	if(a <= 50 && a > 0){
		$("#Hp").css({"color": "yellow"});
	} else if(a <= 0){
		$("#Hp").css({"color": "#ff1104"});
		$("#pVie").css({"width": "100%"});
		$("#pVie").css({"background-color": "black"});
	}
}
function magicBar(){
	
	var a = (playerOne.mp*100)/100;
	$("#pMagie").css({"width": a+"%"});
	if(a <= 50 && a > 0){
		$("#Mp").css({"color": "yellow"});
	}else if(a <= 0){
		$("#Mp").css({"color": "#ff1104"});
		$("#pMagie").css({"background-color": "grey"});
	}
}
function positionChange(){
	$(function() {
		$('#joueur').replaceWith('<img id="joueur" src="/gameproject/resources/image/img/cloud_magicAtk.gif">');

		var infoElt = document.createElement("p");
		infoElt.textContent = playerOne.nom +" utilise foudre et inflige 50 dégats !";
		enemyOne.hp -= 50;
		pVieE.textContent = "Hp : "+enemyOne.hp+ "/400";
		playerOne.mp -= 25;
		pMagie.textContent = "Mp : "+playerOne.mp+ "/100";
		magicBar()
		document.getElementById("infoCombat").insertBefore(infoElt, infoCombat.childNodes[0]);

		setTimeout(function(){
			$(function(){
				$('#joueur').replaceWith('<img id="joueur" src="/gameproject/resources/image/img/cloud_sprite.gif">');
			});

		}, 1000);

		if(enemyOne.hp <= 0){
			setTimeout(function(){
				var victory = document.getElementById("victory");
				victory.volume = 0.2;
				victory.play();
				x.pause();
				$(function(){
					$('#joueur').replaceWith('<img id="joueur " src="/gameproject/resources/image/img/cloud_win.gif">');
					alert("TIN TIN TIN TIIIIN, TIIIIN TIIIIN TIN TIN TIIIIN !!!!")
					
				});
			}, 1500);
			$(function(){
				$("#ennemi").fadeTo("slow", 0);
			})

		} else {
			fade();
			move();
		}
	});

}

/****************************** ACTION JOUEUR ******************************/

/********** ACTION JOUEUR : ATTAQUE  **********/
/*
 *  Ajout d'un évenemment Clic sur le bouton attaque exécutant ainsi
 *  - Désactivation de tout les boutons ( seul la function move peut les réactiver)
 *  - Ajout d'une entrée dans le journal de combat indiquant A attaque B infligeant X dégats
 *  - Modification des Hp de l'ennemi et actualisation de l'affichage via .textContent
 *  - Remplacement du sprite de base par celui attack (=attaque), après 1 seconde, retour au sprite initial
 *  
 *  Si Hp ennemi <= 0
 * - l'opacité de l'ennemi diminue lentement jusqu'à disparition
 *  Après 2 secondes :
 * - On remplace le sprite de base par celui de victoire (=win)
 * - Ajout d'un message d'alert bloquant ainsi toute action en arrière plan
 * Sinon appel des différentes function (fade, move) pour poursuivre le combat
 */
attaque.addEventListener("click", function () {

	disableBtn();

	var infoElt = document.createElement("p");
	infoElt.textContent = playerOne.nom +" attaque "+enemyOne.nom+" et inflige "+playerOne.dmg+" dégats !";
	document.getElementById("infoCombat").insertBefore(infoElt, infoCombat.childNodes[0]);
	enemyOne.hp -= 50;
	pVieE.textContent = "Hp : "+enemyOne.hp+ "/400"; 
	$(function() {
		$('#joueur').replaceWith('<img id="joueur" src="/gameproject/resources/image/img/cloud_attack.gif">');
	});
	var hit = document.getElementById("hit");
		hit.play();
	setTimeout(function(){
		$(function(){
			$('#joueur').replaceWith('<img id="joueur" src="/gameproject/resources/image/img/cloud_sprite.gif">');
		});

	}, 1000);
	if(enemyOne.hp <= 0){
		setTimeout(function(){
			var victory = document.getElementById("victory");
			victory.volume = 0.2;
			victory.play();
			x.pause();
			$(function(){
				$('#joueur').replaceWith('<img id="joueur" src="/gameproject/resources/image/img/cloud_win.gif">');
				alert("TIN TIN TIN TIIIIN, TIIIIN TIIIIN TIN TIN TIIIIN !!!!")
			});
		}, 2000);
		$(function(){
			$("#ennemi").fadeTo("slow", 0);
		})
	} else {
		fade();
		move();
	}
});

/********** ACTION JOUEUR : MAGIE  **********/
/*
 * Suit la même logique que ATTAQUE, hormis que cela est exécuté via la méthode chargement() 
 * Cette dernière est indiqué L.114
 * 
 */
magie.addEventListener("click", function(){
	if(playerOne.mp >=25){
		chargement()
		disableBtn();
	} else {
		var infoElt = document.createElement("p");
		infoElt.textContent = "Vous n'avez pas assez de Mp !";
	}
	document.getElementById("infoCombat").insertBefore(infoElt, infoCombat.childNodes[0]);


})

/********** ACTION JOUEUR : OBJET **********/
/*
 * Suit la même logique que ATTAQUE
 * la condition if permet de vérifié que le joueur ne récupère pas plus d'hp qu'il ne peut en avoir (ex: 310/300)
 * en fonction dans la situation, un message spécifique est indiqué dans le journal de combat
 * 
 */
objet.addEventListener("click", function(){

	var infoElt = document.createElement("p");
	if(playerOne.hp == 300){
		infoElt.textContent = "La potion n'a eu aucun effet !";
	} else if(playerOne.hp <= 250) {
		infoElt.textContent = playerOne.nom +" utilise une potion et récupère 50HP !";
		playerOne.hp += 50;
		pVie.textContent = "Hp : "+playerOne.hp+" / 300";
		var soin = document.getElementById("soin");
		soin.volume = 0.4;
		soin.playbackRate = 3;
		soin.play();
	} else{
		infoElt.textContent = playerOne.nom +" utilise une potion et récupère 50HP !";
		playerOne.hp = 300;
		pVie.textContent = "Hp : "+playerOne.hp+ "/300";
		var soin = document.getElementById("soin");
		soin.volume = 0.4;
		soin.play();
	}
	document.getElementById("infoCombat").insertBefore(infoElt, infoCombat.childNodes[0]);
	healthBar();
	disableBtn();
	move();
})

/********** ACTION JOUEUR : FUIR ( CHANGEMENT PREVU)  **********/

fuir.addEventListener("click", function(){

	var infoElt = document.createElement("p");
	infoElt.textContent = playerOne.nom +" Tente de fuir le combat !";
	document.getElementById("infoCombat").insertBefore(infoElt, infoCombat.childNodes[0]);
	disableBtn();
	move();
})
/****************************** FONCTION ENNEMI ******************************/

/******************* CHARGEMENT DE LA BARRE D'ACTION  *******************/

/*
 * Identique à celle du joueur mais avec un ajout 
 * 
 * -Démarrage UNIQUE de la function rotationE après 1 seconde.
 */
function eMove() {
	var i = 0;
	$(function() {
		$('#chargeE').css('background-color','#ff8040');
	});
	if (i == 0) {
		i = 1;
		var elem = document.getElementById("chargeE");
		var width = 0;
		var id = setInterval(frame, 40);

		function frame() {
			if (width >= 96) {
				$(function() {
					$('#chargeE').css('background-color','yellow');
				});
				clearInterval(id);
				i = 0;
				setTimeout(rotationE, 1000);
			} else {
				width++;
				elem.style.width = width + "%";
			}
		}
	}
}
/******************* EFFET CLIGNOTEMENT SUR JOUEUR  *******************/

/*
 *  function permettant un effet clignotement basique, après chaque action infligeant des dégats au joueur
 */
function fadeE(){ 
	$(function() {
		$("#joueur").fadeOut("slow",function(){
			$(this).fadeIn("slow");
		}); 
	});
}
/******************* CHARGEMENT DE LA BARRE DE CAST *******************/
/*
 * identique à celle du joueur  seul le nom de la function diffère, empéchant ainsi l'appel de 
 * la même fonction
 *  Voir L.114
 * 
 */
function chargementE(){

	$(function() {
		$('#ennemi').replaceWith('<img id="ennemi" src="/gameproject/resources/image/img/sephiroth_magicSB.gif">');
		var i = 0;
		$(function() {
			$('#chargeE').css('background-color','purple');
		});
		if (i == 0) {
			i = 1;
			var elem = document.getElementById("chargeE");
			var width = 0;
			var id = setInterval(frame, 10);
			function frame() {
				if (width >= 96) {

					clearInterval(id);
					i = 0;
					positionChangeE();
				} else {
					width++;
					elem.style.width = width + "%";
				}
			}
		}
	});
}

/******************* CHANGEMENT DE SPRITE APRES INCANTATION  *******************/
/*
 * identique à celle du joueur  seul le nom de la function diffère, empéchant ainsi l'appel de 
 * la même fonction
 *  Voir L.155
 */
function positionChangeE(){
	$(function() {
		$('#ennemi').replaceWith('<img id="ennemi" src="/gameproject/resources/image/img/sephiroth_magicAtk.gif">');

		var infoElt = document.createElement("p");
		infoElt.textContent = enemyOne.nom+" utilise feu et inflige 40 dégats !";
		playerOne.hp -= 40;
		pVie.textContent = "Hp : "+playerOne.hp+" / 300";
		healthBar();
		enemyOne.mp -= 20;
		pMagieE.textContent= "Mp : "+enemyOne.mp+"/100";

		if(playerOne.hp <= 0){
			setTimeout(function(){
				$(function(){
					$('#ennemi').replaceWith('<img id="ennemi" src="/gameproject/resources/image/img/sephiroth_win.gif">');
					alert("DUTY FAILED");
				});
			}, 3000);
			$(function(){
				$("#joueur").fadeTo(2000, 0);
			})
		} else {
			document.getElementById("infoCombat").insertBefore(infoElt, infoCombat.childNodes[0]);

			setTimeout(function(){
				$(function(){
					$('#ennemi').replaceWith('<img id="ennemi" src="/gameproject/resources/image/img/sephiroth_sprite.gif">');

				});

			}, 2000);

			fadeE();
			eMove();
		} 
	});



}

/****************************** ACTION ENNEMI (ALEATOIRE) ******************************/

//Démarrage UNIQUE de la rotation
setTimeout(rotationE, 1000);

/* 
 * CHOICE récupère un nombre ALEATOIRE compris entre le MIN et le MAX
 *  en fonction du nombre récupéré, une action est effectué (attaque, magie,...)
 */
function rotationE() { 

	var min = 1;
	var max = 16;
	var choice = Math.floor(Math.random() * (max - min)) + min;

	switch (choice) {

	/********** ACTION ENNEMI : ATTAQUE  **********/

	/*
	 * on crée un message spécifique à l'action, qui sera ajouté au journal
	 * 1er function : on replace le sprite de base par celui de l'attaque
	 * 2eme function : après 2.5 secondes, on remet le sprite de base
	 * 
	 */
	case 1: case 2: case 3: case 4: case 5: case 6: case 7: case 8: case 9: case 10:
		var infoElt = document.createElement("p");
		infoElt.textContent = enemyOne.nom+" attaque "+ playerOne.nom +" et inflige "+ enemyOne.dmg+" dégats !";
		playerOne.hp -= 30;
		pVie.textContent = "Hp : "+playerOne.hp+" / 300"; 
		healthBar();

		$(function() {
			$('#ennemi').replaceWith('<img id="ennemi" src="/gameproject/resources/image/img/sephiroth_attack.gif">');
		});
		setTimeout(function(){
			$(function(){
				$('#ennemi').replaceWith('<img id="ennemi" src="/gameproject/resources/image/img/sephiroth_sprite.gif">');
			});
		}, 2500);

		document.getElementById("infoCombat").insertBefore(infoElt, infoCombat.childNodes[0]);
		/*
		 * Si après action les Hp du joueur sont inférieur ou égal à 0
		 * - Le sprite du joueur disparais
		 * - Après 3 secondes : Changement du sprite de base pour celui de la victoire, ainsi qu'un message d'alert
		 *
		 * Sinon appel des différentes méthodes
		 */
		if(playerOne.hp <= 0){
			setTimeout(function(){
				$(function(){
					$('#ennemi').replaceWith('<img id="ennemi" src="/gameproject/resources/image/img/sephiroth_win.gif">');
					alert("DUTY FAILED");
				});
			}, 3000);
			$(function(){
				$("#joueur").fadeTo(2000, 0);
			})
		} else {
			fadeE();
			eMove();
		}
		break;

		/********** ACTION MAGIE : ATTAQUE  **********/

		/*
		 *  Si mp supérieur ou égal à 20; appel de la méthode chargementE()
		 *  Sinon message journal indiquant la manque de mana et appel de la méthode eMove()
		 */
	case 11: case 12:

		var infoElt = document.createElement("p");
		if(enemyOne.mp >=20){

			chargementE()

		} else {
			infoElt.textContent = "J'ai...je n'ai plus assez de Mp";
			eMove();
		}
		document.getElementById("infoCombat").insertBefore(infoElt, infoCombat.childNodes[0]);

		break;

		/********** ACTION ENNEMI : OBJET  **********/

	case 13:case 14:

		var infoElt = document.createElement("p");
		infoElt.textContent = enemyOne.nom+" se soigne et récupère 50Hp !";
		enemyOne.hp += 50;
		pVieE.textContent = "Hp : "+enemyOne.hp+ "/400";
		if(enemyOne.hp > 400){
			enemyOne.hp = 400;
			pVieE.textContent = "Hp : "+enemyOne.hp+ "/400";
		}
		document.getElementById("infoCombat").insertBefore(infoElt, infoCombat.childNodes[0]);
		eMove();
		break;

		/********** ACTION ENNEMI : CHARGE ATTAQUE (CHANGEMENT PREVU)  **********/
	case 15:
		var infoElt = document.createElement("p");
		infoElt.textContent = enemyOne.nom+" charge sa prochaine attaque !";
		document.getElementById("infoCombat").insertBefore(infoElt, infoCombat.childNodes[0]);
		eMove();
		break;

		/********** ACTION ENNEMI : OMAE WA WOU SHINDEIRU **********/
	default:
		var infoElt = document.createElement("p");
	infoElt.textContent = "Nani ?!";
	document.getElementById("infoCombat").insertBefore(infoElt, infoCombat.childNodes[0]);
	eMove();
	}
}



