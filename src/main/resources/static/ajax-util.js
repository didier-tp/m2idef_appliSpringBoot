//url : url d'une chose coté serveur (ex: php , servlet java , WS REST , ...)
//callback : callback pour traiter la réponse (asynchrone)
function makeAjaxGetRequest(url,callback) {
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && (xhr.status == 200 || xhr.status == 0)) {
			callback(xhr.responseText);
		}
	};
	xhr.open("GET", url, true);
	xhr.send(null);
}