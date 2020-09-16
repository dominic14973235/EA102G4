$(document).ready(selectImage);
function selectImage() {

	// 1. 抓取DOM元素
	var myFile = document.getElementById("myFile");
	var filename = document.getElementById("fileName");
	var preview = document.getElementById('preview');
	var ifIamgeExist = document.getElementById('ifIamgeExist');
	// 2. 對myFile物件註冊change事件 - 改變選擇的檔案時觸發

//	$("myFile").change(function() {
//		if (preview.childNodes.length > 1) {
//			console.log("1231321354");
//			ifIamgeExist.remove();
//		}
//	})
	myFile.addEventListener('change', function(e) {
		

		
		ifIamgeExist.remove();
		// 取得檔案物件的兩種方式
		// 1. 直接從myFile物件上取得檔案物件 (因為非同步，一樣，多個classname註冊時會有問題)
		// var files = myFile.files;

		// 2. 從event物件中取得他的soure target，也就是myFile物件，再取得檔案物件
		var files = e.srcElement.files;

		// 檔案的基本資訊，包括：檔案的名稱、大小與文件型態
		console.log(files[0])

		// 判斷files物件是否存在
		if (files) {

			for (var i = 0; i < files.length; i++) {
				var file = files[i];

				// 判斷file.type的型別是否包含'image'
				if (file.type.indexOf('image') > -1) {
					// 填入檔名
					filename.value = file.name;
					// new a FileReader
					var reader = new FileReader();
					// 在FileReader物件上註冊load事件 - 載入檔案完成的意思
					reader.addEventListener('load', function(e) {
						// 取得結果 提示：從e.target.result取得讀取到結果
						var result = e.srcElement.result;
						// console.log(result) 確認讀取到結果
//						console.log(result);
						// 新增img元素
						var img = document.createElement('img');
						// var input = document.createElement('input');

						// 賦予src屬性
						img.src = result;
						img.style.maxWidth = "25%";
						img.style.maxHeight = "25%";
						img.value = "image"
						// // 賦予input屬性
						// input.value = files;
						// input.type = "hidden";
						// input.name = "p_photo";
						// 放到div裡面
						preview.append(img);
						// preview.append(input);

					});
					// 使用FileReader物件上的 readAsDataURL(file) 的方法，傳入要讀取的檔案，並開始進行讀取
					reader.readAsDataURL(file); // trigger!!
				} else {
					// 彈出警告視窗
					alert('請上傳圖片！');
				}
			}
		}
	});
}