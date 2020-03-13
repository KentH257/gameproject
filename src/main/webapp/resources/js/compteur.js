
var start = document.getElementById("start");
var stop = document.getElementById("stop");
var save = document.getElementById("save");
var compt = document.getElementById("compte");
var tirelire = document.getElementById("tirelire");


var currentNumber = 0;
var totalCurrentI = 0;
var i = 0;
stop.disabled = true;
start.addEventListener("click", function () {
	
	var id = setInterval(frame, 1000);
	 i = currentNumber;
	
	start.disabled = true;
	stop.disabled = false;
	
	function frame(){
		if( i < 10000){
		i++;
		compt.textContent = i+" €";
		} else {
			clearInterval(id);
	}
	
	}
	stop.addEventListener("click", function(){
		
		clearInterval(id);
		currentNumber = i;
		start.disabled = false;
		stop.disabled = true;
	});
	save.addEventListener("click", function(){
		
		var currentI = i + totalCurrentI;
		i = 0;
		currentNumber = 0;
		totalCurrentI = currentI;
		compt.textContent = i+" €";
		tirelire.textContent = "Tirelire : "+ totalCurrentI+" €";
		i = 0;
		
	})
});

