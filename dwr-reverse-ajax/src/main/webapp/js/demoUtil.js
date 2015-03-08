function updateAsync(eventData){
	var notificationContainer = document.getElementById("notificationContainer");
	notificationContainer.innerHTML = notificationContainer.innerHTML + "<br/>EVENT_DATA: " + eventData;
}