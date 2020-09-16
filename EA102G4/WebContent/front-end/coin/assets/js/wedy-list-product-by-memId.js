$(document).ready(
		function() {
			var pStatus = document.getElementsByClassName('pStatus');
			var updateProductInputBtn = document
					.getElementsByName('updateProductInputBtn');
			var offProductInputBtn = document
					.getElementsByName('offProductInputBtn');
			console.log(pStatus);
			console.log(updateProductInputBtn);
			console.log(offProductInputBtn);
			for (x in pStatus) {
				console.log(pStatus[x].value);
				if (pStatus[x].value == 20) {
					updateProductInputBtn[x].style.display = "none";
					offProductInputBtn[x].style.display = "none";
				}
			}
		})
function parent3FormSub(obj) {
	var o = obj.parentNode.parentNode;
	o.submit();
}

$(document).ready(function() {

	$('.confirmModal').click(function(e) {
		e.preventDefault();
		$.confirmModal('確定要將商品下架嗎?', function(el) {
			console.log("Ok was clicked!");

			// do delete operation
		});
	});

});
var objParent = null;
function deleteProduct(obj) {
	objParent = obj.parentNode;
	console.log('obj.parentNode : ' + objParent);
	// $("#wedy-delete-btn").on('click', function() {
	// objParent.submit();
	// });
}
function deleteProduct2() {
	objParent.submit();
}