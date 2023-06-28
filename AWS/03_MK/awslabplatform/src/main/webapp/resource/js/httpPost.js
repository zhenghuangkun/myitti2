/**************************************
 * httpPost js
 *************************************/

		//发送POST请求跳转到指定页面
		function httpPost(URL, PARAMS) {
		    var temp = document.createElement("form");
		    temp.action = URL;
		    temp.method = "post";
		    temp.style.display = "none";

		    for (var x in PARAMS) {
		        var opt = document.createElement("textarea");
		        opt.name = x;
		        opt.value = PARAMS[x];
		        temp.appendChild(opt);
		    }

		    document.body.appendChild(temp);
		    temp.submit();

		    return temp;
		}