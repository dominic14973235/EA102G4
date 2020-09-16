$(document).ready(listAllTrackingAjax);

function deleteProductTracking(obj) {
	// $(".deleteProductTrackingBtn").click(function() {

	// });
}

function listAllTrackingAjax() {
	$.ajax({
		type : "POST",
		url : "../../product_tracking/ProductTracking.do",
		data : {
			"action" : "initProductTrackingProduct",
		},
		dataType : "json",
		success : function(data) {
			for (x in data) {
				var tr = document.createElement("tr");

				var p2 = document.createElement("p");
				var p3 = document.createElement("p");
				var p4 = document.createElement("p");

				var td1 = document.createElement("td");
				var td2 = document.createElement("td");
				var td3 = document.createElement("td");
				var td4 = document.createElement("td");

				var img = document.createElement("img");
				img.src = "data:image/jpg;base64," + data[x]["base64Image"]
				img.style.maxWidth = "122px";
				img.style.maxHeight = "122px";

				var a = document.createElement("a");
				a.href = "javascript:void(0)";
				a.className = "btn btn-link deleteProductTrackingBtn";
				a.style.fontFamily = "Microsoft JhengHei";
				a.style.textDecoration = "underline";
				// a.onclick = deleteProductTracking(data[x]["productNo"]);
				a.onclick = function() {
					console.log("this : "
							+ this.parentNode.parentNode.parentNode);
					console.log("sibling: " + this.parentNode.lastChild);
					var thisA = this.parentNode.parentNode.parentNode;
					var thisInput = this.parentNode.lastChild;
					console.log(thisInput.value);
					var thisInputValue = thisInput.value;
					// thisA.css('display','none');
					thisA.remove();
					$.ajax({
						type : "POST",
						url : "../../product_tracking/ProductTracking.do",
						data : {
							"action" : "deleteProductTracking",
							"memId" : $(".memId").val(),
							"productNo" : thisInputValue
						},
						// dataType : "json",
						success : function(data) {
							// alert("已加入追蹤清單");
						},
						error : function() {
							alert("AJAX-class發生錯誤囉!")
						}
					});
				};
				// a.style.display = "none";
				a.innerHTML = "取消追蹤";
				var a2 = document.createElement("a");
				a2.href = "javascript:void(0)";
				
				
				var inputA = document.createElement("input");
				inputA.type = "hidden";
				inputA.value = data[x]["productNo"];
				inputA.name = "productNo";

				a2.className = "font-semibold";
				a2.style.color="#515151";
				a2.onclick=function(){
					console.log("this: " + this.parentNode.parentNode);
					var thisA2Form = this.parentNode.parentNode;
					thisA2Form.submit();
				};
				a2.innerHTML = data[x]["pName"];
				
				
				p2.append(a2);
				p3.innerHTML = data[x]["pPrice"] + "元";
				p4.append(a);
				p4.append(inputA);

				var input2 = document.createElement("input");
				input2.type = "hidden";
				input2.name = "productNo";
				input2.value = data[x]["productNo"];
				var input3 = document.createElement("input");
				input3.type = "hidden";
				input3.name = "action";
				input3.value = "getOneForDisplay";

				var form = document.createElement("form");
				form.method = "post";
				form.action = "../../product/product.do";
				form.append(p2)
				form.append(input2);
				form.append(input3);

				td1.append(img);
				td2.append(form);
				td3.append(p3);
				td4.append(p4);

				tr.append(td1);
				tr.append(td2);
				tr.append(td3);
				tr.append(td4);
				$(".productTbody").append(tr);

				// <a href="javascript:void(0)"
				// class="btn btn-link deleteProductTrackingBtn"
				// style="font-family: Microsoft JhengHei; padding-top: 25px;
				// padding-right: 25px;display:none;">取消追蹤</a>
			}
		},
		error : function() {
			alert("AJAX-class發生錯誤囉!");
		}
	});

}

function sub(obj) {
	console.log("obj:"  + obj);
	// o.submit();
}
