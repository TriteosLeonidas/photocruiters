var expanded = false;

function showCheckboxes(elementId) {
  var checkboxes = document.getElementById(elementId);
  if (!expanded) {
    checkboxes.style.display = "block";
    expanded = true;
  } else {
    checkboxes.style.display = "none";
    expanded = false;
  }
}

function updateSelected(checkbox, selectedOption) {
	
	var selOpt = document.getElementById(selectedOption);
	
	if(checkbox.checked) {
		
		if(selOpt.text=="Παρακαλώ επιλέξτε...")
			selOpt.text = "";
		
		selOpt.text  = selOpt.text + " " + checkbox.name;
		selOpt.value = selOpt.value + " " + checkbox.value;
	} else {
		selOpt.text = selOpt.text.replace(checkbox.name," ");
		selOpt.value = selOpt.value.replace(" " + checkbox.value, "");
		
		if(selOpt.text.trim()==" " || selOpt.text.trim()=="")
			selOpt.text = "Παρακαλώ επιλέξτε...";
		
	}
	
	/*if(selOpt.text.endsWith(","))
		selOpt.text = selOpt.text.slice(0,selOpt.text.length-1);
	
	if(selOpt.value.endsWith(","))
		selOpt.value = selOpt.value.slice(0,selOpt.value.length-1);
	*/
}

